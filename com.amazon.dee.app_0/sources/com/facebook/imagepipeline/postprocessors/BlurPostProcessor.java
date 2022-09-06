package com.facebook.imagepipeline.postprocessors;

import android.content.Context;
import android.graphics.Bitmap;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.filter.IterativeBoxBlurFilter;
import com.facebook.imagepipeline.filter.RenderScriptBlurFilter;
import com.facebook.imagepipeline.request.BasePostprocessor;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class BlurPostProcessor extends BasePostprocessor {
    private static final int DEFAULT_ITERATIONS = 3;
    private static final boolean canUseRenderScript = RenderScriptBlurFilter.canUseRenderScript();
    private final int mBlurRadius;
    @Nullable
    private CacheKey mCacheKey;
    private final Context mContext;
    private final int mIterations;

    public BlurPostProcessor(final int blurRadius, final Context context, final int iterations) {
        boolean z = true;
        Preconditions.checkArgument(Boolean.valueOf(blurRadius > 0 && blurRadius <= 25));
        Preconditions.checkArgument(Boolean.valueOf(iterations <= 0 ? false : z));
        Preconditions.checkNotNull(context);
        this.mIterations = iterations;
        this.mBlurRadius = blurRadius;
        this.mContext = context;
    }

    @Override // com.facebook.imagepipeline.request.BasePostprocessor, com.facebook.imagepipeline.request.Postprocessor
    @Nullable
    public CacheKey getPostprocessorCacheKey() {
        if (this.mCacheKey == null) {
            this.mCacheKey = new SimpleCacheKey(canUseRenderScript ? String.format(null, "IntrinsicBlur;%d", Integer.valueOf(this.mBlurRadius)) : String.format(null, "IterativeBoxBlur;%d;%d", Integer.valueOf(this.mIterations), Integer.valueOf(this.mBlurRadius)));
        }
        return this.mCacheKey;
    }

    @Override // com.facebook.imagepipeline.request.BasePostprocessor
    public void process(final Bitmap destBitmap, final Bitmap sourceBitmap) {
        if (canUseRenderScript) {
            RenderScriptBlurFilter.blurBitmap(destBitmap, sourceBitmap, this.mContext, this.mBlurRadius);
        } else {
            super.process(destBitmap, sourceBitmap);
        }
    }

    @Override // com.facebook.imagepipeline.request.BasePostprocessor
    public void process(final Bitmap bitmap) {
        IterativeBoxBlurFilter.boxBlurBitmapInPlace(bitmap, this.mIterations, this.mBlurRadius);
    }

    public BlurPostProcessor(final int blurRadius, final Context context) {
        this(blurRadius, context, 3);
    }
}
