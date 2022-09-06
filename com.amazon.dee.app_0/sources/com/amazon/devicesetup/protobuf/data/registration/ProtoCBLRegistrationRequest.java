package com.amazon.devicesetup.protobuf.data.registration;

import com.amazon.devicesetup.exceptions.DataSerializationError;
import com.amazon.devicesetup.provisioning.data.registration.CBLRegistrationRequest;
import com.amazon.devicesetup.serializer.TypeSerializer;
import com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationRequestClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes12.dex */
public class ProtoCBLRegistrationRequest implements TypeSerializer<CBLRegistrationRequest> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.devicesetup.serializer.TypeSerializer
    /* renamed from: deserialize */
    public CBLRegistrationRequest mo4042deserialize(byte[] bArr) {
        try {
            ProtobufCBLRegistrationRequestClass.ProtobufCBLRegistrationRequest parseFrom = ProtobufCBLRegistrationRequestClass.ProtobufCBLRegistrationRequest.parseFrom(bArr);
            return new CBLRegistrationRequest(parseFrom.getLinkCode(), parseFrom.getExpiration());
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError(GeneratedOutlineSupport1.outline36(e, GeneratedOutlineSupport1.outline107("Illegal data: ")), e);
        }
    }

    @Override // com.amazon.devicesetup.serializer.TypeSerializer
    public byte[] serialize(CBLRegistrationRequest cBLRegistrationRequest) {
        return ProtobufCBLRegistrationRequestClass.ProtobufCBLRegistrationRequest.newBuilder().setLinkCode(cBLRegistrationRequest.getLinkCode()).setExpiration(cBLRegistrationRequest.getExpiration()).mo10084build().toByteArray();
    }
}
