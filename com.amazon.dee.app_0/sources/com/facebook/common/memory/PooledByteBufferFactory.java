package com.facebook.common.memory;

import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes2.dex */
public interface PooledByteBufferFactory {
    /* renamed from: newByteBuffer */
    PooledByteBuffer mo6907newByteBuffer(int size);

    /* renamed from: newByteBuffer */
    PooledByteBuffer mo6908newByteBuffer(InputStream inputStream) throws IOException;

    /* renamed from: newByteBuffer */
    PooledByteBuffer mo6909newByteBuffer(InputStream inputStream, int initialCapacity) throws IOException;

    /* renamed from: newByteBuffer */
    PooledByteBuffer mo6910newByteBuffer(byte[] bytes);

    /* renamed from: newOutputStream */
    PooledByteBufferOutputStream mo6911newOutputStream();

    /* renamed from: newOutputStream */
    PooledByteBufferOutputStream mo6912newOutputStream(int initialCapacity);
}
