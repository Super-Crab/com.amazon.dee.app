package com.amazon.alexa.handsfree.ui.utils;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.font.FontResolver;
import java.util.EnumMap;
/* loaded from: classes8.dex */
public final class FontAdapter {
    private static final EnumMap<FontName, Font> FONT_PATH_MAP = new EnumMap<FontName, Font>(FontName.class) { // from class: com.amazon.alexa.handsfree.ui.utils.FontAdapter.1
        {
            put((AnonymousClass1) FontName.AmazonEmber_Bold, (FontName) Font.AMAZON_EMBER_BOLD);
            put((AnonymousClass1) FontName.AmazonEmber_Light, (FontName) Font.AMAZON_EMBER_LIGHT);
            put((AnonymousClass1) FontName.AmazonEmber_Medium, (FontName) Font.AMAZON_EMBER_MEDIUM);
            put((AnonymousClass1) FontName.AmazonEmber_Regular, (FontName) Font.AMAZON_EMBER_REGULAR);
            put((AnonymousClass1) FontName.AmazonBookerly_Italic, (FontName) Font.AMAZON_BOOKERLY_REGULAR_ITALIC);
            put((AnonymousClass1) FontName.AmazonBookerly_Light_Italic, (FontName) Font.AMAZON_BOOKERLY_LIGHT_ITALIC);
            put((AnonymousClass1) FontName.AmazonEmberDisplay_Bold, (FontName) Font.AMAZON_EMBER_DISPLAY_BOLD);
            put((AnonymousClass1) FontName.AmazonEmberDisplay_Light, (FontName) Font.AMAZON_EMBER_DISPLAY_LIGHT);
        }
    };

    /* loaded from: classes8.dex */
    public enum FontName {
        AmazonEmber_Bold,
        AmazonEmber_Light,
        AmazonEmber_Medium,
        AmazonEmber_Regular,
        AmazonBookerly_Italic,
        AmazonBookerly_Light_Italic,
        AmazonEmberDisplay_Bold,
        AmazonEmberDisplay_Light
    }

    private FontAdapter() {
    }

    public static void setFont(@NonNull FontName fontName, @NonNull View... viewArr) {
        for (View view : viewArr) {
            if (view instanceof TextView) {
                ((TextView) view).setTypeface(FontResolver.getFont(view.getContext(), FONT_PATH_MAP.get(fontName)));
            }
        }
    }
}
