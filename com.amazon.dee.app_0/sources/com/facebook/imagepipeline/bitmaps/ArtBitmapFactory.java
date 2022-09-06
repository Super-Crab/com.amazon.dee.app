package com.facebook.imagepipeline.bitmaps;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.core.CloseableReferenceFactory;
import com.facebook.imagepipeline.memory.BitmapPool;
import com.facebook.imageutils.BitmapUtil;
import javax.annotation.concurrent.ThreadSafe;
@ThreadSafe
@TargetApi(21)
/* loaded from: classes2.dex */
public class ArtBitmapFactory extends PlatformBitmapFactory {
    private final BitmapPool mBitmapPool;
    private final CloseableReferenceFactory mCloseableReferenceFactory;

    public ArtBitmapFactory(BitmapPool bitmapPool, CloseableReferenceFactory closeableReferenceFactory) {
        this.mBitmapPool = bitmapPool;
        this.mCloseableReferenceFactory = closeableReferenceFactory;
    }

    @Override // com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory
    public CloseableReference<Bitmap> createBitmapInternal(int width, int height, Bitmap.Config bitmapConfig) {
        Bitmap mo6905get = this.mBitmapPool.mo6905get(BitmapUtil.getSizeInByteForBitmap(width, height, bitmapConfig));
        Preconditions.checkArgument(Boolean.valueOf(mo6905get.getAllocationByteCount() >= BitmapUtil.getPixelSizeForBitmapConfig(bitmapConfig) * (width * height)));
        mo6905get.reconfigure(width, height, bitmapConfig);
        return this.mCloseableReferenceFactory.create(mo6905get, this.mBitmapPool);
    }
}
