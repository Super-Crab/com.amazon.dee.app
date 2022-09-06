package com.amazon.whisperbridge.ble;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.os.Build;
import com.amazon.whisperjoin.common.sharedtypes.ble.BleScanData;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.ref.WeakReference;
import java.util.List;
/* loaded from: classes13.dex */
public class BleScanner {
    private static final String TAG = "BleScanner";
    ScanCallback mBluetootLeScanCallback;
    BluetoothLeScanner mBluetoothLeScanner;
    final BluetoothAdapter mBtAdapter;
    final Callback mCallback;
    BluetoothAdapter.LeScanCallback mLegacyScanCallback;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static final class BLEScanCallback extends ScanCallback {
        private final WeakReference<BleScanner> mBleScannerWeakReference;
        private final WeakReference<Callback> mCallbackWeakReference;

        public BLEScanCallback(BleScanner bleScanner, Callback callback) {
            this.mBleScannerWeakReference = new WeakReference<>(bleScanner);
            this.mCallbackWeakReference = new WeakReference<>(callback);
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onBatchScanResults(List<ScanResult> list) {
            for (ScanResult scanResult : list) {
                if (this.mBleScannerWeakReference.get() != null) {
                    this.mBleScannerWeakReference.get().addScanResult(scanResult);
                }
            }
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanFailed(int i) {
            if (this.mCallbackWeakReference.get() != null) {
                this.mCallbackWeakReference.get().onScanError(ScanError.fromInt(i));
            }
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int i, ScanResult scanResult) {
            if (i != 1 && i != 2) {
                throw new IllegalArgumentException("Unknown callback-type encountered in onScanResult");
            }
            if (this.mBleScannerWeakReference.get() == null) {
                return;
            }
            this.mBleScannerWeakReference.get().addScanResult(scanResult);
        }
    }

    /* loaded from: classes13.dex */
    public interface Callback {
        void onScanData(BleScanData bleScanData);

        void onScanError(ScanError scanError);
    }

    /* loaded from: classes13.dex */
    public enum ScanError {
        SCAN_FAILED_ALREADY_STARTED(1, "Scan failed, already started"),
        SCAN_FAILED_APPLICATION_REGISTRATION_FAILED(2, "Scan failed, application registration failed"),
        SCAN_FAILED_INTERNAL_ERROR(3, "Scan failed, internal error"),
        SCAN_FAILED_FEATURE_UNSUPPORTED(4, "Scan failed, feature unsupported"),
        SCAN_FAILED_OUT_OF_HARDWARE_RESOURCES(5, "Scan failed, out of hardware resources"),
        SCAN_FAILED_SCANNING_TOO_FREQUENTLY(6, "Scan failed, scanning too frequently");
        
        private String mString;
        private int mValue;

        ScanError(int i, String str) {
            this.mValue = i;
            this.mString = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static ScanError fromInt(int i) {
            switch (i) {
                case 1:
                    return SCAN_FAILED_ALREADY_STARTED;
                case 2:
                    return SCAN_FAILED_APPLICATION_REGISTRATION_FAILED;
                case 3:
                    return SCAN_FAILED_INTERNAL_ERROR;
                case 4:
                    return SCAN_FAILED_FEATURE_UNSUPPORTED;
                case 5:
                    return SCAN_FAILED_OUT_OF_HARDWARE_RESOURCES;
                case 6:
                    return SCAN_FAILED_SCANNING_TOO_FREQUENTLY;
                default:
                    String str = BleScanner.TAG;
                    WJLog.w(str, "Unknown Scan ScanError encountered: " + i);
                    return SCAN_FAILED_INTERNAL_ERROR;
            }
        }

        public int getValue() {
            return this.mValue;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mString;
        }
    }

    private BleScanner(BluetoothAdapter bluetoothAdapter, Callback callback) {
        this.mBtAdapter = bluetoothAdapter;
        this.mCallback = callback;
    }

    @TargetApi(18)
    private static BleScanner createLegacyScanner(BluetoothAdapter bluetoothAdapter, Callback callback) {
        BleScanner bleScanner = new BleScanner(bluetoothAdapter, callback);
        bleScanner.mLegacyScanCallback = new BluetoothAdapter.LeScanCallback() { // from class: com.amazon.whisperbridge.ble.BleScanner.1
            @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
            public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
                BleScanner.this.notifyScanData(new BleScanData(bluetoothDevice, bArr, i));
            }
        };
        if (!bluetoothAdapter.startLeScan(bleScanner.mLegacyScanCallback)) {
            callback.onScanError(ScanError.SCAN_FAILED_INTERNAL_ERROR);
        }
        return bleScanner;
    }

    @TargetApi(21)
    private static BleScanner createScanner(BluetoothAdapter bluetoothAdapter, Callback callback, List<ScanFilter> list, ScanSettings scanSettings) throws BleException {
        WJLog.d(TAG, "Create Scanner");
        BleScanner bleScanner = new BleScanner(bluetoothAdapter, callback);
        bleScanner.mBluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner();
        if (bleScanner.mBluetoothLeScanner != null) {
            bleScanner.mBluetootLeScanCallback = new BLEScanCallback(bleScanner, callback);
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Set Scanner Callback ");
            outline107.append(bleScanner.mBluetoothLeScanner);
            WJLog.d(str, outline107.toString());
            try {
                if (list == null) {
                    bleScanner.mBluetoothLeScanner.startScan(bleScanner.mBluetootLeScanCallback);
                } else {
                    bleScanner.mBluetoothLeScanner.startScan(list, scanSettings, bleScanner.mBluetootLeScanCallback);
                }
                return bleScanner;
            } catch (SecurityException e) {
                throw new BleException("Catching Android 12 SecurityException crash issue", e);
            }
        }
        throw new BleException("Failed to get Bluetooth LE scannder. Bluetooth may be disabled or Bluetooth LE advertising is not supported.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BleScanner startScanning(BluetoothAdapter bluetoothAdapter, Callback callback, List<ScanFilter> list, ScanSettings scanSettings) throws BleException {
        if (bluetoothAdapter != null) {
            if (callback == null) {
                throw new IllegalArgumentException("callback unexpectedly null.");
            }
            boolean z = true;
            boolean z2 = list == null;
            if (scanSettings != null) {
                z = false;
            }
            if (z2 == z) {
                int i = Build.VERSION.SDK_INT;
                WJLog.i(TAG, "Starting Bluetooth LE scanning.");
                int i2 = Build.VERSION.SDK_INT;
                return createScanner(bluetoothAdapter, callback, list, scanSettings);
            }
            throw new IllegalArgumentException("settings unexpectedly null.");
        }
        throw new IllegalArgumentException("btAdapter unexpectedly null.");
    }

    @TargetApi(21)
    synchronized void addScanResult(ScanResult scanResult) {
        ScanRecord scanRecord = scanResult.getScanRecord();
        notifyScanData(new BleScanData(scanResult.getDevice(), scanRecord == null ? new byte[0] : scanRecord.getBytes(), scanResult.getRssi()));
    }

    synchronized void notifyScanData(BleScanData bleScanData) {
        this.mCallback.onScanData(bleScanData);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void stopScanning() {
        int i = Build.VERSION.SDK_INT;
        this.mBluetoothLeScanner.stopScan(this.mBluetootLeScanCallback);
    }
}
