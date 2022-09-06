package com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.crypto;

import com.amazon.whispercloak.SecureMessage;
import com.amazon.whispercloak.protobuf.SecureTransportProtos;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.DataSerializationError;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes13.dex */
public class ProtoSecureMessage implements TypeSerializer<SecureMessage> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public SecureTransportProtos.AesGCMSecureMessage createAESGCMSecureMessage(SecureMessage secureMessage) {
        SecureTransportProtos.AesGCMSecureMessage.Builder initializationVector = SecureTransportProtos.AesGCMSecureMessage.newBuilder().setPayload(ByteString.copyFrom(secureMessage.getCipherText())).setMac(ByteString.copyFrom(secureMessage.getMac())).setInitializationVector(ByteString.copyFrom(secureMessage.getIv()));
        if (secureMessage.getAad() != null && secureMessage.getAad().length > 0) {
            initializationVector.setAad(ByteString.copyFrom(secureMessage.getAad()));
        } else {
            initializationVector.setAad(ByteString.EMPTY);
        }
        return initializationVector.mo10084build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SecureMessage createSecureMessage(SecureTransportProtos.AesGCMSecureMessage aesGCMSecureMessage) {
        return new SecureMessage(aesGCMSecureMessage.getInitializationVector().toByteArray(), aesGCMSecureMessage.getPayload().toByteArray(), aesGCMSecureMessage.getMac().toByteArray(), aesGCMSecureMessage.getAad() == null ? null : aesGCMSecureMessage.getAad().toByteArray());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    /* renamed from: deserialize */
    public SecureMessage mo5427deserialize(byte[] bArr) {
        try {
            return createSecureMessage(SecureTransportProtos.AesGCMSecureMessage.parseFrom(bArr));
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError("unable to deserialize AesGCMSecureMessage", e);
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    public byte[] serialize(SecureMessage secureMessage) {
        return createAESGCMSecureMessage(secureMessage).toByteArray();
    }
}
