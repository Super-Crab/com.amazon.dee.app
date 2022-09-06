package com.amazon.whisperjoin.deviceprovisioningservice.wifi;

import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiKeyManagement;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiNetwork;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import java.util.List;
/* loaded from: classes13.dex */
public class CurrentWifiNetworkProvider {
    private static final String TAG = "CurrentWifiNetworkProvider";
    private static final String UNKNOWN_SSID = "<unknown ssid>";
    private WifiManager mWifiManager;

    public CurrentWifiNetworkProvider(WifiManager wifiManager) {
        this.mWifiManager = wifiManager;
    }

    private WifiKeyManagement getKeyMgmt(WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration.allowedKeyManagement.get(1)) {
            return WifiKeyManagement.WPA_PSK;
        }
        if (!wifiConfiguration.allowedKeyManagement.get(2) && !wifiConfiguration.allowedKeyManagement.get(3)) {
            if (wifiConfiguration.allowedAuthAlgorithms.get(1)) {
                return WifiKeyManagement.WEP;
            }
            return WifiKeyManagement.NONE;
        }
        return WifiKeyManagement.OTHER;
    }

    public WifiNetwork getCurrentWifiNetwork() {
        WifiInfo connectionInfo = this.mWifiManager.getConnectionInfo();
        if (connectionInfo != null && !connectionInfo.getSSID().equals(UNKNOWN_SSID)) {
            List<WifiConfiguration> configuredNetworks = this.mWifiManager.getConfiguredNetworks();
            if (configuredNetworks == null) {
                WJLog.d(TAG, "No system configured networks provided");
                return null;
            }
            int networkId = connectionInfo.getNetworkId();
            for (WifiConfiguration wifiConfiguration : configuredNetworks) {
                if (networkId == wifiConfiguration.networkId) {
                    try {
                        WifiNetwork wifiNetwork = new WifiNetwork(wifiConfiguration.SSID, getKeyMgmt(wifiConfiguration));
                        String str = TAG;
                        WJLog.d(str, "Returning " + wifiNetwork.getSsid() + " " + wifiNetwork.getKeyManagement() + " as phone's current network");
                        return wifiNetwork;
                    } catch (IllegalArgumentException e) {
                        WJLog.w(TAG, "IllegalArgumentException occurred while getting the current wifi network", e);
                        return null;
                    }
                }
            }
            WJLog.d(TAG, "Couldn't find current network");
            return null;
        }
        WJLog.d(TAG, "No current connection info provided");
        return null;
    }
}
