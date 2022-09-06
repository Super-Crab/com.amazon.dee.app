package com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.configuration;

import com.amazon.whisperjoin.common.sharedtypes.exceptions.DataSerializationError;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.configuration.DataMap;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.configuration.DataMapValue;
import com.amazon.whisperjoin.protobuf.ProtobufDataMapClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.List;
import java.util.Map;
/* loaded from: classes13.dex */
public class ProtoDataMap implements TypeSerializer<DataMap> {
    public DataMap deserializeMap(ProtobufDataMapClass.ProtobufDataMap protobufDataMap) {
        List<ProtobufDataMapClass.ProtobufMapEntry> dataMapList = protobufDataMap.getDataMapList();
        DataMap dataMap = new DataMap();
        for (ProtobufDataMapClass.ProtobufMapEntry protobufMapEntry : dataMapList) {
            String key = protobufMapEntry.getKey();
            if (protobufMapEntry.hasBytesValue()) {
                dataMap.putValue(key, protobufMapEntry.getBytesValue().toByteArray());
            } else if (protobufMapEntry.hasStringValue()) {
                dataMap.putStringValue(key, protobufMapEntry.getStringValue());
            } else if (protobufMapEntry.hasSint32Value()) {
                dataMap.putIntegerValue(key, Integer.valueOf(protobufMapEntry.getSint32Value()));
            } else if (protobufMapEntry.hasBoolValue()) {
                dataMap.putBooleanValue(key, Boolean.valueOf(protobufMapEntry.getBoolValue()));
            } else {
                throw new DataSerializationError(GeneratedOutlineSupport1.outline72("Unknown DataValue Type for key ", key));
            }
        }
        return dataMap;
    }

    public ProtobufDataMapClass.ProtobufDataMap serializeMap(DataMap dataMap) {
        Map<String, DataMapValue> map = dataMap.getMap();
        ProtobufDataMapClass.ProtobufDataMap.Builder newBuilder = ProtobufDataMapClass.ProtobufDataMap.newBuilder();
        for (Map.Entry<String, DataMapValue> entry : map.entrySet()) {
            ProtobufDataMapClass.ProtobufMapEntry.Builder key = ProtobufDataMapClass.ProtobufMapEntry.newBuilder().setKey(entry.getKey());
            DataMapValue value = entry.getValue();
            if (value.getBytesValue() != null) {
                key.setBytesValue(ByteString.copyFrom(value.getBytesValue()));
                newBuilder.addDataMap(key.mo10084build());
            } else if (value.getStringValue() != null) {
                key.setStringValue(value.getStringValue());
                newBuilder.addDataMap(key.mo10084build());
            } else if (value.getIntegerValue() != null) {
                key.setSint32Value(value.getIntegerValue().intValue());
                newBuilder.addDataMap(key.mo10084build());
            } else if (value.getBooleanValue() != null) {
                key.setBoolValue(value.getBooleanValue().booleanValue());
                newBuilder.addDataMap(key.mo10084build());
            } else {
                throw new DataSerializationError("DataMapValue doesn't contain any value");
            }
        }
        return newBuilder.mo10084build();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    /* renamed from: deserialize */
    public DataMap mo5427deserialize(byte[] bArr) {
        try {
            return deserializeMap(ProtobufDataMapClass.ProtobufDataMap.parseFrom(bArr));
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError(GeneratedOutlineSupport1.outline36(e, GeneratedOutlineSupport1.outline107("Illegal data: ")), e);
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    public byte[] serialize(DataMap dataMap) {
        return serializeMap(dataMap).toByteArray();
    }
}
