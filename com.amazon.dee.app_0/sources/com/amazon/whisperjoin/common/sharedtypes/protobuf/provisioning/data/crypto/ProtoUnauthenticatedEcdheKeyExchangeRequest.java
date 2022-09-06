package com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.crypto;

import com.amazon.whispercloak.protobuf.SecureTransportProtos;
import com.amazon.whisperjoin.common.sharedtypes.cryptography.UnauthenticatedEcdheKeyExchangeRequest;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.DataSerializationError;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes13.dex */
public class ProtoUnauthenticatedEcdheKeyExchangeRequest implements TypeSerializer<UnauthenticatedEcdheKeyExchangeRequest> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    /* renamed from: deserialize */
    public UnauthenticatedEcdheKeyExchangeRequest mo5427deserialize(byte[] bArr) {
        try {
            SecureTransportProtos.UnauthenticatedECDHEKeyExchangeRequest parseFrom = SecureTransportProtos.UnauthenticatedECDHEKeyExchangeRequest.parseFrom(bArr);
            if (parseFrom.hasSignature()) {
                return new UnauthenticatedEcdheKeyExchangeRequest(parseFrom.getDerECDHEPublicKey().toByteArray(), parseFrom.getSignature().toByteArray());
            }
            return new UnauthenticatedEcdheKeyExchangeRequest(parseFrom.getDerECDHEPublicKey().toByteArray());
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError("Unable to deserialize UnauthenticatedEcdheKeyExchangeRequest", e);
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    public byte[] serialize(UnauthenticatedEcdheKeyExchangeRequest unauthenticatedEcdheKeyExchangeRequest) {
        if (unauthenticatedEcdheKeyExchangeRequest.hasEcdsaSignature()) {
            return SecureTransportProtos.UnauthenticatedECDHEKeyExchangeRequest.newBuilder().setDerECDHEPublicKey(ByteString.copyFrom(unauthenticatedEcdheKeyExchangeRequest.getDerPublicKey())).setSignature(ByteString.copyFrom(unauthenticatedEcdheKeyExchangeRequest.getEcdsaSignature())).mo10084build().toByteArray();
        }
        return SecureTransportProtos.UnauthenticatedECDHEKeyExchangeRequest.newBuilder().setDerECDHEPublicKey(ByteString.copyFrom(unauthenticatedEcdheKeyExchangeRequest.getDerPublicKey())).mo10084build().toByteArray();
    }
}
