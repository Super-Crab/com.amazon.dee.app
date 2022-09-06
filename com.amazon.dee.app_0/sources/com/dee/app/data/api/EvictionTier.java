package com.dee.app.data.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes10.dex */
public enum EvictionTier {
    EVICTION_TIER_1("1", 0),
    EVICTION_TIER_2("2", 1);
    
    private int appDataCacheEvictionTier;
    private String value;

    EvictionTier(@NonNull String str, int i) {
        this.value = str;
        this.appDataCacheEvictionTier = i;
    }

    public static EvictionTier fromValue(@Nullable String str) {
        if (EVICTION_TIER_1.value.equals(str)) {
            return EVICTION_TIER_1;
        }
        return EVICTION_TIER_2;
    }

    public int getAppDataCacheEvictionTier() {
        return this.appDataCacheEvictionTier;
    }

    public String getValue() {
        return this.value;
    }
}
