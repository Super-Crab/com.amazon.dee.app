package com.amazon.whisperbridge.ble.request;

import android.bluetooth.BluetoothDevice;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public abstract class BleRequest {
    protected final BluetoothDevice mBluetoothDevice;
    protected final int mOffset;
    protected final int mRequestID;

    /* JADX INFO: Access modifiers changed from: protected */
    public BleRequest(BluetoothDevice bluetoothDevice, int i, int i2) {
        if (bluetoothDevice != null) {
            if (i2 >= 0) {
                this.mBluetoothDevice = bluetoothDevice;
                this.mRequestID = i;
                this.mOffset = i2;
                return;
            }
            throw new IllegalArgumentException("offset unexpectedly negative.");
        }
        throw new IllegalArgumentException("btDevice is unexpectedly null.");
    }

    public BluetoothDevice getBluetoothDevice() {
        return this.mBluetoothDevice;
    }

    public int getOffset() {
        return this.mOffset;
    }

    public int getRequestID() {
        return this.mRequestID;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BleRequest [device=");
        outline107.append(this.mBluetoothDevice);
        outline107.append(", id=");
        outline107.append(this.mRequestID);
        outline107.append(", offset=");
        return GeneratedOutlineSupport1.outline86(outline107, this.mOffset, "]");
    }
}
