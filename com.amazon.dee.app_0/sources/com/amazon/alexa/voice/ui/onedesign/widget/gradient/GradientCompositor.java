package com.amazon.alexa.voice.ui.onedesign.widget.gradient;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public interface GradientCompositor {
    Bitmap createBitmapWithBackgroundColor(@NonNull GradientModel gradientModel, int i);

    Bitmap createBitmapWithBackgroundColorAndDrawable(@NonNull GradientModel gradientModel, @NonNull GradientBackgroundModel gradientBackgroundModel);
}
