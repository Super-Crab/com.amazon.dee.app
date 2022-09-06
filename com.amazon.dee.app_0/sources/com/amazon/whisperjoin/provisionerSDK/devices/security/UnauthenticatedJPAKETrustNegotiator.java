package com.amazon.whisperjoin.provisionerSDK.devices.security;

import com.amazon.whisperbridge.constants.Command;
import com.amazon.whispercloak.cipher.AesGcmCipher;
import com.amazon.whispercloak.jpake.JPAKESessionKeyNegotiator;
import com.amazon.whispercloak.jpake.JPAKESessionKeyNegotiatorImpl;
import com.amazon.whispercloak.jpake.ec.ECJPAKERound1Payload;
import com.amazon.whispercloak.jpake.ec.ECJPAKERound2Payload;
import com.amazon.whispercloak.jpake.ec.ECJPAKERound3Payload;
import com.amazon.whisperjoin.common.sharedtypes.cryptography.AesGcmEncryptionProvider;
import com.amazon.whisperjoin.common.sharedtypes.cryptography.EncryptionProvider;
import com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.PeripheralDevice;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.TrustProviderInitializationFailedException;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.crypto.ProtoJPAKEPayloadSerializer;
import com.amazon.whisperjoin.common.sharedtypes.utility.Serializer;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.google.common.reflect.TypeToken;
import java.math.BigInteger;
/* loaded from: classes13.dex */
public class UnauthenticatedJPAKETrustNegotiator implements TrustNegotiator {
    private static final String PROVISIONER_PARTICIPANT_ID = "client";
    private static final String TAG = "UnauthenticatedJPAKETrustNegotiator";
    private Serializer jpakeSerializer;
    private final String pin;
    private final String publicKey;
    private final Serializer serializer;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UnauthenticatedJPAKETrustNegotiator(String str, String str2, Serializer serializer) {
        if (str != null) {
            if (str2 == null) {
                throw new IllegalArgumentException("publicKey can not be null");
            }
            if (serializer != null) {
                this.pin = str;
                this.publicKey = str2;
                this.serializer = serializer;
                return;
            }
            throw new IllegalArgumentException("serializer can not be null");
        }
        throw new IllegalArgumentException("pin can not be null");
    }

    private void doRound1(JPAKESessionKeyNegotiator jPAKESessionKeyNegotiator, PeripheralDevice peripheralDevice) throws Exception {
        WJLog.i(TAG, "Provisioner: Starting JPAKE Round 1.");
        jPAKESessionKeyNegotiator.validateRound1PayloadReceived((ECJPAKERound1Payload) this.jpakeSerializer.deserialize(peripheralDevice.executeCommand(Command.JPAKE_ROUND1, this.jpakeSerializer.serialize(jPAKESessionKeyNegotiator.createRound1PayloadToSend())), TypeToken.of(ECJPAKERound1Payload.class)));
        WJLog.i(TAG, "Provisioner: JPAKE Round 1 completed successfully.");
    }

    private void doRound2(JPAKESessionKeyNegotiator jPAKESessionKeyNegotiator, PeripheralDevice peripheralDevice) throws Exception {
        WJLog.i(TAG, "Provisioner: Starting JPAKE Round 2.");
        jPAKESessionKeyNegotiator.validateRound2PayloadReceived((ECJPAKERound2Payload) this.jpakeSerializer.deserialize(peripheralDevice.executeCommand(Command.JPAKE_ROUND2, this.jpakeSerializer.serialize(jPAKESessionKeyNegotiator.createRound2PayloadToSend())), TypeToken.of(ECJPAKERound2Payload.class)));
        WJLog.i(TAG, "Provisioner: JPAKE Round 2 completed successfully.");
    }

    private BigInteger doRound3(JPAKESessionKeyNegotiator jPAKESessionKeyNegotiator, PeripheralDevice peripheralDevice) throws Exception {
        WJLog.i(TAG, "Provisioner: Starting JPAKE Round 3.");
        BigInteger computeCommonKeyMaterial = jPAKESessionKeyNegotiator.computeCommonKeyMaterial();
        jPAKESessionKeyNegotiator.validateRound3PayloadReceived((ECJPAKERound3Payload) this.jpakeSerializer.deserialize(peripheralDevice.executeCommand(Command.JPAKE_ROUND3, this.jpakeSerializer.serialize(jPAKESessionKeyNegotiator.createRound3PayloadToSend(computeCommonKeyMaterial))), TypeToken.of(ECJPAKERound3Payload.class)), computeCommonKeyMaterial);
        WJLog.i(TAG, "Provisioner: JPAKE Round 3 completed successfully.");
        return computeCommonKeyMaterial;
    }

    @Override // com.amazon.whisperjoin.provisionerSDK.devices.security.TrustNegotiator
    public EncryptionProvider start(PeripheralDevice peripheralDevice) throws TrustProviderInitializationFailedException {
        if (peripheralDevice != null) {
            try {
                JPAKESessionKeyNegotiatorImpl jPAKESessionKeyNegotiatorImpl = new JPAKESessionKeyNegotiatorImpl(PROVISIONER_PARTICIPANT_ID, this.pin);
                if (this.jpakeSerializer == null) {
                    this.jpakeSerializer = new ProtoJPAKEPayloadSerializer(jPAKESessionKeyNegotiatorImpl.getParticipant().getGroup());
                }
                doRound1(jPAKESessionKeyNegotiatorImpl, peripheralDevice);
                doRound2(jPAKESessionKeyNegotiatorImpl, peripheralDevice);
                return new AesGcmEncryptionProvider(new AesGcmCipher(), jPAKESessionKeyNegotiatorImpl.deriveSessionKey(doRound3(jPAKESessionKeyNegotiatorImpl, peripheralDevice)), this.serializer);
            } catch (Exception e) {
                throw new TrustProviderInitializationFailedException("Exception thrown executing JPAKE key exchange", e);
            }
        }
        throw new IllegalArgumentException("peripheralDevice can not be null");
    }
}
