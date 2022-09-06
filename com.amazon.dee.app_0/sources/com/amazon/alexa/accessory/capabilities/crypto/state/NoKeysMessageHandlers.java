package com.amazon.alexa.accessory.capabilities.crypto.state;

import com.amazon.alexa.accessory.capabilities.crypto.metrics.KeyExchangeMetrics;
import com.amazon.alexa.accessory.capabilities.crypto.state.MessageHandlers;
import com.amazon.alexa.accessory.crypto.CryptoKeyDataStore;
import com.amazon.alexa.accessory.crypto.cipher.CipherSuiteParser;
import com.amazon.alexa.accessory.crypto.cipher.Nonce;
import com.amazon.alexa.accessory.crypto.cipher.SupportedCipherSuite;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.amazon.alexa.accessory.metrics.Stopwatch;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.Keyexchange;
import com.google.common.base.Optional;
import com.google.protobuf.ByteString;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.crypto.Mac;
/* loaded from: classes.dex */
class NoKeysMessageHandlers extends MessageHandlers {
    private static final int PROTOCOL_V1_VERSION = 1;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class MatchedSuite {
        private final Keyexchange.CipherSuite accessoyrDefinedSuite;
        private final SupportedCipherSuite appDefinedSuite;

        private MatchedSuite(SupportedCipherSuite supportedCipherSuite, Keyexchange.CipherSuite cipherSuite) {
            Preconditions.notNull(supportedCipherSuite, "appDefinedSuite");
            Preconditions.notNull(cipherSuite, "accessoyrDefinedSuite");
            this.appDefinedSuite = supportedCipherSuite;
            this.accessoyrDefinedSuite = cipherSuite;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NoKeysMessageHandlers() {
        this($$Lambda$D1z_ytlzYvthAoeLOQGoy8VTB4U.INSTANCE);
    }

    private static TransitionResponse createProtocolV1Response(Keyexchange.InitiateHandshake initiateHandshake, String str, CryptoKeyDataStore cryptoKeyDataStore, KeyExchangeMetrics keyExchangeMetrics, Stopwatch.CurrentTimeSupplier currentTimeSupplier) {
        Stopwatch stopwatch = new Stopwatch(currentTimeSupplier);
        Stopwatch stopwatch2 = new Stopwatch(currentTimeSupplier);
        stopwatch.startOrResume();
        Logger.d("Attempting to create V1 initiate handshake response");
        List<Keyexchange.CipherSuite> supportedCipherSuitesList = initiateHandshake.getSupportedCipherSuitesList();
        MatchedSuite resolveSuiteToUse = resolveSuiteToUse(supportedCipherSuitesList);
        if (resolveSuiteToUse == null) {
            Logger.w("Failing key exchange, no cipher suite from accessory is supported: %s", supportedCipherSuitesList);
            keyExchangeMetrics.completeWithError(MetricsConstants.KeyExchange.IrrecoverableErrorCause.INVALID_MESSAGE);
            return getUnsupportedResponse();
        }
        Nonce nonce = new Nonce(initiateHandshake.getNonceAccessory());
        Nonce random = Nonce.random();
        try {
            stopwatch2.startOrResume();
            CryptoKeyDataStore.NegotiatedData generateKeys = cryptoKeyDataStore.generateKeys(str, nonce, random, resolveSuiteToUse.appDefinedSuite, 1);
            stopwatch2.stop();
            Keyexchange.MessageCounter defaultMessageCounterSeed = getDefaultMessageCounterSeed();
            try {
                ByteString generateV1Hmac = generateV1Hmac(resolveSuiteToUse.appDefinedSuite.macAlgorithm, generateKeys.authenticationKey, nonce, random, resolveSuiteToUse.accessoyrDefinedSuite, generateKeys.rootKey, defaultMessageCounterSeed, supportedCipherSuitesList);
                stopwatch.stop();
                keyExchangeMetrics.onKeyNegotiationAcknowledged(stopwatch.getElapsedTimeMillis(), stopwatch2.getElapsedTimeMillis());
                return new TransitionResponse(KeyExchangeState.HAS_KEYS_AWAITING_COMPLETE_HANDSHAKE, Accessories.Response.newBuilder().setAcknowledgeHandshake(Keyexchange.AcknowledgeHandshake.newBuilder().setKey(ByteString.copyFrom(generateKeys.rootKey.getEncoded())).setNonceApp(random.getNonceValue()).setSelectedCipherSuite(resolveSuiteToUse.accessoyrDefinedSuite).setSelectedProtocolVersion(1).setCounterSeed(defaultMessageCounterSeed).setValidationHmac(generateV1Hmac).mo10084build()).mo10084build());
            } catch (InvalidKeyException unused) {
                Logger.w("Failing key exchange, invalid authentication key");
                keyExchangeMetrics.completeWithError(MetricsConstants.KeyExchange.IrrecoverableErrorCause.HMAC_COMPUTATION_FAILURE);
                return getInternalErrorResponse();
            } catch (NoSuchAlgorithmException unused2) {
                Logger.w("Failing key exchange, no such mac algorithm %s", resolveSuiteToUse.appDefinedSuite.macAlgorithm);
                keyExchangeMetrics.completeWithError(MetricsConstants.KeyExchange.IrrecoverableErrorCause.HMAC_COMPUTATION_FAILURE);
                return getInternalErrorResponse();
            }
        } catch (CryptoKeyDataStore.CryptoKeyDataStoreException e) {
            Logger.e("Failing key exchange with an internal error", e);
            keyExchangeMetrics.completeWithError(MetricsConstants.KeyExchange.IrrecoverableErrorCause.CRYPTO_KEY_DATA_STORE_FAILURE);
            return getInternalErrorResponse();
        }
    }

    private static ByteString generateV1Hmac(String str, Key key, Nonce nonce, Nonce nonce2, Keyexchange.CipherSuite cipherSuite, Key key2, Keyexchange.MessageCounter messageCounter, List<Keyexchange.CipherSuite> list) throws NoSuchAlgorithmException, InvalidKeyException {
        Preconditions.notNull(str, "macAlgorithm");
        Preconditions.notNull(key, "authenticationKey");
        Preconditions.notNull(nonce, "nonceAccessory");
        Preconditions.notNull(nonce2, "nonceApplication");
        Preconditions.notNull(cipherSuite, "suiteUsed");
        Preconditions.notNull(key2, "rootKey");
        Preconditions.notNull(messageCounter, "messageCounter");
        Preconditions.notNull(list, "suitesFromAccessory");
        Mac mac = Mac.getInstance(str);
        mac.init(key);
        mac.update(nonce.toByteArray());
        mac.update(nonce2.toByteArray());
        mac.update(toBytes(1));
        mac.update(toBytes(cipherSuite.getNumber()));
        mac.update(toBytes(messageCounter.getCounterHi()));
        mac.update(toBytes(messageCounter.getCounterLo()));
        mac.update(nonce2.toByteArray());
        mac.update(key2.getEncoded());
        for (Keyexchange.CipherSuite cipherSuite2 : list) {
            mac.update(toBytes(cipherSuite2.getNumber()));
        }
        return ByteString.copyFrom(mac.doFinal());
    }

    private static Keyexchange.MessageCounter getDefaultMessageCounterSeed() {
        return Keyexchange.MessageCounter.newBuilder().setCounterHi(0).setCounterLo(0).mo10084build();
    }

    private static MessageHandler<Keyexchange.InitiateHandshake> getInitHandshakeHandler(final Stopwatch.CurrentTimeSupplier currentTimeSupplier) {
        return new MessageHandler() { // from class: com.amazon.alexa.accessory.capabilities.crypto.state.-$$Lambda$NoKeysMessageHandlers$9AK8edXpZMobDQ8ypzuCRrHQNrY
            @Override // com.amazon.alexa.accessory.capabilities.crypto.state.MessageHandler
            public final TransitionResponse getResponse(Object obj, String str, CryptoKeyDataStore cryptoKeyDataStore, KeyExchangeMetrics keyExchangeMetrics) {
                return NoKeysMessageHandlers.lambda$getInitHandshakeHandler$0(Stopwatch.CurrentTimeSupplier.this, (Keyexchange.InitiateHandshake) obj, str, cryptoKeyDataStore, keyExchangeMetrics);
            }
        };
    }

    private static TransitionResponse getInternalErrorResponse() {
        return new TransitionResponse(KeyExchangeState.IRRECOVERABLE_ERROR, Accessories.Response.newBuilder().setErrorCode(Common.ErrorCode.INTERNAL).mo10084build());
    }

    private static TransitionResponse getUnsupportedResponse() {
        return new TransitionResponse(KeyExchangeState.IRRECOVERABLE_ERROR, Accessories.Response.newBuilder().setErrorCode(Common.ErrorCode.UNSUPPORTED).mo10084build());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ TransitionResponse lambda$getInitHandshakeHandler$0(Stopwatch.CurrentTimeSupplier currentTimeSupplier, Keyexchange.InitiateHandshake initiateHandshake, String str, CryptoKeyDataStore cryptoKeyDataStore, KeyExchangeMetrics keyExchangeMetrics) {
        if (initiateHandshake.getMaxSupportedProtocolVersion() >= 1 && initiateHandshake.getMinSupportedProtocolVersion() <= 1) {
            return createProtocolV1Response(initiateHandshake, str, cryptoKeyDataStore, keyExchangeMetrics, currentTimeSupplier);
        }
        Logger.w("Failing key exchange, no supported protocol between %d and %d", Integer.valueOf(initiateHandshake.getMinSupportedProtocolVersion()), Integer.valueOf(initiateHandshake.getMaxSupportedProtocolVersion()));
        keyExchangeMetrics.completeWithError(MetricsConstants.KeyExchange.IrrecoverableErrorCause.INVALID_MESSAGE);
        return getUnsupportedResponse();
    }

    private static MatchedSuite resolveSuiteToUse(List<Keyexchange.CipherSuite> list) {
        for (Keyexchange.CipherSuite cipherSuite : list) {
            Optional<SupportedCipherSuite> fromAccessoryDescriptor = CipherSuiteParser.fromAccessoryDescriptor(cipherSuite.name());
            if (fromAccessoryDescriptor.isPresent()) {
                return new MatchedSuite(fromAccessoryDescriptor.get(), cipherSuite);
            }
        }
        return null;
    }

    private static byte[] toBytes(int i) {
        return new byte[]{(byte) (i >>> 24), (byte) (i >>> 16), (byte) (i >>> 8), (byte) i};
    }

    NoKeysMessageHandlers(Stopwatch.CurrentTimeSupplier currentTimeSupplier) {
        super(new MessageHandlers.MessageHandlersBuilder().registerHandler(Accessories.Command.INITIATE_HANDSHAKE, getInitHandshakeHandler(currentTimeSupplier)).build());
    }
}
