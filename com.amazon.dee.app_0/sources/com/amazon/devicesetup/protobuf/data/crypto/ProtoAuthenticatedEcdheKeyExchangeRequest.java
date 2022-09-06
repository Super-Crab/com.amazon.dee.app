package com.amazon.devicesetup.protobuf.data.crypto;

import com.amazon.devicesetup.exceptions.DataSerializationError;
import com.amazon.devicesetup.provisioning.data.crypto.AuthenticatedEcdheKeyExchangeRequest;
import com.amazon.devicesetup.serializer.TypeSerializer;
import com.amazon.whispercloak.protobuf.SecureTransportProtos;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes12.dex */
public class ProtoAuthenticatedEcdheKeyExchangeRequest implements TypeSerializer<AuthenticatedEcdheKeyExchangeRequest> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.devicesetup.serializer.TypeSerializer
    /* renamed from: deserialize */
    public AuthenticatedEcdheKeyExchangeRequest mo4042deserialize(byte[] bArr) {
        try {
            SecureTransportProtos.AuthenticatedECDHEKeyExchangeRequest parseFrom = SecureTransportProtos.AuthenticatedECDHEKeyExchangeRequest.parseFrom(bArr);
            return new AuthenticatedEcdheKeyExchangeRequest(parseFrom.getDerECDHEPublicKey().toByteArray(), parseFrom.getSignature().toByteArray());
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError("Unable to deserialize AuthenticatedEcdheKeyExchangeRequest", e);
        }
    }

    @Override // com.amazon.devicesetup.serializer.TypeSerializer
    public byte[] serialize(AuthenticatedEcdheKeyExchangeRequest authenticatedEcdheKeyExchangeRequest) {
        return SecureTransportProtos.AuthenticatedECDHEKeyExchangeRequest.newBuilder().setDerECDHEPublicKey(ByteString.copyFrom(authenticatedEcdheKeyExchangeRequest.getDerEncodedProvisionerPublicKey())).setSignature(ByteString.copyFrom(authenticatedEcdheKeyExchangeRequest.getEcdsaSignature())).mo10084build().toByteArray();
    }
}
