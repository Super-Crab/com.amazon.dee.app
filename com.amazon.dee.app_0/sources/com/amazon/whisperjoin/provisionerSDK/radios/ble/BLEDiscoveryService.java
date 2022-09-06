package com.amazon.whisperjoin.provisionerSDK.radios.ble;

import android.annotation.TargetApi;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.PowerManager;
import com.amazon.whisperbridge.ble.BleException;
import com.amazon.whisperbridge.ble.BleManager;
import com.amazon.whisperbridge.ble.BleScanner;
import com.amazon.whisperbridge.ble.BluetoothDisabledException;
import com.amazon.whisperjoin.common.sharedtypes.ble.BleScanData;
import com.amazon.whisperjoin.common.sharedtypes.devices.AbstractPeripheralDeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.devices.DeviceFilter;
import com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.RadioNotEnabledException;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.ScanException;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.events.DiscoveryEvent;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.events.ProvisioningEvent;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.events.data.ExceptionData;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.events.data.PeripheralDiscoveredEventData;
import com.amazon.whisperjoin.common.sharedtypes.radios.DiscoveryService;
import com.amazon.whisperjoin.common.sharedtypes.radios.ScanningMode;
import com.amazon.whisperjoin.common.sharedtypes.radios.SupportedRadios;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.provisionerSDK.configuration.DiscoveryConfiguration;
import com.amazon.whisperjoin.provisionerSDK.radios.ble.OveractiveBleActivityDetector;
import com.amazon.whisperjoin.util.FireOSUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.ref.WeakReference;
import java.util.List;
/* loaded from: classes13.dex */
public class BLEDiscoveryService implements DiscoveryService {
    private static final String TAG = "com.amazon.whisperjoin.provisionerSDK.radios.ble.BLEDiscoveryService";
    BleScanner.Callback mBleCallback;
    private final BleManager mBleManager;
    private final BroadcastReceiver mBluetoothAdapterChangedReceiver;
    DiscoveryService.BleScanDataCoalescer mCoalesceDevicesCallback;
    private final Context mContext;
    private final DiscoveryConfiguration mDiscoveryConfiguration;
    private final FireOSUtil mFireOSUtil;
    boolean mIsPowered;
    private final OveractiveBleActivityDetector mOveractiveBleActivityDetector;
    private final PowerConnectionReceiver mPowerConnectionReceiver;
    private final PowerManager mPowerManager;
    private final PowerManagerReceiver mPowerManagerReceiver;
    private ScanningMode mScanningMode;
    private boolean mScanningSuppressed;

    /* renamed from: com.amazon.whisperjoin.provisionerSDK.radios.ble.BLEDiscoveryService$2  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$radios$DiscoveryService$Event = new int[DiscoveryService.Event.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$radios$ScanningMode;

        static {
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$radios$DiscoveryService$Event[DiscoveryService.Event.WJ_DEVICE_ADDED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$radios$DiscoveryService$Event[DiscoveryService.Event.WJ_DEVICE_UPDATED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$radios$DiscoveryService$Event[DiscoveryService.Event.THIRD_PARTY_ADDED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$radios$DiscoveryService$Event[DiscoveryService.Event.THIRD_PARTY_UPDATED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$radios$ScanningMode = new int[ScanningMode.values().length];
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$radios$ScanningMode[ScanningMode.LOW_POWER_HIGH_LATENCY.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$radios$ScanningMode[ScanningMode.HIGH_POWER_LOW_LATENCY.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$radios$ScanningMode[ScanningMode.BALANCED.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* loaded from: classes13.dex */
    private static class BluetoothAdapterChangedReceiver extends BroadcastReceiver {
        private final WeakReference<BLEDiscoveryService> mBleDiscoveryServiceReference;

        public BluetoothAdapterChangedReceiver(BLEDiscoveryService bLEDiscoveryService) {
            this.mBleDiscoveryServiceReference = new WeakReference<>(bLEDiscoveryService);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            BLEDiscoveryService bLEDiscoveryService = this.mBleDiscoveryServiceReference.get();
            if (bLEDiscoveryService != null && "android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction())) {
                int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
                switch (intExtra) {
                    case 10:
                        WJLog.d(BLEDiscoveryService.TAG, "BLE off");
                        if (!bLEDiscoveryService.mBleManager.isScanning()) {
                            return;
                        }
                        try {
                            bLEDiscoveryService.mBleManager.stopScanning();
                            return;
                        } catch (IllegalStateException e) {
                            String str = BLEDiscoveryService.TAG;
                            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error stopping scanning: ");
                            outline107.append(e.getMessage());
                            WJLog.e(str, outline107.toString());
                            return;
                        }
                    case 11:
                        WJLog.d(BLEDiscoveryService.TAG, "BLE turning on");
                        return;
                    case 12:
                        WJLog.d(BLEDiscoveryService.TAG, "BLE on");
                        try {
                            bLEDiscoveryService.startScan();
                            return;
                        } catch (RadioNotEnabledException e2) {
                            String str2 = BLEDiscoveryService.TAG;
                            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Radio not enabled exception: ");
                            outline1072.append(e2.getMessage());
                            WJLog.e(str2, outline1072.toString());
                            bLEDiscoveryService.mDiscoveryConfiguration.getObservers().invoke(this, new ProvisioningEvent<>(DiscoveryEvent.ERROR, new ExceptionData(null, new RadioNotEnabledException(GeneratedOutlineSupport1.outline49("BLE Radio Not Enabled Error ", intExtra)))));
                            return;
                        } catch (ScanException e3) {
                            String str3 = BLEDiscoveryService.TAG;
                            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Scan exception: ");
                            outline1073.append(e3.getMessage());
                            WJLog.e(str3, outline1073.toString());
                            bLEDiscoveryService.mDiscoveryConfiguration.getObservers().invoke(this, new ProvisioningEvent<>(DiscoveryEvent.ERROR, new ExceptionData(null, new ScanException(GeneratedOutlineSupport1.outline49("Scan Error ", intExtra)))));
                            return;
                        }
                    case 13:
                        WJLog.d(BLEDiscoveryService.TAG, "BLE turning off");
                        return;
                    default:
                        WJLog.e(BLEDiscoveryService.TAG, "Bluetooth adapter state changed: unknown state");
                        return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class PowerConnectionReceiver extends BroadcastReceiver {
        private final WeakReference<BLEDiscoveryService> mBleDiscoveryService;

        public PowerConnectionReceiver(BLEDiscoveryService bLEDiscoveryService, Context context) {
            this.mBleDiscoveryService = new WeakReference<>(bLEDiscoveryService);
            bLEDiscoveryService.updateScanForPowerChange(context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED")));
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (this.mBleDiscoveryService.get() != null) {
                this.mBleDiscoveryService.get().updateScanForPowerChange(intent);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class PowerManagerReceiver extends BroadcastReceiver {
        private final WeakReference<BLEDiscoveryService> bleDiscoveryServiceWeakReference;

        public PowerManagerReceiver(BLEDiscoveryService bLEDiscoveryService) {
            this.bleDiscoveryServiceWeakReference = new WeakReference<>(bLEDiscoveryService);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (this.bleDiscoveryServiceWeakReference.get() != null) {
                this.bleDiscoveryServiceWeakReference.get().onPowerIntent();
            }
        }
    }

    public BLEDiscoveryService(DiscoveryConfiguration discoveryConfiguration, BleManager bleManager) {
        if (discoveryConfiguration != null) {
            this.mDiscoveryConfiguration = discoveryConfiguration;
            if (bleManager != null) {
                this.mBluetoothAdapterChangedReceiver = new BluetoothAdapterChangedReceiver(this);
                this.mContext = this.mDiscoveryConfiguration.getContext();
                this.mBleManager = bleManager;
                this.mCoalesceDevicesCallback = null;
                this.mBleCallback = null;
                this.mScanningMode = ScanningMode.LOW_POWER_HIGH_LATENCY;
                Context context = this.mContext;
                if (context != null) {
                    this.mScanningSuppressed = false;
                    this.mPowerConnectionReceiver = new PowerConnectionReceiver(this, context);
                    this.mPowerManager = (PowerManager) this.mContext.getSystemService("power");
                    this.mPowerManagerReceiver = new PowerManagerReceiver(this);
                    initPowerConnectionIntentReceiver();
                    initPowerManagerIntentReceiver();
                    this.mFireOSUtil = new FireOSUtil(this.mContext);
                    this.mOveractiveBleActivityDetector = new OveractiveBleActivityDetector(discoveryConfiguration.getOveractiveConfiguration());
                    return;
                }
                throw new IllegalArgumentException("discoveryConfiguration must have a non-null context");
            }
            throw new IllegalArgumentException("bleManager can not be null");
        }
        throw new IllegalArgumentException("discoveryConfiguration can not be null");
    }

    private void checkForBleOveractivity() {
        if (this.mFireOSUtil.isPoweredDevice()) {
            return;
        }
        try {
            this.mOveractiveBleActivityDetector.onNewDiscoveryActivity();
        } catch (OveractiveBleActivityDetector.OveractivityDetected e) {
            onErrorEvent(e);
        }
    }

    @TargetApi(21)
    private List<ScanFilter> getBLEScanFilters() {
        return this.mDiscoveryConfiguration.getScanFilters();
    }

    @TargetApi(21)
    private ScanSettings getScanSetting(ScanningMode scanningMode) {
        ScanSettings.Builder builder = new ScanSettings.Builder();
        String str = TAG;
        WJLog.d(str, "Getting Android scan settings for provided ScanningMode: " + scanningMode);
        int ordinal = scanningMode.ordinal();
        if (ordinal == 1) {
            builder.setScanMode(0);
        } else if (ordinal == 2) {
            builder.setScanMode(1);
        } else if (ordinal == 3) {
            builder.setScanMode(2);
        } else {
            throw new IllegalArgumentException("Scanning Mode Not Supported " + scanningMode);
        }
        return builder.build();
    }

    private void initPowerConnectionIntentReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        this.mContext.registerReceiver(this.mPowerConnectionReceiver, intentFilter);
    }

    private void initPowerManagerIntentReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.os.action.DEVICE_IDLE_MODE_CHANGED");
        intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
        this.mContext.registerReceiver(this.mPowerManagerReceiver, intentFilter);
    }

    private synchronized void initalizeBle() throws ScanException {
        if (!this.mBleManager.isInitialized()) {
            try {
                this.mBleManager.initializeBluetoothLE(this.mContext, null);
            } catch (BleException unused) {
                throw new ScanException("error initializing ble manager");
            }
        }
    }

    private boolean isAtLeastAPI21() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    private boolean isAtLeastAPI23() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    @TargetApi(21)
    private boolean isLowPowerMode() {
        if (this.mScanningMode.equals(ScanningMode.HIGH_POWER_LOW_LATENCY)) {
            WJLog.d(TAG, "High Power Scan Mode, ignoring device power status");
            return false;
        }
        boolean isPowerSaveMode = this.mPowerManager.isPowerSaveMode();
        return isAtLeastAPI23() ? isPowerSaveMode | this.mPowerManager.isDeviceIdleMode() : isPowerSaveMode;
    }

    private void onErrorEvent(Exception exc) {
        this.mDiscoveryConfiguration.getObservers().invoke(this, new ProvisioningEvent<>(DiscoveryEvent.ERROR, new ExceptionData(null, exc)));
    }

    private boolean shouldFilterDiscoveryResult(DeviceFilter deviceFilter, DiscoveryService.DiscoveryResult discoveryResult) {
        if (discoveryResult.getEvent().equals(DiscoveryService.Event.WJ_DEVICE_ADDED) || discoveryResult.getEvent().equals(DiscoveryService.Event.WJ_DEVICE_UPDATED)) {
            WhisperJoinPeripheralDeviceDetails whisperJoinPeripheralDeviceDetails = (WhisperJoinPeripheralDeviceDetails) discoveryResult.getDevice();
            boolean isDistressed = whisperJoinPeripheralDeviceDetails.isDistressed();
            if ((!isDistressed || deviceFilter.getBeaconType() != DeviceFilter.BeaconType.OOBE) && (isDistressed || deviceFilter.getBeaconType() != DeviceFilter.BeaconType.DISTRESS)) {
                return (deviceFilter == null || deviceFilter.filter(whisperJoinPeripheralDeviceDetails) == DeviceFilter.FilterResult.ACCEPT) ? false : true;
            }
            String str = TAG;
            Object[] objArr = new Object[4];
            objArr[0] = deviceFilter.getBeaconType().toString();
            objArr[1] = whisperJoinPeripheralDeviceDetails.getFriendlyName();
            objArr[2] = whisperJoinPeripheralDeviceDetails.getProductIndex();
            objArr[3] = whisperJoinPeripheralDeviceDetails.isDistressed() ? "DISTRESS beacon" : "OOBE beacon";
            WJLog.d(str, String.format("Only %s mode is supported for beacons, found %s (%s) which is a %s", objArr));
            return true;
        }
        return false;
    }

    void onEvent(AbstractPeripheralDeviceDetails abstractPeripheralDeviceDetails, DiscoveryEvent discoveryEvent) {
        this.mDiscoveryConfiguration.getObservers().invoke(this, new ProvisioningEvent<>(discoveryEvent, new PeripheralDiscoveredEventData(abstractPeripheralDeviceDetails)));
    }

    void onPowerIntent() {
        if (this.mScanningSuppressed && !isLowPowerMode()) {
            try {
                WJLog.d(TAG, "Restarting scan now that not in low power mode");
                startScan();
                this.mScanningSuppressed = false;
            } catch (Exception e) {
                WJLog.e(TAG, "Error while trying to start scanning due to power mode change", e);
            }
            WJLog.v(TAG, "started scan after being suppressed");
        } else if (!this.mBleManager.isScanning() || !isLowPowerMode()) {
        } else {
            WJLog.d(TAG, "Was scanning but now in lower power mode, stopping scan");
            this.mBleManager.stopScanning();
            this.mScanningSuppressed = true;
            WJLog.v(TAG, "suppressing scan due to power mode");
        }
    }

    void processScanData(BleScanData bleScanData, DeviceFilter deviceFilter, DiscoveryService.BleScanDataCoalescer bleScanDataCoalescer) {
        DiscoveryService.DiscoveryResult coalesce = bleScanDataCoalescer.coalesce(bleScanData);
        if (coalesce == null) {
            WJLog.d(TAG, "Discovery result returned null");
            return;
        }
        synchronized (this) {
            if (shouldFilterDiscoveryResult(deviceFilter, coalesce)) {
                WJLog.d(TAG, "Filtered discovery result");
                return;
            }
            checkForBleOveractivity();
            int ordinal = coalesce.getEvent().ordinal();
            if (ordinal == 0) {
                onEvent(coalesce.getDevice(), DiscoveryEvent.WJ_DEVICE_DISCOVERED);
            } else if (ordinal == 1) {
                onEvent(coalesce.getDevice(), DiscoveryEvent.WJ_DEVICE_UPDATED_RADIO);
            } else if (ordinal == 2) {
                onEvent(coalesce.getDevice(), DiscoveryEvent.THIRD_PARTY_DEVICE_DISCOVERED);
            } else if (ordinal == 3) {
                onEvent(coalesce.getDevice(), DiscoveryEvent.THIRD_PARTY_DEVICE_UPDATED_RADIO);
            } else {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unknown discovery event value: ");
                outline107.append(coalesce.getEvent());
                throw new IllegalArgumentException(outline107.toString());
            }
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.radios.DiscoveryService
    public void start(final DeviceFilter deviceFilter, final DiscoveryService.BleScanDataCoalescer bleScanDataCoalescer, ScanningMode scanningMode) throws RadioNotEnabledException, ScanException {
        if (isAtLeastAPI21()) {
            if (scanningMode != null) {
                WJLog.d(TAG, "Register receivers for bluetooth and power");
                this.mContext.registerReceiver(this.mBluetoothAdapterChangedReceiver, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
                initPowerConnectionIntentReceiver();
                initPowerManagerIntentReceiver();
                synchronized (this) {
                    initalizeBle();
                    this.mCoalesceDevicesCallback = bleScanDataCoalescer;
                    this.mBleCallback = new BleScanner.Callback() { // from class: com.amazon.whisperjoin.provisionerSDK.radios.ble.BLEDiscoveryService.1
                        @Override // com.amazon.whisperbridge.ble.BleScanner.Callback
                        public void onScanData(BleScanData bleScanData) {
                            synchronized (BLEDiscoveryService.this) {
                                synchronized (BLEDiscoveryService.this.mBleManager) {
                                    if (!BLEDiscoveryService.this.mBleManager.isScanning()) {
                                        WJLog.w(BLEDiscoveryService.TAG, "New Scan Data available but not currently scanning.");
                                    } else {
                                        this.processScanData(bleScanData, deviceFilter, bleScanDataCoalescer);
                                    }
                                }
                            }
                        }

                        @Override // com.amazon.whisperbridge.ble.BleScanner.Callback
                        public void onScanError(BleScanner.ScanError scanError) {
                            BLEDiscoveryService.this.mDiscoveryConfiguration.getObservers().invoke(this, new ProvisioningEvent<>(DiscoveryEvent.ERROR, new ExceptionData(null, new RuntimeException("scanning error : " + scanError))));
                        }
                    };
                    this.mScanningMode = scanningMode;
                    startScan();
                }
                return;
            }
            throw new IllegalArgumentException("scanningMode can not be null");
        }
        throw new ScanException("API < 21, BLE Scanning not supported");
    }

    synchronized void startScan() throws ScanException, RadioNotEnabledException {
        if (this.mBleCallback != null) {
            try {
                if (this.mBleManager.isScanning()) {
                    WJLog.d(TAG, "Start scan called while already scanning. Stopping scanning before restarting it.");
                    this.mBleManager.stopScanning();
                }
                if (!isLowPowerMode()) {
                    this.mBleManager.startScanning(this.mBleCallback, getBLEScanFilters(), getScanSetting(this.mScanningMode));
                } else {
                    WJLog.d(TAG, "Start scan called but we are in low power mode so scan is suppresed until we are out of lower power mode");
                    this.mScanningSuppressed = true;
                }
            } catch (BleException e) {
                throw new ScanException("error starting scanning", e);
            } catch (BluetoothDisabledException e2) {
                throw new RadioNotEnabledException(SupportedRadios.BLE.getString(), e2);
            }
        } else {
            throw new IllegalStateException("mBleCallback can not be null when calling startScan");
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.radios.DiscoveryService
    public void stop() {
        synchronized (this) {
            if (this.mBleManager.isScanning()) {
                WJLog.d(TAG, "Stop Scanning");
                try {
                    this.mBleManager.stopScanning();
                } catch (IllegalStateException e) {
                    String str = TAG;
                    WJLog.e(str, "Error stopping scanning: " + e.getMessage());
                }
            }
            WJLog.d(TAG, "Unregistering receivers");
            this.mContext.unregisterReceiver(this.mBluetoothAdapterChangedReceiver);
            try {
                this.mContext.unregisterReceiver(this.mPowerConnectionReceiver);
                this.mContext.unregisterReceiver(this.mPowerManagerReceiver);
            } catch (IllegalArgumentException e2) {
                WJLog.d(TAG, "Could not unregister one or more power receivers. Probably it was already unregistered.", e2);
            }
            this.mCoalesceDevicesCallback = null;
            this.mBleCallback = null;
        }
    }

    void updateScanForPowerChange(Intent intent) {
        boolean z = false;
        if (intent == null) {
            this.mIsPowered = false;
            return;
        }
        int intExtra = intent.getIntExtra("plugged", -1);
        boolean z2 = intExtra == 2;
        boolean z3 = intExtra == 1;
        if (z2 || z3) {
            z = true;
        }
        if (this.mIsPowered != z) {
            this.mIsPowered = z;
            if (this.mBleManager.isScanning()) {
                try {
                    WJLog.d(TAG, "Restarting scan after power change");
                    startScan();
                } catch (Exception e) {
                    WJLog.e(TAG, "could not restart scanning after change in power status", e);
                }
            }
        }
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("is powered = ");
        outline107.append(this.mIsPowered);
        WJLog.v(str, outline107.toString());
    }
}
