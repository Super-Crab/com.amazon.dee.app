package com.amazon.alexa.mode.util;

import android.content.Context;
import android.net.ConnectivityManager;
import com.amazon.alexa.mode.metrics.DriveModeMetrics;
/* loaded from: classes9.dex */
public class NetworkStatusHelper {
    private ConnectivityManager connectivityManager;

    public NetworkStatusHelper(Context context) {
        this.connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
    }

    public String getNetworkStatus() {
        return (this.connectivityManager.getActiveNetworkInfo() == null || !this.connectivityManager.getActiveNetworkInfo().isConnected()) ? DriveModeMetrics.NetworkStatus.UNAVAILABLE : DriveModeMetrics.NetworkStatus.ONLINE;
    }
}
