package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation;

import com.amazon.devicesetupservice.v1.RegistrationDetails;
import com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.configuration.DataMap;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.provisioning.DeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.registration.CBLRegistrationRequest;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConfiguration;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee;
import com.amazon.whisperjoin.deviceprovisioningservice.error.NoConfiguredWifiNetworksException;
import com.amazon.whisperjoin.deviceprovisioningservice.identity.MapAuthenticationProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.metrics.MetricsRecorderSingleTransformer;
import com.amazon.whisperjoin.deviceprovisioningservice.service.CBLGenerationConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisioningServiceConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.Action;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.result.WJResult;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisionableConfiguration;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.ComputeConfigurationDataRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.ComputeConfigurationDataResponse;
import com.amazon.whisperjoin.metrics.MetricsRecorderProvider;
import com.amazon.whisperjoin.metrics.WhisperJoinMetricSourceName;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Function;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes13.dex */
public class ProvisionDeviceOperation extends DeviceOperation<Action.ProvisionDevice> {
    private static final String TAG = "ProvisionDeviceOperation";
    private final DSSClient mDSSClient;
    private final MapAuthenticationProvider mMAPAuthentication;
    private final MetricsRecorderProvider mMetricsRecorderProvider;
    private final ProvisioningServiceConfiguration mProvisioningServiceConfiguration;

    public ProvisionDeviceOperation(MapAuthenticationProvider mapAuthenticationProvider, DSSClient dSSClient, ProvisioningServiceConfiguration provisioningServiceConfiguration, MetricsRecorderProvider metricsRecorderProvider) {
        this.mMAPAuthentication = mapAuthenticationProvider;
        this.mDSSClient = dSSClient;
        this.mProvisioningServiceConfiguration = provisioningServiceConfiguration;
        this.mMetricsRecorderProvider = metricsRecorderProvider;
    }

    private DataMap buildConfiguration(Map<String, String> map, String str) {
        DataMap dataMap = new DataMap();
        dataMap.putStringValue("DSS.ReportUrl", str);
        mergeConfigurationIntoDataMap(map, dataMap);
        return dataMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Single<ProvisionableConfiguration> buildConfigurationAndProvisionDevice(WJProvisionee wJProvisionee, CBLRegistrationRequest cBLRegistrationRequest, ProvisionableConfiguration provisionableConfiguration, ComputeConfigurationDataResponse computeConfigurationDataResponse) {
        DataMap buildConfiguration = buildConfiguration(computeConfigurationDataResponse.getConfiguration(), provisionableConfiguration.getProvisionableReportUrl());
        ProvisionableConfiguration updateConfigurationWithChosenNetwork = getUpdateConfigurationWithChosenNetwork(provisionableConfiguration);
        return runProvisioningOperations(wJProvisionee, buildConfiguration, cBLRegistrationRequest, updateConfigurationWithChosenNetwork.getChosenWifiConfiguration()).andThen(wJProvisionee.completeProvisioning()).andThen(Single.just(updateConfigurationWithChosenNetwork));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Observable<WJResult> emitSuccessResult(WJProvisionee wJProvisionee, ProvisionableConfiguration provisionableConfiguration) {
        return Observable.just(WJResult.ProvisionDevice.success(wJProvisionee, provisionableConfiguration));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Single<CBLRegistrationRequest> getCblRegistrationRequest(CBLGenerationConfiguration cBLGenerationConfiguration) {
        WJLog.d(TAG, "Checking if device registration is based on profile level or account level");
        if (cBLGenerationConfiguration != null && cBLGenerationConfiguration.getUseGivenCidAndPid().booleanValue()) {
            return this.mMAPAuthentication.generatePreAuthLinkCodeWithPid(cBLGenerationConfiguration).compose(new MetricsRecorderSingleTransformer(this.mMetricsRecorderProvider, WhisperJoinMetricSourceName.CBL_REGISTRATION_PROFILE_BASED));
        }
        return this.mMAPAuthentication.generatePreAuthLinkCode().compose(new MetricsRecorderSingleTransformer(this.mMetricsRecorderProvider, WhisperJoinMetricSourceName.CBL_REGISTRATION_ACCOUNT_BASED));
    }

    private WifiConfiguration getChosenOrDefaultWifiConfiguration(ProvisionableConfiguration provisionableConfiguration) {
        if (provisionableConfiguration.getChosenWifiConfiguration() != null) {
            return provisionableConfiguration.getChosenWifiConfiguration();
        }
        return provisionableConfiguration.getAvailableWifiNetworks().getPreferredConfiguredNetwork().getWifiConfiguration();
    }

    private ComputeConfigurationDataRequest getComputeConfigurationDataRequest(WJProvisionee wJProvisionee, DeviceDetails deviceDetails, ProvisionableConfiguration provisionableConfiguration) {
        WhisperJoinPeripheralDeviceDetails peripheralDeviceDetails = wJProvisionee.getPeripheralDeviceDetails();
        String clientNonce = peripheralDeviceDetails.getClientNonce();
        com.amazon.devicesetup.common.v1.DeviceDetails deviceDetails2 = new com.amazon.devicesetup.common.v1.DeviceDetails();
        deviceDetails2.setManufacturer(deviceDetails.getManufacturer());
        deviceDetails2.setDeviceSerial(deviceDetails.getDeviceSerialNumber());
        deviceDetails2.setDeviceModel(deviceDetails.getDeviceModelNumber());
        deviceDetails2.setDeviceName(peripheralDeviceDetails.getFriendlyName());
        deviceDetails2.setFirmwareVersion(deviceDetails.getDeviceFirmwareRevision());
        deviceDetails2.setHardwareVersion(deviceDetails.getDeviceHardwareRevision());
        deviceDetails2.setProductIndex(peripheralDeviceDetails.getProductIndex());
        deviceDetails2.setAuthMaterialIndex(peripheralDeviceDetails.getDeviceIdentity());
        deviceDetails2.setSoftwareVersionIndex(peripheralDeviceDetails.getSoftwareVersion());
        return new ComputeConfigurationDataRequest(clientNonce, deviceDetails2, getConfigurationMap(deviceDetails, provisionableConfiguration).getDataMapForRequest());
    }

    private DataMap getConfigurationMap(DeviceDetails deviceDetails, ProvisionableConfiguration provisionableConfiguration) {
        DataMap buildDataMap = this.mProvisioningServiceConfiguration.getLocaleConfiguration().buildDataMap();
        for (Map.Entry<String, String> entry : provisionableConfiguration.getCustomClientConfiguration().entrySet()) {
            if (buildDataMap.containsKey(entry.getKey())) {
                WJLog.w(TAG, String.format(Locale.ENGLISH, "Configuration contains duplicate key: %s, still overwriting.", entry.getKey()));
            }
            buildDataMap.putStringValueOverwrite(entry.getKey(), entry.getValue());
        }
        buildDataMap.putAllValuesOverwrite(deviceDetails.getDeviceCapabilitiesMap());
        return buildDataMap;
    }

    private Single<ProvisionableConfiguration> getProvisionableConfiguration(ComputeConfigurationDataRequest computeConfigurationDataRequest, final WJProvisionee wJProvisionee, final ProvisionableConfiguration provisionableConfiguration) {
        return this.mDSSClient.computeConfigurationData(computeConfigurationDataRequest).flatMap(new Function<ComputeConfigurationDataResponse, SingleSource<? extends ProvisionableConfiguration>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.ProvisionDeviceOperation.2
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public SingleSource<? extends ProvisionableConfiguration> mo10358apply(@NonNull final ComputeConfigurationDataResponse computeConfigurationDataResponse) throws Exception {
                WJLog.d(ProvisionDeviceOperation.TAG, "Got compute configuration data response from DSS");
                if (computeConfigurationDataResponse.getRegistrationDetails() == null) {
                    WJLog.i(ProvisionDeviceOperation.TAG, "Registration Token is null, falling back to MAP token on the device.");
                    return ProvisionDeviceOperation.this.getCblRegistrationRequest(ProvisionDeviceOperation.this.mProvisioningServiceConfiguration.getCblGenerationConfiguration()).flatMap(new Function<CBLRegistrationRequest, SingleSource<? extends ProvisionableConfiguration>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.ProvisionDeviceOperation.2.1
                        @Override // io.reactivex.rxjava3.functions.Function
                        /* renamed from: apply  reason: avoid collision after fix types in other method */
                        public SingleSource<? extends ProvisionableConfiguration> mo10358apply(@NonNull CBLRegistrationRequest cBLRegistrationRequest) throws Exception {
                            AnonymousClass2 anonymousClass2 = AnonymousClass2.this;
                            return ProvisionDeviceOperation.this.buildConfigurationAndProvisionDevice(wJProvisionee, cBLRegistrationRequest, provisionableConfiguration, computeConfigurationDataResponse);
                        }
                    });
                }
                RegistrationDetails registrationDetails = computeConfigurationDataResponse.getRegistrationDetails();
                return ProvisionDeviceOperation.this.buildConfigurationAndProvisionDevice(wJProvisionee, new CBLRegistrationRequest(registrationDetails.getRegistrationToken(), registrationDetails.getExpiresAt()), provisionableConfiguration, computeConfigurationDataResponse);
            }
        });
    }

    private ProvisionableConfiguration getUpdateConfigurationWithChosenNetwork(ProvisionableConfiguration provisionableConfiguration) {
        return new ProvisionableConfiguration.Builder(provisionableConfiguration).setChosenWifiConfiguration(getChosenOrDefaultWifiConfiguration(provisionableConfiguration)).create();
    }

    private boolean hasConfiguredNetworkToUse(ProvisionableConfiguration provisionableConfiguration) {
        return provisionableConfiguration.getChosenWifiConfiguration() != null || (provisionableConfiguration.getAvailableWifiNetworks().getConfiguredNetworks().isEmpty() ^ true);
    }

    private void mergeConfigurationIntoDataMap(Map<String, String> map, DataMap dataMap) {
        WJLog.d(TAG, "Merging configuration into data map");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (dataMap.containsKey(entry.getKey())) {
                WJLog.d(TAG, String.format(Locale.ENGLISH, "Configuration contains duplicate key: %s, dropping value", entry.getKey()));
            } else {
                WJLog.d(TAG, String.format(Locale.ENGLISH, "Adding configuration data entry key: %s, value: %s", entry.getKey(), entry.getValue()));
                dataMap.putStringValue(entry.getKey(), entry.getValue());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Single<ProvisionableConfiguration> provisionDevice(WJProvisionee wJProvisionee, ProvisionableConfiguration provisionableConfiguration) {
        if (!hasConfiguredNetworkToUse(provisionableConfiguration)) {
            return Single.error(new NoConfiguredWifiNetworksException());
        }
        if (wJProvisionee.getPeripheralDeviceDetails().isDistressed()) {
            return provisionDistressDevice(wJProvisionee, provisionableConfiguration, provisionableConfiguration.getDeviceDetails());
        }
        return provisionOobeDevice(wJProvisionee, provisionableConfiguration, provisionableConfiguration.getDeviceDetails());
    }

    private Single<ProvisionableConfiguration> provisionDistressDevice(WJProvisionee wJProvisionee, ProvisionableConfiguration provisionableConfiguration, DeviceDetails deviceDetails) {
        WJLog.d(TAG, "Provisioning a Distress Device");
        ProvisionableConfiguration updateConfigurationWithChosenNetwork = getUpdateConfigurationWithChosenNetwork(provisionableConfiguration);
        DataMap configurationMap = getConfigurationMap(deviceDetails, updateConfigurationWithChosenNetwork);
        configurationMap.putStringValue("DSS.ReportUrl", updateConfigurationWithChosenNetwork.getProvisionableReportUrl());
        return wJProvisionee.saveWifiConfiguration(updateConfigurationWithChosenNetwork.getChosenWifiConfiguration()).andThen(wJProvisionee.saveConfigurationMap(configurationMap)).andThen(wJProvisionee.completeProvisioning()).andThen(Single.just(updateConfigurationWithChosenNetwork));
    }

    private Single<ProvisionableConfiguration> provisionOobeDevice(WJProvisionee wJProvisionee, ProvisionableConfiguration provisionableConfiguration, DeviceDetails deviceDetails) {
        WJLog.d(TAG, "Provisioning a Generic Device");
        return getProvisionableConfiguration(getComputeConfigurationDataRequest(wJProvisionee, deviceDetails, provisionableConfiguration), wJProvisionee, provisionableConfiguration);
    }

    private Completable runProvisioningOperations(WJProvisionee wJProvisionee, DataMap dataMap, CBLRegistrationRequest cBLRegistrationRequest, WifiConfiguration wifiConfiguration) {
        return Completable.concatArray(wJProvisionee.saveConfigurationMap(dataMap), wJProvisionee.saveWifiConfiguration(wifiConfiguration), wJProvisionee.setRegistrationToken(cBLRegistrationRequest));
    }

    @Override // io.reactivex.rxjava3.core.ObservableTransformer
    public ObservableSource<WJResult> apply(@NonNull Observable<Action.ProvisionDevice> observable) {
        return observable.flatMap(new Function<Action.ProvisionDevice, ObservableSource<WJResult>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.ProvisionDeviceOperation.1
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public ObservableSource<WJResult> mo10358apply(@NonNull Action.ProvisionDevice provisionDevice) throws Exception {
                final WJProvisionee wJProvisionee = provisionDevice.getWJProvisionee();
                return ProvisionDeviceOperation.this.provisionDevice(wJProvisionee, provisionDevice.getData()).compose(new MetricsRecorderSingleTransformer(ProvisionDeviceOperation.this.mMetricsRecorderProvider, WhisperJoinMetricSourceName.PROVISION_DEVICE_OPERATION)).flatMapObservable(new Function<ProvisionableConfiguration, ObservableSource<? extends WJResult>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.ProvisionDeviceOperation.1.2
                    @Override // io.reactivex.rxjava3.functions.Function
                    /* renamed from: apply  reason: avoid collision after fix types in other method */
                    public ObservableSource<? extends WJResult> mo10358apply(@NonNull ProvisionableConfiguration provisionableConfiguration) throws Exception {
                        return ProvisionDeviceOperation.this.emitSuccessResult(wJProvisionee, provisionableConfiguration);
                    }
                }).onErrorReturn(new Function<Throwable, WJResult>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.ProvisionDeviceOperation.1.1
                    @Override // io.reactivex.rxjava3.functions.Function
                    /* renamed from: apply  reason: avoid collision after fix types in other method */
                    public WJResult mo10358apply(Throwable th) throws Exception {
                        WJLog.e(ProvisionDeviceOperation.TAG, "Error while provisioning device", th);
                        return WJResult.ProvisionDevice.error(wJProvisionee, th);
                    }
                }).startWith(Observable.just(WJResult.ProvisionDevice.inProgress(wJProvisionee)));
            }
        });
    }
}
