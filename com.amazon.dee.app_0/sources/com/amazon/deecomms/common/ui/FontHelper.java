package com.amazon.deecomms.common.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.font.FontResolver;
import com.amazon.deecomms.R;
import com.amazon.deecomms.common.Constants;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes12.dex */
public final class FontHelper {
    private static final Map<String, Font> fontNameMap = new HashMap();

    static {
        fontNameMap.put("fonts/AmazonEmber/Amazon_Ember_Bd.ttf", Font.AMAZON_EMBER_BOLD);
        fontNameMap.put("fonts/AmazonEmber/Amazon-Ember-Medium.ttf", Font.AMAZON_EMBER_MEDIUM);
        fontNameMap.put(Constants.AMAZON_EMBER_REGULAR_FONT, Font.AMAZON_EMBER_REGULAR);
        fontNameMap.put("fonts/AmazonEmber/Amazon_Ember_Lt.ttf", Font.AMAZON_EMBER_LIGHT);
    }

    private FontHelper() {
    }

    public static Typeface getTypefaceFromAttributes(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CommsTextView);
            String string = obtainStyledAttributes.getString(R.styleable.CommsTextView_typeface);
            obtainStyledAttributes.recycle();
            if (string != null && fontNameMap.containsKey(string)) {
                return FontResolver.getFont(context, fontNameMap.get(string));
            }
            return null;
        }
        return null;
    }
}
