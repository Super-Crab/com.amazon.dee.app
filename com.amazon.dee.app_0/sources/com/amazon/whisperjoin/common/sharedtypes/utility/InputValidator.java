package com.amazon.whisperjoin.common.sharedtypes.utility;

import android.text.TextUtils;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiKeyManagement;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
/* loaded from: classes13.dex */
public class InputValidator {
    public static final int PSK_MAX_LENGTH = 63;
    public static final int PSK_MIN_LENGTH = 8;
    public static final int SSID_MAX_LENGTH = 32;
    public static final int SSID_MIN_LENGTH = 1;

    public static int getValidRssi(int i) {
        if (-100 > i || i > 0) {
            return -100;
        }
        return i;
    }

    private static boolean isAsciiString(String str) {
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt < 0 || charAt > 255) {
                return false;
            }
        }
        return true;
    }

    private static boolean isHexString(String str) {
        for (int i = 0; i < str.length(); i++) {
            if ("0123456789ABCDEFabcdef".indexOf(str.charAt(i)) == -1) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidFrequency(int i) {
        return i >= 0;
    }

    public static boolean isValidKeyExchangeRequest(String str) {
        return !TextUtils.isEmpty(str);
    }

    public static boolean isValidKeyExchangeResponse(String str) {
        return !TextUtils.isEmpty(str);
    }

    public static boolean isValidOpenWifiConfiguration(WifiKeyManagement wifiKeyManagement) {
        return wifiKeyManagement == WifiKeyManagement.NONE;
    }

    public static boolean isValidPreauthorizedLinkCode(String str) {
        return !TextUtils.isEmpty(str);
    }

    public static boolean isValidPsk(String str) {
        if (str == null || !str.startsWith(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED) || !str.endsWith(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED) || str.length() - 2 < 8 || str.length() - 2 > 63) {
            return false;
        }
        for (int i = 1; i < str.length() - 1; i++) {
            char charAt = str.charAt(i);
            if (charAt < ' ' || charAt > '~') {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidRegistrationToken(String str) {
        return !TextUtils.isEmpty(str);
    }

    public static boolean isValidSsid(String str) {
        return str != null && str.startsWith(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED) && str.endsWith(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED) && str.length() + (-2) >= 1 && str.length() + (-2) <= 32;
    }

    public static boolean isValidWEPWifiConfiguration(WifiKeyManagement wifiKeyManagement, String str) {
        return wifiKeyManagement == WifiKeyManagement.WEP && isValidWepKey(str);
    }

    public static boolean isValidWPAWifiConfiguration(WifiKeyManagement wifiKeyManagement, String str) {
        return wifiKeyManagement == WifiKeyManagement.WPA_PSK && isValidPsk(str);
    }

    public static boolean isValidWepKey(String str) {
        if (str == null) {
            return false;
        }
        if (str.startsWith(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED) && str.endsWith(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED)) {
            if (!Collections.unmodifiableSet(new HashSet(Arrays.asList(5, 13, 16, 29))).contains(Integer.valueOf(str.length() - 2))) {
                return false;
            }
            return isAsciiString(str);
        } else if (!Collections.unmodifiableSet(new HashSet(Arrays.asList(10, 26, 32, 58))).contains(Integer.valueOf(str.length()))) {
            return false;
        } else {
            return isHexString(str);
        }
    }

    public static boolean isValidWifiKeyManagement(WifiKeyManagement wifiKeyManagement) {
        return wifiKeyManagement == WifiKeyManagement.NONE || wifiKeyManagement == WifiKeyManagement.WEP || wifiKeyManagement == WifiKeyManagement.WPA_PSK;
    }
}
