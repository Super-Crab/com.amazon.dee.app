package com.amazon.deecomms.ndt.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes12.dex */
public class GetDeviceCommsPreferencesResponse {
    @JsonProperty("deviceId")
    private String deviceId;
    @JsonProperty("devicePermissionsPreferences")
    private List<DevicePermissionsPreference> devicePermissionsPreferences;
    @JsonProperty("deviceType")
    private String deviceType;

    public String getDeviceId() {
        return this.deviceId;
    }

    public List<DevicePermissionsPreference> getDevicePermissionsPreferences() {
        return this.devicePermissionsPreferences;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setDevicePermissionsPreferences(List<DevicePermissionsPreference> list) {
        this.devicePermissionsPreferences = list;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }
}
