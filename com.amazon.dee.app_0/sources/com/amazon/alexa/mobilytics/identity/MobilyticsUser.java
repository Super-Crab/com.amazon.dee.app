package com.amazon.alexa.mobilytics.identity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.deecomms.common.metrics.MetricKeys;
/* loaded from: classes9.dex */
public interface MobilyticsUser {

    /* loaded from: classes9.dex */
    public enum Attribute {
        HASHED_COMMS_ID(MetricKeys.META_HASHED_COMMS_ID),
        HOUSEHOLD_ID("householdId");
        
        private String id;

        Attribute(String str) {
            this.id = str;
        }

        public String id() {
            return this.id;
        }
    }

    @Nullable
    String attribute(Attribute attribute);

    @Nullable
    default String countryOfResidence() {
        return "Unknown";
    }

    @Nullable
    String directedId();

    boolean hasFeature(@NonNull String str);

    @Nullable
    String marketplaceId();

    @Nullable
    String personId();

    @Nullable
    default String personIdv2() {
        return "Unknown";
    }
}
