package com.amazon.communication.wifi;
/* loaded from: classes12.dex */
public interface WifiManagerWrapper {

    /* loaded from: classes12.dex */
    public interface WifiLock {
        void acquire();

        boolean isHeld();

        void release();
    }

    /* loaded from: classes12.dex */
    public enum WifiLockType {
        FULL,
        SCAN_ONLY,
        HIGH_PERF
    }

    WifiLock createWifiLock(WifiLockType wifiLockType, String str);

    WifiLock createWifiLock(String str);

    String getBssid();

    String getOUI();
}
