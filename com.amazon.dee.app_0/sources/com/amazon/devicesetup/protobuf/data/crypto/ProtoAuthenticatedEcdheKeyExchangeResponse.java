package com.amazon.devicesetup.protobuf.data.crypto;

import com.amazon.devicesetup.exceptions.DataSerializationError;
import com.amazon.devicesetup.provisioning.data.crypto.AuthenticatedEcdheKeyExchangeResponse;
import com.amazon.devicesetup.serializer.TypeSerializer;
import com.amazon.whispercloak.protobuf.SecureTransportProtos;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes12.dex */
public class ProtoAuthenticatedEcdheKeyExchangeResponse implements TypeSerializer<AuthenticatedEcdheKeyExchangeResponse> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.devicesetup.serializer.TypeSerializer
    /* renamed from: deserialize */
    public AuthenticatedEcdheKeyExchangeResponse mo4042deserialize(byte[] bArr) {
        try {
            SecureTransportProtos.AuthenticatedECDHEKeyExchangeResponse parseFrom = SecureTransportProtos.AuthenticatedECDHEKeyExchangeResponse.parseFrom(bArr);
            return new AuthenticatedEcdheKeyExchangeResponse(parseFrom.getDerECDHEPublicKey().toByteArray(), new ProtoSecureMessage().createSecureMessage(parseFrom.getAuthenticationDataSecureMessage()));
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError("Unable to deserialize AuthenticatedEcdheKeyExchangeResponse", e);
        }
    }

    @Override // com.amazon.devicesetup.serializer.TypeSerializer
    public byte[] serialize(AuthenticatedEcdheKeyExchangeResponse authenticatedEcdheKeyExchangeResponse) {
        return SecureTransportProtos.AuthenticatedECDHEKeyExchangeResponse.newBuilder().setAuthenticationDataSecureMessage(new ProtoSecureMessage().createAESGCMSecureMessage(authenticatedEcdheKeyExchangeResponse.getSecureMessage())).setDerECDHEPublicKey(ByteString.copyFrom(authenticatedEcdheKeyExchangeResponse.getProvisionableDerPublicKey())).mo10084build().toByteArray();
    }
}
