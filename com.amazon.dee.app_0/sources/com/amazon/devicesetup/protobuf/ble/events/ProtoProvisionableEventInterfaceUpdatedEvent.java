package com.amazon.devicesetup.protobuf.ble.events;

import com.amazon.devicesetup.exceptions.DataSerializationError;
import com.amazon.devicesetup.protobuf.data.device.ProtoProvisionableEventTypeCollection;
import com.amazon.devicesetup.provisioning.ble.events.ProvisionableEventInterfaceUpdatedEvent;
import com.amazon.devicesetup.serializer.TypeSerializer;
import com.amazon.devicesetup.utility.UuidHelpers;
import com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventInterfaceUpdatedEventClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes12.dex */
public class ProtoProvisionableEventInterfaceUpdatedEvent implements TypeSerializer<ProvisionableEventInterfaceUpdatedEvent> {
    private final ProtoProvisionableEventTypeCollection protoProvisionableEventTypeCollection = new ProtoProvisionableEventTypeCollection();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.devicesetup.serializer.TypeSerializer
    /* renamed from: deserialize */
    public ProvisionableEventInterfaceUpdatedEvent mo4042deserialize(byte[] bArr) {
        try {
            return new ProvisionableEventInterfaceUpdatedEvent(this.protoProvisionableEventTypeCollection.getProvisionableEventTypeCollection(ProtobufProvisionableEventInterfaceUpdatedEventClass.ProtobufProvisionableEventInterfaceUpdatedEvent.parseFrom(bArr).getEventData()));
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError(GeneratedOutlineSupport1.outline36(e, GeneratedOutlineSupport1.outline107("Illegal data: ")), e);
        }
    }

    @Override // com.amazon.devicesetup.serializer.TypeSerializer
    public byte[] serialize(ProvisionableEventInterfaceUpdatedEvent provisionableEventInterfaceUpdatedEvent) {
        return ProtobufProvisionableEventInterfaceUpdatedEventClass.ProtobufProvisionableEventInterfaceUpdatedEvent.newBuilder().setUuid(ByteString.copyFrom(UuidHelpers.encodeIntoByteArray(provisionableEventInterfaceUpdatedEvent.getUuid()))).setEventData(this.protoProvisionableEventTypeCollection.getProtobufProvisionableEventTypeCollection(provisionableEventInterfaceUpdatedEvent.getEventData())).mo10084build().toByteArray();
    }
}
