package com.amazon.whisperjoin.common.sharedtypes.provisioning.data.provisioning;
/* loaded from: classes13.dex */
public class DeviceDetailsProtoData {
    private final DeviceDetails mDeviceDetails;
    private final String mNetworkSyncToken;

    public DeviceDetailsProtoData(DeviceDetails deviceDetails, String str) {
        this.mDeviceDetails = deviceDetails;
        this.mNetworkSyncToken = str;
    }

    public DeviceDetails getDeviceDetails() {
        return this.mDeviceDetails;
    }

    public String getNetworkSyncToken() {
        return this.mNetworkSyncToken;
    }
}
