package com.amazon.alexa.voice.ui.onedesign.util;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.font.FontResolver;
import com.google.android.material.tabs.TabLayout;
/* loaded from: classes11.dex */
public final class FontUtils {
    private FontUtils() {
        throw new IllegalStateException("No instances!");
    }

    public static void apply(@NonNull Font font, @NonNull TextView textView) {
        applyTypeface(FontResolver.getFont(textView.getContext(), font), textView);
    }

    private static void applyToAllTextViews(@NonNull Typeface typeface, ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof ViewGroup) {
                applyToAllTextViews(typeface, (ViewGroup) childAt);
            } else if (childAt instanceof TextView) {
                applyTypeface(typeface, (TextView) childAt);
            }
        }
    }

    private static void applyTypeface(@NonNull Typeface typeface, @NonNull TextView textView) {
        textView.setTypeface(typeface);
    }

    @Nullable
    public static Font getFontFromTheme(@NonNull Context context, int i) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i, typedValue, true);
        if (typedValue.type == 0) {
            return null;
        }
        return Font.fromInt(typedValue.data);
    }

    public static void apply(@NonNull Font font, TextView... textViewArr) {
        for (TextView textView : textViewArr) {
            apply(font, textView);
        }
    }

    public static void apply(@NonNull Font font, @NonNull TabLayout tabLayout) {
        applyToAllTextViews(FontResolver.getFont(tabLayout.getContext(), font), tabLayout);
    }

    public static void apply(@NonNull Font font, @NonNull Context context, @NonNull Paint paint) {
        paint.setTypeface(FontResolver.getFont(context, font));
    }
}
