package com.bumptech.glide.request.target;

import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.Nullable;
/* loaded from: classes9.dex */
public abstract class ThumbnailImageViewTarget<T> extends ImageViewTarget<T> {
    public ThumbnailImageViewTarget(ImageView imageView) {
        super(imageView);
    }

    protected abstract Drawable getDrawable(T t);

    @Override // com.bumptech.glide.request.target.ImageViewTarget
    protected void setResource(@Nullable T t) {
        int i;
        int i2;
        ViewGroup.LayoutParams layoutParams = ((ImageView) this.view).getLayoutParams();
        Drawable drawable = getDrawable(t);
        if (layoutParams != null && (i = layoutParams.width) > 0 && (i2 = layoutParams.height) > 0) {
            drawable = new FixedSizeDrawable(drawable, i, i2);
        }
        ((ImageView) this.view).setImageDrawable(drawable);
    }

    @Deprecated
    public ThumbnailImageViewTarget(ImageView imageView, boolean z) {
        super(imageView, z);
    }
}
