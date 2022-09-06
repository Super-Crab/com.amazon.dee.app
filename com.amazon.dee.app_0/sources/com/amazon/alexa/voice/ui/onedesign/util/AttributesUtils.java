package com.amazon.alexa.voice.ui.onedesign.util;

import android.content.Context;
import android.util.TypedValue;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;
/* loaded from: classes11.dex */
public final class AttributesUtils {
    private AttributesUtils() {
        throw new IllegalStateException("No instances!");
    }

    @ColorInt
    public static int getAttributesColor(Context context, int i, @ColorRes int i2) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i, typedValue, true);
        return context.obtainStyledAttributes(typedValue.data, new int[]{i}).getColor(0, ContextCompat.getColor(context, i2));
    }
}
