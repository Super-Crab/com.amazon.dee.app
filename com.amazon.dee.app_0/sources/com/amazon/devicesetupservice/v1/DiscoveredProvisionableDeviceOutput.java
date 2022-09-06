package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class DiscoveredProvisionableDeviceOutput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.DiscoveredProvisionableDeviceOutput");
    private boolean canProceed;
    private String endpointToUse;
    private String provisionableReportUrl;
    private String provisionerReportUrl;
    private long waitTime;

    public boolean equals(Object obj) {
        if (!(obj instanceof DiscoveredProvisionableDeviceOutput)) {
            return false;
        }
        DiscoveredProvisionableDeviceOutput discoveredProvisionableDeviceOutput = (DiscoveredProvisionableDeviceOutput) obj;
        return Helper.equals(Long.valueOf(this.waitTime), Long.valueOf(discoveredProvisionableDeviceOutput.waitTime)) && Helper.equals(this.provisionableReportUrl, discoveredProvisionableDeviceOutput.provisionableReportUrl) && Helper.equals(this.provisionerReportUrl, discoveredProvisionableDeviceOutput.provisionerReportUrl) && Helper.equals(Boolean.valueOf(this.canProceed), Boolean.valueOf(discoveredProvisionableDeviceOutput.canProceed)) && Helper.equals(this.endpointToUse, discoveredProvisionableDeviceOutput.endpointToUse);
    }

    public String getEndpointToUse() {
        return this.endpointToUse;
    }

    public String getProvisionableReportUrl() {
        return this.provisionableReportUrl;
    }

    public String getProvisionerReportUrl() {
        return this.provisionerReportUrl;
    }

    public long getWaitTime() {
        return this.waitTime;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), Long.valueOf(this.waitTime), this.provisionableReportUrl, this.provisionerReportUrl, Boolean.valueOf(this.canProceed), this.endpointToUse);
    }

    public boolean isCanProceed() {
        return this.canProceed;
    }

    public void setCanProceed(boolean z) {
        this.canProceed = z;
    }

    public void setEndpointToUse(String str) {
        this.endpointToUse = str;
    }

    public void setProvisionableReportUrl(String str) {
        this.provisionableReportUrl = str;
    }

    public void setProvisionerReportUrl(String str) {
        this.provisionerReportUrl = str;
    }

    public void setWaitTime(long j) {
        this.waitTime = j;
    }
}
