package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation;

import com.amazon.devicesetupservice.reporting.Radio;
import com.amazon.devicesetupservice.v1.ProvisionableInfo;
import com.amazon.devicesetupservice.v1.ProvisionerInfo;
import com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetailsV2;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.FFSArcusSettings;
import com.amazon.whisperjoin.deviceprovisioningservice.device.DiscoverySettings;
import com.amazon.whisperjoin.deviceprovisioningservice.device.ProvisioningManagerProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.error.BluetoothLowEnergyNotSupportedException;
import com.amazon.whisperjoin.deviceprovisioningservice.error.BluetoothNotEnabledException;
import com.amazon.whisperjoin.deviceprovisioningservice.error.CustomerNotLoggedInError;
import com.amazon.whisperjoin.deviceprovisioningservice.error.DeviceRecentlyProvisionedException;
import com.amazon.whisperjoin.deviceprovisioningservice.error.HighRateOfDssRequestFailures;
import com.amazon.whisperjoin.deviceprovisioningservice.error.RequiredLocationPermissionsForScanningNotGrantedException;
import com.amazon.whisperjoin.deviceprovisioningservice.metrics.MetricsRecorderObservableSourceTransformer;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisionerClientData;
import com.amazon.whisperjoin.deviceprovisioningservice.util.Clock;
import com.amazon.whisperjoin.deviceprovisioningservice.util.DetectEventRateExceedingThreshold;
import com.amazon.whisperjoin.deviceprovisioningservice.util.LocationPermissionsHelper;
import com.amazon.whisperjoin.deviceprovisioningservice.util.ProvisioneeInfoMessage;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.WorkflowConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.Action;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.result.WJResult;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.BluetoothSupportProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.DiscoveredProvisionable;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioningMethod;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.TrustMethod;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.Event;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.DiscoveredDistressedProvisioneeRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.DiscoveredProvisionableDeviceRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.GenericDSSDiscoveryResponse;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.GetRegistrationStatusRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.GetRegistrationStatusResponse;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.error.DSSServiceError;
import com.amazon.whisperjoin.metrics.MetricsRecorderProvider;
import com.amazon.whisperjoin.metrics.WhisperJoinMetricSourceName;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import java.util.Collections;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
/* loaded from: classes13.dex */
public class StartDiscoveryOperation extends DeviceOperation<Action.StartDiscovery> {
    private static final long DSS_FAILED_MONITOR_WINDOW_MS = TimeUnit.MINUTES.toMillis(1);
    private static final long DSS_FAILED_TRESHOLD = 4;
    private static final String TAG = "StartDiscoveryOperation";
    private final BluetoothSupportProvider mBluetoothSupportProvider;
    private final DSSClient mDSSClient;
    private DiscoveryEventHandler mDiscoveryEventHandler = new DiscoveryEventHandler();
    final WJDeviceSetupModeSupportedPredicate mDiscoveryFilter;
    private DiscoverySettings mDiscoverySettings;
    private final Single<FFSArcusSettings> mFFSArcusSettingsSingle;
    private DetectEventRateExceedingThreshold mFailedDSSCallTracker;
    private final LocationPermissionsHelper mLocationPermissionsHelper;
    private final MetricsRecorderProvider mMetricsRecorderProvider;
    private final ProvisionerClientData mProvisionerClientData;
    private final ProvisioningManagerProvider mProvisioningManager;
    private final ProvisioningMethod mProvisioningMethod;
    private final WorkflowConfiguration mWorkflowConfiguration;

    /* loaded from: classes13.dex */
    private static class DiscoveryEventHandler extends DiscoveryEventEmitter<WhisperJoinPeripheralDeviceDetails> {
        private DiscoveryEventHandler() {
        }

        @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.DiscoveryEventEmitter
        protected void onDeviceDiscovered(WhisperJoinPeripheralDeviceDetails whisperJoinPeripheralDeviceDetails) {
            String str = StartDiscoveryOperation.TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Device Discovered - ");
            outline107.append(ProvisioneeInfoMessage.create(whisperJoinPeripheralDeviceDetails));
            WJLog.i(str, outline107.toString());
            onNext(whisperJoinPeripheralDeviceDetails);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class LimitOfSuccessResultsReached implements Predicate<WJResult> {
        private int mSuccessCount = 0;
        private final int mSuccessLimit;

        public LimitOfSuccessResultsReached(int i) {
            this.mSuccessLimit = i;
        }

        @Override // io.reactivex.rxjava3.functions.Predicate
        public boolean test(@NonNull WJResult wJResult) throws Exception {
            if (!wJResult.isState(Event.State.SUCCESS)) {
                return false;
            }
            this.mSuccessCount++;
            return this.mSuccessCount == this.mSuccessLimit;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public class StartDiscoveryOnSubscribe implements ObservableOnSubscribe<WhisperJoinPeripheralDeviceDetails> {
        private final DiscoverySettings mDiscoverySettings;

        public StartDiscoveryOnSubscribe(DiscoverySettings discoverySettings) {
            this.mDiscoverySettings = discoverySettings;
        }

        @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
        public void subscribe(@NonNull ObservableEmitter<WhisperJoinPeripheralDeviceDetails> observableEmitter) throws Exception {
            StartDiscoveryOperation.this.mDiscoveryEventHandler.setEmitter(observableEmitter);
            WJLog.d(StartDiscoveryOperation.TAG, String.format(Locale.ENGLISH, "starting discovery using the filter: %s", this.mDiscoverySettings.getDeviceFilter()));
            StartDiscoveryOperation.this.mProvisioningManager.startDiscovery(this.mDiscoverySettings);
        }
    }

    public StartDiscoveryOperation(ProvisioningManagerProvider provisioningManagerProvider, DSSClient dSSClient, ProvisionerClientData provisionerClientData, DiscoverySettings discoverySettings, MetricsRecorderProvider metricsRecorderProvider, Clock clock, ProvisioningMethod provisioningMethod, LocationPermissionsHelper locationPermissionsHelper, BluetoothSupportProvider bluetoothSupportProvider, WJDeviceSetupModeSupportedPredicate wJDeviceSetupModeSupportedPredicate, WorkflowConfiguration workflowConfiguration, Single<FFSArcusSettings> single) {
        this.mProvisioningManager = provisioningManagerProvider;
        this.mDSSClient = dSSClient;
        this.mProvisionerClientData = provisionerClientData;
        this.mDiscoverySettings = discoverySettings;
        this.mProvisioningMethod = provisioningMethod;
        this.mBluetoothSupportProvider = bluetoothSupportProvider;
        this.mLocationPermissionsHelper = locationPermissionsHelper;
        this.mDiscoveryFilter = wJDeviceSetupModeSupportedPredicate;
        this.mMetricsRecorderProvider = metricsRecorderProvider;
        this.mWorkflowConfiguration = workflowConfiguration;
        this.mFailedDSSCallTracker = new DetectEventRateExceedingThreshold(clock, DSS_FAILED_MONITOR_WINDOW_MS, 4L);
        this.mFFSArcusSettingsSingle = single;
        this.mProvisioningManager.addDiscoveryEventObserver(this.mDiscoveryEventHandler);
    }

    private Observable<WJResult> discoverBarcodeDevice() {
        return this.mDSSClient.getRegistrationStatus(new GetRegistrationStatusRequest(this.mWorkflowConfiguration.getBarcodeString())).flatMapObservable(new Function<GetRegistrationStatusResponse, ObservableSource<? extends WJResult>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.StartDiscoveryOperation.3
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public ObservableSource<? extends WJResult> mo10358apply(GetRegistrationStatusResponse getRegistrationStatusResponse) throws Exception {
                if (!getRegistrationStatusResponse.getStatus().equals(GetRegistrationStatusResponse.Status.RECENTLY_REGISTERED)) {
                    return StartDiscoveryOperation.this.discoverDSSBlessedDevices();
                }
                ConnectableObservable publish = StartDiscoveryOperation.this.discoverDSSBlessedDevices().publish();
                Observable timeout = publish.take(1L).timeout(getRegistrationStatusResponse.getDurationToWait(), TimeUnit.MILLISECONDS, Observable.error(new DeviceRecentlyProvisionedException()));
                ObservableSource skip = publish.skip(1L);
                publish.connect();
                return timeout.mergeWith(skip);
            }
        }).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends WJResult>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.StartDiscoveryOperation.2
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public ObservableSource<? extends WJResult> mo10358apply(Throwable th) throws Exception {
                if (th instanceof DSSServiceError) {
                    return StartDiscoveryOperation.this.discoverDSSBlessedDevices();
                }
                return Observable.error(th);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Observable<WJResult> discoverDSSBlessedDevices() {
        return Observable.create(new StartDiscoveryOnSubscribe(this.mDiscoverySettings)).filter(this.mDiscoveryFilter).flatMap(new Function<WhisperJoinPeripheralDeviceDetails, ObservableSource<WJResult>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.StartDiscoveryOperation.4
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public ObservableSource<WJResult> mo10358apply(@NonNull final WhisperJoinPeripheralDeviceDetails whisperJoinPeripheralDeviceDetails) throws Exception {
                return StartDiscoveryOperation.this.mFFSArcusSettingsSingle.flatMapObservable(new Function<FFSArcusSettings, ObservableSource<? extends WJResult>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.StartDiscoveryOperation.4.1
                    @Override // io.reactivex.rxjava3.functions.Function
                    /* renamed from: apply  reason: avoid collision after fix types in other method */
                    public ObservableSource<? extends WJResult> mo10358apply(FFSArcusSettings fFSArcusSettings) throws Exception {
                        if (!fFSArcusSettings.getFFRSettings().getProvisionerSettings().isEnabled()) {
                            WJLog.d(StartDiscoveryOperation.TAG, "FFR disabled according to Arcus. Checking isDistressed().");
                            if (whisperJoinPeripheralDeviceDetails.isDistressed()) {
                                WJLog.i(StartDiscoveryOperation.TAG, "FFR has been disabled for discovered device");
                                return Observable.empty();
                            }
                        }
                        return StartDiscoveryOperation.this.reportDiscoveryToDSS(whisperJoinPeripheralDeviceDetails);
                    }
                });
            }
        });
    }

    private DiscoveredDistressedProvisioneeRequest getDiscoveredDistressedProvisioneeRequest(@NonNull WhisperJoinPeripheralDeviceDetailsV2 whisperJoinPeripheralDeviceDetailsV2) {
        ProvisionableInfo provisionableInfo = new ProvisionableInfo();
        provisionableInfo.setSoftwareVersionIndex(whisperJoinPeripheralDeviceDetailsV2.getSoftwareVersion());
        provisionableInfo.setDeviceName(whisperJoinPeripheralDeviceDetailsV2.getFriendlyName());
        return new DiscoveredDistressedProvisioneeRequest((this.mProvisioningMethod.equals(ProvisioningMethod.FFS) ? TrustMethod.AUTHENTICATED : TrustMethod.UNAUTHENTICATED).toString(), this.mProvisioningMethod.toString(), whisperJoinPeripheralDeviceDetailsV2.getClientNonce(), whisperJoinPeripheralDeviceDetailsV2.getDistressErrorCode(), provisionableInfo, whisperJoinPeripheralDeviceDetailsV2.getRadios().iterator().next().getSignalStrength(), whisperJoinPeripheralDeviceDetailsV2.getProductIndex(), whisperJoinPeripheralDeviceDetailsV2.getDeviceIdentity(), Radio.BLUETOOTH_LOW_ENERGY, getProvisionerInfoForDSSRequest(), whisperJoinPeripheralDeviceDetailsV2.getAdvertisedSdkVersionIndex());
    }

    private DiscoveredProvisionableDeviceRequest getDiscoveredProvisionableDeviceRequest(@NonNull WhisperJoinPeripheralDeviceDetails whisperJoinPeripheralDeviceDetails) {
        return new DiscoveredProvisionableDeviceRequest.Builder().setProvisionerApplicationName(this.mProvisionerClientData.getClientAppName()).setProvisionerApplicationVersion(this.mProvisionerClientData.getClientAppVersion()).setRssi(whisperJoinPeripheralDeviceDetails.getRadios().iterator().next().getSignalStrength()).setDeviceName(whisperJoinPeripheralDeviceDetails.getFriendlyName()).setNonce(whisperJoinPeripheralDeviceDetails.getClientNonce()).setProductIndex(whisperJoinPeripheralDeviceDetails.getProductIndex()).setAuthMaterialIndex(whisperJoinPeripheralDeviceDetails.getDeviceIdentity()).setSoftwareVersionIndex(whisperJoinPeripheralDeviceDetails.getSoftwareVersion()).setProvisioningMethod(this.mProvisioningMethod.toString()).setTrustMethod((this.mProvisioningMethod.equals(ProvisioningMethod.FFS) ? TrustMethod.AUTHENTICATED : TrustMethod.UNAUTHENTICATED).toString()).setProvisionerInfo(getProvisionerInfoForDSSRequest()).setAdvertisedSdkVersionIndex(whisperJoinPeripheralDeviceDetails.getAdvertisedSdkVersionIndex()).createRequest();
    }

    private ProvisionerInfo getProvisionerInfoForDSSRequest() {
        ProvisionerInfo provisionerInfo = new ProvisionerInfo();
        provisionerInfo.setManufacturer(this.mProvisionerClientData.getDeviceManufacturer());
        provisionerInfo.setFirmwareVersion(this.mProvisionerClientData.getDeviceFirmwareVersion());
        provisionerInfo.setDeviceModel(this.mProvisionerClientData.getDeviceModel());
        provisionerInfo.setApplication(this.mProvisionerClientData.getClientAppName());
        provisionerInfo.setApplicationVersion(this.mProvisionerClientData.getClientAppVersion());
        return provisionerInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Observable<WJResult> handleDssBlessedProvisionable(WhisperJoinPeripheralDeviceDetails whisperJoinPeripheralDeviceDetails, GenericDSSDiscoveryResponse genericDSSDiscoveryResponse) {
        WJLog.d(TAG, "Blessed by DSS to setup wjProvisionee");
        return Observable.just(WJResult.Discovery.success(Collections.singletonList(new DiscoveredProvisionable(this.mProvisioningManager.createWJProvsionee(whisperJoinPeripheralDeviceDetails), genericDSSDiscoveryResponse.getProvisionerEventReportUrl(), genericDSSDiscoveryResponse.getProvisionableEventReportUrl(), genericDSSDiscoveryResponse.getSessionToken()))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Observable<WJResult> handleDssDiscoveryDeferment(WhisperJoinPeripheralDeviceDetails whisperJoinPeripheralDeviceDetails, GenericDSSDiscoveryResponse genericDSSDiscoveryResponse) {
        WJLog.d(TAG, "Deferring discovery event based on DSS response");
        return Observable.empty();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isManualSetup() {
        return this.mProvisioningMethod.equals(ProvisioningMethod.MANUAL);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ObservableSource<WJResult> reportDiscoveryToDSS(@NonNull final WhisperJoinPeripheralDeviceDetails whisperJoinPeripheralDeviceDetails) {
        Single<GenericDSSDiscoveryResponse> discoveredProvisionableDevice;
        if (whisperJoinPeripheralDeviceDetails.isDistressed()) {
            WJLog.i(TAG, "Discovered a distressed beacon");
            discoveredProvisionableDevice = this.mDSSClient.discoveredDistressedProvisionee(getDiscoveredDistressedProvisioneeRequest((WhisperJoinPeripheralDeviceDetailsV2) whisperJoinPeripheralDeviceDetails));
        } else {
            WJLog.i(TAG, "Discovered a setup beacon");
            discoveredProvisionableDevice = this.mDSSClient.discoveredProvisionableDevice(getDiscoveredProvisionableDeviceRequest(whisperJoinPeripheralDeviceDetails));
        }
        return discoveredProvisionableDevice.flatMapObservable(new Function<GenericDSSDiscoveryResponse, ObservableSource<? extends WJResult>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.StartDiscoveryOperation.7
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public ObservableSource<? extends WJResult> mo10358apply(@NonNull GenericDSSDiscoveryResponse genericDSSDiscoveryResponse) throws Exception {
                if (genericDSSDiscoveryResponse.canProceed()) {
                    String str = StartDiscoveryOperation.TAG;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Provisioning Can Proceed - ");
                    outline107.append(ProvisioneeInfoMessage.create(whisperJoinPeripheralDeviceDetails));
                    WJLog.i(str, outline107.toString());
                    return StartDiscoveryOperation.this.handleDssBlessedProvisionable(whisperJoinPeripheralDeviceDetails, genericDSSDiscoveryResponse);
                }
                String str2 = StartDiscoveryOperation.TAG;
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Provisioning Deferred - ");
                outline1072.append(ProvisioneeInfoMessage.create(whisperJoinPeripheralDeviceDetails));
                WJLog.i(str2, outline1072.toString());
                String str3 = StartDiscoveryOperation.TAG;
                StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Wait Time: [");
                outline1073.append(genericDSSDiscoveryResponse.getWaitTime());
                outline1073.append("]");
                WJLog.i(str3, outline1073.toString());
                return StartDiscoveryOperation.this.handleDssDiscoveryDeferment(whisperJoinPeripheralDeviceDetails, genericDSSDiscoveryResponse);
            }
        }).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends WJResult>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.StartDiscoveryOperation.6
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public ObservableSource<? extends WJResult> mo10358apply(@NonNull Throwable th) throws Exception {
                String str = StartDiscoveryOperation.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Provisioning Request Rejected - ");
                outline107.append(ProvisioneeInfoMessage.create(whisperJoinPeripheralDeviceDetails));
                WJLog.i(str, outline107.toString());
                WJLog.d(StartDiscoveryOperation.TAG, "There was an error reporting device to DSS. Not accepting device to be provisioned");
                if (StartDiscoveryOperation.this.mFailedDSSCallTracker.thresholdExceeded()) {
                    return Observable.just(WJResult.Discovery.error(new HighRateOfDssRequestFailures()));
                }
                return Observable.empty();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Observable<WJResult> startDiscovery() {
        if (this.mWorkflowConfiguration.hasBarcodeData()) {
            return discoverBarcodeDevice();
        }
        return discoverDSSBlessedDevices();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Completable verifySetupPreconditions() {
        return Completable.defer(new Supplier<CompletableSource>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.StartDiscoveryOperation.5
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public CompletableSource mo10365get() throws Exception {
                if (StartDiscoveryOperation.this.mBluetoothSupportProvider.isBluetoothLowEnergySupported()) {
                    if (!StartDiscoveryOperation.this.mLocationPermissionsHelper.requiresGrantingAppLocationPermissionBeforeBLEScanning()) {
                        if (StartDiscoveryOperation.this.mBluetoothSupportProvider.isBluetoothEnabled() || !StartDiscoveryOperation.this.isManualSetup()) {
                            if (StartDiscoveryOperation.this.mProvisionerClientData.getCustomerEcid() == null) {
                                return Completable.error(new CustomerNotLoggedInError());
                            }
                            return Completable.complete();
                        }
                        return Completable.error(new BluetoothNotEnabledException());
                    }
                    return Completable.error(new RequiredLocationPermissionsForScanningNotGrantedException());
                }
                return Completable.error(new BluetoothLowEnergyNotSupportedException());
            }
        });
    }

    @Override // io.reactivex.rxjava3.core.ObservableTransformer
    public ObservableSource<WJResult> apply(@NonNull Observable<Action.StartDiscovery> observable) {
        return observable.flatMap(new Function<Action.StartDiscovery, ObservableSource<WJResult>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.StartDiscoveryOperation.1
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public ObservableSource<WJResult> mo10358apply(Action.StartDiscovery startDiscovery) throws Exception {
                return StartDiscoveryOperation.this.verifySetupPreconditions().andThen(StartDiscoveryOperation.this.startDiscovery()).takeUntil(new LimitOfSuccessResultsReached(StartDiscoveryOperation.this.mDiscoverySettings.getLimitForNumberOfDiscoveredDevices())).compose(new MetricsRecorderObservableSourceTransformer(StartDiscoveryOperation.this.mMetricsRecorderProvider, WhisperJoinMetricSourceName.START_DISCOVERY_OPERATION)).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends WJResult>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.StartDiscoveryOperation.1.1
                    @Override // io.reactivex.rxjava3.functions.Function
                    /* renamed from: apply  reason: avoid collision after fix types in other method */
                    public ObservableSource<? extends WJResult> mo10358apply(@NonNull Throwable th) throws Exception {
                        return Observable.just(WJResult.Discovery.error(th));
                    }
                }).startWithItem(WJResult.Discovery.inProgress());
            }
        });
    }
}
