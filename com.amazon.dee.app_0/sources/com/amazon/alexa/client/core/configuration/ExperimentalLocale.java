package com.amazon.alexa.client.core.configuration;

import java.util.Locale;
import java.util.Objects;
/* loaded from: classes6.dex */
public class ExperimentalLocale {
    private final Feature feature;
    private final Locale locale;

    public ExperimentalLocale(Locale locale, Feature feature) {
        this.locale = locale;
        this.feature = feature;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ExperimentalLocale.class != obj.getClass()) {
            return false;
        }
        ExperimentalLocale experimentalLocale = (ExperimentalLocale) obj;
        return Objects.equals(this.locale, experimentalLocale.locale) && this.feature == experimentalLocale.feature;
    }

    public Feature getFeature() {
        return this.feature;
    }

    public Locale getLocale() {
        return this.locale;
    }

    public int hashCode() {
        return Objects.hash(this.locale, this.feature);
    }
}
