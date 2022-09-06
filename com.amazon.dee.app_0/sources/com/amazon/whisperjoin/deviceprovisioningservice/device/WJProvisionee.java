package com.amazon.whisperjoin.deviceprovisioningservice.device;

import com.amazon.whisperbridge.ProvisioningStatus;
import com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.configuration.DataMap;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.configuration.DeviceConnectionConfiguration;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.provisioning.DeviceDetailsProtoData;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.registration.CBLRegistrationDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.registration.CBLRegistrationRequest;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConfiguration;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConnectionDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiScanResultCollection;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.device.Provisionee;
import com.amazon.whisperjoin.deviceprovisioningservice.error.DeviceError;
import com.amazon.whisperjoin.deviceprovisioningservice.error.ProvisioningNotGranted;
import com.amazon.whisperjoin.deviceprovisioningservice.error.UnableToEstablishConnectionException;
import com.amazon.whisperjoin.deviceprovisioningservice.metrics.MetricsRecorderCompletableTransformer;
import com.amazon.whisperjoin.deviceprovisioningservice.metrics.MetricsRecorderSingleTransformer;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorUtils;
import com.amazon.whisperjoin.metrics.MetricsRecorderProvider;
import com.amazon.whisperjoin.metrics.WhisperJoinMetricSourceName;
import com.amazon.whisperjoin.provisionerSDK.devices.AmazonPeripheralDevice;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.Locale;
import org.apache.commons.lang.builder.HashCodeBuilder;
/* loaded from: classes13.dex */
public class WJProvisionee extends Provisionee {
    private static final int NETWORK_SCAN_COUNT = 40;
    private static final String TAG = "WJProvisionee";
    private final AmazonPeripheralDevice mDevice;
    private MetricsRecorderProvider mMetricsRecorderProvider;
    private final Scheduler mSubcribeOnScheduler = Schedulers.io();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static abstract class MapErrorToDeviceError<T> implements Function<Throwable, T> {
        private Provisionee.DeviceOperation mOperation;

        /* loaded from: classes13.dex */
        private static class Completable extends MapErrorToDeviceError<io.reactivex.rxjava3.core.Completable> {
            public Completable(Provisionee.DeviceOperation deviceOperation) {
                super(deviceOperation);
            }

            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public io.reactivex.rxjava3.core.Completable mo10358apply(Throwable th) throws Exception {
                return io.reactivex.rxjava3.core.Completable.error(getDeviceError(th));
            }
        }

        /* loaded from: classes13.dex */
        private static class Observable<T> extends MapErrorToDeviceError<io.reactivex.rxjava3.core.Observable<? extends T>> {
            public Observable(Provisionee.DeviceOperation deviceOperation) {
                super(deviceOperation);
            }

            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public io.reactivex.rxjava3.core.Observable<? extends T> mo10358apply(@NonNull Throwable th) throws Exception {
                return io.reactivex.rxjava3.core.Observable.error(getDeviceError(th));
            }
        }

        /* loaded from: classes13.dex */
        private static class Single<T> extends MapErrorToDeviceError<SingleSource<? extends T>> {
            public Single(Provisionee.DeviceOperation deviceOperation) {
                super(deviceOperation);
            }

            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public SingleSource<? extends T> mo10358apply(Throwable th) throws Exception {
                return io.reactivex.rxjava3.core.Single.error(getDeviceError(th));
            }
        }

        public MapErrorToDeviceError(Provisionee.DeviceOperation deviceOperation) {
            this.mOperation = deviceOperation;
        }

        protected DeviceError getDeviceError(Throwable th) {
            Throwable rootCause = WJErrorUtils.getRootCause(th);
            if (rootCause == null) {
                return new DeviceError(this.mOperation.getOpName());
            }
            return new DeviceError(rootCause, this.mOperation.getOpName());
        }
    }

    public WJProvisionee(AmazonPeripheralDevice amazonPeripheralDevice, MetricsRecorderProvider metricsRecorderProvider) {
        this.mDevice = amazonPeripheralDevice;
        this.mMetricsRecorderProvider = metricsRecorderProvider;
    }

    private void logStart(Provisionee.DeviceOperation deviceOperation) {
        String str = TAG;
        WJLog.d(str, "Call - " + deviceOperation);
    }

    public Completable completeProvisioning() {
        logStart(Provisionee.DeviceOperation.COMPLETE_PROVISIONING);
        return Completable.defer(new Supplier<CompletableSource>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee.6
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public CompletableSource mo10365get() throws Exception {
                return Completable.fromFuture(WJProvisionee.this.mDevice.completeProvisioning());
            }
        }).subscribeOn(this.mSubcribeOnScheduler).compose(new MetricsRecorderCompletableTransformer(this.mMetricsRecorderProvider, WhisperJoinMetricSourceName.COMPLETE_PROVISIONING)).onErrorResumeNext(new MapErrorToDeviceError.Completable(Provisionee.DeviceOperation.COMPLETE_PROVISIONING));
    }

    public Completable connectToDevice(final DeviceConnectionConfiguration deviceConnectionConfiguration) {
        logStart(Provisionee.DeviceOperation.CONNECT);
        return Completable.defer(new Supplier<CompletableSource>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public CompletableSource mo10365get() throws Exception {
                return Completable.fromFuture(WJProvisionee.this.mDevice.connectToPeripheral(deviceConnectionConfiguration));
            }
        }).onErrorResumeNext(new Function<Throwable, CompletableSource>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee.2
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public CompletableSource mo10358apply(@NonNull Throwable th) throws Exception {
                return Completable.error(new UnableToEstablishConnectionException(WJErrorUtils.getRootCause(th)));
            }
        }).subscribeOn(this.mSubcribeOnScheduler).compose(new MetricsRecorderCompletableTransformer(this.mMetricsRecorderProvider, WhisperJoinMetricSourceName.CONNECT_TO_DEVICE)).onErrorResumeNext(new MapErrorToDeviceError.Completable(Provisionee.DeviceOperation.CONNECT));
    }

    public void disconnect() {
        this.mDevice.close();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || WJProvisionee.class != obj.getClass()) {
            return false;
        }
        WhisperJoinPeripheralDeviceDetails peripheralDeviceDetails = this.mDevice.getPeripheralDeviceDetails();
        WhisperJoinPeripheralDeviceDetails peripheralDeviceDetails2 = ((WJProvisionee) obj).getPeripheralDeviceDetails();
        return peripheralDeviceDetails.getDeviceIdentity().equals(peripheralDeviceDetails2.getDeviceIdentity()) && peripheralDeviceDetails.getClientNonce().equals(peripheralDeviceDetails2.getClientNonce());
    }

    public Single<WifiConnectionDetails> getConnectionInformation() {
        logStart(Provisionee.DeviceOperation.GET_CONNECTION_INFO);
        return Single.defer(new Supplier<SingleSource<WifiConnectionDetails>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee.12
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public SingleSource<WifiConnectionDetails> mo10365get() throws Exception {
                return Single.fromFuture(WJProvisionee.this.mDevice.getConnectionInformation());
            }
        }).subscribeOn(this.mSubcribeOnScheduler).compose(new MetricsRecorderSingleTransformer(this.mMetricsRecorderProvider, WhisperJoinMetricSourceName.GET_WIFI_CONNECTION_DETAIL)).onErrorResumeNext(new MapErrorToDeviceError.Single(Provisionee.DeviceOperation.GET_CONNECTION_INFO));
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.device.Provisionee
    public Single<DeviceDetailsProtoData> getDeviceDetails() {
        logStart(Provisionee.DeviceOperation.GET_DEVICE_DETAILS);
        return Single.defer(new Supplier<SingleSource<DeviceDetailsProtoData>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public SingleSource<DeviceDetailsProtoData> mo10365get() throws Exception {
                return Single.fromFuture(WJProvisionee.this.mDevice.getDeviceDetails());
            }
        }).subscribeOn(this.mSubcribeOnScheduler).compose(new MetricsRecorderSingleTransformer(this.mMetricsRecorderProvider, WhisperJoinMetricSourceName.GET_PROVISIONABLE_DEVICE_DETAILS)).onErrorResumeNext(new MapErrorToDeviceError.Single(Provisionee.DeviceOperation.GET_DEVICE_DETAILS));
    }

    public WhisperJoinPeripheralDeviceDetails getPeripheralDeviceDetails() {
        return this.mDevice.getPeripheralDeviceDetails();
    }

    public Single<CBLRegistrationDetails> getRegistrationStatus() {
        logStart(Provisionee.DeviceOperation.GET_REGISTRATION_STATUS);
        return Single.defer(new Supplier<SingleSource<CBLRegistrationDetails>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee.13
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public SingleSource<CBLRegistrationDetails> mo10365get() throws Exception {
                return Single.fromFuture(WJProvisionee.this.mDevice.getRegistrationTokenStatus());
            }
        }).subscribeOn(this.mSubcribeOnScheduler).compose(new MetricsRecorderSingleTransformer(this.mMetricsRecorderProvider, WhisperJoinMetricSourceName.GET_BLE_REGISTRATION_STATUS)).onErrorResumeNext(new MapErrorToDeviceError.Single(Provisionee.DeviceOperation.GET_REGISTRATION_STATUS));
    }

    public Single<WifiScanResultCollection> getVisibleNetworks() {
        logStart(Provisionee.DeviceOperation.GET_VISIBLE_NETWORKS);
        return Single.defer(new Supplier<SingleSource<WifiScanResultCollection>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee.7
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public SingleSource<WifiScanResultCollection> mo10365get() throws Exception {
                return Single.fromFuture(WJProvisionee.this.mDevice.getVisibleNetworks(40));
            }
        }).subscribeOn(this.mSubcribeOnScheduler).compose(new MetricsRecorderSingleTransformer(this.mMetricsRecorderProvider, WhisperJoinMetricSourceName.GET_VISIBLE_NETWORKS)).onErrorResumeNext(new MapErrorToDeviceError.Single(Provisionee.DeviceOperation.GET_VISIBLE_NETWORKS));
    }

    public int hashCode() {
        WhisperJoinPeripheralDeviceDetails peripheralDeviceDetails = this.mDevice.getPeripheralDeviceDetails();
        return new HashCodeBuilder(17, 37).append(peripheralDeviceDetails.getDeviceIdentity()).append(peripheralDeviceDetails.getClientNonce()).toHashCode();
    }

    public Completable initiateWifiScan() {
        logStart(Provisionee.DeviceOperation.INITIATE_WIFI_SCAN);
        return Completable.defer(new Supplier<CompletableSource>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee.8
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public CompletableSource mo10365get() throws Exception {
                return Completable.fromFuture(WJProvisionee.this.mDevice.startNetworkScan());
            }
        }).subscribeOn(this.mSubcribeOnScheduler).compose(new MetricsRecorderCompletableTransformer(this.mMetricsRecorderProvider, WhisperJoinMetricSourceName.INITIATE_WIFI_SCAN)).onErrorResumeNext(new MapErrorToDeviceError.Completable(Provisionee.DeviceOperation.INITIATE_WIFI_SCAN));
    }

    public Completable saveConfigurationMap(final DataMap dataMap) {
        logStart(Provisionee.DeviceOperation.SAVE_CONFIG_MAP);
        return Completable.defer(new Supplier<CompletableSource>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee.11
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public CompletableSource mo10365get() throws Exception {
                return Completable.fromFuture(WJProvisionee.this.mDevice.addConfiguration(dataMap));
            }
        }).subscribeOn(this.mSubcribeOnScheduler).compose(new MetricsRecorderCompletableTransformer(this.mMetricsRecorderProvider, WhisperJoinMetricSourceName.SAVE_CONFIGURATION_MAP)).onErrorResumeNext(new MapErrorToDeviceError.Completable(Provisionee.DeviceOperation.SAVE_CONFIG_MAP));
    }

    public Completable saveWifiConfiguration(final WifiConfiguration wifiConfiguration) {
        logStart(Provisionee.DeviceOperation.SAVE_NETWORK);
        return Completable.defer(new Supplier<CompletableSource>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee.9
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public CompletableSource mo10365get() throws Exception {
                return Completable.fromFuture(WJProvisionee.this.mDevice.saveNetwork(wifiConfiguration));
            }
        }).subscribeOn(this.mSubcribeOnScheduler).compose(new MetricsRecorderCompletableTransformer(this.mMetricsRecorderProvider, WhisperJoinMetricSourceName.SAVE_WIFI_CONFIGURATION)).onErrorResumeNext(new MapErrorToDeviceError.Completable(Provisionee.DeviceOperation.SAVE_NETWORK));
    }

    public Completable setRegistrationToken(final CBLRegistrationRequest cBLRegistrationRequest) {
        logStart(Provisionee.DeviceOperation.SET_REGISTRATION_TOKEN);
        return Completable.defer(new Supplier<CompletableSource>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee.10
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public CompletableSource mo10365get() throws Exception {
                return Completable.fromFuture(WJProvisionee.this.mDevice.setRegistrationToken(cBLRegistrationRequest));
            }
        }).subscribeOn(this.mSubcribeOnScheduler).compose(new MetricsRecorderCompletableTransformer(this.mMetricsRecorderProvider, WhisperJoinMetricSourceName.SET_REGISTRATION_TOKEN)).onErrorResumeNext(new MapErrorToDeviceError.Completable(Provisionee.DeviceOperation.SET_REGISTRATION_TOKEN));
    }

    public Completable startProvisioning() {
        logStart(Provisionee.DeviceOperation.START_PROVISIONING);
        return Single.defer(new Supplier<SingleSource<ProvisioningStatus>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee.5
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public SingleSource<ProvisioningStatus> mo10365get() throws Exception {
                return Single.fromFuture(WJProvisionee.this.mDevice.startProvisioning());
            }
        }).subscribeOn(this.mSubcribeOnScheduler).flatMapCompletable(new Function<ProvisioningStatus, Completable>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee.4
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public Completable mo10358apply(ProvisioningStatus provisioningStatus) throws Exception {
                return provisioningStatus.isProvisioner() ? Completable.complete() : Completable.error(new ProvisioningNotGranted());
            }
        }).compose(new MetricsRecorderCompletableTransformer(this.mMetricsRecorderProvider, WhisperJoinMetricSourceName.START_PROVISIONING)).onErrorResumeNext(new MapErrorToDeviceError.Completable(Provisionee.DeviceOperation.START_PROVISIONING));
    }

    public String toString() {
        return String.format(Locale.ENGLISH, "WJProvisionee{mDevice.FriendlyName=%s}", this.mDevice.getPeripheralDeviceDetails().getFriendlyName());
    }
}
