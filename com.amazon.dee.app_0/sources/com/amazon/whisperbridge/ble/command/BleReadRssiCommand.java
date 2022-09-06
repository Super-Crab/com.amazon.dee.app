package com.amazon.whisperbridge.ble.command;

import android.bluetooth.BluetoothGatt;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.TimeUnit;
/* loaded from: classes13.dex */
public class BleReadRssiCommand extends BleCommand<Result> {
    private static final String TAG = "BleReadRssiCommand";
    private final BluetoothGatt mBluetoothGatt;

    /* loaded from: classes13.dex */
    public static class Result {
        public final int rssi;
        public final int status;

        public Result(int i, int i2) {
            this.rssi = i;
            this.status = i2;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Result [rssi=");
            outline107.append(this.rssi);
            outline107.append(", status=");
            return GeneratedOutlineSupport1.outline86(outline107, this.status, "]");
        }
    }

    public BleReadRssiCommand(BluetoothGatt bluetoothGatt) {
        super(new Result(0, -1));
        this.mBluetoothGatt = bluetoothGatt;
    }

    public String toString() {
        return TAG;
    }

    /* JADX WARN: Type inference failed for: r0v7, types: [com.amazon.whisperbridge.ble.command.BleReadRssiCommand$Result, T] */
    @Override // java.util.concurrent.Callable
    /* renamed from: call */
    public Result mo5069call() throws Exception {
        if (!this.mBluetoothGatt.readRemoteRssi()) {
            return (Result) this.mResult;
        }
        if (!this.mResultLatch.await(10L, TimeUnit.SECONDS)) {
            this.mResult = new Result(0, -2);
        }
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BleReadRssiCommand executed with result=");
        outline107.append(this.mResult);
        WJLog.i(str, outline107.toString());
        return (Result) this.mResult;
    }
}
