package com.amazon.deecomms.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes12.dex */
public class Device {
    @JsonProperty("deviceId")
    private String deviceId;
    @JsonProperty("isMasterDevice")
    private boolean isMasterDevice;

    public String getDeviceId() {
        return this.deviceId;
    }

    public boolean isMasterDevice() {
        return this.isMasterDevice;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setMasterDevice(boolean z) {
        this.isMasterDevice = z;
    }
}
