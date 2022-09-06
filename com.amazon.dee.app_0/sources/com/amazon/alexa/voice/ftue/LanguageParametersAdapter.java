package com.amazon.alexa.voice.ftue;

import com.amazon.alexa.voice.locale.LocaleParameters;
import com.amazon.alexa.voice.ui.onedesign.ftue.language.Language;
import com.amazon.alexa.voice.ui.onedesign.ftue.language.LanguagePrimerParameters;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
/* loaded from: classes11.dex */
public final class LanguageParametersAdapter {
    private LanguageParametersAdapter() {
    }

    private static Language getLanguage(Locale locale) {
        return new Language.Builder().country(locale.getCountry()).displayCountry(locale.getDisplayCountry(locale)).language(locale.getLanguage()).displayLanguage(locale.getDisplayLanguage(locale)).build();
    }

    public static LanguagePrimerParameters getLanguagePrimerParameters(LocaleParameters localeParameters) {
        return new LanguagePrimerParameters.Builder().currentLanguage(getLanguage(localeParameters.getPreselectedLocale())).availableLanguages(getLanguages(localeParameters.getSupportedLocales())).recommendedLanguages(getLanguages(localeParameters.getRecommendedLocales())).build();
    }

    private static ArrayList<Language> getLanguages(List<Locale> list) {
        ArrayList<Language> arrayList = new ArrayList<>();
        for (Locale locale : list) {
            arrayList.add(getLanguage(locale));
        }
        return arrayList;
    }
}
