package com.amazon.alexa.accessory.capabilities.crypto.state;

import com.amazon.alexa.accessory.capabilities.crypto.metrics.KeyExchangeMetrics;
import com.amazon.alexa.accessory.capabilities.crypto.state.MessageHandlers;
import com.amazon.alexa.accessory.crypto.CryptoKeyDataStore;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.amazon.alexa.accessory.metrics.Stopwatch;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.Keyexchange;
import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.Mac;
/* loaded from: classes.dex */
class HasKeysAwaitingCompleteHandshakeMessageHandlers extends MessageHandlers {
    private static final List<Validator> VALIDATORS = ImmutableList.of((DecryptionValidator) new KeyPresenceValidator(), (DecryptionValidator) new BlobPresenceValidator(), (DecryptionValidator) new HmacValidator(), new DecryptionValidator());

    /* loaded from: classes.dex */
    private static final class BlobPresenceValidator extends Validator {
        @Override // com.amazon.alexa.accessory.capabilities.crypto.state.HasKeysAwaitingCompleteHandshakeMessageHandlers.Validator
        protected boolean reallyValidate(CryptoKeyDataStore.NegotiatedData negotiatedData, Keyexchange.TestCipherBlob testCipherBlob) {
            return (testCipherBlob == null || testCipherBlob.getTestCipherText() == null || testCipherBlob.getTestCipherText().isEmpty() || testCipherBlob.getTestHmac() == null || testCipherBlob.getTestHmac().isEmpty() || testCipherBlob.getTestIv() == null || testCipherBlob.getTestIv().isEmpty()) ? false : true;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private BlobPresenceValidator() {
            /*
                r1 = this;
                com.amazon.alexa.accessory.metrics.MetricsConstants$KeyExchange$IrrecoverableErrorCause r0 = com.amazon.alexa.accessory.metrics.MetricsConstants.KeyExchange.IrrecoverableErrorCause.INCOMPLETE_TEST_BLOB
                r1.<init>(r0, r0)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.accessory.capabilities.crypto.state.HasKeysAwaitingCompleteHandshakeMessageHandlers.BlobPresenceValidator.<init>():void");
        }
    }

    /* loaded from: classes.dex */
    private static final class DecryptionValidator extends Validator {
        private static final byte[] EXPECTED_DECRYPTED_PLAIN_TEXT = {1};

        @Override // com.amazon.alexa.accessory.capabilities.crypto.state.HasKeysAwaitingCompleteHandshakeMessageHandlers.Validator
        protected boolean reallyValidate(CryptoKeyDataStore.NegotiatedData negotiatedData, Keyexchange.TestCipherBlob testCipherBlob) throws Exception {
            Cipher cipher = Cipher.getInstance(negotiatedData.cipherTransform);
            cipher.init(2, negotiatedData.encryptionKey, negotiatedData.cipherSuite.algorithmParameterSpecBuilder.build(testCipherBlob.getTestIv()));
            return Arrays.equals(EXPECTED_DECRYPTED_PLAIN_TEXT, cipher.doFinal(testCipherBlob.getTestCipherText().toByteArray()));
        }

        private DecryptionValidator() {
            super(MetricsConstants.KeyExchange.IrrecoverableErrorCause.DECRYPTION_DATA_MISMATCH, MetricsConstants.KeyExchange.IrrecoverableErrorCause.DECRYPTION_FAILURE);
        }
    }

    /* loaded from: classes.dex */
    private static final class HmacValidator extends Validator {
        @Override // com.amazon.alexa.accessory.capabilities.crypto.state.HasKeysAwaitingCompleteHandshakeMessageHandlers.Validator
        protected boolean reallyValidate(CryptoKeyDataStore.NegotiatedData negotiatedData, Keyexchange.TestCipherBlob testCipherBlob) throws Exception {
            Mac mac = Mac.getInstance(negotiatedData.cipherSuite.macAlgorithm);
            mac.init(negotiatedData.authenticationKey);
            mac.update(testCipherBlob.getTestIv().toByteArray());
            mac.update(testCipherBlob.getTestCipherText().toByteArray());
            return Arrays.equals(mac.doFinal(), testCipherBlob.getTestHmac().toByteArray());
        }

        private HmacValidator() {
            super(MetricsConstants.KeyExchange.IrrecoverableErrorCause.HMAC_VALIDATION_FAILURE, MetricsConstants.KeyExchange.IrrecoverableErrorCause.HMAC_COMPUTATION_FAILURE);
        }
    }

    /* loaded from: classes.dex */
    private static final class KeyPresenceValidator extends Validator {
        @Override // com.amazon.alexa.accessory.capabilities.crypto.state.HasKeysAwaitingCompleteHandshakeMessageHandlers.Validator
        protected boolean reallyValidate(CryptoKeyDataStore.NegotiatedData negotiatedData, Keyexchange.TestCipherBlob testCipherBlob) {
            return negotiatedData != null;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private KeyPresenceValidator() {
            /*
                r1 = this;
                com.amazon.alexa.accessory.metrics.MetricsConstants$KeyExchange$IrrecoverableErrorCause r0 = com.amazon.alexa.accessory.metrics.MetricsConstants.KeyExchange.IrrecoverableErrorCause.MISSING_KEYS
                r1.<init>(r0, r0)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.accessory.capabilities.crypto.state.HasKeysAwaitingCompleteHandshakeMessageHandlers.KeyPresenceValidator.<init>():void");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static abstract class Validator {
        protected final MetricsConstants.KeyExchange.IrrecoverableErrorCause validationErrorCause;
        protected final MetricsConstants.KeyExchange.IrrecoverableErrorCause validationFailureCause;

        protected Validator(MetricsConstants.KeyExchange.IrrecoverableErrorCause irrecoverableErrorCause, MetricsConstants.KeyExchange.IrrecoverableErrorCause irrecoverableErrorCause2) {
            this.validationFailureCause = irrecoverableErrorCause;
            this.validationErrorCause = irrecoverableErrorCause2;
        }

        protected abstract boolean reallyValidate(CryptoKeyDataStore.NegotiatedData negotiatedData, Keyexchange.TestCipherBlob testCipherBlob) throws Exception;

        public boolean validate(CryptoKeyDataStore.NegotiatedData negotiatedData, Keyexchange.TestCipherBlob testCipherBlob, KeyExchangeMetrics keyExchangeMetrics) {
            try {
                boolean reallyValidate = reallyValidate(negotiatedData, testCipherBlob);
                if (!reallyValidate) {
                    Logger.d(this.validationFailureCause.metricName + ": Validation failed");
                    keyExchangeMetrics.completeWithError(this.validationFailureCause);
                }
                return reallyValidate;
            } catch (Exception e) {
                Logger.e(this.validationErrorCause + ": Failed to validate the test cipher blob from a CompleteHandshake message", e);
                keyExchangeMetrics.completeWithError(this.validationErrorCause);
                return false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HasKeysAwaitingCompleteHandshakeMessageHandlers() {
        this($$Lambda$D1z_ytlzYvthAoeLOQGoy8VTB4U.INSTANCE);
    }

    private static Accessories.Response buildResponse(Common.ErrorCode errorCode) {
        return Accessories.Response.newBuilder().setErrorCode(errorCode).mo10084build();
    }

    private static MessageHandler<Keyexchange.CompleteHandshake> getCompleteHandshakeHandler(final Stopwatch.CurrentTimeSupplier currentTimeSupplier) {
        return new MessageHandler() { // from class: com.amazon.alexa.accessory.capabilities.crypto.state.-$$Lambda$HasKeysAwaitingCompleteHandshakeMessageHandlers$yPv6c2IWE75gsyj54MJ8TkQkszQ
            @Override // com.amazon.alexa.accessory.capabilities.crypto.state.MessageHandler
            public final TransitionResponse getResponse(Object obj, String str, CryptoKeyDataStore cryptoKeyDataStore, KeyExchangeMetrics keyExchangeMetrics) {
                return HasKeysAwaitingCompleteHandshakeMessageHandlers.lambda$getCompleteHandshakeHandler$0(Stopwatch.CurrentTimeSupplier.this, (Keyexchange.CompleteHandshake) obj, str, cryptoKeyDataStore, keyExchangeMetrics);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0058, code lost:
        r10.completeWithError(com.amazon.alexa.accessory.metrics.MetricsConstants.KeyExchange.IrrecoverableErrorCause.ACCESSORY_FAILURE);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ com.amazon.alexa.accessory.capabilities.crypto.state.TransitionResponse lambda$getCompleteHandshakeHandler$0(com.amazon.alexa.accessory.metrics.Stopwatch.CurrentTimeSupplier r6, com.amazon.alexa.accessory.protocol.Keyexchange.CompleteHandshake r7, java.lang.String r8, com.amazon.alexa.accessory.crypto.CryptoKeyDataStore r9, com.amazon.alexa.accessory.capabilities.crypto.metrics.KeyExchangeMetrics r10) {
        /*
            com.amazon.alexa.accessory.metrics.Stopwatch r0 = new com.amazon.alexa.accessory.metrics.Stopwatch
            r0.<init>(r6)
            com.amazon.alexa.accessory.metrics.Stopwatch r1 = new com.amazon.alexa.accessory.metrics.Stopwatch
            r1.<init>(r6)
            r0.startOrResume()
            boolean r6 = r7.getIsSuccessful()
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r6)
            r4 = 0
            r2[r4] = r3
            java.lang.String r3 = "Handshake completed, isSuccessful=%b"
            com.amazon.alexa.accessory.internal.util.Logger.d(r3, r2)
            com.amazon.alexa.accessory.protocol.Keyexchange$TestCipherBlob r7 = r7.getTestBlob()
            r2 = 0
            r1.startOrResume()     // Catch: com.amazon.alexa.accessory.crypto.CryptoKeyDataStore.CryptoKeyDataStoreException -> L35
            if (r6 == 0) goto L2f
            com.amazon.alexa.accessory.crypto.CryptoKeyDataStore$NegotiatedData r3 = r9.getData(r8)     // Catch: com.amazon.alexa.accessory.crypto.CryptoKeyDataStore.CryptoKeyDataStoreException -> L35
            goto L30
        L2f:
            r3 = r2
        L30:
            r1.stop()     // Catch: com.amazon.alexa.accessory.crypto.CryptoKeyDataStore.CryptoKeyDataStoreException -> L35
            r2 = r3
            goto L3b
        L35:
            r3 = move-exception
            java.lang.String r5 = "Error fetching keys from the data-store while handling CompleteHandshake"
            com.amazon.alexa.accessory.internal.util.Logger.e(r5, r3)
        L3b:
            if (r6 == 0) goto L56
            java.util.List<com.amazon.alexa.accessory.capabilities.crypto.state.HasKeysAwaitingCompleteHandshakeMessageHandlers$Validator> r3 = com.amazon.alexa.accessory.capabilities.crypto.state.HasKeysAwaitingCompleteHandshakeMessageHandlers.VALIDATORS
            int r3 = r3.size()
            if (r4 >= r3) goto L56
            java.util.List<com.amazon.alexa.accessory.capabilities.crypto.state.HasKeysAwaitingCompleteHandshakeMessageHandlers$Validator> r3 = com.amazon.alexa.accessory.capabilities.crypto.state.HasKeysAwaitingCompleteHandshakeMessageHandlers.VALIDATORS
            java.lang.Object r3 = r3.get(r4)
            com.amazon.alexa.accessory.capabilities.crypto.state.HasKeysAwaitingCompleteHandshakeMessageHandlers$Validator r3 = (com.amazon.alexa.accessory.capabilities.crypto.state.HasKeysAwaitingCompleteHandshakeMessageHandlers.Validator) r3
            boolean r3 = r3.validate(r2, r7, r10)
            if (r3 == 0) goto L56
            int r4 = r4 + 1
            goto L3b
        L56:
            if (r6 != 0) goto L5d
            com.amazon.alexa.accessory.metrics.MetricsConstants$KeyExchange$IrrecoverableErrorCause r7 = com.amazon.alexa.accessory.metrics.MetricsConstants.KeyExchange.IrrecoverableErrorCause.ACCESSORY_FAILURE
            r10.completeWithError(r7)
        L5d:
            com.amazon.alexa.accessory.protocol.Common$ErrorCode r7 = com.amazon.alexa.accessory.protocol.Common.ErrorCode.SUCCESS
            com.amazon.alexa.accessory.capabilities.crypto.state.KeyExchangeState r2 = com.amazon.alexa.accessory.capabilities.crypto.state.KeyExchangeState.HAS_KEYS
            if (r6 == 0) goto L7b
            java.util.List<com.amazon.alexa.accessory.capabilities.crypto.state.HasKeysAwaitingCompleteHandshakeMessageHandlers$Validator> r3 = com.amazon.alexa.accessory.capabilities.crypto.state.HasKeysAwaitingCompleteHandshakeMessageHandlers.VALIDATORS
            int r3 = r3.size()
            if (r4 >= r3) goto L6c
            goto L7b
        L6c:
            r0.stop()
            long r8 = r0.getElapsedTimeMillis()
            long r0 = r1.getElapsedTimeMillis()
            r10.onKeyNegotiationComplete(r8, r0)
            goto L8d
        L7b:
            java.lang.String r7 = "Key exchange failed. Removing keys."
            com.amazon.alexa.accessory.internal.util.Logger.d(r7)
            r9.removeData(r8)
            if (r6 == 0) goto L88
            com.amazon.alexa.accessory.protocol.Common$ErrorCode r6 = com.amazon.alexa.accessory.protocol.Common.ErrorCode.INTERNAL
            goto L8a
        L88:
            com.amazon.alexa.accessory.protocol.Common$ErrorCode r6 = com.amazon.alexa.accessory.protocol.Common.ErrorCode.SUCCESS
        L8a:
            r7 = r6
            com.amazon.alexa.accessory.capabilities.crypto.state.KeyExchangeState r2 = com.amazon.alexa.accessory.capabilities.crypto.state.KeyExchangeState.IRRECOVERABLE_ERROR
        L8d:
            com.amazon.alexa.accessory.capabilities.crypto.state.TransitionResponse r6 = new com.amazon.alexa.accessory.capabilities.crypto.state.TransitionResponse
            com.amazon.alexa.accessory.protocol.Accessories$Response r7 = buildResponse(r7)
            r6.<init>(r2, r7)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.accessory.capabilities.crypto.state.HasKeysAwaitingCompleteHandshakeMessageHandlers.lambda$getCompleteHandshakeHandler$0(com.amazon.alexa.accessory.metrics.Stopwatch$CurrentTimeSupplier, com.amazon.alexa.accessory.protocol.Keyexchange$CompleteHandshake, java.lang.String, com.amazon.alexa.accessory.crypto.CryptoKeyDataStore, com.amazon.alexa.accessory.capabilities.crypto.metrics.KeyExchangeMetrics):com.amazon.alexa.accessory.capabilities.crypto.state.TransitionResponse");
    }

    HasKeysAwaitingCompleteHandshakeMessageHandlers(Stopwatch.CurrentTimeSupplier currentTimeSupplier) {
        super(new MessageHandlers.MessageHandlersBuilder().registerHandler(Accessories.Command.COMPLETE_HANDSHAKE, getCompleteHandshakeHandler(currentTimeSupplier)).build());
    }
}
