package com.amazon.alexa.mode.util;

import android.graphics.Typeface;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.font.FontResolver;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes9.dex */
public enum Fonts {
    EMBER_BOLD("ember-bold"),
    EMBER_BOLD_ITALIC("ember-bold_italic"),
    EMBER_LIGHT("ember-light"),
    EMBER_MEDIUM("ember-medium"),
    EMBER_REGULAR("ember-regular"),
    EMBER_DISPLAY_BOLD("ember-display-bold"),
    EMBER_DISPLAY_LIGHT("ember-display-light"),
    BOOKERLY_ITALIC("bookerly-italic"),
    BOOKERLY_REGULAR("bookerly-regular");
    
    private static final String TAG = "ModeFonts";
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
    }

    Fonts(String str) {
        this.name = str;
    }

    public void apply(@NonNull TextView textView) {
        try {
            if (this.font == null) {
                this.font = FontResolver.getFont(textView.getContext(), fontNameMap.get(this.name));
            }
            textView.setTypeface(this.font);
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline156("Apply fonts error:", e, TAG);
        }
    }

    public void apply(TextView... textViewArr) {
        for (TextView textView : textViewArr) {
            apply(textView);
        }
    }
}
