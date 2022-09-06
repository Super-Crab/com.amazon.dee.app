package com.amazon.devicesetup.protobuf.ble;

import com.amazon.devicesetup.exceptions.DataSerializationError;
import com.amazon.devicesetup.provisioning.ble.StartProvisioningRequest;
import com.amazon.devicesetup.serializer.TypeSerializer;
import com.amazon.whisperjoin.protobuf.ProtobufStartProvisioningRequestClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes12.dex */
public class ProtoStartProvisioningRequest implements TypeSerializer<StartProvisioningRequest> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.devicesetup.serializer.TypeSerializer
    /* renamed from: deserialize */
    public StartProvisioningRequest mo4042deserialize(byte[] bArr) {
        try {
            return new StartProvisioningRequest(ProtobufStartProvisioningRequestClass.ProtobufStartProvisioningRequest.parseFrom(bArr).getRequestId());
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError(GeneratedOutlineSupport1.outline36(e, GeneratedOutlineSupport1.outline107("Illegal data: ")), e);
        }
    }

    @Override // com.amazon.devicesetup.serializer.TypeSerializer
    public byte[] serialize(StartProvisioningRequest startProvisioningRequest) {
        return ProtobufStartProvisioningRequestClass.ProtobufStartProvisioningRequest.newBuilder().setRequestId(startProvisioningRequest.getRequestID()).mo10084build().toByteArray();
    }
}
