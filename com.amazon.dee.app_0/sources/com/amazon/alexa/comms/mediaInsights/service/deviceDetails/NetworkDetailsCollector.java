package com.amazon.alexa.comms.mediaInsights.service.deviceDetails;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import java.util.HashMap;
import java.util.Map;
import lombok.NonNull;
/* loaded from: classes6.dex */
public class NetworkDetailsCollector {
    private static final int MAX_RSSI_VALUE = 10;
    @NonNull
    private Context appContext;
    @NonNull
    private String networkDetailsSharedPrefFileName;

    /* loaded from: classes6.dex */
    public enum NetworkDetailKeys {
        DOWN_LINK_SPEED,
        UP_LINK_SPEED,
        WIFI_LINK_SPEED,
        WIFI_LINK_SPEED_UNITS,
        WIFI_SIGNAL_STRENGTH,
        WIFI_FREQUENCY,
        WIFI_FREQUENCY_UNITS
    }

    public NetworkDetailsCollector(@NonNull Context context, @NonNull String str) {
        if (context != null) {
            if (str == null) {
                throw new IllegalArgumentException("networkDetailsSharedPrefFileName is null");
            }
            this.appContext = context;
            this.networkDetailsSharedPrefFileName = str;
            return;
        }
        throw new IllegalArgumentException("appContext is null");
    }

    private Map<String, String> captureNetworkCapabilityDetails(ConnectivityManager connectivityManager) {
        NetworkCapabilities networkCapabilities;
        Network network;
        HashMap hashMap = new HashMap();
        if (connectivityManager == null) {
            return hashMap;
        }
        try {
            Network[] allNetworks = connectivityManager.getAllNetworks();
            int length = allNetworks.length;
            int i = 0;
            while (true) {
                networkCapabilities = null;
                if (i >= length) {
                    network = null;
                    break;
                }
                network = allNetworks[i];
                if (connectivityManager.getNetworkInfo(network).isConnected()) {
                    break;
                }
                i++;
            }
            if (network != null) {
                networkCapabilities = connectivityManager.getNetworkCapabilities(network);
            }
        } catch (Throwable unused) {
        }
        if (networkCapabilities == null) {
            return hashMap;
        }
        int linkDownstreamBandwidthKbps = networkCapabilities.getLinkDownstreamBandwidthKbps();
        int linkUpstreamBandwidthKbps = networkCapabilities.getLinkUpstreamBandwidthKbps();
        hashMap.put(NetworkDetailKeys.DOWN_LINK_SPEED.toString(), String.valueOf(linkDownstreamBandwidthKbps));
        hashMap.put(NetworkDetailKeys.UP_LINK_SPEED.toString(), String.valueOf(linkUpstreamBandwidthKbps));
        return hashMap;
    }

    private Map<String, String> captureWifiDetails(WifiManager wifiManager) {
        HashMap hashMap = new HashMap();
        if (wifiManager != null && wifiManager.isWifiEnabled()) {
            try {
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                if (connectionInfo == null) {
                    return hashMap;
                }
                int linkSpeed = connectionInfo.getLinkSpeed();
                int frequency = connectionInfo.getFrequency();
                int calculateSignalLevel = WifiManager.calculateSignalLevel(connectionInfo.getRssi(), 10);
                hashMap.put(NetworkDetailKeys.WIFI_LINK_SPEED.toString(), String.valueOf(linkSpeed));
                hashMap.put(NetworkDetailKeys.WIFI_LINK_SPEED_UNITS.toString(), "Mbps");
                hashMap.put(NetworkDetailKeys.WIFI_FREQUENCY.toString(), String.valueOf(frequency));
                hashMap.put(NetworkDetailKeys.WIFI_FREQUENCY_UNITS.toString(), "MHz");
                hashMap.put(NetworkDetailKeys.WIFI_SIGNAL_STRENGTH.toString(), String.valueOf(calculateSignalLevel));
            } catch (Throwable unused) {
            }
        }
        return hashMap;
    }

    private void saveNetworkDetails(Map<String, String> map) {
        SharedPreferences.Editor edit = this.appContext.getSharedPreferences(this.networkDetailsSharedPrefFileName, 0).edit();
        for (NetworkDetailKeys networkDetailKeys : (NetworkDetailKeys[]) NetworkDetailKeys.class.getEnumConstants()) {
            String str = networkDetailKeys.toString();
            edit.putString(str, map.get(str));
        }
        edit.apply();
    }

    public void collect() {
        HashMap hashMap = new HashMap();
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.appContext.getApplicationContext().getSystemService("connectivity");
            WifiManager wifiManager = (WifiManager) this.appContext.getApplicationContext().getSystemService("wifi");
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                hashMap.putAll(captureNetworkCapabilityDetails(connectivityManager));
                hashMap.putAll(captureWifiDetails(wifiManager));
            }
            saveNetworkDetails(hashMap);
        } catch (Throwable unused) {
        }
    }
}
