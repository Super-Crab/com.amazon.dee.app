package com.amazon.whisperbridge.ble.request;

import android.bluetooth.BluetoothDevice;
import com.amazon.whisperbridge.ble.BleGattDescriptor;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class BleWriteDescriptorRequest extends BleRequest {
    private final BleGattDescriptor mBleDescriptor;
    private final boolean mPreparedWrite;
    private final boolean mResponseNeeded;
    private final byte[] mValue;

    public BleWriteDescriptorRequest(BluetoothDevice bluetoothDevice, int i, BleGattDescriptor bleGattDescriptor, boolean z, boolean z2, int i2, byte[] bArr) {
        super(bluetoothDevice, i, i2);
        if (bleGattDescriptor != null) {
            this.mBleDescriptor = bleGattDescriptor;
            this.mPreparedWrite = z;
            this.mResponseNeeded = z2;
            this.mValue = (byte[]) bArr.clone();
            return;
        }
        throw new IllegalArgumentException("descriptor unexpectedly null.");
    }

    public BleGattDescriptor getBleDescriptor() {
        return this.mBleDescriptor;
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
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BleWriteDescriptorRequest [device=");
        outline107.append(this.mBluetoothDevice);
        outline107.append(", id=");
        outline107.append(this.mRequestID);
        outline107.append(", offset=");
        outline107.append(this.mOffset);
        outline107.append(", prepared=");
        outline107.append(this.mPreparedWrite);
        outline107.append(", descriptor=");
        outline107.append(this.mBleDescriptor);
        outline107.append("]");
        return outline107.toString();
    }
}
