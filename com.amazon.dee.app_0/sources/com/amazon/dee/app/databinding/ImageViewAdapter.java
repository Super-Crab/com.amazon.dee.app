package com.amazon.dee.app.databinding;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;
import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import com.amazon.alexa.imageloader.GlideApp;
import com.amazon.dee.app.ui.view.InitialsDrawable;
import com.bumptech.glide.request.RequestOptions;
/* loaded from: classes12.dex */
public final class ImageViewAdapter {
    private ImageViewAdapter() {
    }

    @BindingAdapter(requireAll = false, value = {"imageUrl", "placeHolder"})
    public static void setImageUrl(ImageView imageView, Uri uri, Drawable drawable) {
        if (uri == null) {
            imageView.setImageDrawable(null);
        } else {
            GlideApp.with(imageView.getContext()).mo6758load(uri).mo1615apply(new RequestOptions().mo1602placeholder(drawable)).into(imageView);
        }
    }

    @BindingAdapter({"initials"})
    public static void setInitials(ImageView imageView, CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            imageView.setImageDrawable(null);
        } else {
            imageView.setImageDrawable(new InitialsDrawable(imageView.getContext(), Character.toString(charSequence.toString().charAt(0))));
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
