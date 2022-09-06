package com.facebook.imagepipeline.memory;

import androidx.annotation.VisibleForTesting;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
@ThreadSafe
/* loaded from: classes2.dex */
public class MemoryPooledByteBuffer implements PooledByteBuffer {
    @GuardedBy("this")
    @VisibleForTesting
    CloseableReference<MemoryChunk> mBufRef;
    private final int mSize;

    public MemoryPooledByteBuffer(CloseableReference<MemoryChunk> bufRef, int size) {
        Preconditions.checkNotNull(bufRef);
        Preconditions.checkArgument(Boolean.valueOf(size >= 0 && size <= bufRef.get().getSize()));
        this.mBufRef = bufRef.m6865clone();
        this.mSize = size;
    }

    @Override // com.facebook.common.memory.PooledByteBuffer, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        CloseableReference.closeSafely(this.mBufRef);
        this.mBufRef = null;
    }

    synchronized void ensureValid() {
        if (isClosed()) {
            throw new PooledByteBuffer.ClosedException();
        }
    }

    @Override // com.facebook.common.memory.PooledByteBuffer
    @Nullable
    public synchronized ByteBuffer getByteBuffer() {
        return this.mBufRef.get().getByteBuffer();
    }

    @GuardedBy("this")
    @VisibleForTesting
    CloseableReference<MemoryChunk> getCloseableReference() {
        return this.mBufRef;
    }

    @Override // com.facebook.common.memory.PooledByteBuffer
    public synchronized long getNativePtr() throws UnsupportedOperationException {
        ensureValid();
        return this.mBufRef.get().getNativePtr();
    }

    @Override // com.facebook.common.memory.PooledByteBuffer
    public synchronized boolean isClosed() {
        return !CloseableReference.isValid(this.mBufRef);
    }

    @Override // com.facebook.common.memory.PooledByteBuffer
    public synchronized byte read(int offset) {
        ensureValid();
        boolean z = true;
        Preconditions.checkArgument(Boolean.valueOf(offset >= 0));
        if (offset >= this.mSize) {
            z = false;
        }
        Preconditions.checkArgument(Boolean.valueOf(z));
        return this.mBufRef.get().read(offset);
    }

    @Override // com.facebook.common.memory.PooledByteBuffer
    public synchronized int size() {
        ensureValid();
        return this.mSize;
    }

    @Override // com.facebook.common.memory.PooledByteBuffer
    public synchronized int read(int offset, byte[] buffer, int bufferOffset, int length) {
        ensureValid();
        Preconditions.checkArgument(Boolean.valueOf(offset + length <= this.mSize));
        return this.mBufRef.get().read(offset, buffer, bufferOffset, length);
    }
}
