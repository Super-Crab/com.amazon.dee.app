package com.amazon.alexa.voice.locale;

import androidx.annotation.NonNull;
import com.amazon.alexa.voice.settings.LocaleCombinationComparator;
import com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LocaleGroup;
import com.facebook.imageutils.JfifUtil;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
/* loaded from: classes11.dex */
public class LocaleCombinationParameters {
    private final LocaleGroup currentLocale;
    private final LocaleGroup preselectedLocale;
    private final ArrayList<LocaleGroup> recommendedLocales;
    private final ArrayList<LocaleGroup> supportedLocales;

    /* loaded from: classes11.dex */
    public static final class Builder {
        private LocaleGroup currentLocale;
        private LocaleGroup preselectedLocale;
        private ArrayList<LocaleGroup> recommendedLocales;
        private ArrayList<LocaleGroup> supportedLocales;

        public LocaleCombinationParameters build() {
            Objects.requireNonNull(this.currentLocale, "currentLocale cannot be null.");
            Objects.requireNonNull(this.supportedLocales, "supportedLocales cannot be null.");
            Objects.requireNonNull(this.recommendedLocales, "recommendedLocales cannot be null.");
            Objects.requireNonNull(this.preselectedLocale, "preferredLocaleForUser cannot be null.");
            return new LocaleCombinationParameters(this);
        }

        public Builder currentLocale(@NonNull LocaleGroup localeGroup) {
            this.currentLocale = localeGroup;
            return this;
        }

        public Builder preselectedLocale(@NonNull LocaleGroup localeGroup) {
            this.preselectedLocale = localeGroup;
            return this;
        }

        public Builder recommendedLocales(@NonNull ArrayList<LocaleGroup> arrayList) {
            this.recommendedLocales = arrayList;
            return this;
        }

        public Builder removeDuplicates() {
            this.supportedLocales.removeAll(this.recommendedLocales);
            return this;
        }

        public Builder sort() {
            LocaleCombinationComparator localeCombinationComparator = new LocaleCombinationComparator();
            Collections.sort(this.supportedLocales, localeCombinationComparator);
            Collections.sort(this.recommendedLocales, localeCombinationComparator);
            return this;
        }

        public Builder supportedLocales(@NonNull ArrayList<LocaleGroup> arrayList) {
            this.supportedLocales = arrayList;
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || LocaleCombinationParameters.class != obj.getClass()) {
            return false;
        }
        LocaleCombinationParameters localeCombinationParameters = (LocaleCombinationParameters) obj;
        if (!this.currentLocale.equals(localeCombinationParameters.currentLocale) || !this.preselectedLocale.equals(localeCombinationParameters.preselectedLocale) || !this.supportedLocales.equals(localeCombinationParameters.supportedLocales)) {
            return false;
        }
        return this.recommendedLocales.equals(localeCombinationParameters.recommendedLocales);
    }

    public LocaleGroup getCurrentLocale() {
        return this.currentLocale;
    }

    public LocaleGroup getPreselectedLocale() {
        return this.preselectedLocale;
    }

    public ArrayList<LocaleGroup> getRecommendedLocales() {
        return this.recommendedLocales;
    }

    public ArrayList<LocaleGroup> getSupportedLocales() {
        return this.supportedLocales;
    }

    public int hashCode() {
        int hashCode = this.preselectedLocale.hashCode();
        int hashCode2 = this.supportedLocales.hashCode();
        return this.recommendedLocales.hashCode() + ((hashCode2 + ((hashCode + ((this.currentLocale.hashCode() + JfifUtil.MARKER_EOI) * 31)) * 31)) * 31);
    }

    private LocaleCombinationParameters(Builder builder) {
        this.currentLocale = builder.currentLocale;
        this.preselectedLocale = builder.preselectedLocale;
        this.supportedLocales = builder.supportedLocales;
        this.recommendedLocales = builder.recommendedLocales;
    }

    public LocaleCombinationParameters(LocaleCombinationParameters localeCombinationParameters) {
        this.currentLocale = new LocaleGroup(localeCombinationParameters.currentLocale.getLocales());
        this.preselectedLocale = new LocaleGroup(localeCombinationParameters.preselectedLocale.getLocales());
        this.supportedLocales = Lists.newArrayList(localeCombinationParameters.supportedLocales);
        this.recommendedLocales = Lists.newArrayList(localeCombinationParameters.recommendedLocales);
    }
}
