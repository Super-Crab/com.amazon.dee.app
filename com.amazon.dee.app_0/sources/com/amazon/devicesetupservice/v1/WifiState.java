package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class WifiState implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.WifiState");
    private String connectionState;
    private String securityProtocol;
    private String ssid;

    public boolean equals(Object obj) {
        if (!(obj instanceof WifiState)) {
            return false;
        }
        WifiState wifiState = (WifiState) obj;
        return Helper.equals(this.securityProtocol, wifiState.securityProtocol) && Helper.equals(this.connectionState, wifiState.connectionState) && Helper.equals(this.ssid, wifiState.ssid);
    }

    public String getConnectionState() {
        return this.connectionState;
    }

    public String getSecurityProtocol() {
        return this.securityProtocol;
    }

    public String getSsid() {
        return this.ssid;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.securityProtocol, this.connectionState, this.ssid);
    }

    public void setConnectionState(String str) {
        this.connectionState = str;
    }

    public void setSecurityProtocol(String str) {
        this.securityProtocol = str;
    }

    public void setSsid(String str) {
        this.ssid = str;
    }
}
