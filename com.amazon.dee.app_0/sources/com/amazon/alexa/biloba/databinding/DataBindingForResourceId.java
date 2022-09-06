package com.amazon.alexa.biloba.databinding;

import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.BindingAdapter;
/* loaded from: classes6.dex */
public final class DataBindingForResourceId {
    private DataBindingForResourceId() {
    }

    @BindingAdapter({"resourceId"})
    public static void setImageResource(ImageView imageView, int i) {
        imageView.setImageResource(i);
    }

    @BindingAdapter({"resourceId"})
    public static void setTextResource(TextView textView, int i) {
        textView.setText(i);
    }
}
