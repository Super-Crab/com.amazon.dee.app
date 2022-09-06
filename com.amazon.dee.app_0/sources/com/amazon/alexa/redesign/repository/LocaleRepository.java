package com.amazon.alexa.redesign.repository;

import android.content.res.Resources;
import androidx.core.os.ConfigurationCompat;
import java.util.Locale;
/* loaded from: classes10.dex */
public class LocaleRepository {
    private static Locale getCurrentLocale() {
        return ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration()).get(0);
    }

    public static boolean isEnglish() {
        return getCurrentLocale().getLanguage().equals(Locale.ENGLISH.getLanguage());
    }

    private boolean isUsLocale(Locale locale) {
        return locale != null && locale.toString().equalsIgnoreCase(Locale.US.toString());
    }

    public boolean isUsLocale() {
        return isUsLocale(getCurrentLocale());
    }
}
