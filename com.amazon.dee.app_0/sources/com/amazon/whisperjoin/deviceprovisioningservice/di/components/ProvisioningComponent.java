package com.amazon.whisperjoin.deviceprovisioningservice.di.components;

import android.content.Context;
import android.net.wifi.WifiManager;
import com.amazon.client.metrics.common.MetricsFactory;
import com.amazon.devicesetupservice.v1.ProvisionerInfo;
import com.amazon.whisperjoin.common.sharedtypes.cryptography.TrustProvider;
import com.amazon.whisperjoin.common.sharedtypes.smarthome.intent.DSHSSetCredentialsAPI;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.FFSArcusSyncCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.device.DiscoverySettings;
import com.amazon.whisperjoin.deviceprovisioningservice.device.ProvisioningManagerProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.DiscoveryConfigurationModule;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ProvisioningManagerModule;
import com.amazon.whisperjoin.deviceprovisioningservice.di.scopes.ProvisioningScope;
import com.amazon.whisperjoin.deviceprovisioningservice.identity.MapAuthenticationProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.metrics.AutoDiscoveryMetricsRecorder;
import com.amazon.whisperjoin.deviceprovisioningservice.metrics.FFSProvisioningServiceMetricsRecorder;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisionerClientData;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisioningServiceConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.service.power.DevicePowerMonitor;
import com.amazon.whisperjoin.deviceprovisioningservice.service.whitelist.WhiteListPolicyCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.service.whitelist.WhiteListPolicyUpdateListener;
import com.amazon.whisperjoin.deviceprovisioningservice.smarthome.AssociatedDeviceCredentialsSyncCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.smarthome.ZigbeeCredentialsSyncCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.util.Clock;
import com.amazon.whisperjoin.deviceprovisioningservice.util.LocationPermissionsHelper;
import com.amazon.whisperjoin.deviceprovisioningservice.wifi.CurrentWifiNetworkProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.wifi.WifiLocker;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.WorkflowConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.deferred.DeferredDiscoveryHandler;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceDiscoveryStream;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceEventStream;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.WJDeviceSetupModeSupportedPredicate;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.BluetoothSupportProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.CustomerProvisioneesSetupStatusSyncCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioningMethod;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorMapper;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import com.amazon.whisperjoin.metrics.MetricsRecorderProvider;
import com.google.gson.Gson;
import dagger.Component;
import io.reactivex.rxjava3.disposables.Disposable;
@Component(dependencies = {ProvisionerServicesComponent.class}, modules = {DiscoveryConfigurationModule.class, ProvisioningManagerModule.class})
@ProvisioningScope
/* loaded from: classes13.dex */
public interface ProvisioningComponent {
    BluetoothSupportProvider getBluetoothSupportProvider();

    Clock getClock();

    Context getContext();

    CurrentWifiNetworkProvider getCurrentWifiNetworkProvider();

    DevicePowerMonitor getDevicePowerMonitor();

    Gson getGson();

    LocationPermissionsHelper getLocationPermissionsHelper();

    MapAuthenticationProvider getMapAuthProvider();

    WifiManager getWifiManager();

    CustomerProvisioneesSetupStatusSyncCoordinator provideCustomerProvisioneesSetupStatusSyncCoordinator();

    AssociatedDeviceCredentialsSyncCoordinator providesAssociatedDeviceCredentialsSyncCoordinator();

    AutoDiscoveryMetricsRecorder providesAutoDiscoveryMetricsRecorder();

    DSHSSetCredentialsAPI providesDSHSSetCredentialsAPI();

    DSSClient providesDSSClient();

    DeferredDiscoveryHandler providesDeferredDiscoveryHandler();

    DeviceDiscoveryStream providesDeviceDiscoveryStream();

    DeviceEventStream providesDeviceEventStream();

    DiscoverySettings providesDiscoverySettings();

    FFSArcusSyncCoordinator providesFFSArcusSyncCoordinator();

    FFSProvisioningServiceMetricsRecorder providesFFSProvisioningServiceMetricsRecorder();

    WhiteListPolicyCoordinator providesFFSWhiteListPolicyCoordinator();

    Boolean providesIsDebugEnabled();

    MetricsFactory providesMetricsFactory();

    MetricsRecorderProvider providesMetricsRecorderProvider();

    ProvisionerClientData providesProvisionerClientData();

    ProvisionerInfo providesProvisionerInfo();

    Disposable providesProvisioningManagerDisposable();

    ProvisioningManagerProvider providesProvisioningManagerProvider();

    ProvisioningMethod providesProvisioningMethod();

    ProvisioningServiceConfiguration providesProvisioningServiceConfiguration();

    TrustProvider.TrustState providesTrustState();

    WJDeviceSetupModeSupportedPredicate providesWJDeviceSetupModeSupportedPredicate();

    WJErrorMapper<Throwable> providesWJErrorMapper();

    WhiteListPolicyUpdateListener providesWhiteListPolicyUpdateListener();

    WifiLocker providesWifiLocker();

    WorkflowConfiguration providesWorkflowConfiguration();

    ZigbeeCredentialsSyncCoordinator providesZigbeeSyncCoordinator();
}
