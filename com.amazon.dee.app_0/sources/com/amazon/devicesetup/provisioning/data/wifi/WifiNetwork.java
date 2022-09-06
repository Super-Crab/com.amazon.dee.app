package com.amazon.devicesetup.provisioning.data.wifi;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.devicesetup.utility.InputValidator;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public class WifiNetwork {
    protected String ssid;
    protected WifiKeyManagement wifiKeyManagement;

    public WifiNetwork(String str, WifiKeyManagement wifiKeyManagement) {
        if (str != null && str.startsWith(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED) && str.endsWith(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED)) {
            this.ssid = GeneratedOutlineSupport1.outline51(str, 1, 1);
            this.wifiKeyManagement = wifiKeyManagement;
            validateInternal();
            return;
        }
        throw new IllegalArgumentException("Invalid wifi network. SSID is not quoted. Please use com.amazon.whisperjoin.common.sharedtypes.utility.InputValidator to validate wifi parameters.");
    }

    private void validateInternal() {
        if (!InputValidator.isValidSsid(getSsid()) || !InputValidator.isValidWifiKeyManagement(this.wifiKeyManagement)) {
            throw new IllegalArgumentException("Invalid wifi network. Please use com.amazon.whisperjoin.common.sharedtypes.utility.InputValidator to validate wifi parameters.");
        }
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof WifiNetwork)) {
            if (obj == this) {
                return true;
            }
            WifiNetwork wifiNetwork = (WifiNetwork) obj;
            return this.ssid.equals(wifiNetwork.ssid) && this.wifiKeyManagement.equals(wifiNetwork.wifiKeyManagement);
        }
        return false;
    }

    public WifiKeyManagement getKeyManagement() {
        return this.wifiKeyManagement;
    }

    public String getSsid() {
        return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED), this.ssid, EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
    }

    public int hashCode() {
        return this.wifiKeyManagement.ordinal() + GeneratedOutlineSupport1.outline7(this.ssid, 41, 41);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WifiNetwork [ssid=");
        outline107.append(this.ssid);
        outline107.append(", key-mgmt=");
        outline107.append(this.wifiKeyManagement);
        outline107.append("]");
        return outline107.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void validate() {
        validateInternal();
    }

    public WifiNetwork(WifiNetwork wifiNetwork) {
        this.ssid = wifiNetwork.ssid;
        this.wifiKeyManagement = wifiNetwork.wifiKeyManagement;
    }
}
