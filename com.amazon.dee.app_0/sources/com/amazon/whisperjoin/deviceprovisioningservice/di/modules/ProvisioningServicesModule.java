package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import android.app.job.JobScheduler;
import android.content.Context;
import com.amazon.devicesetupservice.v1.ProvisionerInfo;
import com.amazon.whisperjoin.common.sharedtypes.smarthome.intent.DSHSSetCredentialsAPI;
import com.amazon.whisperjoin.common.sharedtypes.smarthome.intent.DSHSSetCredentialsAPIImpl;
import com.amazon.whisperjoin.common.sharedtypes.smarthome.metrics.CredentialSyncMetricsRecorder;
import com.amazon.whisperjoin.deviceprovisioningservice.di.scopes.ProvisionerServices;
import com.amazon.whisperjoin.deviceprovisioningservice.identity.MapAuthenticationProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisionerClientData;
import com.amazon.whisperjoin.deviceprovisioningservice.service.power.DevicePowerMonitor;
import com.amazon.whisperjoin.deviceprovisioningservice.service.power.DevicePowerStatusProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.service.whitelist.WhiteListPolicyCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.service.whitelist.WhiteListPolicyUpdateListener;
import com.amazon.whisperjoin.deviceprovisioningservice.smarthome.AssociatedDeviceCredentialsSyncCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.smarthome.ZigbeeCredentialsSyncCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.util.Clock;
import com.amazon.whisperjoin.deviceprovisioningservice.util.JobInfoProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.util.SharedPreferencesProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.wifi.WifiLocker;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.CustomerProvisioneesSetupStatusSyncCoordinator;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSServiceConfiguration;
import com.amazon.whisperjoin.metrics.MetricsRecorderProvider;
import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes13.dex */
public class ProvisioningServicesModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ProvisionerServices
    public AssociatedDeviceCredentialsSyncCoordinator providesAssociatedDeviceCredentialsSyncCoordinator(Context context, ProvisionerInfo provisionerInfo, DSSClient dSSClient, JobScheduler jobScheduler, JobInfoProvider jobInfoProvider, Clock clock, SharedPreferencesProvider sharedPreferencesProvider, Gson gson) {
        return new AssociatedDeviceCredentialsSyncCoordinator(jobScheduler, jobInfoProvider, context, sharedPreferencesProvider, clock, dSSClient, provisionerInfo, gson);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ProvisionerServices
    public CustomerProvisioneesSetupStatusSyncCoordinator providesAutoDiscoverySyncCoordinator(Context context, ProvisionerInfo provisionerInfo, DSSClient dSSClient, JobScheduler jobScheduler, JobInfoProvider jobInfoProvider, Clock clock, SharedPreferencesProvider sharedPreferencesProvider) {
        return new CustomerProvisioneesSetupStatusSyncCoordinator(jobScheduler, jobInfoProvider, context, sharedPreferencesProvider, clock, dSSClient);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ProvisionerServices
    public ZigbeeCredentialsSyncCoordinator providesCredentialSyncCoordinator(Context context, ProvisionerInfo provisionerInfo, DSSClient dSSClient, JobScheduler jobScheduler, JobInfoProvider jobInfoProvider, Clock clock, SharedPreferencesProvider sharedPreferencesProvider) {
        return new ZigbeeCredentialsSyncCoordinator(jobScheduler, jobInfoProvider, context, sharedPreferencesProvider, clock, dSSClient, provisionerInfo);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ProvisionerServices
    public DSHSSetCredentialsAPI providesDSHSSetCredentialsAPI(Context context, CredentialSyncMetricsRecorder credentialSyncMetricsRecorder) {
        return new DSHSSetCredentialsAPIImpl(context, credentialSyncMetricsRecorder);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ProvisionerServices
    public DSSClient providesDSSClient(Context context, DSSServiceConfiguration dSSServiceConfiguration) {
        return DSSClientImpl.createOrGetStaticInstance(context, dSSServiceConfiguration);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public DevicePowerMonitor providesDevicePowerMonitor(Context context, DevicePowerStatusProvider devicePowerStatusProvider, MetricsRecorderProvider metricsRecorderProvider, ProvisionerClientData provisionerClientData) {
        return new DevicePowerMonitor(context, devicePowerStatusProvider, metricsRecorderProvider, provisionerClientData);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ProvisionerServices
    public WhiteListPolicyCoordinator providesFFSWhiteListPolicyCoordinator(Context context, JobScheduler jobScheduler, JobInfoProvider jobInfoProvider, SharedPreferencesProvider sharedPreferencesProvider, Clock clock, DSSClient dSSClient, ProvisionerClientData provisionerClientData) {
        return new WhiteListPolicyCoordinator(context, jobScheduler, jobInfoProvider, sharedPreferencesProvider, clock, dSSClient, provisionerClientData);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ProvisionerServices
    public WhiteListPolicyUpdateListener providesWhiteListPolicyUpdateListener(Context context, ProvisionerClientData provisionerClientData) {
        return new WhiteListPolicyUpdateListener(context, provisionerClientData);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ProvisionerServices
    public WifiLocker providesWifiLocker(MapAuthenticationProvider mapAuthenticationProvider, Context context) {
        return new WifiLocker(mapAuthenticationProvider, context);
    }
}
