package com.amazon.devicesetup.provisioning.data.device;

import com.amazon.alexa.devicesetup.sdk.whisperjoin.helper.ProvisioningConstants;
import com.amazon.devicesetup.provisioning.data.configuration.DataMap;
import com.amazon.devicesetup.provisioning.data.wifi.WifiConnectionDetails;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/* loaded from: classes12.dex */
public class DeviceDetails {
    private final DataMap deviceCapabilitiesMap;
    private final String deviceFirmwareRevision;
    private final String deviceHardwareRevision;
    private final String deviceModelNumber;
    private final String deviceSerialNumber;
    private final WifiConnectionDetails lastConnectionWifiDetails;
    private final String manufacturer;
    private final String networkSyncToken;
    private final String sdkVersion;

    public DeviceDetails(String str, String str2, String str3, String str4, String str5, DataMap dataMap) {
        this(str, str2, str3, str4, str5, dataMap, null, null, null);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DeviceDetails.class != obj.getClass()) {
            return false;
        }
        DeviceDetails deviceDetails = (DeviceDetails) obj;
        return Objects.equal(this.manufacturer, deviceDetails.manufacturer) && Objects.equal(this.deviceModelNumber, deviceDetails.deviceModelNumber) && Objects.equal(this.deviceSerialNumber, deviceDetails.deviceSerialNumber) && Objects.equal(this.deviceHardwareRevision, deviceDetails.deviceHardwareRevision) && Objects.equal(this.deviceFirmwareRevision, deviceDetails.deviceFirmwareRevision) && Objects.equal(this.deviceCapabilitiesMap, deviceDetails.deviceCapabilitiesMap) && Objects.equal(this.lastConnectionWifiDetails, deviceDetails.lastConnectionWifiDetails) && Objects.equal(this.networkSyncToken, deviceDetails.networkSyncToken) && Objects.equal(this.sdkVersion, deviceDetails.sdkVersion);
    }

    public DataMap getDeviceCapabilitiesMap() {
        return this.deviceCapabilitiesMap;
    }

    public String getDeviceFirmwareRevision() {
        return this.deviceFirmwareRevision;
    }

    public String getDeviceHardwareRevision() {
        return this.deviceHardwareRevision;
    }

    public String getDeviceModelNumber() {
        return this.deviceModelNumber;
    }

    public String getDeviceSerialNumber() {
        return this.deviceSerialNumber;
    }

    public WifiConnectionDetails getLastConnectionWifiDetails() {
        return this.lastConnectionWifiDetails;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public String getNetworkSyncToken() {
        return this.networkSyncToken;
    }

    public String getSdkVersion() {
        return this.sdkVersion;
    }

    public boolean hasLastConnectionWifiDetails() {
        return this.lastConnectionWifiDetails != null;
    }

    public boolean hasNetworkSyncToken() {
        return this.networkSyncToken != null;
    }

    public int hashCode() {
        return Objects.hashCode(this.manufacturer, this.deviceModelNumber, this.deviceSerialNumber, this.deviceHardwareRevision, this.deviceFirmwareRevision, this.deviceCapabilitiesMap, this.lastConnectionWifiDetails, this.networkSyncToken, this.sdkVersion);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("manufacturer", this.manufacturer).add("deviceModelNumber", this.deviceModelNumber).add("deviceSerialNumber", this.deviceSerialNumber).add("deviceHardwareRevision", this.deviceHardwareRevision).add("deviceFirmwareRevision", this.deviceFirmwareRevision).add("deviceCapabilitiesMap", this.deviceCapabilitiesMap).add(ProvisioningConstants.PROVISIONABLE_DEVICE_DETAILS_LAST_CONNECTED_WIFI_DETAILS, this.lastConnectionWifiDetails).add("sdkVersion", this.sdkVersion).toString();
    }

    public DeviceDetails(String str, String str2, String str3, String str4, String str5, DataMap dataMap, String str6) {
        this(str, str2, str3, str4, str5, dataMap, null, null, str6);
    }

    public DeviceDetails(String str, String str2, String str3, String str4, String str5, DataMap dataMap, WifiConnectionDetails wifiConnectionDetails, String str6) {
        this(str, str2, str3, str4, str5, dataMap, wifiConnectionDetails, str6, null);
    }

    public DeviceDetails(String str, String str2, String str3, String str4, String str5, DataMap dataMap, WifiConnectionDetails wifiConnectionDetails, String str6, String str7) {
        this.manufacturer = str;
        this.deviceModelNumber = str2;
        this.deviceSerialNumber = str3;
        this.deviceHardwareRevision = str4;
        this.deviceFirmwareRevision = str5;
        this.deviceCapabilitiesMap = dataMap;
        this.lastConnectionWifiDetails = wifiConnectionDetails;
        this.networkSyncToken = str6;
        this.sdkVersion = str7;
    }
}
