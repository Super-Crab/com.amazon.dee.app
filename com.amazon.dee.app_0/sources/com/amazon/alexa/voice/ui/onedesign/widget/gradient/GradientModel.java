package com.amazon.alexa.voice.ui.onedesign.widget.gradient;

import androidx.annotation.ColorInt;
import com.amazon.alexa.voice.ui.annotation.UiModel;
@UiModel(builder = true)
/* loaded from: classes11.dex */
public interface GradientModel {
    @ColorInt
    int getBottomColor();

    int getHeight();

    @ColorInt
    int getTopColor();

    int getWidth();
}
