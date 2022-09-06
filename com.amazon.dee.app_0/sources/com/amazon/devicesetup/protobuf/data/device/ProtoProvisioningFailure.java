package com.amazon.devicesetup.protobuf.data.device;

import com.amazon.devicesetup.provisioning.data.device.ProvisioningFailure;
import com.amazon.devicesetup.serializer.TypeSerializer;
import com.amazon.whisperjoin.protobuf.ProtobufProvisioningFailureClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes12.dex */
public class ProtoProvisioningFailure implements TypeSerializer<ProvisioningFailure> {
    public ProvisioningFailure createProvisioningFailureFromProto(ProtobufProvisioningFailureClass.ProtobufProvisioningFailure protobufProvisioningFailure) {
        return new ProvisioningFailure(protobufProvisioningFailure.getErrorCode(), protobufProvisioningFailure.getMessage());
    }

    public ProtobufProvisioningFailureClass.ProtobufProvisioningFailure getProtoProvisioningFailure(ProvisioningFailure provisioningFailure) {
        return ProtobufProvisioningFailureClass.ProtobufProvisioningFailure.newBuilder().setErrorCode(provisioningFailure.getErrorCode()).setMessage(provisioningFailure.getMessage()).mo10084build();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.devicesetup.serializer.TypeSerializer
    /* renamed from: deserialize */
    public ProvisioningFailure mo4042deserialize(byte[] bArr) {
        try {
            return createProvisioningFailureFromProto(ProtobufProvisioningFailureClass.ProtobufProvisioningFailure.parseFrom(bArr));
        } catch (InvalidProtocolBufferException e) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline36(e, GeneratedOutlineSupport1.outline107("Illegal data: ")), e);
        }
    }

    @Override // com.amazon.devicesetup.serializer.TypeSerializer
    public byte[] serialize(ProvisioningFailure provisioningFailure) {
        return getProtoProvisioningFailure(provisioningFailure).toByteArray();
    }
}
