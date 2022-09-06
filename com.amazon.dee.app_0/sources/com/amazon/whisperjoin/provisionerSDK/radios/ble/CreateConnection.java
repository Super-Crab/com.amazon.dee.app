package com.amazon.whisperjoin.provisionerSDK.radios.ble;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import com.amazon.whisperbridge.Transport;
import com.amazon.whisperbridge.ble.BleGattClient;
import com.amazon.whisperbridge.ble.BleManager;
import java.util.Map;
import java.util.concurrent.Callable;
/* loaded from: classes13.dex */
class CreateConnection implements Callable<GattClientCallback> {
    final BleManager mBleManager;
    final Map<BluetoothDevice, BLEClients> mClientMap;
    private final Transport.DeviceConnectionStateChangedListener mConnectionStateChangedListener;
    final Context mContext;
    final BluetoothDevice mDevice;

    public CreateConnection(BleManager bleManager, Map<BluetoothDevice, BLEClients> map, Context context, BluetoothDevice bluetoothDevice, Transport.DeviceConnectionStateChangedListener deviceConnectionStateChangedListener) {
        if (bleManager != null) {
            if (map == null) {
                throw new IllegalArgumentException("clientMap can not be null");
            }
            if (context == null) {
                throw new IllegalArgumentException("context can not be null");
            }
            if (bluetoothDevice == null) {
                throw new IllegalArgumentException("device can not be null");
            }
            if (deviceConnectionStateChangedListener != null) {
                this.mClientMap = map;
                this.mBleManager = bleManager;
                this.mContext = context;
                this.mDevice = bluetoothDevice;
                this.mConnectionStateChangedListener = deviceConnectionStateChangedListener;
                return;
            }
            throw new IllegalArgumentException("deviceEventCallback can not be null");
        }
        throw new IllegalArgumentException("bleManager can not be null");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    /* renamed from: call */
    public GattClientCallback mo6640call() throws Exception {
        BLEClients bLEClients;
        Object obj = new Object();
        PrepareEstablishedConnection prepareEstablishedConnection = new PrepareEstablishedConnection(this.mContext);
        GattClientCallback gattClientCallback = new GattClientCallback(prepareEstablishedConnection, obj, this.mConnectionStateChangedListener);
        final GattCharacteristicClient gattCharacteristicClient = new GattCharacteristicClient();
        synchronized (obj) {
            final BleGattClient connectToServer = this.mBleManager.connectToServer(this.mContext, this.mDevice, gattClientCallback, false);
            bLEClients = new BLEClients() { // from class: com.amazon.whisperjoin.provisionerSDK.radios.ble.CreateConnection.1
                @Override // com.amazon.whisperjoin.provisionerSDK.radios.ble.BLEClients
                public BleGattClient getGattClient() {
                    return connectToServer;
                }

                @Override // com.amazon.whisperjoin.provisionerSDK.radios.ble.BLEClients
                public GattCharacteristicClient getServiceClient() {
                    return gattCharacteristicClient;
                }
            };
            prepareEstablishedConnection.setBleClients(connectToServer, gattCharacteristicClient);
        }
        synchronized (this.mClientMap) {
            this.mClientMap.put(this.mDevice, bLEClients);
        }
        return gattClientCallback;
    }
}
