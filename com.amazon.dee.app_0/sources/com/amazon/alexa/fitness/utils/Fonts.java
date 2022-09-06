package com.amazon.alexa.fitness.utils;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.font.FontResolver;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes8.dex */
public enum Fonts {
    EMBER_BOLD("ember-bold"),
    EMBER_LIGHT("ember-light"),
    EMBER_MEDIUM("ember-medium"),
    EMBER_REGULAR("ember-regular"),
    EMBER_DISPLAY_BOLD("ember-display-bold"),
    EMBER_DISPLAY_LIGHT("ember-display-light"),
    EMBER_BOLD_ITALIC("ember-bold_italic"),
    BOOKERLY_ITALIC("bookerly-italic"),
    BOOKERLY_REGULAR("bookerly-regular"),
    BOOKERLY_REGULAR_ITALIC("bookerly-regular_italic");
    
    private static final String TAG = "AlexaMobileFitnessFonts";
    private static Map<String, Font> fontNameMap = new HashMap();
    private Typeface font;
    private final String name;

    static {
        fontNameMap.put("ember-bold", Font.AMAZON_EMBER_BOLD);
        fontNameMap.put("ember-bold_italic", Font.AMAZON_EMBER_BOLD_ITALIC);
        fontNameMap.put("ember-light", Font.AMAZON_EMBER_LIGHT);
        fontNameMap.put("ember-medium", Font.AMAZON_EMBER_MEDIUM);
        fontNameMap.put("ember-regular", Font.AMAZON_EMBER_REGULAR);
        fontNameMap.put("ember-display-bold", Font.AMAZON_EMBER_DISPLAY_BOLD);
        fontNameMap.put("ember-display-light", Font.AMAZON_EMBER_DISPLAY_LIGHT);
        fontNameMap.put("bookerly-italic", Font.AMAZON_BOOKERLY_REGULAR_ITALIC);
        fontNameMap.put("bookerly-regular", Font.AMAZON_BOOKERLY_REGULAR);
        fontNameMap.put("bookerly-regular_italic", Font.AMAZON_BOOKERLY_REGULAR_ITALIC);
    }

    Fonts(String str) {
        this.name = str;
    }

    private void applyToAllTextViews(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof ViewGroup) {
                applyToAllTextViews((ViewGroup) childAt);
            } else if (childAt instanceof TextView) {
                apply((TextView) childAt);
            }
        }
    }

    private Typeface getFont(Context context) {
        try {
            if (this.font == null) {
                this.font = FontResolver.getFont(context, fontNameMap.get(this.name));
            }
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline156("Get fonts error:", e, TAG);
        }
        return this.font;
    }

    public void apply(@NonNull TextView textView) {
        try {
            textView.setTypeface(getFont(textView.getContext()));
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline156("Apply fonts error:", e, TAG);
        }
    }

    public void apply(TextView... textViewArr) {
        for (TextView textView : textViewArr) {
            apply(textView);
        }
    }

    public void apply(@NonNull Context context, @NonNull Paint paint) {
        try {
            paint.setTypeface(getFont(context));
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline156("Apply fonts error:", e, TAG);
        }
    }
}
