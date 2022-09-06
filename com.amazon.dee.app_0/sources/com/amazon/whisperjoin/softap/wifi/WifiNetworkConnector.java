package com.amazon.whisperjoin.softap.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import com.amazon.whisperjoin.softap.util.Utils;
import com.amazon.whisperjoin.softap.wifi.provider.WifiManagerProvider;
import com.google.common.base.Optional;
import com.google.common.base.Strings;
import java.util.List;
/* loaded from: classes13.dex */
public class WifiNetworkConnector {
    private static final String TAG = "WifiNetworkConnector";
    private final Context context;
    private final Handler mainThreadHandler;
    private final NetworkStateChangedReceiver networkStateChangedReceiver;
    private final OnConnectionListener onConnectionListener;
    private final WifiConfiguration wifiConfiguration;
    private final WifiManagerProvider wifiManagerProvider;
    private int networkId = -1;
    private boolean wasPreviousEventTargetNetwork = false;
    private boolean receiverRegistered = false;
    private boolean connected = false;

    /* loaded from: classes13.dex */
    public interface OnConnectionListener {
        void onConnectionDropped();

        void onConnectionFailure();

        void onConnectionFinished();

        void onConnectionSuccess();
    }

    public WifiNetworkConnector(@NonNull WifiConfiguration wifiConfiguration, @NonNull WifiManagerProvider wifiManagerProvider, @NonNull OnConnectionListener onConnectionListener, @NonNull Context context) {
        validateArguments(wifiConfiguration, onConnectionListener, wifiManagerProvider, context);
        this.wifiConfiguration = wifiConfiguration;
        this.wifiManagerProvider = wifiManagerProvider;
        this.onConnectionListener = onConnectionListener;
        this.context = context;
        this.networkStateChangedReceiver = new NetworkStateChangedReceiver();
        this.mainThreadHandler = new Handler(Looper.getMainLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void attemptConnection() {
        if (Strings.isNullOrEmpty(this.wifiConfiguration.BSSID)) {
            String.format("Attempting Connection to target network with SSID %s", this.wifiConfiguration.SSID);
        } else {
            WifiConfiguration wifiConfiguration = this.wifiConfiguration;
            String.format("Attempting Connection to target network with SSID %s and BSSID %s", wifiConfiguration.SSID, wifiConfiguration.BSSID);
        }
        this.wifiManagerProvider.disconnect();
        this.wifiManagerProvider.enableNetwork(this.networkId, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeInMainThread(Runnable runnable) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            runnable.run();
        } else {
            this.mainThreadHandler.post(runnable);
        }
    }

    private int getNetworkId() {
        removeConfiguredNetworks();
        int addNetwork = this.wifiManagerProvider.addNetwork(this.wifiConfiguration);
        if (addNetwork < 0) {
            List<WifiConfiguration> configuredNetworks = this.wifiManagerProvider.getConfiguredNetworks();
            if (configuredNetworks == null) {
                return addNetwork;
            }
            for (WifiConfiguration wifiConfiguration : configuredNetworks) {
                if (isTargetNetwork(wifiConfiguration)) {
                    addNetwork = wifiConfiguration.networkId;
                }
            }
        }
        String.format("Get network ID: %d", Integer.valueOf(addNetwork));
        return addNetwork;
    }

    private boolean isTargetNetwork(WifiConfiguration wifiConfiguration) {
        if (Strings.isNullOrEmpty(wifiConfiguration.SSID)) {
            return false;
        }
        if (Strings.isNullOrEmpty(wifiConfiguration.BSSID)) {
            return isTargetSSID(wifiConfiguration.SSID);
        }
        return wifiConfiguration.BSSID.equals(this.wifiConfiguration.BSSID);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isTargetSSID(String str) {
        return Utils.getStringWithoutWrappingQuotesIfPresent(str).equals(Utils.getStringWithoutWrappingQuotesIfPresent(this.wifiConfiguration.SSID));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onConnectedToTargetNetwork() {
        if (this.connected) {
            return;
        }
        this.connected = true;
        prepareNetwork();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onConnectionAttemptFailed() {
        this.onConnectionListener.onConnectionFailure();
        stop();
    }

    private void prepareNetwork() {
        this.wifiManagerProvider.requestWifiNetwork(this.wifiConfiguration, new WifiManagerProvider.WifiNetworkCallback() { // from class: com.amazon.whisperjoin.softap.wifi.WifiNetworkConnector.1
            @Override // com.amazon.whisperjoin.softap.wifi.provider.WifiManagerProvider.WifiNetworkCallback
            public void onWifiAvailable() {
                WifiNetworkConnector.this.executeInMainThread(new Runnable() { // from class: com.amazon.whisperjoin.softap.wifi.WifiNetworkConnector.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (Build.VERSION.SDK_INT >= 29) {
                            if (WifiNetworkConnector.this.connected) {
                                return;
                            }
                            WifiNetworkConnector.this.connected = true;
                        }
                        if (WifiNetworkConnector.this.connected) {
                            String unused = WifiNetworkConnector.TAG;
                            WifiNetworkConnector.this.onConnectionListener.onConnectionSuccess();
                        }
                    }
                });
            }

            @Override // com.amazon.whisperjoin.softap.wifi.provider.WifiManagerProvider.WifiNetworkCallback
            public void onWifiUnavailable() {
                WifiNetworkConnector.this.executeInMainThread(new Runnable() { // from class: com.amazon.whisperjoin.softap.wifi.WifiNetworkConnector.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        String unused = WifiNetworkConnector.TAG;
                        WifiNetworkConnector.this.onConnectionAttemptFailed();
                    }
                });
            }
        });
    }

    private void registerWifiEventReceiver() {
        this.context.registerReceiver(this.networkStateChangedReceiver, new IntentFilter("android.net.wifi.STATE_CHANGE"));
        this.receiverRegistered = true;
    }

    private void removeConfiguredNetworks() {
        for (WifiConfiguration wifiConfiguration : this.wifiManagerProvider.getConfiguredNetworks()) {
            if (wifiConfiguration != null && !Strings.isNullOrEmpty(wifiConfiguration.SSID) && isTargetSSID(wifiConfiguration.SSID)) {
                this.wifiManagerProvider.removeNetwork(wifiConfiguration.networkId);
            }
        }
    }

    private void removeNetwork() {
        Optional<String> currentSsid = this.wifiManagerProvider.getCurrentSsid();
        if (currentSsid.isPresent() && isTargetSSID(currentSsid.get())) {
            String.format("Disconnection from network with SSID %s", this.wifiConfiguration.SSID);
            this.wifiManagerProvider.disconnect();
        }
        String.format("Removing network %d", Integer.valueOf(this.networkId));
        this.wifiManagerProvider.removeNetwork(this.networkId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unregisterWifiEventReceiver() {
        if (this.receiverRegistered) {
            this.context.unregisterReceiver(this.networkStateChangedReceiver);
            this.receiverRegistered = false;
        }
    }

    private void validateArguments(WifiConfiguration wifiConfiguration, OnConnectionListener onConnectionListener, WifiManagerProvider wifiManagerProvider, Context context) {
        if (wifiConfiguration != null) {
            if (onConnectionListener == null) {
                throw new IllegalArgumentException("On connection listener can't be null");
            }
            if (wifiManagerProvider == null) {
                throw new IllegalArgumentException("Wifi manager provider can't be null");
            }
            if (context == null) {
                throw new IllegalArgumentException("Context can't be null");
            }
            return;
        }
        throw new IllegalArgumentException("Wifi configuration can't be null");
    }

    public synchronized void start() {
        if (Build.VERSION.SDK_INT >= 29) {
            prepareNetwork();
        } else {
            this.wifiManagerProvider.setWifiEnabled(true);
            this.networkId = getNetworkId();
            attemptConnection();
            registerWifiEventReceiver();
        }
    }

    public synchronized void stop() {
        if (Build.VERSION.SDK_INT < 29) {
            unregisterWifiEventReceiver();
            removeNetwork();
        }
        this.wifiManagerProvider.unbindProcessToNetwork();
        if (this.connected) {
            this.onConnectionListener.onConnectionFinished();
            this.connected = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public class NetworkStateChangedReceiver extends BroadcastReceiver {
        private NetworkStateChangedReceiver() {
        }

        private void handleWrongNetwork(NetworkInfo networkInfo) {
            String unused = WifiNetworkConnector.TAG;
            if (WifiNetworkConnector.this.connected) {
                String unused2 = WifiNetworkConnector.TAG;
                WifiNetworkConnector.this.connected = false;
                WifiNetworkConnector.this.unregisterWifiEventReceiver();
                WifiNetworkConnector.this.onConnectionListener.onConnectionDropped();
            } else if (WifiNetworkConnector.this.wasPreviousEventTargetNetwork) {
                String unused3 = WifiNetworkConnector.TAG;
                WifiNetworkConnector.this.attemptConnection();
                WifiNetworkConnector.this.wasPreviousEventTargetNetwork = false;
            } else if (!networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
            } else {
                String unused4 = WifiNetworkConnector.TAG;
                WifiNetworkConnector.this.attemptConnection();
            }
        }

        private boolean isConnectedToTargetNetwork() {
            boolean isCurrentSsidTargetNetwork = isCurrentSsidTargetNetwork();
            return Strings.isNullOrEmpty(WifiNetworkConnector.this.wifiConfiguration.BSSID) ? isCurrentSsidTargetNetwork : isCurrentSsidTargetNetwork && isCurrentBssidTargetNetwork();
        }

        private boolean isConnectionAttemptFailure(NetworkInfo networkInfo) {
            return networkInfo.getDetailedState().equals(NetworkInfo.DetailedState.FAILED);
        }

        private boolean isCurrentBssidTargetNetwork() {
            Optional<String> currentBssid = WifiNetworkConnector.this.wifiManagerProvider.getCurrentBssid();
            if (!currentBssid.isPresent()) {
                return false;
            }
            String str = currentBssid.get();
            String unused = WifiNetworkConnector.TAG;
            String.format("Current BSSID: %s", str);
            return str.equals(WifiNetworkConnector.this.wifiConfiguration.BSSID);
        }

        private boolean isCurrentSsidTargetNetwork() {
            Optional<String> currentSsid = WifiNetworkConnector.this.wifiManagerProvider.getCurrentSsid();
            if (!currentSsid.isPresent()) {
                return false;
            }
            String str = currentSsid.get();
            String unused = WifiNetworkConnector.TAG;
            String.format("Current SSID: %s", str);
            return WifiNetworkConnector.this.isTargetSSID(str);
        }

        @Override // android.content.BroadcastReceiver
        public synchronized void onReceive(Context context, Intent intent) {
            if (!intent.getAction().equals("android.net.wifi.STATE_CHANGE")) {
                return;
            }
            NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
            if (isConnectedToTargetNetwork()) {
                String unused = WifiNetworkConnector.TAG;
                WifiNetworkConnector.this.wasPreviousEventTargetNetwork = true;
                if (isConnectedToTargetNetwork(networkInfo)) {
                    String unused2 = WifiNetworkConnector.TAG;
                    String.format("Connected to network with SSID %s and BSSID %s", WifiNetworkConnector.this.wifiManagerProvider.getCurrentSsid(), WifiNetworkConnector.this.wifiManagerProvider.getCurrentBssid());
                    WifiNetworkConnector.this.onConnectedToTargetNetwork();
                    return;
                } else if (isConnectionAttemptFailure(networkInfo)) {
                    WifiNetworkConnector.this.onConnectionAttemptFailed();
                    return;
                } else {
                    String unused3 = WifiNetworkConnector.TAG;
                    return;
                }
            }
            handleWrongNetwork(networkInfo);
        }

        private boolean isConnectedToTargetNetwork(NetworkInfo networkInfo) {
            return networkInfo.getState().equals(NetworkInfo.State.CONNECTED);
        }
    }
}
