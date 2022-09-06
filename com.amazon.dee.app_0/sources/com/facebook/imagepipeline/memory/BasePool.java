package com.facebook.imagepipeline.memory;

import android.annotation.SuppressLint;
import android.util.SparseArray;
import android.util.SparseIntArray;
import androidx.annotation.VisibleForTesting;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Sets;
import com.facebook.common.internal.Throwables;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.memory.Pool;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.NotThreadSafe;
/* loaded from: classes2.dex */
public abstract class BasePool<V> implements Pool<V> {
    private final Class<?> TAG;
    private boolean mAllowNewBuckets;
    @VisibleForTesting
    final SparseArray<Bucket<V>> mBuckets;
    @GuardedBy("this")
    @VisibleForTesting
    final Counter mFree;
    private boolean mIgnoreHardCap;
    @VisibleForTesting
    final Set<V> mInUseValues;
    final MemoryTrimmableRegistry mMemoryTrimmableRegistry;
    final PoolParams mPoolParams;
    private final PoolStatsTracker mPoolStatsTracker;
    @GuardedBy("this")
    @VisibleForTesting
    final Counter mUsed;

    /* JADX INFO: Access modifiers changed from: package-private */
    @NotThreadSafe
    @VisibleForTesting
    /* loaded from: classes2.dex */
    public static class Counter {
        private static final String TAG = "com.facebook.imagepipeline.memory.BasePool.Counter";
        int mCount;
        int mNumBytes;

        Counter() {
        }

        public void decrement(int numBytes) {
            int i;
            int i2 = this.mNumBytes;
            if (i2 >= numBytes && (i = this.mCount) > 0) {
                this.mCount = i - 1;
                this.mNumBytes = i2 - numBytes;
                return;
            }
            FLog.wtf(TAG, "Unexpected decrement of %d. Current numBytes = %d, count = %d", Integer.valueOf(numBytes), Integer.valueOf(this.mNumBytes), Integer.valueOf(this.mCount));
        }

        public void increment(int numBytes) {
            this.mCount++;
            this.mNumBytes += numBytes;
        }

        public void reset() {
            this.mCount = 0;
            this.mNumBytes = 0;
        }
    }

    /* loaded from: classes2.dex */
    public static class InvalidSizeException extends RuntimeException {
        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public InvalidSizeException(java.lang.Object r2) {
            /*
                r1 = this;
                java.lang.String r0 = "Invalid size: "
                java.lang.StringBuilder r0 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r0)
                java.lang.String r2 = r2.toString()
                r0.append(r2)
                java.lang.String r2 = r0.toString()
                r1.<init>(r2)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.memory.BasePool.InvalidSizeException.<init>(java.lang.Object):void");
        }
    }

    /* loaded from: classes2.dex */
    public static class InvalidValueException extends RuntimeException {
        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public InvalidValueException(java.lang.Object r2) {
            /*
                r1 = this;
                java.lang.String r0 = "Invalid value: "
                java.lang.StringBuilder r0 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r0)
                java.lang.String r2 = r2.toString()
                r0.append(r2)
                java.lang.String r2 = r0.toString()
                r1.<init>(r2)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.memory.BasePool.InvalidValueException.<init>(java.lang.Object):void");
        }
    }

    /* loaded from: classes2.dex */
    public static class PoolSizeViolationException extends RuntimeException {
        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public PoolSizeViolationException(int r4, int r5, int r6, int r7) {
            /*
                r3 = this;
                java.lang.String r0 = "Pool hard cap violation? Hard cap = "
                java.lang.String r1 = " Used size = "
                java.lang.String r2 = " Free size = "
                java.lang.StringBuilder r4 = com.android.tools.r8.GeneratedOutlineSupport1.outline110(r0, r4, r1, r5, r2)
                r4.append(r6)
                java.lang.String r5 = " Request size = "
                r4.append(r5)
                r4.append(r7)
                java.lang.String r4 = r4.toString()
                r3.<init>(r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.memory.BasePool.PoolSizeViolationException.<init>(int, int, int, int):void");
        }
    }

    /* loaded from: classes2.dex */
    public static class SizeTooLargeException extends InvalidSizeException {
        public SizeTooLargeException(Object size) {
            super(size);
        }
    }

    public BasePool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams, PoolStatsTracker poolStatsTracker) {
        this.TAG = getClass();
        this.mMemoryTrimmableRegistry = (MemoryTrimmableRegistry) Preconditions.checkNotNull(memoryTrimmableRegistry);
        this.mPoolParams = (PoolParams) Preconditions.checkNotNull(poolParams);
        this.mPoolStatsTracker = (PoolStatsTracker) Preconditions.checkNotNull(poolStatsTracker);
        this.mBuckets = new SparseArray<>();
        if (this.mPoolParams.fixBucketsReinitialization) {
            initBuckets();
        } else {
            legacyInitBuckets(new SparseIntArray(0));
        }
        this.mInUseValues = Sets.newIdentityHashSet();
        this.mFree = new Counter();
        this.mUsed = new Counter();
    }

    private synchronized void ensurePoolSizeInvariant() {
        boolean z;
        if (isMaxSizeSoftCapExceeded() && this.mFree.mNumBytes != 0) {
            z = false;
            Preconditions.checkState(z);
        }
        z = true;
        Preconditions.checkState(z);
    }

    private void fillBuckets(SparseIntArray bucketSizes) {
        this.mBuckets.clear();
        for (int i = 0; i < bucketSizes.size(); i++) {
            int keyAt = bucketSizes.keyAt(i);
            this.mBuckets.put(keyAt, new Bucket<>(getSizeInBytes(keyAt), bucketSizes.valueAt(i), 0, this.mPoolParams.fixBucketsReinitialization));
        }
    }

    @Nullable
    private synchronized Bucket<V> getBucketIfPresent(int bucketedSize) {
        return this.mBuckets.get(bucketedSize);
    }

    private synchronized void initBuckets() {
        SparseIntArray sparseIntArray = this.mPoolParams.bucketSizes;
        if (sparseIntArray != null) {
            fillBuckets(sparseIntArray);
            this.mAllowNewBuckets = false;
        } else {
            this.mAllowNewBuckets = true;
        }
    }

    private synchronized void legacyInitBuckets(SparseIntArray inUseCounts) {
        Preconditions.checkNotNull(inUseCounts);
        this.mBuckets.clear();
        SparseIntArray sparseIntArray = this.mPoolParams.bucketSizes;
        if (sparseIntArray != null) {
            for (int i = 0; i < sparseIntArray.size(); i++) {
                int keyAt = sparseIntArray.keyAt(i);
                this.mBuckets.put(keyAt, new Bucket<>(getSizeInBytes(keyAt), sparseIntArray.valueAt(i), inUseCounts.get(keyAt, 0), this.mPoolParams.fixBucketsReinitialization));
            }
            this.mAllowNewBuckets = false;
        } else {
            this.mAllowNewBuckets = true;
        }
    }

    @SuppressLint({"InvalidAccessToGuardedField"})
    private void logStats() {
        if (FLog.isLoggable(2)) {
            FLog.v(this.TAG, "Used = (%d, %d); Free = (%d, %d)", Integer.valueOf(this.mUsed.mCount), Integer.valueOf(this.mUsed.mNumBytes), Integer.valueOf(this.mFree.mCount), Integer.valueOf(this.mFree.mNumBytes));
        }
    }

    private List<Bucket<V>> refillBuckets() {
        ArrayList arrayList = new ArrayList(this.mBuckets.size());
        int size = this.mBuckets.size();
        for (int i = 0; i < size; i++) {
            Bucket bucket = (Bucket) Preconditions.checkNotNull(this.mBuckets.valueAt(i));
            int i2 = bucket.mItemSize;
            int i3 = bucket.mMaxLength;
            int inUseCount = bucket.getInUseCount();
            if (bucket.getFreeListSize() > 0) {
                arrayList.add(bucket);
            }
            this.mBuckets.setValueAt(i, new Bucket<>(getSizeInBytes(i2), i3, inUseCount, this.mPoolParams.fixBucketsReinitialization));
        }
        return arrayList;
    }

    /* renamed from: alloc */
    protected abstract V mo6914alloc(int bucketedSize);

    @VisibleForTesting
    synchronized boolean canAllocate(int sizeInBytes) {
        if (this.mIgnoreHardCap) {
            return true;
        }
        int i = this.mPoolParams.maxSizeHardCap;
        if (sizeInBytes > i - this.mUsed.mNumBytes) {
            this.mPoolStatsTracker.onHardCapReached();
            return false;
        }
        int i2 = this.mPoolParams.maxSizeSoftCap;
        if (sizeInBytes > i2 - (this.mUsed.mNumBytes + this.mFree.mNumBytes)) {
            trimToSize(i2 - sizeInBytes);
        }
        if (sizeInBytes <= i - (this.mUsed.mNumBytes + this.mFree.mNumBytes)) {
            return true;
        }
        this.mPoolStatsTracker.onHardCapReached();
        return false;
    }

    @VisibleForTesting
    protected abstract void free(V value);

    @Override // com.facebook.common.memory.Pool
    /* renamed from: get */
    public V mo6905get(int size) {
        V mo6901getValue;
        ensurePoolSizeInvariant();
        int bucketedSize = getBucketedSize(size);
        synchronized (this) {
            Bucket<V> bucket = getBucket(bucketedSize);
            if (bucket != null && (mo6901getValue = mo6901getValue(bucket)) != null) {
                Preconditions.checkState(this.mInUseValues.add(mo6901getValue));
                int bucketedSizeForValue = getBucketedSizeForValue(mo6901getValue);
                int sizeInBytes = getSizeInBytes(bucketedSizeForValue);
                this.mUsed.increment(sizeInBytes);
                this.mFree.decrement(sizeInBytes);
                this.mPoolStatsTracker.onValueReuse(sizeInBytes);
                logStats();
                if (FLog.isLoggable(2)) {
                    FLog.v(this.TAG, "get (reuse) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(mo6901getValue)), Integer.valueOf(bucketedSizeForValue));
                }
                return mo6901getValue;
            }
            int sizeInBytes2 = getSizeInBytes(bucketedSize);
            if (canAllocate(sizeInBytes2)) {
                this.mUsed.increment(sizeInBytes2);
                if (bucket != null) {
                    bucket.incrementInUseCount();
                }
                V v = null;
                try {
                    v = mo6914alloc(bucketedSize);
                } catch (Throwable th) {
                    synchronized (this) {
                        this.mUsed.decrement(sizeInBytes2);
                        Bucket<V> bucket2 = getBucket(bucketedSize);
                        if (bucket2 != null) {
                            bucket2.decrementInUseCount();
                        }
                        Throwables.propagateIfPossible(th);
                    }
                }
                synchronized (this) {
                    Preconditions.checkState(this.mInUseValues.add(v));
                    trimToSoftCap();
                    this.mPoolStatsTracker.onAlloc(sizeInBytes2);
                    logStats();
                    if (FLog.isLoggable(2)) {
                        FLog.v(this.TAG, "get (alloc) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(v)), Integer.valueOf(bucketedSize));
                    }
                }
                return v;
            }
            throw new PoolSizeViolationException(this.mPoolParams.maxSizeHardCap, this.mUsed.mNumBytes, this.mFree.mNumBytes, sizeInBytes2);
        }
    }

    @Nullable
    @VisibleForTesting
    synchronized Bucket<V> getBucket(int bucketedSize) {
        Bucket<V> bucket = this.mBuckets.get(bucketedSize);
        if (bucket == null && this.mAllowNewBuckets) {
            if (FLog.isLoggable(2)) {
                FLog.v(this.TAG, "creating new bucket %s", Integer.valueOf(bucketedSize));
            }
            Bucket<V> newBucket = newBucket(bucketedSize);
            this.mBuckets.put(bucketedSize, newBucket);
            return newBucket;
        }
        return bucket;
    }

    protected abstract int getBucketedSize(int requestSize);

    protected abstract int getBucketedSizeForValue(V value);

    protected abstract int getSizeInBytes(int bucketedSize);

    public synchronized Map<String, Integer> getStats() {
        HashMap hashMap;
        hashMap = new HashMap();
        for (int i = 0; i < this.mBuckets.size(); i++) {
            int keyAt = this.mBuckets.keyAt(i);
            hashMap.put(PoolStatsTracker.BUCKETS_USED_PREFIX + getSizeInBytes(keyAt), Integer.valueOf(((Bucket) Preconditions.checkNotNull(this.mBuckets.valueAt(i))).getInUseCount()));
        }
        hashMap.put(PoolStatsTracker.SOFT_CAP, Integer.valueOf(this.mPoolParams.maxSizeSoftCap));
        hashMap.put(PoolStatsTracker.HARD_CAP, Integer.valueOf(this.mPoolParams.maxSizeHardCap));
        hashMap.put(PoolStatsTracker.USED_COUNT, Integer.valueOf(this.mUsed.mCount));
        hashMap.put(PoolStatsTracker.USED_BYTES, Integer.valueOf(this.mUsed.mNumBytes));
        hashMap.put(PoolStatsTracker.FREE_COUNT, Integer.valueOf(this.mFree.mCount));
        hashMap.put(PoolStatsTracker.FREE_BYTES, Integer.valueOf(this.mFree.mNumBytes));
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    /* renamed from: getValue */
    public synchronized V mo6901getValue(Bucket<V> bucket) {
        return bucket.get();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void initialize() {
        this.mMemoryTrimmableRegistry.registerMemoryTrimmable(this);
        this.mPoolStatsTracker.setBasePool(this);
    }

    @VisibleForTesting
    synchronized boolean isMaxSizeSoftCapExceeded() {
        boolean z;
        z = this.mUsed.mNumBytes + this.mFree.mNumBytes > this.mPoolParams.maxSizeSoftCap;
        if (z) {
            this.mPoolStatsTracker.onSoftCapReached();
        }
        return z;
    }

    protected boolean isReusable(V value) {
        Preconditions.checkNotNull(value);
        return true;
    }

    Bucket<V> newBucket(int bucketedSize) {
        return new Bucket<>(getSizeInBytes(bucketedSize), Integer.MAX_VALUE, 0, this.mPoolParams.fixBucketsReinitialization);
    }

    protected void onParamsChanged() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0082, code lost:
        r2.decrementInUseCount();
     */
    @Override // com.facebook.common.memory.Pool, com.facebook.common.references.ResourceReleaser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void release(V r8) {
        /*
            r7 = this;
            com.facebook.common.internal.Preconditions.checkNotNull(r8)
            int r0 = r7.getBucketedSizeForValue(r8)
            int r1 = r7.getSizeInBytes(r0)
            monitor-enter(r7)
            com.facebook.imagepipeline.memory.Bucket r2 = r7.getBucketIfPresent(r0)     // Catch: java.lang.Throwable -> Lb1
            java.util.Set<V> r3 = r7.mInUseValues     // Catch: java.lang.Throwable -> Lb1
            boolean r3 = r3.remove(r8)     // Catch: java.lang.Throwable -> Lb1
            r4 = 2
            if (r3 != 0) goto L3e
            java.lang.Class<?> r2 = r7.TAG     // Catch: java.lang.Throwable -> Lb1
            java.lang.String r3 = "release (free, value unrecognized) (object, size) = (%x, %s)"
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> Lb1
            r5 = 0
            int r6 = java.lang.System.identityHashCode(r8)     // Catch: java.lang.Throwable -> Lb1
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch: java.lang.Throwable -> Lb1
            r4[r5] = r6     // Catch: java.lang.Throwable -> Lb1
            r5 = 1
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch: java.lang.Throwable -> Lb1
            r4[r5] = r0     // Catch: java.lang.Throwable -> Lb1
            com.facebook.common.logging.FLog.e(r2, r3, r4)     // Catch: java.lang.Throwable -> Lb1
            r7.free(r8)     // Catch: java.lang.Throwable -> Lb1
            com.facebook.imagepipeline.memory.PoolStatsTracker r8 = r7.mPoolStatsTracker     // Catch: java.lang.Throwable -> Lb1
            r8.onFree(r1)     // Catch: java.lang.Throwable -> Lb1
            goto Lac
        L3e:
            if (r2 == 0) goto L80
            boolean r3 = r2.isMaxLengthExceeded()     // Catch: java.lang.Throwable -> Lb1
            if (r3 != 0) goto L80
            boolean r3 = r7.isMaxSizeSoftCapExceeded()     // Catch: java.lang.Throwable -> Lb1
            if (r3 != 0) goto L80
            boolean r3 = r7.isReusable(r8)     // Catch: java.lang.Throwable -> Lb1
            if (r3 != 0) goto L53
            goto L80
        L53:
            r2.release(r8)     // Catch: java.lang.Throwable -> Lb1
            com.facebook.imagepipeline.memory.BasePool$Counter r2 = r7.mFree     // Catch: java.lang.Throwable -> Lb1
            r2.increment(r1)     // Catch: java.lang.Throwable -> Lb1
            com.facebook.imagepipeline.memory.BasePool$Counter r2 = r7.mUsed     // Catch: java.lang.Throwable -> Lb1
            r2.decrement(r1)     // Catch: java.lang.Throwable -> Lb1
            com.facebook.imagepipeline.memory.PoolStatsTracker r2 = r7.mPoolStatsTracker     // Catch: java.lang.Throwable -> Lb1
            r2.onValueRelease(r1)     // Catch: java.lang.Throwable -> Lb1
            boolean r1 = com.facebook.common.logging.FLog.isLoggable(r4)     // Catch: java.lang.Throwable -> Lb1
            if (r1 == 0) goto Lac
            java.lang.Class<?> r1 = r7.TAG     // Catch: java.lang.Throwable -> Lb1
            java.lang.String r2 = "release (reuse) (object, size) = (%x, %s)"
            int r8 = java.lang.System.identityHashCode(r8)     // Catch: java.lang.Throwable -> Lb1
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch: java.lang.Throwable -> Lb1
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch: java.lang.Throwable -> Lb1
            com.facebook.common.logging.FLog.v(r1, r2, r8, r0)     // Catch: java.lang.Throwable -> Lb1
            goto Lac
        L80:
            if (r2 == 0) goto L85
            r2.decrementInUseCount()     // Catch: java.lang.Throwable -> Lb1
        L85:
            boolean r2 = com.facebook.common.logging.FLog.isLoggable(r4)     // Catch: java.lang.Throwable -> Lb1
            if (r2 == 0) goto L9f
            java.lang.Class<?> r2 = r7.TAG     // Catch: java.lang.Throwable -> Lb1
            java.lang.String r3 = "release (free) (object, size) = (%x, %s)"
            int r4 = java.lang.System.identityHashCode(r8)     // Catch: java.lang.Throwable -> Lb1
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch: java.lang.Throwable -> Lb1
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch: java.lang.Throwable -> Lb1
            com.facebook.common.logging.FLog.v(r2, r3, r4, r0)     // Catch: java.lang.Throwable -> Lb1
        L9f:
            r7.free(r8)     // Catch: java.lang.Throwable -> Lb1
            com.facebook.imagepipeline.memory.BasePool$Counter r8 = r7.mUsed     // Catch: java.lang.Throwable -> Lb1
            r8.decrement(r1)     // Catch: java.lang.Throwable -> Lb1
            com.facebook.imagepipeline.memory.PoolStatsTracker r8 = r7.mPoolStatsTracker     // Catch: java.lang.Throwable -> Lb1
            r8.onFree(r1)     // Catch: java.lang.Throwable -> Lb1
        Lac:
            r7.logStats()     // Catch: java.lang.Throwable -> Lb1
            monitor-exit(r7)     // Catch: java.lang.Throwable -> Lb1
            return
        Lb1:
            r8 = move-exception
            monitor-exit(r7)     // Catch: java.lang.Throwable -> Lb1
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.memory.BasePool.release(java.lang.Object):void");
    }

    @Override // com.facebook.common.memory.MemoryTrimmable
    public void trim(MemoryTrimType memoryTrimType) {
        trimToNothing();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @VisibleForTesting
    void trimToNothing() {
        int i;
        List arrayList;
        synchronized (this) {
            if (this.mPoolParams.fixBucketsReinitialization) {
                arrayList = refillBuckets();
            } else {
                arrayList = new ArrayList(this.mBuckets.size());
                SparseIntArray sparseIntArray = new SparseIntArray();
                for (int i2 = 0; i2 < this.mBuckets.size(); i2++) {
                    Bucket bucket = (Bucket) Preconditions.checkNotNull(this.mBuckets.valueAt(i2));
                    if (bucket.getFreeListSize() > 0) {
                        arrayList.add(bucket);
                    }
                    sparseIntArray.put(this.mBuckets.keyAt(i2), bucket.getInUseCount());
                }
                legacyInitBuckets(sparseIntArray);
            }
            this.mFree.reset();
            logStats();
        }
        onParamsChanged();
        for (i = 0; i < arrayList.size(); i++) {
            Bucket bucket2 = (Bucket) arrayList.get(i);
            while (true) {
                Object pop = bucket2.pop();
                if (pop == null) {
                    break;
                }
                free(pop);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @VisibleForTesting
    synchronized void trimToSize(int targetSize) {
        int min = Math.min((this.mUsed.mNumBytes + this.mFree.mNumBytes) - targetSize, this.mFree.mNumBytes);
        if (min <= 0) {
            return;
        }
        if (FLog.isLoggable(2)) {
            FLog.v(this.TAG, "trimToSize: TargetSize = %d; Initial Size = %d; Bytes to free = %d", Integer.valueOf(targetSize), Integer.valueOf(this.mUsed.mNumBytes + this.mFree.mNumBytes), Integer.valueOf(min));
        }
        logStats();
        for (int i = 0; i < this.mBuckets.size() && min > 0; i++) {
            Bucket bucket = (Bucket) Preconditions.checkNotNull(this.mBuckets.valueAt(i));
            while (min > 0) {
                Object pop = bucket.pop();
                if (pop == null) {
                    break;
                }
                free(pop);
                int i2 = bucket.mItemSize;
                min -= i2;
                this.mFree.decrement(i2);
            }
        }
        logStats();
        if (FLog.isLoggable(2)) {
            FLog.v(this.TAG, "trimToSize: TargetSize = %d; Final Size = %d", Integer.valueOf(targetSize), Integer.valueOf(this.mUsed.mNumBytes + this.mFree.mNumBytes));
        }
    }

    @VisibleForTesting
    synchronized void trimToSoftCap() {
        if (isMaxSizeSoftCapExceeded()) {
            trimToSize(this.mPoolParams.maxSizeSoftCap);
        }
    }

    public BasePool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams, PoolStatsTracker poolStatsTracker, boolean ignoreHardCap) {
        this(memoryTrimmableRegistry, poolParams, poolStatsTracker);
        this.mIgnoreHardCap = ignoreHardCap;
    }
}
