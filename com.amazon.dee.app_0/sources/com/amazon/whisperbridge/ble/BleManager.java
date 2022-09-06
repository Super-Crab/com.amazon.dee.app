package com.amazon.whisperbridge.ble;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import com.amazon.whisperbridge.ble.BleAdvertiser;
import com.amazon.whisperbridge.ble.BleGattClient;
import com.amazon.whisperbridge.ble.BleGattServer;
import com.amazon.whisperbridge.ble.BleScanner;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import java.util.List;
import java.util.Set;
/* loaded from: classes13.dex */
public class BleManager {
    private static final String TAG = "BleManager";
    BleAdvertiser mBleAdvertiser;
    BleGattServer mBleGattServer;
    BleScanner mBleScanner;
    BluetoothAdapter mBtAdapter;
    BluetoothManager mBtManager;
    boolean mIsInitialized = false;

    public synchronized void bindBleService(BleGattService bleGattService) throws InterruptedException {
        if (this.mBleGattServer != null) {
            this.mBleGattServer.bindBleService(bleGattService);
        } else {
            throw new IllegalStateException("Cannot add bleService when Bluetooth LE GATT server does not exist.");
        }
    }

    public synchronized void cleanupBluetoothLE() {
        if (this.mIsInitialized) {
            if (this.mBleGattServer != null) {
                releaseBleServer();
            }
            if (this.mBleAdvertiser != null) {
                stopAdvertising();
            }
            if (this.mBleScanner != null) {
                stopScanning();
            }
            this.mIsInitialized = false;
        } else {
            throw new IllegalStateException("BleManager cannot be cleaned-up as it not initialized.");
        }
    }

    public synchronized void connectClient(BluetoothDevice bluetoothDevice, boolean z) {
        if (this.mBleGattServer != null) {
            this.mBleGattServer.connectClient(bluetoothDevice, z);
        } else {
            throw new IllegalStateException("Cannot connect client when Bluetooth LE GATT server does not exist.");
        }
    }

    public synchronized BleGattClient connectToServer(Context context, BluetoothDevice bluetoothDevice, BleGattClient.Callback callback, boolean z) throws BluetoothDisabledException {
        String str = TAG;
        WJLog.i(str, "connectToServer with autoconnect: " + z);
        if (!this.mBtAdapter.isEnabled()) {
            throw new BluetoothDisabledException();
        }
        if (context == null) {
            throw new IllegalArgumentException("context cannot be null.");
        }
        if (bluetoothDevice == null) {
            throw new IllegalArgumentException("btDevice cannot be null.");
        }
        if (callback != null) {
        } else {
            throw new IllegalArgumentException("callback cannot be null.");
        }
        return BleGattClientFactory.getInstance().createGattClient(context, bluetoothDevice, callback, z);
    }

    public synchronized void createBleServer(Context context, BleGattServer.Callback callback) throws BluetoothDisabledException {
        if (this.mBleGattServer != null) {
            throw new IllegalStateException("Only one Bluetooth LE GATT server can be active at a time.");
        }
        if (callback != null) {
            if (this.mBtAdapter.isEnabled()) {
                this.mBleGattServer = BleGattServer.createGattServer(context, this.mBtManager, callback);
            } else {
                throw new BluetoothDisabledException();
            }
        } else {
            throw new IllegalArgumentException("callback cannot be null.");
        }
    }

    public synchronized void disconnectClient(BluetoothDevice bluetoothDevice) {
        if (this.mBleGattServer != null) {
            this.mBleGattServer.disconnectClient(bluetoothDevice);
        } else {
            throw new IllegalStateException("Cannot disconnect client when Bluetooth LE GATT server does not exist.");
        }
    }

    public synchronized Set<BluetoothDevice> getConnectedClients() {
        if (this.mBleGattServer != null) {
        } else {
            throw new IllegalStateException("Cannot disconnect client when Bluetooth LE GATT server does not exist.");
        }
        return this.mBleGattServer.getConnectedClients();
    }

    public synchronized void initializeBluetoothLE(Context context, String str) throws BleException {
        if (this.mIsInitialized) {
            throw new IllegalStateException("BleManager is already initialized.");
        }
        if (context != null) {
            if (context.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) {
                this.mBtManager = (BluetoothManager) context.getSystemService("bluetooth");
                if (this.mBtManager != null) {
                    this.mBtAdapter = this.mBtManager.getAdapter();
                    if (this.mBtAdapter != null) {
                        if (str != null && !"".equals(str)) {
                            String str2 = TAG;
                            WJLog.i(str2, "Creating adapter for device " + str + ".");
                            this.mBtAdapter.setName(str);
                        }
                        this.mIsInitialized = true;
                    } else {
                        throw new BleException("Unable to acquire Bluetooth adapter.");
                    }
                } else {
                    throw new BleException("Unable to acquire Bluetooth system service.");
                }
            } else {
                throw new BleException("No Bluetooth LE support.");
            }
        } else {
            throw new IllegalArgumentException("context cannot be null.");
        }
    }

    @TargetApi(21)
    public synchronized boolean isAdvertising() {
        return this.mBleAdvertiser != null;
    }

    public synchronized boolean isBleServerActive() {
        return this.mBleGattServer != null;
    }

    public synchronized boolean isInitialized() {
        return this.mIsInitialized;
    }

    public synchronized boolean isScanning() {
        return this.mBleScanner != null;
    }

    public synchronized void releaseBleServer() {
        if (this.mBleGattServer != null) {
            this.mBleGattServer.close();
            this.mBleGattServer = null;
        } else {
            throw new IllegalStateException("Cannot release Bluetooth LE GATT server as it does not exist.");
        }
    }

    @TargetApi(21)
    public synchronized void startAdvertising(BleAdvertiser.Callback callback, AdvertiseSettings advertiseSettings, AdvertiseData advertiseData, AdvertiseData advertiseData2) throws BleException, BluetoothDisabledException {
        if (this.mBtAdapter.isEnabled()) {
            if (this.mBleAdvertiser != null) {
                throw new IllegalStateException("Only one Bluetooth LE advertisement can be active at a time.");
            }
            if (callback == null) {
                throw new IllegalArgumentException("callback cannot be null.");
            }
            if (advertiseSettings == null) {
                throw new IllegalArgumentException("advertisingSettings cannot be null.");
            }
            if (advertiseData != null) {
                this.mBleAdvertiser = BleAdvertiser.startAdvertising(this.mBtAdapter, callback, advertiseSettings, advertiseData, advertiseData2);
            } else {
                throw new IllegalArgumentException("advertisingData cannot be null");
            }
        } else {
            throw new BluetoothDisabledException();
        }
    }

    public synchronized void startScanning(BleScanner.Callback callback, List<ScanFilter> list, ScanSettings scanSettings) throws BleException, BluetoothDisabledException {
        if (this.mBtAdapter.isEnabled()) {
            if (this.mBleScanner != null) {
                throw new IllegalStateException("Only one Bluetooth LE scanner can be active at a time.");
            }
            boolean z = true;
            boolean z2 = list == null;
            if (scanSettings != null) {
                z = false;
            }
            if (z2 == z) {
                this.mBleScanner = BleScanner.startScanning(this.mBtAdapter, callback, list, scanSettings);
            } else {
                throw new IllegalArgumentException("filters and settings must either both be null or neither is null.");
            }
        } else {
            throw new BluetoothDisabledException();
        }
    }

    @TargetApi(21)
    public synchronized void stopAdvertising() {
        if (this.mBleAdvertiser != null) {
            this.mBleAdvertiser.stopAdvertising();
            this.mBleAdvertiser = null;
        } else {
            throw new IllegalStateException("Cannot stop Bluetooth LE advertisement as it does not exist.");
        }
    }

    public synchronized void stopScanning() {
        if (this.mBleScanner != null) {
            this.mBleScanner.stopScanning();
            this.mBleScanner = null;
        } else {
            throw new IllegalStateException("Cannot stop Bluetooth LE scanner as it does not exist.");
        }
    }

    public void unbindBleService(BleGattService bleGattService) {
        BleGattServer bleGattServer = this.mBleGattServer;
        if (bleGattServer != null) {
            bleGattServer.unbindBleService(bleGattService);
            return;
        }
        throw new IllegalStateException("Cannot remove bleService when Bluetooth LE GATT server does not exist.");
    }
}
