package com.amazon.alexa.marketplace.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes9.dex */
public enum MarketplaceName {
    AU("AU"),
    AU_DEVO("AU_DEVO"),
    BR("BR"),
    BR_DEVO("BR_DEVO"),
    CA("CA"),
    CN("CN"),
    DE("DE"),
    ES("ES"),
    ES_DEVO("ES_DEVO"),
    FR("FR"),
    GB("GB"),
    ID("ID"),
    ID_DEVO("ID_DEVO"),
    IN("IN"),
    IT("IT"),
    JP("JP"),
    MX("MX"),
    MX_DEVO("MX_DEVO"),
    NL("NL"),
    NL_DEVO("NL_DEVO"),
    RU("RU"),
    RU_DEVO("RU_DEVO"),
    US("US");
    
    private final String marketplaceName;

    MarketplaceName(@NonNull String str) {
        this.marketplaceName = str;
    }

    @Nullable
    public static MarketplaceName fromString(@Nullable String str) {
        MarketplaceName[] values;
        for (MarketplaceName marketplaceName : values()) {
            if (marketplaceName.toString().equals(str)) {
                return marketplaceName;
            }
        }
        return null;
    }

    @Override // java.lang.Enum
    @NonNull
    public String toString() {
        return this.marketplaceName;
    }
}
