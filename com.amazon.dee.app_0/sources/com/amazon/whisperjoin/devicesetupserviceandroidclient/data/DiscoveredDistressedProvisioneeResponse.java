package com.amazon.whisperjoin.devicesetupserviceandroidclient.data;

import com.google.common.base.Objects;
/* loaded from: classes13.dex */
public class DiscoveredDistressedProvisioneeResponse {
    private final boolean mCanProceed;
    private final String mProvisionableEventReportUrl;
    private final String mProvisionerEventReportUrl;
    private final long mWaitTime;

    public DiscoveredDistressedProvisioneeResponse(String str, String str2, boolean z, long j) {
        this.mProvisionerEventReportUrl = str;
        this.mProvisionableEventReportUrl = str2;
        this.mCanProceed = z;
        this.mWaitTime = j;
    }

    public boolean canProceed() {
        return this.mCanProceed;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DiscoveredDistressedProvisioneeResponse.class != obj.getClass()) {
            return false;
        }
        DiscoveredDistressedProvisioneeResponse discoveredDistressedProvisioneeResponse = (DiscoveredDistressedProvisioneeResponse) obj;
        return this.mCanProceed == discoveredDistressedProvisioneeResponse.mCanProceed && this.mWaitTime == discoveredDistressedProvisioneeResponse.mWaitTime && Objects.equal(this.mProvisionerEventReportUrl, discoveredDistressedProvisioneeResponse.mProvisionerEventReportUrl) && Objects.equal(this.mProvisionableEventReportUrl, discoveredDistressedProvisioneeResponse.mProvisionableEventReportUrl);
    }

    public String getProvisionableEventReportUrl() {
        return this.mProvisionableEventReportUrl;
    }

    public String getProvisionerEventReportUrl() {
        return this.mProvisionerEventReportUrl;
    }

    public long getWaitTime() {
        return this.mWaitTime;
    }

    public int hashCode() {
        return Objects.hashCode(this.mProvisionerEventReportUrl, this.mProvisionableEventReportUrl, Boolean.valueOf(this.mCanProceed), Long.valueOf(this.mWaitTime));
    }
}
