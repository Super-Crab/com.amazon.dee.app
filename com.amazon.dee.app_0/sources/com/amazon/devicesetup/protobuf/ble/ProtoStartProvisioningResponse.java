package com.amazon.devicesetup.protobuf.ble;

import com.amazon.devicesetup.exceptions.DataSerializationError;
import com.amazon.devicesetup.provisioning.ble.StartProvisioningResponse;
import com.amazon.devicesetup.serializer.TypeSerializer;
import com.amazon.whisperjoin.protobuf.ProtobufStartProvisioningResponseClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes12.dex */
public class ProtoStartProvisioningResponse implements TypeSerializer<StartProvisioningResponse> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.devicesetup.serializer.TypeSerializer
    /* renamed from: deserialize */
    public StartProvisioningResponse mo4042deserialize(byte[] bArr) {
        try {
            ProtobufStartProvisioningResponseClass.ProtobufStartProvisioningResponse parseFrom = ProtobufStartProvisioningResponseClass.ProtobufStartProvisioningResponse.parseFrom(bArr);
            return new StartProvisioningResponse(parseFrom.getRequestId(), (byte) parseFrom.getStatus());
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError(GeneratedOutlineSupport1.outline36(e, GeneratedOutlineSupport1.outline107("Illegal data: ")), e);
        }
    }

    @Override // com.amazon.devicesetup.serializer.TypeSerializer
    public byte[] serialize(StartProvisioningResponse startProvisioningResponse) {
        return ProtobufStartProvisioningResponseClass.ProtobufStartProvisioningResponse.newBuilder().setRequestId(startProvisioningResponse.getRequestID()).setStatus(startProvisioningResponse.getStatus()).mo10084build().toByteArray();
    }
}
