package com.facebook.drawee.backends.pipeline;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.facebook.drawee.drawable.OrientedDrawable;
import com.facebook.imagepipeline.drawable.DrawableFactory;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class DefaultDrawableFactory implements DrawableFactory {
    @Nullable
    private final DrawableFactory mAnimatedDrawableFactory;
    private final Resources mResources;

    public DefaultDrawableFactory(Resources resources, @Nullable DrawableFactory animatedDrawableFactory) {
        this.mResources = resources;
        this.mAnimatedDrawableFactory = animatedDrawableFactory;
    }

    private static boolean hasTransformableExifOrientation(CloseableStaticBitmap closeableStaticBitmap) {
        return (closeableStaticBitmap.getExifOrientation() == 1 || closeableStaticBitmap.getExifOrientation() == 0) ? false : true;
    }

    private static boolean hasTransformableRotationAngle(CloseableStaticBitmap closeableStaticBitmap) {
        return (closeableStaticBitmap.getRotationAngle() == 0 || closeableStaticBitmap.getRotationAngle() == -1) ? false : true;
    }

    @Override // com.facebook.imagepipeline.drawable.DrawableFactory
    @Nullable
    public Drawable createDrawable(CloseableImage closeableImage) {
        try {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("DefaultDrawableFactory#createDrawable");
            }
            if (closeableImage instanceof CloseableStaticBitmap) {
                CloseableStaticBitmap closeableStaticBitmap = (CloseableStaticBitmap) closeableImage;
                BitmapDrawable bitmapDrawable = new BitmapDrawable(this.mResources, closeableStaticBitmap.getUnderlyingBitmap());
                if (!hasTransformableRotationAngle(closeableStaticBitmap) && !hasTransformableExifOrientation(closeableStaticBitmap)) {
                    return bitmapDrawable;
                }
                OrientedDrawable orientedDrawable = new OrientedDrawable(bitmapDrawable, closeableStaticBitmap.getRotationAngle(), closeableStaticBitmap.getExifOrientation());
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return orientedDrawable;
            } else if (this.mAnimatedDrawableFactory != null && this.mAnimatedDrawableFactory.supportsImageType(closeableImage)) {
                Drawable createDrawable = this.mAnimatedDrawableFactory.createDrawable(closeableImage);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return createDrawable;
            } else {
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return null;
            }
        } finally {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
        }
    }

    @Override // com.facebook.imagepipeline.drawable.DrawableFactory
    public boolean supportsImageType(CloseableImage image) {
        return true;
    }
}
