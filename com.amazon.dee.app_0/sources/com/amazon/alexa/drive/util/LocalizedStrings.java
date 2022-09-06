package com.amazon.alexa.drive.util;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
/* loaded from: classes7.dex */
public final class LocalizedStrings {
    private static final String[] sDriveModeSupportedLocales = {"de", "en", "en-rAU", "en-rCA", "en-rIN", "es", "es-rMX", "es-rUS", "fr", "fr-rCA", "it", "ja", "pt-rBR"};

    private LocalizedStrings() {
        throw new IllegalStateException("No instances!");
    }

    private static Resources getLocalizedResources(Context context, Locale locale) {
        Configuration configuration = new Configuration(context.getResources().getConfiguration());
        configuration.setLocale(locale);
        return context.createConfigurationContext(configuration).getResources();
    }

    public static List<String> getLocalizedStringList(Context context, int i) {
        ArrayList arrayList = new ArrayList();
        for (String str : sDriveModeSupportedLocales) {
            arrayList.add(getLocalizedResources(context, Locale.forLanguageTag(str)).getString(i));
        }
        return arrayList;
    }
}
