package com.amazon.whisperjoin.wifi;

import android.net.wifi.ScanResult;
import java.util.BitSet;
/* loaded from: classes13.dex */
public enum WifiKeyManagement {
    NONE,
    WPA_PSK,
    WEP,
    OTHER;

    /* renamed from: com.amazon.whisperjoin.wifi.WifiKeyManagement$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$whisperjoin$wifi$WifiKeyManagement = new int[WifiKeyManagement.values().length];

        static {
            try {
                $SwitchMap$com$amazon$whisperjoin$wifi$WifiKeyManagement[WifiKeyManagement.WEP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$wifi$WifiKeyManagement[WifiKeyManagement.WPA_PSK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$wifi$WifiKeyManagement[WifiKeyManagement.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$wifi$WifiKeyManagement[WifiKeyManagement.OTHER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static WifiKeyManagement getWifiKeyManagement(ScanResult scanResult) {
        if (scanResult.capabilities.contains("WEP")) {
            return WEP;
        }
        if (scanResult.capabilities.contains("PSK")) {
            return WPA_PSK;
        }
        if (scanResult.capabilities.contains("EAP")) {
            return OTHER;
        }
        return NONE;
    }

    public BitSet toAndroidKeyManagement() {
        BitSet bitSet = new BitSet();
        int ordinal = ordinal();
        if (ordinal == 0) {
            bitSet.set(0);
        } else if (ordinal == 1) {
            bitSet.set(1);
        } else if (ordinal == 2) {
            bitSet.set(0);
        } else if (ordinal == 3) {
            throw new IllegalArgumentException("Network type of OTHER key management not supported.");
        }
        return bitSet;
    }

    public static WifiKeyManagement getWifiKeyManagement(android.net.wifi.WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration != null) {
            if (wifiConfiguration.allowedKeyManagement.get(1)) {
                return WPA_PSK;
            }
            if (wifiConfiguration.allowedKeyManagement.get(0)) {
                String[] strArr = wifiConfiguration.wepKeys;
                if (strArr != null && strArr.length > 0 && strArr[0] != null) {
                    return WEP;
                }
                return NONE;
            }
            return OTHER;
        }
        throw new IllegalArgumentException("Cannot convert a null wifi-configuration's key-management.");
    }
}
