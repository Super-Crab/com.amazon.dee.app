package com.amazon.alexa.presence.detection;

import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
/* loaded from: classes9.dex */
public class BLEScannerCallback extends ScanCallback {
    private static BLEScannerCallback bleScannerInstance;
    private final BlePacketConsumer mBlePacketConsumer;

    /* loaded from: classes9.dex */
    public static class Builder {
        private BlePacketConsumer mBlePacketConsumer;

        public BLEScannerCallback build() {
            if (BLEScannerCallback.bleScannerInstance == null) {
                synchronized (BLEScannerCallback.class) {
                    if (BLEScannerCallback.bleScannerInstance == null) {
                        BLEScannerCallback unused = BLEScannerCallback.bleScannerInstance = new BLEScannerCallback(this.mBlePacketConsumer);
                    }
                }
            }
            return BLEScannerCallback.bleScannerInstance;
        }

        public Builder withConsumer(BlePacketConsumer blePacketConsumer) {
            this.mBlePacketConsumer = blePacketConsumer;
            return this;
        }
    }

    @Override // android.bluetooth.le.ScanCallback
    public void onScanFailed(int i) {
        super.onScanFailed(i);
        this.mBlePacketConsumer.executeOnScanFailed(i);
    }

    @Override // android.bluetooth.le.ScanCallback
    public void onScanResult(int i, ScanResult scanResult) {
        super.onScanResult(i, scanResult);
        this.mBlePacketConsumer.executeOnScanDetection(i, scanResult);
    }

    private BLEScannerCallback(BlePacketConsumer blePacketConsumer) {
        this.mBlePacketConsumer = blePacketConsumer;
    }
}
