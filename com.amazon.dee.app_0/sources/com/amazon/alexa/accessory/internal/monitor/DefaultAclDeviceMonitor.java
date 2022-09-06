package com.amazon.alexa.accessory.internal.monitor;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.amazon.alexa.accessory.internal.PeripheralDevice;
import com.amazon.alexa.accessory.internal.bluetooth.BluetoothUtils;
import com.amazon.alexa.accessory.internal.monitor.AclDeviceMonitor;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes.dex */
public final class DefaultAclDeviceMonitor implements AclDeviceMonitor {
    private final BroadcastReceiver bluetoothReceiver;
    private final Context context;
    private final Set<AclDeviceMonitor.Observer> observers;

    /* loaded from: classes.dex */
    private final class BluetoothReceiver extends BroadcastReceiver {
        private BluetoothReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            if ("android.bluetooth.device.action.ACL_CONNECTED".equals(intent.getAction())) {
                DefaultAclDeviceMonitor.this.notifyDeviceConnected(bluetoothDevice);
            } else if (!"android.bluetooth.device.action.ACL_DISCONNECTED".equals(intent.getAction())) {
            } else {
                DefaultAclDeviceMonitor.this.notifyDeviceDisconnected(bluetoothDevice);
            }
        }
    }

    public DefaultAclDeviceMonitor(Context context) {
        Preconditions.notNull(context, "context");
        this.context = context;
        this.observers = new HashSet();
        this.bluetoothReceiver = new BluetoothReceiver();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyDeviceConnected(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return;
        }
        Logger.d("AclDeviceMonitor: a device connected. address=%s, type=%s, uuids=%s", bluetoothDevice, BluetoothUtils.bluetoothDeviceTypeToString(bluetoothDevice.getType()), bluetoothDevice.getUuids());
        for (AclDeviceMonitor.Observer observer : new ArrayList(this.observers)) {
            observer.onAclConnected(new PeripheralDevice(bluetoothDevice));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyDeviceDisconnected(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return;
        }
        Logger.d("AclDeviceMonitor: a device disconnected. address=%s, type=%s", bluetoothDevice, BluetoothUtils.bluetoothDeviceTypeToString(bluetoothDevice.getType()));
        for (AclDeviceMonitor.Observer observer : new ArrayList(this.observers)) {
            observer.onAclDisconnected(new PeripheralDevice(bluetoothDevice));
        }
    }

    @Override // com.amazon.alexa.accessory.internal.monitor.AclDeviceMonitor
    public void addObserver(AclDeviceMonitor.Observer observer) {
        Preconditions.mainThread();
        Preconditions.notNull(observer, "observer");
        if (this.observers.isEmpty()) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
            intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
            this.context.registerReceiver(this.bluetoothReceiver, intentFilter);
        }
        this.observers.add(observer);
    }

    @Override // com.amazon.alexa.accessory.internal.monitor.AclDeviceMonitor
    public void removeObserver(AclDeviceMonitor.Observer observer) {
        Preconditions.mainThread();
        Preconditions.notNull(observer, "observer");
        if (!this.observers.remove(observer) || !this.observers.isEmpty()) {
            return;
        }
        this.context.unregisterReceiver(this.bluetoothReceiver);
    }
}
