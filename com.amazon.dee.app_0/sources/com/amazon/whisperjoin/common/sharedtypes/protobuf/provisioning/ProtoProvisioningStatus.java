package com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning;

import com.amazon.whisperjoin.common.sharedtypes.exceptions.DataSerializationError;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.ProvisioningStatus;
import com.amazon.whisperjoin.protobuf.ProtobufProvisioningStatusClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes13.dex */
public class ProtoProvisioningStatus implements TypeSerializer<ProvisioningStatus> {
    private ProtobufProvisioningStatusClass.ProtobufProvisioningStatus.State getProtobufState(ProvisioningStatus.State state) {
        return ProtobufProvisioningStatusClass.ProtobufProvisioningStatus.State.valueOf(state.getValue());
    }

    private ProvisioningStatus.State getState(ProtobufProvisioningStatusClass.ProtobufProvisioningStatus.State state) {
        return (ProvisioningStatus.State) Enum.valueOf(ProvisioningStatus.State.class, state.name());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    /* renamed from: deserialize */
    public ProvisioningStatus mo5427deserialize(byte[] bArr) {
        try {
            ProtobufProvisioningStatusClass.ProtobufProvisioningStatus parseFrom = ProtobufProvisioningStatusClass.ProtobufProvisioningStatus.parseFrom(bArr);
            return new ProvisioningStatus(getState(parseFrom.getState()), parseFrom.getInsecureModeSupported());
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError(GeneratedOutlineSupport1.outline36(e, GeneratedOutlineSupport1.outline107("Illegal data: ")), e);
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    public byte[] serialize(ProvisioningStatus provisioningStatus) {
        return ProtobufProvisioningStatusClass.ProtobufProvisioningStatus.newBuilder().setState(getProtobufState(provisioningStatus.getProvisioningState())).setInsecureModeSupported(provisioningStatus.insecureModeSupported()).mo10084build().toByteArray();
    }
}
