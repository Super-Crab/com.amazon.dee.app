package com.amazon.alexa.accessory.internal.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
/* loaded from: classes.dex */
public final class BluetoothBondStateChangedReceiver extends BroadcastReceiver {
    private final Listener listener;

    /* loaded from: classes.dex */
    public interface Listener {
        void onBondStateChanged(BluetoothDevice bluetoothDevice, int i, int i2);
    }

    public BluetoothBondStateChangedReceiver(Listener listener) {
        Preconditions.notNull(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.listener = listener;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        int intExtra = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", 10);
        int intExtra2 = intent.getIntExtra("android.bluetooth.device.extra.PREVIOUS_BOND_STATE", 10);
        this.listener.onBondStateChanged((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE"), intExtra2, intExtra);
    }
}
