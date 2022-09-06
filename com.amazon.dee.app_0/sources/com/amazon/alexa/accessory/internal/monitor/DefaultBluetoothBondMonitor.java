package com.amazon.alexa.accessory.internal.monitor;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Build;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.accessory.internal.PeripheralDevice;
import com.amazon.alexa.accessory.internal.PeripheralDevices;
import com.amazon.alexa.accessory.internal.bluetooth.BluetoothBondStateChangedReceiver;
import com.amazon.alexa.accessory.internal.bluetooth.BluetoothUtils;
import com.amazon.alexa.accessory.internal.monitor.BluetoothBondMonitor;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes.dex */
public final class DefaultBluetoothBondMonitor implements BluetoothBondMonitor {
    private final BluetoothBondStateChangedReceiver bondStateReceiver;
    private final Context context;
    private final Set<BluetoothBondMonitor.Observer> observers;

    public DefaultBluetoothBondMonitor(Context context) {
        Preconditions.notNull(context, "context");
        this.context = context;
        this.observers = new HashSet();
        this.bondStateReceiver = new BluetoothBondStateChangedReceiver(new BluetoothBondStateChangedReceiver.Listener() { // from class: com.amazon.alexa.accessory.internal.monitor.-$$Lambda$DefaultBluetoothBondMonitor$tTvdn9hxFNHdE0i88w8eHjLhuQI
            @Override // com.amazon.alexa.accessory.internal.bluetooth.BluetoothBondStateChangedReceiver.Listener
            public final void onBondStateChanged(BluetoothDevice bluetoothDevice, int i, int i2) {
                DefaultBluetoothBondMonitor.this.lambda$new$0$DefaultBluetoothBondMonitor(bluetoothDevice, i, i2);
            }
        });
    }

    private static String getBondStateString(int i) {
        switch (i) {
            case 10:
                return "NONE";
            case 11:
                return "BONDING";
            case 12:
                return "BONDED";
            default:
                return GeneratedOutlineSupport1.outline52("UNKNOWN(", i, ")");
        }
    }

    private void notifyDeviceBondRemoved(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return;
        }
        Logger.d("BluetoothBondMonitor: a device bond was removed. address=%s, type=%s", bluetoothDevice, BluetoothUtils.bluetoothDeviceTypeToString(bluetoothDevice.getType()));
        for (BluetoothBondMonitor.Observer observer : new ArrayList(this.observers)) {
            observer.onBondRemoved(new PeripheralDevice(bluetoothDevice));
        }
    }

    private void notifyDeviceBonded(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return;
        }
        Logger.d("BluetoothBondMonitor: a device bond was added. address=%s, type=%s", bluetoothDevice, BluetoothUtils.bluetoothDeviceTypeToString(bluetoothDevice.getType()));
        for (BluetoothBondMonitor.Observer observer : new ArrayList(this.observers)) {
            observer.onBondedAdded(new PeripheralDevice(bluetoothDevice));
        }
    }

    @Override // com.amazon.alexa.accessory.internal.monitor.BluetoothBondMonitor
    public void addObserver(BluetoothBondMonitor.Observer observer) {
        Preconditions.mainThread();
        Preconditions.notNull(observer, "observer");
        if (this.observers.isEmpty()) {
            this.context.registerReceiver(this.bondStateReceiver, GeneratedOutlineSupport1.outline10("android.bluetooth.device.action.BOND_STATE_CHANGED"));
        }
        this.observers.add(observer);
    }

    @Override // com.amazon.alexa.accessory.internal.monitor.BluetoothBondMonitor
    public PeripheralDevices getBondedDevices() {
        BluetoothAdapter adapter = ((BluetoothManager) this.context.getSystemService("bluetooth")).getAdapter();
        if (Build.VERSION.SDK_INT >= 31 && ContextCompat.checkSelfPermission(this.context, "android.permission.BLUETOOTH_CONNECT") != 0) {
            Logger.d("BluetoothBondMonitor: cannot check bonded devices without android.permission.BLUETOOTH_CONNECT permissions");
            return PeripheralDevices.unavailable();
        } else if (adapter != null && adapter.isEnabled()) {
            Set<BluetoothDevice> bondedDevices = adapter.getBondedDevices();
            if (bondedDevices == null) {
                return PeripheralDevices.unavailable();
            }
            HashSet hashSet = new HashSet();
            for (BluetoothDevice bluetoothDevice : bondedDevices) {
                hashSet.add(new PeripheralDevice(bluetoothDevice));
            }
            return new PeripheralDevices(hashSet);
        } else {
            return PeripheralDevices.unavailable();
        }
    }

    public /* synthetic */ void lambda$new$0$DefaultBluetoothBondMonitor(BluetoothDevice bluetoothDevice, int i, int i2) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BluetoothBondMonitor: state for ");
        outline107.append(bluetoothDevice.getAddress());
        outline107.append(" changed from ");
        outline107.append(getBondStateString(i));
        outline107.append(" to ");
        outline107.append(getBondStateString(i2));
        Logger.d(outline107.toString());
        if (i == 11 && i2 == 12) {
            notifyDeviceBonded(bluetoothDevice);
        } else if (i != 12 || i2 != 10) {
        } else {
            notifyDeviceBondRemoved(bluetoothDevice);
        }
    }

    @Override // com.amazon.alexa.accessory.internal.monitor.BluetoothBondMonitor
    public void removeObserver(BluetoothBondMonitor.Observer observer) {
        Preconditions.mainThread();
        Preconditions.notNull(observer, "observer");
        if (!this.observers.remove(observer) || !this.observers.isEmpty()) {
            return;
        }
        this.context.unregisterReceiver(this.bondStateReceiver);
    }
}
