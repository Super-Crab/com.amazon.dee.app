package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class ProvisionerInfo implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.ProvisionerInfo");
    private String application;
    private String applicationVersion;
    private String deviceModel;
    private String firmwareVersion;
    private String manufacturer;

    public boolean equals(Object obj) {
        if (!(obj instanceof ProvisionerInfo)) {
            return false;
        }
        ProvisionerInfo provisionerInfo = (ProvisionerInfo) obj;
        return Helper.equals(this.deviceModel, provisionerInfo.deviceModel) && Helper.equals(this.manufacturer, provisionerInfo.manufacturer) && Helper.equals(this.applicationVersion, provisionerInfo.applicationVersion) && Helper.equals(this.firmwareVersion, provisionerInfo.firmwareVersion) && Helper.equals(this.application, provisionerInfo.application);
    }

    public String getApplication() {
        return this.application;
    }

    public String getApplicationVersion() {
        return this.applicationVersion;
    }

    public String getDeviceModel() {
        return this.deviceModel;
    }

    public String getFirmwareVersion() {
        return this.firmwareVersion;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.deviceModel, this.manufacturer, this.applicationVersion, this.firmwareVersion, this.application);
    }

    public void setApplication(String str) {
        this.application = str;
    }

    public void setApplicationVersion(String str) {
        this.applicationVersion = str;
    }

    public void setDeviceModel(String str) {
        this.deviceModel = str;
    }

    public void setFirmwareVersion(String str) {
        this.firmwareVersion = str;
    }

    public void setManufacturer(String str) {
        this.manufacturer = str;
    }
}
