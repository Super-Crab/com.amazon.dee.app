package com.amazon.alexa.voice.ui.onedesign.widget.gradient;

import android.graphics.drawable.Drawable;
import androidx.annotation.ColorInt;
import com.amazon.alexa.voice.ui.annotation.UiModel;
@UiModel(builder = true)
/* loaded from: classes11.dex */
public interface GradientBackgroundModel {
    @ColorInt
    int getColor();

    Drawable getDrawable();
}
