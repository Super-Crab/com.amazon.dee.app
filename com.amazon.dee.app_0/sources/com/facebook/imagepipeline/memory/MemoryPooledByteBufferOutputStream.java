package com.facebook.imagepipeline.memory;

import androidx.annotation.VisibleForTesting;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.memory.PooledByteBufferOutputStream;
import com.facebook.common.references.CloseableReference;
import java.io.IOException;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
@NotThreadSafe
/* loaded from: classes2.dex */
public class MemoryPooledByteBufferOutputStream extends PooledByteBufferOutputStream {
    @Nullable
    private CloseableReference<MemoryChunk> mBufRef;
    private int mCount;
    private final MemoryChunkPool mPool;

    /* loaded from: classes2.dex */
    public static class InvalidStreamException extends RuntimeException {
        public InvalidStreamException() {
            super("OutputStream no longer valid");
        }
    }

    public MemoryPooledByteBufferOutputStream(MemoryChunkPool pool) {
        this(pool, pool.getMinBufferSize());
    }

    private void ensureValid() {
        if (CloseableReference.isValid(this.mBufRef)) {
            return;
        }
        throw new InvalidStreamException();
    }

    @Override // com.facebook.common.memory.PooledByteBufferOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        CloseableReference.closeSafely(this.mBufRef);
        this.mBufRef = null;
        this.mCount = -1;
        super.close();
    }

    @VisibleForTesting
    void realloc(int newLength) {
        ensureValid();
        Preconditions.checkNotNull(this.mBufRef);
        if (newLength <= this.mBufRef.get().getSize()) {
            return;
        }
        MemoryChunk mo6905get = this.mPool.mo6905get(newLength);
        Preconditions.checkNotNull(this.mBufRef);
        this.mBufRef.get().copy(0, mo6905get, 0, this.mCount);
        this.mBufRef.close();
        this.mBufRef = CloseableReference.of(mo6905get, this.mPool);
    }

    @Override // com.facebook.common.memory.PooledByteBufferOutputStream
    public int size() {
        return this.mCount;
    }

    @Override // java.io.OutputStream
    public void write(int oneByte) throws IOException {
        write(new byte[]{(byte) oneByte});
    }

    public MemoryPooledByteBufferOutputStream(MemoryChunkPool pool, int initialCapacity) {
        Preconditions.checkArgument(Boolean.valueOf(initialCapacity > 0));
        this.mPool = (MemoryChunkPool) Preconditions.checkNotNull(pool);
        this.mCount = 0;
        this.mBufRef = CloseableReference.of(this.mPool.mo6905get(initialCapacity), this.mPool);
    }

    @Override // com.facebook.common.memory.PooledByteBufferOutputStream
    /* renamed from: toByteBuffer  reason: collision with other method in class */
    public MemoryPooledByteBuffer mo6913toByteBuffer() {
        ensureValid();
        return new MemoryPooledByteBuffer((CloseableReference) Preconditions.checkNotNull(this.mBufRef), this.mCount);
    }

    @Override // java.io.OutputStream
    public void write(byte[] buffer, int offset, int count) throws IOException {
        if (offset >= 0 && count >= 0 && offset + count <= buffer.length) {
            ensureValid();
            realloc(this.mCount + count);
            ((MemoryChunk) ((CloseableReference) Preconditions.checkNotNull(this.mBufRef)).get()).write(this.mCount, buffer, offset, count);
            this.mCount += count;
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("length=");
        GeneratedOutlineSupport1.outline175(outline107, buffer.length, "; regionStart=", offset, "; regionLength=");
        outline107.append(count);
        throw new ArrayIndexOutOfBoundsException(outline107.toString());
    }
}
