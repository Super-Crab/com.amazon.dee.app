package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.Util;
/* loaded from: classes6.dex */
public abstract class BitmapTransformation implements Transformation<Bitmap> {
    public BitmapTransformation() {
    }

    protected abstract Bitmap transform(@NonNull BitmapPool bitmapPool, @NonNull Bitmap bitmap, int i, int i2);

    @Override // com.bumptech.glide.load.Transformation
    @NonNull
    public final Resource<Bitmap> transform(@NonNull Context context, @NonNull Resource<Bitmap> resource, int i, int i2) {
        if (Util.isValidDimensions(i, i2)) {
            BitmapPool bitmapPool = Glide.get(context).getBitmapPool();
            Bitmap mo6789get = resource.mo6789get();
            if (i == Integer.MIN_VALUE) {
                i = mo6789get.getWidth();
            }
            if (i2 == Integer.MIN_VALUE) {
                i2 = mo6789get.getHeight();
            }
            Bitmap transform = transform(bitmapPool, mo6789get, i, i2);
            return mo6789get.equals(transform) ? resource : BitmapResource.obtain(transform, bitmapPool);
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline54("Cannot apply transformation on width: ", i, " or height: ", i2, " less than or equal to zero and not Target.SIZE_ORIGINAL"));
    }

    @Deprecated
    public BitmapTransformation(Context context) {
        this();
    }
}
