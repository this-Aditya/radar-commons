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
package org.radarbase.producer.rest

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.time.Duration

/**
 * Current connection status of a KafkaSender. After a timeout occurs this will turn to
 * disconnected. When the connection is dropped, the associated KafkaSender should set this to
 * disconnected, when it successfully connects, it should set it to connected. This class is
 * thread-safe. The state transition diagram is CONNECTED to and from DISCONNECTED with
 * [.didConnect] and [.didDisconnect]; CONNECTED to and from UNKNOWN with
 * [.getState] after a timeout occurs and [.didConnect]; and UNKNOWN to DISCONNECTED
 * with [.didDisconnect].
 *
 *
 * A connection state could be shared with multiple HTTP clients if they are talking to the same
 * server.
 *
 * @param timeout timeout after which the connected state will be reset to unknown.
 * @throws IllegalArgumentException if the timeout is not strictly positive.
 */
class ConnectionState(
    private val timeout: Duration,
    scope: CoroutineScope = CoroutineScope(EmptyCoroutineContext)
) {
    /** State symbols of the connection.  */
    enum class State {
        CONNECTED, DISCONNECTED, UNKNOWN, UNAUTHORIZED
    }

    val scope = scope + Job()

    private val mutableState = MutableStateFlow(State.UNKNOWN)

    @OptIn(ExperimentalCoroutinesApi::class)
    val state: Flow<State> = mutableState
        .transformLatest { state ->
            emit(state)
            if (state == State.CONNECTED) {
                delay(timeout)
                emit(State.UNKNOWN)
            }
        }
        .shareIn(this.scope + Dispatchers.Unconfined, SharingStarted.Eagerly, replay = 1)

    init {
        mutableState.value = State.UNKNOWN
    }

    /** For a sender to indicate that a connection attempt succeeded.  */
    fun didConnect() {
        mutableState.value = State.CONNECTED
    }

    /** For a sender to indicate that a connection attempt failed.  */
    fun didDisconnect() {
        mutableState.value = State.DISCONNECTED
    }

    fun wasUnauthorized() {
        mutableState.value = State.UNAUTHORIZED
    }

    fun reset() {
        mutableState.value = State.UNKNOWN
    }
}
