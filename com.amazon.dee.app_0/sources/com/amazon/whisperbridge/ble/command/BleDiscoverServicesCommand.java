package com.amazon.whisperbridge.ble.command;

import android.bluetooth.BluetoothGatt;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.TimeUnit;
/* loaded from: classes13.dex */
public class BleDiscoverServicesCommand extends BleCommand<Result> {
    private static final String TAG = "BleDiscoverServicesCommand";
    private final BluetoothGatt mBluetoothGatt;

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

    public BleDiscoverServicesCommand(BluetoothGatt bluetoothGatt) {
        super(new Result(-1));
        this.mBluetoothGatt = bluetoothGatt;
    }

    public String toString() {
        return TAG;
    }

    /* JADX WARN: Type inference failed for: r0v7, types: [T, com.amazon.whisperbridge.ble.command.BleDiscoverServicesCommand$Result] */
    @Override // java.util.concurrent.Callable
    /* renamed from: call */
    public Result mo5066call() throws Exception {
        if (!this.mBluetoothGatt.discoverServices()) {
            return (Result) this.mResult;
        }
        if (!this.mResultLatch.await(10L, TimeUnit.SECONDS)) {
            this.mResult = new Result(-2);
        }
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BleDiscoverServicesCommand executed with result=");
        outline107.append(this.mResult);
        WJLog.i(str, outline107.toString());
        return (Result) this.mResult;
    }
}
