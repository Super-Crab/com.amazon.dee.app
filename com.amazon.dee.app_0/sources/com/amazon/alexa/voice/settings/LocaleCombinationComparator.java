package com.amazon.alexa.voice.settings;

import com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LocaleGroup;
import java.text.Collator;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
/* loaded from: classes11.dex */
public class LocaleCombinationComparator implements Comparator<LocaleGroup> {
    private final List<Comparator<LocaleGroup>> comparators = Arrays.asList(new LocaleLanguageComparator(), new LocaleCountryComparator());
    private final Collator collator = Collator.getInstance(Locale.US);

    /* loaded from: classes11.dex */
    private class LocaleCountryComparator implements Comparator<LocaleGroup> {
        private LocaleCountryComparator() {
        }

        @Override // java.util.Comparator
        public int compare(LocaleGroup localeGroup, LocaleGroup localeGroup2) {
            List<Locale> locales = localeGroup.getLocales();
            List<Locale> locales2 = localeGroup2.getLocales();
            int compare = LocaleCombinationComparator.this.collator.compare(locales.get(0).getDisplayCountry(locales.get(0)), locales2.get(0).getDisplayCountry(locales2.get(0)));
            if (compare == 0) {
                if (locales.size() > 1 && locales2.size() > 1) {
                    return LocaleCombinationComparator.this.collator.compare(locales.get(1).getDisplayCountry(locales.get(1)), locales2.get(1).getDisplayCountry(locales2.get(1)));
                }
                return locales.size() == 1 ? -1 : 1;
            }
            return compare;
        }
    }

    /* loaded from: classes11.dex */
    private class LocaleLanguageComparator implements Comparator<LocaleGroup> {
        private LocaleLanguageComparator() {
        }

        @Override // java.util.Comparator
        public int compare(LocaleGroup localeGroup, LocaleGroup localeGroup2) {
            List<Locale> locales = localeGroup.getLocales();
            List<Locale> locales2 = localeGroup2.getLocales();
            int compare = LocaleCombinationComparator.this.collator.compare(locales.get(0).getDisplayLanguage(locales.get(0)), locales2.get(0).getDisplayLanguage(locales2.get(0)));
            if (compare == 0) {
                if (locales.size() > 1 && locales2.size() > 1) {
                    return LocaleCombinationComparator.this.collator.compare(locales.get(1).getDisplayLanguage(locales.get(1)), locales2.get(1).getDisplayLanguage(locales2.get(1)));
                }
                return locales.size() == 1 ? -1 : 1;
            }
            return compare;
        }
    }

    @Override // java.util.Comparator
    public int compare(LocaleGroup localeGroup, LocaleGroup localeGroup2) {
        for (Comparator<LocaleGroup> comparator : this.comparators) {
            int compare = comparator.compare(localeGroup, localeGroup2);
            if (compare != 0) {
                return compare;
            }
        }
        return 0;
    }
}
