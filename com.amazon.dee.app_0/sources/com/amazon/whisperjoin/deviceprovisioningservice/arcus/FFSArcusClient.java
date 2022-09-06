package com.amazon.whisperjoin.deviceprovisioningservice.arcus;

import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.ArcusConstants;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.FFSArcusSettings;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisionerClientData;
import com.amazon.whisperjoin.util.rx.RxLog;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.amazonaws.mobileconnectors.remoteconfiguration.Configuration;
import com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback;
import com.amazonaws.mobileconnectors.remoteconfiguration.RemoteConfigurationManager;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Supplier;
import org.json.JSONException;
/* loaded from: classes13.dex */
public class FFSArcusClient {
    private static final String TAG = "FFSArcusClient";
    private final RemoteConfigurationManager mRemoteConfigurationManager;

    public FFSArcusClient(RemoteConfigurationManager remoteConfigurationManager) {
        this.mRemoteConfigurationManager = remoteConfigurationManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FFSArcusSettings getSettingsFromConfiguration(Configuration configuration) {
        try {
            return FFSArcusSettings.fromJSONObject(configuration.getAsJsonObject());
        } catch (JSONException e) {
            WJLog.d(TAG, "A JSONException occurred parsing Arcus JSONObject, using default", e);
            return new FFSArcusSettings();
        }
    }

    public Single<FFSArcusSyncResult> getStoredConfiguration() {
        return Single.defer(new Supplier<SingleSource<? extends FFSArcusSyncResult>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.arcus.FFSArcusClient.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public SingleSource<? extends FFSArcusSyncResult> mo10365get() throws Exception {
                Configuration openConfiguration = FFSArcusClient.this.mRemoteConfigurationManager.openConfiguration();
                return Single.just(new FFSArcusSyncResult(FFSArcusClient.this.getSettingsFromConfiguration(openConfiguration), false, openConfiguration.getTimestamp().getTime()));
            }
        }).doOnSubscribe(RxLog.doOnSubscribe(TAG, "Getting Stored Configuration"));
    }

    public void initWithProvisionerClientData(ProvisionerClientData provisionerClientData) {
        WJLog.d(TAG, "initWithClientData");
        Attributes openAttributes = this.mRemoteConfigurationManager.openAttributes();
        openAttributes.addAttribute("application", provisionerClientData.getClientAppName());
        openAttributes.addAttribute(ArcusConstants.InputAttribute.APPLICATION_VERSION, provisionerClientData.getClientAppVersion());
        openAttributes.addAttribute(ArcusConstants.InputAttribute.DEVICE_MODEL, provisionerClientData.getDeviceModel());
        openAttributes.addAttribute("firmwareVersion", provisionerClientData.getDeviceFirmwareVersion());
        openAttributes.addAttribute("manufacturer", provisionerClientData.getDeviceManufacturer());
        openAttributes.addAttribute("dsn", provisionerClientData.getDeviceSerialNumber());
        openAttributes.addAttribute("directedId", provisionerClientData.getCustomerEcid());
        openAttributes.addAttribute("marketplace", provisionerClientData.getMarketplace());
        String str = TAG;
        WJLog.d(str, "Configured Attributes: " + openAttributes);
    }

    public Single<FFSArcusSyncResult> syncWithArcus() {
        return Single.create(new SingleOnSubscribe<FFSArcusSyncResult>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.arcus.FFSArcusClient.1
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public void subscribe(final SingleEmitter<FFSArcusSyncResult> singleEmitter) throws Exception {
                FFSArcusClient.this.mRemoteConfigurationManager.sync(new ConfigurationSyncCallback() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.arcus.FFSArcusClient.1.1
                    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback
                    public void onConfigurationModified(Configuration configuration) {
                        WJLog.d(FFSArcusClient.TAG, "onConfigurationModified");
                        if (!singleEmitter.isDisposed()) {
                            singleEmitter.onSuccess(new FFSArcusSyncResult(FFSArcusClient.this.getSettingsFromConfiguration(configuration), true, configuration.getTimestamp().getTime()));
                        }
                    }

                    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback
                    public void onConfigurationUnmodified(Configuration configuration) {
                        WJLog.d(FFSArcusClient.TAG, "onConfigurationUnmodified");
                        if (!singleEmitter.isDisposed()) {
                            singleEmitter.onSuccess(new FFSArcusSyncResult(FFSArcusClient.this.getSettingsFromConfiguration(configuration), false, configuration.getTimestamp().getTime()));
                        }
                    }

                    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback
                    public void onFailure(Exception exc) {
                        WJLog.e(FFSArcusClient.TAG, "onFailure", exc);
                        if (!singleEmitter.isDisposed()) {
                            singleEmitter.onError(exc);
                        }
                    }

                    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback
                    public void onThrottle(long j) {
                        String str = FFSArcusClient.TAG;
                        WJLog.w(str, "onThrottle: " + j);
                        if (!singleEmitter.isDisposed()) {
                            singleEmitter.onError(new ArcusSyncThrottled(j));
                        }
                    }
                });
            }
        });
    }
}
