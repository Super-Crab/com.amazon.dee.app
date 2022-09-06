package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetup.common.v1.WifiCredentials;
import com.amazon.devicesetup.common.v1.WifiScanData;
/* loaded from: classes12.dex */
public class WifiNetworkDetail implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.WifiNetworkDetail");
    private WifiCredentials credential;
    private Boolean preferredNetwork;
    private WifiScanData scanData;

    public boolean equals(Object obj) {
        if (!(obj instanceof WifiNetworkDetail)) {
            return false;
        }
        WifiNetworkDetail wifiNetworkDetail = (WifiNetworkDetail) obj;
        return Helper.equals(this.preferredNetwork, wifiNetworkDetail.preferredNetwork) && Helper.equals(this.scanData, wifiNetworkDetail.scanData) && Helper.equals(this.credential, wifiNetworkDetail.credential);
    }

    public WifiCredentials getCredential() {
        return this.credential;
    }

    public WifiScanData getScanData() {
        return this.scanData;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.preferredNetwork, this.scanData, this.credential);
    }

    public Boolean isPreferredNetwork() {
        return this.preferredNetwork;
    }

    public void setCredential(WifiCredentials wifiCredentials) {
        this.credential = wifiCredentials;
    }

    public void setPreferredNetwork(Boolean bool) {
        this.preferredNetwork = bool;
    }

    public void setScanData(WifiScanData wifiScanData) {
        this.scanData = wifiScanData;
    }
}
