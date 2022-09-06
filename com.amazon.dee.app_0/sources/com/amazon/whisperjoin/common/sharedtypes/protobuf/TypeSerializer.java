package com.amazon.whisperjoin.common.sharedtypes.protobuf;
/* loaded from: classes13.dex */
public interface TypeSerializer<T> {
    /* renamed from: deserialize */
    T mo5427deserialize(byte[] bArr);

    byte[] serialize(T t);
}
