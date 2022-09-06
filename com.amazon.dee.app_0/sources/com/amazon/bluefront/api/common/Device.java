package com.amazon.bluefront.api.common;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
/* loaded from: classes11.dex */
public class Device implements Comparable<Device> {
    private String mDeviceNamespace;
    private String mDeviceSerialNumber;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof Device) && compareTo((Device) obj) == 0;
    }

    public String getDeviceNamespace() {
        return this.mDeviceNamespace;
    }

    public String getDeviceSerialNumber() {
        return this.mDeviceSerialNumber;
    }

    public int hashCode() {
        return new HashCodeBuilder().append(getDeviceNamespace()).append(getDeviceSerialNumber()).toHashCode();
    }

    public void setDeviceNamespace(String str) {
        this.mDeviceNamespace = str;
    }

    public void setDeviceSerialNumber(String str) {
        this.mDeviceSerialNumber = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(Device device) {
        if (device == null) {
            return -1;
        }
        if (device != this) {
            return new CompareToBuilder().append(getDeviceNamespace(), device.getDeviceNamespace()).append(getDeviceSerialNumber(), device.getDeviceSerialNumber()).toComparison();
        }
        return 0;
    }
}
