package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.imagepipeline.memory.BasePool;
import javax.annotation.concurrent.ThreadSafe;
@ThreadSafe
/* loaded from: classes2.dex */
public class GenericByteArrayPool extends BasePool<byte[]> implements ByteArrayPool {
    private final int[] mBucketSizes;

    public GenericByteArrayPool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams, PoolStatsTracker poolStatsTracker) {
        super(memoryTrimmableRegistry, poolParams, poolStatsTracker);
        SparseIntArray sparseIntArray = (SparseIntArray) Preconditions.checkNotNull(poolParams.bucketSizes);
        this.mBucketSizes = new int[sparseIntArray.size()];
        for (int i = 0; i < sparseIntArray.size(); i++) {
            this.mBucketSizes[i] = sparseIntArray.keyAt(i);
        }
        initialize();
    }

    @Override // com.facebook.imagepipeline.memory.BasePool
    protected int getBucketedSize(int requestSize) {
        int[] iArr;
        if (requestSize > 0) {
            for (int i : this.mBucketSizes) {
                if (i >= requestSize) {
                    return i;
                }
            }
            return requestSize;
        }
        throw new BasePool.InvalidSizeException(Integer.valueOf(requestSize));
    }

    public int getMinBufferSize() {
        return this.mBucketSizes[0];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.memory.BasePool
    public int getSizeInBytes(int bucketedSize) {
        return bucketedSize;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.memory.BasePool
    /* renamed from: alloc  reason: collision with other method in class */
    public byte[] mo6914alloc(int bucketedSize) {
        return new byte[bucketedSize];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.memory.BasePool
    public void free(byte[] value) {
        Preconditions.checkNotNull(value);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.memory.BasePool
    public int getBucketedSizeForValue(byte[] value) {
        Preconditions.checkNotNull(value);
        return value.length;
    }
}
