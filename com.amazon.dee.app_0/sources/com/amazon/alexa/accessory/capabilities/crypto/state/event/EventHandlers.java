package com.amazon.alexa.accessory.capabilities.crypto.state.event;

import com.amazon.alexa.accessory.capabilities.crypto.metrics.KeyExchangeMetrics;
import com.amazon.alexa.accessory.capabilities.crypto.state.KeyExchangeState;
import com.amazon.alexa.accessory.capabilities.crypto.state.event.EventHandlers;
import com.amazon.alexa.accessory.crypto.CryptoKeyDataStore;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import java.util.EnumMap;
/* loaded from: classes.dex */
public final class EventHandlers {
    public static final EventHandlers NO_SUPPORTED_EVENTS = builder().build();
    private final EnumMap<KeyExchangeEvent, KeyExchangeEventHandler> handlers;

    /* loaded from: classes.dex */
    public static class Builder {
        private final EnumMap<KeyExchangeEvent, KeyExchangeEventHandler> handlers = new EnumMap<>(KeyExchangeEvent.class);

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ KeyExchangeState lambda$build$0(KeyExchangeEvent keyExchangeEvent, Object obj, String str, CryptoKeyDataStore cryptoKeyDataStore, KeyExchangeMetrics keyExchangeMetrics) {
            Logger.w("EventHandlers: Received unsupported event, %s. Transitioning to Irrecoverable Error.", keyExchangeEvent);
            keyExchangeMetrics.completeWithError(MetricsConstants.KeyExchange.IrrecoverableErrorCause.UNEXPECTED_EVENT);
            return KeyExchangeState.IRRECOVERABLE_ERROR;
        }

        public Builder addHandler(KeyExchangeEvent keyExchangeEvent, KeyExchangeEventHandler keyExchangeEventHandler) {
            Preconditions.notNull(keyExchangeEvent, "event");
            Preconditions.notNull(keyExchangeEventHandler, "handler");
            this.handlers.put((EnumMap<KeyExchangeEvent, KeyExchangeEventHandler>) keyExchangeEvent, (KeyExchangeEvent) keyExchangeEventHandler);
            return this;
        }

        public EventHandlers build() {
            KeyExchangeEvent[] values;
            for (final KeyExchangeEvent keyExchangeEvent : KeyExchangeEvent.values()) {
                if (!this.handlers.containsKey(keyExchangeEvent)) {
                    this.handlers.put((EnumMap<KeyExchangeEvent, KeyExchangeEventHandler>) keyExchangeEvent, (KeyExchangeEvent) new KeyExchangeEventHandler() { // from class: com.amazon.alexa.accessory.capabilities.crypto.state.event.-$$Lambda$EventHandlers$Builder$Y2B9DQBbKsGFeYr_zO1hATVy3A0
                        @Override // com.amazon.alexa.accessory.capabilities.crypto.state.event.KeyExchangeEventHandler
                        public final KeyExchangeState handleEvent(Object obj, String str, CryptoKeyDataStore cryptoKeyDataStore, KeyExchangeMetrics keyExchangeMetrics) {
                            return EventHandlers.Builder.lambda$build$0(KeyExchangeEvent.this, obj, str, cryptoKeyDataStore, keyExchangeMetrics);
                        }
                    });
                }
            }
            return new EventHandlers(this.handlers);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public KeyExchangeState handleEvent(KeyExchangeEvent keyExchangeEvent, Object obj, String str, CryptoKeyDataStore cryptoKeyDataStore, KeyExchangeMetrics keyExchangeMetrics) {
        return this.handlers.get(keyExchangeEvent).handleEvent(obj, str, cryptoKeyDataStore, keyExchangeMetrics);
    }

    private EventHandlers(EnumMap<KeyExchangeEvent, KeyExchangeEventHandler> enumMap) {
        this.handlers = enumMap;
    }
}
