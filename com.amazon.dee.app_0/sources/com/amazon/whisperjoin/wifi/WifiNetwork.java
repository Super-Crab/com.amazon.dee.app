package com.amazon.whisperjoin.wifi;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.whisperjoin.provisioning.bluetooth.request.serializers.Validatable;
import com.amazon.whisperjoin.utils.InputValidator;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class WifiNetwork implements Serializable, Validatable {
    protected WifiKeyManagement keyMgmt;
    protected String ssid;

    public WifiNetwork(String str, WifiKeyManagement wifiKeyManagement) {
        if (str != null && str.startsWith(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED) && str.endsWith(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED)) {
            this.ssid = GeneratedOutlineSupport1.outline51(str, 1, 1);
            this.keyMgmt = wifiKeyManagement;
            validateInternal();
            return;
        }
        throw new IllegalArgumentException("Invalid wifi network. SSID is not quoted. Please use com.amazon.whisperjoin.utils.InputValidator to validate wifi parameters.");
    }

    private void validateInternal() {
        if (!InputValidator.isValidSsid(getSsid()) || !InputValidator.isValidWifiKeyManagement(this.keyMgmt)) {
            throw new IllegalArgumentException("Invalid wifi network. Please use com.amazon.whisperjoin.utils.InputValidator to validate wifi parameters.");
        }
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof WifiNetwork;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof WifiNetwork)) {
            return false;
        }
        WifiNetwork wifiNetwork = (WifiNetwork) obj;
        if (!wifiNetwork.canEqual(this)) {
            return false;
        }
        String ssid = getSsid();
        String ssid2 = wifiNetwork.getSsid();
        if (ssid != null ? !ssid.equals(ssid2) : ssid2 != null) {
            return false;
        }
        WifiKeyManagement keyMgmt = getKeyMgmt();
        WifiKeyManagement keyMgmt2 = wifiNetwork.getKeyMgmt();
        return keyMgmt != null ? keyMgmt.equals(keyMgmt2) : keyMgmt2 == null;
    }

    public WifiKeyManagement getKeyMgmt() {
        return this.keyMgmt;
    }

    public String getSsid() {
        return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED), this.ssid, EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
    }

    public int hashCode() {
        String ssid = getSsid();
        int i = 43;
        int hashCode = ssid == null ? 43 : ssid.hashCode();
        WifiKeyManagement keyMgmt = getKeyMgmt();
        int i2 = (hashCode + 59) * 59;
        if (keyMgmt != null) {
            i = keyMgmt.hashCode();
        }
        return i2 + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WifiNetwork(ssid=");
        outline107.append(getSsid());
        outline107.append(", keyMgmt=");
        outline107.append(getKeyMgmt());
        outline107.append(")");
        return outline107.toString();
    }

    public void validate() {
        validateInternal();
    }

    public WifiNetwork(WifiNetwork wifiNetwork) {
        this.ssid = wifiNetwork.ssid;
        this.keyMgmt = wifiNetwork.keyMgmt;
    }
}
