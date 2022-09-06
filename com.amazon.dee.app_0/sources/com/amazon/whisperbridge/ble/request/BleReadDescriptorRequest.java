package com.amazon.whisperbridge.ble.request;

import android.bluetooth.BluetoothDevice;
import com.amazon.whisperbridge.ble.BleGattDescriptor;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class BleReadDescriptorRequest extends BleRequest {
    private final BleGattDescriptor mBleDescriptor;

    public BleReadDescriptorRequest(BluetoothDevice bluetoothDevice, int i, int i2, BleGattDescriptor bleGattDescriptor) {
        super(bluetoothDevice, i, i2);
        if (bleGattDescriptor != null) {
            this.mBleDescriptor = bleGattDescriptor;
            return;
        }
        throw new IllegalArgumentException("bleDescriptor unexpectedly null.");
    }

    public BleGattDescriptor getBleDescriptor() {
        return this.mBleDescriptor;
    }

    @Override // com.amazon.whisperbridge.ble.request.BleRequest
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BleReadDescriptorRequest [device=");
        outline107.append(this.mBluetoothDevice);
        outline107.append(", id=");
        outline107.append(this.mRequestID);
        outline107.append(", offset=");
        outline107.append(this.mOffset);
        outline107.append(", descriptor=");
        outline107.append(this.mBleDescriptor);
        outline107.append("]");
        return outline107.toString();
    }
}
