package com.amazon.whisperjoin.common.sharedtypes.protobuf.ble.events;

import com.amazon.whisperjoin.common.sharedtypes.ble.events.WifiConnectionUpdatedEvent;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.DataSerializationError;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.wifi.ProtoWifiConnectionDetails;
import com.amazon.whisperjoin.common.sharedtypes.utility.UuidHelpers;
import com.amazon.whisperjoin.protobuf.ProtobufWifiConnectionUpdatedEventClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes13.dex */
public class ProtoWifiConnectionUpdatedEvent implements TypeSerializer<WifiConnectionUpdatedEvent> {
    private final ProtoWifiConnectionDetails protoWifiConnectionDetails = new ProtoWifiConnectionDetails();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    /* renamed from: deserialize */
    public WifiConnectionUpdatedEvent mo5427deserialize(byte[] bArr) {
        try {
            return new WifiConnectionUpdatedEvent(this.protoWifiConnectionDetails.getWifiConnectionDetails(ProtobufWifiConnectionUpdatedEventClass.ProtobufWifiConnectionUpdatedEvent.parseFrom(bArr).getEventData()));
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError(GeneratedOutlineSupport1.outline36(e, GeneratedOutlineSupport1.outline107("Illegal data: ")), e);
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    public byte[] serialize(WifiConnectionUpdatedEvent wifiConnectionUpdatedEvent) {
        return ProtobufWifiConnectionUpdatedEventClass.ProtobufWifiConnectionUpdatedEvent.newBuilder().setUuid(ByteString.copyFrom(UuidHelpers.encodeIntoByteArray(wifiConnectionUpdatedEvent.getUuid()))).setEventData(this.protoWifiConnectionDetails.getProtobufWifiConnectionDetails(wifiConnectionUpdatedEvent.getEventData())).mo10084build().toByteArray();
    }
}
