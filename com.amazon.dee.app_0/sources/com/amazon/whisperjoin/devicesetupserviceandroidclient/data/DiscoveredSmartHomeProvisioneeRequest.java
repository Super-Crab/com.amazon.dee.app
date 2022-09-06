package com.amazon.whisperjoin.devicesetupserviceandroidclient.data;

import com.amazon.devicesetupservice.v1.ProvisionerInfo;
import com.google.common.base.Objects;
/* loaded from: classes13.dex */
public class DiscoveredSmartHomeProvisioneeRequest extends AbstractDiscoveredProvisioneeRequest {
    private final String mMacAddress;
    private final String mProtocolType;
    private final int mRssi;

    public DiscoveredSmartHomeProvisioneeRequest(String str, String str2, int i, ProvisionerInfo provisionerInfo) {
        this.mProtocolType = str;
        this.mMacAddress = str2;
        this.mRssi = i;
        this.mProvisionerInfo = provisionerInfo;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DiscoveredSmartHomeProvisioneeRequest)) {
            return false;
        }
        DiscoveredSmartHomeProvisioneeRequest discoveredSmartHomeProvisioneeRequest = (DiscoveredSmartHomeProvisioneeRequest) obj;
        return this.mRssi == discoveredSmartHomeProvisioneeRequest.mRssi && Objects.equal(this.mProtocolType, discoveredSmartHomeProvisioneeRequest.mProtocolType) && Objects.equal(this.mMacAddress, discoveredSmartHomeProvisioneeRequest.mMacAddress) && Objects.equal(this.mProvisionerInfo, discoveredSmartHomeProvisioneeRequest.mProvisionerInfo);
    }

    public String getMacAddress() {
        return this.mMacAddress;
    }

    public String getProtocolType() {
        return this.mProtocolType;
    }

    public int getRssi() {
        return this.mRssi;
    }

    public int hashCode() {
        return Objects.hashCode(this.mProtocolType, this.mMacAddress, Integer.valueOf(this.mRssi), this.mProvisionerInfo);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("mProtocolType", this.mProtocolType).add("mMacAddress", this.mMacAddress).add("mRssi", this.mRssi).add("mProvisionerInfo", this.mProvisionerInfo).toString();
    }
}
