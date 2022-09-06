package com.amazon.deecomms.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes12.dex */
public class GetPersonalDevicesResponse {
    @JsonProperty("devices")
    private List<Device> devices;
    @JsonProperty("masterDeviceId")
    private String masterDeviceId;

    public List<Device> getDevices() {
        return this.devices;
    }

    public String getMasterDeviceId() {
        return this.masterDeviceId;
    }

    public void setDevices(List<Device> list) {
        this.devices = list;
    }

    public void setMasterDeviceId(String str) {
        this.masterDeviceId = str;
    }
}
