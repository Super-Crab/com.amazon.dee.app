package com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data;

import com.amazon.whisperjoin.common.sharedtypes.exceptions.DataSerializationError;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer;
import com.amazon.whisperjoin.protobuf.ProtobufIntegerClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes13.dex */
public class ProtoInteger implements TypeSerializer<Integer> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    /* renamed from: deserialize */
    public Integer mo5427deserialize(byte[] bArr) {
        try {
            return Integer.valueOf(ProtobufIntegerClass.ProtobufInteger.parseFrom(bArr).getValue());
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError(GeneratedOutlineSupport1.outline36(e, GeneratedOutlineSupport1.outline107("Illegal data: ")), e);
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    public byte[] serialize(Integer num) {
        return ProtobufIntegerClass.ProtobufInteger.newBuilder().setValue(num.intValue()).mo10084build().toByteArray();
    }
}
