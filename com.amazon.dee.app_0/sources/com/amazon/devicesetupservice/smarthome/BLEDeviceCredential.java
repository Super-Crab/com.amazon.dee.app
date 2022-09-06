package com.amazon.devicesetupservice.smarthome;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class BLEDeviceCredential implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.smarthome.BLEDeviceCredential");
    private String macAddress;
    private int rssi;
    private String scanRecord;
    private int timestamp;

    public boolean equals(Object obj) {
        if (!(obj instanceof BLEDeviceCredential)) {
            return false;
        }
        BLEDeviceCredential bLEDeviceCredential = (BLEDeviceCredential) obj;
        return Helper.equals(Integer.valueOf(this.timestamp), Integer.valueOf(bLEDeviceCredential.timestamp)) && Helper.equals(Integer.valueOf(this.rssi), Integer.valueOf(bLEDeviceCredential.rssi)) && Helper.equals(this.scanRecord, bLEDeviceCredential.scanRecord) && Helper.equals(this.macAddress, bLEDeviceCredential.macAddress);
    }

    public String getMacAddress() {
        return this.macAddress;
    }

    public int getRssi() {
        return this.rssi;
    }

    public String getScanRecord() {
        return this.scanRecord;
    }

    public int getTimestamp() {
        return this.timestamp;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), Integer.valueOf(this.timestamp), Integer.valueOf(this.rssi), this.scanRecord, this.macAddress);
    }

    public void setMacAddress(String str) {
        this.macAddress = str;
    }

    public void setRssi(int i) {
        this.rssi = i;
    }

    public void setScanRecord(String str) {
        this.scanRecord = str;
    }

    public void setTimestamp(int i) {
        this.timestamp = i;
    }
}
