package com.amazon.whisperjoin.devicesetupserviceandroidclient.data;

import com.google.common.base.Objects;
/* loaded from: classes13.dex */
public class DiscoveredLocalNotificationProvisioneeResponse {
    private final boolean mCanProceed;
    private final LegacyIdentifierData mLegacyIdentifierData;

    public DiscoveredLocalNotificationProvisioneeResponse(boolean z, LegacyIdentifierData legacyIdentifierData) {
        this.mCanProceed = z;
        this.mLegacyIdentifierData = legacyIdentifierData;
    }

    public boolean canProceed() {
        return this.mCanProceed;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DiscoveredLocalNotificationProvisioneeResponse.class != obj.getClass()) {
            return false;
        }
        DiscoveredLocalNotificationProvisioneeResponse discoveredLocalNotificationProvisioneeResponse = (DiscoveredLocalNotificationProvisioneeResponse) obj;
        return this.mCanProceed == discoveredLocalNotificationProvisioneeResponse.mCanProceed && Objects.equal(this.mLegacyIdentifierData, discoveredLocalNotificationProvisioneeResponse.mLegacyIdentifierData);
    }

    public LegacyIdentifierData getLegacyIdentifier() {
        return this.mLegacyIdentifierData;
    }

    public int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.mCanProceed), this.mLegacyIdentifierData);
    }
}
