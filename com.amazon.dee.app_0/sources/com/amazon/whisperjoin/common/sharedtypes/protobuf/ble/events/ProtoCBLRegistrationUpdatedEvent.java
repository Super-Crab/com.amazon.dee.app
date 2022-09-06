package com.amazon.whisperjoin.common.sharedtypes.protobuf.ble.events;

import com.amazon.whisperjoin.common.sharedtypes.ble.events.CBLRegistrationUpdatedEvent;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.DataSerializationError;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.registration.ProtoCBLRegistrationDetails;
import com.amazon.whisperjoin.common.sharedtypes.utility.UuidHelpers;
import com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationUpdatedEventClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes13.dex */
public class ProtoCBLRegistrationUpdatedEvent implements TypeSerializer<CBLRegistrationUpdatedEvent> {
    private final ProtoCBLRegistrationDetails protoCBLRegistrationDetails = new ProtoCBLRegistrationDetails();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    /* renamed from: deserialize */
    public CBLRegistrationUpdatedEvent mo5427deserialize(byte[] bArr) {
        try {
            return new CBLRegistrationUpdatedEvent(this.protoCBLRegistrationDetails.getCBLRegistrationDetails(ProtobufCBLRegistrationUpdatedEventClass.ProtobufCBLRegistrationUpdatedEvent.parseFrom(bArr).getEventData()));
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError(GeneratedOutlineSupport1.outline36(e, GeneratedOutlineSupport1.outline107("Illegal data: ")), e);
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    public byte[] serialize(CBLRegistrationUpdatedEvent cBLRegistrationUpdatedEvent) {
        return ProtobufCBLRegistrationUpdatedEventClass.ProtobufCBLRegistrationUpdatedEvent.newBuilder().setUuid(ByteString.copyFrom(UuidHelpers.encodeIntoByteArray(cBLRegistrationUpdatedEvent.getUuid()))).setEventData(this.protoCBLRegistrationDetails.getProtobufCBLRegistrationDetails(cBLRegistrationUpdatedEvent.getEventData())).mo10084build().toByteArray();
    }
}
