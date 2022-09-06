package com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data;

import com.amazon.whisperjoin.common.sharedtypes.exceptions.DataSerializationError;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer;
import com.amazon.whisperjoin.protobuf.ProtobufBooleanClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes13.dex */
public class ProtoBoolean implements TypeSerializer<Boolean> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    /* renamed from: deserialize */
    public Boolean mo5427deserialize(byte[] bArr) {
        try {
            return Boolean.valueOf(ProtobufBooleanClass.ProtobufBoolean.parseFrom(bArr).getValue());
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError(GeneratedOutlineSupport1.outline36(e, GeneratedOutlineSupport1.outline107("Illegal data: ")), e);
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    public byte[] serialize(Boolean bool) {
        return ProtobufBooleanClass.ProtobufBoolean.newBuilder().setValue(bool.booleanValue()).mo10084build().toByteArray();
    }
}
