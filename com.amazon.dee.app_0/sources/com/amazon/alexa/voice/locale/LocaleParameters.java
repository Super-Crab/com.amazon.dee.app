package com.amazon.alexa.voice.locale;

import androidx.annotation.NonNull;
import com.amazon.alexa.voice.settings.LocaleComparator;
import com.facebook.imageutils.JfifUtil;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Objects;
/* loaded from: classes11.dex */
public class LocaleParameters {
    private final Locale currentLocale;
    private final Locale preselectedLocale;
    private final ArrayList<Locale> recommendedLocales;
    private final ArrayList<Locale> supportedLocales;

    /* loaded from: classes11.dex */
    public static final class Adjuster {
        private final Locale currentLocale;
        private final Locale preselectedLocale;
        private final ArrayList<Locale> recommendedLocales;
        private final ArrayList<Locale> supportedLocales;

        public Adjuster(LocaleParameters localeParameters) {
            this.currentLocale = localeParameters.getCurrentLocale();
            this.preselectedLocale = localeParameters.getPreselectedLocale();
            this.supportedLocales = localeParameters.getSupportedLocales();
            this.recommendedLocales = localeParameters.getRecommendedLocales();
        }

        public LocaleParameters getResult() {
            return new Builder().currentLocale(this.currentLocale).preselectedLocale(this.preselectedLocale).supportedLocales(this.supportedLocales).recommendedLocales(this.recommendedLocales).build();
        }

        public Adjuster removeDuplicates() {
            this.supportedLocales.removeAll(this.recommendedLocales);
            return this;
        }

        public Adjuster sort() {
            LocaleComparator localeComparator = new LocaleComparator();
            Collections.sort(this.supportedLocales, localeComparator);
            Collections.sort(this.recommendedLocales, localeComparator);
            return this;
        }
    }

    /* loaded from: classes11.dex */
    public static final class Builder {
        private Locale currentLocale;
        private Locale preselectedLocale;
        private ArrayList<Locale> recommendedLocales;
        private ArrayList<Locale> supportedLocales;

        public LocaleParameters build() {
            Objects.requireNonNull(this.currentLocale, "currentLocale cannot be null.");
            Objects.requireNonNull(this.supportedLocales, "supportedLocales cannot be null.");
            Objects.requireNonNull(this.recommendedLocales, "recommendedLocales cannot be null.");
            Objects.requireNonNull(this.preselectedLocale, "preferredLocaleForUser cannot be null.");
            return new LocaleParameters(this);
        }

        public Builder currentLocale(@NonNull Locale locale) {
            this.currentLocale = locale;
            return this;
        }

        public Builder preselectedLocale(@NonNull Locale locale) {
            this.preselectedLocale = locale;
            return this;
        }

        public Builder recommendedLocales(@NonNull ArrayList<Locale> arrayList) {
            this.recommendedLocales = arrayList;
            return this;
        }

        public Builder supportedLocales(@NonNull ArrayList<Locale> arrayList) {
            this.supportedLocales = arrayList;
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || LocaleParameters.class != obj.getClass()) {
            return false;
        }
        LocaleParameters localeParameters = (LocaleParameters) obj;
        if (!this.currentLocale.equals(localeParameters.currentLocale) || !this.preselectedLocale.equals(localeParameters.preselectedLocale) || !this.supportedLocales.equals(localeParameters.supportedLocales)) {
            return false;
        }
        return this.recommendedLocales.equals(localeParameters.recommendedLocales);
    }

    public Locale getCurrentLocale() {
        return this.currentLocale;
    }

    public Locale getPreselectedLocale() {
        return this.preselectedLocale;
    }

    public ArrayList<Locale> getRecommendedLocales() {
        return this.recommendedLocales;
    }

    public ArrayList<Locale> getSupportedLocales() {
        return this.supportedLocales;
    }

    public int hashCode() {
        int hashCode = this.preselectedLocale.hashCode();
        int hashCode2 = this.supportedLocales.hashCode();
        return this.recommendedLocales.hashCode() + ((hashCode2 + ((hashCode + ((this.currentLocale.hashCode() + JfifUtil.MARKER_EOI) * 31)) * 31)) * 31);
    }

    private LocaleParameters(Builder builder) {
        this.currentLocale = builder.currentLocale;
        this.preselectedLocale = builder.preselectedLocale;
        this.supportedLocales = builder.supportedLocales;
        this.recommendedLocales = builder.recommendedLocales;
    }

    public LocaleParameters(LocaleParameters localeParameters) {
        this.currentLocale = (Locale) localeParameters.currentLocale.clone();
        this.preselectedLocale = (Locale) localeParameters.preselectedLocale.clone();
        this.supportedLocales = Lists.newArrayList(localeParameters.supportedLocales);
        this.recommendedLocales = Lists.newArrayList(localeParameters.recommendedLocales);
    }
}
