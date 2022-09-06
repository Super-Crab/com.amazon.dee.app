package com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.provisioning;

import com.amazon.whisperjoin.common.sharedtypes.exceptions.DataSerializationError;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.ValidatableUuid;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.provisioning.UuidCollection;
import com.amazon.whisperjoin.common.sharedtypes.utility.UuidHelpers;
import com.amazon.whisperjoin.protobuf.ProtobufValidatableUuidClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
/* loaded from: classes13.dex */
public class ProtoUuidCollection implements TypeSerializer<UuidCollection> {
    private ProtobufValidatableUuidClass.ProtobufValidatableUuid createProtobufValidatableUuid(ValidatableUuid validatableUuid) {
        return ProtobufValidatableUuidClass.ProtobufValidatableUuid.newBuilder().setValue(ByteString.copyFrom(UuidHelpers.encodeIntoByteArray(validatableUuid.getValue()))).mo10084build();
    }

    private UUID createValidatableUuid(ProtobufValidatableUuidClass.ProtobufValidatableUuid protobufValidatableUuid) {
        return UuidHelpers.decodeIntoUuid(protobufValidatableUuid.getValue().toByteArray());
    }

    public ProtobufValidatableUuidClass.ProtobufUuidCollection getProtobufUuidCollection(UuidCollection uuidCollection) {
        ArrayList arrayList = new ArrayList(uuidCollection.getSetCollection());
        ProtobufValidatableUuidClass.ProtobufUuidCollection.Builder newBuilder = ProtobufValidatableUuidClass.ProtobufUuidCollection.newBuilder();
        for (int i = 0; i < arrayList.size(); i++) {
            newBuilder.addProtobufUuidCollection(createProtobufValidatableUuid((ValidatableUuid) arrayList.get(i)));
        }
        return newBuilder.mo10084build();
    }

    public UuidCollection getUuidCollection(ProtobufValidatableUuidClass.ProtobufUuidCollection protobufUuidCollection) {
        List<ProtobufValidatableUuidClass.ProtobufValidatableUuid> protobufUuidCollectionList = protobufUuidCollection.getProtobufUuidCollectionList();
        ArrayList arrayList = new ArrayList();
        for (ProtobufValidatableUuidClass.ProtobufValidatableUuid protobufValidatableUuid : protobufUuidCollectionList) {
            arrayList.add(createValidatableUuid(protobufValidatableUuid));
        }
        return new UuidCollection(arrayList);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    /* renamed from: deserialize */
    public UuidCollection mo5427deserialize(byte[] bArr) {
        try {
            return getUuidCollection(ProtobufValidatableUuidClass.ProtobufUuidCollection.parseFrom(bArr));
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError(GeneratedOutlineSupport1.outline36(e, GeneratedOutlineSupport1.outline107("Illegal data: ")), e);
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    public byte[] serialize(UuidCollection uuidCollection) {
        return getProtobufUuidCollection(uuidCollection).toByteArray();
    }
}
