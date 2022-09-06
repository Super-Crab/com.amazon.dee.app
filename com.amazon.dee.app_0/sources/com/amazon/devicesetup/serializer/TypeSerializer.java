package com.amazon.devicesetup.serializer;
/* loaded from: classes12.dex */
public interface TypeSerializer<T> {
    /* renamed from: deserialize */
    T mo4042deserialize(byte[] bArr);

    byte[] serialize(T t);
}
