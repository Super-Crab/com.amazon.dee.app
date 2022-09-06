package com.amazon.devicesetup.common.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class WifiScanData implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetup.common.v1.WifiScanData");
    private String bssid;
    private int frequency;
    private int rssi;
    private String securityProtocol;
    private String ssid;

    public boolean equals(Object obj) {
        if (!(obj instanceof WifiScanData)) {
            return false;
        }
        WifiScanData wifiScanData = (WifiScanData) obj;
        return Helper.equals(this.bssid, wifiScanData.bssid) && Helper.equals(Integer.valueOf(this.frequency), Integer.valueOf(wifiScanData.frequency)) && Helper.equals(Integer.valueOf(this.rssi), Integer.valueOf(wifiScanData.rssi)) && Helper.equals(this.ssid, wifiScanData.ssid) && Helper.equals(this.securityProtocol, wifiScanData.securityProtocol);
    }

    public String getBssid() {
        return this.bssid;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public int getRssi() {
        return this.rssi;
    }

    public String getSecurityProtocol() {
        return this.securityProtocol;
    }

    public String getSsid() {
        return this.ssid;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.bssid, Integer.valueOf(this.frequency), Integer.valueOf(this.rssi), this.ssid, this.securityProtocol);
    }

    public void setBssid(String str) {
        this.bssid = str;
    }

    public void setFrequency(int i) {
        this.frequency = i;
    }

    public void setRssi(int i) {
        this.rssi = i;
    }

    public void setSecurityProtocol(String str) {
        this.securityProtocol = str;
    }

    public void setSsid(String str) {
        this.ssid = str;
    }
}
