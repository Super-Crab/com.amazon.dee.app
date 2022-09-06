package com.amazon.devicesetup.protobuf.ble.events;

import com.amazon.devicesetup.exceptions.DataSerializationError;
import com.amazon.devicesetup.protobuf.data.registration.ProtoCBLRegistrationDetails;
import com.amazon.devicesetup.provisioning.ble.events.CBLRegistrationUpdatedEvent;
import com.amazon.devicesetup.serializer.TypeSerializer;
import com.amazon.devicesetup.utility.UuidHelpers;
import com.amazon.whisperjoin.protobuf.ProtobufCBLRegistrationUpdatedEventClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes12.dex */
public class ProtoCBLRegistrationUpdatedEvent implements TypeSerializer<CBLRegistrationUpdatedEvent> {
    private final ProtoCBLRegistrationDetails protoCBLRegistrationDetails = new ProtoCBLRegistrationDetails();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.devicesetup.serializer.TypeSerializer
    /* renamed from: deserialize */
    public CBLRegistrationUpdatedEvent mo4042deserialize(byte[] bArr) {
        try {
            return new CBLRegistrationUpdatedEvent(this.protoCBLRegistrationDetails.getCBLRegistrationDetails(ProtobufCBLRegistrationUpdatedEventClass.ProtobufCBLRegistrationUpdatedEvent.parseFrom(bArr).getEventData()));
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError(GeneratedOutlineSupport1.outline36(e, GeneratedOutlineSupport1.outline107("Illegal data: ")), e);
        }
    }

    @Override // com.amazon.devicesetup.serializer.TypeSerializer
    public byte[] serialize(CBLRegistrationUpdatedEvent cBLRegistrationUpdatedEvent) {
        return ProtobufCBLRegistrationUpdatedEventClass.ProtobufCBLRegistrationUpdatedEvent.newBuilder().setUuid(ByteString.copyFrom(UuidHelpers.encodeIntoByteArray(cBLRegistrationUpdatedEvent.getUuid()))).setEventData(this.protoCBLRegistrationDetails.getProtobufCBLRegistrationDetails(cBLRegistrationUpdatedEvent.getEventData())).mo10084build().toByteArray();
    }
}
