package com.amazon.whisperjoin.softap.wifi.provider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkSpecifier;
import android.os.Build;
import android.os.PatternMatcher;
import com.amazon.whisperjoin.softap.util.Utils;
import com.google.common.base.Optional;
import java.util.List;
/* loaded from: classes13.dex */
public interface WifiManagerProvider {

    /* loaded from: classes13.dex */
    public static class DefaultWifiManagerProvider implements WifiManagerProvider {
        private static final String TAG = "DefaultWifiManagerProvider";
        private final ConnectivityManager connectivityManager;
        private ConnectivityManager.NetworkCallback networkCallback = null;
        private final WifiManager wifiManager;

        public DefaultWifiManagerProvider(Context context) {
            this.wifiManager = (WifiManager) context.getSystemService("wifi");
            this.connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        }

        @Override // com.amazon.whisperjoin.softap.wifi.provider.WifiManagerProvider
        public int addNetwork(WifiConfiguration wifiConfiguration) {
            return this.wifiManager.addNetwork(wifiConfiguration);
        }

        @Override // com.amazon.whisperjoin.softap.wifi.provider.WifiManagerProvider
        public boolean bindProcessToNetwork(Network network) {
            int i = Build.VERSION.SDK_INT;
            return this.connectivityManager.bindProcessToNetwork(network);
        }

        @Override // com.amazon.whisperjoin.softap.wifi.provider.WifiManagerProvider
        public boolean disconnect() {
            return this.wifiManager.disconnect();
        }

        @Override // com.amazon.whisperjoin.softap.wifi.provider.WifiManagerProvider
        public boolean enableNetwork(int i, boolean z) {
            return this.wifiManager.enableNetwork(i, z);
        }

        @Override // com.amazon.whisperjoin.softap.wifi.provider.WifiManagerProvider
        @SuppressLint({"MissingPermission"})
        public List<WifiConfiguration> getConfiguredNetworks() {
            return this.wifiManager.getConfiguredNetworks();
        }

        @Override // com.amazon.whisperjoin.softap.wifi.provider.WifiManagerProvider
        public Optional<WifiInfo> getConnectionInfo() {
            return Optional.fromNullable(this.wifiManager.getConnectionInfo());
        }

        @Override // com.amazon.whisperjoin.softap.wifi.provider.WifiManagerProvider
        public Optional<String> getCurrentBssid() {
            Optional<WifiInfo> connectionInfo = getConnectionInfo();
            if (connectionInfo.isPresent()) {
                return Optional.fromNullable(connectionInfo.get().getBSSID());
            }
            return Optional.absent();
        }

        @Override // com.amazon.whisperjoin.softap.wifi.provider.WifiManagerProvider
        public Optional<Integer> getCurrentNetworkId() {
            Optional<WifiInfo> connectionInfo = getConnectionInfo();
            if (connectionInfo.isPresent()) {
                return Optional.fromNullable(Integer.valueOf(connectionInfo.get().getNetworkId()));
            }
            return Optional.absent();
        }

        @Override // com.amazon.whisperjoin.softap.wifi.provider.WifiManagerProvider
        public Optional<String> getCurrentSsid() {
            Optional<WifiInfo> connectionInfo = getConnectionInfo();
            if (connectionInfo.isPresent()) {
                return Optional.fromNullable(connectionInfo.get().getSSID());
            }
            return Optional.absent();
        }

        @Override // com.amazon.whisperjoin.softap.wifi.provider.WifiManagerProvider
        public boolean isWifiEnabled() {
            return this.wifiManager.isWifiEnabled();
        }

        @Override // com.amazon.whisperjoin.softap.wifi.provider.WifiManagerProvider
        public boolean reconnect() {
            return this.wifiManager.reconnect();
        }

        @Override // com.amazon.whisperjoin.softap.wifi.provider.WifiManagerProvider
        public boolean removeNetwork(int i) {
            return this.wifiManager.removeNetwork(i);
        }

        @Override // com.amazon.whisperjoin.softap.wifi.provider.WifiManagerProvider
        public void requestWifiNetwork(WifiConfiguration wifiConfiguration, final WifiNetworkCallback wifiNetworkCallback) {
            NetworkRequest build;
            int i = Build.VERSION.SDK_INT;
            this.networkCallback = new ConnectivityManager.NetworkCallback() { // from class: com.amazon.whisperjoin.softap.wifi.provider.WifiManagerProvider.DefaultWifiManagerProvider.1
                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onAvailable(Network network) {
                    DefaultWifiManagerProvider.this.bindProcessToNetwork(network);
                    wifiNetworkCallback.onWifiAvailable();
                }

                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onLost(Network network) {
                    DefaultWifiManagerProvider.this.unbindProcessToNetwork();
                    String unused = DefaultWifiManagerProvider.TAG;
                    wifiNetworkCallback.onWifiUnavailable();
                }

                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onUnavailable() {
                    DefaultWifiManagerProvider.this.unbindProcessToNetwork();
                    String unused = DefaultWifiManagerProvider.TAG;
                    wifiNetworkCallback.onWifiUnavailable();
                }
            };
            NetworkRequest.Builder addTransportType = new NetworkRequest.Builder().addTransportType(1);
            if (Build.VERSION.SDK_INT >= 29) {
                build = addTransportType.removeCapability(12).setNetworkSpecifier(new WifiNetworkSpecifier.Builder().setSsidPattern(new PatternMatcher(Utils.getStringWithoutWrappingQuotesIfPresent(wifiConfiguration.SSID), 1)).build()).build();
            } else {
                build = addTransportType.build();
            }
            this.connectivityManager.requestNetwork(build, this.networkCallback);
        }

        @Override // com.amazon.whisperjoin.softap.wifi.provider.WifiManagerProvider
        public boolean setWifiEnabled(boolean z) {
            return this.wifiManager.setWifiEnabled(z);
        }

        @Override // com.amazon.whisperjoin.softap.wifi.provider.WifiManagerProvider
        public boolean unbindProcessToNetwork() {
            int i = Build.VERSION.SDK_INT;
            ConnectivityManager.NetworkCallback networkCallback = this.networkCallback;
            if (networkCallback != null) {
                this.connectivityManager.unregisterNetworkCallback(networkCallback);
                this.networkCallback = null;
            }
            int i2 = Build.VERSION.SDK_INT;
            return this.connectivityManager.bindProcessToNetwork(null);
        }
    }

    /* loaded from: classes13.dex */
    public interface WifiNetworkCallback {
        void onWifiAvailable();

        void onWifiUnavailable();
    }

    int addNetwork(WifiConfiguration wifiConfiguration);

    boolean bindProcessToNetwork(Network network);

    boolean disconnect();

    boolean enableNetwork(int i, boolean z);

    List<WifiConfiguration> getConfiguredNetworks();

    Optional<WifiInfo> getConnectionInfo();

    Optional<String> getCurrentBssid();

    Optional<Integer> getCurrentNetworkId();

    Optional<String> getCurrentSsid();

    boolean isWifiEnabled();

    boolean reconnect();

    boolean removeNetwork(int i);

    void requestWifiNetwork(WifiConfiguration wifiConfiguration, WifiNetworkCallback wifiNetworkCallback);

    boolean setWifiEnabled(boolean z);

    boolean unbindProcessToNetwork();
}
