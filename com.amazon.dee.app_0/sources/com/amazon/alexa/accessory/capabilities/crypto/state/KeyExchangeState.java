package com.amazon.alexa.accessory.capabilities.crypto.state;

import com.amazon.alexa.accessory.capabilities.crypto.metrics.KeyExchangeMetrics;
import com.amazon.alexa.accessory.capabilities.crypto.state.KeyExchangeState;
import com.amazon.alexa.accessory.capabilities.crypto.state.MessageHandlers;
import com.amazon.alexa.accessory.capabilities.crypto.state.event.EventHandlers;
import com.amazon.alexa.accessory.capabilities.crypto.state.event.KeyExchangeEvent;
import com.amazon.alexa.accessory.capabilities.crypto.state.event.KeyExchangeEventHandler;
import com.amazon.alexa.accessory.crypto.CryptoKeyDataStore;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.transport.TransportFeature;
import com.amazon.dee.app.dependencies.ServiceModule;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public enum KeyExchangeState {
    INITIALIZING(new MessageHandlers() { // from class: com.amazon.alexa.accessory.capabilities.crypto.state.NoSupportedMessagesHandlers
        {
            Collections.emptyMap();
        }
    }, EventHandlers.builder().addHandler(KeyExchangeEvent.TRANSPORT_FEATURES_DISCOVERED, new KeyExchangeEventHandler<Set<TransportFeature>>() { // from class: com.amazon.alexa.accessory.capabilities.crypto.state.event.InitializationTransportFeaturesEventHandler
        @Override // com.amazon.alexa.accessory.capabilities.crypto.state.event.KeyExchangeEventHandler
        public KeyExchangeState handleEvent(Set<TransportFeature> set, String str, CryptoKeyDataStore cryptoKeyDataStore, KeyExchangeMetrics keyExchangeMetrics) {
            KeyExchangeState keyExchangeState;
            Preconditions.notNull(set, "eventData");
            Preconditions.notNull(str, "accessoryId");
            Preconditions.notNull(cryptoKeyDataStore, ServiceModule.DATA_STORE);
            Preconditions.notNull(keyExchangeMetrics, "metrics");
            if (!set.contains(TransportFeature.AUTHENTICATION)) {
                keyExchangeState = KeyExchangeState.NO_KEYS_REQUIRED;
            } else {
                keyExchangeState = cryptoKeyDataStore.getData(str) != null ? KeyExchangeState.HAS_KEYS : KeyExchangeState.NO_KEYS;
            }
            keyExchangeMetrics.onInitializationComplete(keyExchangeState);
            return keyExchangeState;
        }
    }).build(), null),
    NO_KEYS(new NoKeysMessageHandlers(), EventHandlers.NO_SUPPORTED_EVENTS, new TimeoutCondition() { // from class: com.amazon.alexa.accessory.capabilities.crypto.state.KeyExchangeState.1
        @Override // com.amazon.alexa.accessory.capabilities.crypto.state.TimeoutCondition
        public KeyExchangeState getOnTimeoutState() {
            return KeyExchangeState.IRRECOVERABLE_ERROR;
        }

        @Override // com.amazon.alexa.accessory.capabilities.crypto.state.TimeoutCondition
        public long getTimeoutMs() {
            return TimeUnit.SECONDS.toMillis(300L);
        }
    }),
    HAS_KEYS_AWAITING_COMPLETE_HANDSHAKE(new HasKeysAwaitingCompleteHandshakeMessageHandlers(), EventHandlers.NO_SUPPORTED_EVENTS, new TimeoutCondition() { // from class: com.amazon.alexa.accessory.capabilities.crypto.state.KeyExchangeState.2
        @Override // com.amazon.alexa.accessory.capabilities.crypto.state.TimeoutCondition
        public KeyExchangeState getOnTimeoutState() {
            return KeyExchangeState.HAS_KEYS;
        }

        @Override // com.amazon.alexa.accessory.capabilities.crypto.state.TimeoutCondition
        public long getTimeoutMs() {
            return TimeUnit.SECONDS.toMillis(20L);
        }
    }),
    HAS_KEYS(new HasKeysMessageHandlers(), EventHandlers.NO_SUPPORTED_EVENTS, null),
    HAS_KEYS_AWAITING_CONFIRM_RESET(new HasKeysAwaitingConfirmResetMessageHandlers(), EventHandlers.NO_SUPPORTED_EVENTS, new TimeoutCondition() { // from class: com.amazon.alexa.accessory.capabilities.crypto.state.KeyExchangeState.3
        @Override // com.amazon.alexa.accessory.capabilities.crypto.state.TimeoutCondition
        public KeyExchangeState getOnTimeoutState() {
            return KeyExchangeState.HAS_KEYS;
        }

        @Override // com.amazon.alexa.accessory.capabilities.crypto.state.TimeoutCondition
        public long getTimeoutMs() {
            return TimeUnit.SECONDS.toMillis(20L);
        }
    }),
    NO_KEYS_REQUIRED(new MessageHandlers() { // from class: com.amazon.alexa.accessory.capabilities.crypto.state.ResetRootKeyOnlyMessageHandlers
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            new MessageHandlers.MessageHandlersBuilder().build();
        }
    }, EventHandlers.NO_SUPPORTED_EVENTS, null),
    IRRECOVERABLE_ERROR(new MessageHandlers() { // from class: com.amazon.alexa.accessory.capabilities.crypto.state.ResetRootKeyOnlyMessageHandlers
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            new MessageHandlers.MessageHandlersBuilder().build();
        }
    }, EventHandlers.NO_SUPPORTED_EVENTS, null);
    
    private final EventHandlers stateEventHandlers;
    private final MessageHandlers stateMessageHandlers;
    private final TimeoutCondition timeoutCondition;

    KeyExchangeState(MessageHandlers messageHandlers, EventHandlers eventHandlers, TimeoutCondition timeoutCondition) {
        this.stateMessageHandlers = messageHandlers;
        this.stateEventHandlers = eventHandlers;
        this.timeoutCondition = timeoutCondition;
    }

    public Set<Accessories.Command> getAllSupportedCommands() {
        return this.stateMessageHandlers.getAllSupportedCommands();
    }

    public final TransitionResponse getResponse(Accessories.Command command, Object obj, String str, CryptoKeyDataStore cryptoKeyDataStore, KeyExchangeMetrics keyExchangeMetrics) {
        return this.stateMessageHandlers.getResponse(command, obj, str, cryptoKeyDataStore, keyExchangeMetrics);
    }

    public TimeoutCondition getTimeoutCondition() {
        return this.timeoutCondition;
    }

    public final KeyExchangeState nextState(KeyExchangeEvent keyExchangeEvent, Object obj, String str, CryptoKeyDataStore cryptoKeyDataStore, KeyExchangeMetrics keyExchangeMetrics) {
        return this.stateEventHandlers.handleEvent(keyExchangeEvent, obj, str, cryptoKeyDataStore, keyExchangeMetrics);
    }
}
