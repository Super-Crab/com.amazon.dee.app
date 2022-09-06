package com.amazon.devicesetup.protobuf.data;

import com.amazon.devicesetup.exceptions.DataSerializationError;
import com.amazon.devicesetup.serializer.TypeSerializer;
import com.amazon.whisperjoin.protobuf.ProtobufBooleanClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes12.dex */
public class ProtoBoolean implements TypeSerializer<Boolean> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.devicesetup.serializer.TypeSerializer
    /* renamed from: deserialize */
    public Boolean mo4042deserialize(byte[] bArr) {
        try {
            return Boolean.valueOf(ProtobufBooleanClass.ProtobufBoolean.parseFrom(bArr).getValue());
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError(GeneratedOutlineSupport1.outline36(e, GeneratedOutlineSupport1.outline107("Illegal data: ")), e);
        }
    }

    @Override // com.amazon.devicesetup.serializer.TypeSerializer
    public byte[] serialize(Boolean bool) {
        return ProtobufBooleanClass.ProtobufBoolean.newBuilder().setValue(bool.booleanValue()).mo10084build().toByteArray();
    }
}
