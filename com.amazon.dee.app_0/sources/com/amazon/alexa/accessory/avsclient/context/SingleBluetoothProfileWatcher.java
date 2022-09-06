package com.amazon.alexa.accessory.avsclient.context;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class SingleBluetoothProfileWatcher implements BluetoothProfileWatcher {
    private boolean active;
    private final Context context;
    private final BluetoothServiceListener serviceListener;

    /* loaded from: classes.dex */
    private static final class BluetoothServiceListener implements BluetoothProfile.ServiceListener {
        private boolean active;
        private BluetoothProfile bluetoothProxy;
        private int profile;

        private BluetoothServiceListener() {
        }

        public void activate(BluetoothAdapter bluetoothAdapter, Context context, int i) {
            if (this.active) {
                return;
            }
            this.active = true;
            this.profile = i;
            Logger.d("BluetoothServiceListener: activating listening for profile %d", Integer.valueOf(i));
            bluetoothAdapter.getProfileProxy(context, this, i);
        }

        void deactivate(BluetoothAdapter bluetoothAdapter) {
            if (!this.active) {
                return;
            }
            this.active = false;
            Logger.d("BluetoothServiceListener: deactivating listening for profile %d", Integer.valueOf(this.profile));
            BluetoothProfile bluetoothProfile = this.bluetoothProxy;
            if (bluetoothProfile == null) {
                return;
            }
            bluetoothAdapter.closeProfileProxy(this.profile, bluetoothProfile);
        }

        List<BluetoothDevice> getActiveDevices() {
            BluetoothProfile bluetoothProfile;
            if (this.active && (bluetoothProfile = this.bluetoothProxy) != null) {
                List<BluetoothDevice> connectedDevices = bluetoothProfile.getConnectedDevices();
                Logger.d("BluetoothServiceListener: returning %d active devices", Integer.valueOf(connectedDevices.size()));
                return connectedDevices;
            }
            Logger.d("BluetoothServiceListener: returning empty list as active devices");
            return new ArrayList();
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            Logger.d("BluetoothServiceListener connected over profile %d", Integer.valueOf(i));
            this.bluetoothProxy = bluetoothProfile;
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i) {
            Logger.d("BluetoothServiceListener disconnected over profile %d", Integer.valueOf(i));
            this.bluetoothProxy = null;
        }
    }

    public SingleBluetoothProfileWatcher(Context context) {
        Preconditions.notNull(context, "context");
        this.context = context;
        this.serviceListener = new BluetoothServiceListener();
    }

    private static BluetoothAdapter getBluetoothAdapter(Context context) {
        BluetoothAdapter adapter = ((BluetoothManager) context.getSystemService("bluetooth")).getAdapter();
        if (adapter == null || !adapter.isEnabled()) {
            return null;
        }
        return adapter;
    }

    @Override // com.amazon.alexa.accessory.avsclient.context.BluetoothProfileWatcher
    public void deactivate() {
        BluetoothAdapter bluetoothAdapter = getBluetoothAdapter(this.context);
        if (bluetoothAdapter == null) {
            Logger.d("SingleBluetoothProfileWatcher: bluetooth adapter is not available. Cannot deactivate.");
            return;
        }
        if (this.active) {
            this.serviceListener.deactivate(bluetoothAdapter);
        }
        this.active = false;
    }

    @Override // com.amazon.alexa.accessory.avsclient.context.BluetoothProfileWatcher
    public void ensureActive(int i) {
        BluetoothAdapter bluetoothAdapter = getBluetoothAdapter(this.context);
        if (bluetoothAdapter == null) {
            Logger.d("SingleBluetoothProfileWatcher: bluetooth adapter is not available. Cannot watch profile status.");
            return;
        }
        if (!this.active) {
            this.serviceListener.activate(bluetoothAdapter, this.context, i);
        }
        this.active = true;
    }

    @Override // com.amazon.alexa.accessory.avsclient.context.BluetoothProfileWatcher
    public List<BluetoothDevice> getActiveDevices() {
        return this.serviceListener.getActiveDevices();
    }
}
