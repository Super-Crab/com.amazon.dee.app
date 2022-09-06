package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation;

import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.configuration.DataMap;
import com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee;
import com.amazon.whisperjoin.deviceprovisioningservice.error.ProvisioningVerificationTimeoutException;
import com.amazon.whisperjoin.deviceprovisioningservice.metrics.MetricsRecorderCompletableTransformer;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.Action;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.provisioningverification.ProvisioningVerificationMethod;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.wifi.SaveWifiNetworkThroughDSS;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.result.WJResult;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisionableConfiguration;
import com.amazon.whisperjoin.metrics.MetricsRecorderProvider;
import com.amazon.whisperjoin.metrics.WhisperJoinMetricSourceName;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
import java.util.concurrent.TimeoutException;
/* loaded from: classes13.dex */
public class VerifyProvisioningOperation extends DeviceOperation<Action.VerifyProvisioning> {
    private static final String TAG = "VerifyProvisioningOperation";
    private final ProvisioningVerificationMethod mDashButtonVerificationMethod;
    private final ProvisioningVerificationMethod mDefaultVerificationMethod;
    private final MetricsRecorderProvider mMetricsRecorderProvider;
    private final SaveWifiNetworkThroughDSS mSaveWifiNetworkThroughDSS;

    public VerifyProvisioningOperation(ProvisioningVerificationMethod provisioningVerificationMethod, ProvisioningVerificationMethod provisioningVerificationMethod2, MetricsRecorderProvider metricsRecorderProvider, SaveWifiNetworkThroughDSS saveWifiNetworkThroughDSS) {
        this.mDefaultVerificationMethod = provisioningVerificationMethod;
        this.mDashButtonVerificationMethod = provisioningVerificationMethod2;
        this.mMetricsRecorderProvider = metricsRecorderProvider;
        this.mSaveWifiNetworkThroughDSS = saveWifiNetworkThroughDSS;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Observable<WJResult> emitSuccessResult(WJProvisionee wJProvisionee, ProvisionableConfiguration provisionableConfiguration) {
        return Observable.just(WJResult.VerifyProvisioning.success(wJProvisionee, provisionableConfiguration));
    }

    private boolean isCoexistenceSupported(ProvisionableConfiguration provisionableConfiguration) {
        DataMap deviceCapabilitiesMap = provisionableConfiguration.getDeviceDetails().getDeviceCapabilitiesMap();
        if (deviceCapabilitiesMap.containsKey("Connectivity.SupportsCoexistBleWifi")) {
            if (deviceCapabilitiesMap.getBooleanValue("Connectivity.SupportsCoexistBleWifi") != null) {
                if (deviceCapabilitiesMap.getBooleanValue("Connectivity.SupportsCoexistBleWifi").booleanValue()) {
                    return Boolean.TRUE.booleanValue();
                }
            } else if (deviceCapabilitiesMap.getStringValue("Connectivity.SupportsCoexistBleWifi") != null && deviceCapabilitiesMap.getStringValue("Connectivity.SupportsCoexistBleWifi").equalsIgnoreCase("TRUE")) {
                return Boolean.TRUE.booleanValue();
            }
        }
        return Boolean.FALSE.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Completable saveNetworkToWifiLockerIfNeeded(WJProvisionee wJProvisionee, ProvisionableConfiguration provisionableConfiguration) {
        if (!provisionableConfiguration.saveWifiNetworkToLocker()) {
            return Completable.complete();
        }
        return this.mSaveWifiNetworkThroughDSS.saveWifiNetwork(wJProvisionee, provisionableConfiguration.getChosenWifiConfiguration(), provisionableConfiguration.getSessionToken()).onErrorComplete();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Completable verifyProvisioning(WJProvisionee wJProvisionee, ProvisionableConfiguration provisionableConfiguration) {
        if (!isCoexistenceSupported(provisionableConfiguration)) {
            return this.mDashButtonVerificationMethod.verify(wJProvisionee, provisionableConfiguration);
        }
        return this.mDefaultVerificationMethod.verify(wJProvisionee, provisionableConfiguration);
    }

    @Override // io.reactivex.rxjava3.core.ObservableTransformer
    public ObservableSource<WJResult> apply(@NonNull Observable<Action.VerifyProvisioning> observable) {
        return observable.flatMap(new Function<Action.VerifyProvisioning, ObservableSource<WJResult>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.VerifyProvisioningOperation.1
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public ObservableSource<WJResult> mo10358apply(@NonNull Action.VerifyProvisioning verifyProvisioning) throws Exception {
                final WJProvisionee wJProvisionee = verifyProvisioning.getWJProvisionee();
                ProvisionableConfiguration data = verifyProvisioning.getData();
                return VerifyProvisioningOperation.this.verifyProvisioning(wJProvisionee, data).onErrorResumeNext(new Function<Throwable, CompletableSource>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.VerifyProvisioningOperation.1.2
                    @Override // io.reactivex.rxjava3.functions.Function
                    /* renamed from: apply  reason: avoid collision after fix types in other method */
                    public CompletableSource mo10358apply(@NonNull Throwable th) throws Exception {
                        if (th instanceof TimeoutException) {
                            return Completable.error(new ProvisioningVerificationTimeoutException());
                        }
                        return Completable.error(th);
                    }
                }).compose(new MetricsRecorderCompletableTransformer(VerifyProvisioningOperation.this.mMetricsRecorderProvider, WhisperJoinMetricSourceName.VERIFY_PROVISIONING_OPERATION)).andThen(VerifyProvisioningOperation.this.saveNetworkToWifiLockerIfNeeded(wJProvisionee, data)).andThen(VerifyProvisioningOperation.this.emitSuccessResult(wJProvisionee, data)).onErrorReturn(new Function<Throwable, WJResult>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.VerifyProvisioningOperation.1.1
                    @Override // io.reactivex.rxjava3.functions.Function
                    /* renamed from: apply  reason: avoid collision after fix types in other method */
                    public WJResult mo10358apply(@NonNull Throwable th) throws Exception {
                        return WJResult.VerifyProvisioning.error(wJProvisionee, th);
                    }
                }).startWithItem(WJResult.VerifyProvisioning.inProgress(wJProvisionee));
            }
        });
    }
}
