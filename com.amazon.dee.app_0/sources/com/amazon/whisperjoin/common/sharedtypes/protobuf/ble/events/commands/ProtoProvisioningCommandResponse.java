package com.amazon.whisperjoin.common.sharedtypes.protobuf.ble.events.commands;

import com.amazon.whisperjoin.common.sharedtypes.ble.commands.ProvisioningCommandResponse;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.DataSerializationError;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer;
import com.amazon.whisperjoin.common.sharedtypes.utility.UuidHelpers;
import com.amazon.whisperjoin.protobuf.ProtobufProvisioningCommandResponseClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes13.dex */
public class ProtoProvisioningCommandResponse implements TypeSerializer<ProvisioningCommandResponse> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    /* renamed from: deserialize */
    public ProvisioningCommandResponse mo5427deserialize(byte[] bArr) {
        try {
            ProtobufProvisioningCommandResponseClass.ProtobufProvisioningCommandResponse parseFrom = ProtobufProvisioningCommandResponseClass.ProtobufProvisioningCommandResponse.parseFrom(bArr);
            return new ProvisioningCommandResponse(UuidHelpers.decodeIntoUuid(parseFrom.getUuid().toByteArray()), parseFrom.getStatus(), parseFrom.getData() == null ? null : parseFrom.getData().toByteArray());
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError(GeneratedOutlineSupport1.outline36(e, GeneratedOutlineSupport1.outline107("Illegal data: ")), e);
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    public byte[] serialize(ProvisioningCommandResponse provisioningCommandResponse) {
        ProtobufProvisioningCommandResponseClass.ProtobufProvisioningCommandResponse.Builder status = ProtobufProvisioningCommandResponseClass.ProtobufProvisioningCommandResponse.newBuilder().setUuid(ByteString.copyFrom(UuidHelpers.encodeIntoByteArray(provisioningCommandResponse.getIdentifier()))).setStatus(provisioningCommandResponse.getStatus());
        if (provisioningCommandResponse.getData() != null) {
            status.setData(ByteString.copyFrom(provisioningCommandResponse.getData()));
        }
        return status.mo10084build().toByteArray();
    }
}
