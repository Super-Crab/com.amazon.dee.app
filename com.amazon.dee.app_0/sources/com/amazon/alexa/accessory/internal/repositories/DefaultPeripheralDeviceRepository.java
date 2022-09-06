package com.amazon.alexa.accessory.internal.repositories;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.os.Build;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.accessory.internal.PeripheralDevice;
import com.amazon.alexa.accessory.internal.bluetooth.BluetoothUtils;
import com.amazon.alexa.accessory.internal.repositories.PeripheralDeviceRepository;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* loaded from: classes.dex */
public class DefaultPeripheralDeviceRepository implements PeripheralDeviceRepository {
    private static final int[] SUPPORTED_BLUETOOTH_PROFILES = {2, 1, 3};
    private final Context context;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class ConnectedDevicesProfileListener implements BluetoothProfile.ServiceListener {
        private static final String TAG = "ConnectedDevicesProfileListener:";
        private final BluetoothAdapter bluetoothAdapter;
        private final PeripheralDeviceRepository.Callback callback;
        private final Set<PeripheralDevice> connectedDevices = new HashSet();
        private final Set<Integer> unvisitedProfiles = new HashSet();

        ConnectedDevicesProfileListener(BluetoothAdapter bluetoothAdapter, PeripheralDeviceRepository.Callback callback) {
            this.bluetoothAdapter = bluetoothAdapter;
            this.callback = callback;
            for (int i : DefaultPeripheralDeviceRepository.SUPPORTED_BLUETOOTH_PROFILES) {
                this.unvisitedProfiles.add(Integer.valueOf(i));
            }
        }

        private void closeBluetoothProfileProxy(int i, BluetoothProfile bluetoothProfile) {
            this.unvisitedProfiles.remove(Integer.valueOf(i));
            if (this.unvisitedProfiles.isEmpty()) {
                this.callback.onConnectedBluetoothDevicesFound(this.connectedDevices);
            }
            this.bluetoothAdapter.closeProfileProxy(i, bluetoothProfile);
        }

        void markProfileAsUnvisitable(int i) {
            this.unvisitedProfiles.remove(Integer.valueOf(i));
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            Logger.d("%s onServiceConnected profile: %d", TAG, Integer.valueOf(i));
            List<BluetoothDevice> arrayList = new ArrayList<>();
            try {
                arrayList = bluetoothProfile.getConnectedDevices();
            } catch (NullPointerException e) {
                Logger.e("%s Error in getConnectedDevices", e, TAG);
            }
            for (BluetoothDevice bluetoothDevice : arrayList) {
                if (bluetoothDevice != null && this.connectedDevices.add(new PeripheralDevice(bluetoothDevice))) {
                    Logger.d("Device (%s) is currently connected over bluetooth profile %s.", bluetoothDevice, BluetoothUtils.bluetoothProfileToString(i));
                }
            }
            closeBluetoothProfileProxy(i, bluetoothProfile);
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i) {
            Logger.d("%s onServiceDisconnected profile: %d", TAG, Integer.valueOf(i));
        }
    }

    public DefaultPeripheralDeviceRepository(Context context) {
        this.context = context;
    }

    private boolean registerToSupportedBluetoothProfiles(BluetoothAdapter bluetoothAdapter, ConnectedDevicesProfileListener connectedDevicesProfileListener) {
        int[] iArr;
        boolean z = false;
        for (int i : SUPPORTED_BLUETOOTH_PROFILES) {
            if (!bluetoothAdapter.getProfileProxy(this.context, connectedDevicesProfileListener, i)) {
                Logger.d("Unable to get connected devices for profile %s.", BluetoothUtils.bluetoothProfileToString(i));
                connectedDevicesProfileListener.markProfileAsUnvisitable(i);
            } else {
                z = true;
            }
        }
        return z;
    }

    @Override // com.amazon.alexa.accessory.internal.repositories.PeripheralDeviceRepository
    public void queryConnectedBluetoothDevices(PeripheralDeviceRepository.Callback callback) {
        Preconditions.mainThread();
        Preconditions.notNull(callback, "callback");
        if (Build.VERSION.SDK_INT >= 31 && ContextCompat.checkSelfPermission(this.context, "android.permission.BLUETOOTH_CONNECT") != 0) {
            callback.onQueryFailed(new SecurityException("Need android.permission.BLUETOOTH_CONNECT permission"));
            return;
        }
        BluetoothAdapter bluetoothAdapter = BluetoothUtils.getBluetoothAdapter(this.context);
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            callback.onQueryFailed(new IllegalStateException("Bluetooth not on."));
        }
        if (registerToSupportedBluetoothProfiles(bluetoothAdapter, new ConnectedDevicesProfileListener(bluetoothAdapter, callback))) {
            return;
        }
        Logger.d("Unable to register to any of the supported bluetooth profiles.");
        callback.onConnectedBluetoothDevicesFound(Collections.emptySet());
    }
}
