package com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.crypto;

import com.amazon.whispercloak.protobuf.SecureTransportProtos;
import com.amazon.whisperjoin.common.sharedtypes.cryptography.UnauthenticatedEcdheKeyExchangeResponse;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.DataSerializationError;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes13.dex */
public class ProtoUnauthenticatedEcdheKeyExchangeResponse implements TypeSerializer<UnauthenticatedEcdheKeyExchangeResponse> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    /* renamed from: deserialize */
    public UnauthenticatedEcdheKeyExchangeResponse mo5427deserialize(byte[] bArr) {
        try {
            SecureTransportProtos.UnauthenticatedECDHEKeyExchangeResponse parseFrom = SecureTransportProtos.UnauthenticatedECDHEKeyExchangeResponse.parseFrom(bArr);
            if (parseFrom.hasAuthenticationDataSecureMessage()) {
                return new UnauthenticatedEcdheKeyExchangeResponse(parseFrom.getDerECDHEPublicKey().toByteArray(), new ProtoSecureMessage().createSecureMessage(parseFrom.getAuthenticationDataSecureMessage()));
            }
            return new UnauthenticatedEcdheKeyExchangeResponse(parseFrom.getDerECDHEPublicKey().toByteArray());
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError("Unable to deserialize UnauthenticatedEcdheKeyExchangeResponse", e);
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    public byte[] serialize(UnauthenticatedEcdheKeyExchangeResponse unauthenticatedEcdheKeyExchangeResponse) {
        if (unauthenticatedEcdheKeyExchangeResponse.hasSecureMessage()) {
            return SecureTransportProtos.UnauthenticatedECDHEKeyExchangeResponse.newBuilder().setDerECDHEPublicKey(ByteString.copyFrom(unauthenticatedEcdheKeyExchangeResponse.getDerPublicKey())).setAuthenticationDataSecureMessage(new ProtoSecureMessage().createAESGCMSecureMessage(unauthenticatedEcdheKeyExchangeResponse.getSecureMessage())).mo10084build().toByteArray();
        }
        return SecureTransportProtos.UnauthenticatedECDHEKeyExchangeResponse.newBuilder().setDerECDHEPublicKey(ByteString.copyFrom(unauthenticatedEcdheKeyExchangeResponse.getDerPublicKey())).mo10084build().toByteArray();
    }
}
