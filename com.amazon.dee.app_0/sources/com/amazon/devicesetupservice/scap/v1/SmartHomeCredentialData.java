package com.amazon.devicesetupservice.scap.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetupservice.smarthome.SmartHomeCredential;
/* loaded from: classes12.dex */
public class SmartHomeCredentialData implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.scap.v1.SmartHomeCredentialData");
    private BleEntryData bleEntryData;
    private String timestamp;
    private SmartHomeCredential zigbeeCredential;

    public boolean equals(Object obj) {
        if (!(obj instanceof SmartHomeCredentialData)) {
            return false;
        }
        SmartHomeCredentialData smartHomeCredentialData = (SmartHomeCredentialData) obj;
        return Helper.equals(this.zigbeeCredential, smartHomeCredentialData.zigbeeCredential) && Helper.equals(this.bleEntryData, smartHomeCredentialData.bleEntryData) && Helper.equals(this.timestamp, smartHomeCredentialData.timestamp);
    }

    public BleEntryData getBleEntryData() {
        return this.bleEntryData;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public SmartHomeCredential getZigbeeCredential() {
        return this.zigbeeCredential;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.zigbeeCredential, this.bleEntryData, this.timestamp);
    }

    public void setBleEntryData(BleEntryData bleEntryData) {
        this.bleEntryData = bleEntryData;
    }

    public void setTimestamp(String str) {
        this.timestamp = str;
    }

    public void setZigbeeCredential(SmartHomeCredential smartHomeCredential) {
        this.zigbeeCredential = smartHomeCredential;
    }
}
