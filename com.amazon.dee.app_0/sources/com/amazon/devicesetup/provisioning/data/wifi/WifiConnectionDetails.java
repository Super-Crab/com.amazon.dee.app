package com.amazon.devicesetup.provisioning.data.wifi;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.mode.metrics.DriveModeMetrics;
import com.amazon.devicesetup.utility.InputValidator;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public class WifiConnectionDetails {
    private String ssid;
    private State state;
    private WifiKeyManagement wifiKeyManagement;

    /* loaded from: classes12.dex */
    public enum State {
        INITIATING_CONNECTION_ATTEMPT(0, "Initiating connection attempt"),
        DISCONNECTED(1, DriveModeMetrics.ConnectionStatus.DISCONNECTED),
        CONNECTING(2, "Connecting"),
        ASSOCIATED(3, "Associated with AP"),
        CONNECTED(4, DriveModeMetrics.ConnectionStatus.CONNECTED),
        CONNECTION_FAILED_INTERNAL_ERROR(-1, "Connection failed due to internal error"),
        CONNECTED_BEHIND_CAPTIVE_PORTAL(-2, "Connected, but behind captive portal"),
        CONNECTED_LIMITED_CONNECTIVITY(-3, "Connected, but with limited connectivity"),
        CONNECTION_FAILED_PSK_AUTHENTICATION(-4, "Connection failed due to PSK authentation error"),
        CONNECTION_FAILED_AP_NOT_FOUND(-5, "Connection failed due to Access Point Not Found");
        
        private final String description;
        private final int value;

        State(int i, String str) {
            this.value = i;
            this.description = str;
        }

        static State fromInt(int i) {
            switch (i) {
                case -5:
                    return CONNECTION_FAILED_AP_NOT_FOUND;
                case -4:
                    return CONNECTION_FAILED_PSK_AUTHENTICATION;
                case -3:
                    return CONNECTED_LIMITED_CONNECTIVITY;
                case -2:
                    return CONNECTED_BEHIND_CAPTIVE_PORTAL;
                case -1:
                    return CONNECTION_FAILED_INTERNAL_ERROR;
                case 0:
                    return INITIATING_CONNECTION_ATTEMPT;
                case 1:
                    return DISCONNECTED;
                case 2:
                    return CONNECTING;
                case 3:
                    return ASSOCIATED;
                case 4:
                    return CONNECTED;
                default:
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Unknown WifiConnectionDetails.State: ", i));
            }
        }

        public String getDescription() {
            return this.description;
        }

        public int getValue() {
            return this.value;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.description;
        }
    }

    public WifiConnectionDetails(String str, WifiKeyManagement wifiKeyManagement, State state) {
        if (str != null && (!str.startsWith(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED) || !str.endsWith(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED))) {
            throw new IllegalArgumentException("SSID is not quoted. Please use com.amazon.whisperjoin.utils.InputValidator to validate wifi parameters.");
        }
        this.ssid = str == null ? null : GeneratedOutlineSupport1.outline51(str, 1, 1);
        this.wifiKeyManagement = wifiKeyManagement;
        this.state = state;
        validate();
    }

    public boolean equals(Object obj) {
        boolean equals;
        if (obj != null && (obj instanceof WifiConnectionDetails)) {
            if (obj == this) {
                return true;
            }
            WifiConnectionDetails wifiConnectionDetails = (WifiConnectionDetails) obj;
            String str = this.ssid;
            if (str == null) {
                equals = wifiConnectionDetails.ssid == null;
            } else {
                equals = str.equals(wifiConnectionDetails.ssid);
            }
            return equals && this.wifiKeyManagement == wifiConnectionDetails.wifiKeyManagement && this.state == wifiConnectionDetails.state;
        }
        return false;
    }

    public State getConnectionState() {
        return this.state;
    }

    public WifiKeyManagement getKeyManagement() {
        return this.wifiKeyManagement;
    }

    public String getSsid() {
        return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED), this.ssid, EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
    }

    public int hashCode() {
        String str = this.ssid;
        int hashCode = str == null ? 0 : str.hashCode();
        return this.state.ordinal() + ((this.wifiKeyManagement.ordinal() + ((hashCode + 41) * 41)) * 41);
    }

    public void setConnectionState(State state) {
        this.state = state;
        validate();
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WifiConnectionDetails [state=");
        outline107.append(this.state);
        outline107.append(", ssid=");
        outline107.append(this.ssid);
        outline107.append(", key-mgmt=");
        outline107.append(this.wifiKeyManagement);
        outline107.append("]");
        return outline107.toString();
    }

    protected void validate() {
        if (this.ssid != null && !InputValidator.isValidSsid(getSsid())) {
            throw new IllegalArgumentException("Invalid ssid in wifi connection details. Please use com.amazon.whisperjoin.common.sharedtypes.utility. InputValidator to validate wifi parameters.");
        }
        WifiKeyManagement wifiKeyManagement = this.wifiKeyManagement;
        if (wifiKeyManagement != null && !InputValidator.isValidWifiKeyManagement(wifiKeyManagement)) {
            throw new IllegalArgumentException("Invalid key management in wifi connection details. Please use com.amazon.whisperjoin.common.sharedtypes.utility. InputValidator to validate wifi parameters.");
        }
    }
}
