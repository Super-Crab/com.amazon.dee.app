package com.amazon.devicesetupservice.scap.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class BleEntryData implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.scap.v1.BleEntryData");
    private String deviceId;
    private ScanData scanData;

    public boolean equals(Object obj) {
        if (!(obj instanceof BleEntryData)) {
            return false;
        }
        BleEntryData bleEntryData = (BleEntryData) obj;
        return Helper.equals(this.scanData, bleEntryData.scanData) && Helper.equals(this.deviceId, bleEntryData.deviceId);
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public ScanData getScanData() {
        return this.scanData;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.scanData, this.deviceId);
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setScanData(ScanData scanData) {
        this.scanData = scanData;
    }
}
