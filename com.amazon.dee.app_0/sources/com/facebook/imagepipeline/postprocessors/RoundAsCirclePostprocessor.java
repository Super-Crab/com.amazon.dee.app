package com.facebook.imagepipeline.postprocessors;

import android.graphics.Bitmap;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.imagepipeline.nativecode.NativeRoundingFilter;
import com.facebook.imagepipeline.request.BasePostprocessor;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class RoundAsCirclePostprocessor extends BasePostprocessor {
    private static final boolean ENABLE_ANTI_ALIASING = true;
    @Nullable
    private CacheKey mCacheKey;
    private final boolean mEnableAntiAliasing;

    public RoundAsCirclePostprocessor() {
        this(true);
    }

    @Override // com.facebook.imagepipeline.request.BasePostprocessor, com.facebook.imagepipeline.request.Postprocessor
    @Nullable
    public CacheKey getPostprocessorCacheKey() {
        if (this.mCacheKey == null) {
            if (this.mEnableAntiAliasing) {
                this.mCacheKey = new SimpleCacheKey("RoundAsCirclePostprocessor#AntiAliased");
            } else {
                this.mCacheKey = new SimpleCacheKey("RoundAsCirclePostprocessor");
            }
        }
        return this.mCacheKey;
    }

    @Override // com.facebook.imagepipeline.request.BasePostprocessor
    public void process(Bitmap bitmap) {
        NativeRoundingFilter.toCircleFast(bitmap, this.mEnableAntiAliasing);
    }

    public RoundAsCirclePostprocessor(boolean enableAntiAliasing) {
        this.mEnableAntiAliasing = enableAntiAliasing;
    }
}
