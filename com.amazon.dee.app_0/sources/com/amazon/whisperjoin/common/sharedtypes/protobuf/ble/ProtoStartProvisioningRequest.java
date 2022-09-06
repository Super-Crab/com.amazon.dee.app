package com.amazon.whisperjoin.common.sharedtypes.protobuf.ble;

import com.amazon.whisperjoin.common.sharedtypes.ble.StartProvisioningRequest;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.DataSerializationError;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer;
import com.amazon.whisperjoin.protobuf.ProtobufStartProvisioningRequestClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes13.dex */
public class ProtoStartProvisioningRequest implements TypeSerializer<StartProvisioningRequest> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    /* renamed from: deserialize */
    public StartProvisioningRequest mo5427deserialize(byte[] bArr) {
        try {
            return new StartProvisioningRequest(ProtobufStartProvisioningRequestClass.ProtobufStartProvisioningRequest.parseFrom(bArr).getRequestId());
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError(GeneratedOutlineSupport1.outline36(e, GeneratedOutlineSupport1.outline107("Illegal data: ")), e);
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    public byte[] serialize(StartProvisioningRequest startProvisioningRequest) {
        return ProtobufStartProvisioningRequestClass.ProtobufStartProvisioningRequest.newBuilder().setRequestId(startProvisioningRequest.getRequestID()).mo10084build().toByteArray();
    }
}
