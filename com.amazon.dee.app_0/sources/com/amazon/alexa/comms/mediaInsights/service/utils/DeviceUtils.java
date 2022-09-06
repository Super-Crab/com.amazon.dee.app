package com.amazon.alexa.comms.mediaInsights.service.utils;

import android.net.wifi.WifiManager;
import android.os.PowerManager;
/* loaded from: classes6.dex */
public class DeviceUtils {
    public static PowerManager.WakeLock acquireWakeLock(PowerManager powerManager, long j, String str) {
        PowerManager.WakeLock newWakeLock;
        if (powerManager == null || (newWakeLock = powerManager.newWakeLock(1, str)) == null) {
            return null;
        }
        newWakeLock.setReferenceCounted(true);
        newWakeLock.acquire(j);
        return newWakeLock;
    }

    public static WifiManager.WifiLock acquireWifiLock(WifiManager wifiManager, String str) {
        WifiManager.WifiLock createWifiLock;
        if (wifiManager == null || (createWifiLock = wifiManager.createWifiLock(3, str)) == null) {
            return null;
        }
        createWifiLock.setReferenceCounted(true);
        createWifiLock.acquire();
        return createWifiLock;
    }

    public static void releaseWakeLock(PowerManager.WakeLock wakeLock) {
        if (wakeLock == null || !wakeLock.isHeld()) {
            return;
        }
        wakeLock.release();
    }

    public static void releaseWifiLock(WifiManager.WifiLock wifiLock) {
        if (wifiLock == null || !wifiLock.isHeld()) {
            return;
        }
        wifiLock.release();
    }
}
