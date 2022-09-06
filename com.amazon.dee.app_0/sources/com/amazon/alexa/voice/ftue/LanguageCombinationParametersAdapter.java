package com.amazon.alexa.voice.ftue;

import com.amazon.alexa.voice.locale.LocaleCombinationParameters;
import com.amazon.alexa.voice.ui.onedesign.ftue.language.Language;
import com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LanguageCombinationPrimerParameters;
import com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LanguageGroup;
import com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LocaleGroup;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
/* loaded from: classes11.dex */
public final class LanguageCombinationParametersAdapter {
    private LanguageCombinationParametersAdapter() {
    }

    private static LanguageGroup getLanguageGroup(List<Locale> list) {
        ArrayList arrayList = new ArrayList();
        for (Locale locale : list) {
            arrayList.add(getLanguageModel(locale));
        }
        return new LanguageGroup(arrayList);
    }

    private static Language getLanguageModel(Locale locale) {
        return new Language.Builder().country(locale.getCountry()).displayCountry(locale.getDisplayCountry(locale)).language(locale.getLanguage()).displayLanguage(locale.getDisplayLanguage(locale)).build();
    }

    public static LanguageCombinationPrimerParameters getLanguagePrimerParameters(LocaleCombinationParameters localeCombinationParameters) {
        return new LanguageCombinationPrimerParameters.Builder().currentLanguage(getLanguageGroup(localeCombinationParameters.getPreselectedLocale().getLocales())).availableLanguages(getLanguages(localeCombinationParameters.getSupportedLocales())).recommendedLanguages(getLanguages(localeCombinationParameters.getRecommendedLocales())).build();
    }

    private static ArrayList<LanguageGroup> getLanguages(ArrayList<LocaleGroup> arrayList) {
        ArrayList<LanguageGroup> arrayList2 = new ArrayList<>();
        Iterator<LocaleGroup> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            arrayList2.add(getLanguageGroup(it2.next().getLocales()));
        }
        return arrayList2;
    }
}
