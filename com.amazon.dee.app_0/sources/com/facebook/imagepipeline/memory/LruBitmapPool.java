package com.facebook.imagepipeline.memory;

import android.graphics.Bitmap;
import androidx.annotation.VisibleForTesting;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class LruBitmapPool implements BitmapPool {
    private int mCurrentSize;
    private int mMaxBitmapSize;
    private final int mMaxPoolSize;
    private final PoolStatsTracker mPoolStatsTracker;
    protected final PoolBackend<Bitmap> mStrategy = new BitmapPoolBackend();

    public LruBitmapPool(int maxPoolSize, int maxBitmapSize, PoolStatsTracker poolStatsTracker, @Nullable MemoryTrimmableRegistry memoryTrimmableRegistry) {
        this.mMaxPoolSize = maxPoolSize;
        this.mMaxBitmapSize = maxBitmapSize;
        this.mPoolStatsTracker = poolStatsTracker;
        if (memoryTrimmableRegistry != null) {
            memoryTrimmableRegistry.registerMemoryTrimmable(this);
        }
    }

    @VisibleForTesting
    private Bitmap alloc(int size) {
        this.mPoolStatsTracker.onAlloc(size);
        return Bitmap.createBitmap(1, size, Bitmap.Config.ALPHA_8);
    }

    private synchronized void trimTo(int maxSize) {
        Bitmap pop;
        while (this.mCurrentSize > maxSize && (pop = this.mStrategy.pop()) != null) {
            int size = this.mStrategy.getSize(pop);
            this.mCurrentSize -= size;
            this.mPoolStatsTracker.onFree(size);
        }
    }

    @Override // com.facebook.common.memory.MemoryTrimmable
    public void trim(MemoryTrimType trimType) {
        trimTo((int) ((1.0d - trimType.getSuggestedTrimRatio()) * this.mMaxPoolSize));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.facebook.common.memory.Pool
    /* renamed from: get */
    public synchronized Bitmap mo6905get(final int size) {
        if (this.mCurrentSize > this.mMaxPoolSize) {
            trimTo(this.mMaxPoolSize);
        }
        Bitmap mo6899get = this.mStrategy.mo6899get(size);
        if (mo6899get != null) {
            int size2 = this.mStrategy.getSize(mo6899get);
            this.mCurrentSize -= size2;
            this.mPoolStatsTracker.onValueReuse(size2);
            return mo6899get;
        }
        return alloc(size);
    }

    @Override // com.facebook.common.memory.Pool, com.facebook.common.references.ResourceReleaser
    public void release(final Bitmap value) {
        int size = this.mStrategy.getSize(value);
        if (size <= this.mMaxBitmapSize) {
            this.mPoolStatsTracker.onValueRelease(size);
            this.mStrategy.put(value);
            synchronized (this) {
                this.mCurrentSize += size;
            }
        }
    }
}
