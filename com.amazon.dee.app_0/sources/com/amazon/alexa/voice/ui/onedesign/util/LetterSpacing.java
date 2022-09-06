package com.amazon.alexa.voice.ui.onedesign.util;

import android.widget.TextView;
import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public final class LetterSpacing {
    private LetterSpacing() {
    }

    public static void apply(@DimenRes int i, @NonNull TextView... textViewArr) {
        for (TextView textView : textViewArr) {
            textView.setLetterSpacing(textView.getResources().getDimension(i) / textView.getTextSize());
        }
    }
}
