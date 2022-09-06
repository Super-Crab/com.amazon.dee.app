package com.facebook.imagepipeline.memory;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.nativecode.ImagePipelineNativeLoader;
import com.facebook.soloader.nativeloader.NativeLoader;
import java.io.Closeable;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;
@DoNotStrip
/* loaded from: classes2.dex */
public class NativeMemoryChunk implements MemoryChunk, Closeable {
    private static final String TAG = "NativeMemoryChunk";
    private boolean mIsClosed;
    private final long mNativePtr;
    private final int mSize;

    static {
        NativeLoader.loadLibrary(ImagePipelineNativeLoader.DSO_NAME);
    }

    public NativeMemoryChunk(final int size) {
        Preconditions.checkArgument(Boolean.valueOf(size > 0));
        this.mSize = size;
        this.mNativePtr = nativeAllocate(this.mSize);
        this.mIsClosed = false;
    }

    private void doCopy(final int offset, final MemoryChunk other, final int otherOffset, final int count) {
        if (other instanceof NativeMemoryChunk) {
            Preconditions.checkState(!isClosed());
            Preconditions.checkState(!other.isClosed());
            MemoryChunkUtil.checkBounds(offset, other.getSize(), otherOffset, count, this.mSize);
            nativeMemcpy(other.getNativePtr() + otherOffset, this.mNativePtr + offset, count);
            return;
        }
        throw new IllegalArgumentException("Cannot copy two incompatible MemoryChunks");
    }

    @DoNotStrip
    private static native long nativeAllocate(int size);

    @DoNotStrip
    private static native void nativeCopyFromByteArray(long address, byte[] array, int offset, int count);

    @DoNotStrip
    private static native void nativeCopyToByteArray(long address, byte[] array, int offset, int count);

    @DoNotStrip
    private static native void nativeFree(long address);

    @DoNotStrip
    private static native void nativeMemcpy(long toPtr, long fromPtr, int count);

    @DoNotStrip
    private static native byte nativeReadByte(long fromPtr);

    @Override // com.facebook.imagepipeline.memory.MemoryChunk, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        if (!this.mIsClosed) {
            this.mIsClosed = true;
            nativeFree(this.mNativePtr);
        }
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public void copy(final int offset, final MemoryChunk other, final int otherOffset, final int count) {
        Preconditions.checkNotNull(other);
        if (other.getUniqueId() == getUniqueId()) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Copying from NativeMemoryChunk ");
            outline107.append(Integer.toHexString(System.identityHashCode(this)));
            outline107.append(" to NativeMemoryChunk ");
            outline107.append(Integer.toHexString(System.identityHashCode(other)));
            outline107.append(" which share the same address ");
            outline107.append(Long.toHexString(this.mNativePtr));
            Log.w(TAG, outline107.toString());
            Preconditions.checkArgument(false);
        }
        if (other.getUniqueId() < getUniqueId()) {
            synchronized (other) {
                synchronized (this) {
                    doCopy(offset, other, otherOffset, count);
                }
            }
            return;
        }
        synchronized (this) {
            synchronized (other) {
                doCopy(offset, other, otherOffset, count);
            }
        }
    }

    protected void finalize() throws Throwable {
        if (isClosed()) {
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("finalize: Chunk ");
        outline107.append(Integer.toHexString(System.identityHashCode(this)));
        outline107.append(" still active. ");
        Log.w(TAG, outline107.toString());
        try {
            close();
        } finally {
            super.finalize();
        }
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    @Nullable
    public ByteBuffer getByteBuffer() {
        return null;
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public long getNativePtr() {
        return this.mNativePtr;
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public int getSize() {
        return this.mSize;
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public long getUniqueId() {
        return this.mNativePtr;
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public synchronized boolean isClosed() {
        return this.mIsClosed;
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public synchronized int read(final int memoryOffset, final byte[] byteArray, final int byteArrayOffset, final int count) {
        int adjustByteCount;
        Preconditions.checkNotNull(byteArray);
        Preconditions.checkState(!isClosed());
        adjustByteCount = MemoryChunkUtil.adjustByteCount(memoryOffset, count, this.mSize);
        MemoryChunkUtil.checkBounds(memoryOffset, byteArray.length, byteArrayOffset, adjustByteCount, this.mSize);
        nativeCopyToByteArray(this.mNativePtr + memoryOffset, byteArray, byteArrayOffset, adjustByteCount);
        return adjustByteCount;
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public synchronized int write(final int memoryOffset, final byte[] byteArray, final int byteArrayOffset, final int count) {
        int adjustByteCount;
        Preconditions.checkNotNull(byteArray);
        Preconditions.checkState(!isClosed());
        adjustByteCount = MemoryChunkUtil.adjustByteCount(memoryOffset, count, this.mSize);
        MemoryChunkUtil.checkBounds(memoryOffset, byteArray.length, byteArrayOffset, adjustByteCount, this.mSize);
        nativeCopyFromByteArray(this.mNativePtr + memoryOffset, byteArray, byteArrayOffset, adjustByteCount);
        return adjustByteCount;
    }

    @VisibleForTesting
    public NativeMemoryChunk() {
        this.mSize = 0;
        this.mNativePtr = 0L;
        this.mIsClosed = true;
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public synchronized byte read(final int offset) {
        boolean z = true;
        Preconditions.checkState(!isClosed());
        Preconditions.checkArgument(Boolean.valueOf(offset >= 0));
        if (offset >= this.mSize) {
            z = false;
        }
        Preconditions.checkArgument(Boolean.valueOf(z));
        return nativeReadByte(this.mNativePtr + offset);
    }
}
