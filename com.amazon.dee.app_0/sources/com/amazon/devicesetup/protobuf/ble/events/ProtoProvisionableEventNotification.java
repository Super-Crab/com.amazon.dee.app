package com.amazon.devicesetup.protobuf.ble.events;

import com.amazon.devicesetup.exceptions.DataSerializationError;
import com.amazon.devicesetup.provisioning.ble.events.ProvisionableEventNotification;
import com.amazon.devicesetup.serializer.TypeSerializer;
import com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventNotificationClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes12.dex */
public class ProtoProvisionableEventNotification implements TypeSerializer<ProvisionableEventNotification> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.devicesetup.serializer.TypeSerializer
    /* renamed from: deserialize */
    public ProvisionableEventNotification mo4042deserialize(byte[] bArr) {
        try {
            ProtobufProvisionableEventNotificationClass.ProtobufProvisionableEventNotification parseFrom = ProtobufProvisionableEventNotificationClass.ProtobufProvisionableEventNotification.parseFrom(bArr);
            return new ProvisionableEventNotification(parseFrom.getEventKey(), parseFrom.getEventType());
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError(GeneratedOutlineSupport1.outline36(e, GeneratedOutlineSupport1.outline107("Illegal data: ")), e);
        }
    }

    @Override // com.amazon.devicesetup.serializer.TypeSerializer
    public byte[] serialize(ProvisionableEventNotification provisionableEventNotification) {
        return ProtobufProvisionableEventNotificationClass.ProtobufProvisionableEventNotification.newBuilder().setEventType(provisionableEventNotification.getEventType()).setEventKey(provisionableEventNotification.getEventKey()).mo10084build().toByteArray();
    }
}
