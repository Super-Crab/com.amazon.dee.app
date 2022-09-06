package com.facebook.cache.disk;

import android.content.Context;
import android.support.v4.media.session.PlaybackStateCompat;
import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.cache.common.CacheEventListener;
import com.facebook.cache.common.NoOpCacheErrorLogger;
import com.facebook.cache.common.NoOpCacheEventListener;
import com.facebook.common.disk.DiskTrimmableRegistry;
import com.facebook.common.disk.NoOpDiskTrimmableRegistry;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.internal.Suppliers;
import java.io.File;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class DiskCacheConfig {
    private final String mBaseDirectoryName;
    private final Supplier<File> mBaseDirectoryPathSupplier;
    private final CacheErrorLogger mCacheErrorLogger;
    private final CacheEventListener mCacheEventListener;
    @Nullable
    private final Context mContext;
    private final long mDefaultSizeLimit;
    private final DiskTrimmableRegistry mDiskTrimmableRegistry;
    private final EntryEvictionComparatorSupplier mEntryEvictionComparatorSupplier;
    private final boolean mIndexPopulateAtStartupEnabled;
    private final long mLowDiskSpaceSizeLimit;
    private final long mMinimumSizeLimit;
    private final int mVersion;

    /* loaded from: classes2.dex */
    public static class Builder {
        private String mBaseDirectoryName;
        @Nullable
        private Supplier<File> mBaseDirectoryPathSupplier;
        @Nullable
        private CacheErrorLogger mCacheErrorLogger;
        @Nullable
        private CacheEventListener mCacheEventListener;
        @Nullable
        private final Context mContext;
        @Nullable
        private DiskTrimmableRegistry mDiskTrimmableRegistry;
        private EntryEvictionComparatorSupplier mEntryEvictionComparatorSupplier;
        private boolean mIndexPopulateAtStartupEnabled;
        private long mMaxCacheSize;
        private long mMaxCacheSizeOnLowDiskSpace;
        private long mMaxCacheSizeOnVeryLowDiskSpace;
        private int mVersion;

        public DiskCacheConfig build() {
            return new DiskCacheConfig(this);
        }

        public Builder setBaseDirectoryName(String baseDirectoryName) {
            this.mBaseDirectoryName = baseDirectoryName;
            return this;
        }

        public Builder setBaseDirectoryPath(final File baseDirectoryPath) {
            this.mBaseDirectoryPathSupplier = Suppliers.of(baseDirectoryPath);
            return this;
        }

        public Builder setBaseDirectoryPathSupplier(Supplier<File> baseDirectoryPathSupplier) {
            this.mBaseDirectoryPathSupplier = baseDirectoryPathSupplier;
            return this;
        }

        public Builder setCacheErrorLogger(CacheErrorLogger cacheErrorLogger) {
            this.mCacheErrorLogger = cacheErrorLogger;
            return this;
        }

        public Builder setCacheEventListener(CacheEventListener cacheEventListener) {
            this.mCacheEventListener = cacheEventListener;
            return this;
        }

        public Builder setDiskTrimmableRegistry(DiskTrimmableRegistry diskTrimmableRegistry) {
            this.mDiskTrimmableRegistry = diskTrimmableRegistry;
            return this;
        }

        public Builder setEntryEvictionComparatorSupplier(EntryEvictionComparatorSupplier supplier) {
            this.mEntryEvictionComparatorSupplier = supplier;
            return this;
        }

        public Builder setIndexPopulateAtStartupEnabled(boolean indexEnabled) {
            this.mIndexPopulateAtStartupEnabled = indexEnabled;
            return this;
        }

        public Builder setMaxCacheSize(long maxCacheSize) {
            this.mMaxCacheSize = maxCacheSize;
            return this;
        }

        public Builder setMaxCacheSizeOnLowDiskSpace(long maxCacheSizeOnLowDiskSpace) {
            this.mMaxCacheSizeOnLowDiskSpace = maxCacheSizeOnLowDiskSpace;
            return this;
        }

        public Builder setMaxCacheSizeOnVeryLowDiskSpace(long maxCacheSizeOnVeryLowDiskSpace) {
            this.mMaxCacheSizeOnVeryLowDiskSpace = maxCacheSizeOnVeryLowDiskSpace;
            return this;
        }

        public Builder setVersion(int version) {
            this.mVersion = version;
            return this;
        }

        private Builder(@Nullable Context context) {
            this.mVersion = 1;
            this.mBaseDirectoryName = "image_cache";
            this.mMaxCacheSize = 41943040L;
            this.mMaxCacheSizeOnLowDiskSpace = 10485760L;
            this.mMaxCacheSizeOnVeryLowDiskSpace = PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
            this.mEntryEvictionComparatorSupplier = new DefaultEntryEvictionComparatorSupplier();
            this.mContext = context;
        }
    }

    protected DiskCacheConfig(Builder builder) {
        CacheErrorLogger cacheErrorLogger;
        CacheEventListener cacheEventListener;
        DiskTrimmableRegistry diskTrimmableRegistry;
        this.mContext = builder.mContext;
        Preconditions.checkState((builder.mBaseDirectoryPathSupplier == null && this.mContext == null) ? false : true, "Either a non-null context or a base directory path or supplier must be provided.");
        if (builder.mBaseDirectoryPathSupplier == null && this.mContext != null) {
            builder.mBaseDirectoryPathSupplier = new Supplier<File>() { // from class: com.facebook.cache.disk.DiskCacheConfig.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.facebook.common.internal.Supplier
                /* renamed from: get */
                public File mo6895get() {
                    Preconditions.checkNotNull(DiskCacheConfig.this.mContext);
                    return DiskCacheConfig.this.mContext.getApplicationContext().getCacheDir();
                }
            };
        }
        this.mVersion = builder.mVersion;
        this.mBaseDirectoryName = (String) Preconditions.checkNotNull(builder.mBaseDirectoryName);
        this.mBaseDirectoryPathSupplier = (Supplier) Preconditions.checkNotNull(builder.mBaseDirectoryPathSupplier);
        this.mDefaultSizeLimit = builder.mMaxCacheSize;
        this.mLowDiskSpaceSizeLimit = builder.mMaxCacheSizeOnLowDiskSpace;
        this.mMinimumSizeLimit = builder.mMaxCacheSizeOnVeryLowDiskSpace;
        this.mEntryEvictionComparatorSupplier = (EntryEvictionComparatorSupplier) Preconditions.checkNotNull(builder.mEntryEvictionComparatorSupplier);
        if (builder.mCacheErrorLogger != null) {
            cacheErrorLogger = builder.mCacheErrorLogger;
        } else {
            cacheErrorLogger = NoOpCacheErrorLogger.getInstance();
        }
        this.mCacheErrorLogger = cacheErrorLogger;
        if (builder.mCacheEventListener != null) {
            cacheEventListener = builder.mCacheEventListener;
        } else {
            cacheEventListener = NoOpCacheEventListener.getInstance();
        }
        this.mCacheEventListener = cacheEventListener;
        if (builder.mDiskTrimmableRegistry != null) {
            diskTrimmableRegistry = builder.mDiskTrimmableRegistry;
        } else {
            diskTrimmableRegistry = NoOpDiskTrimmableRegistry.getInstance();
        }
        this.mDiskTrimmableRegistry = diskTrimmableRegistry;
        this.mIndexPopulateAtStartupEnabled = builder.mIndexPopulateAtStartupEnabled;
    }

    public static Builder newBuilder(@Nullable Context context) {
        return new Builder(context);
    }

    public String getBaseDirectoryName() {
        return this.mBaseDirectoryName;
    }

    public Supplier<File> getBaseDirectoryPathSupplier() {
        return this.mBaseDirectoryPathSupplier;
    }

    public CacheErrorLogger getCacheErrorLogger() {
        return this.mCacheErrorLogger;
    }

    public CacheEventListener getCacheEventListener() {
        return this.mCacheEventListener;
    }

    @Nullable
    public Context getContext() {
        return this.mContext;
    }

    public long getDefaultSizeLimit() {
        return this.mDefaultSizeLimit;
    }

    public DiskTrimmableRegistry getDiskTrimmableRegistry() {
        return this.mDiskTrimmableRegistry;
    }

    public EntryEvictionComparatorSupplier getEntryEvictionComparatorSupplier() {
        return this.mEntryEvictionComparatorSupplier;
    }

    public boolean getIndexPopulateAtStartupEnabled() {
        return this.mIndexPopulateAtStartupEnabled;
    }

    public long getLowDiskSpaceSizeLimit() {
        return this.mLowDiskSpaceSizeLimit;
    }

    public long getMinimumSizeLimit() {
        return this.mMinimumSizeLimit;
    }

    public int getVersion() {
        return this.mVersion;
    }
}
