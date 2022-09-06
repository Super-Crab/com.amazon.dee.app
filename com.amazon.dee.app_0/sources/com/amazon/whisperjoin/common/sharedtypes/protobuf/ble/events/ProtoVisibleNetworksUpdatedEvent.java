package com.amazon.whisperjoin.common.sharedtypes.protobuf.ble.events;

import com.amazon.whisperjoin.common.sharedtypes.ble.events.VisibleNetworksUpdatedEvent;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.DataSerializationError;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.wifi.ProtoWifiScanResultCollection;
import com.amazon.whisperjoin.common.sharedtypes.utility.UuidHelpers;
import com.amazon.whisperjoin.protobuf.ProtobufVisibleNetworksUpdatedEventClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes13.dex */
public class ProtoVisibleNetworksUpdatedEvent implements TypeSerializer<VisibleNetworksUpdatedEvent> {
    private final ProtoWifiScanResultCollection protoWifiScanResultCollection = new ProtoWifiScanResultCollection();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    /* renamed from: deserialize */
    public VisibleNetworksUpdatedEvent mo5427deserialize(byte[] bArr) {
        try {
            return new VisibleNetworksUpdatedEvent(this.protoWifiScanResultCollection.getWifiScanResultCollection(ProtobufVisibleNetworksUpdatedEventClass.ProtobufVisibleNetworksUpdatedEvent.parseFrom(bArr).getEventData()));
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError(GeneratedOutlineSupport1.outline36(e, GeneratedOutlineSupport1.outline107("Illegal data: ")), e);
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    public byte[] serialize(VisibleNetworksUpdatedEvent visibleNetworksUpdatedEvent) {
        return ProtobufVisibleNetworksUpdatedEventClass.ProtobufVisibleNetworksUpdatedEvent.newBuilder().setUuid(ByteString.copyFrom(UuidHelpers.encodeIntoByteArray(visibleNetworksUpdatedEvent.getUuid()))).setEventData(this.protoWifiScanResultCollection.getProtobufWifiScanResultCollection(visibleNetworksUpdatedEvent.getEventData())).mo10084build().toByteArray();
    }
}
