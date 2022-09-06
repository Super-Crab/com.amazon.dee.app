package com.amazon.devicesetup.protobuf.data.crypto;

import com.amazon.devicesetup.exceptions.DataSerializationError;
import com.amazon.devicesetup.provisioning.data.crypto.UnauthenticatedEcdheKeyExchangeRequest;
import com.amazon.devicesetup.serializer.TypeSerializer;
import com.amazon.whispercloak.protobuf.SecureTransportProtos;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes12.dex */
public class ProtoUnauthenticatedEcdheKeyExchangeRequest implements TypeSerializer<UnauthenticatedEcdheKeyExchangeRequest> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.devicesetup.serializer.TypeSerializer
    /* renamed from: deserialize */
    public UnauthenticatedEcdheKeyExchangeRequest mo4042deserialize(byte[] bArr) {
        try {
            return new UnauthenticatedEcdheKeyExchangeRequest(SecureTransportProtos.UnauthenticatedECDHEKeyExchangeRequest.parseFrom(bArr).getDerECDHEPublicKey().toByteArray());
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError("Unable to deserialize UnauthenticatedEcdheKeyExchangeRequest", e);
        }
    }

    @Override // com.amazon.devicesetup.serializer.TypeSerializer
    public byte[] serialize(UnauthenticatedEcdheKeyExchangeRequest unauthenticatedEcdheKeyExchangeRequest) {
        return SecureTransportProtos.UnauthenticatedECDHEKeyExchangeRequest.newBuilder().setDerECDHEPublicKey(ByteString.copyFrom(unauthenticatedEcdheKeyExchangeRequest.getDerPublicKey())).mo10084build().toByteArray();
    }
}
