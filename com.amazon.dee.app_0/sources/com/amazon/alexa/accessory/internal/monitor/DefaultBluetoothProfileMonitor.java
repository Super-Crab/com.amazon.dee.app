package com.amazon.alexa.accessory.internal.monitor;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.amazon.alexa.accessory.internal.PeripheralDevice;
import com.amazon.alexa.accessory.internal.bluetooth.BluetoothUtils;
import com.amazon.alexa.accessory.internal.monitor.BluetoothProfileMonitor;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes.dex */
public final class DefaultBluetoothProfileMonitor implements BluetoothProfileMonitor {
    private final BluetoothProfileReceiver bluetoothProfileReceiver;
    private final Context context;
    private final Set<BluetoothProfileMonitor.Observer> observers;

    /* loaded from: classes.dex */
    private final class BluetoothProfileReceiver extends BroadcastReceiver {
        private BluetoothProfileReceiver() {
        }

        private int getProfile(String str) {
            if ("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED".equals(str)) {
                return 2;
            }
            if (!"android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED".equals(str)) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Unexpected profile: ", str));
            }
            return 1;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            if (bluetoothDevice == null) {
                return;
            }
            int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
            int profile = getProfile(intent.getAction());
            if (intExtra == 2) {
                DefaultBluetoothProfileMonitor.this.notifyDeviceConnected(bluetoothDevice, profile);
            } else if (intExtra != 0) {
            } else {
                DefaultBluetoothProfileMonitor.this.notifyDeviceDisconnected(bluetoothDevice, profile);
            }
        }
    }

    public DefaultBluetoothProfileMonitor(Context context) {
        Preconditions.notNull(context, "context");
        this.context = context;
        this.observers = new HashSet();
        this.bluetoothProfileReceiver = new BluetoothProfileReceiver();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyDeviceConnected(BluetoothDevice bluetoothDevice, int i) {
        if (bluetoothDevice == null) {
            return;
        }
        Logger.d("BluetoothProfileMonitor: a device connected over profile %d. address=%s, type=%s", Integer.valueOf(i), bluetoothDevice, BluetoothUtils.bluetoothDeviceTypeToString(bluetoothDevice.getType()));
        for (BluetoothProfileMonitor.Observer observer : new ArrayList(this.observers)) {
            observer.onProfileConnected(new PeripheralDevice(bluetoothDevice), i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyDeviceDisconnected(BluetoothDevice bluetoothDevice, int i) {
        if (bluetoothDevice == null) {
            return;
        }
        Logger.d("BluetoothProfileMonitor: a device disconnected from profile %d. address=%s, type=%s", Integer.valueOf(i), bluetoothDevice, BluetoothUtils.bluetoothDeviceTypeToString(bluetoothDevice.getType()));
        for (BluetoothProfileMonitor.Observer observer : new ArrayList(this.observers)) {
            observer.onProfileDisconnected(new PeripheralDevice(bluetoothDevice), i);
        }
    }

    @Override // com.amazon.alexa.accessory.internal.monitor.BluetoothProfileMonitor
    public void addObserver(BluetoothProfileMonitor.Observer observer) {
        Preconditions.mainThread();
        Preconditions.notNull(observer, "observer");
        if (this.observers.isEmpty()) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
            intentFilter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
            this.context.registerReceiver(this.bluetoothProfileReceiver, intentFilter);
        }
        this.observers.add(observer);
    }

    @Override // com.amazon.alexa.accessory.internal.monitor.BluetoothProfileMonitor
    public void removeObserver(BluetoothProfileMonitor.Observer observer) {
        Preconditions.mainThread();
        Preconditions.notNull(observer, "observer");
        if (!this.observers.remove(observer) || !this.observers.isEmpty()) {
            return;
        }
        this.context.unregisterReceiver(this.bluetoothProfileReceiver);
    }
}
