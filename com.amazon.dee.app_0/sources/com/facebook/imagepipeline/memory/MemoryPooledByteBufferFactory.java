package com.facebook.imagepipeline.memory;

import androidx.annotation.VisibleForTesting;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Throwables;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteStreams;
import com.facebook.common.references.CloseableReference;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.concurrent.ThreadSafe;
@ThreadSafe
/* loaded from: classes2.dex */
public class MemoryPooledByteBufferFactory implements PooledByteBufferFactory {
    private final MemoryChunkPool mPool;
    private final PooledByteStreams mPooledByteStreams;

    public MemoryPooledByteBufferFactory(MemoryChunkPool pool, PooledByteStreams pooledByteStreams) {
        this.mPool = pool;
        this.mPooledByteStreams = pooledByteStreams;
    }

    @VisibleForTesting
    MemoryPooledByteBuffer newByteBuf(InputStream inputStream, MemoryPooledByteBufferOutputStream outputStream) throws IOException {
        this.mPooledByteStreams.copy(inputStream, outputStream);
        return outputStream.mo6913toByteBuffer();
    }

    @Override // com.facebook.common.memory.PooledByteBufferFactory
    /* renamed from: newOutputStream  reason: collision with other method in class */
    public MemoryPooledByteBufferOutputStream mo6911newOutputStream() {
        return new MemoryPooledByteBufferOutputStream(this.mPool);
    }

    @Override // com.facebook.common.memory.PooledByteBufferFactory
    /* renamed from: newOutputStream  reason: collision with other method in class */
    public MemoryPooledByteBufferOutputStream mo6912newOutputStream(int initialCapacity) {
        return new MemoryPooledByteBufferOutputStream(this.mPool, initialCapacity);
    }

    @Override // com.facebook.common.memory.PooledByteBufferFactory
    /* renamed from: newByteBuffer  reason: collision with other method in class */
    public MemoryPooledByteBuffer mo6907newByteBuffer(int size) {
        Preconditions.checkArgument(Boolean.valueOf(size > 0));
        CloseableReference of = CloseableReference.of(this.mPool.mo6905get(size), this.mPool);
        try {
            return new MemoryPooledByteBuffer(of, size);
        } finally {
            of.close();
        }
    }

    @Override // com.facebook.common.memory.PooledByteBufferFactory
    /* renamed from: newByteBuffer  reason: collision with other method in class */
    public MemoryPooledByteBuffer mo6908newByteBuffer(InputStream inputStream) throws IOException {
        MemoryPooledByteBufferOutputStream memoryPooledByteBufferOutputStream = new MemoryPooledByteBufferOutputStream(this.mPool);
        try {
            return newByteBuf(inputStream, memoryPooledByteBufferOutputStream);
        } finally {
            memoryPooledByteBufferOutputStream.close();
        }
    }

    @Override // com.facebook.common.memory.PooledByteBufferFactory
    /* renamed from: newByteBuffer  reason: collision with other method in class */
    public MemoryPooledByteBuffer mo6910newByteBuffer(byte[] bytes) {
        MemoryPooledByteBufferOutputStream memoryPooledByteBufferOutputStream = new MemoryPooledByteBufferOutputStream(this.mPool, bytes.length);
        try {
            try {
                memoryPooledByteBufferOutputStream.write(bytes, 0, bytes.length);
                return memoryPooledByteBufferOutputStream.mo6913toByteBuffer();
            } catch (IOException e) {
                throw Throwables.propagate(e);
            }
        } finally {
            memoryPooledByteBufferOutputStream.close();
        }
    }

    @Override // com.facebook.common.memory.PooledByteBufferFactory
    /* renamed from: newByteBuffer  reason: collision with other method in class */
    public MemoryPooledByteBuffer mo6909newByteBuffer(InputStream inputStream, int initialCapacity) throws IOException {
        MemoryPooledByteBufferOutputStream memoryPooledByteBufferOutputStream = new MemoryPooledByteBufferOutputStream(this.mPool, initialCapacity);
        try {
            return newByteBuf(inputStream, memoryPooledByteBufferOutputStream);
        } finally {
            memoryPooledByteBufferOutputStream.close();
        }
    }
}
