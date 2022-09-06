package com.amazon.whisperbridge.ble.command;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattDescriptor;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.TimeUnit;
/* loaded from: classes13.dex */
public class BleUpdateNotificationsCommand extends BleCommand<Result> {
    public static final int FAILED_TO_UPDATE_NOTIFICATION_LOCALLY = -3;
    private static final String TAG = "BleUpdateNotificationsCommand";
    private final BluetoothGatt mBluetoothGatt;
    private final BluetoothGattDescriptor mBluetoothGattDescriptor;
    private final boolean mEnable;

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

    public BleUpdateNotificationsCommand(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, boolean z) {
        super(new Result(-1));
        this.mBluetoothGatt = bluetoothGatt;
        this.mBluetoothGattDescriptor = bluetoothGattDescriptor;
        this.mEnable = z;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BleUpdateNotificationsCommand [descriptor");
        outline107.append(this.mBluetoothGattDescriptor.getUuid());
        outline107.append(", enable=");
        return GeneratedOutlineSupport1.outline97(outline107, this.mEnable, "]");
    }

    /* JADX WARN: Type inference failed for: r0v12, types: [com.amazon.whisperbridge.ble.command.BleUpdateNotificationsCommand$Result, T] */
    /* JADX WARN: Type inference failed for: r0v9, types: [com.amazon.whisperbridge.ble.command.BleUpdateNotificationsCommand$Result, T] */
    @Override // java.util.concurrent.Callable
    /* renamed from: call */
    public Result mo5070call() throws Exception {
        if (!this.mBluetoothGatt.setCharacteristicNotification(this.mBluetoothGattDescriptor.getCharacteristic(), this.mEnable)) {
            this.mResult = new Result(-3);
            return (Result) this.mResult;
        } else if (!this.mBluetoothGatt.writeDescriptor(this.mBluetoothGattDescriptor)) {
            return (Result) this.mResult;
        } else {
            if (!this.mResultLatch.await(10L, TimeUnit.SECONDS)) {
                this.mResult = new Result(-2);
            }
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BleUpdateNotificationsCommand executed with result=");
            outline107.append(this.mResult);
            WJLog.i(str, outline107.toString());
            return (Result) this.mResult;
        }
    }
}
