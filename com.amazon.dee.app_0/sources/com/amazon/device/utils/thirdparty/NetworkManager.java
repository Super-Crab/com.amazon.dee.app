package com.amazon.device.utils.thirdparty;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.PowerManager;
import android.util.Log;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes12.dex */
public class NetworkManager {
    private static final String TAG = "NetworkManager";
    private static final List<Integer> TYPE_WAN_LIST = Arrays.asList(0, 4, 5, 2, 3, 6);
    private static NetworkManager sNetworkManager;
    private final ConnectivityManager mConnectivityManager;
    private final Context mContext;
    private final NetworkBroadcastReceiver mNetworkBroadcastReceiver;
    private final PowerManager mPowerManager;
    private final WifiManager mWifiManager;

    private NetworkManager(Context context) {
        this.mContext = context;
        this.mConnectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        this.mWifiManager = (WifiManager) context.getSystemService("wifi");
        this.mPowerManager = (PowerManager) context.getSystemService("power");
        this.mNetworkBroadcastReceiver = new NetworkBroadcastReceiver(this.mContext);
    }

    public static synchronized NetworkManager instance(Context context) {
        NetworkManager networkManager;
        synchronized (NetworkManager.class) {
            if (sNetworkManager == null) {
                sNetworkManager = new NetworkManager(context);
            }
            networkManager = sNetworkManager;
        }
        return networkManager;
    }

    private boolean isConnectedToType(NetworkInfo networkInfo, Integer num) {
        return networkInfo != null && networkInfo.isConnected() && num.intValue() == networkInfo.getType();
    }

    public static synchronized void reset() {
        synchronized (NetworkManager.class) {
            sNetworkManager = null;
        }
    }

    public void acquireWakeLock(PowerManager.WakeLock wakeLock) {
        if (wakeLock != null) {
            try {
                wakeLock.acquire();
            } catch (SecurityException e) {
                Log.e(TAG, "acquireWifiLock : SecurityException caught while trying to acquire wake lock: ", e);
            }
        }
    }

    public void acquireWifiLock(WifiManager.WifiLock wifiLock) {
        if (wifiLock != null) {
            try {
                wifiLock.acquire();
            } catch (SecurityException e) {
                Log.e(TAG, "acquireWifiLock : SecurityException caught while trying to acquire wifi lock: ", e);
            }
        }
    }

    public PowerManager.WakeLock createWakeLock(String str) {
        PowerManager powerManager = this.mPowerManager;
        if (powerManager != null) {
            return powerManager.newWakeLock(1, str);
        }
        return null;
    }

    public WifiManager.WifiLock createWifiLock(String str) {
        WifiManager wifiManager = this.mWifiManager;
        if (wifiManager != null) {
            int i = Build.VERSION.SDK_INT;
            return wifiManager.createWifiLock(3, str);
        }
        return null;
    }

    public boolean isConnected() {
        NetworkInfo activeNetworkInfo = this.mConnectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public boolean isEthernetConnected() {
        NetworkInfo activeNetworkInfo = this.mConnectivityManager.getActiveNetworkInfo();
        int i = Build.VERSION.SDK_INT;
        return isConnectedToType(activeNetworkInfo, 9);
    }

    public boolean isWanConnected() {
        NetworkInfo activeNetworkInfo = this.mConnectivityManager.getActiveNetworkInfo();
        for (Integer num : TYPE_WAN_LIST) {
            if (isConnectedToType(activeNetworkInfo, num)) {
                return true;
            }
        }
        return false;
    }

    public boolean isWifiConnected() {
        return isConnectedToType(this.mConnectivityManager.getActiveNetworkInfo(), 1);
    }

    public void registerNetworkListener(NetworkListener networkListener) {
        this.mNetworkBroadcastReceiver.registerNetworkListener(networkListener);
    }

    public void releaseWakeLock(PowerManager.WakeLock wakeLock) {
        if (wakeLock == null || !wakeLock.isHeld()) {
            return;
        }
        wakeLock.release();
    }

    public void releaseWifiLock(WifiManager.WifiLock wifiLock) {
        if (wifiLock == null || !wifiLock.isHeld()) {
            return;
        }
        wifiLock.release();
    }

    public void unregisterNetworkListener(NetworkListener networkListener) {
        this.mNetworkBroadcastReceiver.unregisterNetworkListener(networkListener);
    }

    public void acquireWakeLock(PowerManager.WakeLock wakeLock, long j) {
        if (wakeLock != null && j > 0) {
            try {
                wakeLock.acquire(j);
                return;
            } catch (SecurityException e) {
                Log.e(TAG, "acquireWifiLock : SecurityException caught while trying to acquire wake lock with timeout: ", e);
                return;
            }
        }
        acquireWakeLock(wakeLock);
    }
}
