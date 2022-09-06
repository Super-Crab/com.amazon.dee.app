package com.amazon.credentiallocker;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class WifiNetwork implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.credentiallocker.WifiNetwork");
    private String bssid;
    private String keyManagement;
    private String ssid;

    public boolean equals(Object obj) {
        if (!(obj instanceof WifiNetwork)) {
            return false;
        }
        WifiNetwork wifiNetwork = (WifiNetwork) obj;
        return Helper.equals(this.keyManagement, wifiNetwork.keyManagement) && Helper.equals(this.bssid, wifiNetwork.bssid) && Helper.equals(this.ssid, wifiNetwork.ssid);
    }

    public String getBssid() {
        return this.bssid;
    }

    public String getKeyManagement() {
        return this.keyManagement;
    }

    public String getSsid() {
        return this.ssid;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.keyManagement, this.bssid, this.ssid);
    }

    public void setBssid(String str) {
        this.bssid = str;
    }

    public void setKeyManagement(String str) {
        this.keyManagement = str;
    }

    public void setSsid(String str) {
        this.ssid = str;
    }
}
