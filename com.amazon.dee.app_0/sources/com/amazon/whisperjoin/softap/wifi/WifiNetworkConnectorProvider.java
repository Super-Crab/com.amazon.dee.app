package com.amazon.whisperjoin.softap.wifi;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import com.amazon.whisperjoin.softap.wifi.WifiNetworkConnector;
import com.amazon.whisperjoin.softap.wifi.provider.WifiManagerProvider;
/* loaded from: classes13.dex */
public interface WifiNetworkConnectorProvider {

    /* loaded from: classes13.dex */
    public static class DefaultWifiNetworkConnectorProvider implements WifiNetworkConnectorProvider {
        @Override // com.amazon.whisperjoin.softap.wifi.WifiNetworkConnectorProvider
        public WifiNetworkConnector buildConnector(WifiConfiguration wifiConfiguration, WifiManagerProvider wifiManagerProvider, WifiNetworkConnector.OnConnectionListener onConnectionListener, Context context) {
            return new WifiNetworkConnector(wifiConfiguration, wifiManagerProvider, onConnectionListener, context);
        }
    }

    WifiNetworkConnector buildConnector(WifiConfiguration wifiConfiguration, WifiManagerProvider wifiManagerProvider, WifiNetworkConnector.OnConnectionListener onConnectionListener, Context context);
}
