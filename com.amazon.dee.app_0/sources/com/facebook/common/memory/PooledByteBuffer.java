package com.facebook.common.memory;

import java.io.Closeable;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public interface PooledByteBuffer extends Closeable {

    /* loaded from: classes2.dex */
    public static class ClosedException extends RuntimeException {
        public ClosedException() {
            super("Invalid bytebuf. Already closed");
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    @Nullable
    ByteBuffer getByteBuffer();

    long getNativePtr();

    boolean isClosed();

    byte read(int offset);

    int read(int offset, byte[] buffer, int bufferOffset, int length);

    int size();
}
