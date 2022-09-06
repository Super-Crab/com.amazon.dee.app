package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class WifiNetworkInfo implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.WifiNetworkInfo");
    private String keyManagement;
    private long lastUpdatedDateUtcMillis;
    private String networkState;
    private String ssid;

    public boolean equals(Object obj) {
        if (!(obj instanceof WifiNetworkInfo)) {
            return false;
        }
        WifiNetworkInfo wifiNetworkInfo = (WifiNetworkInfo) obj;
        return Helper.equals(Long.valueOf(this.lastUpdatedDateUtcMillis), Long.valueOf(wifiNetworkInfo.lastUpdatedDateUtcMillis)) && Helper.equals(this.keyManagement, wifiNetworkInfo.keyManagement) && Helper.equals(this.ssid, wifiNetworkInfo.ssid) && Helper.equals(this.networkState, wifiNetworkInfo.networkState);
    }

    public String getKeyManagement() {
        return this.keyManagement;
    }

    public long getLastUpdatedDateUtcMillis() {
        return this.lastUpdatedDateUtcMillis;
    }

    public String getNetworkState() {
        return this.networkState;
    }

    public String getSsid() {
        return this.ssid;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), Long.valueOf(this.lastUpdatedDateUtcMillis), this.keyManagement, this.ssid, this.networkState);
    }

    public void setKeyManagement(String str) {
        this.keyManagement = str;
    }

    public void setLastUpdatedDateUtcMillis(long j) {
        this.lastUpdatedDateUtcMillis = j;
    }

    public void setNetworkState(String str) {
        this.networkState = str;
    }

    public void setSsid(String str) {
        this.ssid = str;
    }
}
