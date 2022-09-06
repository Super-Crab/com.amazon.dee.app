package com.amazon.whisperjoin.wifi;

import com.amazon.whisperjoin.provisioning.bluetooth.request.serializers.Validatable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class WifiBaseConfiguration extends WifiNetwork implements Serializable, Validatable {
    protected boolean hidden;
    protected int priority;

    /* JADX INFO: Access modifiers changed from: protected */
    public WifiBaseConfiguration(String str, WifiKeyManagement wifiKeyManagement, int i, boolean z) {
        super(str, wifiKeyManagement);
        this.priority = i;
        this.hidden = z;
    }

    public static WifiBaseConfiguration convertFromAndroidWifiConfiguration(android.net.wifi.WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration.allowedKeyManagement.get(0)) {
            String[] strArr = wifiConfiguration.wepKeys;
            if (strArr != null && strArr.length > 0 && strArr[0] != null) {
                return createWepWifiConfiguration(wifiConfiguration.SSID, wifiConfiguration.priority, wifiConfiguration.hiddenSSID);
            }
            return createOpenWifiConfiguration(wifiConfiguration.SSID, wifiConfiguration.priority, wifiConfiguration.hiddenSSID);
        } else if (!wifiConfiguration.allowedKeyManagement.get(1)) {
            return null;
        } else {
            return createWpaWifiConfiguration(wifiConfiguration.SSID, wifiConfiguration.priority, wifiConfiguration.hiddenSSID);
        }
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

    @Override // com.amazon.whisperjoin.wifi.WifiNetwork
    protected boolean canEqual(Object obj) {
        return obj instanceof WifiBaseConfiguration;
    }

    @Override // com.amazon.whisperjoin.wifi.WifiNetwork
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof WifiBaseConfiguration)) {
            return false;
        }
        WifiBaseConfiguration wifiBaseConfiguration = (WifiBaseConfiguration) obj;
        return wifiBaseConfiguration.canEqual(this) && super.equals(obj) && getPriority() == wifiBaseConfiguration.getPriority() && isHidden() == wifiBaseConfiguration.isHidden();
    }

    public int getPriority() {
        return this.priority;
    }

    @Override // com.amazon.whisperjoin.wifi.WifiNetwork
    public int hashCode() {
        return ((getPriority() + (super.hashCode() * 59)) * 59) + (isHidden() ? 79 : 97);
    }

    public boolean isHidden() {
        return this.hidden;
    }

    @Override // com.amazon.whisperjoin.wifi.WifiNetwork
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WifiBaseConfiguration(priority=");
        outline107.append(getPriority());
        outline107.append(", hidden=");
        outline107.append(isHidden());
        outline107.append(")");
        return outline107.toString();
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
