package com.amazon.devicesetup.protobuf.ble.events;

import com.amazon.devicesetup.protobuf.data.device.ProtoProvisioningFailure;
import com.amazon.devicesetup.provisioning.ble.events.ProvisioningDoneFailureEvent;
import com.amazon.devicesetup.serializer.TypeSerializer;
import com.amazon.devicesetup.utility.UuidHelpers;
import com.amazon.whisperjoin.protobuf.ProtobufProvisioningDoneFailureEventClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes12.dex */
public class ProtoProvisioningDoneFailureEvent implements TypeSerializer<ProvisioningDoneFailureEvent> {
    final ProtoProvisioningFailure mProtoProvisioningFailure;

    public ProtoProvisioningDoneFailureEvent(ProtoProvisioningFailure protoProvisioningFailure) {
        this.mProtoProvisioningFailure = protoProvisioningFailure;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.devicesetup.serializer.TypeSerializer
    /* renamed from: deserialize */
    public ProvisioningDoneFailureEvent mo4042deserialize(byte[] bArr) {
        try {
            return new ProvisioningDoneFailureEvent(this.mProtoProvisioningFailure.createProvisioningFailureFromProto(ProtobufProvisioningDoneFailureEventClass.ProtobufProvisioningDoneFailureEvent.parseFrom(bArr).getEventData()));
        } catch (InvalidProtocolBufferException e) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline36(e, GeneratedOutlineSupport1.outline107("Illegal data: ")), e);
        }
    }

    @Override // com.amazon.devicesetup.serializer.TypeSerializer
    public byte[] serialize(ProvisioningDoneFailureEvent provisioningDoneFailureEvent) {
        return ProtobufProvisioningDoneFailureEventClass.ProtobufProvisioningDoneFailureEvent.newBuilder().setUuid(ByteString.copyFrom(UuidHelpers.encodeIntoByteArray(provisioningDoneFailureEvent.getUuid()))).setEventData(this.mProtoProvisioningFailure.getProtoProvisioningFailure(provisioningDoneFailureEvent.getEventData())).mo10084build().toByteArray();
    }
}
