package com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data;

import com.amazon.whisperjoin.common.sharedtypes.exceptions.DataSerializationError;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer;
import com.amazon.whisperjoin.protobuf.ProtobufStringClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes13.dex */
public class ProtoString implements TypeSerializer<String> {
    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    /* renamed from: deserialize  reason: collision with other method in class */
    public String mo5427deserialize(byte[] bArr) {
        try {
            return ProtobufStringClass.ProtobufString.parseFrom(bArr).getValue();
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError(GeneratedOutlineSupport1.outline36(e, GeneratedOutlineSupport1.outline107("Illegal data: ")), e);
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    public byte[] serialize(String str) {
        return ProtobufStringClass.ProtobufString.newBuilder().setValue(str).mo10084build().toByteArray();
    }
}
