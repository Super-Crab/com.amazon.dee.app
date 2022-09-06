package com.amazon.alexa.presence.utils;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import android.util.Log;
import androidx.annotation.RequiresApi;
import com.amazon.alexa.presence.Presence;
import com.amazon.alexa.presence.detection.BLEScannerCallback;
import com.amazon.alexa.presence.library.Compatibility;
import java.util.Collections;
import java.util.concurrent.Callable;
/* loaded from: classes9.dex */
public class BluetoothHelper {
    private static final String TAG = Presence.tag();

    @RequiresApi(26)
    private void startIntentBasedScanning(BluetoothLeScanner bluetoothLeScanner, ScanFilter scanFilter, ScanSettings scanSettings, PendingIntent pendingIntent) {
        bluetoothLeScanner.startScan(Collections.singletonList(scanFilter), scanSettings, pendingIntent);
    }

    public /* synthetic */ Object lambda$startScanMethodNew$0$BluetoothHelper(BluetoothLeScanner bluetoothLeScanner, ScanFilter scanFilter, ScanSettings scanSettings, PendingIntent pendingIntent) throws Exception {
        startIntentBasedScanning(bluetoothLeScanner, scanFilter, scanSettings, pendingIntent);
        return null;
    }

    @SuppressLint({"NewApi"})
    public void startScanMethodNew(final BluetoothLeScanner bluetoothLeScanner, final ScanFilter scanFilter, final ScanSettings scanSettings, final PendingIntent pendingIntent) {
        Log.i(TAG, "Starting scanning for beacons using intent based implementation.");
        Compatibility.ifAndroidOOrLater(new Callable() { // from class: com.amazon.alexa.presence.utils.-$$Lambda$BluetoothHelper$YFv6ZcmXSo_P--2FaA3155cSzeo
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return BluetoothHelper.this.lambda$startScanMethodNew$0$BluetoothHelper(bluetoothLeScanner, scanFilter, scanSettings, pendingIntent);
            }
        });
    }

    public void startScanMethodOld(BluetoothLeScanner bluetoothLeScanner, ScanFilter scanFilter, ScanSettings scanSettings, ScanCallback scanCallback) {
        Log.i(TAG, "Starting scanning for beacons using callback based implementation.");
        bluetoothLeScanner.startScan(Collections.singletonList(scanFilter), scanSettings, scanCallback);
    }

    @SuppressLint({"NewApi"})
    public void stopScanMethodNew(final BluetoothLeScanner bluetoothLeScanner, final PendingIntent pendingIntent) {
        Compatibility.ifAndroidOOrLater(new Callable() { // from class: com.amazon.alexa.presence.utils.-$$Lambda$BluetoothHelper$xHtlYhfOjAItR-UUFP9SE_Mh3ss
            @Override // java.util.concurrent.Callable
            public final Object call() {
                bluetoothLeScanner.stopScan(pendingIntent);
                return null;
            }
        });
    }

    public void stopScanMethodOld(BluetoothLeScanner bluetoothLeScanner, BLEScannerCallback bLEScannerCallback) {
        bluetoothLeScanner.stopScan(bLEScannerCallback);
    }
}
