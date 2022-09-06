package com.amazon.whisperjoin.softap.wifi;

import com.amazon.whisperjoin.softap.wifi.WifiNetworkConnector;
/* loaded from: classes13.dex */
public interface SoftApNetworkStateController {
    void connectToSoftAp(WifiNetworkConnector.OnConnectionListener onConnectionListener, String str);

    void connectToSoftAp(WifiNetworkConnector.OnConnectionListener onConnectionListener, String str, String str2);

    void restoreNetworkState();

    void saveNetworkState();
}
