package com.amazon.devicesetup.common.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class DeviceDetails implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetup.common.v1.DeviceDetails");
    private String authMaterialIndex;
    private String deviceModel;
    private String deviceName;
    private String deviceSerial;
    private String firmwareVersion;
    private String hardwareVersion;
    private String manufacturer;
    private String productIndex;
    private String sdkVersion;
    private String softwareVersionIndex;

    public boolean equals(Object obj) {
        if (!(obj instanceof DeviceDetails)) {
            return false;
        }
        DeviceDetails deviceDetails = (DeviceDetails) obj;
        return Helper.equals(this.deviceName, deviceDetails.deviceName) && Helper.equals(this.deviceSerial, deviceDetails.deviceSerial) && Helper.equals(this.softwareVersionIndex, deviceDetails.softwareVersionIndex) && Helper.equals(this.hardwareVersion, deviceDetails.hardwareVersion) && Helper.equals(this.productIndex, deviceDetails.productIndex) && Helper.equals(this.authMaterialIndex, deviceDetails.authMaterialIndex) && Helper.equals(this.sdkVersion, deviceDetails.sdkVersion) && Helper.equals(this.deviceModel, deviceDetails.deviceModel) && Helper.equals(this.manufacturer, deviceDetails.manufacturer) && Helper.equals(this.firmwareVersion, deviceDetails.firmwareVersion);
    }

    public String getAuthMaterialIndex() {
        return this.authMaterialIndex;
    }

    public String getDeviceModel() {
        return this.deviceModel;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public String getDeviceSerial() {
        return this.deviceSerial;
    }

    public String getFirmwareVersion() {
        return this.firmwareVersion;
    }

    public String getHardwareVersion() {
        return this.hardwareVersion;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public String getProductIndex() {
        return this.productIndex;
    }

    public String getSdkVersion() {
        return this.sdkVersion;
    }

    public String getSoftwareVersionIndex() {
        return this.softwareVersionIndex;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.deviceName, this.deviceSerial, this.softwareVersionIndex, this.hardwareVersion, this.productIndex, this.authMaterialIndex, this.sdkVersion, this.deviceModel, this.manufacturer, this.firmwareVersion);
    }

    public void setAuthMaterialIndex(String str) {
        this.authMaterialIndex = str;
    }

    public void setDeviceModel(String str) {
        this.deviceModel = str;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public void setDeviceSerial(String str) {
        this.deviceSerial = str;
    }

    public void setFirmwareVersion(String str) {
        this.firmwareVersion = str;
    }

    public void setHardwareVersion(String str) {
        this.hardwareVersion = str;
    }

    public void setManufacturer(String str) {
        this.manufacturer = str;
    }

    public void setProductIndex(String str) {
        this.productIndex = str;
    }

    public void setSdkVersion(String str) {
        this.sdkVersion = str;
    }

    public void setSoftwareVersionIndex(String str) {
        this.softwareVersionIndex = str;
    }
}
