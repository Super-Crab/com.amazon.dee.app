package com.facebook.imagepipeline.memory;

import androidx.annotation.VisibleForTesting;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Throwables;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.common.memory.MemoryTrimmable;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.OOMSoftReference;
import com.facebook.common.references.ResourceReleaser;
import java.util.concurrent.Semaphore;
import javax.annotation.concurrent.ThreadSafe;
@ThreadSafe
/* loaded from: classes2.dex */
public class SharedByteArray implements MemoryTrimmable {
    @VisibleForTesting
    final OOMSoftReference<byte[]> mByteArraySoftRef;
    @VisibleForTesting
    final int mMaxByteArraySize;
    @VisibleForTesting
    final int mMinByteArraySize;
    private final ResourceReleaser<byte[]> mResourceReleaser;
    @VisibleForTesting
    final Semaphore mSemaphore;

    public SharedByteArray(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams params) {
        Preconditions.checkNotNull(memoryTrimmableRegistry);
        boolean z = false;
        Preconditions.checkArgument(Boolean.valueOf(params.minBucketSize > 0));
        Preconditions.checkArgument(Boolean.valueOf(params.maxBucketSize >= params.minBucketSize ? true : z));
        this.mMaxByteArraySize = params.maxBucketSize;
        this.mMinByteArraySize = params.minBucketSize;
        this.mByteArraySoftRef = new OOMSoftReference<>();
        this.mSemaphore = new Semaphore(1);
        this.mResourceReleaser = new ResourceReleaser<byte[]>() { // from class: com.facebook.imagepipeline.memory.SharedByteArray.1
            @Override // com.facebook.common.references.ResourceReleaser
            public void release(byte[] unused) {
                SharedByteArray.this.mSemaphore.release();
            }
        };
        memoryTrimmableRegistry.registerMemoryTrimmable(this);
    }

    private synchronized byte[] allocateByteArray(int size) {
        byte[] bArr;
        this.mByteArraySoftRef.clear();
        bArr = new byte[size];
        this.mByteArraySoftRef.set(bArr);
        return bArr;
    }

    private byte[] getByteArray(int requestedSize) {
        int bucketedSize = getBucketedSize(requestedSize);
        byte[] bArr = this.mByteArraySoftRef.get();
        return (bArr == null || bArr.length < bucketedSize) ? allocateByteArray(bucketedSize) : bArr;
    }

    public CloseableReference<byte[]> get(int size) {
        boolean z = true;
        Preconditions.checkArgument(size > 0, "Size must be greater than zero");
        if (size > this.mMaxByteArraySize) {
            z = false;
        }
        Preconditions.checkArgument(z, "Requested size is too big");
        this.mSemaphore.acquireUninterruptibly();
        try {
            return CloseableReference.of(getByteArray(size), this.mResourceReleaser);
        } catch (Throwable th) {
            this.mSemaphore.release();
            throw Throwables.propagate(th);
        }
    }

    @VisibleForTesting
    int getBucketedSize(int size) {
        return Integer.highestOneBit(Math.max(size, this.mMinByteArraySize) - 1) * 2;
    }

    @Override // com.facebook.common.memory.MemoryTrimmable
    public void trim(MemoryTrimType trimType) {
        if (!this.mSemaphore.tryAcquire()) {
            return;
        }
        try {
            this.mByteArraySoftRef.clear();
        } finally {
            this.mSemaphore.release();
        }
    }
}
