package com.amazon.alexa.preload.attribution.country;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes9.dex */
public enum MarketplaceCode {
    US("20"),
    MX("20"),
    BR("20"),
    CA("20"),
    DE("21"),
    GB("21") { // from class: com.amazon.alexa.preload.attribution.country.MarketplaceCode.1
        @Override // com.amazon.alexa.preload.attribution.country.MarketplaceCode
        public String getCountryCode() {
            return "uk";
        }
    },
    FR("21"),
    IT("21"),
    ES("21"),
    IN("21"),
    TR("21"),
    JP("22"),
    AU("22"),
    CN("23");
    
    private final String mRegionId;

    @Nullable
    public static MarketplaceCode fromString(@NonNull String str) {
        MarketplaceCode[] values;
        if (!TextUtils.isEmpty(str)) {
            for (MarketplaceCode marketplaceCode : values()) {
                if (marketplaceCode.toString().equalsIgnoreCase(str)) {
                    return marketplaceCode;
                }
            }
            return null;
        }
        return null;
    }

    public String getCountryCode() {
        return name().toLowerCase();
    }

    public String getRegionId() {
        return this.mRegionId;
    }

    MarketplaceCode(@NonNull String str) {
        this.mRegionId = str;
    }
}
