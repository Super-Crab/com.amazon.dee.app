package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import android.app.job.JobScheduler;
import android.content.Context;
import android.os.Build;
import com.amazon.devicesetupservice.v1.ProvisionerInfo;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.BuildConfig;
import com.amazon.whisperjoin.deviceprovisioningservice.di.scopes.ProvisioningScope;
import com.amazon.whisperjoin.deviceprovisioningservice.identity.MapAuthenticationProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisionerClientData;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisioningServiceConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.service.power.DevicePowerMonitor;
import com.amazon.whisperjoin.deviceprovisioningservice.service.power.DevicePowerStatusProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.service.whitelist.WhiteListPolicyCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.service.whitelist.WhiteListPolicyUpdateListener;
import com.amazon.whisperjoin.deviceprovisioningservice.smarthome.ZigbeeCredentialsSyncCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.util.Clock;
import com.amazon.whisperjoin.deviceprovisioningservice.util.DeviceSerialProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.util.JobInfoProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.util.SharedPreferencesProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.wifi.WifiLocker;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.WorkflowConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.CustomerProvisioneesSetupStatusSyncCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioningMethod;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl;
import com.amazon.whisperjoin.metrics.MetricsRecorderProvider;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes13.dex */
public class ProvisioningModule {
    private static final String TAG = "ProvisioningModule";
    private final ProvisioningMethod mProvisioningMethod;
    private final ProvisioningServiceConfiguration mProvisioningServiceConfiguration;
    private final WorkflowConfiguration mWorkflowConfiguration;

    public ProvisioningModule(ProvisioningServiceConfiguration provisioningServiceConfiguration, WorkflowConfiguration workflowConfiguration, ProvisioningMethod provisioningMethod) {
        if (provisioningServiceConfiguration != null) {
            if (workflowConfiguration == null) {
                throw new IllegalArgumentException("workflowConfiguration cannot be null");
            }
            if (provisioningMethod != null) {
                this.mProvisioningServiceConfiguration = provisioningServiceConfiguration;
                this.mWorkflowConfiguration = workflowConfiguration;
                this.mProvisioningMethod = provisioningMethod;
                WJLog.setDebug(this.mProvisioningServiceConfiguration.isDebugEnabled());
                WJLog.i(TAG, "ProvisionerSDK Version: 1.21.2_sdk30.39.0");
                WJLog.d(TAG, "ProvisionerSDK Runtime Dependencies");
                WJLog.d(TAG, BuildConfig.WJ_VERSIONS);
                return;
            }
            throw new IllegalArgumentException("provisioningMethod can not be null");
        }
        throw new IllegalArgumentException("Provisioning service configuration cannot be null");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ProvisioningScope
    public CustomerProvisioneesSetupStatusSyncCoordinator providesAutoDiscoverySyncCoordinator(Context context, ProvisionerInfo provisionerInfo, DSSClient dSSClient, JobScheduler jobScheduler, JobInfoProvider jobInfoProvider, Clock clock, SharedPreferencesProvider sharedPreferencesProvider) {
        return new CustomerProvisioneesSetupStatusSyncCoordinator(jobScheduler, jobInfoProvider, context, sharedPreferencesProvider, clock, dSSClient);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ProvisioningScope
    public DSSClient providesDSSClient(Context context, ProvisioningServiceConfiguration provisioningServiceConfiguration) {
        return DSSClientImpl.createOrGetStaticInstance(context, provisioningServiceConfiguration.getDSSServiceConfiguration());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ProvisioningScope
    public ZigbeeCredentialsSyncCoordinator providesDeviceCredentialsSyncCoordinator(Context context, ProvisionerInfo provisionerInfo, DSSClient dSSClient, JobScheduler jobScheduler, JobInfoProvider jobInfoProvider, Clock clock, SharedPreferencesProvider sharedPreferencesProvider) {
        return new ZigbeeCredentialsSyncCoordinator(jobScheduler, jobInfoProvider, context, sharedPreferencesProvider, clock, dSSClient, provisionerInfo);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public DevicePowerMonitor providesDevicePowerMonitor(Context context, DevicePowerStatusProvider devicePowerStatusProvider, MetricsRecorderProvider metricsRecorderProvider, ProvisionerClientData provisionerClientData) {
        return new DevicePowerMonitor(context, devicePowerStatusProvider, metricsRecorderProvider, provisionerClientData);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ProvisioningScope
    public WhiteListPolicyCoordinator providesFFSWhiteListPolicyCoordinator(Context context, JobScheduler jobScheduler, JobInfoProvider jobInfoProvider, SharedPreferencesProvider sharedPreferencesProvider, Clock clock, DSSClient dSSClient, ProvisionerClientData provisionerClientData) {
        return new WhiteListPolicyCoordinator(context, jobScheduler, jobInfoProvider, sharedPreferencesProvider, clock, dSSClient, provisionerClientData);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ProvisioningScope
    public ProvisionerClientData providesProvisionerClientData(ProvisioningServiceConfiguration provisioningServiceConfiguration, MapAuthenticationProvider mapAuthenticationProvider) {
        ProvisionerClientData createProvisionerClientData = new ProvisionerClientData.Builder().setClientAppName(provisioningServiceConfiguration.getProvisionerApplicationName()).setClientAppVersion(provisioningServiceConfiguration.getProvisionerVersionNumber()).setDeviceManufacturer(Build.MANUFACTURER).setDeviceModel(Build.MODEL).setDeviceSerialNumber(DeviceSerialProvider.getDeviceSerial()).setDeviceFirmwareVersion(Build.VERSION.RELEASE).setMarketplace(provisioningServiceConfiguration.getLocaleConfiguration().getMarketplace()).setMetricsDeviceGroup(provisioningServiceConfiguration.getProvisionerDeviceGroup()).setCustomerEcid(mapAuthenticationProvider.getAccount()).createProvisionerClientData();
        WJLog.d(TAG, "ProvisionerClientData");
        WJLog.d(TAG, createProvisionerClientData.toString());
        return createProvisionerClientData;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ProvisioningScope
    public ProvisionerInfo providesProvisionerInfo(ProvisionerClientData provisionerClientData) {
        ProvisionerInfo provisionerInfo = new ProvisionerInfo();
        provisionerInfo.setDeviceModel(provisionerClientData.getDeviceModel());
        provisionerInfo.setManufacturer(provisionerClientData.getDeviceManufacturer());
        provisionerInfo.setFirmwareVersion(provisionerClientData.getDeviceFirmwareVersion());
        provisionerInfo.setApplication(provisionerClientData.getClientAppName());
        provisionerInfo.setApplicationVersion(provisionerClientData.getClientAppVersion());
        return provisionerInfo;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ProvisioningScope
    public ProvisioningMethod providesProvisioningMethod() {
        return this.mProvisioningMethod;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ProvisioningScope
    public ProvisioningServiceConfiguration providesProvisioningServiceConfiguration() {
        return this.mProvisioningServiceConfiguration;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ProvisioningScope
    public WhiteListPolicyUpdateListener providesWhiteListPolicyUpdateListener(Context context, ProvisionerClientData provisionerClientData) {
        return new WhiteListPolicyUpdateListener(context, provisionerClientData);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ProvisioningScope
    public WifiLocker providesWifiLocker(MapAuthenticationProvider mapAuthenticationProvider, Context context) {
        return new WifiLocker(mapAuthenticationProvider, context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ProvisioningScope
    public WorkflowConfiguration providesWorkflowConfiguration() {
        return this.mWorkflowConfiguration;
    }
}
