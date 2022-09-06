package com.amazon.devicesetupservice.smarthome;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class ZigbeeDeviceCredential implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.smarthome.ZigbeeDeviceCredential");
    private String installCode;
    private String macAddress;

    public boolean equals(Object obj) {
        if (!(obj instanceof ZigbeeDeviceCredential)) {
            return false;
        }
        ZigbeeDeviceCredential zigbeeDeviceCredential = (ZigbeeDeviceCredential) obj;
        return Helper.equals(this.macAddress, zigbeeDeviceCredential.macAddress) && Helper.equals(this.installCode, zigbeeDeviceCredential.installCode);
    }

    public String getInstallCode() {
        return this.installCode;
    }

    public String getMacAddress() {
        return this.macAddress;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.macAddress, this.installCode);
    }

    public void setInstallCode(String str) {
        this.installCode = str;
    }

    public void setMacAddress(String str) {
        this.macAddress = str;
    }
}
