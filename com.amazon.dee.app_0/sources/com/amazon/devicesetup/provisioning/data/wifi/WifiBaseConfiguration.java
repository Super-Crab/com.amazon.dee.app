package com.amazon.devicesetup.provisioning.data.wifi;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public class WifiBaseConfiguration extends WifiNetwork {
    protected boolean mIsHiddenNetwork;
    protected int mNetworkPriority;

    /* JADX INFO: Access modifiers changed from: protected */
    public WifiBaseConfiguration(String str, WifiKeyManagement wifiKeyManagement, int i, boolean z) {
        super(str, wifiKeyManagement);
        this.mNetworkPriority = i;
        this.mIsHiddenNetwork = z;
    }

    public static WifiBaseConfiguration createOpenWifiConfiguration(String str) {
        return new WifiBaseConfiguration(str, WifiKeyManagement.NONE, 0, false);
    }

    public static WifiBaseConfiguration createWepWifiConfiguration(String str) {
        return new WifiBaseConfiguration(str, WifiKeyManagement.WEP, 0, false);
    }

    public static WifiBaseConfiguration createWpaWifiConfiguration(String str) {
        return new WifiBaseConfiguration(str, WifiKeyManagement.WPA_PSK, 0, false);
    }

    @Override // com.amazon.devicesetup.provisioning.data.wifi.WifiNetwork
    public boolean equals(Object obj) {
        if (obj instanceof WifiBaseConfiguration) {
            WifiBaseConfiguration wifiBaseConfiguration = (WifiBaseConfiguration) obj;
            return super.equals(this) && this.mNetworkPriority == wifiBaseConfiguration.mNetworkPriority && this.mIsHiddenNetwork == wifiBaseConfiguration.mIsHiddenNetwork;
        }
        return false;
    }

    public int getNetworkPriority() {
        return this.mNetworkPriority;
    }

    @Override // com.amazon.devicesetup.provisioning.data.wifi.WifiNetwork
    public int hashCode() {
        return ((((super.hashCode() + 41) * 41) + this.mNetworkPriority) * 41) + (this.mIsHiddenNetwork ? 1 : 0);
    }

    public boolean isHiddenNetwork() {
        return this.mIsHiddenNetwork;
    }

    @Override // com.amazon.devicesetup.provisioning.data.wifi.WifiNetwork
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WifiBaseConfiguration [ssid=");
        outline107.append(this.ssid);
        outline107.append(", key-mgmt=");
        outline107.append(this.wifiKeyManagement);
        outline107.append(", priority=");
        outline107.append(this.mNetworkPriority);
        outline107.append(", hidden=");
        return GeneratedOutlineSupport1.outline97(outline107, this.mIsHiddenNetwork, "]");
    }

    public static WifiBaseConfiguration createOpenWifiConfiguration(String str, int i, boolean z) {
        return new WifiBaseConfiguration(str, WifiKeyManagement.NONE, i, z);
    }

    public static WifiBaseConfiguration createWepWifiConfiguration(String str, int i, boolean z) {
        return new WifiBaseConfiguration(str, WifiKeyManagement.WEP, i, z);
    }

    public static WifiBaseConfiguration createWpaWifiConfiguration(String str, int i, boolean z) {
        return new WifiBaseConfiguration(str, WifiKeyManagement.WPA_PSK, i, z);
    }
}
