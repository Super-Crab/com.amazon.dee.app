package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type;

import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.provisioning.DeviceDetails;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/* loaded from: classes13.dex */
public class GetProvisioningDetailsOptions {
    private final DeviceDetails mDeviceDetails;
    private boolean mForceRescan;
    private final String mSessionToken;
    private boolean mShouldRetrieveAvailableNetworks;
    private boolean mShouldRetrieveDeviceDetails;

    public GetProvisioningDetailsOptions(boolean z, boolean z2, boolean z3, String str, DeviceDetails deviceDetails) {
        this.mShouldRetrieveAvailableNetworks = z;
        this.mForceRescan = z2;
        this.mShouldRetrieveDeviceDetails = z3;
        this.mSessionToken = str;
        this.mDeviceDetails = deviceDetails;
    }

    public static GetProvisioningDetailsOptions getAllDetails(String str, DeviceDetails deviceDetails) {
        return new GetProvisioningDetailsOptions(true, false, true, str, deviceDetails);
    }

    public static GetProvisioningDetailsOptions refreshAvailableNetworks(String str, DeviceDetails deviceDetails) {
        return new GetProvisioningDetailsOptions(true, true, false, str, deviceDetails);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || GetProvisioningDetailsOptions.class != obj.getClass()) {
            return false;
        }
        GetProvisioningDetailsOptions getProvisioningDetailsOptions = (GetProvisioningDetailsOptions) obj;
        return this.mShouldRetrieveAvailableNetworks == getProvisioningDetailsOptions.mShouldRetrieveAvailableNetworks && this.mForceRescan == getProvisioningDetailsOptions.mForceRescan && this.mShouldRetrieveDeviceDetails == getProvisioningDetailsOptions.mShouldRetrieveDeviceDetails && Objects.equal(this.mSessionToken, getProvisioningDetailsOptions.mSessionToken) && Objects.equal(this.mDeviceDetails, getProvisioningDetailsOptions.mDeviceDetails);
    }

    public boolean forceRescan() {
        return this.mForceRescan;
    }

    public DeviceDetails getDeviceDetails() {
        return this.mDeviceDetails;
    }

    public String getSessionToken() {
        return this.mSessionToken;
    }

    public int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.mShouldRetrieveAvailableNetworks), Boolean.valueOf(this.mForceRescan), Boolean.valueOf(this.mShouldRetrieveDeviceDetails), this.mSessionToken, this.mDeviceDetails);
    }

    public boolean shouldRetrieveAvailableNetworks() {
        return this.mShouldRetrieveAvailableNetworks;
    }

    public boolean shouldRetrieveDeviceDetails() {
        return this.mShouldRetrieveDeviceDetails;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mShouldRetrieveAvailableNetworks", this.mShouldRetrieveAvailableNetworks).add("mForceRescan", this.mForceRescan).add("mShouldRetrieveDeviceDetails", this.mShouldRetrieveDeviceDetails).add("mSessionToken", this.mSessionToken).add("mDeviceDetails", this.mDeviceDetails).toString();
    }
}
