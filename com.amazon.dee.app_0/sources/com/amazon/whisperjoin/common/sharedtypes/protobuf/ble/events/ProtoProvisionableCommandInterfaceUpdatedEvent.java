package com.amazon.whisperjoin.common.sharedtypes.protobuf.ble.events;

import com.amazon.whisperjoin.common.sharedtypes.ble.events.ProvisionableCommandInterfaceUpdatedEvent;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.DataSerializationError;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.provisioning.ProtoUuidCollection;
import com.amazon.whisperjoin.common.sharedtypes.utility.UuidHelpers;
import com.amazon.whisperjoin.protobuf.ProtobufProvisionableCommandInterfaceUpdatedEventClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes13.dex */
public class ProtoProvisionableCommandInterfaceUpdatedEvent implements TypeSerializer<ProvisionableCommandInterfaceUpdatedEvent> {
    private final ProtoUuidCollection protoUuidCollection = new ProtoUuidCollection();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    /* renamed from: deserialize */
    public ProvisionableCommandInterfaceUpdatedEvent mo5427deserialize(byte[] bArr) {
        try {
            return new ProvisionableCommandInterfaceUpdatedEvent(this.protoUuidCollection.getUuidCollection(ProtobufProvisionableCommandInterfaceUpdatedEventClass.ProtobufProvisionableCommandInterfaceUpdatedEvent.parseFrom(bArr).getEventData()));
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError(GeneratedOutlineSupport1.outline36(e, GeneratedOutlineSupport1.outline107("Illegal data: ")), e);
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    public byte[] serialize(ProvisionableCommandInterfaceUpdatedEvent provisionableCommandInterfaceUpdatedEvent) {
        return ProtobufProvisionableCommandInterfaceUpdatedEventClass.ProtobufProvisionableCommandInterfaceUpdatedEvent.newBuilder().setUuid(ByteString.copyFrom(UuidHelpers.encodeIntoByteArray(provisionableCommandInterfaceUpdatedEvent.getUuid()))).setEventData(this.protoUuidCollection.getProtobufUuidCollection(provisionableCommandInterfaceUpdatedEvent.getEventData())).mo10084build().toByteArray();
    }
}
