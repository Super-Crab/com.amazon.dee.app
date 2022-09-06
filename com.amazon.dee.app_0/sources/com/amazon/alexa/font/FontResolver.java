package com.amazon.alexa.font;

import android.content.Context;
import android.graphics.Typeface;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.mobile.heremapsexplore.Constants.Resources;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes8.dex */
public final class FontResolver {
    private static final String TAG = "FontResolver";
    @VisibleForTesting
    static final Map<Font, Typeface> TYPEFACE_CACHE = new HashMap();
    @VisibleForTesting
    static final Map<Font, Typeface> V2_TYPEFACE_CACHE = new HashMap();
    private static final Map<Font, String> assetFontPaths = new HashMap();
    private static final Map<Font, String> v2AssetFontPaths = new HashMap();
    private static final Map<Font, Font> v2FontMapping = new HashMap();
    @VisibleForTesting
    static Boolean v2FontFeatureEnabled = null;

    static {
        assetFontPaths.put(Font.AMAZON_BOOKERLY_BOLD, "fonts/bookerly-bold.ttf");
        assetFontPaths.put(Font.AMAZON_BOOKERLY_BOLD_ITALIC, "fonts/bookerly-bold_italic.ttf");
        assetFontPaths.put(Font.AMAZON_BOOKERLY_LIGHT, "fonts/bookerly-light.ttf");
        assetFontPaths.put(Font.AMAZON_BOOKERLY_LIGHT_ITALIC, "fonts/bookerly-light_italic.ttf");
        assetFontPaths.put(Font.AMAZON_BOOKERLY_REGULAR, "fonts/bookerly-regular.ttf");
        assetFontPaths.put(Font.AMAZON_BOOKERLY_REGULAR_ITALIC, "fonts/bookerly-regular_italic.ttf");
        assetFontPaths.put(Font.AMAZON_EMBER_BOLD, "fonts/ember-bold.ttf");
        assetFontPaths.put(Font.AMAZON_EMBER_BOLD_ITALIC, "fonts/ember-bold_italic.ttf");
        assetFontPaths.put(Font.AMAZON_EMBER_DISPLAY_BOLD, Resources.Fonts.AMAZON_EMBER_BOLD);
        assetFontPaths.put(Font.AMAZON_EMBER_DISPLAY_LIGHT, "fonts/amazon-ember-display-light.ttf");
        assetFontPaths.put(Font.AMAZON_EMBER_DISPLAY_MEDIUM, "fonts/amazon-ember-display-medium.ttf");
        assetFontPaths.put(Font.AMAZON_EMBER_DISPLAY_REGULAR, "fonts/amazon-ember-display-regular.ttf");
        assetFontPaths.put(Font.AMAZON_EMBER_LIGHT, "fonts/ember-light.ttf");
        assetFontPaths.put(Font.AMAZON_EMBER_LIGHT_ITALIC, "fonts/ember-light_italic.ttf");
        assetFontPaths.put(Font.AMAZON_EMBER_MEDIUM, "fonts/ember-medium.ttf");
        assetFontPaths.put(Font.AMAZON_EMBER_MEDIUM_ITALIC, "fonts/ember-medium_italic.ttf");
        assetFontPaths.put(Font.AMAZON_EMBER_REGULAR, "fonts/ember-regular.ttf");
        assetFontPaths.put(Font.AMAZON_EMBER_REGULAR_ITALIC, "fonts/ember-regular_italic.ttf");
        assetFontPaths.put(Font.AMAZON_EMBER_THIN, "fonts/ember-thin.ttf");
        assetFontPaths.put(Font.AMAZON_EMBER_THIN_ITALIC, "fonts/ember-thin_italic.ttf");
        v2AssetFontPaths.put(Font.AMAZON_EMBER_BOLD, "fonts/ember-bold-v2.ttf");
        v2AssetFontPaths.put(Font.AMAZON_EMBER_MEDIUM, "fonts/ember-medium-v2.ttf");
        v2AssetFontPaths.put(Font.AMAZON_EMBER_REGULAR, "fonts/ember-regular-v2.ttf");
        v2FontMapping.put(Font.AMAZON_BOOKERLY_BOLD, Font.AMAZON_EMBER_BOLD);
        v2FontMapping.put(Font.AMAZON_BOOKERLY_BOLD_ITALIC, Font.AMAZON_EMBER_BOLD);
        v2FontMapping.put(Font.AMAZON_BOOKERLY_LIGHT, Font.AMAZON_EMBER_REGULAR);
        v2FontMapping.put(Font.AMAZON_BOOKERLY_LIGHT_ITALIC, Font.AMAZON_EMBER_REGULAR);
        v2FontMapping.put(Font.AMAZON_BOOKERLY_REGULAR, Font.AMAZON_EMBER_REGULAR);
        v2FontMapping.put(Font.AMAZON_BOOKERLY_REGULAR_ITALIC, Font.AMAZON_EMBER_REGULAR);
        Map<Font, Font> map = v2FontMapping;
        Font font = Font.AMAZON_EMBER_BOLD;
        map.put(font, font);
        v2FontMapping.put(Font.AMAZON_EMBER_BOLD_ITALIC, Font.AMAZON_EMBER_BOLD);
        v2FontMapping.put(Font.AMAZON_EMBER_DISPLAY_BOLD, Font.AMAZON_EMBER_BOLD);
        v2FontMapping.put(Font.AMAZON_EMBER_DISPLAY_LIGHT, Font.AMAZON_EMBER_REGULAR);
        v2FontMapping.put(Font.AMAZON_EMBER_DISPLAY_MEDIUM, Font.AMAZON_EMBER_MEDIUM);
        v2FontMapping.put(Font.AMAZON_EMBER_DISPLAY_REGULAR, Font.AMAZON_EMBER_REGULAR);
        v2FontMapping.put(Font.AMAZON_EMBER_LIGHT, Font.AMAZON_EMBER_REGULAR);
        v2FontMapping.put(Font.AMAZON_EMBER_LIGHT_ITALIC, Font.AMAZON_EMBER_REGULAR);
        Map<Font, Font> map2 = v2FontMapping;
        Font font2 = Font.AMAZON_EMBER_MEDIUM;
        map2.put(font2, font2);
        v2FontMapping.put(Font.AMAZON_EMBER_MEDIUM_ITALIC, Font.AMAZON_EMBER_MEDIUM);
        Map<Font, Font> map3 = v2FontMapping;
        Font font3 = Font.AMAZON_EMBER_REGULAR;
        map3.put(font3, font3);
        v2FontMapping.put(Font.AMAZON_EMBER_REGULAR_ITALIC, Font.AMAZON_EMBER_REGULAR);
        v2FontMapping.put(Font.AMAZON_EMBER_THIN, Font.AMAZON_EMBER_REGULAR);
        v2FontMapping.put(Font.AMAZON_EMBER_THIN_ITALIC, Font.AMAZON_EMBER_REGULAR);
    }

    private FontResolver() {
    }

    @Nullable
    public static Typeface getFont(@NonNull Context context, @NonNull Font font) {
        Map<Font, Typeface> map;
        Map<Font, String> map2;
        if (isLocaleArabic(context)) {
            font = v2FontMapping.get(font);
            map = V2_TYPEFACE_CACHE;
            map2 = v2AssetFontPaths;
        } else {
            map = TYPEFACE_CACHE;
            map2 = assetFontPaths;
        }
        if (!map.containsKey(font)) {
            Typeface typeface = null;
            if (map2.containsKey(font)) {
                typeface = Typeface.createFromAsset(context.getAssets(), map2.get(font));
            }
            map.put(font, typeface);
        }
        return map.get(font);
    }

    public static boolean isLocaleArabic(@NonNull Context context) {
        try {
            return context.getResources().getConfiguration().locale.toString().startsWith("ar");
        } catch (NullPointerException unused) {
            return false;
        }
    }
}
