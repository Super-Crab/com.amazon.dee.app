package com.amazon.whisperjoin.devicesetupserviceandroidclient.data;

import com.google.common.base.Objects;
/* loaded from: classes13.dex */
public class DiscoveredProvisionableDeviceResponse {
    private final boolean mCanProceed;
    private final String mProvisionableEventReportUrl;
    private final String mProvisionerEventReportUrl;
    private final long mWaitTime;

    public DiscoveredProvisionableDeviceResponse(String str, String str2, boolean z, long j) {
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
        if (obj == null || DiscoveredProvisionableDeviceResponse.class != obj.getClass()) {
            return false;
        }
        DiscoveredProvisionableDeviceResponse discoveredProvisionableDeviceResponse = (DiscoveredProvisionableDeviceResponse) obj;
        return this.mCanProceed == discoveredProvisionableDeviceResponse.mCanProceed && this.mWaitTime == discoveredProvisionableDeviceResponse.mWaitTime && Objects.equal(this.mProvisionerEventReportUrl, discoveredProvisionableDeviceResponse.mProvisionerEventReportUrl) && Objects.equal(this.mProvisionableEventReportUrl, discoveredProvisionableDeviceResponse.mProvisionableEventReportUrl);
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
