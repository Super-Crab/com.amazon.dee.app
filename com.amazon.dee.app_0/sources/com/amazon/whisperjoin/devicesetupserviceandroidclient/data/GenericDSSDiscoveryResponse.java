package com.amazon.whisperjoin.devicesetupserviceandroidclient.data;

import com.google.common.base.Objects;
/* loaded from: classes13.dex */
public class GenericDSSDiscoveryResponse {
    private final boolean mCanProceed;
    private final String mProvisionableEventReportUrl;
    private final String mProvisionerEventReportUrl;
    private final String mSessionToken;
    private final long mWaitTime;

    public GenericDSSDiscoveryResponse(String str, String str2, boolean z, long j, String str3) {
        this.mProvisionerEventReportUrl = str;
        this.mProvisionableEventReportUrl = str2;
        this.mCanProceed = z;
        this.mWaitTime = j;
        this.mSessionToken = str3;
    }

    public boolean canProceed() {
        return this.mCanProceed;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || GenericDSSDiscoveryResponse.class != obj.getClass()) {
            return false;
        }
        GenericDSSDiscoveryResponse genericDSSDiscoveryResponse = (GenericDSSDiscoveryResponse) obj;
        return this.mCanProceed == genericDSSDiscoveryResponse.mCanProceed && this.mWaitTime == genericDSSDiscoveryResponse.mWaitTime && Objects.equal(this.mProvisionerEventReportUrl, genericDSSDiscoveryResponse.mProvisionerEventReportUrl) && Objects.equal(this.mProvisionableEventReportUrl, genericDSSDiscoveryResponse.mProvisionableEventReportUrl) && Objects.equal(this.mSessionToken, genericDSSDiscoveryResponse.mSessionToken);
    }

    public String getProvisionableEventReportUrl() {
        return this.mProvisionableEventReportUrl;
    }

    public String getProvisionerEventReportUrl() {
        return this.mProvisionerEventReportUrl;
    }

    public String getSessionToken() {
        return this.mSessionToken;
    }

    public long getWaitTime() {
        return this.mWaitTime;
    }

    public int hashCode() {
        return Objects.hashCode(this.mProvisionerEventReportUrl, this.mProvisionableEventReportUrl, Boolean.valueOf(this.mCanProceed), Long.valueOf(this.mWaitTime), this.mSessionToken);
    }
}
