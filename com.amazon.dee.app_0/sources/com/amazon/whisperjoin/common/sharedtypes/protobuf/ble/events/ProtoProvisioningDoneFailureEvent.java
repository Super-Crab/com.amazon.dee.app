package com.amazon.whisperjoin.common.sharedtypes.protobuf.ble.events;

import com.amazon.whisperjoin.common.sharedtypes.ble.events.ProvisioningDoneFailureEvent;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.provisioning.ProtoProvisioningFailure;
import com.amazon.whisperjoin.common.sharedtypes.utility.UuidHelpers;
import com.amazon.whisperjoin.protobuf.ProtobufProvisioningDoneFailureEventClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes13.dex */
public class ProtoProvisioningDoneFailureEvent implements TypeSerializer<ProvisioningDoneFailureEvent> {
    final ProtoProvisioningFailure mProtoProvisioningFailure;

    public ProtoProvisioningDoneFailureEvent(ProtoProvisioningFailure protoProvisioningFailure) {
        this.mProtoProvisioningFailure = protoProvisioningFailure;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    /* renamed from: deserialize */
    public ProvisioningDoneFailureEvent mo5427deserialize(byte[] bArr) {
        try {
            return new ProvisioningDoneFailureEvent(this.mProtoProvisioningFailure.createProvisioningFailureFromProto(ProtobufProvisioningDoneFailureEventClass.ProtobufProvisioningDoneFailureEvent.parseFrom(bArr).getEventData()));
        } catch (InvalidProtocolBufferException e) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline36(e, GeneratedOutlineSupport1.outline107("Illegal data: ")), e);
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    public byte[] serialize(ProvisioningDoneFailureEvent provisioningDoneFailureEvent) {
        return ProtobufProvisioningDoneFailureEventClass.ProtobufProvisioningDoneFailureEvent.newBuilder().setUuid(ByteString.copyFrom(UuidHelpers.encodeIntoByteArray(provisioningDoneFailureEvent.getUuid()))).setEventData(this.mProtoProvisioningFailure.getProtoProvisioningFailure(provisioningDoneFailureEvent.getEventData())).mo10084build().toByteArray();
    }
}
