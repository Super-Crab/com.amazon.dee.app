package com.amazon.communication;
/* loaded from: classes12.dex */
public enum NetworkType {
    WIFI("WiFi"),
    MOBILE("WAN"),
    ETHERNET("LAN");
    
    private final String mNetworkType;

    NetworkType(String str) {
        this.mNetworkType = str;
    }

    public String getNetworkType() {
        return this.mNetworkType;
    }
}
