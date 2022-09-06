package com.amazon.devicesetupservice.wss1p;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class DiscoveredDevice implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.wss1p.DiscoveredDevice");
    private String encodedSsid;
    private String macAddress;
    private int rssi;

    public boolean equals(Object obj) {
        if (!(obj instanceof DiscoveredDevice)) {
            return false;
        }
        DiscoveredDevice discoveredDevice = (DiscoveredDevice) obj;
        return Helper.equals(Integer.valueOf(this.rssi), Integer.valueOf(discoveredDevice.rssi)) && Helper.equals(this.macAddress, discoveredDevice.macAddress) && Helper.equals(this.encodedSsid, discoveredDevice.encodedSsid);
    }

    public String getEncodedSsid() {
        return this.encodedSsid;
    }

    public String getMacAddress() {
        return this.macAddress;
    }

    public int getRssi() {
        return this.rssi;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), Integer.valueOf(this.rssi), this.macAddress, this.encodedSsid);
    }

    public void setEncodedSsid(String str) {
        this.encodedSsid = str;
    }

    public void setMacAddress(String str) {
        this.macAddress = str;
    }

    public void setRssi(int i) {
        this.rssi = i;
    }
}
