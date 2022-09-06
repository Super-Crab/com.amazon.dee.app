package com.amazon.alexa.marketplace.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.identity.auth.device.authorization.RegionUtil;
/* loaded from: classes9.dex */
public enum Region {
    EUROPE(RegionUtil.REGION_STRING_EU),
    FAR_EAST(RegionUtil.REGION_STRING_FE),
    NORTH_AMERICA_AND_WORLD(RegionUtil.REGION_STRING_NA);
    
    private final String region;

    Region(@NonNull String str) {
        this.region = str;
    }

    @Nullable
    public static Region fromString(@Nullable String str) {
        Region[] values;
        for (Region region : values()) {
            if (region.toString().equals(str)) {
                return region;
            }
        }
        return null;
    }

    @Override // java.lang.Enum
    @NonNull
    public String toString() {
        return this.region;
    }
}
