package com.amazon.alexa.accessory.internal.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelUuid;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.accessory.internal.PeripheralDevice;
import com.amazon.alexa.accessory.internal.bluetooth.DefaultLowEnergyScanner;
import com.amazon.alexa.accessory.internal.bluetooth.LowEnergyScanner;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
/* loaded from: classes.dex */
public final class DefaultLowEnergyScanner implements LowEnergyScanner {
    private final Map<LowEnergyScanner.Listener, LowEnergyScanAdapter> adapters;
    private final BluetoothManager bluetoothManager;
    private final Context context;
    private final Handler handler;
    private final BluetoothLeScannerDelegateExtractor leScannerDelegateExtractor;
    private final long scanDurationMillis;
    private final UUID uuid;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class BluetoothLeScannerDelegate {
        private final BluetoothLeScanner leScanner;

        BluetoothLeScannerDelegate(BluetoothLeScanner bluetoothLeScanner) {
            Preconditions.notNull(bluetoothLeScanner, "leScanner");
            this.leScanner = bluetoothLeScanner;
        }

        public void stopScan(ScanCallback scanCallback) {
            this.leScanner.stopScan(scanCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class BluetoothLeScannerDelegateExtractor {
        BluetoothLeScannerDelegateExtractor() {
        }

        public BluetoothLeScannerDelegate extract(BluetoothAdapter bluetoothAdapter) {
            BluetoothLeScanner bluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner();
            if (bluetoothLeScanner != null) {
                return new BluetoothLeScannerDelegate(bluetoothLeScanner);
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public final class LowEnergyScanAdapter extends ScanCallback implements Runnable {
        private final LowEnergyScanner.Listener listener;

        public LowEnergyScanAdapter(LowEnergyScanner.Listener listener) {
            this.listener = listener;
        }

        public /* synthetic */ void lambda$notifyCancelled$0$DefaultLowEnergyScanner$LowEnergyScanAdapter() {
            Logger.d("Scanning was cancelled");
            this.listener.onScanCancelled();
        }

        public /* synthetic */ void lambda$notifyCompleted$2$DefaultLowEnergyScanner$LowEnergyScanAdapter() {
            Logger.d("Scanning has completed");
            this.listener.onScanCompleted();
        }

        public /* synthetic */ void lambda$notifyFailed$3$DefaultLowEnergyScanner$LowEnergyScanAdapter(int i) {
            Logger.d("Scanning has failed with error code: %d", Integer.valueOf(i));
            this.listener.onScanFailed(i);
        }

        public /* synthetic */ void lambda$notifyFound$4$DefaultLowEnergyScanner$LowEnergyScanAdapter(PeripheralDevice peripheralDevice, BleAdvertisementData bleAdvertisementData, int i) {
            this.listener.onPeripheralFound(peripheralDevice, bleAdvertisementData, i);
        }

        public /* synthetic */ void lambda$notifyStarted$1$DefaultLowEnergyScanner$LowEnergyScanAdapter() {
            Logger.d("Scanning has started");
            this.listener.onScanStarted();
        }

        public void notifyCancelled() {
            DefaultLowEnergyScanner.this.handler.post(new Runnable() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultLowEnergyScanner$LowEnergyScanAdapter$ufS2Ss0nStXPBo-h-5zUBioXT8c
                @Override // java.lang.Runnable
                public final void run() {
                    DefaultLowEnergyScanner.LowEnergyScanAdapter.this.lambda$notifyCancelled$0$DefaultLowEnergyScanner$LowEnergyScanAdapter();
                }
            });
        }

        public void notifyCompleted() {
            DefaultLowEnergyScanner.this.handler.post(new Runnable() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultLowEnergyScanner$LowEnergyScanAdapter$aiIUoUa-ZaTzTfIhwRP_iaQ4CWk
                @Override // java.lang.Runnable
                public final void run() {
                    DefaultLowEnergyScanner.LowEnergyScanAdapter.this.lambda$notifyCompleted$2$DefaultLowEnergyScanner$LowEnergyScanAdapter();
                }
            });
        }

        public void notifyFailed(final int i) {
            DefaultLowEnergyScanner.this.handler.post(new Runnable() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultLowEnergyScanner$LowEnergyScanAdapter$1XXBvndkw4AQZcWrrqm3VNQ7sUk
                @Override // java.lang.Runnable
                public final void run() {
                    DefaultLowEnergyScanner.LowEnergyScanAdapter.this.lambda$notifyFailed$3$DefaultLowEnergyScanner$LowEnergyScanAdapter(i);
                }
            });
        }

        public void notifyFound(final PeripheralDevice peripheralDevice, final BleAdvertisementData bleAdvertisementData, final int i) {
            DefaultLowEnergyScanner.this.handler.post(new Runnable() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultLowEnergyScanner$LowEnergyScanAdapter$pn0Vr4JgXagQOEX94q5SVGvLIR4
                @Override // java.lang.Runnable
                public final void run() {
                    DefaultLowEnergyScanner.LowEnergyScanAdapter.this.lambda$notifyFound$4$DefaultLowEnergyScanner$LowEnergyScanAdapter(peripheralDevice, bleAdvertisementData, i);
                }
            });
        }

        public void notifyStarted() {
            DefaultLowEnergyScanner.this.handler.post(new Runnable() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultLowEnergyScanner$LowEnergyScanAdapter$86ms99O9yim36M0cMSwhSwM9y0Y
                @Override // java.lang.Runnable
                public final void run() {
                    DefaultLowEnergyScanner.LowEnergyScanAdapter.this.lambda$notifyStarted$1$DefaultLowEnergyScanner$LowEnergyScanAdapter();
                }
            });
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanFailed(int i) {
            int i2 = 1;
            if (i != 1) {
                i2 = 0;
            }
            if (i == 4) {
                i2 = 2;
            }
            if (i == 3 || i == 2) {
                i2 = 3;
            }
            notifyFailed(i2);
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int i, ScanResult scanResult) {
            BluetoothDevice device = scanResult.getDevice();
            ScanRecord scanRecord = scanResult.getScanRecord();
            byte[] bytes = scanRecord != null ? scanRecord.getBytes() : null;
            PeripheralDevice peripheralDevice = new PeripheralDevice(device.getAddress(), PeripheralDevice.Type.BLUETOOTH_LE, device.getName());
            Logger.v("Low-Energy Peripheral scanner found a peripheral: %s", peripheralDevice);
            BleAdvertisementData bleAdvertisementData = new BleAdvertisementData(bytes);
            if (Logger.shouldLog(Logger.Level.VERBOSE)) {
                Logger.v("    advertisement=%s", bleAdvertisementData.toString());
            }
            notifyFound(peripheralDevice, bleAdvertisementData, scanResult.getRssi());
        }

        @Override // java.lang.Runnable
        public void run() {
            DefaultLowEnergyScanner.this.stopScan(this.listener);
        }
    }

    public DefaultLowEnergyScanner(Context context, UUID uuid, long j) {
        this(context, uuid, j, new HashMap(), new Handler(Looper.getMainLooper()), new BluetoothLeScannerDelegateExtractor());
    }

    private List<ScanFilter> createScanFilters() {
        return Collections.singletonList(new ScanFilter.Builder().setServiceUuid(new ParcelUuid(this.uuid)).build());
    }

    private ScanSettings createScanSettings() {
        return new ScanSettings.Builder().setScanMode(2).build();
    }

    @Deprecated
    private BluetoothLeScanner getScanner() {
        BluetoothAdapter adapter = this.bluetoothManager.getAdapter();
        if (adapter == null) {
            Logger.w("Bluetooth is not available, make sure needed permissions are granted and bluetooth is enabled");
            return null;
        }
        BluetoothLeScanner bluetoothLeScanner = adapter.getBluetoothLeScanner();
        if (bluetoothLeScanner != null) {
            return bluetoothLeScanner;
        }
        Logger.w("Bluetooth LE is not available, this device may not support Bluetooth LE connectivity!");
        return null;
    }

    private BluetoothLeScannerDelegate getScannerDelegate() {
        BluetoothAdapter adapter = this.bluetoothManager.getAdapter();
        if (adapter == null) {
            Logger.w("Bluetooth is not available, make sure needed permissions are granted and bluetooth is enabled");
            return null;
        }
        BluetoothLeScannerDelegate extract = this.leScannerDelegateExtractor.extract(adapter);
        if (extract != null) {
            return extract;
        }
        Logger.w("Bluetooth LE is not available, this device may not support Bluetooth LE connectivity!");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void stopScan(LowEnergyScanner.Listener listener) {
        Preconditions.notNull(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        LowEnergyScanAdapter remove = this.adapters.remove(listener);
        if (remove == null) {
            return;
        }
        this.handler.removeCallbacks(remove);
        BluetoothLeScanner scanner = getScanner();
        if (scanner != null) {
            try {
                scanner.stopScan(remove);
            } catch (IllegalStateException e) {
                Logger.w("Got stuck in rare situation where BT state was ON before calling stop scan but the moment stopScan is called, BT is no more in ON state and hence the illegal state exception as: %s", e.getMessage());
            }
        }
        remove.notifyCompleted();
    }

    @Override // com.amazon.alexa.accessory.internal.bluetooth.LowEnergyScanner
    public synchronized void cancelScan(LowEnergyScanner.Listener listener) {
        Preconditions.notNull(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        LowEnergyScanAdapter remove = this.adapters.remove(listener);
        if (remove == null) {
            return;
        }
        this.handler.removeCallbacks(remove);
        BluetoothLeScannerDelegate scannerDelegate = getScannerDelegate();
        if (scannerDelegate != null) {
            try {
                scannerDelegate.stopScan(remove);
            } catch (IllegalStateException e) {
                Logger.w("Got stuck in rare situation where BT state was ON before calling stop scan but the moment stopScan is called, BT is no more in ON state and hence the illegal state exception as: %s", e.getMessage());
            }
        }
        remove.notifyCancelled();
    }

    @Override // com.amazon.alexa.accessory.internal.bluetooth.LowEnergyScanner
    public synchronized boolean isScanning(LowEnergyScanner.Listener listener) {
        Preconditions.notNull(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        return this.adapters.containsKey(listener);
    }

    @Override // com.amazon.alexa.accessory.internal.bluetooth.LowEnergyScanner
    public synchronized boolean startScan(LowEnergyScanner.Listener listener) {
        Preconditions.notNull(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        if (this.adapters.containsKey(listener)) {
            return true;
        }
        int i = Build.VERSION.SDK_INT;
        if (ContextCompat.checkSelfPermission(this.context, "android.permission.ACCESS_FINE_LOCATION") != 0 && ContextCompat.checkSelfPermission(this.context, "android.permission.ACCESS_COARSE_LOCATION") != 0) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 31 && ContextCompat.checkSelfPermission(this.context, "android.permission.BLUETOOTH_SCAN") != 0) {
            return false;
        }
        BluetoothLeScanner scanner = getScanner();
        if (scanner == null) {
            return false;
        }
        LowEnergyScanAdapter lowEnergyScanAdapter = new LowEnergyScanAdapter(listener);
        this.adapters.put(listener, lowEnergyScanAdapter);
        lowEnergyScanAdapter.notifyStarted();
        scanner.startScan(createScanFilters(), createScanSettings(), lowEnergyScanAdapter);
        this.handler.postDelayed(lowEnergyScanAdapter, this.scanDurationMillis);
        return true;
    }

    @VisibleForTesting
    DefaultLowEnergyScanner(Context context, UUID uuid, long j, Map<LowEnergyScanner.Listener, LowEnergyScanAdapter> map, Handler handler, BluetoothLeScannerDelegateExtractor bluetoothLeScannerDelegateExtractor) {
        Preconditions.notNull(context, "context");
        Preconditions.notNull(uuid, "uuid");
        Preconditions.notNull(map, "adapters");
        Preconditions.notNull(handler, "handler");
        Preconditions.notNull(bluetoothLeScannerDelegateExtractor, "leScannerDelegateExtractor");
        this.scanDurationMillis = j;
        this.bluetoothManager = (BluetoothManager) context.getSystemService("bluetooth");
        this.adapters = map;
        this.handler = handler;
        this.uuid = uuid;
        this.context = context;
        this.leScannerDelegateExtractor = bluetoothLeScannerDelegateExtractor;
    }
}
