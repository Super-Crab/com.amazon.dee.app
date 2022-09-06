package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.provisioningverification;

import androidx.annotation.NonNull;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.registration.CBLRegistrationDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConfiguration;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConnectionDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiNetwork;
import com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee;
import com.amazon.whisperjoin.deviceprovisioningservice.error.ProvisionableRegistrationError;
import com.amazon.whisperjoin.deviceprovisioningservice.error.ProvisionableWifiNetworkConnectionError;
import com.amazon.whisperjoin.deviceprovisioningservice.metrics.MetricsConstants;
import com.amazon.whisperjoin.deviceprovisioningservice.wifi.CurrentWifiNetworkProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceEventStream;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.result.WJResult;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisionableConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.Event;
import com.amazon.whisperjoin.metrics.MetricsRecorder;
import com.amazon.whisperjoin.metrics.MetricsRecorderProvider;
import com.amazon.whisperjoin.metrics.WhisperJoinMetricSourceName;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import java.util.concurrent.TimeUnit;
/* loaded from: classes13.dex */
public class ProvisioningVerificationUsingDeviceEvents implements ProvisioningVerificationMethod {
    private static final long VERIFICATION_TIMEOUT_MS = TimeUnit.MINUTES.toMillis(4);
    private final CurrentWifiNetworkProvider mCurrentWifiNetworkProvider;
    private final DeviceEventStream mDeviceEventStream;
    private final MetricsRecorderProvider mMetricsRecorderProvider;

    public ProvisioningVerificationUsingDeviceEvents(DeviceEventStream deviceEventStream, MetricsRecorderProvider metricsRecorderProvider, CurrentWifiNetworkProvider currentWifiNetworkProvider) {
        this.mDeviceEventStream = deviceEventStream;
        this.mMetricsRecorderProvider = metricsRecorderProvider;
        this.mCurrentWifiNetworkProvider = currentWifiNetworkProvider;
    }

    private Consumer<WJResult.WifiConnectionStateChange> recordMetricsAboutProvisioneeWifiConnectionState(final WifiConfiguration wifiConfiguration) {
        return new Consumer<WJResult.WifiConnectionStateChange>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.provisioningverification.ProvisioningVerificationUsingDeviceEvents.6
            @Override // io.reactivex.rxjava3.functions.Consumer
            public void accept(WJResult.WifiConnectionStateChange wifiConnectionStateChange) throws Exception {
                WifiConnectionDetails data = wifiConnectionStateChange.getData();
                if (!WifiConnectionDetails.State.CONNECTED.equals(data.getConnectionState())) {
                    return;
                }
                WifiNetwork currentWifiNetwork = ProvisioningVerificationUsingDeviceEvents.this.mCurrentWifiNetworkProvider.getCurrentWifiNetwork();
                MetricsRecorder metricsRecorder = ProvisioningVerificationUsingDeviceEvents.this.mMetricsRecorderProvider.getMetricsRecorder(WhisperJoinMetricSourceName.VERIFY_PROVISIONING_OPERATION);
                double d = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
                if (currentWifiNetwork == null) {
                    metricsRecorder.recordCounter(MetricsConstants.PROVISIONER_NETWORK_USED, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
                    metricsRecorder.close();
                    return;
                }
                boolean z = true;
                boolean z2 = currentWifiNetwork.getSsid().equals(wifiConfiguration.getSsid()) && wifiConfiguration.getSsid().equals(data.getSsid());
                if (!currentWifiNetwork.getKeyManagement().equals(wifiConfiguration.getKeyManagement()) || !wifiConfiguration.getKeyManagement().equals(data.getKeyManagement())) {
                    z = false;
                }
                if (z2 && z) {
                    d = 1.0d;
                }
                metricsRecorder.recordCounter(MetricsConstants.PROVISIONER_NETWORK_USED, d);
                metricsRecorder.close();
            }
        };
    }

    private Completable verifyInternetConnection(WJProvisionee wJProvisionee, final WifiConfiguration wifiConfiguration) {
        return this.mDeviceEventStream.filterResultForDevice(WJResult.WifiConnectionStateChange.class, wJProvisionee).filter(new Predicate<WJResult.WifiConnectionStateChange>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.provisioningverification.ProvisioningVerificationUsingDeviceEvents.2
            @Override // io.reactivex.rxjava3.functions.Predicate
            public boolean test(WJResult.WifiConnectionStateChange wifiConnectionStateChange) throws Exception {
                return !WifiConnectionDetails.State.CONNECTING.equals(wifiConnectionStateChange.getData().getConnectionState());
            }
        }).take(1L).doOnNext(recordMetricsAboutProvisioneeWifiConnectionState(wifiConfiguration)).flatMapCompletable(new Function<WJResult.WifiConnectionStateChange, CompletableSource>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.provisioningverification.ProvisioningVerificationUsingDeviceEvents.1
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public CompletableSource mo10358apply(WJResult.WifiConnectionStateChange wifiConnectionStateChange) throws Exception {
                WifiConnectionDetails data = wifiConnectionStateChange.getData();
                if (WifiConnectionDetails.State.CONNECTED.equals(data.getConnectionState())) {
                    return Completable.complete();
                }
                return Completable.error(new ProvisionableWifiNetworkConnectionError(data, wifiConfiguration));
            }
        });
    }

    private Completable verifyProvisioningDone(WJProvisionee wJProvisionee) {
        return this.mDeviceEventStream.filterResultForDevice(WJResult.ProvisioningDoneStateChange.class, wJProvisionee).take(1L).flatMapCompletable(new Function<WJResult.ProvisioningDoneStateChange, CompletableSource>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.provisioningverification.ProvisioningVerificationUsingDeviceEvents.5
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public CompletableSource mo10358apply(WJResult.ProvisioningDoneStateChange provisioningDoneStateChange) throws Exception {
                if (provisioningDoneStateChange.isState(Event.State.SUCCESS)) {
                    return Completable.complete();
                }
                return Completable.error(provisioningDoneStateChange.getError());
            }
        });
    }

    private Completable verifyRegistration(WJProvisionee wJProvisionee) {
        return this.mDeviceEventStream.filterResultForDevice(WJResult.RegistrationStateChange.class, wJProvisionee).filter(new Predicate<WJResult.RegistrationStateChange>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.provisioningverification.ProvisioningVerificationUsingDeviceEvents.4
            @Override // io.reactivex.rxjava3.functions.Predicate
            public boolean test(WJResult.RegistrationStateChange registrationStateChange) throws Exception {
                return !CBLRegistrationDetails.State.COMPLETING_REGISTRATION.equals(((CBLRegistrationDetails) registrationStateChange.data).getRegistrationState());
            }
        }).take(1L).flatMapCompletable(new Function<WJResult.RegistrationStateChange, CompletableSource>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.provisioningverification.ProvisioningVerificationUsingDeviceEvents.3
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public CompletableSource mo10358apply(WJResult.RegistrationStateChange registrationStateChange) throws Exception {
                CBLRegistrationDetails data = registrationStateChange.getData();
                if (CBLRegistrationDetails.State.REGISTRATION_COMPLETE.equals(data.getRegistrationState())) {
                    return Completable.complete();
                }
                return Completable.error(new ProvisionableRegistrationError(data));
            }
        });
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.provisioningverification.ProvisioningVerificationMethod
    public Completable verify(WJProvisionee wJProvisionee, @NonNull ProvisionableConfiguration provisionableConfiguration) {
        return Completable.ambArray(Completable.mergeArray(verifyInternetConnection(wJProvisionee, provisionableConfiguration.getChosenWifiConfiguration()), verifyRegistration(wJProvisionee), verifyProvisioningDone(wJProvisionee)), verifyProvisioningDone(wJProvisionee)).timeout(VERIFICATION_TIMEOUT_MS, TimeUnit.MILLISECONDS);
    }
}
