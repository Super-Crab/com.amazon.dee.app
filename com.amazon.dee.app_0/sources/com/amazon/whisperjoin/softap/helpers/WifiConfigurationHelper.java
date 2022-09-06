package com.amazon.whisperjoin.softap.helpers;

import com.amazon.whisperjoin.softap.pojos.WifiConfiguration;
/* loaded from: classes13.dex */
public class WifiConfigurationHelper {

    /* renamed from: com.amazon.whisperjoin.softap.helpers.WifiConfigurationHelper$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$whisperjoin$softap$pojos$WifiConfiguration$WifiKeyManagement = new int[WifiConfiguration.WifiKeyManagement.values().length];

        static {
            try {
                $SwitchMap$com$amazon$whisperjoin$softap$pojos$WifiConfiguration$WifiKeyManagement[WifiConfiguration.WifiKeyManagement.WEP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$softap$pojos$WifiConfiguration$WifiKeyManagement[WifiConfiguration.WifiKeyManagement.WPA_PSK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$softap$pojos$WifiConfiguration$WifiKeyManagement[WifiConfiguration.WifiKeyManagement.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static com.amazon.whisperjoin.wifi.WifiConfiguration toWhisperjoinWifiConfiguration(WifiConfiguration wifiConfiguration) {
        int ordinal = wifiConfiguration.getKeyMgmt().ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return com.amazon.whisperjoin.wifi.WifiConfiguration.createWpaWifiConfiguration(wifiConfiguration.getSsid(), wifiConfiguration.getKey());
            }
            if (ordinal == 2) {
                return com.amazon.whisperjoin.wifi.WifiConfiguration.createWepWifiConfiguration(wifiConfiguration.getSsid(), wifiConfiguration.getKey());
            }
            throw new IllegalArgumentException("Unknown network");
        }
        return com.amazon.whisperjoin.wifi.WifiConfiguration.createOpenWifiConfiguration(wifiConfiguration.getSsid());
    }
}
