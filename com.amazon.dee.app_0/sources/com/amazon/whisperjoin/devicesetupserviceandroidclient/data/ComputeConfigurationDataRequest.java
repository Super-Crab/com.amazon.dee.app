package com.amazon.whisperjoin.devicesetupserviceandroidclient.data;

import com.amazon.devicesetup.common.DataMapValue;
import com.amazon.devicesetup.common.v1.DeviceDetails;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.util.Map;
/* loaded from: classes13.dex */
public class ComputeConfigurationDataRequest {
    private final Map<String, DataMapValue> mConfiguration;
    private final DeviceDetails mDeviceDetails;
    private final String mNonce;

    public ComputeConfigurationDataRequest(String str, DeviceDetails deviceDetails, Map<String, DataMapValue> map) {
        this.mNonce = str;
        this.mDeviceDetails = deviceDetails;
        this.mConfiguration = map;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ComputeConfigurationDataRequest.class != obj.getClass()) {
            return false;
        }
        ComputeConfigurationDataRequest computeConfigurationDataRequest = (ComputeConfigurationDataRequest) obj;
        return Objects.equal(this.mNonce, computeConfigurationDataRequest.mNonce) && Objects.equal(this.mDeviceDetails, computeConfigurationDataRequest.mDeviceDetails) && Objects.equal(this.mConfiguration, computeConfigurationDataRequest.mConfiguration);
    }

    public Map<String, DataMapValue> getConfiguration() {
        return this.mConfiguration;
    }

    public DeviceDetails getDeviceDetails() {
        return this.mDeviceDetails;
    }

    public String getNonce() {
        return this.mNonce;
    }

    public int hashCode() {
        return Objects.hashCode(this.mNonce, this.mDeviceDetails, this.mConfiguration);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mNonce", this.mNonce).add("mDeviceDetails", this.mDeviceDetails).add("mConfiguration", this.mConfiguration).toString();
    }
}
