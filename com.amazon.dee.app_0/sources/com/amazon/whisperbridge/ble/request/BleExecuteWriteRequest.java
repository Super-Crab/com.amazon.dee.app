package com.amazon.whisperbridge.ble.request;

import android.bluetooth.BluetoothDevice;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class BleExecuteWriteRequest extends BleRequest {
    private final boolean mExecute;

    public BleExecuteWriteRequest(BluetoothDevice bluetoothDevice, int i, boolean z) {
        super(bluetoothDevice, i, 0);
        this.mExecute = z;
    }

    public boolean execute() {
        return this.mExecute;
    }

    @Override // com.amazon.whisperbridge.ble.request.BleRequest
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BleExecuteWriteRequest [device=");
        outline107.append(this.mBluetoothDevice);
        outline107.append(", id=");
        outline107.append(this.mRequestID);
        outline107.append(", offset=");
        outline107.append(this.mOffset);
        outline107.append(", execute=");
        return GeneratedOutlineSupport1.outline97(outline107, this.mExecute, "]");
    }
}
