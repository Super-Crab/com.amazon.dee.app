package com.amazon.alexa.api;

import android.os.Bundle;
import com.amazon.alexa.api.Bundles;
import com.amazon.alexa.client.annotations.Nullable;
/* loaded from: classes6.dex */
public class AlexaCardExtras {
    private final java.util.Locale locale;
    private final String marketplace;

    /* loaded from: classes6.dex */
    public static class Builder {
        private java.util.Locale locale;
        private String marketplace;

        private Builder() {
        }

        public AlexaCardExtras build() {
            return new AlexaCardExtras(this);
        }

        public Builder setLocale(java.util.Locale locale) {
            this.locale = locale;
            return this;
        }

        public Builder setMarketplace(String str) {
            this.marketplace = str;
            return this;
        }
    }

    /* loaded from: classes6.dex */
    enum CardExtrasKeys implements Bundles.Key {
        MARKETPLACE,
        LOCALE
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaCardExtras(@Nullable Bundle bundle) {
        this.marketplace = Bundles.getString(bundle, CardExtrasKeys.MARKETPLACE);
        String optionalString = Bundles.getOptionalString(bundle, CardExtrasKeys.LOCALE);
        this.locale = optionalString != null ? java.util.Locale.forLanguageTag(optionalString) : null;
    }

    private AlexaCardExtras(Builder builder) {
        this.marketplace = builder.marketplace;
        this.locale = builder.locale;
    }

    public static Builder builder() {
        return new Builder();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(CardExtrasKeys.MARKETPLACE.name(), this.marketplace);
        if (this.locale != null) {
            bundle.putString(CardExtrasKeys.LOCALE.name(), this.locale.toLanguageTag());
        }
        return bundle;
    }

    @Nullable
    public java.util.Locale getLocale() {
        return this.locale;
    }

    @Nullable
    public String getMarketplace() {
        return this.marketplace;
    }
}
