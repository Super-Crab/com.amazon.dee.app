package com.amazon.alexa.accessory.capabilities.crypto.state;

import com.amazon.alexa.accessory.capabilities.crypto.metrics.KeyExchangeMetrics;
import com.amazon.alexa.accessory.crypto.CryptoKeyDataStore;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.crypto.state.-$$Lambda$MessageHandlers$Nck6DFc6Wh2AP9Y5QIqg4lYkHMI  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$MessageHandlers$Nck6DFc6Wh2AP9Y5QIqg4lYkHMI implements MessageHandler {
    public static final /* synthetic */ $$Lambda$MessageHandlers$Nck6DFc6Wh2AP9Y5QIqg4lYkHMI INSTANCE = new $$Lambda$MessageHandlers$Nck6DFc6Wh2AP9Y5QIqg4lYkHMI();

    private /* synthetic */ $$Lambda$MessageHandlers$Nck6DFc6Wh2AP9Y5QIqg4lYkHMI() {
    }

    @Override // com.amazon.alexa.accessory.capabilities.crypto.state.MessageHandler
    public final TransitionResponse getResponse(Object obj, String str, CryptoKeyDataStore cryptoKeyDataStore, KeyExchangeMetrics keyExchangeMetrics) {
        return MessageHandlers.lambda$static$0(obj, str, cryptoKeyDataStore, keyExchangeMetrics);
    }
}
