package com.amazon.communication.wifi;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.amazon.communication.wifi.WifiManagerWrapper;
import com.amazon.dp.logger.DPLogger;
import org.aspectj.lang.JoinPoint;
/* loaded from: classes12.dex */
public class WifiManagerWrapperImpl implements WifiManagerWrapper {
    private static final DPLogger log = new DPLogger("TComm.WifiManagerWrapperImpl");
    private final WifiManager mWifiManager;

    /* renamed from: com.amazon.communication.wifi.WifiManagerWrapperImpl$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$communication$wifi$WifiManagerWrapper$WifiLockType = new int[WifiManagerWrapper.WifiLockType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$communication$wifi$WifiManagerWrapper$WifiLockType[WifiManagerWrapper.WifiLockType.FULL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$communication$wifi$WifiManagerWrapper$WifiLockType[WifiManagerWrapper.WifiLockType.SCAN_ONLY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$communication$wifi$WifiManagerWrapper$WifiLockType[WifiManagerWrapper.WifiLockType.HIGH_PERF.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static final class WifiLockImpl implements WifiManagerWrapper.WifiLock {
        private final WifiManager.WifiLock mLock;
        private final WifiManagerWrapper.WifiLockType mLockType;

        WifiLockImpl(WifiManager.WifiLock wifiLock, WifiManagerWrapper.WifiLockType wifiLockType) {
            this.mLock = wifiLock;
            this.mLockType = wifiLockType;
        }

        @Override // com.amazon.communication.wifi.WifiManagerWrapper.WifiLock
        public void acquire() {
            this.mLock.acquire();
            WifiManagerWrapperImpl.log.info("WifiLockImpl.acquire", "acquired wifiLock", JoinPoint.SYNCHRONIZATION_LOCK, this.mLock, "lockType", this.mLockType);
        }

        @Override // com.amazon.communication.wifi.WifiManagerWrapper.WifiLock
        public boolean isHeld() {
            return this.mLock.isHeld();
        }

        @Override // com.amazon.communication.wifi.WifiManagerWrapper.WifiLock
        public void release() {
            this.mLock.release();
            WifiManagerWrapperImpl.log.info("WifiLockImpl.release", "released wifiLock", JoinPoint.SYNCHRONIZATION_LOCK, this.mLock, "lockType", this.mLockType);
        }
    }

    public WifiManagerWrapperImpl(Context context) {
        this.mWifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
    }

    private int toAndroidWifiLockType(WifiManagerWrapper.WifiLockType wifiLockType) {
        int ordinal = wifiLockType.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return 2;
            }
            if (ordinal == 2) {
                return 3;
            }
            throw new IllegalArgumentException("Unknown lockType: " + wifiLockType);
        }
        return 1;
    }

    @Override // com.amazon.communication.wifi.WifiManagerWrapper
    public WifiManagerWrapper.WifiLock createWifiLock(String str) {
        return createWifiLock(WifiManagerWrapper.WifiLockType.FULL, str);
    }

    @Override // com.amazon.communication.wifi.WifiManagerWrapper
    public String getBssid() {
        WifiInfo connectionInfo = this.mWifiManager.getConnectionInfo();
        if (connectionInfo == null) {
            return null;
        }
        return connectionInfo.getBSSID();
    }

    @Override // com.amazon.communication.wifi.WifiManagerWrapper
    public String getOUI() {
        String bssid = getBssid();
        if (bssid == null) {
            return null;
        }
        String replaceAll = bssid.toLowerCase().replaceAll("[^0-9a-f]", "");
        return replaceAll.length() > 6 ? replaceAll.substring(0, 6) : replaceAll;
    }

    @Override // com.amazon.communication.wifi.WifiManagerWrapper
    public WifiManagerWrapper.WifiLock createWifiLock(WifiManagerWrapper.WifiLockType wifiLockType, String str) {
        return new WifiLockImpl(this.mWifiManager.createWifiLock(toAndroidWifiLockType(wifiLockType), str), wifiLockType);
    }
}
