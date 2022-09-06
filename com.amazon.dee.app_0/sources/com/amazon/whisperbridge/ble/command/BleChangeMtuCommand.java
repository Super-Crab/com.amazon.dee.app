package com.amazon.whisperbridge.ble.command;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothGatt;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.TimeUnit;
@TargetApi(21)
/* loaded from: classes13.dex */
public class BleChangeMtuCommand extends BleCommand<Result> {
    private static final String TAG = "BleChangeMtuCommand";
    private final BluetoothGatt mBluetoothGatt;
    private final int mMtu;

    /* loaded from: classes13.dex */
    public static class Result {
        public final int mtu;
        public final int status;

        public Result(int i, int i2) {
            this.mtu = i;
            this.status = i2;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Result [mtu=");
            outline107.append(this.mtu);
            outline107.append(", status=");
            return GeneratedOutlineSupport1.outline86(outline107, this.status, "]");
        }
    }

    public BleChangeMtuCommand(BluetoothGatt bluetoothGatt, int i) {
        super(new Result(0, -1));
        this.mBluetoothGatt = bluetoothGatt;
        this.mMtu = i;
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline86(GeneratedOutlineSupport1.outline107("BleChangeMtuCommand [mtu="), this.mMtu, "]");
    }

    /* JADX WARN: Type inference failed for: r0v7, types: [T, com.amazon.whisperbridge.ble.command.BleChangeMtuCommand$Result] */
    @Override // java.util.concurrent.Callable
    /* renamed from: call */
    public Result mo5064call() throws Exception {
        if (!this.mBluetoothGatt.requestMtu(this.mMtu)) {
            return (Result) this.mResult;
        }
        if (!this.mResultLatch.await(10L, TimeUnit.SECONDS)) {
            this.mResult = new Result(0, -2);
        }
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BleChangeMtuCommand executed with result=");
        outline107.append(this.mResult);
        WJLog.i(str, outline107.toString());
        return (Result) this.mResult;
    }
}
