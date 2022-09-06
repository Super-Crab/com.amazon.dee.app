package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class ProvisionableInfo implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.ProvisionableInfo");
    private String deviceModel;
    private String deviceName;
    private String deviceSerial;
    private String firmwareVersion;
    private String hardwareVersion;
    private String manufacturer;
    private String sdkVersion;
    private String softwareVersionIndex;

    public boolean equals(Object obj) {
        if (!(obj instanceof ProvisionableInfo)) {
            return false;
        }
        ProvisionableInfo provisionableInfo = (ProvisionableInfo) obj;
        return Helper.equals(this.softwareVersionIndex, provisionableInfo.softwareVersionIndex) && Helper.equals(this.firmwareVersion, provisionableInfo.firmwareVersion) && Helper.equals(this.deviceSerial, provisionableInfo.deviceSerial) && Helper.equals(this.deviceName, provisionableInfo.deviceName) && Helper.equals(this.sdkVersion, provisionableInfo.sdkVersion) && Helper.equals(this.manufacturer, provisionableInfo.manufacturer) && Helper.equals(this.deviceModel, provisionableInfo.deviceModel) && Helper.equals(this.hardwareVersion, provisionableInfo.hardwareVersion);
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

    public String getSdkVersion() {
        return this.sdkVersion;
    }

    public String getSoftwareVersionIndex() {
        return this.softwareVersionIndex;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.softwareVersionIndex, this.firmwareVersion, this.deviceSerial, this.deviceName, this.sdkVersion, this.manufacturer, this.deviceModel, this.hardwareVersion);
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

    public void setSdkVersion(String str) {
        this.sdkVersion = str;
    }

    public void setSoftwareVersionIndex(String str) {
        this.softwareVersionIndex = str;
    }
}
