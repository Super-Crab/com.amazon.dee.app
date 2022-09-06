package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.memory.NoOpMemoryTrimmableRegistry;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes2.dex */
public class PoolConfig {
    public static final int BITMAP_POOL_MAX_BITMAP_SIZE_DEFAULT = 4194304;
    private final int mBitmapPoolMaxBitmapSize;
    private final int mBitmapPoolMaxPoolSize;
    private final PoolParams mBitmapPoolParams;
    private final PoolStatsTracker mBitmapPoolStatsTracker;
    private final String mBitmapPoolType;
    private final PoolParams mFlexByteArrayPoolParams;
    private final boolean mIgnoreBitmapPoolHardCap;
    private final PoolParams mMemoryChunkPoolParams;
    private final PoolStatsTracker mMemoryChunkPoolStatsTracker;
    private final MemoryTrimmableRegistry mMemoryTrimmableRegistry;
    private final boolean mRegisterLruBitmapPoolAsMemoryTrimmable;
    private final PoolParams mSmallByteArrayPoolParams;
    private final PoolStatsTracker mSmallByteArrayPoolStatsTracker;

    /* loaded from: classes2.dex */
    public static class Builder {
        private int mBitmapPoolMaxBitmapSize;
        private int mBitmapPoolMaxPoolSize;
        @Nullable
        private PoolParams mBitmapPoolParams;
        @Nullable
        private PoolStatsTracker mBitmapPoolStatsTracker;
        @Nullable
        private String mBitmapPoolType;
        @Nullable
        private PoolParams mFlexByteArrayPoolParams;
        public boolean mIgnoreBitmapPoolHardCap;
        @Nullable
        private PoolParams mMemoryChunkPoolParams;
        @Nullable
        private PoolStatsTracker mMemoryChunkPoolStatsTracker;
        @Nullable
        private MemoryTrimmableRegistry mMemoryTrimmableRegistry;
        private boolean mRegisterLruBitmapPoolAsMemoryTrimmable;
        @Nullable
        private PoolParams mSmallByteArrayPoolParams;
        @Nullable
        private PoolStatsTracker mSmallByteArrayPoolStatsTracker;

        public PoolConfig build() {
            return new PoolConfig(this);
        }

        public Builder setBitmapPoolMaxBitmapSize(int bitmapPoolMaxBitmapSize) {
            this.mBitmapPoolMaxBitmapSize = bitmapPoolMaxBitmapSize;
            return this;
        }

        public Builder setBitmapPoolMaxPoolSize(int bitmapPoolMaxPoolSize) {
            this.mBitmapPoolMaxPoolSize = bitmapPoolMaxPoolSize;
            return this;
        }

        public Builder setBitmapPoolParams(PoolParams bitmapPoolParams) {
            this.mBitmapPoolParams = (PoolParams) Preconditions.checkNotNull(bitmapPoolParams);
            return this;
        }

        public Builder setBitmapPoolStatsTracker(PoolStatsTracker bitmapPoolStatsTracker) {
            this.mBitmapPoolStatsTracker = (PoolStatsTracker) Preconditions.checkNotNull(bitmapPoolStatsTracker);
            return this;
        }

        public Builder setBitmapPoolType(String bitmapPoolType) {
            this.mBitmapPoolType = bitmapPoolType;
            return this;
        }

        public Builder setFlexByteArrayPoolParams(PoolParams flexByteArrayPoolParams) {
            this.mFlexByteArrayPoolParams = flexByteArrayPoolParams;
            return this;
        }

        public Builder setIgnoreBitmapPoolHardCap(boolean ignoreBitmapPoolHardCap) {
            this.mIgnoreBitmapPoolHardCap = ignoreBitmapPoolHardCap;
            return this;
        }

        public Builder setMemoryTrimmableRegistry(MemoryTrimmableRegistry memoryTrimmableRegistry) {
            this.mMemoryTrimmableRegistry = memoryTrimmableRegistry;
            return this;
        }

        public Builder setNativeMemoryChunkPoolParams(PoolParams memoryChunkPoolParams) {
            this.mMemoryChunkPoolParams = (PoolParams) Preconditions.checkNotNull(memoryChunkPoolParams);
            return this;
        }

        public Builder setNativeMemoryChunkPoolStatsTracker(PoolStatsTracker memoryChunkPoolStatsTracker) {
            this.mMemoryChunkPoolStatsTracker = (PoolStatsTracker) Preconditions.checkNotNull(memoryChunkPoolStatsTracker);
            return this;
        }

        public Builder setRegisterLruBitmapPoolAsMemoryTrimmable(boolean registerLruBitmapPoolAsMemoryTrimmable) {
            this.mRegisterLruBitmapPoolAsMemoryTrimmable = registerLruBitmapPoolAsMemoryTrimmable;
            return this;
        }

        public Builder setSmallByteArrayPoolParams(PoolParams commonByteArrayPoolParams) {
            this.mSmallByteArrayPoolParams = (PoolParams) Preconditions.checkNotNull(commonByteArrayPoolParams);
            return this;
        }

        public Builder setSmallByteArrayPoolStatsTracker(PoolStatsTracker smallByteArrayPoolStatsTracker) {
            this.mSmallByteArrayPoolStatsTracker = (PoolStatsTracker) Preconditions.checkNotNull(smallByteArrayPoolStatsTracker);
            return this;
        }

        private Builder() {
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public int getBitmapPoolMaxBitmapSize() {
        return this.mBitmapPoolMaxBitmapSize;
    }

    public int getBitmapPoolMaxPoolSize() {
        return this.mBitmapPoolMaxPoolSize;
    }

    public PoolParams getBitmapPoolParams() {
        return this.mBitmapPoolParams;
    }

    public PoolStatsTracker getBitmapPoolStatsTracker() {
        return this.mBitmapPoolStatsTracker;
    }

    public String getBitmapPoolType() {
        return this.mBitmapPoolType;
    }

    public PoolParams getFlexByteArrayPoolParams() {
        return this.mFlexByteArrayPoolParams;
    }

    public PoolParams getMemoryChunkPoolParams() {
        return this.mMemoryChunkPoolParams;
    }

    public PoolStatsTracker getMemoryChunkPoolStatsTracker() {
        return this.mMemoryChunkPoolStatsTracker;
    }

    public MemoryTrimmableRegistry getMemoryTrimmableRegistry() {
        return this.mMemoryTrimmableRegistry;
    }

    public PoolParams getSmallByteArrayPoolParams() {
        return this.mSmallByteArrayPoolParams;
    }

    public PoolStatsTracker getSmallByteArrayPoolStatsTracker() {
        return this.mSmallByteArrayPoolStatsTracker;
    }

    public boolean isIgnoreBitmapPoolHardCap() {
        return this.mIgnoreBitmapPoolHardCap;
    }

    public boolean isRegisterLruBitmapPoolAsMemoryTrimmable() {
        return this.mRegisterLruBitmapPoolAsMemoryTrimmable;
    }

    private PoolConfig(Builder builder) {
        PoolParams poolParams;
        PoolStatsTracker poolStatsTracker;
        PoolParams poolParams2;
        MemoryTrimmableRegistry memoryTrimmableRegistry;
        PoolParams poolParams3;
        PoolStatsTracker poolStatsTracker2;
        PoolParams poolParams4;
        PoolStatsTracker poolStatsTracker3;
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("PoolConfig()");
        }
        if (builder.mBitmapPoolParams != null) {
            poolParams = builder.mBitmapPoolParams;
        } else {
            poolParams = DefaultBitmapPoolParams.get();
        }
        this.mBitmapPoolParams = poolParams;
        if (builder.mBitmapPoolStatsTracker != null) {
            poolStatsTracker = builder.mBitmapPoolStatsTracker;
        } else {
            poolStatsTracker = NoOpPoolStatsTracker.getInstance();
        }
        this.mBitmapPoolStatsTracker = poolStatsTracker;
        if (builder.mFlexByteArrayPoolParams != null) {
            poolParams2 = builder.mFlexByteArrayPoolParams;
        } else {
            poolParams2 = DefaultFlexByteArrayPoolParams.get();
        }
        this.mFlexByteArrayPoolParams = poolParams2;
        if (builder.mMemoryTrimmableRegistry != null) {
            memoryTrimmableRegistry = builder.mMemoryTrimmableRegistry;
        } else {
            memoryTrimmableRegistry = NoOpMemoryTrimmableRegistry.getInstance();
        }
        this.mMemoryTrimmableRegistry = memoryTrimmableRegistry;
        if (builder.mMemoryChunkPoolParams != null) {
            poolParams3 = builder.mMemoryChunkPoolParams;
        } else {
            poolParams3 = DefaultNativeMemoryChunkPoolParams.get();
        }
        this.mMemoryChunkPoolParams = poolParams3;
        if (builder.mMemoryChunkPoolStatsTracker != null) {
            poolStatsTracker2 = builder.mMemoryChunkPoolStatsTracker;
        } else {
            poolStatsTracker2 = NoOpPoolStatsTracker.getInstance();
        }
        this.mMemoryChunkPoolStatsTracker = poolStatsTracker2;
        if (builder.mSmallByteArrayPoolParams != null) {
            poolParams4 = builder.mSmallByteArrayPoolParams;
        } else {
            poolParams4 = DefaultByteArrayPoolParams.get();
        }
        this.mSmallByteArrayPoolParams = poolParams4;
        if (builder.mSmallByteArrayPoolStatsTracker != null) {
            poolStatsTracker3 = builder.mSmallByteArrayPoolStatsTracker;
        } else {
            poolStatsTracker3 = NoOpPoolStatsTracker.getInstance();
        }
        this.mSmallByteArrayPoolStatsTracker = poolStatsTracker3;
        this.mBitmapPoolType = builder.mBitmapPoolType == null ? "legacy" : builder.mBitmapPoolType;
        this.mBitmapPoolMaxPoolSize = builder.mBitmapPoolMaxPoolSize;
        this.mBitmapPoolMaxBitmapSize = builder.mBitmapPoolMaxBitmapSize > 0 ? builder.mBitmapPoolMaxBitmapSize : 4194304;
        this.mRegisterLruBitmapPoolAsMemoryTrimmable = builder.mRegisterLruBitmapPoolAsMemoryTrimmable;
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
        this.mIgnoreBitmapPoolHardCap = builder.mIgnoreBitmapPoolHardCap;
    }
}
