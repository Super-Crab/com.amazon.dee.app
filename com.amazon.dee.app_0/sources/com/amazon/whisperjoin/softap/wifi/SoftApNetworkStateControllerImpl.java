package com.amazon.whisperjoin.softap.wifi;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import com.amazon.whisperjoin.softap.util.Utils;
import com.amazon.whisperjoin.softap.wifi.WifiNetworkConnector;
import com.amazon.whisperjoin.softap.wifi.WifiNetworkConnectorProvider;
import com.amazon.whisperjoin.softap.wifi.provider.WifiManagerProvider;
import com.google.common.base.Optional;
/* loaded from: classes13.dex */
public class SoftApNetworkStateControllerImpl implements SoftApNetworkStateController {
    private static final String TAG = "SoftApNetworkStateControllerImpl";
    private final Context context;
    private Optional<WifiInfo> savedWifiInfo;
    private boolean wasWifiEnabled;
    private final WifiManagerProvider wifiManagerProvider;
    private WifiNetworkConnector wifiNetworkConnector;
    private final WifiNetworkConnectorProvider wifiNetworkConnectorProvider;

    public SoftApNetworkStateControllerImpl(Context context) {
        this(context, new WifiManagerProvider.DefaultWifiManagerProvider(context), new WifiNetworkConnectorProvider.DefaultWifiNetworkConnectorProvider());
    }

    private void buildConnection(WifiNetworkConnector.OnConnectionListener onConnectionListener, WifiConfiguration wifiConfiguration) {
        this.wifiNetworkConnector = this.wifiNetworkConnectorProvider.buildConnector(wifiConfiguration, this.wifiManagerProvider, onConnectionListener, this.context);
        this.wifiNetworkConnector.start();
    }

    private WifiConfiguration getConfigurationForSoftAP(String str, String str2) {
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        if (!Utils.isStringWrappedInQuotes(str)) {
            wifiConfiguration.SSID = String.format("\"%s\"", str);
        } else {
            wifiConfiguration.SSID = str;
        }
        wifiConfiguration.BSSID = str2;
        wifiConfiguration.allowedKeyManagement.set(0);
        return wifiConfiguration;
    }

    @Override // com.amazon.whisperjoin.softap.wifi.SoftApNetworkStateController
    public synchronized void connectToSoftAp(WifiNetworkConnector.OnConnectionListener onConnectionListener, String str) {
        if (this.savedWifiInfo.isPresent()) {
            String.format("Connecting to Soft AP with SSID %s", str);
            buildConnection(onConnectionListener, getConfigurationForSoftAP(str, null));
        } else {
            throw new IllegalStateException("Must call saveNetworkState state before connecting");
        }
    }

    @Override // com.amazon.whisperjoin.softap.wifi.SoftApNetworkStateController
    public synchronized void restoreNetworkState() {
        if (this.wifiNetworkConnector != null) {
            this.wifiNetworkConnector.stop();
        }
        if (this.wifiManagerProvider.isWifiEnabled() ^ this.wasWifiEnabled) {
            this.wifiManagerProvider.setWifiEnabled(this.wasWifiEnabled);
        }
        if (this.wasWifiEnabled && this.savedWifiInfo.isPresent()) {
            int networkId = this.savedWifiInfo.get().getNetworkId();
            Optional<Integer> currentNetworkId = this.wifiManagerProvider.getCurrentNetworkId();
            if (currentNetworkId.isPresent()) {
                int intValue = currentNetworkId.get().intValue();
                String.format("Re-enabling wifi. Saved net id: %d, Current net id: %d", Integer.valueOf(networkId), Integer.valueOf(intValue));
                if (networkId != intValue) {
                    this.wifiManagerProvider.enableNetwork(this.savedWifiInfo.get().getNetworkId(), true);
                }
            }
        }
    }

    @Override // com.amazon.whisperjoin.softap.wifi.SoftApNetworkStateController
    public synchronized void saveNetworkState() {
        boolean isWifiEnabled = this.wifiManagerProvider.isWifiEnabled();
        if (isWifiEnabled) {
            this.savedWifiInfo = this.wifiManagerProvider.getConnectionInfo();
            this.wasWifiEnabled = isWifiEnabled;
        } else {
            this.savedWifiInfo = Optional.absent();
        }
        Object[] objArr = new Object[2];
        objArr[0] = Boolean.valueOf(this.wasWifiEnabled);
        objArr[1] = this.savedWifiInfo.isPresent() ? this.savedWifiInfo.get().getSSID() : "No Current SSID";
        String.format("Saving wifi network state. Wifi enabled: %s, Saved SSID: %s", objArr);
    }

    SoftApNetworkStateControllerImpl(Context context, WifiManagerProvider wifiManagerProvider, WifiNetworkConnectorProvider wifiNetworkConnectorProvider) {
        this.context = context;
        this.wifiManagerProvider = wifiManagerProvider;
        this.wifiNetworkConnectorProvider = wifiNetworkConnectorProvider;
        this.wasWifiEnabled = false;
    }

    @Override // com.amazon.whisperjoin.softap.wifi.SoftApNetworkStateController
    public synchronized void connectToSoftAp(WifiNetworkConnector.OnConnectionListener onConnectionListener, String str, String str2) {
        if (this.savedWifiInfo.isPresent()) {
            String.format("Connecting to Soft AP with SSID %s and BSSID %s", str, str2);
            buildConnection(onConnectionListener, getConfigurationForSoftAP(str, str2));
        } else {
            throw new IllegalStateException("Must call saveNetworkState state before connecting");
        }
    }
}
