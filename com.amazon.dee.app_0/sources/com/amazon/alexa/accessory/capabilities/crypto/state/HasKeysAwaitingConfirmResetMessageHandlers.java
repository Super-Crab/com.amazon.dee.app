package com.amazon.alexa.accessory.capabilities.crypto.state;

import com.amazon.alexa.accessory.capabilities.crypto.metrics.KeyExchangeMetrics;
import com.amazon.alexa.accessory.capabilities.crypto.state.MessageHandlers;
import com.amazon.alexa.accessory.crypto.CryptoKeyDataStore;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.Keyexchange;
/* loaded from: classes.dex */
class HasKeysAwaitingConfirmResetMessageHandlers extends MessageHandlers {
    /* JADX INFO: Access modifiers changed from: package-private */
    public HasKeysAwaitingConfirmResetMessageHandlers() {
        super(new MessageHandlers.MessageHandlersBuilder().registerHandler(Accessories.Command.CONFIRM_RESET_KEY, getConfirmResetKeyHandler()).build());
    }

    private static MessageHandler<Keyexchange.ConfirmResetKey> getConfirmResetKeyHandler() {
        return $$Lambda$HasKeysAwaitingConfirmResetMessageHandlers$YibTI0ayPyvDdrYZzJ_qo0Yr5Zk.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ TransitionResponse lambda$getConfirmResetKeyHandler$0(Keyexchange.ConfirmResetKey confirmResetKey, String str, CryptoKeyDataStore cryptoKeyDataStore, KeyExchangeMetrics keyExchangeMetrics) {
        boolean isSuccessful = confirmResetKey.getIsSuccessful();
        Logger.d("Reset completed, isSuccessful=%b", Boolean.valueOf(isSuccessful));
        if (!isSuccessful) {
            Logger.w("Using unsuccessfully rotated derived keys, root key may still be in sync.");
            keyExchangeMetrics.completeWithError(MetricsConstants.KeyExchange.IrrecoverableErrorCause.ACCESSORY_FAILURE);
        } else {
            keyExchangeMetrics.onKeyRotationComplete();
        }
        return new TransitionResponse(KeyExchangeState.HAS_KEYS, Accessories.Response.newBuilder().setErrorCode(Common.ErrorCode.SUCCESS).mo10084build());
    }
}
