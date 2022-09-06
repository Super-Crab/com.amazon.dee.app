package com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.crypto;

import com.amazon.whispercloak.protobuf.SecureTransportProtos;
import com.amazon.whisperjoin.common.sharedtypes.cryptography.AuthenticatedEcdheKeyExchangeRequest;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.DataSerializationError;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes13.dex */
public class ProtoAuthenticatedEcdheKeyExchangeRequest implements TypeSerializer<AuthenticatedEcdheKeyExchangeRequest> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    /* renamed from: deserialize */
    public AuthenticatedEcdheKeyExchangeRequest mo5427deserialize(byte[] bArr) {
        try {
            SecureTransportProtos.AuthenticatedECDHEKeyExchangeRequest parseFrom = SecureTransportProtos.AuthenticatedECDHEKeyExchangeRequest.parseFrom(bArr);
            return new AuthenticatedEcdheKeyExchangeRequest(parseFrom.getDerECDHEPublicKey().toByteArray(), parseFrom.getSignature().toByteArray());
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError("Unable to deserialize AuthenticatedEcdheKeyExchangeRequest", e);
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    public byte[] serialize(AuthenticatedEcdheKeyExchangeRequest authenticatedEcdheKeyExchangeRequest) {
        return SecureTransportProtos.AuthenticatedECDHEKeyExchangeRequest.newBuilder().setDerECDHEPublicKey(ByteString.copyFrom(authenticatedEcdheKeyExchangeRequest.getDerEncodedProvisionerPublicKey())).setSignature(ByteString.copyFrom(authenticatedEcdheKeyExchangeRequest.getEcdsaSignature())).mo10084build().toByteArray();
    }
}
