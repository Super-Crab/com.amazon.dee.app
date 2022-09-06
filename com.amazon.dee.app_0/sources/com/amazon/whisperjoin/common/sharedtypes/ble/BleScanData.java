package com.amazon.whisperjoin.common.sharedtypes.ble;

import android.bluetooth.BluetoothDevice;
import androidx.annotation.VisibleForTesting;
/* loaded from: classes13.dex */
public class BleScanData {
    private final BluetoothDevice mBluetoothDevice;
    private final byte[] mRawScanRecord;
    private final int mRssi;
    private final long mTimestampNanos;

    public BleScanData(BluetoothDevice bluetoothDevice, byte[] bArr, int i) {
        this.mTimestampNanos = System.nanoTime();
        this.mBluetoothDevice = bluetoothDevice;
        this.mRawScanRecord = (byte[]) bArr.clone();
        this.mRssi = i;
    }

    public BluetoothDevice getDevice() {
        return this.mBluetoothDevice;
    }

    public byte[] getRawScanRecord() {
        return (byte[]) this.mRawScanRecord.clone();
    }

    public int getRssi() {
        return this.mRssi;
    }

    public long getTimestampNanos() {
        return this.mTimestampNanos;
    }

    @VisibleForTesting
    public BleScanData(long j, BluetoothDevice bluetoothDevice, byte[] bArr, int i) {
        this.mTimestampNanos = j;
        this.mBluetoothDevice = bluetoothDevice;
        this.mRawScanRecord = bArr;
        this.mRssi = i;
    }
}
