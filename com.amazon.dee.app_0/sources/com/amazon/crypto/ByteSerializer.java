package com.amazon.crypto;
/* loaded from: classes12.dex */
public interface ByteSerializer<T> {
    T fromBytes(byte[] bArr) throws IllegalArgumentException;

    byte[] toBytes(T t) throws IllegalArgumentException;
}
