package com.amazon.devicesetup.common.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class WifiCredentials implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetup.common.v1.WifiCredentials");
    private int frequency;
    private String key;
    private int keyIndex;
    private int priority;
    private String securityProtocol;
    private String ssid;

    public boolean equals(Object obj) {
        if (!(obj instanceof WifiCredentials)) {
            return false;
        }
        WifiCredentials wifiCredentials = (WifiCredentials) obj;
        return Helper.equals(Integer.valueOf(this.keyIndex), Integer.valueOf(wifiCredentials.keyIndex)) && Helper.equals(Integer.valueOf(this.priority), Integer.valueOf(wifiCredentials.priority)) && Helper.equals(Integer.valueOf(this.frequency), Integer.valueOf(wifiCredentials.frequency)) && Helper.equals(this.ssid, wifiCredentials.ssid) && Helper.equals(this.securityProtocol, wifiCredentials.securityProtocol) && Helper.equals(this.key, wifiCredentials.key);
    }

    public int getFrequency() {
        return this.frequency;
    }

    public String getKey() {
        return this.key;
    }

    public int getKeyIndex() {
        return this.keyIndex;
    }

    public int getPriority() {
        return this.priority;
    }

    public String getSecurityProtocol() {
        return this.securityProtocol;
    }

    public String getSsid() {
        return this.ssid;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), Integer.valueOf(this.keyIndex), Integer.valueOf(this.priority), Integer.valueOf(this.frequency), this.ssid, this.securityProtocol, this.key);
    }

    public void setFrequency(int i) {
        this.frequency = i;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public void setKeyIndex(int i) {
        this.keyIndex = i;
    }

    public void setPriority(int i) {
        this.priority = i;
    }

    public void setSecurityProtocol(String str) {
        this.securityProtocol = str;
    }

    public void setSsid(String str) {
        this.ssid = str;
    }
}
