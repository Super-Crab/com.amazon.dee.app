package com.amazon.whisperbridge.ble.request;

import android.bluetooth.BluetoothDevice;
import com.amazon.whisperbridge.ble.BleGattCharacteristic;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class BleReadCharacteristicRequest extends BleRequest {
    private final BleGattCharacteristic mBleCharacteristic;

    public BleReadCharacteristicRequest(BluetoothDevice bluetoothDevice, int i, int i2, BleGattCharacteristic bleGattCharacteristic) {
        super(bluetoothDevice, i, i2);
        if (bleGattCharacteristic != null) {
            this.mBleCharacteristic = bleGattCharacteristic;
            return;
        }
        throw new IllegalArgumentException("characteristic unexpectedly null.");
    }

    public BleGattCharacteristic getBleCharacteristic() {
        return this.mBleCharacteristic;
    }

    @Override // com.amazon.whisperbridge.ble.request.BleRequest
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BleReadCharacteristicRequest [device=");
        outline107.append(this.mBluetoothDevice);
        outline107.append(", id=");
        outline107.append(this.mRequestID);
        outline107.append(", offset=");
        outline107.append(this.mOffset);
        outline107.append(", characteristic=");
        outline107.append(this.mBleCharacteristic);
        outline107.append("]");
        return outline107.toString();
    }
}
