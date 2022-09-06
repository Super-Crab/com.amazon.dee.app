package com.amazon.alexa.accessory.capabilities.crypto.state;

import com.amazon.alexa.accessory.capabilities.crypto.metrics.KeyExchangeMetrics;
import com.amazon.alexa.accessory.crypto.CryptoKeyDataStore;
import com.amazon.alexa.accessory.protocol.Keyexchange;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.crypto.state.-$$Lambda$HasKeysAwaitingConfirmResetMessageHandlers$YibTI0ayPyvDdrYZzJ_qo0Yr5Zk  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$HasKeysAwaitingConfirmResetMessageHandlers$YibTI0ayPyvDdrYZzJ_qo0Yr5Zk implements MessageHandler {
    public static final /* synthetic */ $$Lambda$HasKeysAwaitingConfirmResetMessageHandlers$YibTI0ayPyvDdrYZzJ_qo0Yr5Zk INSTANCE = new $$Lambda$HasKeysAwaitingConfirmResetMessageHandlers$YibTI0ayPyvDdrYZzJ_qo0Yr5Zk();

    private /* synthetic */ $$Lambda$HasKeysAwaitingConfirmResetMessageHandlers$YibTI0ayPyvDdrYZzJ_qo0Yr5Zk() {
    }

    @Override // com.amazon.alexa.accessory.capabilities.crypto.state.MessageHandler
    public final TransitionResponse getResponse(Object obj, String str, CryptoKeyDataStore cryptoKeyDataStore, KeyExchangeMetrics keyExchangeMetrics) {
        return HasKeysAwaitingConfirmResetMessageHandlers.lambda$getConfirmResetKeyHandler$0((Keyexchange.ConfirmResetKey) obj, str, cryptoKeyDataStore, keyExchangeMetrics);
    }
}
