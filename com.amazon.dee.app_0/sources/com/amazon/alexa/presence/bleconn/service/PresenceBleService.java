package com.amazon.alexa.presence.bleconn.service;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattServer;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.content.Context;
import android.os.ParcelUuid;
import android.util.Log;
import com.amazon.alexa.presence.bleconn.service.handlers.IdentityRequestHandler;
import com.amazon.alexa.presence.library.interfaces.Supplier;
import com.amazon.alexa.presence.service.PresenceForegroundService;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import javax.inject.Inject;
/* loaded from: classes9.dex */
public class PresenceBleService {
    private static final String TAG = "com.amazon.alexa.presence.bleconn.service.PresenceBleService";
    private AdvertiseCallback advertisingCallback;
    private final BleConnServiceComponent bleConnServiceComponent;
    private final BluetoothAdapter bluetoothAdapter;
    private BluetoothLeAdvertiser bluetoothLeAdvertiser;
    private final BluetoothManager bluetoothManager;
    private final Context context;
    private BluetoothGattServer currentBtGattServer;
    private byte[] currentServiceIdData;
    private final Object lock;
    private final AtomicReference<String> serviceState;
    private static final UUID PRESENCE_BLE_CONN_SERVICE_ID = UUID.fromString("0000fe15-0000-1000-8000-00805f9b34fb");
    private static final ParcelUuid SERVICE_ID_PARCEL = new ParcelUuid(PRESENCE_BLE_CONN_SERVICE_ID);
    private static final Random RNG = new SecureRandom();
    public static final BluetoothGattCharacteristic READ_IDENTITY_CHARACTERISTIC = new BluetoothGattCharacteristic(UUID.fromString("ab8a1a4d-7d63-4711-b106-2f313435ad9d"), 2, 1);
    public static final BluetoothGattCharacteristic WRITE_REQUESTER_GROUPS_CHARACTERISTIC = new BluetoothGattCharacteristic(UUID.fromString("154f0121-d5eb-46b6-a56a-ba4e9372e581"), 8, 16);

    /* loaded from: classes9.dex */
    public static class ServiceState {
        public static final String RUNNING = "RUNNING";
        public static final String STARTING = "STARTING";
        public static final String STOPPED = "STOPPED";
        public static final String STOPPING = "STOPPING";
    }

    public PresenceBleService(Context context, BluetoothManager bluetoothManager, BluetoothAdapter bluetoothAdapter) {
        this(context, bluetoothManager, bluetoothAdapter, new BleConnServiceComponent(context, new BleConnIdentityComponent(context)));
    }

    private BluetoothLeAdvertiser advertiser() {
        synchronized (this.lock) {
            if (this.bluetoothLeAdvertiser == null) {
                this.bluetoothLeAdvertiser = this.bluetoothAdapter.getBluetoothLeAdvertiser();
            }
        }
        return this.bluetoothLeAdvertiser;
    }

    private AdvertiseCallback callback() {
        synchronized (this.lock) {
            if (this.advertisingCallback == null) {
                this.advertisingCallback = new AdvertiseCallback() { // from class: com.amazon.alexa.presence.bleconn.service.PresenceBleService.1
                    @Override // android.bluetooth.le.AdvertiseCallback
                    public void onStartFailure(int i) {
                        super.onStartFailure(i);
                        String unused = PresenceBleService.TAG;
                        String str = "Advertising failed to start. Error code: " + i;
                        new PresenceForegroundService.Controls(PresenceBleService.this.context).notifyPresenceServiceToStop();
                    }

                    @Override // android.bluetooth.le.AdvertiseCallback
                    public void onStartSuccess(AdvertiseSettings advertiseSettings) {
                        super.onStartSuccess(advertiseSettings);
                    }
                };
            }
        }
        return this.advertisingCallback;
    }

    private boolean canAdvertise() {
        return this.bluetoothAdapter.getBluetoothLeAdvertiser() != null;
    }

    private void changeServerState(String str, String str2) {
        if (this.serviceState.compareAndSet(str, str2)) {
            return;
        }
        throw new IllegalStateException(String.format("Attempted state change %s -> %s, but was in state %s.", str, str2, this.serviceState.get()));
    }

    private static AdvertiseSettings defaultServiceAdvertisementSettings() {
        return new AdvertiseSettings.Builder().setAdvertiseMode(1).setConnectable(true).setTimeout(0).setTxPowerLevel(3).build();
    }

    private boolean deviceIsBleCapable() {
        return this.context.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le");
    }

    private static byte[] generate16ByteRandomIdentifier(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.putLong(RNG.nextLong());
        wrap.putLong(RNG.nextLong());
        byte[] array = wrap.array();
        array[0] = 0;
        array[1] = 2;
        return array;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BluetoothGattService lambda$startGattServer$1(BluetoothGattService bluetoothGattService) {
        return bluetoothGattService;
    }

    private synchronized void startAdvertising() {
        synchronized (this.lock) {
            BluetoothLeAdvertiser advertiser = advertiser();
            if (advertiser == null) {
                Log.w(TAG, "No advertiser found, unable to start advertising");
                return;
            }
            Log.i(TAG, "Starting Advertiser");
            advertiser.startAdvertising(defaultServiceAdvertisementSettings(), new AdvertiseData.Builder().setIncludeDeviceName(false).setIncludeTxPowerLevel(true).addServiceUuid(SERVICE_ID_PARCEL).addServiceData(SERVICE_ID_PARCEL, this.currentServiceIdData).build(), callback());
        }
    }

    private void startGattServer() {
        final BluetoothGattService bluetoothGattService = new BluetoothGattService(PRESENCE_BLE_CONN_SERVICE_ID, 0);
        bluetoothGattService.addCharacteristic(READ_IDENTITY_CHARACTERISTIC);
        bluetoothGattService.addCharacteristic(WRITE_REQUESTER_GROUPS_CHARACTERISTIC);
        ServiceContext serviceContext = new ServiceContext(new Supplier() { // from class: com.amazon.alexa.presence.bleconn.service.-$$Lambda$PresenceBleService$q_igkaLsD7PELNG76FBLQAIPrOY
            @Override // com.amazon.alexa.presence.library.interfaces.Supplier
            public final Object get() {
                return PresenceBleService.this.lambda$startGattServer$0$PresenceBleService();
            }
        }, new Supplier() { // from class: com.amazon.alexa.presence.bleconn.service.-$$Lambda$PresenceBleService$ZdvNUjf8M48Z2ox5JPdk84mC4GM
            @Override // com.amazon.alexa.presence.library.interfaces.Supplier
            public final Object get() {
                BluetoothGattService bluetoothGattService2 = bluetoothGattService;
                PresenceBleService.lambda$startGattServer$1(bluetoothGattService2);
                return bluetoothGattService2;
            }
        });
        IdentityRequestHandler identityRequestHandler = this.bleConnServiceComponent.getIdentityRequestHandler();
        identityRequestHandler.onIdentityReadRequested(new PresenceFeatureSuggestionSubscriber(this.context));
        GattRequestRouter registerWriteCharacteristicRoute = new GattRequestRouter(serviceContext).registerReadCharacteristicRoute(READ_IDENTITY_CHARACTERISTIC, identityRequestHandler).registerWriteCharacteristicRoute(WRITE_REQUESTER_GROUPS_CHARACTERISTIC, identityRequestHandler);
        this.currentBtGattServer = this.bluetoothManager.openGattServer(this.context, registerWriteCharacteristicRoute);
        this.currentBtGattServer.addService(bluetoothGattService);
        registerWriteCharacteristicRoute.awaitServiceAdded(15000);
    }

    private void startService() {
        rotateServiceData();
        startGattServer();
        startAdvertising();
    }

    private void stopAdvertising() {
        Log.i(TAG, "Stopping advertising");
        if (this.bluetoothAdapter == null) {
            Log.w(TAG, "No adapter found, unable to stop advertising");
            return;
        }
        BluetoothLeAdvertiser advertiser = advertiser();
        if (advertiser == null) {
            Log.w(TAG, "No advertiser found, unable to stop advertising");
        } else {
            advertiser.stopAdvertising(callback());
        }
    }

    private void stopGattServer() {
        this.currentBtGattServer.clearServices();
        this.currentBtGattServer.close();
        this.currentBtGattServer = null;
    }

    private void stopService() {
        stopAdvertising();
        stopGattServer();
    }

    public synchronized boolean canOperateService() {
        boolean z;
        if (deviceIsBleCapable()) {
            if (canAdvertise()) {
                z = true;
            }
        }
        z = false;
        return z;
    }

    public synchronized String currentState() {
        return this.serviceState.get();
    }

    public synchronized boolean isRunning() {
        return currentState().equals(ServiceState.RUNNING);
    }

    public /* synthetic */ BluetoothGattServer lambda$startGattServer$0$PresenceBleService() {
        return this.currentBtGattServer;
    }

    public synchronized void rotateServiceData() {
        long currentTimeMillis = System.currentTimeMillis();
        this.currentServiceIdData = generate16ByteRandomIdentifier(this.currentServiceIdData);
        Log.i(TAG, "Rotating service data.  New ID: " + this.currentServiceIdData);
        if (this.serviceState.get() == ServiceState.RUNNING) {
            stopAdvertising();
            startAdvertising();
        }
        String str = "Service rotation duration : " + (System.currentTimeMillis() - currentTimeMillis) + "ms";
    }

    public synchronized boolean start() {
        if (this.serviceState.get() != ServiceState.STOPPED) {
            Log.i(TAG, "Service start requested when service is not stopped, ignoring.");
            return false;
        }
        Log.i(TAG, "Starting Presence Ble Gatt Service");
        changeServerState(ServiceState.STOPPED, ServiceState.STARTING);
        if (!canOperateService()) {
            Log.w(TAG, "Device is not currently capable of starting");
            changeServerState(ServiceState.STARTING, ServiceState.STOPPED);
            return false;
        }
        try {
            startService();
            changeServerState(ServiceState.STARTING, ServiceState.RUNNING);
            Log.i(TAG, "Presence Ble Gatt Service started.");
            return true;
        } catch (Exception e) {
            Log.w(TAG, "Unable to start service due to unhandled exception.", e);
            changeServerState(ServiceState.STARTING, ServiceState.STOPPED);
            return false;
        }
    }

    public synchronized void stop() {
        if (this.serviceState.get() != ServiceState.RUNNING) {
            Log.i(TAG, "Service stop requested when service is not running, ignoring.");
            return;
        }
        Log.i(TAG, "Stopping Presence Ble Gatt Service");
        changeServerState(ServiceState.RUNNING, ServiceState.STOPPING);
        try {
            stopService();
            changeServerState(ServiceState.STOPPING, ServiceState.STOPPED);
            Log.i(TAG, "Presence Ble Gatt Service stopped.");
        } catch (Exception e) {
            Log.w(TAG, "Unable to gracefully stop service, exception encountered.");
            changeServerState(ServiceState.STOPPING, ServiceState.STOPPED);
            throw new RuntimeException(e);
        }
    }

    @Inject
    public PresenceBleService(Context context, BluetoothManager bluetoothManager, BluetoothAdapter bluetoothAdapter, BleConnServiceComponent bleConnServiceComponent) {
        this.serviceState = new AtomicReference<>(ServiceState.STOPPED);
        this.currentServiceIdData = generate16ByteRandomIdentifier(new byte[16]);
        this.currentBtGattServer = null;
        this.bluetoothLeAdvertiser = null;
        this.advertisingCallback = null;
        this.lock = new Object();
        this.context = (Context) Objects.requireNonNull(context);
        this.bluetoothManager = (BluetoothManager) Objects.requireNonNull(bluetoothManager);
        this.bluetoothAdapter = (BluetoothAdapter) Objects.requireNonNull(bluetoothAdapter);
        this.bleConnServiceComponent = (BleConnServiceComponent) Objects.requireNonNull(bleConnServiceComponent);
    }
}
