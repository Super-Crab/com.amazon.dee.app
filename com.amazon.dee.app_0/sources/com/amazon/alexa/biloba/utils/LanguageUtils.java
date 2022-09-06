package com.amazon.alexa.biloba.utils;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
/* loaded from: classes6.dex */
public final class LanguageUtils {
    private static final String TAG = "LanguageUtils";
    private static final Set<Locale> SUPPORTED_LOCALES = ImmutableSet.of(new Locale("en", "US"), new Locale("es", "US"));
    private static final Map<String, Locale> FALLBACK_LANG_TAG_LOCALE = ImmutableMap.of("en", new Locale("en", "US"), "eng", new Locale("en", "US"), "es", new Locale("es", "US"), "spa", new Locale("es", "US"));

    private LanguageUtils() {
    }

    public static Locale getAcceptableLanguageTag(Locale locale) {
        if (SUPPORTED_LOCALES.contains(locale)) {
            LogUtils.i(TAG, String.format("Using supported locale %s ", locale));
            return locale;
        }
        Locale locale2 = FALLBACK_LANG_TAG_LOCALE.get(locale.getLanguage());
        if (FALLBACK_LANG_TAG_LOCALE.containsKey(locale.getLanguage())) {
            LogUtils.i(TAG, String.format("Falling back to language %s, and locale %s", locale.getLanguage(), locale2));
            return locale2;
        }
        LogUtils.i(TAG, String.format("Falling back to American English because locale %s (or language) is not supported", locale));
        return Locale.US;
    }
}
