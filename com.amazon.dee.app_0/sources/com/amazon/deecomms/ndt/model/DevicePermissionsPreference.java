package com.amazon.deecomms.ndt.model;

import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes12.dex */
public class DevicePermissionsPreference {
    @JsonProperty("allowed")
    private boolean allowed;
    @JsonProperty("state")
    private String state;

    public boolean getAllowed() {
        return this.allowed;
    }

    public String getState() {
        return this.state;
    }

    public void setAllowed(boolean z) {
        this.allowed = z;
    }

    public void setState(String str) {
        this.state = str;
    }
}
