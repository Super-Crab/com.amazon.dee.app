package com.amazon.whisperbridge.ble;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Build;
import com.amazon.whisperbridge.ble.BleGattClient;
import com.amazon.whisperbridge.ble.command.BleCommand;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.LinkedList;
import java.util.Queue;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes13.dex */
public class BleGattClientFactory {
    private static final BleGattClientFactory INSTANCE;
    private static final String TAG = "BleGattClientFactory";
    @Inject
    BluetoothGattFactory mBluetoothGattFactory;

    static {
        BleGattClientFactoryComponent create = DaggerBleGattClientFactoryComponent.create();
        BleGattClientFactory bleGattClientFactory = new BleGattClientFactory();
        create.inject(bleGattClientFactory);
        INSTANCE = bleGattClientFactory;
    }

    public static BleGattClientFactory getInstance() {
        return INSTANCE;
    }

    public BleGattClient createGattClient(Context context, BluetoothDevice bluetoothDevice, BleGattClient.Callback callback, boolean z) {
        return createGattClientWithCommandQueue(context, bluetoothDevice, callback, z, new LinkedList());
    }

    BleGattClient createGattClientWithCommandQueue(Context context, BluetoothDevice bluetoothDevice, BleGattClient.Callback callback, boolean z, Queue<BleCommand> queue) {
        if (context != null) {
            if (bluetoothDevice == null) {
                throw new IllegalArgumentException("btDevice cannot be null.");
            }
            if (callback == null) {
                throw new IllegalArgumentException("callback cannot be null.");
            }
            if (queue != null) {
                BleGattClient bleGattClient = new BleGattClient(bluetoothDevice, callback, queue);
                String str = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Creating GATT client on build Version = ");
                outline107.append(Build.VERSION.SDK_INT);
                WJLog.i(str, outline107.toString());
                bleGattClient.setBluetoothGatt(this.mBluetoothGattFactory.createGattClient(context, bluetoothDevice, bleGattClient.getGattCallback(), z));
                if (bleGattClient.getBluetoothGatt() == null) {
                    throw new IllegalStateException("Failed to open Android BluetoothGatt.");
                }
                return bleGattClient;
            }
            throw new IllegalArgumentException("commandQueue cannot be null.");
        }
        throw new IllegalArgumentException("context cannot be null.");
    }
}
