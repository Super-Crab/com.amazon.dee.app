package com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.crypto;

import com.amazon.whispercloak.protobuf.SecureTransportProtos;
import com.amazon.whisperjoin.common.sharedtypes.cryptography.AuthenticatedEcdheKeyExchangeResponse;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.DataSerializationError;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes13.dex */
public class ProtoAuthenticatedEcdheKeyExchangeResponse implements TypeSerializer<AuthenticatedEcdheKeyExchangeResponse> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    /* renamed from: deserialize */
    public AuthenticatedEcdheKeyExchangeResponse mo5427deserialize(byte[] bArr) {
        try {
            SecureTransportProtos.AuthenticatedECDHEKeyExchangeResponse parseFrom = SecureTransportProtos.AuthenticatedECDHEKeyExchangeResponse.parseFrom(bArr);
            return new AuthenticatedEcdheKeyExchangeResponse(parseFrom.getDerECDHEPublicKey().toByteArray(), new ProtoSecureMessage().createSecureMessage(parseFrom.getAuthenticationDataSecureMessage()));
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError("Unable to deserialize AuthenticatedEcdheKeyExchangeResponse", e);
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    public byte[] serialize(AuthenticatedEcdheKeyExchangeResponse authenticatedEcdheKeyExchangeResponse) {
        return SecureTransportProtos.AuthenticatedECDHEKeyExchangeResponse.newBuilder().setAuthenticationDataSecureMessage(new ProtoSecureMessage().createAESGCMSecureMessage(authenticatedEcdheKeyExchangeResponse.getSecureMessage())).setDerECDHEPublicKey(ByteString.copyFrom(authenticatedEcdheKeyExchangeResponse.getProvisionableDerPublicKey())).mo10084build().toByteArray();
    }
}
