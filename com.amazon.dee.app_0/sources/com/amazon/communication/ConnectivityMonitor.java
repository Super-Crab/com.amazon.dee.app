package com.amazon.communication;

import android.net.NetworkInfo;
/* loaded from: classes12.dex */
public interface ConnectivityMonitor {
    void deregisterConnectivityChangedHandler(ConnectivityChangedHandler connectivityChangedHandler);

    NetworkInfo.State getNetworkState(int i);

    boolean isConnectivityPossible();

    boolean isEthernetAvailable();

    boolean isMobileAvailable();

    boolean isWiFiAvailable();

    void registerConnectivityChangedHandler(ConnectivityChangedHandler connectivityChangedHandler);

    void start();

    void stop();
}
