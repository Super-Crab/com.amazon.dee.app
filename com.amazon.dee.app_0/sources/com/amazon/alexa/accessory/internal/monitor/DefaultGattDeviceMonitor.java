package com.amazon.alexa.accessory.internal.monitor;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.amazon.alexa.accessory.internal.PeripheralDevice;
import com.amazon.alexa.accessory.internal.bluetooth.BluetoothUtils;
import com.amazon.alexa.accessory.internal.monitor.DefaultGattDeviceMonitor;
import com.amazon.alexa.accessory.internal.monitor.GattDeviceMonitor;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public final class DefaultGattDeviceMonitor implements GattDeviceMonitor {
    private final BluetoothManager bluetoothManager;
    private final Context context;
    private final Map<BluetoothDevice, BluetoothGatt> gattConnections;
    private final Handler handler;
    private final Set<GattDeviceMonitor.Observer> observers;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.alexa.accessory.internal.monitor.DefaultGattDeviceMonitor$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 extends BluetoothGattCallback {
        final /* synthetic */ BluetoothDevice val$device;

        AnonymousClass1(BluetoothDevice bluetoothDevice) {
            this.val$device = bluetoothDevice;
        }

        public /* synthetic */ void lambda$onConnectionStateChange$0$DefaultGattDeviceMonitor$1(int i, BluetoothDevice bluetoothDevice, int i2) {
            if (i == 0) {
                DefaultGattDeviceMonitor.this.notifyDeviceDisconnected(bluetoothDevice);
            } else if (i2 != 0 || i != 2) {
            } else {
                DefaultGattDeviceMonitor.this.notifyDeviceConnected(bluetoothDevice);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, final int i, final int i2) {
            Logger.d("Gatt device explorer observed GATT connection state changed for %s: %d", this.val$device, Integer.valueOf(i2));
            Handler handler = DefaultGattDeviceMonitor.this.handler;
            final BluetoothDevice bluetoothDevice = this.val$device;
            handler.post(new Runnable() { // from class: com.amazon.alexa.accessory.internal.monitor.-$$Lambda$DefaultGattDeviceMonitor$1$N5K3zM0xKUo6Y6b1c_iLSR4Ahwc
                @Override // java.lang.Runnable
                public final void run() {
                    DefaultGattDeviceMonitor.AnonymousClass1.this.lambda$onConnectionStateChange$0$DefaultGattDeviceMonitor$1(i2, bluetoothDevice, i);
                }
            });
        }
    }

    public DefaultGattDeviceMonitor(Context context) {
        Preconditions.notNull(context, "context");
        this.context = context;
        this.observers = new HashSet();
        this.gattConnections = new HashMap();
        this.handler = new Handler(Looper.myLooper());
        this.bluetoothManager = (BluetoothManager) context.getSystemService("bluetooth");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyDeviceConnected(BluetoothDevice bluetoothDevice) {
        for (GattDeviceMonitor.Observer observer : new ArrayList(this.observers)) {
            observer.onConnected(new PeripheralDevice(bluetoothDevice));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyDeviceDisconnected(BluetoothDevice bluetoothDevice) {
        for (GattDeviceMonitor.Observer observer : new ArrayList(this.observers)) {
            observer.onDisconnected(new PeripheralDevice(bluetoothDevice));
        }
    }

    @Override // com.amazon.alexa.accessory.internal.monitor.GattDeviceMonitor
    public void addObserver(GattDeviceMonitor.Observer observer) {
        Preconditions.mainThread();
        Preconditions.notNull(observer, "observer");
        this.observers.add(observer);
    }

    @Override // com.amazon.alexa.accessory.internal.monitor.GattDeviceMonitor
    public void cancel(PeripheralDevice peripheralDevice) {
        Preconditions.mainThread();
        BluetoothAdapter adapter = this.bluetoothManager.getAdapter();
        if (adapter != null && adapter.isEnabled()) {
            BluetoothDevice remoteDevice = adapter.getRemoteDevice(peripheralDevice.getAddress());
            BluetoothGatt remove = this.gattConnections.remove(remoteDevice);
            if (remove == null) {
                return;
            }
            remove.disconnect();
            remove.close();
            notifyDeviceDisconnected(remoteDevice);
            return;
        }
        Logger.e("Bluetooth adapter is unavailable. Cannot cancel monitoring of peripheral %s", peripheralDevice);
    }

    @Override // com.amazon.alexa.accessory.internal.monitor.GattDeviceMonitor
    public void monitor(PeripheralDevice peripheralDevice) {
        Preconditions.mainThread();
        BluetoothAdapter adapter = this.bluetoothManager.getAdapter();
        if (adapter != null && adapter.isEnabled()) {
            BluetoothDevice remoteDevice = adapter.getRemoteDevice(peripheralDevice.getAddress());
            if (this.gattConnections.containsKey(remoteDevice)) {
                return;
            }
            Logger.d("Gatt device explorer is exploring a connection with %s over %s", remoteDevice.getAddress(), BluetoothUtils.bluetoothDeviceTypeToString(remoteDevice.getType()));
            this.gattConnections.put(remoteDevice, BluetoothUtils.connectGatt(remoteDevice, this.context, true, new AnonymousClass1(remoteDevice), 2));
            return;
        }
        Logger.e("Bluetooth adapter is unavailable. Cannot monitor peripheral %s", peripheralDevice);
    }

    @Override // com.amazon.alexa.accessory.internal.monitor.GattDeviceMonitor
    public void removeObserver(GattDeviceMonitor.Observer observer) {
        Preconditions.mainThread();
        Preconditions.notNull(observer, "observer");
        this.observers.remove(observer);
    }
}
