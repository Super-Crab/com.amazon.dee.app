package com.amazon.alexa.marketplace.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes9.dex */
public enum CountryCode {
    AU("AU"),
    BR("BR"),
    CA("CA"),
    CN("CN"),
    DE("DE"),
    ES("ES"),
    FR("FR"),
    GB("GB"),
    IN("IN"),
    IT("IT"),
    JP("JP"),
    MX("MX"),
    NL("NL"),
    US("US"),
    SA("SA");
    
    private final String countryCode;

    CountryCode(@NonNull String str) {
        this.countryCode = str;
    }

    @Nullable
    public static CountryCode fromString(@Nullable String str) {
        CountryCode[] values;
        for (CountryCode countryCode : values()) {
            if (countryCode.toString().equals(str)) {
                return countryCode;
            }
        }
        return null;
    }

    @Override // java.lang.Enum
    @NonNull
    public String toString() {
        return this.countryCode;
    }
}
