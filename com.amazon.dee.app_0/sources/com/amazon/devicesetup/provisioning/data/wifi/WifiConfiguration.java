package com.amazon.devicesetup.provisioning.data.wifi;

import com.amazon.devicesetup.utility.InputValidator;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public class WifiConfiguration extends WifiBaseConfiguration {
    private String mPsk;
    private String mWepKey;

    private WifiConfiguration(String str, WifiKeyManagement wifiKeyManagement, String str2, String str3, int i, boolean z) {
        super(str, wifiKeyManagement, i, z);
        if (wifiKeyManagement == WifiKeyManagement.WEP) {
            this.mWepKey = str2;
        } else if (wifiKeyManagement == WifiKeyManagement.WPA_PSK) {
            this.mPsk = str3;
        }
        validate();
    }

    public static WifiConfiguration createOpenWifiConfiguration(String str) {
        return new WifiConfiguration(str, WifiKeyManagement.NONE, null, null, 0, false);
    }

    public static WifiConfiguration createWepWifiConfiguration(String str, String str2) {
        return new WifiConfiguration(str, WifiKeyManagement.WEP, str2, null, 0, false);
    }

    public static WifiConfiguration createWpaWifiConfiguration(String str, String str2) {
        return new WifiConfiguration(str, WifiKeyManagement.WPA_PSK, null, str2, 0, false);
    }

    @Override // com.amazon.devicesetup.provisioning.data.wifi.WifiBaseConfiguration, com.amazon.devicesetup.provisioning.data.wifi.WifiNetwork
    public boolean equals(Object obj) {
        if (obj instanceof WifiConfiguration) {
            WifiConfiguration wifiConfiguration = (WifiConfiguration) obj;
            if (this.mPsk != null) {
                return super.equals(obj) && this.mPsk.equals(wifiConfiguration.mPsk);
            } else if (this.mWepKey == null) {
                return super.equals(obj);
            } else {
                return super.equals(obj) && this.mWepKey.equals(wifiConfiguration.mWepKey);
            }
        }
        return false;
    }

    public String getPsk() {
        return this.mPsk;
    }

    public String getWepKey() {
        return this.mWepKey;
    }

    @Override // com.amazon.devicesetup.provisioning.data.wifi.WifiBaseConfiguration, com.amazon.devicesetup.provisioning.data.wifi.WifiNetwork
    public int hashCode() {
        int hashCode = (super.hashCode() + 41) * 41;
        String str = this.mWepKey;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 41;
        String str2 = this.mPsk;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode2 + i;
    }

    @Override // com.amazon.devicesetup.provisioning.data.wifi.WifiBaseConfiguration, com.amazon.devicesetup.provisioning.data.wifi.WifiNetwork
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WifiConfiguration [ssid=");
        outline107.append(this.ssid);
        outline107.append(", key-mgmt=");
        outline107.append(this.wifiKeyManagement);
        outline107.append("]");
        return outline107.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.devicesetup.provisioning.data.wifi.WifiNetwork
    public void validate() {
        super.validate();
        if (this.mPsk != null && this.mWepKey != null) {
            throw new IllegalArgumentException("Wifi configuration cannot have both WEP-key and PSK. Please use com.amazon.whisperjoin.common.sharedtypes.utility.InputValidator to validate wifi parameters.");
        }
        if (this.mPsk != null && !InputValidator.isValidWPAWifiConfiguration(this.wifiKeyManagement, getPsk())) {
            throw new IllegalArgumentException("Invalid PSK in wifi configuration. Please use com.amazon.whisperjoin.common.sharedtypes.utility.InputValidator to validate wifi parameters.");
        }
        String str = this.mWepKey;
        if (str != null && !InputValidator.isValidWEPWifiConfiguration(this.wifiKeyManagement, str)) {
            throw new IllegalArgumentException("Invalid WEP-key in wifi configuration. Please use com.amazon.whisperjoin.common.sharedtypes.utility.InputValidator to validate wifi parameters.");
        }
        if (this.mPsk != null || this.mWepKey != null || InputValidator.isValidOpenWifiConfiguration(this.wifiKeyManagement)) {
            return;
        }
        throw new IllegalArgumentException("Invalid open wifi configuration. Please use com.amazon.whisperjoin.common.sharedtypes.utility.InputValidator to validate wifi parameters.");
    }

    public static WifiConfiguration createOpenWifiConfiguration(String str, int i, boolean z) {
        return new WifiConfiguration(str, WifiKeyManagement.NONE, null, null, i, z);
    }

    public static WifiConfiguration createWepWifiConfiguration(String str, String str2, int i, boolean z) {
        return new WifiConfiguration(str, WifiKeyManagement.WEP, str2, null, i, z);
    }

    public static WifiConfiguration createWpaWifiConfiguration(String str, String str2, int i, boolean z) {
        return new WifiConfiguration(str, WifiKeyManagement.WPA_PSK, null, str2, i, z);
    }
}
