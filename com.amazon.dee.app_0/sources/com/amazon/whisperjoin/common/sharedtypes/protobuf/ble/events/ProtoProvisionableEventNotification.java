package com.amazon.whisperjoin.common.sharedtypes.protobuf.ble.events;

import com.amazon.whisperjoin.common.sharedtypes.ble.events.ProvisionableEventNotification;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.DataSerializationError;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer;
import com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventNotificationClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes13.dex */
public class ProtoProvisionableEventNotification implements TypeSerializer<ProvisionableEventNotification> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    /* renamed from: deserialize */
    public ProvisionableEventNotification mo5427deserialize(byte[] bArr) {
        try {
            ProtobufProvisionableEventNotificationClass.ProtobufProvisionableEventNotification parseFrom = ProtobufProvisionableEventNotificationClass.ProtobufProvisionableEventNotification.parseFrom(bArr);
            return new ProvisionableEventNotification(parseFrom.getEventKey(), parseFrom.getEventType());
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError(GeneratedOutlineSupport1.outline36(e, GeneratedOutlineSupport1.outline107("Illegal data: ")), e);
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    public byte[] serialize(ProvisionableEventNotification provisionableEventNotification) {
        return ProtobufProvisionableEventNotificationClass.ProtobufProvisionableEventNotification.newBuilder().setEventType(provisionableEventNotification.getEventType()).setEventKey(provisionableEventNotification.getEventKey()).mo10084build().toByteArray();
    }
}
