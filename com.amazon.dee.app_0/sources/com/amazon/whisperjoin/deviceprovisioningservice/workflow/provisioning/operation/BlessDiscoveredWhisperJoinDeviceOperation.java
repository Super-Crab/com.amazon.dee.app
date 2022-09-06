package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation;

import com.amazon.devicesetupservice.reporting.Radio;
import com.amazon.devicesetupservice.v1.ProvisionableInfo;
import com.amazon.devicesetupservice.v1.ProvisionerInfo;
import com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetailsV2;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.device.ProvisioningManagerProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.error.DeviceProvisioningDeferredException;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisionerClientData;
import com.amazon.whisperjoin.deviceprovisioningservice.util.ProvisioneeInfoMessage;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.deferred.DeferredDiscoveryHandler;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.Action;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.result.WJResult;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.DiscoveredProvisionable;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioningMethod;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.TrustMethod;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.DiscoveredDistressedProvisioneeRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.DiscoveredProvisionableDeviceRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.GenericDSSDiscoveryResponse;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import java.util.Collections;
/* loaded from: classes13.dex */
public class BlessDiscoveredWhisperJoinDeviceOperation extends DeviceOperation<Action.BlessDiscoveredDevice> {
    private static final String TAG = "BlessDiscoveredWhisperJoinDeviceOperation";
    private final DSSClient mDSSClient;
    private final DeferredDiscoveryHandler mDeferredDiscoveryHandler;
    private final ProvisionerClientData mProvisionerClientData;
    private final ProvisioningManagerProvider mProvisioningManagerProvider;
    private final ProvisioningMethod mProvisioningMethod;

    public BlessDiscoveredWhisperJoinDeviceOperation(DSSClient dSSClient, ProvisionerClientData provisionerClientData, ProvisioningMethod provisioningMethod, ProvisioningManagerProvider provisioningManagerProvider, DeferredDiscoveryHandler deferredDiscoveryHandler) {
        this.mDSSClient = dSSClient;
        this.mProvisioningMethod = provisioningMethod;
        this.mProvisioningManagerProvider = provisioningManagerProvider;
        this.mProvisionerClientData = provisionerClientData;
        this.mDeferredDiscoveryHandler = deferredDiscoveryHandler;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DiscoveredDistressedProvisioneeRequest getDiscoveredDistressedProvisioneeRequest(@NonNull WhisperJoinPeripheralDeviceDetailsV2 whisperJoinPeripheralDeviceDetailsV2) {
        ProvisionableInfo provisionableInfo = new ProvisionableInfo();
        provisionableInfo.setSoftwareVersionIndex(whisperJoinPeripheralDeviceDetailsV2.getSoftwareVersion());
        provisionableInfo.setDeviceName(whisperJoinPeripheralDeviceDetailsV2.getFriendlyName());
        return new DiscoveredDistressedProvisioneeRequest((this.mProvisioningMethod.equals(ProvisioningMethod.FFS) ? TrustMethod.AUTHENTICATED : TrustMethod.UNAUTHENTICATED).toString(), this.mProvisioningMethod.toString(), whisperJoinPeripheralDeviceDetailsV2.getClientNonce(), whisperJoinPeripheralDeviceDetailsV2.getDistressErrorCode(), provisionableInfo, whisperJoinPeripheralDeviceDetailsV2.getRadios().iterator().next().getSignalStrength(), whisperJoinPeripheralDeviceDetailsV2.getProductIndex(), whisperJoinPeripheralDeviceDetailsV2.getDeviceIdentity(), Radio.BLUETOOTH_LOW_ENERGY, getProvisionerInfo(), whisperJoinPeripheralDeviceDetailsV2.getAdvertisedSdkVersionIndex());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DiscoveredProvisionableDeviceRequest getDiscoveredProvisionableDeviceRequest(@NonNull WhisperJoinPeripheralDeviceDetails whisperJoinPeripheralDeviceDetails) {
        return new DiscoveredProvisionableDeviceRequest.Builder().setProvisionerApplicationName(this.mProvisionerClientData.getClientAppName()).setProvisionerApplicationVersion(this.mProvisionerClientData.getClientAppVersion()).setRssi(whisperJoinPeripheralDeviceDetails.getRadios().iterator().next().getSignalStrength()).setDeviceName(whisperJoinPeripheralDeviceDetails.getFriendlyName()).setNonce(whisperJoinPeripheralDeviceDetails.getClientNonce()).setProductIndex(whisperJoinPeripheralDeviceDetails.getProductIndex()).setAuthMaterialIndex(whisperJoinPeripheralDeviceDetails.getDeviceIdentity()).setSoftwareVersionIndex(whisperJoinPeripheralDeviceDetails.getSoftwareVersion()).setProvisioningMethod(this.mProvisioningMethod.toString()).setTrustMethod((this.mProvisioningMethod.equals(ProvisioningMethod.FFS) ? TrustMethod.AUTHENTICATED : TrustMethod.UNAUTHENTICATED).toString()).setProvisionerInfo(getProvisionerInfo()).setAdvertisedSdkVersionIndex(whisperJoinPeripheralDeviceDetails.getAdvertisedSdkVersionIndex()).createRequest();
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
    public Observable<WJResult> handleDssBlessedProvisionable(WhisperJoinPeripheralDeviceDetails whisperJoinPeripheralDeviceDetails, GenericDSSDiscoveryResponse genericDSSDiscoveryResponse) {
        WJLog.d(TAG, "Blessed by DSS to setup wjProvisionee");
        return Observable.just(WJResult.Discovery.success(Collections.singletonList(new DiscoveredProvisionable(this.mProvisioningManagerProvider.createWJProvsionee(whisperJoinPeripheralDeviceDetails), genericDSSDiscoveryResponse.getProvisionerEventReportUrl(), genericDSSDiscoveryResponse.getProvisionableEventReportUrl(), genericDSSDiscoveryResponse.getSessionToken()))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Observable<WJResult> handleDssDiscoveryDeferment(WhisperJoinPeripheralDeviceDetails whisperJoinPeripheralDeviceDetails, GenericDSSDiscoveryResponse genericDSSDiscoveryResponse) {
        WJLog.d(TAG, "Deferring discovery event based on DSS response");
        this.mDeferredDiscoveryHandler.defer(whisperJoinPeripheralDeviceDetails, genericDSSDiscoveryResponse.getWaitTime());
        return Observable.just(WJResult.Discovery.error(new DeviceProvisioningDeferredException(genericDSSDiscoveryResponse.getWaitTime())));
    }

    @Override // io.reactivex.rxjava3.core.ObservableTransformer
    public ObservableSource<WJResult> apply(Observable<Action.BlessDiscoveredDevice> observable) {
        return observable.flatMap(new Function<Action.BlessDiscoveredDevice, ObservableSource<WJResult>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.BlessDiscoveredWhisperJoinDeviceOperation.1
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public ObservableSource<WJResult> mo10358apply(Action.BlessDiscoveredDevice blessDiscoveredDevice) throws Exception {
                Single<GenericDSSDiscoveryResponse> discoveredProvisionableDevice;
                final WhisperJoinPeripheralDeviceDetails data = blessDiscoveredDevice.getData();
                if (data.isDistressed()) {
                    discoveredProvisionableDevice = BlessDiscoveredWhisperJoinDeviceOperation.this.mDSSClient.discoveredDistressedProvisionee(BlessDiscoveredWhisperJoinDeviceOperation.this.getDiscoveredDistressedProvisioneeRequest((WhisperJoinPeripheralDeviceDetailsV2) data));
                } else {
                    discoveredProvisionableDevice = BlessDiscoveredWhisperJoinDeviceOperation.this.mDSSClient.discoveredProvisionableDevice(BlessDiscoveredWhisperJoinDeviceOperation.this.getDiscoveredProvisionableDeviceRequest(data));
                }
                return discoveredProvisionableDevice.flatMapObservable(new Function<GenericDSSDiscoveryResponse, ObservableSource<? extends WJResult>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.BlessDiscoveredWhisperJoinDeviceOperation.1.2
                    @Override // io.reactivex.rxjava3.functions.Function
                    /* renamed from: apply  reason: avoid collision after fix types in other method */
                    public ObservableSource<? extends WJResult> mo10358apply(@NonNull GenericDSSDiscoveryResponse genericDSSDiscoveryResponse) throws Exception {
                        if (genericDSSDiscoveryResponse.canProceed()) {
                            String str = BlessDiscoveredWhisperJoinDeviceOperation.TAG;
                            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Provisioning Can Proceed - ");
                            outline107.append(ProvisioneeInfoMessage.create(data));
                            WJLog.i(str, outline107.toString());
                            return BlessDiscoveredWhisperJoinDeviceOperation.this.handleDssBlessedProvisionable(data, genericDSSDiscoveryResponse);
                        }
                        String str2 = BlessDiscoveredWhisperJoinDeviceOperation.TAG;
                        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Provisioning Deferred - ");
                        outline1072.append(ProvisioneeInfoMessage.create(data));
                        WJLog.i(str2, outline1072.toString());
                        String str3 = BlessDiscoveredWhisperJoinDeviceOperation.TAG;
                        StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Wait Time: [");
                        outline1073.append(genericDSSDiscoveryResponse.getWaitTime());
                        outline1073.append("]");
                        WJLog.i(str3, outline1073.toString());
                        return BlessDiscoveredWhisperJoinDeviceOperation.this.handleDssDiscoveryDeferment(data, genericDSSDiscoveryResponse);
                    }
                }).onErrorResumeNext(new Function<Throwable, ObservableSource<WJResult>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.BlessDiscoveredWhisperJoinDeviceOperation.1.1
                    @Override // io.reactivex.rxjava3.functions.Function
                    /* renamed from: apply  reason: avoid collision after fix types in other method */
                    public ObservableSource<WJResult> mo10358apply(@NonNull Throwable th) throws Exception {
                        String str = BlessDiscoveredWhisperJoinDeviceOperation.TAG;
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Provisioning Request Rejected - ");
                        outline107.append(ProvisioneeInfoMessage.create(data));
                        WJLog.i(str, outline107.toString());
                        WJLog.d(BlessDiscoveredWhisperJoinDeviceOperation.TAG, "There was an error reporting device to DSS. Not accepting device to be provisioned");
                        return Observable.just(WJResult.Discovery.error(th));
                    }
                }).startWithItem(WJResult.Discovery.inProgress());
            }
        });
    }
}
