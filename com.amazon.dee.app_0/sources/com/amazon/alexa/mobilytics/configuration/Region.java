package com.amazon.alexa.mobilytics.configuration;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazonaws.regions.Regions;
/* loaded from: classes9.dex */
public enum Region {
    US(Regions.US_EAST_1),
    CA(Regions.US_EAST_1),
    MX(Regions.US_EAST_1),
    BR(Regions.US_EAST_1),
    CN(Regions.US_EAST_1),
    GB(Regions.EU_WEST_1),
    DE(Regions.EU_WEST_1),
    IN(Regions.EU_WEST_1),
    FR(Regions.EU_WEST_1),
    IT(Regions.EU_WEST_1),
    NL(Regions.EU_WEST_1),
    ES(Regions.EU_WEST_1),
    JP(Regions.US_WEST_2),
    AU(Regions.US_WEST_2),
    DEFAULT(US);
    
    private Regions awsRegion;

    Region(@NonNull Regions regions) {
        this.awsRegion = regions;
    }

    @NonNull
    public static Region fromCountryCode(@Nullable String str) {
        Region[] values;
        if (str == null) {
            return DEFAULT;
        }
        for (Region region : values()) {
            if (region.name().equalsIgnoreCase(str)) {
                return region;
            }
        }
        return DEFAULT;
    }

    @NonNull
    public Regions awsRegion() {
        return this.awsRegion;
    }

    Region(@NonNull Region region) {
        this.awsRegion = region.awsRegion;
    }
}
