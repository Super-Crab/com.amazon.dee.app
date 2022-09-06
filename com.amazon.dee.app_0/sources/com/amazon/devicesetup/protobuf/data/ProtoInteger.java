package com.amazon.devicesetup.protobuf.data;

import com.amazon.devicesetup.exceptions.DataSerializationError;
import com.amazon.devicesetup.serializer.TypeSerializer;
import com.amazon.whisperjoin.protobuf.ProtobufIntegerClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes12.dex */
public class ProtoInteger implements TypeSerializer<Integer> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.devicesetup.serializer.TypeSerializer
    /* renamed from: deserialize */
    public Integer mo4042deserialize(byte[] bArr) {
        try {
            return Integer.valueOf(ProtobufIntegerClass.ProtobufInteger.parseFrom(bArr).getValue());
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError(GeneratedOutlineSupport1.outline36(e, GeneratedOutlineSupport1.outline107("Illegal data: ")), e);
        }
    }

    @Override // com.amazon.devicesetup.serializer.TypeSerializer
    public byte[] serialize(Integer num) {
        return ProtobufIntegerClass.ProtobufInteger.newBuilder().setValue(num.intValue()).mo10084build().toByteArray();
    }
}
