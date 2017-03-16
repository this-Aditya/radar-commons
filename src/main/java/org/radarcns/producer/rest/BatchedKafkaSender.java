/*
 * Copyright 2017 Kings College London and The Hyve
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.radarcns.producer.rest;

import org.radarcns.data.Record;
import org.radarcns.topic.AvroTopic;
import org.radarcns.producer.KafkaSender;
import org.radarcns.producer.KafkaTopicSender;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BatchedKafkaSender<K, V> implements KafkaSender<K, V> {
    private final KafkaSender<K, V> sender;
    private final int ageMillis;
    private final int maxBatchSize;

    public BatchedKafkaSender(KafkaSender<K, V> sender, int ageMillis, int maxBatchSize) {
        this.sender = sender;
        this.ageMillis = ageMillis;
        this.maxBatchSize = maxBatchSize;
    }

    @Override
    public <L extends K, W extends V> KafkaTopicSender<L, W> sender(final AvroTopic<L, W> topic) throws IOException {
        return new KafkaTopicSender<L, W>() {
            List<Record<L, W>> cache = new ArrayList<>();
            KafkaTopicSender<L, W> topicSender = sender.sender(topic);

            @Override
            public void send(long offset, L key, W value) throws IOException {
                if (!isConnected()) {
                    throw new IOException("Cannot send records to unconnected producer.");
                }
                cache.add(new Record<>(offset, key, value));

                if (exceedsBuffer(cache)) {
                    topicSender.send(cache);
                    cache.clear();
                }
            }

            @Override
            public void send(List<Record<L, W>> records) throws IOException {
                if (records.isEmpty()) {
                    return;
                }
                if (cache.isEmpty()) {
                    if (exceedsBuffer(records)) {
                        topicSender.send(records);
                    } else {
                        cache.addAll(records);
                    }
                } else {
                    cache.addAll(records);

                    if (exceedsBuffer(cache)) {
                        topicSender.send(cache);
                        cache.clear();
                    }
                }
            }

            @Override
            public long getLastSentOffset() {
                return topicSender.getLastSentOffset();
            }

            @Override
            public synchronized void clear() {
                cache.clear();
                topicSender.clear();
            }

            @Override
            public void flush() throws IOException {
                if (!cache.isEmpty()) {
                    topicSender.send(cache);
                    cache.clear();
                }
                topicSender.flush();
            }

            @Override
            public synchronized void close() throws IOException {
                try {
                    flush();
                } finally {
                    sender.close();
                }
            }

            private boolean exceedsBuffer(List<Record<L, W>> records) {
                return records.size() >= maxBatchSize || System.currentTimeMillis() - records.get(0).milliTimeAdded >= ageMillis;
            }
        };
    }

    @Override
    public boolean isConnected() {
        return sender.isConnected();
    }

    @Override
    public boolean resetConnection() {
        return sender.resetConnection();
    }

    @Override
    public synchronized void close() throws IOException {
        sender.close();
    }
}
