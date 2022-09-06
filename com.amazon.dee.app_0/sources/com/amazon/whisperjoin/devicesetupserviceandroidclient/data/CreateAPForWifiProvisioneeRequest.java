package com.amazon.whisperjoin.devicesetupserviceandroidclient.data;

import com.amazon.devicesetupservice.v1.ProvisionerInfo;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/* loaded from: classes13.dex */
public class CreateAPForWifiProvisioneeRequest {
    private int mActiveConnectionsCount;
    private DiscoveredWifiProvisionee mDiscoveredWifiProvisionee;
    private ProvisionerInfo mProvisionerInfo;

    public CreateAPForWifiProvisioneeRequest(DiscoveredWifiProvisionee discoveredWifiProvisionee, ProvisionerInfo provisionerInfo, int i) {
        this.mDiscoveredWifiProvisionee = discoveredWifiProvisionee;
        this.mProvisionerInfo = provisionerInfo;
        this.mActiveConnectionsCount = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || CreateAPForWifiProvisioneeRequest.class != obj.getClass()) {
            return false;
        }
        CreateAPForWifiProvisioneeRequest createAPForWifiProvisioneeRequest = (CreateAPForWifiProvisioneeRequest) obj;
        return Objects.equal(this.mDiscoveredWifiProvisionee, createAPForWifiProvisioneeRequest.getDiscoveredWifiProvisionee()) && Objects.equal(this.mProvisionerInfo, createAPForWifiProvisioneeRequest.getProvisionerInfo()) && this.mActiveConnectionsCount == createAPForWifiProvisioneeRequest.getActiveConnectionsCount();
    }

    public int getActiveConnectionsCount() {
        return this.mActiveConnectionsCount;
    }

    public DiscoveredWifiProvisionee getDiscoveredWifiProvisionee() {
        return this.mDiscoveredWifiProvisionee;
    }

    public ProvisionerInfo getProvisionerInfo() {
        return this.mProvisionerInfo;
    }

    public int hashCode() {
        return Objects.hashCode(this.mDiscoveredWifiProvisionee, this.mProvisionerInfo, Integer.valueOf(this.mActiveConnectionsCount));
    }

    public void setActiveConnectionsCount(int i) {
        this.mActiveConnectionsCount = i;
    }

    public void setDiscoveredWifiProvisionee(DiscoveredWifiProvisionee discoveredWifiProvisionee) {
        this.mDiscoveredWifiProvisionee = discoveredWifiProvisionee;
    }

    public void setProvisionerInfo(ProvisionerInfo provisionerInfo) {
        this.mProvisionerInfo = provisionerInfo;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("discoveredWifiPrvovisionee", this.mDiscoveredWifiProvisionee).add("provisionerInfo", this.mProvisionerInfo).add("activeConnectionsCount", this.mActiveConnectionsCount).toString();
    }
}
