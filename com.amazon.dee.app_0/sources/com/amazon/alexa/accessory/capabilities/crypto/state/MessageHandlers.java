package com.amazon.alexa.accessory.capabilities.crypto.state;

import com.amazon.alexa.accessory.capabilities.crypto.metrics.KeyExchangeMetrics;
import com.amazon.alexa.accessory.crypto.CryptoKeyDataStore;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.Keyexchange;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class MessageHandlers {
    private static final MessageHandler UNEXPECTED_MESSAGE_HANDLER = $$Lambda$MessageHandlers$Nck6DFc6Wh2AP9Y5QIqg4lYkHMI.INSTANCE;
    private final Map<Accessories.Command, MessageHandler> handlers;

    /* loaded from: classes.dex */
    public static final class MessageHandlersBuilder {
        private static final MessageHandler<Keyexchange.ResetRootKeys> RESET_ROOT_KEYS_RESPONSE = $$Lambda$MessageHandlers$MessageHandlersBuilder$5wSOqzuIIey2CzBAtJViV0nlH4U.INSTANCE;
        private final ImmutableMap.Builder<Accessories.Command, MessageHandler> messageHandlers = new ImmutableMap.Builder<>();

        public MessageHandlersBuilder() {
            registerHandler(Accessories.Command.RESET_ROOT_KEYS, RESET_ROOT_KEYS_RESPONSE);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ TransitionResponse lambda$static$0(Keyexchange.ResetRootKeys resetRootKeys, String str, CryptoKeyDataStore cryptoKeyDataStore, KeyExchangeMetrics keyExchangeMetrics) {
            Logger.d("Removing keys due to RESET_ROOT_KEYS");
            cryptoKeyDataStore.removeData(str);
            keyExchangeMetrics.completeWithError(MetricsConstants.KeyExchange.IrrecoverableErrorCause.RESET_ROOT_KEY);
            return new TransitionResponse(KeyExchangeState.IRRECOVERABLE_ERROR, Accessories.Response.newBuilder().setErrorCode(Common.ErrorCode.SUCCESS).mo10084build());
        }

        public Map<Accessories.Command, MessageHandler> build() {
            return this.messageHandlers.mo7826build();
        }

        public <T> MessageHandlersBuilder registerHandler(Accessories.Command command, MessageHandler<T> messageHandler) {
            this.messageHandlers.mo7828put(command, messageHandler);
            return this;
        }
    }

    public MessageHandlers(Map<Accessories.Command, MessageHandler> map) {
        Preconditions.notNull(map, "handlers");
        this.handlers = map;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ TransitionResponse lambda$static$0(Object obj, String str, CryptoKeyDataStore cryptoKeyDataStore, KeyExchangeMetrics keyExchangeMetrics) {
        Logger.w("Unexpected message, clearing keys and setting state to IRRECOVERABLE FAILURE");
        cryptoKeyDataStore.removeData(str);
        keyExchangeMetrics.completeWithError(MetricsConstants.KeyExchange.IrrecoverableErrorCause.UNEXPECTED_MESSAGE);
        return new TransitionResponse(KeyExchangeState.IRRECOVERABLE_ERROR, Accessories.Response.newBuilder().setErrorCode(Common.ErrorCode.INTERNAL).mo10084build());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Set<Accessories.Command> getAllSupportedCommands() {
        return this.handlers.keySet();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final TransitionResponse getResponse(Accessories.Command command, Object obj, String str, CryptoKeyDataStore cryptoKeyDataStore, KeyExchangeMetrics keyExchangeMetrics) {
        MessageHandler messageHandler = this.handlers.get(command);
        if (messageHandler == null) {
            Logger.w("Receive command %s, no handler registered", command);
            return UNEXPECTED_MESSAGE_HANDLER.getResponse(obj, str, cryptoKeyDataStore, keyExchangeMetrics);
        }
        return messageHandler.getResponse(obj, str, cryptoKeyDataStore, keyExchangeMetrics);
    }
}
