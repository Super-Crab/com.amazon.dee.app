package com.amazon.alexa.accessory.capabilities.crypto.state;

import com.amazon.alexa.accessory.capabilities.crypto.metrics.KeyExchangeMetrics;
import com.amazon.alexa.accessory.crypto.CryptoKeyDataStore;
import com.amazon.alexa.accessory.protocol.Keyexchange;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.crypto.state.-$$Lambda$HasKeysMessageHandlers$o9sHVm4BJ0DgMDOsS1XtuvW7Dw0  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$HasKeysMessageHandlers$o9sHVm4BJ0DgMDOsS1XtuvW7Dw0 implements MessageHandler {
    public static final /* synthetic */ $$Lambda$HasKeysMessageHandlers$o9sHVm4BJ0DgMDOsS1XtuvW7Dw0 INSTANCE = new $$Lambda$HasKeysMessageHandlers$o9sHVm4BJ0DgMDOsS1XtuvW7Dw0();

    private /* synthetic */ $$Lambda$HasKeysMessageHandlers$o9sHVm4BJ0DgMDOsS1XtuvW7Dw0() {
    }

    @Override // com.amazon.alexa.accessory.capabilities.crypto.state.MessageHandler
    public final TransitionResponse getResponse(Object obj, String str, CryptoKeyDataStore cryptoKeyDataStore, KeyExchangeMetrics keyExchangeMetrics) {
        return HasKeysMessageHandlers.lambda$getResetKeyHandler$0((Keyexchange.ResetKey) obj, str, cryptoKeyDataStore, keyExchangeMetrics);
    }
}
