package com.amazon.whisperjoin.deviceprovisioningservice.di.components;

import android.app.job.JobScheduler;
import android.content.Context;
import android.net.wifi.WifiManager;
import com.amazon.client.metrics.common.MetricsFactory;
import com.amazon.devicesetupservice.v1.ProvisionerInfo;
import com.amazon.whisperjoin.common.sharedtypes.cryptography.TrustProvider;
import com.amazon.whisperjoin.common.sharedtypes.smarthome.intent.DSHSSetCredentialsAPI;
import com.amazon.whisperjoin.common.sharedtypes.smarthome.metrics.CredentialSyncMetricsRecorder;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.FFSArcusSyncCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ErrorCodeMapperModule;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.MetricsModule;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ProvisioningConfigurationModule;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ProvisioningServicesModule;
import com.amazon.whisperjoin.deviceprovisioningservice.di.scopes.ProvisionerServices;
import com.amazon.whisperjoin.deviceprovisioningservice.identity.MapAuthenticationProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.metrics.AutoDiscoveryMetricsRecorder;
import com.amazon.whisperjoin.deviceprovisioningservice.metrics.FFSProvisioningServiceMetricsRecorder;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisionerClientData;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisioningServiceConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.service.power.DevicePowerMonitor;
import com.amazon.whisperjoin.deviceprovisioningservice.service.power.DevicePowerStatusProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.service.whitelist.WhiteListPolicyCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.service.whitelist.WhiteListPolicyUpdateListener;
import com.amazon.whisperjoin.deviceprovisioningservice.smarthome.AssociatedDeviceCredentialsSyncCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.smarthome.ZigbeeCredentialsSyncCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.util.Clock;
import com.amazon.whisperjoin.deviceprovisioningservice.util.JobInfoProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.util.LocationPermissionsHelper;
import com.amazon.whisperjoin.deviceprovisioningservice.util.SharedPreferencesProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.wifi.CurrentWifiNetworkProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.wifi.WifiLocker;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.WorkflowConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.BluetoothSupportProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.CustomerProvisioneesSetupStatusSyncCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioningMethod;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorMapper;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import com.amazon.whisperjoin.metrics.MetricsRecorderProvider;
import com.google.gson.Gson;
import dagger.Component;
@Component(dependencies = {BaseComponent.class}, modules = {ProvisioningServicesModule.class, ProvisioningConfigurationModule.class, MetricsModule.class, ErrorCodeMapperModule.class})
@ProvisionerServices
/* loaded from: classes13.dex */
public interface ProvisionerServicesComponent {
    BluetoothSupportProvider getBluetoothSupportProvider();

    Clock getClock();

    Context getContext();

    CurrentWifiNetworkProvider getCurrentWifiNetworkProvider();

    DevicePowerMonitor getDevicePowerMonitor();

    DevicePowerStatusProvider getDevicePowerStatusProvider();

    FFSArcusSyncCoordinator getFFSArcusSyncCoordinator();

    Gson getGson();

    JobInfoProvider getJobInfoProvider();

    JobScheduler getJobScheduler();

    LocationPermissionsHelper getLocationPermissionsHelper();

    MapAuthenticationProvider getMapAuthProvider();

    SharedPreferencesProvider getSharedPreferencesProvider();

    WifiManager getWifiManager();

    CustomerProvisioneesSetupStatusSyncCoordinator provideCustomerProvisioneesSetupStatusSyncCoordinator();

    AssociatedDeviceCredentialsSyncCoordinator providesAssociatedDeviceCredentialsSyncCoordinator();

    AutoDiscoveryMetricsRecorder providesAutoDiscoveryMetricsRecorder();

    ZigbeeCredentialsSyncCoordinator providesCredentialSyncCoordinator();

    CredentialSyncMetricsRecorder providesCredentialSyncMetricsRecorder();

    DSSClient providesDSSClient();

    FFSProvisioningServiceMetricsRecorder providesFFSProvisioningServiceMetricsRecorder();

    WhiteListPolicyCoordinator providesFFSWhiteListPolicyCoordinator();

    Boolean providesIsDebugEnabled();

    MetricsFactory providesMetricsFactory();

    MetricsRecorderProvider providesMetricsRecorderProvider();

    ProvisionerClientData providesProvisionerClientData();

    ProvisionerInfo providesProvisionerInfo();

    ProvisioningMethod providesProvisioningMethod();

    ProvisioningServiceConfiguration providesProvisioningServiceConfiguration();

    TrustProvider.TrustState providesTrustState();

    WJErrorMapper<Throwable> providesWJErrorMapper();

    WhiteListPolicyUpdateListener providesWhiteListPolicyUpdateListener();

    WifiLocker providesWifiLocker();

    WorkflowConfiguration providesWorkflowConfiguration();

    DSHSSetCredentialsAPI providesZigbeeCredentialSyncIntent();
}
