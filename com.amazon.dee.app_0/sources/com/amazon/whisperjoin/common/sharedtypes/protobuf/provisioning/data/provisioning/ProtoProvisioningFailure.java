package com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.provisioning;

import com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.provisioning.ProvisioningFailure;
import com.amazon.whisperjoin.protobuf.ProtobufProvisioningFailureClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes13.dex */
public class ProtoProvisioningFailure implements TypeSerializer<ProvisioningFailure> {
    public ProvisioningFailure createProvisioningFailureFromProto(ProtobufProvisioningFailureClass.ProtobufProvisioningFailure protobufProvisioningFailure) {
        return new ProvisioningFailure(protobufProvisioningFailure.getErrorCode(), protobufProvisioningFailure.getMessage());
    }

    public ProtobufProvisioningFailureClass.ProtobufProvisioningFailure getProtoProvisioningFailure(ProvisioningFailure provisioningFailure) {
        return ProtobufProvisioningFailureClass.ProtobufProvisioningFailure.newBuilder().setErrorCode(provisioningFailure.getErrorCode()).setMessage(provisioningFailure.getMessage()).mo10084build();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    /* renamed from: deserialize */
    public ProvisioningFailure mo5427deserialize(byte[] bArr) {
        try {
            return createProvisioningFailureFromProto(ProtobufProvisioningFailureClass.ProtobufProvisioningFailure.parseFrom(bArr));
        } catch (InvalidProtocolBufferException e) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline36(e, GeneratedOutlineSupport1.outline107("Illegal data: ")), e);
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    public byte[] serialize(ProvisioningFailure provisioningFailure) {
        return getProtoProvisioningFailure(provisioningFailure).toByteArray();
    }
}
