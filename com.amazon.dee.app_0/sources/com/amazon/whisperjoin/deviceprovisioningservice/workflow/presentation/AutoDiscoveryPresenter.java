package com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.devicesetupservice.reporting.Radio;
import com.amazon.devicesetupservice.v1.ProvisionableInfo;
import com.amazon.devicesetupservice.v1.ProvisionerInfo;
import com.amazon.whisperjoin.common.sharedtypes.devices.DiscoveredRadio;
import com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetailsV2;
import com.amazon.whisperjoin.common.sharedtypes.radios.ScanningMode;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.FFSArcusSyncCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.FFSArcusSettings;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.ThrottleSettings;
import com.amazon.whisperjoin.deviceprovisioningservice.device.DiscoveredDevice;
import com.amazon.whisperjoin.deviceprovisioningservice.device.DiscoveredDevices;
import com.amazon.whisperjoin.deviceprovisioningservice.device.DiscoverySettings;
import com.amazon.whisperjoin.deviceprovisioningservice.di.DaggerWrapper;
import com.amazon.whisperjoin.deviceprovisioningservice.metrics.AutoDiscoveryMetricsRecorder;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisionerClientData;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisioningServiceConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.WorkflowConfigurationFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.AutoDiscoveryPresenterContract;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.DiscoveredDevicesViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.FailureViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.RecentlySetupDevicesViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceDiscoveryEvent;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceDiscoveryStream;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.CustomerProvisioneesSetupStatusSyncCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.GetCustomerProvisioneesSetupStatusResponse;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioningMethod;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorMapper;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorUtils;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.Event;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.DiscoveredLocalNotificationProvisioneeRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.DiscoveredLocalNotificationProvisioneeResponse;
import com.amazon.whisperjoin.provisionerSDK.radios.ble.OveractiveBleActivityDetector;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import org.apache.commons.lang.exception.ExceptionUtils;
/* loaded from: classes13.dex */
public class AutoDiscoveryPresenter extends BasePresenter<AutoDiscoveryPresenterContract.View> implements AutoDiscoveryPresenterContract.Action {
    private static final String TAG = "AutoDiscoveryPresenter";
    @Inject
    CustomerProvisioneesSetupStatusSyncCoordinator mAutoDiscoverySyncCoordinator;
    private final Context mContext;
    @Inject
    DeviceDiscoveryStream mDeviceDiscoveryStream;
    private Disposable mDeviceDiscoveryStreamSubscription;
    @Inject
    DiscoverySettings mDiscoverySettings;
    @Inject
    DSSClient mDssClient;
    @Inject
    FFSArcusSyncCoordinator mFFSArcusSyncCoordinator;
    @Inject
    AutoDiscoveryMetricsRecorder mMetricsRecorder;
    @Inject
    ProvisionerClientData mProvisionerClientData;
    @Inject
    WJErrorMapper<Throwable> mWJErrorMapper;
    private final Map<DiscoveredDevice, DeviceDiscoveryIdentity> mKnownDevices = new LinkedHashMap();
    private final Set<DiscoveredDevice> mUnblessedDevices = new HashSet();
    private final CompositeDisposable mDisposables = new CompositeDisposable();
    private List<DiscoveredDevice> mReportedDevicesForControlledSetup = Collections.emptyList();
    private List<DiscoveredDevice> mReportedDevicesIneligibleForAutomatedSetup = Collections.emptyList();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static final class DeviceDiscoveryAndResult {
        private final DeviceDiscoveryEvent deviceDiscoveryEvent;
        private final DiscoveredLocalNotificationProvisioneeResponse response;

        DeviceDiscoveryAndResult(DeviceDiscoveryEvent deviceDiscoveryEvent, DiscoveredLocalNotificationProvisioneeResponse discoveredLocalNotificationProvisioneeResponse) {
            this.deviceDiscoveryEvent = deviceDiscoveryEvent;
            this.response = discoveredLocalNotificationProvisioneeResponse;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static final class DeviceDiscoveryIdentity {
        private final boolean authenticatedEcdhe;
        private final boolean canProceed;
        private final String deviceIdentity;
        private final String productIndex;
        private final boolean unauthenticatedEcdhe;

        public boolean equals(Object obj) {
            if (obj == null || DeviceDiscoveryIdentity.class != obj.getClass()) {
                return false;
            }
            DeviceDiscoveryIdentity deviceDiscoveryIdentity = (DeviceDiscoveryIdentity) obj;
            return Objects.equal(this.productIndex, deviceDiscoveryIdentity.productIndex) && Objects.equal(this.deviceIdentity, deviceDiscoveryIdentity.deviceIdentity) && Objects.equal(Boolean.valueOf(this.unauthenticatedEcdhe), Boolean.valueOf(deviceDiscoveryIdentity.unauthenticatedEcdhe)) && Objects.equal(Boolean.valueOf(this.authenticatedEcdhe), Boolean.valueOf(deviceDiscoveryIdentity.authenticatedEcdhe)) && Objects.equal(Boolean.valueOf(this.canProceed), Boolean.valueOf(deviceDiscoveryIdentity.canProceed));
        }

        public int hashCode() {
            return Objects.hashCode(this.productIndex, this.deviceIdentity, Boolean.valueOf(this.unauthenticatedEcdhe), Boolean.valueOf(this.authenticatedEcdhe), Boolean.valueOf(this.canProceed));
        }

        private DeviceDiscoveryIdentity(WhisperJoinPeripheralDeviceDetails whisperJoinPeripheralDeviceDetails, boolean z) {
            this.productIndex = whisperJoinPeripheralDeviceDetails.getProductIndex();
            this.deviceIdentity = whisperJoinPeripheralDeviceDetails.getDeviceIdentity();
            this.unauthenticatedEcdhe = whisperJoinPeripheralDeviceDetails.getSupportsUnauthenticatedEcdhe();
            this.authenticatedEcdhe = whisperJoinPeripheralDeviceDetails.getSupportsAuthenticatedEcdhe();
            this.canProceed = z;
        }
    }

    public AutoDiscoveryPresenter(Context context, ProvisioningServiceConfiguration provisioningServiceConfiguration) {
        this.mContext = context;
        DaggerWrapper.initializeBaseComponent(context);
        DaggerWrapper.ProvisionerServices provisionerServices = new DaggerWrapper.ProvisionerServices(DaggerWrapper.getBaseComponent(), provisioningServiceConfiguration, WorkflowConfigurationFactory.createWorkflowConfigurationForAllDevices(provisioningServiceConfiguration.getProvisionableBeaconType()), ProvisioningMethod.MANUAL);
        this.mDisposables.add(provisionerServices.initProvisioningComponent(ScanningMode.HIGH_POWER_LOW_LATENCY, false, new ThrottleSettings().getOveractiveConfiguration()));
        provisionerServices.getProvisioningDependencyInjector().inject(this);
        PresenterValidator.validatePreconditions(this.mProvisionerClientData);
        this.mAutoDiscoverySyncCoordinator.initializeWithProvisionerClientData(this.mProvisionerClientData);
        this.mFFSArcusSyncCoordinator.initializeWithProvisionerClientData(this.mProvisionerClientData);
        this.mDisposables.add(this.mDeviceDiscoveryStream.getStreamDisposable());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SingleSource<DeviceDiscoveryAndResult> discoverLocalNotificationDevice(final DeviceDiscoveryEvent deviceDiscoveryEvent) {
        WhisperJoinPeripheralDeviceDetails deviceDetails = getDeviceDetails(deviceDiscoveryEvent);
        Iterator<DiscoveredRadio> it2 = deviceDetails.getRadios().iterator();
        if (it2.hasNext()) {
            ProvisionableInfo provisionableInfo = new ProvisionableInfo();
            provisionableInfo.setSoftwareVersionIndex(deviceDetails.getSoftwareVersion());
            provisionableInfo.setDeviceName(deviceDetails.getFriendlyName());
            return Completable.timer(1L, TimeUnit.SECONDS).andThen(this.mDssClient.discoveredLocalNotificationProvisionee(new DiscoveredLocalNotificationProvisioneeRequest(Radio.BLUETOOTH_LOW_ENERGY, it2.next().getSignalStrength(), deviceDetails.getProductIndex(), deviceDetails.getDeviceIdentity(), provisionableInfo, deviceDetails.isDistressed() ? ((WhisperJoinPeripheralDeviceDetailsV2) deviceDetails).getDistressErrorCode() : null, deviceDetails.getClientNonce(), deviceDetails.getAuthenticationMode(), deviceDetails.isDistressed() ? 1 : 0, getProvisionerInfo(), deviceDetails.getAdvertisedSdkVersionIndex()))).flatMap(new Function<DiscoveredLocalNotificationProvisioneeResponse, SingleSource<DeviceDiscoveryAndResult>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.AutoDiscoveryPresenter.6
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply  reason: avoid collision after fix types in other method */
                public SingleSource<DeviceDiscoveryAndResult> mo10358apply(DiscoveredLocalNotificationProvisioneeResponse discoveredLocalNotificationProvisioneeResponse) throws Exception {
                    return Single.just(new DeviceDiscoveryAndResult(deviceDiscoveryEvent, discoveredLocalNotificationProvisioneeResponse));
                }
            }).observeOn(AndroidSchedulers.mainThread()).onErrorResumeNext(new Function<Throwable, SingleSource<DeviceDiscoveryAndResult>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.AutoDiscoveryPresenter.5
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply  reason: avoid collision after fix types in other method */
                public SingleSource<DeviceDiscoveryAndResult> mo10358apply(Throwable th) throws Exception {
                    AutoDiscoveryPresenter.this.reportFailure(th);
                    return Single.just(new DeviceDiscoveryAndResult(deviceDiscoveryEvent, null));
                }
            });
        }
        throw new IllegalStateException("Discovered device missing radio: " + deviceDetails);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WhisperJoinPeripheralDeviceDetails getDeviceDetails(DeviceDiscoveryEvent deviceDiscoveryEvent) {
        return (WhisperJoinPeripheralDeviceDetails) Preconditions.checkNotNull(deviceDiscoveryEvent.getData());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DiscoveredDevice getDiscoveredDevice(WhisperJoinPeripheralDeviceDetails whisperJoinPeripheralDeviceDetails) {
        if (whisperJoinPeripheralDeviceDetails.isDistressed()) {
            WhisperJoinPeripheralDeviceDetailsV2 whisperJoinPeripheralDeviceDetailsV2 = (WhisperJoinPeripheralDeviceDetailsV2) whisperJoinPeripheralDeviceDetails;
            return new DiscoveredDevice(whisperJoinPeripheralDeviceDetailsV2.getProductIndex(), whisperJoinPeripheralDeviceDetailsV2.getDeviceIdentity(), whisperJoinPeripheralDeviceDetailsV2.getFriendlyName(), true, whisperJoinPeripheralDeviceDetailsV2.getDistressErrorObject());
        }
        return new DiscoveredDevice(whisperJoinPeripheralDeviceDetails.getProductIndex(), whisperJoinPeripheralDeviceDetails.getDeviceIdentity(), whisperJoinPeripheralDeviceDetails.getFriendlyName());
    }

    private ProvisionerInfo getProvisionerInfo() {
        ProvisionerInfo provisionerInfo = new ProvisionerInfo();
        provisionerInfo.setManufacturer(this.mProvisionerClientData.getDeviceManufacturer());
        provisionerInfo.setFirmwareVersion(this.mProvisionerClientData.getDeviceFirmwareVersion());
        provisionerInfo.setDeviceModel(this.mProvisionerClientData.getDeviceModel());
        provisionerInfo.setApplication(this.mProvisionerClientData.getClientAppName());
        provisionerInfo.setApplicationVersion(this.mProvisionerClientData.getClientAppVersion());
        return provisionerInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleAttach() {
        if (this.mView != 0) {
            if (!this.mReportedDevicesForControlledSetup.isEmpty()) {
                showDevicesForControlledSetup(this.mReportedDevicesForControlledSetup);
            }
            if (this.mReportedDevicesIneligibleForAutomatedSetup.isEmpty()) {
                return;
            }
            showDevicesIneligibleForAutomatedSetup(this.mReportedDevicesIneligibleForAutomatedSetup);
        }
    }

    private void maybeShowDevicesForControlledSetup() {
        List<DiscoveredDevice> arrayList = new ArrayList<>();
        synchronized (this) {
            for (Map.Entry<DiscoveredDevice, DeviceDiscoveryIdentity> entry : this.mKnownDevices.entrySet()) {
                if (entry.getValue().unauthenticatedEcdhe) {
                    arrayList.add(entry.getKey());
                }
            }
        }
        if (!this.mReportedDevicesForControlledSetup.equals(arrayList)) {
            showDevicesForControlledSetup(arrayList);
            this.mReportedDevicesForControlledSetup = arrayList;
        }
    }

    private void maybeShowDevicesIneligibleForAutomatedSetup() {
        List<DiscoveredDevice> arrayList = new ArrayList<>();
        synchronized (this) {
            for (Map.Entry<DiscoveredDevice, DeviceDiscoveryIdentity> entry : this.mKnownDevices.entrySet()) {
                if (entry.getValue().authenticatedEcdhe) {
                    arrayList.add(entry.getKey());
                }
            }
        }
        if (!this.mReportedDevicesIneligibleForAutomatedSetup.equals(arrayList)) {
            showDevicesIneligibleForAutomatedSetup(arrayList);
            this.mReportedDevicesIneligibleForAutomatedSetup = arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportDevice(DeviceDiscoveryEvent deviceDiscoveryEvent, DiscoveredLocalNotificationProvisioneeResponse discoveredLocalNotificationProvisioneeResponse) {
        WhisperJoinPeripheralDeviceDetails deviceDetails = getDeviceDetails(deviceDiscoveryEvent);
        DiscoveredDevice discoveredDevice = getDiscoveredDevice(deviceDetails);
        DeviceDiscoveryIdentity deviceDiscoveryIdentity = new DeviceDiscoveryIdentity(deviceDetails, discoveredLocalNotificationProvisioneeResponse.canProceed());
        if (!discoveredLocalNotificationProvisioneeResponse.canProceed()) {
            String str = TAG;
            WJLog.d(str, "Ignoring device due to canProceed=false " + discoveredDevice);
            synchronized (this) {
                this.mUnblessedDevices.add(discoveredDevice);
            }
            return;
        }
        WJLog.d(TAG, deviceDiscoveryEvent.toString());
        synchronized (this) {
            this.mKnownDevices.put(discoveredDevice, deviceDiscoveryIdentity);
        }
        maybeShowDevicesForControlledSetup();
        maybeShowDevicesIneligibleForAutomatedSetup();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportFailure(Throwable th) {
        Throwable rootCause = WJErrorUtils.getRootCause(th);
        if (rootCause instanceof OveractiveBleActivityDetector.OveractivityDetected) {
            return;
        }
        WJLog.e(TAG, "There was error calling GetCustomerProvisioneesSetupStatusResponse", th);
        ((AutoDiscoveryPresenterContract.View) this.mView).showFailure(new FailureViewModel(rootCause.getClass().getSimpleName(), ExceptionUtils.getFullStackTrace(rootCause), this.mWJErrorMapper.map(rootCause)));
        this.mMetricsRecorder.onFailureReport();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportSetupStatusResponse(GetCustomerProvisioneesSetupStatusResponse getCustomerProvisioneesSetupStatusResponse) {
        Preconditions.checkNotNull(getCustomerProvisioneesSetupStatusResponse);
        V v = this.mView;
        if (v != 0) {
            ((AutoDiscoveryPresenterContract.View) v).showRecentlySetupDevices(new RecentlySetupDevicesViewModel.Builder().setGetCustomerProvisioneesSetupStatusResponse(getCustomerProvisioneesSetupStatusResponse).build());
            this.mMetricsRecorder.onRecentlySetupDevicesReport(getCustomerProvisioneesSetupStatusResponse.getProvisioneeSetupStatuses().size());
        }
    }

    private void showDevicesForControlledSetup(List<DiscoveredDevice> list) {
        V v = this.mView;
        if (v != 0) {
            ((AutoDiscoveryPresenterContract.View) v).showDevicesForControlledSetup(new DiscoveredDevicesViewModel(new DiscoveredDevices(list)));
            this.mMetricsRecorder.onControlledSetupReport(list.size());
        }
    }

    private void showDevicesIneligibleForAutomatedSetup(List<DiscoveredDevice> list) {
        V v = this.mView;
        if (v != 0) {
            ((AutoDiscoveryPresenterContract.View) v).showDevicesIneligibleForAutomatedSetup(new DiscoveredDevicesViewModel(new DiscoveredDevices(list)));
            this.mMetricsRecorder.onDevicesIneligibleForAutomatedSetupReport(list.size());
        }
    }

    private void stopDiscovery() {
        Disposable disposable = this.mDeviceDiscoveryStreamSubscription;
        if (disposable != null) {
            disposable.dispose();
            this.mDeviceDiscoveryStreamSubscription = null;
        }
        this.mDeviceDiscoveryStream.stop();
        this.mMetricsRecorder.onAutoDiscoveryStop();
    }

    public void destroy() {
        WJLog.d(TAG, "Destroy");
        stopDiscovery();
        this.mDisposables.dispose();
        this.mView = null;
        this.mMetricsRecorder.onDestroy();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.BasePresenter, com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.BasePresenterAction, com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.AutoDiscoveryPresenterContract.Action
    public void detachView() {
        super.detachView();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.AutoDiscoveryPresenterContract.Action
    public void getCustomerProvisioneesSetupStatus() {
        this.mMetricsRecorder.onGetCustomerProvisioneesSetupStatus();
        this.mDisposables.add((Disposable) this.mAutoDiscoverySyncCoordinator.getData().observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<GetCustomerProvisioneesSetupStatusResponse>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.AutoDiscoveryPresenter.7
            @Override // io.reactivex.rxjava3.core.SingleObserver
            public void onError(@NonNull Throwable th) {
                AutoDiscoveryPresenter.this.reportFailure(th);
            }

            @Override // io.reactivex.rxjava3.core.SingleObserver
            public void onSuccess(@NonNull GetCustomerProvisioneesSetupStatusResponse getCustomerProvisioneesSetupStatusResponse) {
                String str = AutoDiscoveryPresenter.TAG;
                WJLog.d(str, "Received GetCustomerProvisioneesSetupStatusResponse: " + getCustomerProvisioneesSetupStatusResponse);
                AutoDiscoveryPresenter.this.reportSetupStatusResponse(getCustomerProvisioneesSetupStatusResponse);
            }
        }));
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.AutoDiscoveryPresenterContract.Action
    public void startDiscovery() {
        this.mMetricsRecorder.onAutoDiscoveryStart();
        this.mDeviceDiscoveryStreamSubscription = this.mDeviceDiscoveryStream.getEventStream().observeOn(Schedulers.io()).flatMapSingle(new Function<DeviceDiscoveryEvent, SingleSource<DeviceDiscoveryAndResult>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.AutoDiscoveryPresenter.4
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public SingleSource<DeviceDiscoveryAndResult> mo10358apply(final DeviceDiscoveryEvent deviceDiscoveryEvent) throws Exception {
                if (deviceDiscoveryEvent.getState() != Event.State.SUCCESS) {
                    String str = AutoDiscoveryPresenter.TAG;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Ignoring non success event: ");
                    outline107.append(deviceDiscoveryEvent.toString());
                    WJLog.d(str, outline107.toString());
                    return Single.just(new DeviceDiscoveryAndResult(deviceDiscoveryEvent, null));
                }
                WhisperJoinPeripheralDeviceDetails deviceDetails = AutoDiscoveryPresenter.this.getDeviceDetails(deviceDiscoveryEvent);
                final DiscoveredDevice discoveredDevice = AutoDiscoveryPresenter.this.getDiscoveredDevice(deviceDetails);
                synchronized (this) {
                    DeviceDiscoveryIdentity deviceDiscoveryIdentity = (DeviceDiscoveryIdentity) AutoDiscoveryPresenter.this.mKnownDevices.get(discoveredDevice);
                    if (!AutoDiscoveryPresenter.this.mUnblessedDevices.contains(discoveredDevice)) {
                        if (deviceDiscoveryIdentity != null && deviceDiscoveryIdentity.authenticatedEcdhe == deviceDetails.getSupportsAuthenticatedEcdhe() && deviceDiscoveryIdentity.unauthenticatedEcdhe == deviceDetails.getSupportsUnauthenticatedEcdhe()) {
                            String str2 = AutoDiscoveryPresenter.TAG;
                            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Ignoring previously seen device: ");
                            outline1072.append(deviceDetails.getFriendlyName());
                            WJLog.v(str2, outline1072.toString());
                            return Single.just(new DeviceDiscoveryAndResult(deviceDiscoveryEvent, null));
                        }
                        return AutoDiscoveryPresenter.this.mFFSArcusSyncCoordinator.getFFSArcusSettings().flatMap(new Function<FFSArcusSettings, SingleSource<? extends DeviceDiscoveryAndResult>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.AutoDiscoveryPresenter.4.1
                            @Override // io.reactivex.rxjava3.functions.Function
                            /* renamed from: apply  reason: avoid collision after fix types in other method */
                            public SingleSource<? extends DeviceDiscoveryAndResult> mo10358apply(FFSArcusSettings fFSArcusSettings) throws Exception {
                                if (!fFSArcusSettings.getFFRSettings().getProvisionerSettings().isEnabled()) {
                                    WJLog.d(AutoDiscoveryPresenter.TAG, "FFR disabled according to Arcus. Checking isDistressed().");
                                    if (discoveredDevice.isDistressed()) {
                                        WJLog.i(AutoDiscoveryPresenter.TAG, "FFR has been disabled for discovered device");
                                        return Single.just(new DeviceDiscoveryAndResult(deviceDiscoveryEvent, null));
                                    }
                                }
                                return AutoDiscoveryPresenter.this.discoverLocalNotificationDevice(deviceDiscoveryEvent);
                            }
                        });
                    }
                    return Single.just(new DeviceDiscoveryAndResult(deviceDiscoveryEvent, null));
                }
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<DeviceDiscoveryAndResult>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.AutoDiscoveryPresenter.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public void accept(DeviceDiscoveryAndResult deviceDiscoveryAndResult) {
                if (deviceDiscoveryAndResult.response == null || deviceDiscoveryAndResult.deviceDiscoveryEvent.getState() != Event.State.SUCCESS) {
                    if (deviceDiscoveryAndResult.deviceDiscoveryEvent.getState() != Event.State.ERROR) {
                        return;
                    }
                    AutoDiscoveryPresenter.this.reportFailure(deviceDiscoveryAndResult.deviceDiscoveryEvent.getError());
                    return;
                }
                AutoDiscoveryPresenter.this.reportDevice(deviceDiscoveryAndResult.deviceDiscoveryEvent, deviceDiscoveryAndResult.response);
            }
        }, new Consumer<Throwable>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.AutoDiscoveryPresenter.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public void accept(Throwable th) throws Exception {
                AutoDiscoveryPresenter.this.reportFailure(th);
            }
        });
        this.mDisposables.add(this.mDeviceDiscoveryStreamSubscription);
        this.mDeviceDiscoveryStream.start(this.mDiscoverySettings);
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.AutoDiscoveryPresenterContract.Action
    public void terminate() {
        stopDiscovery();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.BasePresenter, com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.BasePresenterAction
    public void attachView(@NonNull AutoDiscoveryPresenterContract.View view) {
        super.attachView((AutoDiscoveryPresenter) view);
        this.mDisposables.add(Observable.defer(new Supplier<ObservableSource<String>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.AutoDiscoveryPresenter.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public ObservableSource<String> mo10365get() {
                AutoDiscoveryPresenter.this.handleAttach();
                return Observable.empty();
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).subscribe());
    }

    @VisibleForTesting
    AutoDiscoveryPresenter(Context context, DiscoverySettings discoverySettings, DeviceDiscoveryStream deviceDiscoveryStream, DSSClient dSSClient, ProvisionerClientData provisionerClientData) {
        this.mContext = context;
        this.mDiscoverySettings = discoverySettings;
        this.mDeviceDiscoveryStream = deviceDiscoveryStream;
        this.mDssClient = dSSClient;
        this.mProvisionerClientData = provisionerClientData;
    }
}
