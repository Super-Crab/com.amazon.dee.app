package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.Preconditions;
import java.security.MessageDigest;
@Deprecated
/* loaded from: classes6.dex */
public class BitmapDrawableTransformation implements Transformation<BitmapDrawable> {
    private final Transformation<Drawable> wrapped;

    public BitmapDrawableTransformation(Transformation<Bitmap> transformation) {
        this.wrapped = (Transformation) Preconditions.checkNotNull(new DrawableTransformation(transformation, false));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static Resource<BitmapDrawable> convertToBitmapDrawableResource(Resource<Drawable> resource) {
        if (resource.mo6789get() instanceof BitmapDrawable) {
            return resource;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Wrapped transformation unexpectedly returned a non BitmapDrawable resource: ");
        outline107.append(resource.mo6789get());
        throw new IllegalArgumentException(outline107.toString());
    }

    private static Resource<Drawable> convertToDrawableResource(Resource<BitmapDrawable> resource) {
        return resource;
    }

    @Override // com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        if (obj instanceof BitmapDrawableTransformation) {
            return this.wrapped.equals(((BitmapDrawableTransformation) obj).wrapped);
        }
        return false;
    }

    @Override // com.bumptech.glide.load.Key
    public int hashCode() {
        return this.wrapped.hashCode();
    }

    @Override // com.bumptech.glide.load.Transformation
    @NonNull
    public Resource<BitmapDrawable> transform(@NonNull Context context, @NonNull Resource<BitmapDrawable> resource, int i, int i2) {
        return convertToBitmapDrawableResource(this.wrapped.transform(context, convertToDrawableResource(resource), i, i2));
    }

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        this.wrapped.updateDiskCacheKey(messageDigest);
    }

    @Deprecated
    public BitmapDrawableTransformation(Context context, Transformation<Bitmap> transformation) {
        this(transformation);
    }

    @Deprecated
    public BitmapDrawableTransformation(Context context, BitmapPool bitmapPool, Transformation<Bitmap> transformation) {
        this(transformation);
    }
}
