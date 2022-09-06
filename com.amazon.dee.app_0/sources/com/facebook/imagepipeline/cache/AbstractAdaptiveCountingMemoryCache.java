package com.facebook.imagepipeline.cache;

import android.graphics.Bitmap;
import android.os.SystemClock;
import androidx.annotation.VisibleForTesting;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Predicate;
import com.facebook.common.internal.Supplier;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.ResourceReleaser;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import com.facebook.imagepipeline.cache.MemoryCache;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
@ThreadSafe
/* loaded from: classes2.dex */
public abstract class AbstractAdaptiveCountingMemoryCache<K, V> implements CountingMemoryCache<K, V> {
    static final int DEFAULT_ADAPTIVE_RATE_PROMIL = 10;
    static final int DEFAULT_LFU_FRACTION_PROMIL = 500;
    static final int MAX_FRACTION_PROMIL = 900;
    @VisibleForTesting
    static final int MIN_FRACTION_PROMIL = 100;
    private static final String TAG = "AbstractArcCountingMemoryCache";
    static final int TOTAL_PROMIL = 1000;
    @GuardedBy("this")
    @VisibleForTesting
    final int mAdaptiveRatePromil;
    private final MemoryCache.CacheTrimStrategy mCacheTrimStrategy;
    @GuardedBy("this")
    @VisibleForTesting
    final CountingLruMap<K, CountingMemoryCache.Entry<K, V>> mCachedEntries;
    private final int mFrequentlyUsedThreshold;
    @GuardedBy("this")
    @VisibleForTesting
    final int mGhostListMaxSize;
    @GuardedBy("this")
    @VisibleForTesting
    int mLFUFractionPromil;
    @GuardedBy("this")
    private long mLastCacheParamsCheck;
    @GuardedBy("this")
    @VisibleForTesting
    final CountingLruMap<K, CountingMemoryCache.Entry<K, V>> mLeastFrequentlyUsedExclusiveEntries;
    @GuardedBy("this")
    @VisibleForTesting
    final AbstractAdaptiveCountingMemoryCache<K, V>.IntMapArrayList<K> mLeastFrequentlyUsedKeysGhostList;
    @GuardedBy("this")
    protected MemoryCacheParams mMemoryCacheParams;
    private final Supplier<MemoryCacheParams> mMemoryCacheParamsSupplier;
    @GuardedBy("this")
    @VisibleForTesting
    final CountingLruMap<K, CountingMemoryCache.Entry<K, V>> mMostFrequentlyUsedExclusiveEntries;
    @GuardedBy("this")
    @VisibleForTesting
    final ArrayList<K> mMostFrequentlyUsedKeysGhostList;
    private final ValueDescriptor<V> mValueDescriptor;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public enum ArrayListType {
        LFU,
        MFU
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes2.dex */
    public class IntMapArrayList<E> {
        private final ArrayList<E> mFirstList;
        private final int mMaxCapacity;
        private final ArrayList<Integer> mSecondList;

        public IntMapArrayList(int maxCapacity) {
            this.mFirstList = new ArrayList<>(maxCapacity);
            this.mSecondList = new ArrayList<>(maxCapacity);
            this.mMaxCapacity = maxCapacity;
        }

        public void addPair(E element, Integer second) {
            if (this.mFirstList.size() == this.mMaxCapacity) {
                this.mFirstList.remove(0);
                this.mSecondList.remove(0);
            }
            this.mFirstList.add(element);
            this.mSecondList.add(second);
        }

        public boolean contains(E element) {
            return this.mFirstList.contains(element);
        }

        @Nullable
        public Integer getValue(E element) {
            int indexOf = this.mFirstList.indexOf(element);
            if (indexOf < 0) {
                return null;
            }
            return this.mSecondList.get(indexOf);
        }

        public void increaseValueIfExists(E element) {
            int indexOf = this.mFirstList.indexOf(element);
            if (indexOf < 0) {
                return;
            }
            Integer valueOf = Integer.valueOf(this.mSecondList.get(indexOf).intValue() + 1);
            int i = this.mMaxCapacity;
            if (indexOf == i - 1) {
                this.mSecondList.set(i - 1, valueOf);
                return;
            }
            this.mFirstList.remove(indexOf);
            this.mSecondList.remove(indexOf);
            this.mFirstList.add(element);
            this.mSecondList.add(valueOf);
        }

        public int size() {
            return this.mFirstList.size();
        }
    }

    public AbstractAdaptiveCountingMemoryCache(Supplier<MemoryCacheParams> memoryCacheParamsSupplier, MemoryCache.CacheTrimStrategy cacheTrimStrategy, ValueDescriptor<V> valueDescriptor, int adaptiveRatePromil, int frequentlyUsedThreshold, int ghostListMaxSize, int lfuFractionPromil) {
        FLog.d(TAG, "Create Adaptive Replacement Cache");
        this.mValueDescriptor = valueDescriptor;
        this.mLeastFrequentlyUsedExclusiveEntries = new CountingLruMap<>(wrapValueDescriptor(valueDescriptor));
        this.mMostFrequentlyUsedExclusiveEntries = new CountingLruMap<>(wrapValueDescriptor(valueDescriptor));
        this.mCachedEntries = new CountingLruMap<>(wrapValueDescriptor(valueDescriptor));
        this.mCacheTrimStrategy = cacheTrimStrategy;
        this.mMemoryCacheParamsSupplier = memoryCacheParamsSupplier;
        this.mMemoryCacheParams = (MemoryCacheParams) Preconditions.checkNotNull(this.mMemoryCacheParamsSupplier.mo6895get(), "mMemoryCacheParamsSupplier returned null");
        this.mLastCacheParamsCheck = SystemClock.uptimeMillis();
        this.mFrequentlyUsedThreshold = frequentlyUsedThreshold;
        this.mGhostListMaxSize = ghostListMaxSize;
        this.mLeastFrequentlyUsedKeysGhostList = new IntMapArrayList<>(this.mGhostListMaxSize);
        this.mMostFrequentlyUsedKeysGhostList = new ArrayList<>(this.mGhostListMaxSize);
        if (lfuFractionPromil >= 100 && lfuFractionPromil <= 900) {
            this.mLFUFractionPromil = lfuFractionPromil;
        } else {
            this.mLFUFractionPromil = 500;
            logIllegalLfuFraction();
        }
        if (adaptiveRatePromil > 0 && adaptiveRatePromil < 1000) {
            this.mAdaptiveRatePromil = adaptiveRatePromil;
            return;
        }
        this.mAdaptiveRatePromil = 10;
        logIllegalAdaptiveRate();
    }

    private synchronized void addElementToGhostList(K key, int accessCount, ArrayListType evictionType) {
        if (evictionType == ArrayListType.LFU) {
            this.mLeastFrequentlyUsedKeysGhostList.addPair(key, Integer.valueOf(accessCount));
        } else {
            if (this.mMostFrequentlyUsedKeysGhostList.size() == this.mGhostListMaxSize) {
                this.mMostFrequentlyUsedKeysGhostList.remove(0);
            }
            this.mMostFrequentlyUsedKeysGhostList.add(key);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0022, code lost:
        if (getInUseSizeInBytes() <= (r3.mMemoryCacheParams.maxCacheSize - r4)) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private synchronized boolean canCacheNewValue(V r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            com.facebook.imagepipeline.cache.ValueDescriptor<V> r0 = r3.mValueDescriptor     // Catch: java.lang.Throwable -> L28
            int r4 = r0.getSizeInBytes(r4)     // Catch: java.lang.Throwable -> L28
            com.facebook.imagepipeline.cache.MemoryCacheParams r0 = r3.mMemoryCacheParams     // Catch: java.lang.Throwable -> L28
            int r0 = r0.maxCacheEntrySize     // Catch: java.lang.Throwable -> L28
            r1 = 1
            if (r4 > r0) goto L25
            int r0 = r3.getInUseCount()     // Catch: java.lang.Throwable -> L28
            com.facebook.imagepipeline.cache.MemoryCacheParams r2 = r3.mMemoryCacheParams     // Catch: java.lang.Throwable -> L28
            int r2 = r2.maxCacheEntries     // Catch: java.lang.Throwable -> L28
            int r2 = r2 - r1
            if (r0 > r2) goto L25
            int r0 = r3.getInUseSizeInBytes()     // Catch: java.lang.Throwable -> L28
            com.facebook.imagepipeline.cache.MemoryCacheParams r2 = r3.mMemoryCacheParams     // Catch: java.lang.Throwable -> L28
            int r2 = r2.maxCacheSize     // Catch: java.lang.Throwable -> L28
            int r2 = r2 - r4
            if (r0 > r2) goto L25
            goto L26
        L25:
            r1 = 0
        L26:
            monitor-exit(r3)
            return r1
        L28:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.cache.AbstractAdaptiveCountingMemoryCache.canCacheNewValue(java.lang.Object):boolean");
    }

    private synchronized void decreaseClientCount(CountingMemoryCache.Entry<K, V> entry) {
        Preconditions.checkNotNull(entry);
        Preconditions.checkState(entry.clientCount > 0);
        entry.clientCount--;
    }

    private synchronized void increaseAccessCount(CountingMemoryCache.Entry<K, V> entry) {
        Preconditions.checkNotNull(entry);
        Preconditions.checkState(!entry.isOrphan);
        entry.accessCount++;
    }

    private synchronized void increaseCounters(CountingMemoryCache.Entry<K, V> entry) {
        Preconditions.checkNotNull(entry);
        Preconditions.checkState(!entry.isOrphan);
        entry.clientCount++;
        increaseAccessCount(entry);
    }

    private synchronized void makeOrphan(CountingMemoryCache.Entry<K, V> entry) {
        Preconditions.checkNotNull(entry);
        Preconditions.checkState(!entry.isOrphan);
        entry.isOrphan = true;
    }

    private synchronized void makeOrphans(@Nullable ArrayList<CountingMemoryCache.Entry<K, V>> oldEntries1, @Nullable ArrayList<CountingMemoryCache.Entry<K, V>> oldEntries2) {
        makeOrphans(oldEntries1);
        makeOrphans(oldEntries2);
    }

    private synchronized boolean maybeAddToExclusives(CountingMemoryCache.Entry<K, V> entry) {
        if (entry.isOrphan || entry.clientCount != 0) {
            return false;
        }
        if (entry.accessCount > this.mFrequentlyUsedThreshold) {
            this.mMostFrequentlyUsedExclusiveEntries.put(entry.key, entry);
        } else {
            this.mLeastFrequentlyUsedExclusiveEntries.put(entry.key, entry);
        }
        return true;
    }

    private void maybeClose(@Nullable ArrayList<CountingMemoryCache.Entry<K, V>> oldEntries1, @Nullable ArrayList<CountingMemoryCache.Entry<K, V>> oldEntries2) {
        maybeClose(oldEntries1);
        maybeClose(oldEntries2);
    }

    private void maybeNotifyExclusiveEntriesRemoval(@Nullable ArrayList<CountingMemoryCache.Entry<K, V>> entries1, @Nullable ArrayList<CountingMemoryCache.Entry<K, V>> entries2) {
        maybeNotifyExclusiveEntryRemoval(entries1);
        maybeNotifyExclusiveEntryRemoval(entries2);
    }

    private static <K, V> void maybeNotifyExclusiveEntryInsertion(@Nullable CountingMemoryCache.Entry<K, V> entry) {
        CountingMemoryCache.EntryStateObserver<K> entryStateObserver;
        if (entry == null || (entryStateObserver = entry.observer) == null) {
            return;
        }
        entryStateObserver.onExclusivityChanged(entry.key, true);
    }

    private void maybeNotifyExclusiveEntryRemoval(@Nullable CountingMemoryCache.Entry<K, V> entry1, @Nullable CountingMemoryCache.Entry<K, V> entry2) {
        maybeNotifyExclusiveEntryRemoval(entry1);
        maybeNotifyExclusiveEntryRemoval(entry2);
    }

    private synchronized void maybeUpdateCacheFraction(K key) {
        if (this.mLeastFrequentlyUsedKeysGhostList.contains(key)) {
            if (this.mLFUFractionPromil + this.mAdaptiveRatePromil <= 900) {
                this.mLFUFractionPromil += this.mAdaptiveRatePromil;
            }
            this.mLeastFrequentlyUsedKeysGhostList.increaseValueIfExists(key);
        } else if (this.mLFUFractionPromil - this.mAdaptiveRatePromil >= 100 && this.mMostFrequentlyUsedKeysGhostList.contains(key)) {
            this.mLFUFractionPromil -= this.mAdaptiveRatePromil;
        }
    }

    private synchronized void maybeUpdateCacheParams() {
        if (this.mLastCacheParamsCheck + this.mMemoryCacheParams.paramsCheckIntervalMs > SystemClock.uptimeMillis()) {
            return;
        }
        this.mLastCacheParamsCheck = SystemClock.uptimeMillis();
        this.mMemoryCacheParams = (MemoryCacheParams) Preconditions.checkNotNull(this.mMemoryCacheParamsSupplier.mo6895get(), "mMemoryCacheParamsSupplier returned null");
    }

    private synchronized CloseableReference<V> newClientReference(final CountingMemoryCache.Entry<K, V> entry) {
        increaseCounters(entry);
        return CloseableReference.of(entry.valueRef.get(), new ResourceReleaser<V>() { // from class: com.facebook.imagepipeline.cache.AbstractAdaptiveCountingMemoryCache.2
            @Override // com.facebook.common.references.ResourceReleaser
            public void release(V unused) {
                AbstractAdaptiveCountingMemoryCache.this.releaseClientReference(entry);
            }
        });
    }

    @Nullable
    private synchronized CloseableReference<V> referenceToClose(CountingMemoryCache.Entry<K, V> entry) {
        Preconditions.checkNotNull(entry);
        return (!entry.isOrphan || entry.clientCount != 0) ? null : entry.valueRef;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseClientReference(final CountingMemoryCache.Entry<K, V> entry) {
        boolean maybeAddToExclusives;
        CloseableReference<V> referenceToClose;
        Preconditions.checkNotNull(entry);
        synchronized (this) {
            decreaseClientCount(entry);
            maybeAddToExclusives = maybeAddToExclusives(entry);
            referenceToClose = referenceToClose(entry);
        }
        CloseableReference.closeSafely((CloseableReference<?>) referenceToClose);
        if (!maybeAddToExclusives) {
            entry = null;
        }
        maybeNotifyExclusiveEntryInsertion(entry);
        maybeUpdateCacheParams();
        maybeEvictEntries();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    private synchronized ArrayList<CountingMemoryCache.Entry<K, V>> trimExclusivelyOwnedEntries(int count, int size, CountingLruMap<K, CountingMemoryCache.Entry<K, V>> ExclusixeEntries, ArrayListType evictionType) {
        int max = Math.max(count, 0);
        int max2 = Math.max(size, 0);
        if (ExclusixeEntries.getCount() > max || ExclusixeEntries.getSizeInBytes() > max2) {
            ArrayList<CountingMemoryCache.Entry<K, V>> arrayList = new ArrayList<>();
            while (true) {
                if (ExclusixeEntries.getCount() <= max && ExclusixeEntries.getSizeInBytes() <= max2) {
                    return arrayList;
                }
                Object checkNotNull = Preconditions.checkNotNull(ExclusixeEntries.getFirstKey());
                addElementToGhostList(checkNotNull, ((CountingMemoryCache.Entry) Preconditions.checkNotNull(ExclusixeEntries.get(checkNotNull))).accessCount, evictionType);
                ExclusixeEntries.remove(checkNotNull);
                arrayList.add(this.mCachedEntries.remove(checkNotNull));
            }
        } else {
            return null;
        }
    }

    private ValueDescriptor<CountingMemoryCache.Entry<K, V>> wrapValueDescriptor(final ValueDescriptor<V> evictableValueDescriptor) {
        return new ValueDescriptor<CountingMemoryCache.Entry<K, V>>() { // from class: com.facebook.imagepipeline.cache.AbstractAdaptiveCountingMemoryCache.1
            @Override // com.facebook.imagepipeline.cache.ValueDescriptor
            public /* bridge */ /* synthetic */ int getSizeInBytes(Object entry) {
                return getSizeInBytes((CountingMemoryCache.Entry) ((CountingMemoryCache.Entry) entry));
            }

            public int getSizeInBytes(CountingMemoryCache.Entry<K, V> entry) {
                return evictableValueDescriptor.getSizeInBytes(entry.valueRef.get());
            }
        };
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    @Nullable
    public CloseableReference<V> cache(final K key, final CloseableReference<V> valueRef) {
        return cache(key, valueRef, null);
    }

    @Override // com.facebook.imagepipeline.cache.CountingMemoryCache
    public void clear() {
        ArrayList<CountingMemoryCache.Entry<K, V>> clear;
        ArrayList<CountingMemoryCache.Entry<K, V>> clear2;
        ArrayList<CountingMemoryCache.Entry<K, V>> clear3;
        synchronized (this) {
            clear = this.mLeastFrequentlyUsedExclusiveEntries.clear();
            clear2 = this.mMostFrequentlyUsedExclusiveEntries.clear();
            clear3 = this.mCachedEntries.clear();
            makeOrphans(clear3);
        }
        maybeClose(clear3);
        maybeNotifyExclusiveEntriesRemoval(clear, clear2);
        maybeUpdateCacheParams();
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public synchronized boolean contains(Predicate<K> predicate) {
        return !this.mCachedEntries.getMatchingEntries(predicate).isEmpty();
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    @Nullable
    public CloseableReference<V> get(final K key) {
        CountingMemoryCache.Entry<K, V> remove;
        CountingMemoryCache.Entry<K, V> remove2;
        CloseableReference<V> closeableReference;
        Preconditions.checkNotNull(key);
        synchronized (this) {
            remove = this.mLeastFrequentlyUsedExclusiveEntries.remove(key);
            remove2 = this.mMostFrequentlyUsedExclusiveEntries.remove(key);
            CountingMemoryCache.Entry<K, V> entry = this.mCachedEntries.get(key);
            if (entry != null) {
                closeableReference = newClientReference(entry);
            } else {
                maybeUpdateCacheFraction(key);
                closeableReference = null;
            }
        }
        maybeNotifyExclusiveEntryRemoval(remove, remove2);
        maybeUpdateCacheParams();
        maybeEvictEntries();
        return closeableReference;
    }

    @Override // com.facebook.imagepipeline.cache.CountingMemoryCache
    public CountingLruMap getCachedEntries() {
        return this.mCachedEntries;
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public synchronized int getCount() {
        return this.mCachedEntries.getCount();
    }

    @Override // com.facebook.imagepipeline.cache.CountingMemoryCache
    public synchronized int getEvictionQueueCount() {
        return this.mLeastFrequentlyUsedExclusiveEntries.getCount() + this.mMostFrequentlyUsedExclusiveEntries.getCount();
    }

    @Override // com.facebook.imagepipeline.cache.CountingMemoryCache
    public synchronized int getEvictionQueueSizeInBytes() {
        return this.mLeastFrequentlyUsedExclusiveEntries.getSizeInBytes() + this.mMostFrequentlyUsedExclusiveEntries.getSizeInBytes();
    }

    public synchronized int getInUseCount() {
        return (this.mCachedEntries.getCount() - this.mLeastFrequentlyUsedExclusiveEntries.getCount()) - this.mMostFrequentlyUsedExclusiveEntries.getCount();
    }

    @Override // com.facebook.imagepipeline.cache.CountingMemoryCache
    public synchronized int getInUseSizeInBytes() {
        return (this.mCachedEntries.getSizeInBytes() - this.mLeastFrequentlyUsedExclusiveEntries.getSizeInBytes()) - this.mMostFrequentlyUsedExclusiveEntries.getSizeInBytes();
    }

    @Override // com.facebook.imagepipeline.cache.CountingMemoryCache
    public MemoryCacheParams getMemoryCacheParams() {
        return this.mMemoryCacheParams;
    }

    @Override // com.facebook.imagepipeline.cache.CountingMemoryCache
    public Map<Bitmap, Object> getOtherEntries() {
        return Collections.emptyMap();
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public synchronized int getSizeInBytes() {
        return this.mCachedEntries.getSizeInBytes();
    }

    protected abstract void logIllegalAdaptiveRate();

    protected abstract void logIllegalLfuFraction();

    @Override // com.facebook.imagepipeline.cache.CountingMemoryCache
    public void maybeEvictEntries() {
        ArrayList<CountingMemoryCache.Entry<K, V>> trimExclusivelyOwnedEntries;
        ArrayList<CountingMemoryCache.Entry<K, V>> trimExclusivelyOwnedEntries2;
        synchronized (this) {
            int min = Math.min(this.mMemoryCacheParams.maxEvictionQueueEntries, this.mMemoryCacheParams.maxCacheEntries - getInUseCount());
            int min2 = Math.min(this.mMemoryCacheParams.maxEvictionQueueSize, this.mMemoryCacheParams.maxCacheSize - getInUseSizeInBytes());
            int i = (int) ((min * this.mLFUFractionPromil) / 1000);
            int i2 = (int) ((min2 * this.mLFUFractionPromil) / 1000);
            trimExclusivelyOwnedEntries = trimExclusivelyOwnedEntries(i, i2, this.mLeastFrequentlyUsedExclusiveEntries, ArrayListType.LFU);
            trimExclusivelyOwnedEntries2 = trimExclusivelyOwnedEntries(min - i, min2 - i2, this.mMostFrequentlyUsedExclusiveEntries, ArrayListType.MFU);
            makeOrphans(trimExclusivelyOwnedEntries, trimExclusivelyOwnedEntries2);
        }
        maybeClose(trimExclusivelyOwnedEntries, trimExclusivelyOwnedEntries2);
        maybeNotifyExclusiveEntriesRemoval(trimExclusivelyOwnedEntries, trimExclusivelyOwnedEntries2);
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public void probe(K key) {
        Preconditions.checkNotNull(key);
        synchronized (this) {
            CountingMemoryCache.Entry<K, V> remove = this.mLeastFrequentlyUsedExclusiveEntries.remove(key);
            if (remove == null) {
                remove = this.mMostFrequentlyUsedExclusiveEntries.remove(key);
            }
            if (remove != null) {
                increaseAccessCount(remove);
                maybeAddToExclusives(remove);
            }
        }
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public int removeAll(Predicate<K> predicate) {
        ArrayList<CountingMemoryCache.Entry<K, V>> removeAll;
        ArrayList<CountingMemoryCache.Entry<K, V>> removeAll2;
        ArrayList<CountingMemoryCache.Entry<K, V>> removeAll3;
        synchronized (this) {
            removeAll = this.mLeastFrequentlyUsedExclusiveEntries.removeAll(predicate);
            removeAll2 = this.mMostFrequentlyUsedExclusiveEntries.removeAll(predicate);
            removeAll3 = this.mCachedEntries.removeAll(predicate);
            makeOrphans(removeAll3);
        }
        maybeClose(removeAll3);
        maybeNotifyExclusiveEntriesRemoval(removeAll, removeAll2);
        maybeUpdateCacheParams();
        maybeEvictEntries();
        return removeAll3.size();
    }

    public String reportData() {
        return Objects.toStringHelper("CountingMemoryCache").add("cached_entries_count:", this.mCachedEntries.getCount()).add("exclusive_entries_count", getEvictionQueueCount()).toString();
    }

    @Override // com.facebook.imagepipeline.cache.CountingMemoryCache
    @Nullable
    public CloseableReference<V> reuse(K key) {
        CountingMemoryCache.Entry<K, V> remove;
        boolean z;
        CloseableReference<V> closeableReference;
        Preconditions.checkNotNull(key);
        synchronized (this) {
            remove = this.mLeastFrequentlyUsedExclusiveEntries.remove(key);
            if (remove == null) {
                remove = this.mMostFrequentlyUsedExclusiveEntries.remove(key);
            }
            z = true;
            boolean z2 = false;
            if (remove != null) {
                CountingMemoryCache.Entry<K, V> remove2 = this.mCachedEntries.remove(key);
                Preconditions.checkNotNull(remove2);
                if (remove2.clientCount == 0) {
                    z2 = true;
                }
                Preconditions.checkState(z2);
                closeableReference = remove2.valueRef;
            } else {
                closeableReference = null;
                z = false;
            }
        }
        if (z) {
            maybeNotifyExclusiveEntryRemoval(remove);
        }
        return closeableReference;
    }

    @Override // com.facebook.common.memory.MemoryTrimmable
    public void trim(MemoryTrimType trimType) {
        ArrayList<CountingMemoryCache.Entry<K, V>> trimExclusivelyOwnedEntries;
        ArrayList<CountingMemoryCache.Entry<K, V>> trimExclusivelyOwnedEntries2;
        double trimRatio = this.mCacheTrimStrategy.getTrimRatio(trimType);
        synchronized (this) {
            int i = 0;
            int max = Math.max(0, ((int) ((1.0d - trimRatio) * this.mCachedEntries.getSizeInBytes())) - getInUseSizeInBytes());
            int sizeInBytes = this.mMostFrequentlyUsedExclusiveEntries.getSizeInBytes();
            int max2 = Math.max(0, max - sizeInBytes);
            if (max > sizeInBytes) {
                max = sizeInBytes;
                i = max2;
            }
            trimExclusivelyOwnedEntries = trimExclusivelyOwnedEntries(Integer.MAX_VALUE, i, this.mLeastFrequentlyUsedExclusiveEntries, ArrayListType.LFU);
            trimExclusivelyOwnedEntries2 = trimExclusivelyOwnedEntries(Integer.MAX_VALUE, max, this.mMostFrequentlyUsedExclusiveEntries, ArrayListType.MFU);
            makeOrphans(trimExclusivelyOwnedEntries, trimExclusivelyOwnedEntries2);
        }
        maybeClose(trimExclusivelyOwnedEntries, trimExclusivelyOwnedEntries2);
        maybeNotifyExclusiveEntriesRemoval(trimExclusivelyOwnedEntries, trimExclusivelyOwnedEntries2);
        maybeUpdateCacheParams();
        maybeEvictEntries();
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0031 A[Catch: all -> 0x006a, TryCatch #0 {, blocks: (B:4:0x000a, B:10:0x0023, B:12:0x0031, B:14:0x003a, B:16:0x0044, B:18:0x0050, B:19:0x0054, B:20:0x005f), top: B:26:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0044 A[Catch: all -> 0x006a, TryCatch #0 {, blocks: (B:4:0x000a, B:10:0x0023, B:12:0x0031, B:14:0x003a, B:16:0x0044, B:18:0x0050, B:19:0x0054, B:20:0x005f), top: B:26:0x000a }] */
    @Override // com.facebook.imagepipeline.cache.CountingMemoryCache
    @javax.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.facebook.common.references.CloseableReference<V> cache(final K r7, final com.facebook.common.references.CloseableReference<V> r8, @javax.annotation.Nullable final com.facebook.imagepipeline.cache.CountingMemoryCache.EntryStateObserver<K> r9) {
        /*
            r6 = this;
            com.facebook.common.internal.Preconditions.checkNotNull(r7)
            com.facebook.common.internal.Preconditions.checkNotNull(r8)
            r6.maybeUpdateCacheParams()
            monitor-enter(r6)
            com.facebook.imagepipeline.cache.CountingLruMap<K, com.facebook.imagepipeline.cache.CountingMemoryCache$Entry<K, V>> r0 = r6.mLeastFrequentlyUsedExclusiveEntries     // Catch: java.lang.Throwable -> L6a
            java.lang.Object r0 = r0.remove(r7)     // Catch: java.lang.Throwable -> L6a
            com.facebook.imagepipeline.cache.CountingMemoryCache$Entry r0 = (com.facebook.imagepipeline.cache.CountingMemoryCache.Entry) r0     // Catch: java.lang.Throwable -> L6a
            com.facebook.imagepipeline.cache.CountingLruMap<K, com.facebook.imagepipeline.cache.CountingMemoryCache$Entry<K, V>> r1 = r6.mMostFrequentlyUsedExclusiveEntries     // Catch: java.lang.Throwable -> L6a
            java.lang.Object r1 = r1.remove(r7)     // Catch: java.lang.Throwable -> L6a
            com.facebook.imagepipeline.cache.CountingMemoryCache$Entry r1 = (com.facebook.imagepipeline.cache.CountingMemoryCache.Entry) r1     // Catch: java.lang.Throwable -> L6a
            r2 = 0
            if (r0 == 0) goto L22
            if (r1 != 0) goto L20
            goto L22
        L20:
            r3 = r2
            goto L23
        L22:
            r3 = 1
        L23:
            com.facebook.common.internal.Preconditions.checkState(r3)     // Catch: java.lang.Throwable -> L6a
            com.facebook.imagepipeline.cache.CountingLruMap<K, com.facebook.imagepipeline.cache.CountingMemoryCache$Entry<K, V>> r3 = r6.mCachedEntries     // Catch: java.lang.Throwable -> L6a
            java.lang.Object r3 = r3.remove(r7)     // Catch: java.lang.Throwable -> L6a
            com.facebook.imagepipeline.cache.CountingMemoryCache$Entry r3 = (com.facebook.imagepipeline.cache.CountingMemoryCache.Entry) r3     // Catch: java.lang.Throwable -> L6a
            r4 = 0
            if (r3 == 0) goto L39
            r6.makeOrphan(r3)     // Catch: java.lang.Throwable -> L6a
            com.facebook.common.references.CloseableReference r3 = r6.referenceToClose(r3)     // Catch: java.lang.Throwable -> L6a
            goto L3a
        L39:
            r3 = r4
        L3a:
            java.lang.Object r5 = r8.get()     // Catch: java.lang.Throwable -> L6a
            boolean r5 = r6.canCacheNewValue(r5)     // Catch: java.lang.Throwable -> L6a
            if (r5 == 0) goto L5f
            com.facebook.imagepipeline.cache.CountingMemoryCache$Entry r8 = com.facebook.imagepipeline.cache.CountingMemoryCache.Entry.of(r7, r8, r9)     // Catch: java.lang.Throwable -> L6a
            com.facebook.imagepipeline.cache.AbstractAdaptiveCountingMemoryCache<K, V>$IntMapArrayList<K> r9 = r6.mLeastFrequentlyUsedKeysGhostList     // Catch: java.lang.Throwable -> L6a
            java.lang.Integer r9 = r9.getValue(r7)     // Catch: java.lang.Throwable -> L6a
            if (r9 == 0) goto L54
            int r2 = r9.intValue()     // Catch: java.lang.Throwable -> L6a
        L54:
            r8.accessCount = r2     // Catch: java.lang.Throwable -> L6a
            com.facebook.imagepipeline.cache.CountingLruMap<K, com.facebook.imagepipeline.cache.CountingMemoryCache$Entry<K, V>> r9 = r6.mCachedEntries     // Catch: java.lang.Throwable -> L6a
            r9.put(r7, r8)     // Catch: java.lang.Throwable -> L6a
            com.facebook.common.references.CloseableReference r4 = r6.newClientReference(r8)     // Catch: java.lang.Throwable -> L6a
        L5f:
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L6a
            com.facebook.common.references.CloseableReference.closeSafely(r3)
            r6.maybeNotifyExclusiveEntryRemoval(r0, r1)
            r6.maybeEvictEntries()
            return r4
        L6a:
            r7 = move-exception
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L6a
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.cache.AbstractAdaptiveCountingMemoryCache.cache(java.lang.Object, com.facebook.common.references.CloseableReference, com.facebook.imagepipeline.cache.CountingMemoryCache$EntryStateObserver):com.facebook.common.references.CloseableReference");
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public synchronized boolean contains(K key) {
        return this.mCachedEntries.contains(key);
    }

    private void maybeClose(@Nullable ArrayList<CountingMemoryCache.Entry<K, V>> oldEntries) {
        if (oldEntries != null) {
            Iterator<CountingMemoryCache.Entry<K, V>> it2 = oldEntries.iterator();
            while (it2.hasNext()) {
                CloseableReference.closeSafely((CloseableReference<?>) referenceToClose(it2.next()));
            }
        }
    }

    private void maybeNotifyExclusiveEntryRemoval(@Nullable ArrayList<CountingMemoryCache.Entry<K, V>> entries) {
        if (entries != null) {
            Iterator<CountingMemoryCache.Entry<K, V>> it2 = entries.iterator();
            while (it2.hasNext()) {
                maybeNotifyExclusiveEntryRemoval(it2.next());
            }
        }
    }

    private synchronized void makeOrphans(@Nullable ArrayList<CountingMemoryCache.Entry<K, V>> oldEntries) {
        if (oldEntries != null) {
            Iterator<CountingMemoryCache.Entry<K, V>> it2 = oldEntries.iterator();
            while (it2.hasNext()) {
                makeOrphan(it2.next());
            }
        }
    }

    private static <K, V> void maybeNotifyExclusiveEntryRemoval(@Nullable CountingMemoryCache.Entry<K, V> entry) {
        CountingMemoryCache.EntryStateObserver<K> entryStateObserver;
        if (entry == null || (entryStateObserver = entry.observer) == null) {
            return;
        }
        entryStateObserver.onExclusivityChanged(entry.key, false);
    }
}
