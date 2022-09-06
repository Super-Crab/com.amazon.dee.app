package com.amazon.whisperbridge.ble.command;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattDescriptor;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.TimeUnit;
/* loaded from: classes13.dex */
public class BleReadDescriptorCommand extends BleCommand<Result> {
    private static final String TAG = "BleReadDescriptorCommand";
    private final BluetoothGatt mBluetoothGatt;
    private final BluetoothGattDescriptor mBluetoothGattDescriptor;

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

    public BleReadDescriptorCommand(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor) {
        super(new Result(-1));
        this.mBluetoothGatt = bluetoothGatt;
        this.mBluetoothGattDescriptor = bluetoothGattDescriptor;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BleReadDescriptorCommand [descriptor=");
        outline107.append(this.mBluetoothGattDescriptor.getUuid());
        outline107.append("]");
        return outline107.toString();
    }

    /* JADX WARN: Type inference failed for: r0v7, types: [T, com.amazon.whisperbridge.ble.command.BleReadDescriptorCommand$Result] */
    @Override // java.util.concurrent.Callable
    /* renamed from: call */
    public Result mo5068call() throws Exception {
        if (!this.mBluetoothGatt.readDescriptor(this.mBluetoothGattDescriptor)) {
            return (Result) this.mResult;
        }
        if (!this.mResultLatch.await(10L, TimeUnit.SECONDS)) {
            this.mResult = new Result(-2);
        }
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BleReadDescriptorCommand executed with result=");
        outline107.append(this.mResult);
        WJLog.i(str, outline107.toString());
        return (Result) this.mResult;
    }
}
