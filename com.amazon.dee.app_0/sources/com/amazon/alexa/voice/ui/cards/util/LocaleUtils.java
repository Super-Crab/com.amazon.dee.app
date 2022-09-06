package com.amazon.alexa.voice.ui.cards.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
/* loaded from: classes11.dex */
public final class LocaleUtils {
    private static final Set<String> RTL_SUPPORTED_LANGUAGES = new HashSet(Arrays.asList("ar"));

    private LocaleUtils() {
    }

    public static boolean isRtl(Locale locale) {
        return RTL_SUPPORTED_LANGUAGES.contains(locale.getLanguage());
    }
}
