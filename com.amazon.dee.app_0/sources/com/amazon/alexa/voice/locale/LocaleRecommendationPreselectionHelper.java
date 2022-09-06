package com.amazon.alexa.voice.locale;

import com.amazon.alexa.accessorykit.accessories.session.system.SystemModelI18nConfigTransformer;
import com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LocaleGroup;
import com.amazon.alexa.voice.utils.Preconditions;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class LocaleRecommendationPreselectionHelper {
    private static final Map<String, ArrayList<Locale>> OTHER_RECOMMENDED_LOCALES_LIST = new HashMap();
    private static final Map<String, ArrayList<LocaleGroup>> OTHER_RECOMMENDED_LOCALES_LIST_COMBINATIONS = new HashMap();

    static {
        OTHER_RECOMMENDED_LOCALES_LIST.put("NL", Lists.newArrayList(new Locale("en", "GB"), new Locale("de", "DE")));
        OTHER_RECOMMENDED_LOCALES_LIST.put("AE", Lists.newArrayList(new Locale("ar", "SA")));
        OTHER_RECOMMENDED_LOCALES_LIST_COMBINATIONS.put("NL", Lists.newArrayList(new LocaleGroup(Arrays.asList(new Locale("en", "GB"))), new LocaleGroup(Arrays.asList(new Locale("de", "DE")))));
        OTHER_RECOMMENDED_LOCALES_LIST_COMBINATIONS.put("AE", Lists.newArrayList(new LocaleGroup(Arrays.asList(new Locale("ar", "SA")))));
    }

    private LocaleRecommendationPreselectionHelper() {
    }

    private static boolean doAllLocalesMatchPFMCountryCode(List<Locale> list, String str) {
        Preconditions.notNullOrEmpty(SystemModelI18nConfigTransformer.KEY_LOCALES, list);
        Preconditions.notBlank("marketPlaceCountryCode", str);
        for (Locale locale : list) {
            if (!locale.getCountry().equals(str)) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Locale getPreselectedLocale(Locale locale, List<Locale> list) {
        Preconditions.notNullOrEmpty("recommendedLocales", list);
        Preconditions.notNull("currentSystemLocale", locale);
        if (list.size() == 1) {
            return list.get(0);
        }
        for (Locale locale2 : list) {
            if (locale2.getLanguage().equals(locale.getLanguage())) {
                return locale2;
            }
        }
        for (Locale locale3 : list) {
            if (locale3.getLanguage().equals(Locale.ENGLISH.getLanguage())) {
                return locale3;
            }
        }
        return list.get(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static LocaleGroup getPreselectedLocaleCombination(Locale locale, List<LocaleGroup> list) {
        Preconditions.notNullOrEmpty("recommendedLocales", list);
        Preconditions.notNull("currentSystemLocale", locale);
        if (list.size() == 1) {
            return list.get(0);
        }
        for (LocaleGroup localeGroup : list) {
            if (localeGroup.getLocales().size() == 1 && localeGroup.getLocales().get(0).getLanguage().equals(locale.getLanguage())) {
                return localeGroup;
            }
        }
        for (LocaleGroup localeGroup2 : list) {
            if (localeGroup2.getLocales().size() == 1 && localeGroup2.getLocales().get(0).getLanguage().equals(Locale.ENGLISH.getLanguage())) {
                return localeGroup2;
            }
        }
        return list.get(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ArrayList<Locale> getRecommendedLocale(String str, List<Locale> list) {
        Preconditions.notNullOrEmpty("alexaSupportedLocales", list);
        Preconditions.notBlank("marketPlaceCountryCode", str);
        ArrayList<Locale> arrayList = new ArrayList<>();
        for (Locale locale : list) {
            if (locale.getCountry().equals(str)) {
                arrayList.add(locale);
            }
        }
        if (arrayList.isEmpty()) {
            ArrayList<Locale> arrayList2 = OTHER_RECOMMENDED_LOCALES_LIST.get(str);
            ArrayList arrayList3 = new ArrayList();
            if (arrayList2 != null && arrayList2.size() > 0) {
                Iterator<Locale> it2 = arrayList2.iterator();
                while (it2.hasNext()) {
                    Locale next = it2.next();
                    if (list.contains(next)) {
                        arrayList3.add(next);
                    }
                }
                if (arrayList3.size() > 0) {
                    return arrayList2;
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ArrayList<LocaleGroup> getRecommendedLocaleCombination(String str, List<List<Locale>> list) {
        ArrayList<LocaleGroup> arrayList;
        Preconditions.notNullOrEmpty("alexaSupportedLocales", list);
        Preconditions.notBlank("marketPlaceCountryCode", str);
        ArrayList<LocaleGroup> arrayList2 = new ArrayList<>();
        for (List<Locale> list2 : list) {
            if (doAllLocalesMatchPFMCountryCode(list2, str)) {
                arrayList2.add(new LocaleGroup(list2));
            }
        }
        return (!arrayList2.isEmpty() || (arrayList = OTHER_RECOMMENDED_LOCALES_LIST_COMBINATIONS.get(str)) == null) ? arrayList2 : arrayList;
    }
}
