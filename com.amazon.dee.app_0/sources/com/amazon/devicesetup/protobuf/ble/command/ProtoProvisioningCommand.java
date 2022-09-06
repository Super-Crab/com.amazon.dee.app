package com.amazon.devicesetup.protobuf.ble.command;

import com.amazon.devicesetup.exceptions.DataSerializationError;
import com.amazon.devicesetup.provisioning.ble.command.ProvisioningCommand;
import com.amazon.devicesetup.serializer.TypeSerializer;
import com.amazon.devicesetup.utility.UuidHelpers;
import com.amazon.whisperjoin.protobuf.ProtobufProvisioningCommandClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes12.dex */
public class ProtoProvisioningCommand implements TypeSerializer<ProvisioningCommand> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.devicesetup.serializer.TypeSerializer
    /* renamed from: deserialize */
    public ProvisioningCommand mo4042deserialize(byte[] bArr) {
        try {
            ProtobufProvisioningCommandClass.ProtobufProvisioningCommand parseFrom = ProtobufProvisioningCommandClass.ProtobufProvisioningCommand.parseFrom(bArr);
            return new ProvisioningCommand(UuidHelpers.decodeIntoUuid(parseFrom.getUuid().toByteArray()), parseFrom.getData() == null ? null : parseFrom.getData().toByteArray());
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError(GeneratedOutlineSupport1.outline36(e, GeneratedOutlineSupport1.outline107("Illegal data: ")), e);
        }
    }

    @Override // com.amazon.devicesetup.serializer.TypeSerializer
    public byte[] serialize(ProvisioningCommand provisioningCommand) {
        ProtobufProvisioningCommandClass.ProtobufProvisioningCommand.Builder uuid = ProtobufProvisioningCommandClass.ProtobufProvisioningCommand.newBuilder().setUuid(ByteString.copyFrom(UuidHelpers.encodeIntoByteArray(provisioningCommand.getIdentifier())));
        if (provisioningCommand.getData() != null) {
            uuid.setData(ByteString.copyFrom(provisioningCommand.getData()));
        }
        return uuid.mo10084build().toByteArray();
    }
}
