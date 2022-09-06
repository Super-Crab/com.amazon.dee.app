package com.amazon.whisperbridge.ble.request;

import android.bluetooth.BluetoothDevice;
import com.amazon.whisperbridge.ble.BleGattCharacteristic;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class BleWriteCharacteristicRequest extends BleRequest {
    private final BleGattCharacteristic mBleCharacteristic;
    private final boolean mPreparedWrite;
    private final boolean mResponseNeeded;
    private final byte[] mValue;

    public BleWriteCharacteristicRequest(BluetoothDevice bluetoothDevice, int i, BleGattCharacteristic bleGattCharacteristic, boolean z, boolean z2, int i2, byte[] bArr) {
        super(bluetoothDevice, i, i2);
        if (bleGattCharacteristic != null) {
            this.mBleCharacteristic = bleGattCharacteristic;
            this.mPreparedWrite = z;
            this.mResponseNeeded = z2;
            this.mValue = (byte[]) bArr.clone();
            return;
        }
        throw new IllegalArgumentException("bleCharacteristic unexpectedly null.");
    }

    public BleGattCharacteristic getBleCharacteristic() {
        return this.mBleCharacteristic;
    }

    public byte[] getValue() {
        byte[] bArr = this.mValue;
        if (bArr == null) {
            return null;
        }
        return (byte[]) bArr.clone();
    }

    public boolean isPreparedWrite() {
        return this.mPreparedWrite;
    }

    public boolean isResponseNeeded() {
        return this.mResponseNeeded;
    }

    @Override // com.amazon.whisperbridge.ble.request.BleRequest
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BleWriteCharacteristicRequest [device=");
        outline107.append(this.mBluetoothDevice);
        outline107.append(", id=");
        outline107.append(this.mRequestID);
        outline107.append(", offset=");
        outline107.append(this.mOffset);
        outline107.append(", prepared=");
        outline107.append(this.mPreparedWrite);
        outline107.append(", characteristic=");
        outline107.append(this.mBleCharacteristic);
        outline107.append("]");
        return outline107.toString();
    }
}
