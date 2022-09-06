package com.amazon.matter.data;

import lombok.Generated;
/* loaded from: classes9.dex */
public class AddAndEnableNetworkRequest {
    private long fabricId;
    private long nodeId;
    private String password;
    private String ssid;

    @Generated
    public AddAndEnableNetworkRequest(long j, long j2, String str, String str2) {
        this.fabricId = j;
        this.nodeId = j2;
        this.ssid = str;
        this.password = str2;
    }

    @Generated
    public long getFabricId() {
        return this.fabricId;
    }

    @Generated
    public long getNodeId() {
        return this.nodeId;
    }

    @Generated
    public String getPassword() {
        return this.password;
    }

    @Generated
    public String getSsid() {
        return this.ssid;
    }
}
