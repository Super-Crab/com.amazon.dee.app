package com.amazon.alexa.voice.settings;

import java.text.Collator;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
/* loaded from: classes11.dex */
public class LocaleComparator implements Comparator<Locale> {
    private final List<Comparator<Locale>> comparators = Arrays.asList(new LocaleLanguageComparator(), new LocaleCountryComparator());
    private final Collator collator = Collator.getInstance(Locale.US);

    /* loaded from: classes11.dex */
    private class LocaleCountryComparator implements Comparator<Locale> {
        private LocaleCountryComparator() {
        }

        @Override // java.util.Comparator
        public int compare(Locale locale, Locale locale2) {
            return LocaleComparator.this.collator.compare(locale.getDisplayCountry(locale), locale2.getDisplayCountry(locale2));
        }
    }

    /* loaded from: classes11.dex */
    private class LocaleLanguageComparator implements Comparator<Locale> {
        private LocaleLanguageComparator() {
        }

        @Override // java.util.Comparator
        public int compare(Locale locale, Locale locale2) {
            return LocaleComparator.this.collator.compare(locale.getDisplayLanguage(locale), locale2.getDisplayLanguage(locale2));
        }
    }

    @Override // java.util.Comparator
    public int compare(Locale locale, Locale locale2) {
        for (Comparator<Locale> comparator : this.comparators) {
            int compare = comparator.compare(locale, locale2);
            if (compare != 0) {
                return compare;
            }
        }
        return 0;
    }
}
