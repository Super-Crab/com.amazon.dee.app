package com.amazon.devicesetup.protobuf.data.crypto;

import com.amazon.devicesetup.exceptions.DataSerializationError;
import com.amazon.devicesetup.provisioning.data.crypto.UnauthenticatedEcdheKeyExchangeResponse;
import com.amazon.devicesetup.serializer.TypeSerializer;
import com.amazon.whispercloak.protobuf.SecureTransportProtos;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes12.dex */
public class ProtoUnauthenticatedEcdheKeyExchangeResponse implements TypeSerializer<UnauthenticatedEcdheKeyExchangeResponse> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.devicesetup.serializer.TypeSerializer
    /* renamed from: deserialize */
    public UnauthenticatedEcdheKeyExchangeResponse mo4042deserialize(byte[] bArr) {
        try {
            return new UnauthenticatedEcdheKeyExchangeResponse(SecureTransportProtos.UnauthenticatedECDHEKeyExchangeRequest.parseFrom(bArr).getDerECDHEPublicKey().toByteArray());
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError("Unable to deserialize UnauthenticatedEcdheKeyExchangeResponse", e);
        }
    }

    @Override // com.amazon.devicesetup.serializer.TypeSerializer
    public byte[] serialize(UnauthenticatedEcdheKeyExchangeResponse unauthenticatedEcdheKeyExchangeResponse) {
        return SecureTransportProtos.UnauthenticatedECDHEKeyExchangeResponse.newBuilder().setDerECDHEPublicKey(ByteString.copyFrom(unauthenticatedEcdheKeyExchangeResponse.getDerPublicKey())).mo10084build().toByteArray();
    }
}
