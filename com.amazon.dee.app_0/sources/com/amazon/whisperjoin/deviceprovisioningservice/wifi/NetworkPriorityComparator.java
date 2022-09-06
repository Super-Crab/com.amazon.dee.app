package com.amazon.whisperjoin.deviceprovisioningservice.wifi;

import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiKeyManagement;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiScanResult;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public class NetworkPriorityComparator implements Comparator<WifiScanResult>, Serializable {
    private static final Map<WifiKeyManagement, Integer> SECURITY_RANKING;

    static {
        HashMap hashMap = new HashMap(WifiKeyManagement.values().length);
        hashMap.put(WifiKeyManagement.NONE, 1);
        hashMap.put(WifiKeyManagement.WEP, 2);
        hashMap.put(WifiKeyManagement.WPA_PSK, 3);
        SECURITY_RANKING = Collections.unmodifiableMap(hashMap);
    }

    @Override // java.util.Comparator
    public int compare(WifiScanResult wifiScanResult, WifiScanResult wifiScanResult2) {
        int compareTo = SECURITY_RANKING.get(wifiScanResult.getKeyManagement()).compareTo(SECURITY_RANKING.get(wifiScanResult2.getKeyManagement()));
        if (compareTo != 0) {
            return compareTo;
        }
        int compareTo2 = Integer.valueOf(wifiScanResult.getSignalStrength()).compareTo(Integer.valueOf(wifiScanResult2.getSignalStrength()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        String ssid = wifiScanResult.getSsid();
        String ssid2 = wifiScanResult2.getSsid();
        int compare = String.CASE_INSENSITIVE_ORDER.compare(ssid2, ssid);
        return compare != 0 ? compare : ssid2.compareTo(ssid);
    }
}
