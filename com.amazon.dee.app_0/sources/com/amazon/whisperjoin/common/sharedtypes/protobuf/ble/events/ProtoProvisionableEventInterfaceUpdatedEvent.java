package com.amazon.whisperjoin.common.sharedtypes.protobuf.ble.events;

import com.amazon.whisperjoin.common.sharedtypes.ble.events.ProvisionableEventInterfaceUpdatedEvent;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.DataSerializationError;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.provisioning.ProtoProvisionableEventTypeCollection;
import com.amazon.whisperjoin.common.sharedtypes.utility.UuidHelpers;
import com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventInterfaceUpdatedEventClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes13.dex */
public class ProtoProvisionableEventInterfaceUpdatedEvent implements TypeSerializer<ProvisionableEventInterfaceUpdatedEvent> {
    private final ProtoProvisionableEventTypeCollection protoProvisionableEventTypeCollection = new ProtoProvisionableEventTypeCollection();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    /* renamed from: deserialize */
    public ProvisionableEventInterfaceUpdatedEvent mo5427deserialize(byte[] bArr) {
        try {
            return new ProvisionableEventInterfaceUpdatedEvent(this.protoProvisionableEventTypeCollection.getProvisionableEventTypeCollection(ProtobufProvisionableEventInterfaceUpdatedEventClass.ProtobufProvisionableEventInterfaceUpdatedEvent.parseFrom(bArr).getEventData()));
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError(GeneratedOutlineSupport1.outline36(e, GeneratedOutlineSupport1.outline107("Illegal data: ")), e);
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    public byte[] serialize(ProvisionableEventInterfaceUpdatedEvent provisionableEventInterfaceUpdatedEvent) {
        return ProtobufProvisionableEventInterfaceUpdatedEventClass.ProtobufProvisionableEventInterfaceUpdatedEvent.newBuilder().setUuid(ByteString.copyFrom(UuidHelpers.encodeIntoByteArray(provisionableEventInterfaceUpdatedEvent.getUuid()))).setEventData(this.protoProvisionableEventTypeCollection.getProtobufProvisionableEventTypeCollection(provisionableEventInterfaceUpdatedEvent.getEventData())).mo10084build().toByteArray();
    }
}
