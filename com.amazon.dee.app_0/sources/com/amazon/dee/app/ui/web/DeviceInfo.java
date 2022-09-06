package com.amazon.dee.app.ui.web;
/* loaded from: classes12.dex */
public class DeviceInfo {
    public final String accountName;
    public final String deviceType;
    public final String serialNumber;

    public DeviceInfo(String str, String str2, String str3) {
        this.accountName = str;
        this.serialNumber = str2;
        this.deviceType = str3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DeviceInfo.class != obj.getClass()) {
            return false;
        }
        DeviceInfo deviceInfo = (DeviceInfo) obj;
        return this.accountName.equals(deviceInfo.accountName) && this.serialNumber.equals(deviceInfo.serialNumber);
    }

    public int hashCode() {
        return this.serialNumber.hashCode() + (this.accountName.hashCode() * 31);
    }
}
