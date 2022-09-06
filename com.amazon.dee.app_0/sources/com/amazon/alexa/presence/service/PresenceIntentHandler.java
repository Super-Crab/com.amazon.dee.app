package com.amazon.alexa.presence.service;

import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import android.content.Intent;
import android.os.ParcelUuid;
import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.alexa.presence.Presence;
import com.amazon.alexa.presence.detection.BLEScannerCallback;
import com.amazon.alexa.presence.identity.IdentityHelper;
import com.amazon.alexa.presence.library.ContextHelper;
import com.amazon.alexa.presence.receiver.BeaconReceiver;
import com.amazon.alexa.presence.utils.BluetoothHelper;
import com.amazon.alexa.presence.utils.MetricsUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsServiceV2;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes9.dex */
public class PresenceIntentHandler {
    private static final int SCAN_PENDING_INTENT_CODE = 173;
    private static final String SERVICE_UUID_MASK = "FFFFFFFF-FFFF-FFFF-FFFF-FFFFFFFFFFFF";
    private final AtomicBoolean IS_SCANNING = new AtomicBoolean(false);
    @Inject
    BLEScannerCallback mBLEScannerCallback;
    @Inject
    @Nullable
    BluetoothAdapter mBluetoothAdapter;
    @Inject
    BluetoothHelper mBluetoothHelper;
    @Inject
    ContextHelper mContextHelper;
    @Inject
    IdentityHelper mIdentityHelper;
    @Inject
    MetricsServiceV2 mMetricsServiceV2;
    private static final String TAG = Presence.tag();
    private static final String SERVICE_UUID = String.format("0000%04X-0000-1000-8000-00805f9b34fb", 65045);

    @Inject
    public PresenceIntentHandler(@Nullable BluetoothAdapter bluetoothAdapter, MetricsServiceV2 metricsServiceV2, BLEScannerCallback bLEScannerCallback, IdentityHelper identityHelper, BluetoothHelper bluetoothHelper, ContextHelper contextHelper) {
        this.mBluetoothAdapter = bluetoothAdapter;
        this.mMetricsServiceV2 = metricsServiceV2;
        this.mBLEScannerCallback = bLEScannerCallback;
        this.mIdentityHelper = identityHelper;
        this.mBluetoothHelper = bluetoothHelper;
        this.mContextHelper = contextHelper;
    }

    private ScanSettings getScanSettings() {
        ScanSettings.Builder builder = new ScanSettings.Builder();
        this.mContextHelper.setScanSettings(builder);
        return builder.build();
    }

    private void handleActionStartScan() {
        if (isBluetoothAdapterAvailable(true) && isBluetoothAdapterEnabled(true) && isPermissionsGranted() && isUserAuthenticated() && hasAnyDomainRequestingPresence()) {
            BluetoothLeScanner bluetoothLeScanner = this.mBluetoothAdapter.getBluetoothLeScanner();
            if (!isBluetoothLeScannerAvailable(bluetoothLeScanner, true)) {
                Log.i(TAG, "cannot start scanning, stopping if was scanning.");
                handleActionStopScan(false);
                return;
            }
            startBleScanning(bluetoothLeScanner, new ScanFilter.Builder().setServiceUuid(ParcelUuid.fromString(SERVICE_UUID), ParcelUuid.fromString(SERVICE_UUID_MASK)).build());
            return;
        }
        Log.i(TAG, "cannot start scanning, stopping if was scanning.");
        handleActionStopScan(true);
    }

    private void handleActionStopScan(boolean z) {
        if (!isBluetoothAdapterAvailable(false)) {
            this.IS_SCANNING.set(false);
            Log.w(TAG, "no need to stop scanning, scan stopped already.");
            return;
        }
        BluetoothLeScanner bluetoothLeScanner = this.mBluetoothAdapter.getBluetoothLeScanner();
        if (!isBluetoothLeScannerAvailable(bluetoothLeScanner, false)) {
            this.IS_SCANNING.set(false);
            Log.w(TAG, "no scanner found, scan stopped already.");
        } else if (!z && this.mIdentityHelper.isUserAuthenticated() && this.mContextHelper.isAnyDomainRequestingV1Presence()) {
            Log.w(TAG, "Atleast one domain requests scanning and the user is authenticated. Cannot stop scanning.");
            MetricsUtil.recordCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.AUTH_USER_WITH_PRESENCE_REQUEST, MetricsUtil.Method.STOP_SCANNING_IMPL);
        } else {
            stopBleScanning(bluetoothLeScanner);
        }
    }

    private boolean hasAnyDomainRequestingPresence() {
        if (!this.mContextHelper.isAnyDomainRequestingV1Presence()) {
            Log.w(TAG, "No domain requests scanning. Not scanning.");
            MetricsUtil.recordCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.NO_DOMAIN_REQUESTING_PRESENCE, MetricsUtil.Method.START_SCANNING_IMPL);
            return false;
        }
        return true;
    }

    private boolean isBluetoothAdapterAvailable(boolean z) {
        if (this.mBluetoothAdapter == null) {
            String str = z ? MetricsUtil.Method.START_SCANNING_IMPL : MetricsUtil.Method.STOP_SCANNING_IMPL;
            String str2 = TAG;
            Log.w(str2, "No bluetooth adapter found, requested for scanning: " + z);
            MetricsUtil.recordCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.NO_BLUETOOTH_ADAPTER, str);
            return false;
        }
        GeneratedOutlineSupport1.outline172("BluetoothAdapter is available, requested for scanning: ", z);
        return true;
    }

    private boolean isBluetoothAdapterEnabled(boolean z) {
        if (isBluetoothAdapterAvailable(z) && this.mBluetoothAdapter.isEnabled()) {
            GeneratedOutlineSupport1.outline172("BluetoothAdapter is enabled, requested for scanning: ", z);
            return true;
        }
        String str = z ? MetricsUtil.Method.START_SCANNING_IMPL : MetricsUtil.Method.STOP_SCANNING_IMPL;
        String str2 = TAG;
        Log.w(str2, "Bluetooth is disabled, requested for scanning: " + z);
        MetricsUtil.recordCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.BLUETOOTH_DISABLED, str);
        return false;
    }

    private boolean isBluetoothLeScannerAvailable(BluetoothLeScanner bluetoothLeScanner, boolean z) {
        if (bluetoothLeScanner == null) {
            String str = z ? MetricsUtil.Method.START_SCANNING_IMPL : MetricsUtil.Method.STOP_SCANNING_IMPL;
            String str2 = TAG;
            Log.w(str2, "No scanner returned, requested for scanning: " + z);
            MetricsUtil.recordCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.NO_BLUETOOTH_SCANNER, str);
            return false;
        }
        return true;
    }

    private boolean isPermissionsGranted() {
        if (!this.mContextHelper.hasPermissions()) {
            Log.w(TAG, "No Permissions. Not scanning.");
            MetricsUtil.recordCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.NO_PERMISSIONS, MetricsUtil.Method.START_SCANNING_IMPL);
            return false;
        }
        return true;
    }

    private boolean isUserAuthenticated() {
        if (!this.mIdentityHelper.isUserAuthenticated()) {
            Log.w(TAG, "User not signed in. Not scanning.");
            MetricsUtil.recordCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.USER_UNAUTHENTICATED, MetricsUtil.Method.START_SCANNING_IMPL);
            return false;
        }
        return true;
    }

    private void startBleScanning(BluetoothLeScanner bluetoothLeScanner, ScanFilter scanFilter) {
        if (!this.IS_SCANNING.compareAndSet(false, true)) {
            Log.w(TAG, "Asked to start scanning, but already scanning. Doing nothing.");
            return;
        }
        ScanSettings scanSettings = getScanSettings();
        try {
            if (this.mContextHelper.isAndroidOreoOrLater()) {
                this.mBluetoothHelper.startScanMethodNew(bluetoothLeScanner, scanFilter, scanSettings, PendingIntent.getBroadcast(this.mContextHelper.getContext(), 173, new Intent(this.mContextHelper.getContext(), BeaconReceiver.class), 134217728));
            } else {
                this.mBluetoothHelper.startScanMethodOld(bluetoothLeScanner, scanFilter, scanSettings, this.mBLEScannerCallback);
            }
            Log.i(TAG, "Started scanning for beacons.");
            MetricsUtil.recordSuccess(this.mMetricsServiceV2, MetricsUtil.MetricsId.START_SCAN_SUCCESSFUL, MetricsUtil.Method.START_SCANNING_IMPL);
            MetricsUtil.recordCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.START_SCAN_SUCCESSFUL, MetricsUtil.Method.START_SCANNING_IMPL);
        } catch (Exception e) {
            Log.e(TAG, "Exception encountered while trying to scan for beacons. Not scanning.", e);
            MetricsUtil.recordFailure(this.mMetricsServiceV2, MetricsUtil.MetricsId.START_SCAN_FAILURE, MetricsUtil.Method.START_SCANNING_IMPL, "Exception encountered while trying to scan for beacons");
            MetricsUtil.recordCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.START_SCAN_FAILURE, MetricsUtil.Method.START_SCANNING_IMPL);
            this.IS_SCANNING.set(false);
        }
    }

    private void stopBleScanning(BluetoothLeScanner bluetoothLeScanner) {
        if (!this.IS_SCANNING.compareAndSet(true, false)) {
            Log.w(TAG, "Asked to stop scanning, but already not scanning. Doing nothing.");
            return;
        }
        try {
            if (this.mContextHelper.isAndroidOreoOrLater()) {
                this.mBluetoothHelper.stopScanMethodNew(bluetoothLeScanner, PendingIntent.getBroadcast(this.mContextHelper.getContext(), 173, new Intent(this.mContextHelper.getContext(), BeaconReceiver.class), 134217728));
            } else {
                this.mBluetoothHelper.stopScanMethodOld(bluetoothLeScanner, this.mBLEScannerCallback);
            }
            MetricsUtil.recordSuccess(this.mMetricsServiceV2, MetricsUtil.MetricsId.STOP_SCAN_SUCCESSFUL, MetricsUtil.Method.STOP_SCANNING_IMPL);
            MetricsUtil.recordCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.STOP_SCAN_SUCCESSFUL, MetricsUtil.Method.STOP_SCANNING_IMPL);
            Log.i(TAG, "Stopping scanning for beacons.");
        } catch (Exception e) {
            Log.e(TAG, "Exception encountered while trying to stop scan.", e);
            MetricsUtil.recordFailure(this.mMetricsServiceV2, MetricsUtil.MetricsId.STOP_SCAN_FAILURE, MetricsUtil.Method.STOP_SCANNING_IMPL, "Exception encountered while trying to stop scan");
            MetricsUtil.recordCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.STOP_SCAN_FAILURE, MetricsUtil.Method.STOP_SCANNING_IMPL);
            this.IS_SCANNING.set(true);
        }
    }

    public void handle(@NonNull Intent intent) {
        String action = intent.getAction();
        MetricsUtil.recordZeroCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.START_SCANNING, MetricsUtil.Method.START_SCANNING_WORKFLOW);
        MetricsUtil.recordZeroCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.STOP_SCANNING, MetricsUtil.Method.STOP_SCANNING_WORKFLOW);
        MetricsUtil.recordZeroCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.NO_BLUETOOTH_ADAPTER, MetricsUtil.Method.START_SCANNING_IMPL);
        MetricsUtil.recordZeroCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.BLUETOOTH_DISABLED, MetricsUtil.Method.START_SCANNING_IMPL);
        MetricsUtil.recordZeroCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.NO_PERMISSIONS, MetricsUtil.Method.START_SCANNING_IMPL);
        MetricsUtil.recordZeroCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.USER_UNAUTHENTICATED, MetricsUtil.Method.START_SCANNING_IMPL);
        MetricsUtil.recordZeroCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.NO_DOMAIN_REQUESTING_PRESENCE, MetricsUtil.Method.START_SCANNING_IMPL);
        MetricsUtil.recordZeroCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.NO_BLUETOOTH_SCANNER, MetricsUtil.Method.START_SCANNING_IMPL);
        MetricsUtil.recordZeroCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.NO_BLUETOOTH_ADAPTER, MetricsUtil.Method.STOP_SCANNING_IMPL);
        MetricsUtil.recordZeroCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.NO_BLUETOOTH_SCANNER, MetricsUtil.Method.STOP_SCANNING_IMPL);
        MetricsUtil.recordZeroCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.AUTH_USER_WITH_PRESENCE_REQUEST, MetricsUtil.Method.STOP_SCANNING_IMPL);
        MetricsUtil.recordZeroCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.START_SCAN_SUCCESSFUL, MetricsUtil.Method.START_SCANNING_IMPL);
        MetricsUtil.recordZeroCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.START_SCAN_FAILURE, MetricsUtil.Method.START_SCANNING_IMPL);
        MetricsUtil.recordZeroCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.STOP_SCAN_SUCCESSFUL, MetricsUtil.Method.START_SCANNING_IMPL);
        MetricsUtil.recordZeroCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.STOP_SCAN_FAILURE, MetricsUtil.Method.STOP_SCANNING_IMPL);
        if ("com.amazon.alexa.presence.action.startScan".equals(action)) {
            MetricsUtil.recordCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.START_SCANNING, MetricsUtil.Method.START_SCANNING_WORKFLOW);
            handleActionStartScan();
        } else if (!"com.amazon.alexa.presence.action.stopScan".equals(action)) {
        } else {
            MetricsUtil.recordCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.STOP_SCANNING, MetricsUtil.Method.STOP_SCANNING_WORKFLOW);
            handleActionStopScan(intent.getBooleanExtra("com.amazon.alexa.presence.stopScan.extra.forceStop", false));
        }
    }
}
