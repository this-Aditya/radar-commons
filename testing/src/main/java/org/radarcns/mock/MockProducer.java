/*
 * Copyright 2017 The Hyve and King's College London
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

package org.radarcns.mock;

import static org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG;
import static org.radarcns.util.serde.AbstractKafkaAvroSerde.SCHEMA_REGISTRY_CONFIG;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.avro.specific.SpecificRecord;
import org.radarcns.data.SpecificRecordEncoder;
import org.radarcns.key.MeasurementKey;
import org.radarcns.producer.KafkaSender;
import org.radarcns.producer.SchemaRetriever;
import org.radarcns.producer.direct.DirectSender;
import org.radarcns.producer.rest.BatchedKafkaSender;
import org.radarcns.producer.rest.RestSender;
import org.radarcns.util.serde.KafkaAvroSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A Mock Producer class that can be used to stream data. It can use MockFile and MockDevice for
 * testing purposes, with direct or indirect streaming.
 */
public class MockProducer {

    private static final Logger logger = LoggerFactory.getLogger(MockProducer.class);

    private final List<MockDevice<MeasurementKey>> devices;
    private final List<MockFile> files;
    private final List<KafkaSender<MeasurementKey, SpecificRecord>> senders;

    public MockProducer(BasicMockConfig mockConfig) throws IOException {
        int numDevices = 0;
        if (mockConfig.getData() != null) {
            numDevices = mockConfig.getData().size();
        } else if (mockConfig.getNumberOfDevices() != 0) {
            numDevices = mockConfig.getNumberOfDevices();
        } else {
            logger.error(
                    "Error simulating mock device setup. Please provide data or number_of_devices");
        }

        try {
            senders = createSenders(mockConfig, numDevices);
        } catch (KeyManagementException exec) {
            logger.error("Sender cannot be created.", exec);
            throw new ExceptionInInitializerError(exec);
        } catch (NoSuchAlgorithmException exec) {
            logger.error("Sender cannot be created.", exec);
            throw new ExceptionInInitializerError(exec);
        }

        devices = new ArrayList<>(numDevices);
        files = new ArrayList<>(numDevices);

        String userId = "UserID_";
        String sourceId = "SourceID_";

        if (mockConfig.getData() == null) {
            for (int i = 0; i < numDevices; i++) {
                devices.add(new MockDevice<>(senders.get(i), new MeasurementKey(userId + i,
                        sourceId + i), MeasurementKey.getClassSchema(), MeasurementKey.class));
            }
        } else {
            try {
                for (int i = 0; i < numDevices; i++) {
                    File mockFile = new File(mockConfig.getData().get(i).getAbsoluteDataFile());
                    files.add(new MockFile(senders.get(i), mockFile, mockConfig.getData().get(i)));
                }
            } catch (NoSuchMethodException | IllegalAccessException
                    | InvocationTargetException | ClassNotFoundException ex) {
                throw new IOException("Cannot instantiate mock file", ex);
            }
        }
    }

    private List<KafkaSender<MeasurementKey, SpecificRecord>> createSenders(
            BasicMockConfig mockConfig, int numDevices)
            throws KeyManagementException, NoSuchAlgorithmException {
        List<KafkaSender<MeasurementKey, SpecificRecord>> result = new ArrayList<>(numDevices);
        try (SchemaRetriever retriever = new SchemaRetriever(mockConfig.getSchemaRegistry(), 10)) {

            if (mockConfig.isDirectProducer()) {
                for (int i = 0; i < numDevices; i++) {
                    Properties properties = new Properties();
                    properties.put(KEY_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
                    properties.put(VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
                    properties.put(SCHEMA_REGISTRY_CONFIG, retriever);
                    properties.put(BOOTSTRAP_SERVERS_CONFIG, mockConfig.getBrokerPaths());

                    result.add(new DirectSender<MeasurementKey, SpecificRecord>(properties));
                }
            } else {
                for (int i = 0; i < numDevices; i++) {
                    RestSender<MeasurementKey, SpecificRecord> firstSender = new RestSender<>(
                            mockConfig.getRestProxy(), retriever,
                            new SpecificRecordEncoder(false), new SpecificRecordEncoder(false),
                            10, mockConfig.hasCompression(), mockConfig.isUnsafeProducer());

                    result.add(new BatchedKafkaSender<>(firstSender, 10_000, 1000));
                }
            }
        }
        return result;
    }

    public void start() throws IOException, InterruptedException {
        for (MockDevice device : devices) {
            device.start();
        }
        for (MockFile file : files) {
            file.send();
        }
    }

    public void shutdown() throws IOException, InterruptedException {
        if (devices.isEmpty()) {
            return;
        }
        logger.info("Shutting down mock devices");
        for (MockDevice device : devices) {
            device.shutdown();
        }
        logger.info("Waiting for mock devices to finish...");
        for (MockDevice device : devices) {
            device.join(5_000L);
        }
        logger.info("Closing channels");
        for (KafkaSender<MeasurementKey, SpecificRecord> sender : senders) {
            sender.close();
        }
    }
}
