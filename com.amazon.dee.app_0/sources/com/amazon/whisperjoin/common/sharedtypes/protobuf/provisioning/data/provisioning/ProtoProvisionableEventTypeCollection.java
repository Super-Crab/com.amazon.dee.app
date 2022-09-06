package com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.provisioning;

import com.amazon.whisperjoin.common.sharedtypes.ble.events.ProvisionableEventType;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.DataSerializationError;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.provisioning.ProvisionableEventTypeCollection;
import com.amazon.whisperjoin.common.sharedtypes.utility.UuidHelpers;
import com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes13.dex */
public class ProtoProvisionableEventTypeCollection implements TypeSerializer<ProvisionableEventTypeCollection> {
    private ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventType createProtobufProvisionableEventType(ProvisionableEventType provisionableEventType) {
        return ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventType.newBuilder().setEventType(provisionableEventType.getEventType()).setEventUuid(ByteString.copyFrom(UuidHelpers.encodeIntoByteArray(provisionableEventType.getEventUuid()))).mo10084build();
    }

    private ProvisionableEventType createProvisionableEventType(ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventType protobufProvisionableEventType) {
        return new ProvisionableEventType(UuidHelpers.decodeIntoUuid(protobufProvisionableEventType.getEventUuid().toByteArray()), protobufProvisionableEventType.getEventType());
    }

    public ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection getProtobufProvisionableEventTypeCollection(ProvisionableEventTypeCollection provisionableEventTypeCollection) {
        ArrayList arrayList = new ArrayList(provisionableEventTypeCollection.getSetCollection());
        ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection.Builder newBuilder = ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection.newBuilder();
        for (int i = 0; i < arrayList.size(); i++) {
            newBuilder.addProtobufProvisionableEventTypeCollection(createProtobufProvisionableEventType((ProvisionableEventType) arrayList.get(i)));
        }
        return newBuilder.mo10084build();
    }

    public ProvisionableEventTypeCollection getProvisionableEventTypeCollection(ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection protobufProvisionableEventTypeCollection) {
        List<ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventType> protobufProvisionableEventTypeCollectionList = protobufProvisionableEventTypeCollection.getProtobufProvisionableEventTypeCollectionList();
        HashMap hashMap = new HashMap();
        for (ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventType protobufProvisionableEventType : protobufProvisionableEventTypeCollectionList) {
            hashMap.put(UuidHelpers.decodeIntoUuid(protobufProvisionableEventType.getEventUuid().toByteArray()), Integer.valueOf(protobufProvisionableEventType.getEventType()));
        }
        return new ProvisionableEventTypeCollection(hashMap);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    /* renamed from: deserialize */
    public ProvisionableEventTypeCollection mo5427deserialize(byte[] bArr) {
        try {
            return getProvisionableEventTypeCollection(ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection.parseFrom(bArr));
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError(GeneratedOutlineSupport1.outline36(e, GeneratedOutlineSupport1.outline107("Illegal data: ")), e);
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    public byte[] serialize(ProvisionableEventTypeCollection provisionableEventTypeCollection) {
        return getProtobufProvisionableEventTypeCollection(provisionableEventTypeCollection).toByteArray();
    }
}
