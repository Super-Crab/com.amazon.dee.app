package com.facebook.imagepipeline.memory;
/* loaded from: classes2.dex */
public class BitmapCounterConfig {
    public static final int DEFAULT_MAX_BITMAP_COUNT = 384;
    private int mMaxBitmapCount;

    /* loaded from: classes2.dex */
    public static class Builder {
        private int mMaxBitmapCount;

        public BitmapCounterConfig build() {
            return new BitmapCounterConfig(this);
        }

        public int getMaxBitmapCount() {
            return this.mMaxBitmapCount;
        }

        public Builder setMaxBitmapCount(int maxBitmapCount) {
            this.mMaxBitmapCount = maxBitmapCount;
            return this;
        }

        private Builder() {
            this.mMaxBitmapCount = BitmapCounterConfig.DEFAULT_MAX_BITMAP_COUNT;
        }
    }

    public BitmapCounterConfig(Builder builder) {
        this.mMaxBitmapCount = DEFAULT_MAX_BITMAP_COUNT;
        this.mMaxBitmapCount = builder.getMaxBitmapCount();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public int getMaxBitmapCount() {
        return this.mMaxBitmapCount;
    }

    public void setMaxBitmapCount(int maxBitmapCount) {
        this.mMaxBitmapCount = maxBitmapCount;
    }
}
