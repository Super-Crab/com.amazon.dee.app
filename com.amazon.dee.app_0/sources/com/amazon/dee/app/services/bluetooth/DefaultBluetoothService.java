package com.amazon.dee.app.services.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import androidx.annotation.GuardedBy;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.accessory.internal.PeripheralDevice;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserverRegistrar;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.google.common.collect.ImmutableMap;
import dagger.Lazy;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
/* loaded from: classes12.dex */
public class DefaultBluetoothService implements BluetoothService {
    public static final String AUDIO_DEVICE_MAC_ID = "deviceMacId";
    public static final String AUDIO_DEVICE_NAME = "deviceName";
    private static final int[] BLUETOOTH_AUDIO_DEVICE_PROFILES = {2, 1};
    private BluetoothDeviceConnectivityChangeReceiver bluetoothDeviceChangeReceiver;
    @GuardedBy("registrationLock")
    private MainActivityLifecycleObserver bluetoothServiceLifecycleObserver;
    private final Context context;
    private final Lazy<Mobilytics> mobilytics;
    private final ReentrantLock registrationLock = new ReentrantLock(true);
    private final BehaviorSubject<List<? extends Map<String, String>>> onBluetoothDevicesChanged = BehaviorSubject.create();
    private Map<String, PeripheralDevice> connectedBluetoothDevices = new HashMap();

    /* loaded from: classes12.dex */
    public class BluetoothDeviceConnectivityChangeReceiver extends BroadcastReceiver {
        public BluetoothDeviceConnectivityChangeReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            if (bluetoothDevice == null) {
                return;
            }
            int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", 1);
            if (intExtra == 2) {
                DefaultBluetoothService.this.onBluetoothAudioDeviceConnected(bluetoothDevice);
            } else if (intExtra != 0) {
            } else {
                DefaultBluetoothService.this.onBluetoothAudioDeviceDisconnected(bluetoothDevice);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public final class BluetoothServiceLifecycleObserver implements MainActivityLifecycleObserver {
        BluetoothServiceLifecycleObserver() {
        }

        @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
        public void onActivityCreated() {
        }

        @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
        public void onActivityDestroy() {
        }

        @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
        public void onActivityPause() {
            DefaultBluetoothService.this.unregisterBluetoothReceiver();
        }

        @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
        public void onActivityResume() {
            DefaultBluetoothService.this.registerBluetoothReceiver();
            DefaultBluetoothService.this.queryConnectedBluetoothAudioDevices();
        }

        @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
        public void onActivityStart() {
        }

        @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
        public void onActivityStop() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes12.dex */
    public final class ConnectedDevicesProfileListener implements BluetoothProfile.ServiceListener {
        private final BluetoothAdapter bluetoothAdapter;
        private final Map<String, PeripheralDevice> connectedDevices = new HashMap();
        private final Set<Integer> unvisitedProfiles = new HashSet();

        ConnectedDevicesProfileListener(BluetoothAdapter bluetoothAdapter) {
            this.bluetoothAdapter = bluetoothAdapter;
            for (int i : DefaultBluetoothService.BLUETOOTH_AUDIO_DEVICE_PROFILES) {
                this.unvisitedProfiles.add(Integer.valueOf(i));
            }
        }

        private void closeBluetoothProfileProxy(int i, BluetoothProfile bluetoothProfile) {
            this.unvisitedProfiles.remove(Integer.valueOf(i));
            if (this.unvisitedProfiles.isEmpty()) {
                DefaultBluetoothService.this.onQueryBluetoothDevicesCompleted(this.connectedDevices);
            }
            this.bluetoothAdapter.closeProfileProxy(i, bluetoothProfile);
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            for (BluetoothDevice bluetoothDevice : bluetoothProfile.getConnectedDevices()) {
                if (bluetoothDevice != null && !this.connectedDevices.containsKey(bluetoothDevice.getAddress())) {
                    this.connectedDevices.put(bluetoothDevice.getAddress(), new PeripheralDevice(bluetoothDevice));
                }
            }
            closeBluetoothProfileProxy(i, bluetoothProfile);
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i) {
        }
    }

    public DefaultBluetoothService(Context context, MainActivityLifecycleObserverRegistrar mainActivityLifecycleObserverRegistrar, Lazy<Mobilytics> lazy) {
        this.context = context;
        this.mobilytics = lazy;
        registerLifecycleObserver(mainActivityLifecycleObserverRegistrar);
    }

    private void registerLifecycleObserver(MainActivityLifecycleObserverRegistrar mainActivityLifecycleObserverRegistrar) {
        this.bluetoothServiceLifecycleObserver = createActivityLifecycleObserver();
        mainActivityLifecycleObserverRegistrar.addObserver(this.bluetoothServiceLifecycleObserver);
    }

    @VisibleForTesting
    MainActivityLifecycleObserver createActivityLifecycleObserver() {
        return new BluetoothServiceLifecycleObserver();
    }

    @VisibleForTesting
    BroadcastReceiver getBluetoothDeviceChangeReceiver() {
        return this.bluetoothDeviceChangeReceiver;
    }

    @Override // com.amazon.dee.app.services.bluetooth.BluetoothService
    public List<? extends Map<String, String>> getBluetoothDevices() {
        ArrayList arrayList = new ArrayList();
        for (PeripheralDevice peripheralDevice : this.connectedBluetoothDevices.values()) {
            ImmutableMap.Builder builder = ImmutableMap.builder();
            builder.mo7828put(AUDIO_DEVICE_NAME, peripheralDevice.getName() == null ? "" : peripheralDevice.getName());
            builder.mo7828put(AUDIO_DEVICE_MAC_ID, peripheralDevice.getAddress());
            arrayList.add(builder.mo7826build());
        }
        return arrayList;
    }

    @VisibleForTesting
    Map<String, PeripheralDevice> getConnectedDevices() {
        return this.connectedBluetoothDevices;
    }

    @VisibleForTesting
    boolean haveSameDevicesData(Map<String, PeripheralDevice> map, Map<String, PeripheralDevice> map2) {
        if (map.keySet().equals(map2.keySet())) {
            for (Map.Entry<String, PeripheralDevice> entry : map.entrySet()) {
                if (!Objects.equals(entry.getValue().getName(), map2.get(entry.getKey()).getName())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    void onBluetoothAudioDeviceConnected(BluetoothDevice bluetoothDevice) {
        if (!this.connectedBluetoothDevices.containsKey(bluetoothDevice.getAddress())) {
            this.connectedBluetoothDevices.put(bluetoothDevice.getAddress(), new PeripheralDevice(bluetoothDevice));
            this.onBluetoothDevicesChanged.onNext(getBluetoothDevices());
        }
    }

    void onBluetoothAudioDeviceDisconnected(BluetoothDevice bluetoothDevice) {
        if (this.connectedBluetoothDevices.containsKey(bluetoothDevice.getAddress())) {
            this.connectedBluetoothDevices.remove(bluetoothDevice.getAddress());
            this.onBluetoothDevicesChanged.onNext(getBluetoothDevices());
        }
    }

    @Override // com.amazon.dee.app.services.bluetooth.BluetoothService
    public Observable<List<? extends Map<String, String>>> onBluetoothDevicesChanged() {
        return this.onBluetoothDevicesChanged.hide();
    }

    void onQueryBluetoothDevicesCompleted(Map<String, PeripheralDevice> map) {
        if (haveSameDevicesData(this.connectedBluetoothDevices, map)) {
            return;
        }
        this.connectedBluetoothDevices.clear();
        this.connectedBluetoothDevices.putAll(map);
        this.onBluetoothDevicesChanged.onNext(getBluetoothDevices());
    }

    void queryConnectedBluetoothAudioDevices() {
        if (Build.VERSION.SDK_INT > 30 && ContextCompat.checkSelfPermission(this.context, "android.permission.BLUETOOTH_CONNECT") != 0) {
            this.mobilytics.mo358get().recordOccurrence(AlexaMetricsConstants.MetricEvents.NEARBY_PERMISSIONS_DENIED, true, AlexaMetricsConstants.MetricsComponents.BLUETOOTH_SERVICE, null);
            return;
        }
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null || !defaultAdapter.isEnabled()) {
            return;
        }
        ConnectedDevicesProfileListener connectedDevicesProfileListener = new ConnectedDevicesProfileListener(defaultAdapter);
        for (int i : BLUETOOTH_AUDIO_DEVICE_PROFILES) {
            defaultAdapter.getProfileProxy(this.context, connectedDevicesProfileListener, i);
        }
    }

    void registerBluetoothReceiver() {
        this.registrationLock.lock();
        try {
            if (this.bluetoothDeviceChangeReceiver == null) {
                this.bluetoothDeviceChangeReceiver = new BluetoothDeviceConnectivityChangeReceiver();
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
                intentFilter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
                this.context.registerReceiver(this.bluetoothDeviceChangeReceiver, intentFilter);
            }
        } finally {
            this.registrationLock.unlock();
        }
    }

    void unregisterBluetoothReceiver() {
        this.registrationLock.lock();
        try {
            if (this.bluetoothDeviceChangeReceiver != null) {
                this.context.unregisterReceiver(this.bluetoothDeviceChangeReceiver);
                this.bluetoothDeviceChangeReceiver = null;
            }
        } finally {
            this.registrationLock.unlock();
        }
    }
}
