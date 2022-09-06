package com.amazon.alexa.biloba.databinding;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import com.amazon.alexa.imageloader.api.ImageLoader;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.net.URI;
/* loaded from: classes6.dex */
public final class ImageViewAdapter {
    private ImageViewAdapter() {
    }

    @BindingAdapter({"imageUrl"})
    public static void setImageUrl(ImageView imageView, String str) {
        if (str == null) {
            imageView.setImageDrawable(null);
        } else {
            ((ImageLoader) GeneratedOutlineSupport1.outline20(ImageLoader.class)).loadInto(URI.create(str), imageView);
        }
    }

    @BindingAdapter({"android:src"})
    public static void setSrc(ImageView imageView, Drawable drawable) {
        if (drawable == null) {
            imageView.setImageDrawable(null);
        } else {
            imageView.setImageDrawable(drawable);
        }
    }

    @BindingAdapter({"android:src"})
    public static void setSrc(ImageView imageView, @DrawableRes int i) {
        if (i == 0) {
            imageView.setImageDrawable(null);
        } else {
            imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(), i));
        }
    }
}
