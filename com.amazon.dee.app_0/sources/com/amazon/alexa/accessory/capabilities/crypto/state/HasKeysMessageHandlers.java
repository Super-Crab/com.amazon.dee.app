package com.amazon.alexa.accessory.capabilities.crypto.state;

import com.amazon.alexa.accessory.capabilities.crypto.metrics.KeyExchangeMetrics;
import com.amazon.alexa.accessory.capabilities.crypto.state.MessageHandlers;
import com.amazon.alexa.accessory.crypto.CryptoKeyDataStore;
import com.amazon.alexa.accessory.crypto.cipher.Nonce;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.Keyexchange;
import com.google.protobuf.ByteString;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
/* loaded from: classes.dex */
class HasKeysMessageHandlers extends MessageHandlers {
    /* JADX INFO: Access modifiers changed from: package-private */
    public HasKeysMessageHandlers() {
        super(new MessageHandlers.MessageHandlersBuilder().registerHandler(Accessories.Command.RESET_KEY, getResetKeyHandler()).build());
    }

    private static ByteString generateHmac(String str, Key key, Nonce nonce, Nonce nonce2) throws NoSuchAlgorithmException, InvalidKeyException {
        Preconditions.notNull(str, "macAlgorithm");
        Preconditions.notNull(key, "authenticationKey");
        Preconditions.notNull(nonce, "nonceAccessory");
        Preconditions.notNull(nonce2, "nonceApplication");
        Mac mac = Mac.getInstance(str);
        mac.init(key);
        mac.update(nonce.toByteArray());
        mac.update(nonce2.toByteArray());
        return ByteString.copyFrom(mac.doFinal());
    }

    private static TransitionResponse getInternalErrorResponse() {
        return new TransitionResponse(KeyExchangeState.HAS_KEYS, Accessories.Response.newBuilder().setErrorCode(Common.ErrorCode.INTERNAL).mo10084build());
    }

    private static MessageHandler<Keyexchange.ResetKey> getResetKeyHandler() {
        return $$Lambda$HasKeysMessageHandlers$o9sHVm4BJ0DgMDOsS1XtuvW7Dw0.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ TransitionResponse lambda$getResetKeyHandler$0(Keyexchange.ResetKey resetKey, String str, CryptoKeyDataStore cryptoKeyDataStore, KeyExchangeMetrics keyExchangeMetrics) {
        CryptoKeyDataStore.NegotiatedData negotiatedData;
        keyExchangeMetrics.onKeyRotationStart();
        Nonce nonce = new Nonce(resetKey.getNonceAccessory());
        Nonce random = Nonce.random();
        try {
            CryptoKeyDataStore.NegotiatedData rotateDerivedKeys = cryptoKeyDataStore.rotateDerivedKeys(str, nonce, random);
            try {
                ByteString generateHmac = generateHmac(rotateDerivedKeys.cipherSuite.macAlgorithm, rotateDerivedKeys.authenticationKey, nonce, random);
                Accessories.Response.Builder newBuilder = Accessories.Response.newBuilder();
                keyExchangeMetrics.onKeyRotationAcknowledged();
                return new TransitionResponse(KeyExchangeState.HAS_KEYS_AWAITING_CONFIRM_RESET, newBuilder.setAcknowledgeResetKey(Keyexchange.AcknowledgeResetKey.newBuilder().setNonceApp(random.getNonceValue()).setValidationHmac(generateHmac).mo10084build()).mo10084build());
            } catch (InvalidKeyException unused) {
                Logger.w("Failing key rotation, invalid authentication key");
                keyExchangeMetrics.onResetKeysError(MetricsConstants.KeyExchange.IrrecoverableErrorCause.HMAC_COMPUTATION_FAILURE);
                return getInternalErrorResponse();
            } catch (NoSuchAlgorithmException unused2) {
                Logger.w("Failing key rotation, no such mac algorithm %s", negotiatedData.cipherSuite.macAlgorithm);
                keyExchangeMetrics.onResetKeysError(MetricsConstants.KeyExchange.IrrecoverableErrorCause.HMAC_COMPUTATION_FAILURE);
                return getInternalErrorResponse();
            }
        } catch (CryptoKeyDataStore.UnrecognizedAccessoryException e) {
            Logger.e("Failing key rotation, unrecognized accessory exception", e);
            keyExchangeMetrics.onResetKeysError(MetricsConstants.KeyExchange.IrrecoverableErrorCause.INVALID_ACCESSORY);
            return getInternalErrorResponse();
        } catch (CryptoKeyDataStore.CryptoKeyDataStoreException e2) {
            Logger.e("Failing key rotation, unexpected crypto-key data store error", e2);
            keyExchangeMetrics.onResetKeysError(MetricsConstants.KeyExchange.IrrecoverableErrorCause.CRYPTO_KEY_DATA_STORE_FAILURE);
            return getInternalErrorResponse();
        }
    }
}
