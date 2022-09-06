package com.amazon.alexa.voice.ui.marketplace;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes11.dex */
public interface MarketplaceAuthority {

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes11.dex */
    public @interface Marketplace {
        public static final String AU = "AU";
        public static final String BR = "BR";
        public static final String CA = "CA";
        public static final String DE = "DE";
        public static final String ES = "ES";
        public static final String FR = "FR";
        public static final String GB = "GB";
        public static final String IN = "IN";
        public static final String IT = "IT";
        public static final String JP = "JP";
        public static final String MX = "MX";
        public static final String NL = "NL";
        public static final String US = "US";
    }

    String getMarketplace();
}
