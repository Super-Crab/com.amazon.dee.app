package com.amazon.whisperbridge.ble.command;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothGatt;
import com.amazon.whisperbridge.ble.BleGattClient;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
@TargetApi(21)
/* loaded from: classes13.dex */
public class BleChangeConnectionPriorityCommand extends BleCommand<Boolean> {
    private static final String TAG = "BleChangeConnectionPriorityCommand";
    private final BleGattClient.BleGattCallback mBleCallback;
    private final BluetoothGatt mBluetoothGatt;
    private final int mPriority;

    public BleChangeConnectionPriorityCommand(BleGattClient.BleGattCallback bleGattCallback, BluetoothGatt bluetoothGatt, int i) {
        super(false);
        this.mBleCallback = bleGattCallback;
        this.mBluetoothGatt = bluetoothGatt;
        this.mPriority = i;
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline86(GeneratedOutlineSupport1.outline107("BleChangeConnectionPriorityCommand [priority="), this.mPriority, "]");
    }

    @Override // java.util.concurrent.Callable
    /* renamed from: call */
    public Boolean mo5063call() throws Exception {
        boolean requestConnectionPriority = this.mBluetoothGatt.requestConnectionPriority(this.mPriority);
        this.mBleCallback.onConnectionPriorityChange(this.mBluetoothGatt, requestConnectionPriority);
        String str = TAG;
        WJLog.i(str, "BleChangeConnectionPriorityCommand executed with status=" + requestConnectionPriority);
        return Boolean.valueOf(requestConnectionPriority);
    }
}
