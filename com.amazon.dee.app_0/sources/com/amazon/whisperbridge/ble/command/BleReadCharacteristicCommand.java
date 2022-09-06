package com.amazon.whisperbridge.ble.command;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.TimeUnit;
/* loaded from: classes13.dex */
public class BleReadCharacteristicCommand extends BleCommand<Result> {
    private static final String TAG = "BleReadCharacteristicCommand";
    private final BluetoothGatt mBluetoothGatt;
    private final BluetoothGattCharacteristic mBluetoothGattCharacteristic;

    /* loaded from: classes13.dex */
    public static class Result {
        public final int status;

        public Result(int i) {
            this.status = i;
        }

        public String toString() {
            return GeneratedOutlineSupport1.outline86(GeneratedOutlineSupport1.outline107("Result [status="), this.status, "]");
        }
    }

    public BleReadCharacteristicCommand(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        super(new Result(-1));
        this.mBluetoothGatt = bluetoothGatt;
        this.mBluetoothGattCharacteristic = bluetoothGattCharacteristic;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BleReadCharacteristicCommand [characteristic=");
        outline107.append(this.mBluetoothGattCharacteristic.getUuid());
        outline107.append("]");
        return outline107.toString();
    }

    /* JADX WARN: Type inference failed for: r0v9, types: [T, com.amazon.whisperbridge.ble.command.BleReadCharacteristicCommand$Result] */
    @Override // java.util.concurrent.Callable
    /* renamed from: call */
    public Result mo5067call() throws Exception {
        if (!this.mBluetoothGatt.readCharacteristic(this.mBluetoothGattCharacteristic)) {
            WJLog.e(TAG, "Failed to start readCharacteristic");
            return (Result) this.mResult;
        }
        this.mResultLatch.await(10L, TimeUnit.SECONDS);
        if (!this.mResultLatch.await(10L, TimeUnit.SECONDS)) {
            WJLog.e(TAG, "Failed to complete due to a timeout");
            this.mResult = new Result(-2);
        }
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BleReadCharacteristicCommand executed with result=");
        outline107.append(this.mResult);
        WJLog.i(str, outline107.toString());
        return (Result) this.mResult;
    }
}
