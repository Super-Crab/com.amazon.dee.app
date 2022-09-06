package com.facebook.cache.disk;

import androidx.annotation.VisibleForTesting;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.binaryresource.BinaryResource;
import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.cache.common.CacheEventListener;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.CacheKeyUtil;
import com.facebook.cache.common.WriterCallback;
import com.facebook.cache.disk.DiskStorage;
import com.facebook.common.disk.DiskTrimmable;
import com.facebook.common.disk.DiskTrimmableRegistry;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.common.statfs.StatFsHelper;
import com.facebook.common.time.Clock;
import com.facebook.common.time.SystemClock;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
@ThreadSafe
/* loaded from: classes2.dex */
public class DiskStorageCache implements FileCache, DiskTrimmable {
    public static final int START_OF_VERSIONING = 1;
    private static final double TRIMMING_LOWER_BOUND = 0.02d;
    private static final long UNINITIALIZED = -1;
    private final CacheErrorLogger mCacheErrorLogger;
    private final CacheEventListener mCacheEventListener;
    private long mCacheSizeLastUpdateTime;
    private long mCacheSizeLimit;
    private final long mCacheSizeLimitMinimum;
    private final CacheStats mCacheStats;
    private final Clock mClock;
    private final CountDownLatch mCountDownLatch;
    private final long mDefaultCacheSizeLimit;
    private final EntryEvictionComparatorSupplier mEntryEvictionComparatorSupplier;
    private final boolean mIndexPopulateAtStartupEnabled;
    private boolean mIndexReady;
    private final Object mLock = new Object();
    private final long mLowDiskSpaceCacheSizeLimit;
    @GuardedBy("mLock")
    @VisibleForTesting
    final Set<String> mResourceIndex;
    private final StatFsHelper mStatFsHelper;
    private final DiskStorage mStorage;
    private static final Class<?> TAG = DiskStorageCache.class;
    private static final long FUTURE_TIMESTAMP_THRESHOLD_MS = TimeUnit.HOURS.toMillis(2);
    private static final long FILECACHE_SIZE_UPDATE_PERIOD_MS = TimeUnit.MINUTES.toMillis(30);

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes2.dex */
    public static class CacheStats {
        private boolean mInitialized = false;
        private long mSize = -1;
        private long mCount = -1;

        CacheStats() {
        }

        public synchronized long getCount() {
            return this.mCount;
        }

        public synchronized long getSize() {
            return this.mSize;
        }

        public synchronized void increment(long sizeIncrement, long countIncrement) {
            if (this.mInitialized) {
                this.mSize += sizeIncrement;
                this.mCount += countIncrement;
            }
        }

        public synchronized boolean isInitialized() {
            return this.mInitialized;
        }

        public synchronized void reset() {
            this.mInitialized = false;
            this.mCount = -1L;
            this.mSize = -1L;
        }

        public synchronized void set(long size, long count) {
            this.mCount = count;
            this.mSize = size;
            this.mInitialized = true;
        }
    }

    /* loaded from: classes2.dex */
    public static class Params {
        public final long mCacheSizeLimitMinimum;
        public final long mDefaultCacheSizeLimit;
        public final long mLowDiskSpaceCacheSizeLimit;

        public Params(long cacheSizeLimitMinimum, long lowDiskSpaceCacheSizeLimit, long defaultCacheSizeLimit) {
            this.mCacheSizeLimitMinimum = cacheSizeLimitMinimum;
            this.mLowDiskSpaceCacheSizeLimit = lowDiskSpaceCacheSizeLimit;
            this.mDefaultCacheSizeLimit = defaultCacheSizeLimit;
        }
    }

    public DiskStorageCache(DiskStorage diskStorage, EntryEvictionComparatorSupplier entryEvictionComparatorSupplier, Params params, CacheEventListener cacheEventListener, CacheErrorLogger cacheErrorLogger, @Nullable DiskTrimmableRegistry diskTrimmableRegistry, final Executor executorForBackgrountInit, boolean indexPopulateAtStartupEnabled) {
        this.mLowDiskSpaceCacheSizeLimit = params.mLowDiskSpaceCacheSizeLimit;
        long j = params.mDefaultCacheSizeLimit;
        this.mDefaultCacheSizeLimit = j;
        this.mCacheSizeLimit = j;
        this.mStatFsHelper = StatFsHelper.getInstance();
        this.mStorage = diskStorage;
        this.mEntryEvictionComparatorSupplier = entryEvictionComparatorSupplier;
        this.mCacheSizeLastUpdateTime = -1L;
        this.mCacheEventListener = cacheEventListener;
        this.mCacheSizeLimitMinimum = params.mCacheSizeLimitMinimum;
        this.mCacheErrorLogger = cacheErrorLogger;
        this.mCacheStats = new CacheStats();
        this.mClock = SystemClock.get();
        this.mIndexPopulateAtStartupEnabled = indexPopulateAtStartupEnabled;
        this.mResourceIndex = new HashSet();
        if (diskTrimmableRegistry != null) {
            diskTrimmableRegistry.registerDiskTrimmable(this);
        }
        if (this.mIndexPopulateAtStartupEnabled) {
            this.mCountDownLatch = new CountDownLatch(1);
            executorForBackgrountInit.execute(new Runnable() { // from class: com.facebook.cache.disk.DiskStorageCache.1
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (DiskStorageCache.this.mLock) {
                        DiskStorageCache.this.maybeUpdateFileCacheSize();
                    }
                    DiskStorageCache.this.mIndexReady = true;
                    DiskStorageCache.this.mCountDownLatch.countDown();
                }
            });
            return;
        }
        this.mCountDownLatch = new CountDownLatch(0);
    }

    private BinaryResource endInsert(final DiskStorage.Inserter inserter, final CacheKey key, String resourceId) throws IOException {
        BinaryResource commit;
        synchronized (this.mLock) {
            commit = inserter.commit(key);
            this.mResourceIndex.add(resourceId);
            this.mCacheStats.increment(commit.size(), 1L);
        }
        return commit;
    }

    @GuardedBy("mLock")
    private void evictAboveSize(long desiredSize, CacheEventListener.EvictionReason reason) throws IOException {
        try {
            Collection<DiskStorage.Entry> sortedEntries = getSortedEntries(this.mStorage.mo6853getEntries());
            long size = this.mCacheStats.getSize();
            long j = size - desiredSize;
            int i = 0;
            long j2 = 0;
            for (DiskStorage.Entry entry : sortedEntries) {
                if (j2 > j) {
                    break;
                }
                long remove = this.mStorage.remove(entry);
                this.mResourceIndex.remove(entry.getId());
                if (remove > 0) {
                    i++;
                    j2 += remove;
                    SettableCacheEvent cacheLimit = SettableCacheEvent.obtain().setResourceId(entry.getId()).setEvictionReason(reason).setItemSize(remove).setCacheSize(size - j2).setCacheLimit(desiredSize);
                    this.mCacheEventListener.onEviction(cacheLimit);
                    cacheLimit.recycle();
                }
            }
            this.mCacheStats.increment(-j2, -i);
            this.mStorage.purgeUnexpectedResources();
        } catch (IOException e) {
            this.mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.EVICTION, TAG, GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("evictAboveSize: ")), e);
            throw e;
        }
    }

    private Collection<DiskStorage.Entry> getSortedEntries(Collection<DiskStorage.Entry> allEntries) {
        long now = this.mClock.now() + FUTURE_TIMESTAMP_THRESHOLD_MS;
        ArrayList arrayList = new ArrayList(allEntries.size());
        ArrayList arrayList2 = new ArrayList(allEntries.size());
        for (DiskStorage.Entry entry : allEntries) {
            if (entry.getTimestamp() > now) {
                arrayList.add(entry);
            } else {
                arrayList2.add(entry);
            }
        }
        Collections.sort(arrayList2, this.mEntryEvictionComparatorSupplier.get());
        arrayList.addAll(arrayList2);
        return arrayList;
    }

    private void maybeEvictFilesInCacheDir() throws IOException {
        synchronized (this.mLock) {
            boolean maybeUpdateFileCacheSize = maybeUpdateFileCacheSize();
            updateFileCacheSizeLimit();
            long size = this.mCacheStats.getSize();
            if (size > this.mCacheSizeLimit && !maybeUpdateFileCacheSize) {
                this.mCacheStats.reset();
                maybeUpdateFileCacheSize();
            }
            if (size > this.mCacheSizeLimit) {
                evictAboveSize((this.mCacheSizeLimit * 9) / 10, CacheEventListener.EvictionReason.CACHE_FULL);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @GuardedBy("mLock")
    public boolean maybeUpdateFileCacheSize() {
        long now = this.mClock.now();
        if (this.mCacheStats.isInitialized()) {
            long j = this.mCacheSizeLastUpdateTime;
            if (j != -1 && now - j <= FILECACHE_SIZE_UPDATE_PERIOD_MS) {
                return false;
            }
        }
        return maybeUpdateFileCacheSizeAndIndex();
    }

    @GuardedBy("mLock")
    private boolean maybeUpdateFileCacheSizeAndIndex() {
        Set<String> hashSet;
        long j;
        long now = this.mClock.now();
        long j2 = FUTURE_TIMESTAMP_THRESHOLD_MS + now;
        if (this.mIndexPopulateAtStartupEnabled && this.mResourceIndex.isEmpty()) {
            hashSet = this.mResourceIndex;
        } else {
            hashSet = this.mIndexPopulateAtStartupEnabled ? new HashSet<>() : null;
        }
        try {
            long j3 = 0;
            long j4 = -1;
            int i = 0;
            int i2 = 0;
            boolean z = false;
            int i3 = 0;
            for (DiskStorage.Entry entry : this.mStorage.mo6853getEntries()) {
                i3++;
                j3 += entry.getSize();
                if (entry.getTimestamp() > j2) {
                    i++;
                    i2 = (int) (i2 + entry.getSize());
                    j = j2;
                    j4 = Math.max(entry.getTimestamp() - now, j4);
                    z = true;
                } else {
                    j = j2;
                    if (this.mIndexPopulateAtStartupEnabled) {
                        Preconditions.checkNotNull(hashSet);
                        hashSet.add(entry.getId());
                    }
                }
                j2 = j;
            }
            if (z) {
                this.mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.READ_INVALID_ENTRY, TAG, "Future timestamp found in " + i + " files , with a total size of " + i2 + " bytes, and a maximum time delta of " + j4 + "ms", null);
            }
            long j5 = i3;
            if (this.mCacheStats.getCount() != j5 || this.mCacheStats.getSize() != j3) {
                if (this.mIndexPopulateAtStartupEnabled && this.mResourceIndex != hashSet) {
                    Preconditions.checkNotNull(hashSet);
                    this.mResourceIndex.clear();
                    this.mResourceIndex.addAll(hashSet);
                }
                this.mCacheStats.set(j3, j5);
            }
            this.mCacheSizeLastUpdateTime = now;
            return true;
        } catch (IOException e) {
            this.mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.GENERIC_IO, TAG, GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("calcFileCacheSize: ")), e);
            return false;
        }
    }

    private DiskStorage.Inserter startInsert(final String resourceId, final CacheKey key) throws IOException {
        maybeEvictFilesInCacheDir();
        return this.mStorage.insert(resourceId, key);
    }

    private void trimBy(final double trimRatio) {
        synchronized (this.mLock) {
            try {
                this.mCacheStats.reset();
                maybeUpdateFileCacheSize();
                long size = this.mCacheStats.getSize();
                evictAboveSize(size - ((long) (trimRatio * size)), CacheEventListener.EvictionReason.CACHE_MANAGER_TRIMMED);
            } catch (IOException e) {
                CacheErrorLogger cacheErrorLogger = this.mCacheErrorLogger;
                CacheErrorLogger.CacheErrorCategory cacheErrorCategory = CacheErrorLogger.CacheErrorCategory.EVICTION;
                Class<?> cls = TAG;
                cacheErrorLogger.logError(cacheErrorCategory, cls, "trimBy: " + e.getMessage(), e);
            }
        }
    }

    @GuardedBy("mLock")
    private void updateFileCacheSizeLimit() {
        if (this.mStatFsHelper.testLowDiskSpace(this.mStorage.isExternal() ? StatFsHelper.StorageType.EXTERNAL : StatFsHelper.StorageType.INTERNAL, this.mDefaultCacheSizeLimit - this.mCacheStats.getSize())) {
            this.mCacheSizeLimit = this.mLowDiskSpaceCacheSizeLimit;
        } else {
            this.mCacheSizeLimit = this.mDefaultCacheSizeLimit;
        }
    }

    @VisibleForTesting
    protected void awaitIndex() {
        try {
            this.mCountDownLatch.await();
        } catch (InterruptedException unused) {
            FLog.e(TAG, "Memory Index is not ready yet. ");
        }
    }

    @Override // com.facebook.cache.disk.FileCache
    public void clearAll() {
        synchronized (this.mLock) {
            try {
                this.mStorage.clearAll();
                this.mResourceIndex.clear();
                this.mCacheEventListener.onCleared();
            } catch (IOException | NullPointerException e) {
                CacheErrorLogger cacheErrorLogger = this.mCacheErrorLogger;
                CacheErrorLogger.CacheErrorCategory cacheErrorCategory = CacheErrorLogger.CacheErrorCategory.EVICTION;
                Class<?> cls = TAG;
                cacheErrorLogger.logError(cacheErrorCategory, cls, "clearAll: " + e.getMessage(), e);
            }
            this.mCacheStats.reset();
        }
    }

    @Override // com.facebook.cache.disk.FileCache
    public long clearOldEntries(long cacheExpirationMs) {
        long j;
        synchronized (this.mLock) {
            try {
                long now = this.mClock.now();
                Collection<DiskStorage.Entry> mo6853getEntries = this.mStorage.mo6853getEntries();
                long size = this.mCacheStats.getSize();
                int i = 0;
                long j2 = 0;
                j = 0;
                for (DiskStorage.Entry entry : mo6853getEntries) {
                    try {
                        long j3 = now;
                        long max = Math.max(1L, Math.abs(now - entry.getTimestamp()));
                        if (max >= cacheExpirationMs) {
                            long remove = this.mStorage.remove(entry);
                            this.mResourceIndex.remove(entry.getId());
                            if (remove > 0) {
                                i++;
                                j2 += remove;
                                SettableCacheEvent cacheSize = SettableCacheEvent.obtain().setResourceId(entry.getId()).setEvictionReason(CacheEventListener.EvictionReason.CONTENT_STALE).setItemSize(remove).setCacheSize(size - j2);
                                this.mCacheEventListener.onEviction(cacheSize);
                                cacheSize.recycle();
                            }
                        } else {
                            j = Math.max(j, max);
                        }
                        now = j3;
                    } catch (IOException e) {
                        e = e;
                        this.mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.EVICTION, TAG, "clearOldEntries: " + e.getMessage(), e);
                        return j;
                    }
                }
                this.mStorage.purgeUnexpectedResources();
                if (i > 0) {
                    maybeUpdateFileCacheSize();
                    this.mCacheStats.increment(-j2, -i);
                }
            } catch (IOException e2) {
                e = e2;
                j = 0;
            }
        }
        return j;
    }

    @Override // com.facebook.cache.disk.FileCache
    public long getCount() {
        return this.mCacheStats.getCount();
    }

    @Override // com.facebook.cache.disk.FileCache
    public DiskStorage.DiskDumpInfo getDumpInfo() throws IOException {
        return this.mStorage.getDumpInfo();
    }

    @Override // com.facebook.cache.disk.FileCache
    @Nullable
    public BinaryResource getResource(final CacheKey key) {
        BinaryResource binaryResource;
        SettableCacheEvent cacheKey = SettableCacheEvent.obtain().setCacheKey(key);
        try {
            synchronized (this.mLock) {
                List<String> resourceIds = CacheKeyUtil.getResourceIds(key);
                String str = null;
                binaryResource = null;
                for (int i = 0; i < resourceIds.size(); i++) {
                    str = resourceIds.get(i);
                    cacheKey.setResourceId(str);
                    binaryResource = this.mStorage.getResource(str, key);
                    if (binaryResource != null) {
                        break;
                    }
                }
                if (binaryResource == null) {
                    this.mCacheEventListener.onMiss(cacheKey);
                    this.mResourceIndex.remove(str);
                } else {
                    Preconditions.checkNotNull(str);
                    this.mCacheEventListener.onHit(cacheKey);
                    this.mResourceIndex.add(str);
                }
            }
            return binaryResource;
        } catch (IOException e) {
            this.mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.GENERIC_IO, TAG, "getResource", e);
            cacheKey.setException(e);
            this.mCacheEventListener.onReadException(cacheKey);
            return null;
        } finally {
            cacheKey.recycle();
        }
    }

    @Override // com.facebook.cache.disk.FileCache
    public long getSize() {
        return this.mCacheStats.getSize();
    }

    @Override // com.facebook.cache.disk.FileCache
    public boolean hasKey(final CacheKey key) {
        synchronized (this.mLock) {
            if (hasKeySync(key)) {
                return true;
            }
            try {
                List<String> resourceIds = CacheKeyUtil.getResourceIds(key);
                for (int i = 0; i < resourceIds.size(); i++) {
                    String str = resourceIds.get(i);
                    if (this.mStorage.contains(str, key)) {
                        this.mResourceIndex.add(str);
                        return true;
                    }
                }
                return false;
            } catch (IOException unused) {
                return false;
            }
        }
    }

    @Override // com.facebook.cache.disk.FileCache
    public boolean hasKeySync(CacheKey key) {
        synchronized (this.mLock) {
            List<String> resourceIds = CacheKeyUtil.getResourceIds(key);
            for (int i = 0; i < resourceIds.size(); i++) {
                if (this.mResourceIndex.contains(resourceIds.get(i))) {
                    return true;
                }
            }
            return false;
        }
    }

    @Override // com.facebook.cache.disk.FileCache
    public BinaryResource insert(CacheKey key, WriterCallback callback) throws IOException {
        String firstResourceId;
        SettableCacheEvent cacheKey = SettableCacheEvent.obtain().setCacheKey(key);
        this.mCacheEventListener.onWriteAttempt(cacheKey);
        synchronized (this.mLock) {
            firstResourceId = CacheKeyUtil.getFirstResourceId(key);
        }
        cacheKey.setResourceId(firstResourceId);
        try {
            try {
                DiskStorage.Inserter startInsert = startInsert(firstResourceId, key);
                try {
                    startInsert.writeData(callback, key);
                    BinaryResource endInsert = endInsert(startInsert, key, firstResourceId);
                    cacheKey.setItemSize(endInsert.size()).setCacheSize(this.mCacheStats.getSize());
                    this.mCacheEventListener.onWriteSuccess(cacheKey);
                    return endInsert;
                } finally {
                    if (!startInsert.cleanUp()) {
                        FLog.e(TAG, "Failed to delete temp file");
                    }
                }
            } finally {
                cacheKey.recycle();
            }
        } catch (IOException e) {
            cacheKey.setException(e);
            this.mCacheEventListener.onWriteException(cacheKey);
            FLog.e(TAG, "Failed inserting a file into the cache", e);
            throw e;
        }
    }

    @Override // com.facebook.cache.disk.FileCache
    public boolean isEnabled() {
        return this.mStorage.isEnabled();
    }

    public boolean isIndexReady() {
        return this.mIndexReady || !this.mIndexPopulateAtStartupEnabled;
    }

    @Override // com.facebook.cache.disk.FileCache
    public boolean probe(final CacheKey key) {
        String str;
        String str2 = null;
        try {
            try {
                synchronized (this.mLock) {
                    try {
                        List<String> resourceIds = CacheKeyUtil.getResourceIds(key);
                        String str3 = null;
                        int i = 0;
                        while (i < resourceIds.size()) {
                            try {
                                String str4 = resourceIds.get(i);
                                if (this.mStorage.touch(str4, key)) {
                                    this.mResourceIndex.add(str4);
                                    return true;
                                }
                                i++;
                                str3 = str4;
                            } catch (Throwable th) {
                                th = th;
                                str = str3;
                                try {
                                    throw th;
                                } catch (IOException e) {
                                    e = e;
                                    str2 = str;
                                    SettableCacheEvent exception = SettableCacheEvent.obtain().setCacheKey(key).setResourceId(str2).setException(e);
                                    this.mCacheEventListener.onReadException(exception);
                                    exception.recycle();
                                    return false;
                                }
                            }
                        }
                        return false;
                    } catch (Throwable th2) {
                        str = null;
                        th = th2;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (IOException e2) {
            e = e2;
        }
    }

    @Override // com.facebook.cache.disk.FileCache
    public void remove(CacheKey key) {
        synchronized (this.mLock) {
            try {
                List<String> resourceIds = CacheKeyUtil.getResourceIds(key);
                for (int i = 0; i < resourceIds.size(); i++) {
                    String str = resourceIds.get(i);
                    this.mStorage.remove(str);
                    this.mResourceIndex.remove(str);
                }
            } catch (IOException e) {
                CacheErrorLogger cacheErrorLogger = this.mCacheErrorLogger;
                CacheErrorLogger.CacheErrorCategory cacheErrorCategory = CacheErrorLogger.CacheErrorCategory.DELETE_FILE;
                Class<?> cls = TAG;
                cacheErrorLogger.logError(cacheErrorCategory, cls, "delete: " + e.getMessage(), e);
            }
        }
    }

    @Override // com.facebook.common.disk.DiskTrimmable
    public void trimToMinimum() {
        synchronized (this.mLock) {
            maybeUpdateFileCacheSize();
            long size = this.mCacheStats.getSize();
            if (this.mCacheSizeLimitMinimum > 0 && size > 0 && size >= this.mCacheSizeLimitMinimum) {
                double d = 1.0d - (this.mCacheSizeLimitMinimum / size);
                if (d > TRIMMING_LOWER_BOUND) {
                    trimBy(d);
                }
            }
        }
    }

    @Override // com.facebook.common.disk.DiskTrimmable
    public void trimToNothing() {
        clearAll();
    }
}
