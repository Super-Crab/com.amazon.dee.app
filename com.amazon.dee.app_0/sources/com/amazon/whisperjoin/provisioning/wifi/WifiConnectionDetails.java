package com.amazon.whisperjoin.provisioning.wifi;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.whisperjoin.provisioning.bluetooth.request.serializers.Validatable;
import com.amazon.whisperjoin.utils.InputValidator;
import com.amazon.whisperjoin.wifi.WifiKeyManagement;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes13.dex */
public class WifiConnectionDetails implements Validatable {
    public static final int ASSOCIATED = 3;
    public static final int CONNECTED = 2;
    public static final int CONNECTED_BEHIND_CAPTIVE_PORTAL = 5;
    public static final int CONNECTED_LIMITED_CONNECTIVITY = 4;
    public static final int CONNECTING = 1;
    @Deprecated
    public static final int CONNECTION_FAILED_ASSOCIATION_TIMED_OUT = -12;
    public static final int CONNECTION_FAILED_AUTHENTICATION_FAILED = -4;
    @Deprecated
    public static final int CONNECTION_FAILED_CAPTIVE_PORTAL = -11;
    @Deprecated
    public static final int CONNECTION_FAILED_DHCP_LEASE_FAILED = -7;
    @Deprecated
    public static final int CONNECTION_FAILED_INTERNET_UNREACHABLE = -10;
    @Deprecated
    public static final int CONNECTION_FAILED_IP_CONFLICT = -9;
    @Deprecated
    public static final int CONNECTION_FAILED_LOW_SIGNAL = -3;
    @Deprecated
    public static final int CONNECTION_FAILED_NO_AP_RESPONSE = -5;
    @Deprecated
    public static final int CONNECTION_FAILED_NO_DHCP_SERVER = -6;
    @Deprecated
    public static final int CONNECTION_FAILED_NO_DNS_SERVER = -8;
    @Deprecated
    public static final int CONNECTION_FAILED_NO_NETWORK_AVAILABLE = -2;
    public static final int CONNECTION_FAILED_OTHER = -1;
    public static final int DISCONNECTED = 0;
    private WifiKeyManagement keyMgmt;
    @SerializedName("msg")
    private String message;
    private String ssid;
    private Integer state;

    public WifiConnectionDetails(String str, WifiKeyManagement wifiKeyManagement, Integer num) {
        if (str != null && (!str.startsWith(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED) || !str.endsWith(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED))) {
            throw new IllegalArgumentException("SSID is not quoted. Please use com.amazon.whisperjoin.utils.InputValidator to validate wifi parameters.");
        }
        this.ssid = str == null ? null : GeneratedOutlineSupport1.outline51(str, 1, 1);
        this.keyMgmt = wifiKeyManagement;
        this.state = num;
        validate();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof WifiConnectionDetails;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof WifiConnectionDetails)) {
            return false;
        }
        WifiConnectionDetails wifiConnectionDetails = (WifiConnectionDetails) obj;
        if (!wifiConnectionDetails.canEqual(this)) {
            return false;
        }
        String ssid = getSsid();
        String ssid2 = wifiConnectionDetails.getSsid();
        if (ssid != null ? !ssid.equals(ssid2) : ssid2 != null) {
            return false;
        }
        WifiKeyManagement keyMgmt = getKeyMgmt();
        WifiKeyManagement keyMgmt2 = wifiConnectionDetails.getKeyMgmt();
        if (keyMgmt != null ? !keyMgmt.equals(keyMgmt2) : keyMgmt2 != null) {
            return false;
        }
        Integer state = getState();
        Integer state2 = wifiConnectionDetails.getState();
        if (state != null ? !state.equals(state2) : state2 != null) {
            return false;
        }
        String message = getMessage();
        String message2 = wifiConnectionDetails.getMessage();
        return message != null ? message.equals(message2) : message2 == null;
    }

    public WifiKeyManagement getKeyMgmt() {
        return this.keyMgmt;
    }

    public String getMessage() {
        return this.message;
    }

    public String getSsid() {
        return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED), this.ssid, EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
    }

    public Integer getState() {
        return this.state;
    }

    public int hashCode() {
        String ssid = getSsid();
        int i = 43;
        int hashCode = ssid == null ? 43 : ssid.hashCode();
        WifiKeyManagement keyMgmt = getKeyMgmt();
        int hashCode2 = ((hashCode + 59) * 59) + (keyMgmt == null ? 43 : keyMgmt.hashCode());
        Integer state = getState();
        int hashCode3 = (hashCode2 * 59) + (state == null ? 43 : state.hashCode());
        String message = getMessage();
        int i2 = hashCode3 * 59;
        if (message != null) {
            i = message.hashCode();
        }
        return i2 + i;
    }

    public void setKeyMgmt(WifiKeyManagement wifiKeyManagement) {
        this.keyMgmt = wifiKeyManagement;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setSsid(String str) {
        this.ssid = str;
    }

    public void setState(Integer num) {
        this.state = num;
        validate();
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WifiConnectionDetails(ssid=");
        outline107.append(getSsid());
        outline107.append(", keyMgmt=");
        outline107.append(getKeyMgmt());
        outline107.append(", state=");
        outline107.append(getState());
        outline107.append(", message=");
        outline107.append(getMessage());
        outline107.append(")");
        return outline107.toString();
    }

    @Override // com.amazon.whisperjoin.provisioning.bluetooth.request.serializers.Validatable
    public void validate() {
        if (this.ssid != null && !InputValidator.isValidSsid(getSsid())) {
            throw new IllegalArgumentException("Invalid ssid in wifi connection details. Please use com.amazon.whisperjoin.utils.InputValidator to validate wifi parameters.");
        }
        WifiKeyManagement wifiKeyManagement = this.keyMgmt;
        if (wifiKeyManagement != null && !InputValidator.isValidWifiKeyManagement(wifiKeyManagement)) {
            throw new IllegalArgumentException("Invalid key management in wifi connection details. Please use com.amazon.whisperjoin.utils.InputValidator to validate wifi parameters.");
        }
        if (this.state == null) {
            throw new IllegalArgumentException("WifiConnectionDetails does not support a null state value.");
        }
    }
}
