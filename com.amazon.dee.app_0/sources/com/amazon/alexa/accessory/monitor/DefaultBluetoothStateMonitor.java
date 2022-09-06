package com.amazon.alexa.accessory.monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.monitor.BluetoothStateMonitor;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes.dex */
public final class DefaultBluetoothStateMonitor implements BluetoothStateMonitor {
    private final BroadcastReceiver bluetoothReceiver;
    private final Context context;
    private final Set<BluetoothStateMonitor.Observer> observers;

    /* loaded from: classes.dex */
    private final class BluetoothReceiver extends BroadcastReceiver {
        private BluetoothReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (!"android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction())) {
                return;
            }
            int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 10);
            if (intExtra == 12) {
                Logger.d("BluetoothStateMonitor: Bluetooth turned on.");
                DefaultBluetoothStateMonitor.this.notifyBluetoothStateOn();
            } else if (intExtra != 10) {
            } else {
                Logger.d("BluetoothStateMonitor: Bluetooth turned off.");
                DefaultBluetoothStateMonitor.this.notifyBluetoothStateOff();
            }
        }
    }

    public DefaultBluetoothStateMonitor(Context context) {
        Preconditions.notNull(context, "context");
        this.context = context;
        this.observers = new HashSet();
        this.bluetoothReceiver = new BluetoothReceiver();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyBluetoothStateOff() {
        for (BluetoothStateMonitor.Observer observer : new ArrayList(this.observers)) {
            observer.onBluetoothDisabled();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyBluetoothStateOn() {
        for (BluetoothStateMonitor.Observer observer : new ArrayList(this.observers)) {
            observer.onBluetoothEnabled();
        }
    }

    @Override // com.amazon.alexa.accessory.monitor.BluetoothStateMonitor
    public void addObserver(BluetoothStateMonitor.Observer observer) {
        Preconditions.mainThread();
        Preconditions.notNull(observer, "observer");
        if (this.observers.isEmpty()) {
            this.context.registerReceiver(this.bluetoothReceiver, GeneratedOutlineSupport1.outline10("android.bluetooth.adapter.action.STATE_CHANGED"));
        }
        this.observers.add(observer);
    }

    @Override // com.amazon.alexa.accessory.monitor.BluetoothStateMonitor
    public void removeObserver(BluetoothStateMonitor.Observer observer) {
        Preconditions.mainThread();
        Preconditions.notNull(observer, "observer");
        if (!this.observers.remove(observer) || !this.observers.isEmpty()) {
            return;
        }
        this.context.unregisterReceiver(this.bluetoothReceiver);
    }
}
