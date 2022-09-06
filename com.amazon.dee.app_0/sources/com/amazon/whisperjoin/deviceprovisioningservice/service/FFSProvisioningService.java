package com.amazon.whisperjoin.deviceprovisioningservice.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import com.amazon.whisperjoin.common.sharedtypes.configuration.OveractiveConfiguration;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.FFSArcusSyncCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.FFSArcusSyncResult;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.ArcusConstants;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.FFSArcusSettings;
import com.amazon.whisperjoin.deviceprovisioningservice.di.DaggerWrapper;
import com.amazon.whisperjoin.deviceprovisioningservice.metrics.FFSProvisioningServiceMetricsRecorder;
import com.amazon.whisperjoin.deviceprovisioningservice.service.power.DevicePowerMonitor;
import com.amazon.whisperjoin.deviceprovisioningservice.service.power.DevicePowerStatus;
import com.amazon.whisperjoin.deviceprovisioningservice.service.whitelist.WhiteListPolicy;
import com.amazon.whisperjoin.deviceprovisioningservice.service.whitelist.WhiteListPolicyCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.service.whitelist.WhiteListPolicyUpdateListener;
import com.amazon.whisperjoin.deviceprovisioningservice.util.LocationPermissionsHelper;
import com.amazon.whisperjoin.deviceprovisioningservice.util.SharedPreferencesProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.PhilipsZigbeeBleWorkflow;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.ProvisioningEventListener;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.WifiSimpleSetupZeroTouchWorkflow;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.WorkflowConfigurationFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioningMethod;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ControllerUpdate;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ZeroTouchWorkflowController;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ZeroTouchWorkflowFactory;
import com.amazon.whisperjoin.util.FireOSUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import java.lang.ref.WeakReference;
import java.util.Locale;
import javax.inject.Inject;
/* loaded from: classes13.dex */
public class FFSProvisioningService {
    private static final String TAG = "FFSProvisioningService";
    private ArcusBroadcastReceiver mArcusBroadcastReceiver;
    @Inject
    Context mContext;
    private Disposable mControllerDisposable;
    private boolean mDebugEnabled;
    @Inject
    DevicePowerMonitor mDevicePowerMonitor;
    private DevicePowerStatus mDevicePowerStatus;
    private FFSArcusSettings mFFSArcusSettings;
    @Inject
    FFSArcusSyncCoordinator mFFSArcusSyncCoordinator;
    @Inject
    FFSProvisioningServiceMetricsRecorder mFFSServiceMetricsRecorder;
    private FireOSUtil mFireOSUtil;
    @Inject
    LocationPermissionsHelper mLocationPermissionsHelper;
    @Inject
    ProvisionerClientData mProvisionerClientData;
    private DaggerWrapper.ProvisionerServices mProvisionerServices;
    private Disposable mProvisioningDisposable;
    private final ProvisioningEventListener mProvisioningEventListener;
    private boolean mServiceRunning;
    @Inject
    SharedPreferencesProvider mSharedPreferencesProvider;
    private WhiteListPolicy mWhiteListPolicy;
    @Inject
    WhiteListPolicyCoordinator mWhiteListPolicyCoordinator;
    @Inject
    WhiteListPolicyUpdateListener mWhiteListPolicyUpdateListener;
    private ZeroTouchWorkflowController mZeroTouchWorkflowController;
    private WhiteListPolicyUpdateListener.WhiteListPolicyUpdateCallback mWhiteListPolicyUpdateCallback = new WhiteListPolicyUpdateListener.WhiteListPolicyUpdateCallback() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.service.FFSProvisioningService.8
        @Override // com.amazon.whisperjoin.deviceprovisioningservice.service.whitelist.WhiteListPolicyUpdateListener.WhiteListPolicyUpdateCallback
        public void onUpdate(WhiteListPolicy whiteListPolicy, ProvisionerClientData provisionerClientData) {
            synchronized (FFSProvisioningService.this) {
                String str = FFSProvisioningService.TAG;
                WJLog.d(str, "WhiteListPolicy Update: " + whiteListPolicy);
                FFSProvisioningService.this.mWhiteListPolicy = whiteListPolicy;
                FFSProvisioningService.this.updateWorkflowActivityStateBasedOnConstraints(FFSProvisioningService.this.mWhiteListPolicy, FFSProvisioningService.this.mDevicePowerStatus);
            }
        }
    };
    private DevicePowerMonitor.DevicePowerUpdateListener mDevicePowerUpdateListener = new DevicePowerMonitor.DevicePowerUpdateListener() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.service.FFSProvisioningService.9
        @Override // com.amazon.whisperjoin.deviceprovisioningservice.service.power.DevicePowerMonitor.DevicePowerUpdateListener
        public void onUpdate(DevicePowerStatus devicePowerStatus) {
            synchronized (FFSProvisioningService.this) {
                String str = FFSProvisioningService.TAG;
                WJLog.d(str, "DevicePowerStatus Update: " + devicePowerStatus);
                FFSProvisioningService.this.mDevicePowerStatus = devicePowerStatus;
                FFSProvisioningService.this.updateWorkflowActivityStateBasedOnConstraints(FFSProvisioningService.this.mWhiteListPolicy, FFSProvisioningService.this.mDevicePowerStatus);
            }
        }
    };

    /* loaded from: classes13.dex */
    private static class ArcusBroadcastReceiver extends BroadcastReceiver {
        private final WeakReference<FFSProvisioningService> mService;

        public ArcusBroadcastReceiver(FFSProvisioningService fFSProvisioningService) {
            this.mService = new WeakReference<>(fFSProvisioningService);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (this.mService.get() == null) {
                return;
            }
            if (!ArcusConstants.ARCUS_UPDATE_INTENT_ACTION.equals(intent.getAction())) {
                WJLog.w(FFSProvisioningService.TAG, "Received unknown intent");
                return;
            }
            WJLog.d(FFSProvisioningService.TAG, "Received broadcast intent for updated arcus configs");
            Bundle extras = intent.getExtras();
            if (extras == null) {
                WJLog.d(FFSProvisioningService.TAG, "Bundle is null");
                return;
            }
            synchronized (this.mService.get()) {
                this.mService.get().mFFSArcusSettings = FFSArcusSettings.readFromBundle(extras);
                this.mService.get().restartWorkflowController();
            }
        }
    }

    public FFSProvisioningService(ProvisioningServiceConfiguration provisioningServiceConfiguration, ProvisioningEventListener provisioningEventListener) {
        if (provisioningServiceConfiguration != null) {
            if (provisioningEventListener != null) {
                this.mProvisionerServices = new DaggerWrapper.ProvisionerServices(DaggerWrapper.getBaseComponent(), provisioningServiceConfiguration, WorkflowConfigurationFactory.createWorkflowConfigurationForAllDevices(provisioningServiceConfiguration.getProvisionableBeaconType()), ProvisioningMethod.FFS);
                this.mProvisionerServices.getDependencyInjector().inject(this);
                this.mProvisioningEventListener = provisioningEventListener;
                this.mFireOSUtil = new FireOSUtil(this.mContext);
                this.mDebugEnabled = provisioningServiceConfiguration.isDebugEnabled();
                persistProvisioningServiceConfiguration(provisioningServiceConfiguration);
                this.mFFSArcusSyncCoordinator.initializeWithProvisionerClientData(this.mProvisionerClientData);
                return;
            }
            throw new IllegalArgumentException("eventListener can not be null");
        }
        throw new IllegalArgumentException("provisioningServiceConfiguration can not be null");
    }

    private Action cleanupWorkflow() {
        return new Action() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.service.FFSProvisioningService.7
            @Override // io.reactivex.rxjava3.functions.Action
            public void run() throws Exception {
                WJLog.i(FFSProvisioningService.TAG, "Cleanup");
                FFSProvisioningService.this.mDevicePowerMonitor.onWorkflowActivationStateChange(false);
                if (FFSProvisioningService.this.mProvisioningDisposable != null) {
                    FFSProvisioningService.this.mProvisioningDisposable.dispose();
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ZeroTouchWorkflowFactory createNewWorkflowFactory() {
        return new ZeroTouchWorkflowFactory() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.service.FFSProvisioningService.3
            @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ZeroTouchWorkflowFactory
            public PhilipsZigbeeBleWorkflow createNewPhilipsZigbeeBleWorkflow() {
                return FFSProvisioningService.this.mProvisionerServices.createWorkflow(FFSProvisioningService.this.mFFSArcusSyncCoordinator.getFFSArcusSettings()).getPhilipsZigbeeBleWorkflow();
            }

            @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ZeroTouchWorkflowFactory
            public WifiSimpleSetupZeroTouchWorkflow createNewWifiSimpleSetupWorkflow() {
                return FFSProvisioningService.this.mProvisionerServices.createWorkflow(FFSProvisioningService.this.mFFSArcusSyncCoordinator.getFFSArcusSettings()).getWifiSimpleSetupZeroTouchWorkflow();
            }
        };
    }

    private boolean isWorkflowActive() {
        return this.mZeroTouchWorkflowController != null;
    }

    private void persistProvisioningServiceConfiguration(ProvisioningServiceConfiguration provisioningServiceConfiguration) {
        provisioningServiceConfiguration.writeToSharedPreferences(this.mSharedPreferencesProvider.getFFSConfigurationPreferences().edit());
    }

    private void recordPermissionsAndSettingsMetricsAtStart() {
        if (this.mLocationPermissionsHelper.isLocationPermissionNeededForBLEScanning()) {
            this.mFFSServiceMetricsRecorder.onApiRequireLocationPermissionForScan(this.mLocationPermissionsHelper.isAppLocationPermissionGranted(), this.mLocationPermissionsHelper.isSystemLocationServicesEnabled());
        } else {
            this.mFFSServiceMetricsRecorder.onApiDoesntRequireLocationPermissionForScan();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void restartWorkflowController() {
        WJLog.d(TAG, "Restarting workflow - Arcus settings have changed");
        stopWorkflowController();
        startWorkflowController();
    }

    private void startRecordingMetrics() {
        this.mFFSServiceMetricsRecorder.onServiceStart();
        recordPermissionsAndSettingsMetricsAtStart();
    }

    private void startWorkflowController() {
        if (this.mControllerDisposable != null) {
            WJLog.d(TAG, "Start workflow called but already started, ignoring");
        } else {
            this.mControllerDisposable = (Disposable) Observable.defer(new Supplier<ObservableSource<ControllerUpdate>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.service.FFSProvisioningService.6
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // io.reactivex.rxjava3.functions.Supplier
                /* renamed from: get */
                public ObservableSource<ControllerUpdate> mo10365get() {
                    WJLog.d(FFSProvisioningService.TAG, "Creating ZTS controller");
                    OveractiveConfiguration overactiveConfiguration = FFSProvisioningService.this.mFFSArcusSettings.getThrottleSettings().getOveractiveConfiguration();
                    FFSProvisioningService fFSProvisioningService = FFSProvisioningService.this;
                    fFSProvisioningService.mProvisioningDisposable = fFSProvisioningService.mProvisionerServices.initProvisioningComponent(FFSProvisioningService.this.mWhiteListPolicy.getScanningMode(), FFSProvisioningService.this.mFFSArcusSettings.getZeroTouchProvisioningSettings().isPhilipsBLEWorkflowEnabled(), overactiveConfiguration);
                    FFSProvisioningService fFSProvisioningService2 = FFSProvisioningService.this;
                    DaggerWrapper.ProvisionerServices provisionerServices = fFSProvisioningService2.mProvisionerServices;
                    FFSProvisioningService fFSProvisioningService3 = FFSProvisioningService.this;
                    fFSProvisioningService2.mZeroTouchWorkflowController = provisionerServices.zeroTouchController(fFSProvisioningService3.mContext, fFSProvisioningService3.createNewWorkflowFactory(), FFSProvisioningService.this.mFFSArcusSettings).getZeroTouchWorkflowController();
                    return FFSProvisioningService.this.mZeroTouchWorkflowController.start();
                }
            }).doOnSubscribe(new Consumer<Disposable>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.service.FFSProvisioningService.5
                @Override // io.reactivex.rxjava3.functions.Consumer
                public void accept(@NonNull Disposable disposable) throws Exception {
                    FFSProvisioningService.this.mDevicePowerMonitor.onWorkflowActivationStateChange(true);
                }
            }).doFinally(cleanupWorkflow()).subscribeWith(new DisposableObserver<ControllerUpdate>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.service.FFSProvisioningService.4
                @Override // io.reactivex.rxjava3.core.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.rxjava3.core.Observer
                public void onError(Throwable th) {
                    Log.e(FFSProvisioningService.TAG, "An unhandled ffs workflow error occurred", th);
                    FFSProvisioningService.this.mFFSServiceMetricsRecorder.onWorkflowError();
                }

                @Override // io.reactivex.rxjava3.core.Observer
                public void onNext(ControllerUpdate controllerUpdate) {
                    FFSProvisioningService.this.mProvisioningEventListener.onNext(controllerUpdate.getEvent().toString(), controllerUpdate.getMessage());
                }
            });
        }
    }

    private void stopRecordingMetrics() {
        this.mFFSServiceMetricsRecorder.onServiceStop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopWorkflowController() {
        WJLog.i(TAG, "Stopping workflow controller");
        Disposable disposable = this.mControllerDisposable;
        if (disposable != null) {
            disposable.dispose();
            this.mControllerDisposable = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateWorkflowActivityStateBasedOnConstraints(WhiteListPolicy whiteListPolicy, DevicePowerStatus devicePowerStatus) {
        if (whiteListPolicy == null) {
            WJLog.w(TAG, "WhiteListPolicy is null, no update");
        } else if (devicePowerStatus == null) {
            WJLog.w(TAG, "PowerStats is null, no update");
        } else if (!isServiceRunning()) {
            WJLog.d(TAG, "Service not running when update occurred ignoring.");
        } else {
            boolean z = devicePowerStatus.getBatteryPercentage() * 100.0d >= ((double) whiteListPolicy.getMinBatteryLevel());
            WJLog.d(TAG, String.format(Locale.ENGLISH, "UpdateWorkflowActivityState: batteryAboveMinThreshold: %b, allowedToScan: %b, workflowActive: %b", Boolean.valueOf(z), Boolean.valueOf(whiteListPolicy.allowedToScan()), Boolean.valueOf(isWorkflowActive())));
            if (isWorkflowActive()) {
                if (!whiteListPolicy.allowedToScan()) {
                    WJLog.d(TAG, "Shutting down active workflow, no longer allowed to scan");
                    stopWorkflowController();
                } else if (!z) {
                    WJLog.d(TAG, "Shutting down active workflow, battery level below min threshold");
                    stopWorkflowController();
                } else {
                    WJLog.d(TAG, "No action taken on active workflow, still allowed to continue");
                }
            } else if (whiteListPolicy.allowedToScan() && z) {
                WJLog.d(TAG, "Starting workflow since allowed to scan and battery level above min threshold");
                startWorkflowController();
            } else {
                WJLog.d(TAG, "No action taken on active workflow, constraints not met");
            }
        }
    }

    public synchronized boolean isServiceRunning() {
        return this.mServiceRunning;
    }

    public void shutdown() {
        WJLog.i(TAG, "Shutdown Service");
        stopWorkflowController();
        this.mServiceRunning = false;
        this.mWhiteListPolicyCoordinator.cancelAnyPendingRefreshJobs();
        this.mWhiteListPolicyUpdateListener.unregisterForWhiteListPolicyUpdates();
        this.mDevicePowerMonitor.stopMonitoring();
        this.mContext.unregisterReceiver(this.mArcusBroadcastReceiver);
        this.mArcusBroadcastReceiver = null;
        stopRecordingMetrics();
    }

    public void start() {
        WJLog.i(TAG, "Start Service");
        this.mServiceRunning = true;
        if (this.mFireOSUtil.isPoweredDevice()) {
            WJLog.d(TAG, "Power monitoring will not be started. Storing current power status.");
            this.mDevicePowerStatus = this.mDevicePowerMonitor.getCurrentDevicePowerStatus();
        } else {
            WJLog.d(TAG, "Starting power monitoring.");
            this.mDevicePowerStatus = this.mDevicePowerMonitor.startMonitoring(this.mDevicePowerUpdateListener);
        }
        this.mWhiteListPolicyUpdateListener.registerForWhiteListPolicyUpdates(this.mWhiteListPolicyUpdateCallback);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ArcusConstants.ARCUS_UPDATE_INTENT_ACTION);
        this.mArcusBroadcastReceiver = new ArcusBroadcastReceiver(this);
        this.mContext.registerReceiver(this.mArcusBroadcastReceiver, intentFilter, "com.amazon.whisperjoin.deviceprovisioningservice.ArcusUpdate.Permission", null);
        startRecordingMetrics();
        this.mFFSArcusSyncCoordinator.getData().flatMap(new Function<FFSArcusSyncResult, SingleSource<WhiteListPolicy>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.service.FFSProvisioningService.2
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public SingleSource<WhiteListPolicy> mo10358apply(FFSArcusSyncResult fFSArcusSyncResult) throws Exception {
                FFSProvisioningService.this.mFFSArcusSettings = fFSArcusSyncResult.getFFSArcusSettings();
                return FFSProvisioningService.this.mWhiteListPolicyCoordinator.getWhiteListPolicy();
            }
        }).subscribeWith(new DisposableSingleObserver<WhiteListPolicy>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.service.FFSProvisioningService.1
            @Override // io.reactivex.rxjava3.core.SingleObserver
            public void onError(@NonNull Throwable th) {
                WJLog.e(FFSProvisioningService.TAG, "There was error getting the whitelist policy", th);
                FFSProvisioningService.this.stopWorkflowController();
            }

            @Override // io.reactivex.rxjava3.core.SingleObserver
            public void onSuccess(@NonNull WhiteListPolicy whiteListPolicy) {
                String str = FFSProvisioningService.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Received WhiteListPolicy. Allowed to Scan: ");
                outline107.append(whiteListPolicy.allowedToScan());
                WJLog.d(str, outline107.toString());
                FFSProvisioningService.this.mWhiteListPolicy = whiteListPolicy;
                FFSProvisioningService fFSProvisioningService = FFSProvisioningService.this;
                fFSProvisioningService.updateWorkflowActivityStateBasedOnConstraints(fFSProvisioningService.mWhiteListPolicy, FFSProvisioningService.this.mDevicePowerStatus);
            }
        });
    }

    FFSProvisioningService(ProvisioningServiceConfiguration provisioningServiceConfiguration, ProvisioningEventListener provisioningEventListener, DaggerWrapper.ProvisionerServices provisionerServices) {
        if (provisioningServiceConfiguration != null) {
            if (provisioningEventListener != null) {
                this.mProvisionerServices = provisionerServices;
                this.mProvisionerServices.getDependencyInjector().inject(this);
                this.mProvisioningEventListener = provisioningEventListener;
                this.mFireOSUtil = new FireOSUtil(this.mContext);
                this.mDebugEnabled = provisioningServiceConfiguration.isDebugEnabled();
                persistProvisioningServiceConfiguration(provisioningServiceConfiguration);
                return;
            }
            throw new IllegalArgumentException("eventListener can not be null");
        }
        throw new IllegalArgumentException("provisioningServiceConfiguration can not be null");
    }
}
