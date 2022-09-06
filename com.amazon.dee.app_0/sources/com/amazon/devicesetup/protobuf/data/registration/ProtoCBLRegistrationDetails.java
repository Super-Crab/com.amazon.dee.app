package com.amazon.devicesetup.protobuf.data.registration;

import com.amazon.devicesetup.exceptions.DataSerializationError;
import com.amazon.devicesetup.provisioning.data.registration.CBLRegistrationDetails;
import com.amazon.devicesetup.serializer.TypeSerializer;
import com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationDetailsClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.InvalidProtocolBufferException;
import org.apache.commons.lang.StringUtils;
/* loaded from: classes12.dex */
public class ProtoCBLRegistrationDetails implements TypeSerializer<CBLRegistrationDetails> {
    private ProtobufCBLRegistrationDetailsClass.ProtobufCBLRegistrationDetails.State getProtobufState(CBLRegistrationDetails.State state) {
        return ProtobufCBLRegistrationDetailsClass.ProtobufCBLRegistrationDetails.State.valueOf(state.getValue());
    }

    private CBLRegistrationDetails.State getState(ProtobufCBLRegistrationDetailsClass.ProtobufCBLRegistrationDetails.State state) {
        return (CBLRegistrationDetails.State) Enum.valueOf(CBLRegistrationDetails.State.class, state.name());
    }

    public CBLRegistrationDetails getCBLRegistrationDetails(ProtobufCBLRegistrationDetailsClass.ProtobufCBLRegistrationDetails protobufCBLRegistrationDetails) {
        CBLRegistrationDetails.State state = getState(protobufCBLRegistrationDetails.getState());
        Integer num = null;
        String message = StringUtils.isEmpty(protobufCBLRegistrationDetails.getMessage()) ? null : protobufCBLRegistrationDetails.getMessage();
        if (protobufCBLRegistrationDetails.getHttpCode() != 0) {
            num = Integer.valueOf(protobufCBLRegistrationDetails.getHttpCode());
        }
        return new CBLRegistrationDetails(state, message, num);
    }

    public ProtobufCBLRegistrationDetailsClass.ProtobufCBLRegistrationDetails getProtobufCBLRegistrationDetails(CBLRegistrationDetails cBLRegistrationDetails) {
        ProtobufCBLRegistrationDetailsClass.ProtobufCBLRegistrationDetails.Builder state = ProtobufCBLRegistrationDetailsClass.ProtobufCBLRegistrationDetails.newBuilder().setState(getProtobufState(cBLRegistrationDetails.getRegistrationState()));
        if (cBLRegistrationDetails.getMessage() != null) {
            state.setMessage(cBLRegistrationDetails.getMessage());
        }
        if (cBLRegistrationDetails.getHttpCode() != null) {
            state.setHttpCode(cBLRegistrationDetails.getHttpCode().intValue());
        }
        return state.mo10084build();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.devicesetup.serializer.TypeSerializer
    /* renamed from: deserialize */
    public CBLRegistrationDetails mo4042deserialize(byte[] bArr) {
        try {
            return getCBLRegistrationDetails(ProtobufCBLRegistrationDetailsClass.ProtobufCBLRegistrationDetails.parseFrom(bArr));
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError(GeneratedOutlineSupport1.outline36(e, GeneratedOutlineSupport1.outline107("Illegal data: ")), e);
        }
    }

    @Override // com.amazon.devicesetup.serializer.TypeSerializer
    public byte[] serialize(CBLRegistrationDetails cBLRegistrationDetails) {
        return getProtobufCBLRegistrationDetails(cBLRegistrationDetails).toByteArray();
    }
}
