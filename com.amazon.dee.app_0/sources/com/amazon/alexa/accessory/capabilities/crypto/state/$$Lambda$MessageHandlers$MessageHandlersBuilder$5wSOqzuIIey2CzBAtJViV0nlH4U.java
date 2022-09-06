package com.amazon.alexa.accessory.capabilities.crypto.state;

import com.amazon.alexa.accessory.capabilities.crypto.metrics.KeyExchangeMetrics;
import com.amazon.alexa.accessory.capabilities.crypto.state.MessageHandlers;
import com.amazon.alexa.accessory.crypto.CryptoKeyDataStore;
import com.amazon.alexa.accessory.protocol.Keyexchange;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.crypto.state.-$$Lambda$MessageHandlers$MessageHandlersBuilder$5wSOqzuIIey2CzBAtJViV0nlH4U  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$MessageHandlers$MessageHandlersBuilder$5wSOqzuIIey2CzBAtJViV0nlH4U implements MessageHandler {
    public static final /* synthetic */ $$Lambda$MessageHandlers$MessageHandlersBuilder$5wSOqzuIIey2CzBAtJViV0nlH4U INSTANCE = new $$Lambda$MessageHandlers$MessageHandlersBuilder$5wSOqzuIIey2CzBAtJViV0nlH4U();

    private /* synthetic */ $$Lambda$MessageHandlers$MessageHandlersBuilder$5wSOqzuIIey2CzBAtJViV0nlH4U() {
    }

    @Override // com.amazon.alexa.accessory.capabilities.crypto.state.MessageHandler
    public final TransitionResponse getResponse(Object obj, String str, CryptoKeyDataStore cryptoKeyDataStore, KeyExchangeMetrics keyExchangeMetrics) {
        return MessageHandlers.MessageHandlersBuilder.lambda$static$0((Keyexchange.ResetRootKeys) obj, str, cryptoKeyDataStore, keyExchangeMetrics);
    }
}
