package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation;

import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.provisioning.DeviceDetailsProtoData;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiScanResultCollection;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee;
import com.amazon.whisperjoin.deviceprovisioningservice.error.DeviceError;
import com.amazon.whisperjoin.deviceprovisioningservice.error.WiFiSyncAuthTokenFailedValidationException;
import com.amazon.whisperjoin.deviceprovisioningservice.error.WiFiSyncAuthTokenNotFoundException;
import com.amazon.whisperjoin.deviceprovisioningservice.metrics.MetricsConstants;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.Action;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceEventStream;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.wifi.GetAvailableWifiNetworksFromDSS;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.result.WJResult;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.AvailableWifiNetworks;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.GetProvisioningDetailsOptions;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioningDetails;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.ValidateWiFiSyncAuthTokenRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.ValidateWiFiSyncAuthTokenResponse;
import com.amazon.whisperjoin.metrics.MetricsRecorder;
import com.amazon.whisperjoin.metrics.MetricsRecorderProvider;
import com.amazon.whisperjoin.metrics.WhisperJoinMetricSourceName;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.commons.lang.StringUtils;
/* loaded from: classes13.dex */
public class GetProvisioningDetailsOperation extends DeviceOperation<Action.GetProvisioningDetails> {
    private final DSSClient mDSSClient;
    private final DeviceEventStream mDeviceEventStream;
    private final GetAvailableWifiNetworksFromDSS mGetAvailableWifiNetworksFromDSS;
    private final MetricsRecorderProvider mMetricsRecorderProvider;
    private static final long WAITING_FOR_RESCAN_TIMEOUT_MS = TimeUnit.SECONDS.toMillis(20);
    private static final String TAG = GetProvisioningDetailsOperation.class.getSimpleName();

    public GetProvisioningDetailsOperation(DeviceEventStream deviceEventStream, GetAvailableWifiNetworksFromDSS getAvailableWifiNetworksFromDSS, MetricsRecorderProvider metricsRecorderProvider, DSSClient dSSClient) {
        this.mDeviceEventStream = deviceEventStream;
        this.mDSSClient = dSSClient;
        this.mGetAvailableWifiNetworksFromDSS = getAvailableWifiNetworksFromDSS;
        this.mMetricsRecorderProvider = metricsRecorderProvider;
    }

    private Single<WifiScanResultCollection> forceWifiRescanAndGetResults(final WJProvisionee wJProvisionee) {
        WJLog.d(TAG, "forceWifiRescanAndGetResults");
        return wJProvisionee.initiateWifiScan().andThen(this.mDeviceEventStream.filterResultForDevice(WJResult.NetworkScanComplete.class, wJProvisionee)).take(1L).timeout(WAITING_FOR_RESCAN_TIMEOUT_MS, TimeUnit.MILLISECONDS).firstOrError().flatMap(new Function<WJResult.NetworkScanComplete, SingleSource<? extends WifiScanResultCollection>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.GetProvisioningDetailsOperation.8
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public SingleSource<? extends WifiScanResultCollection> mo10358apply(WJResult.NetworkScanComplete networkScanComplete) throws Exception {
                WJLog.d(GetProvisioningDetailsOperation.TAG, "Received notification that network scan was complete, get the new list of visible networks");
                return wJProvisionee.getVisibleNetworks();
            }
        }).onErrorResumeNext(new Function<Throwable, SingleSource<? extends WifiScanResultCollection>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.GetProvisioningDetailsOperation.7
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public SingleSource<? extends WifiScanResultCollection> mo10358apply(Throwable th) throws Exception {
                if ((th instanceof DeviceError) || (th instanceof TimeoutException)) {
                    WJLog.d(GetProvisioningDetailsOperation.TAG, "Something went wrong, just get visible networks again", th);
                    return wJProvisionee.getVisibleNetworks();
                }
                return Single.error(th);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Observable<WJResult> getProvisioningDetails(final WJProvisionee wJProvisionee, final ProvisioningDetails provisioningDetails, GetProvisioningDetailsOptions getProvisioningDetailsOptions) {
        ArrayList arrayList = new ArrayList();
        if (getProvisioningDetailsOptions.shouldRetrieveDeviceDetails() || wJProvisionee.getPeripheralDeviceDetails().isDistressed()) {
            arrayList.add(setDeviceDetails(wJProvisionee, provisioningDetails, getProvisioningDetailsOptions));
        }
        if (getProvisioningDetailsOptions.shouldRetrieveAvailableNetworks()) {
            arrayList.add(setAvailableNetworks(wJProvisionee, provisioningDetails, getProvisioningDetailsOptions));
        }
        return Completable.concat(arrayList).andThen(Observable.create(new ObservableOnSubscribe<WJResult>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.GetProvisioningDetailsOperation.2
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public void subscribe(@NonNull ObservableEmitter<WJResult> observableEmitter) throws Exception {
                if (observableEmitter.isDisposed()) {
                    return;
                }
                observableEmitter.onNext(WJResult.GetProvisioningDetails.success(wJProvisionee, provisioningDetails));
            }
        }));
    }

    private Single<WifiScanResultCollection> getVisibleNetworksOperation(WJProvisionee wJProvisionee, boolean z) {
        return (z ? forceWifiRescanAndGetResults(wJProvisionee) : wJProvisionee.getVisibleNetworks()).doOnSuccess(recordVisibleNetworkMetrics());
    }

    private Consumer<WifiScanResultCollection> recordVisibleNetworkMetrics() {
        return new Consumer<WifiScanResultCollection>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.GetProvisioningDetailsOperation.9
            @Override // io.reactivex.rxjava3.functions.Consumer
            public void accept(WifiScanResultCollection wifiScanResultCollection) throws Exception {
                MetricsRecorder metricsRecorder = GetProvisioningDetailsOperation.this.mMetricsRecorderProvider.getMetricsRecorder(WhisperJoinMetricSourceName.GET_VISIBLE_NETWORKS);
                metricsRecorder.recordCounter(MetricsConstants.DISCOVERED_NETWORKS_COUNT, wifiScanResultCollection.size());
                metricsRecorder.close();
            }
        };
    }

    private Completable setAvailableNetworks(final WJProvisionee wJProvisionee, final ProvisioningDetails provisioningDetails, final GetProvisioningDetailsOptions getProvisioningDetailsOptions) {
        return getVisibleNetworksOperation(wJProvisionee, getProvisioningDetailsOptions.forceRescan()).flatMap(new Function<WifiScanResultCollection, SingleSource<AvailableWifiNetworks>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.GetProvisioningDetailsOperation.6
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public SingleSource<AvailableWifiNetworks> mo10358apply(WifiScanResultCollection wifiScanResultCollection) throws Exception {
                return GetProvisioningDetailsOperation.this.mGetAvailableWifiNetworksFromDSS.getAvailableWifiNetworks(wJProvisionee, wifiScanResultCollection, provisioningDetails.getDeviceDetails() != null ? provisioningDetails.getDeviceDetails() : getProvisioningDetailsOptions.getDeviceDetails(), getProvisioningDetailsOptions.getSessionToken());
            }
        }).flatMapCompletable(new Function<AvailableWifiNetworks, CompletableSource>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.GetProvisioningDetailsOperation.5
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public CompletableSource mo10358apply(AvailableWifiNetworks availableWifiNetworks) throws Exception {
                provisioningDetails.setAvailableWifiNetworks(availableWifiNetworks);
                return Completable.complete();
            }
        });
    }

    private Completable setDeviceDetails(final WJProvisionee wJProvisionee, final ProvisioningDetails provisioningDetails, final GetProvisioningDetailsOptions getProvisioningDetailsOptions) {
        if (getProvisioningDetailsOptions.getDeviceDetails() != null) {
            provisioningDetails.setDeviceDetails(getProvisioningDetailsOptions.getDeviceDetails());
            return Completable.complete();
        }
        return wJProvisionee.getDeviceDetails().flatMapCompletable(new Function<DeviceDetailsProtoData, CompletableSource>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.GetProvisioningDetailsOperation.3
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public CompletableSource mo10358apply(@NonNull DeviceDetailsProtoData deviceDetailsProtoData) throws Exception {
                provisioningDetails.setDeviceDetails(deviceDetailsProtoData.getDeviceDetails());
                if (wJProvisionee.getPeripheralDeviceDetails().isDistressed()) {
                    return GetProvisioningDetailsOperation.this.validateWiFiSyncAuthToken(wJProvisionee, deviceDetailsProtoData.getNetworkSyncToken(), getProvisioningDetailsOptions.getSessionToken());
                }
                return Completable.complete();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Completable validateWiFiSyncAuthToken(WJProvisionee wJProvisionee, String str, String str2) {
        if (StringUtils.isEmpty(str)) {
            return Completable.error(new WiFiSyncAuthTokenNotFoundException());
        }
        return this.mDSSClient.validateWiFiSyncAuthToken(new ValidateWiFiSyncAuthTokenRequest(str, wJProvisionee.getPeripheralDeviceDetails().getDeviceIdentity(), wJProvisionee.getPeripheralDeviceDetails().getProductIndex(), str2)).flatMapCompletable(new Function<ValidateWiFiSyncAuthTokenResponse, CompletableSource>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.GetProvisioningDetailsOperation.4
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public CompletableSource mo10358apply(@NonNull ValidateWiFiSyncAuthTokenResponse validateWiFiSyncAuthTokenResponse) throws Exception {
                if (!validateWiFiSyncAuthTokenResponse.isValidToken()) {
                    WJLog.i(GetProvisioningDetailsOperation.TAG, "Wi-Fi Sync Auth Token failed validation");
                    return Completable.error(new WiFiSyncAuthTokenFailedValidationException());
                }
                WJLog.i(GetProvisioningDetailsOperation.TAG, "Wi-Fi Sync Auth Token passed validation");
                return Completable.complete();
            }
        });
    }

    @Override // io.reactivex.rxjava3.core.ObservableTransformer
    public ObservableSource<WJResult> apply(@NonNull Observable<Action.GetProvisioningDetails> observable) {
        return observable.flatMap(new Function<Action.GetProvisioningDetails, ObservableSource<WJResult>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.GetProvisioningDetailsOperation.1
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public ObservableSource<WJResult> mo10358apply(@NonNull Action.GetProvisioningDetails getProvisioningDetails) throws Exception {
                final WJProvisionee wJProvisionee = getProvisioningDetails.getWJProvisionee();
                final ProvisioningDetails provisioningDetails = new ProvisioningDetails();
                return GetProvisioningDetailsOperation.this.getProvisioningDetails(wJProvisionee, provisioningDetails, getProvisioningDetails.getData()).onErrorReturn(new Function<Throwable, WJResult>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.GetProvisioningDetailsOperation.1.1
                    @Override // io.reactivex.rxjava3.functions.Function
                    /* renamed from: apply  reason: avoid collision after fix types in other method */
                    public WJResult mo10358apply(@NonNull Throwable th) throws Exception {
                        return WJResult.GetProvisioningDetails.error(wJProvisionee, th, provisioningDetails);
                    }
                }).startWithItem(WJResult.GetProvisioningDetails.inProgress(wJProvisionee));
            }
        });
    }
}
