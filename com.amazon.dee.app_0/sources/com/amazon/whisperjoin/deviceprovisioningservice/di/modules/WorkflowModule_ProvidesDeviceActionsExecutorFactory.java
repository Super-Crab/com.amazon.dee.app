package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.FFSArcusSettings;
import com.amazon.whisperjoin.deviceprovisioningservice.device.DiscoverySettings;
import com.amazon.whisperjoin.deviceprovisioningservice.device.ProvisioningManagerProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.identity.MapAuthenticationProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisionerClientData;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisioningServiceConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.util.Clock;
import com.amazon.whisperjoin.deviceprovisioningservice.util.LocationPermissionsHelper;
import com.amazon.whisperjoin.deviceprovisioningservice.wifi.CurrentWifiNetworkProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.WorkflowConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.deferred.DeferredDiscoveryHandler;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceActionsExecutor;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceEventStream;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.WJDeviceSetupModeSupportedPredicate;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.wifi.GetAvailableWifiNetworksFromDSS;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.wifi.SaveWifiNetworkThroughDSS;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.BluetoothSupportProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioningMethod;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import com.amazon.whisperjoin.metrics.MetricsRecorderProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.reactivex.rxjava3.core.Single;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class WorkflowModule_ProvidesDeviceActionsExecutorFactory implements Factory<DeviceActionsExecutor> {
    private final Provider<BluetoothSupportProvider> bluetoothSupportProvider;
    private final Provider<Clock> clockProvider;
    private final Provider<CurrentWifiNetworkProvider> currentWifiNetworkProvider;
    private final Provider<DeferredDiscoveryHandler> deferredDiscoveryHandlerProvider;
    private final Provider<DiscoverySettings> discoverySettingsProvider;
    private final Provider<DSSClient> dssClientProvider;
    private final Provider<DeviceEventStream> eventStreamProvider;
    private final Provider<Single<FFSArcusSettings>> ffsArcusSettingsSingleProvider;
    private final Provider<GetAvailableWifiNetworksFromDSS> getAvailableWifiNetworksFromDSSProvider;
    private final Provider<LocationPermissionsHelper> locationPermissionsHelperProvider;
    private final Provider<MapAuthenticationProvider> mapAuthenticationProvider;
    private final Provider<MetricsRecorderProvider> metricsRecorderProvider;
    private final WorkflowModule module;
    private final Provider<ProvisionerClientData> provisionerClientDataProvider;
    private final Provider<ProvisioningManagerProvider> provisioningManagerProvider;
    private final Provider<ProvisioningMethod> provisioningMethodProvider;
    private final Provider<ProvisioningServiceConfiguration> provisioningServiceConfigurationProvider;
    private final Provider<SaveWifiNetworkThroughDSS> saveWifiNetworkThroughDSSProvider;
    private final Provider<WJDeviceSetupModeSupportedPredicate> wjDeviceSetupModeSupportedPredicateProvider;
    private final Provider<WorkflowConfiguration> workflowConfigurationProvider;

    public WorkflowModule_ProvidesDeviceActionsExecutorFactory(WorkflowModule workflowModule, Provider<MapAuthenticationProvider> provider, Provider<ProvisioningManagerProvider> provider2, Provider<DeviceEventStream> provider3, Provider<DSSClient> provider4, Provider<ProvisioningServiceConfiguration> provider5, Provider<WorkflowConfiguration> provider6, Provider<ProvisionerClientData> provider7, Provider<DiscoverySettings> provider8, Provider<BluetoothSupportProvider> provider9, Provider<LocationPermissionsHelper> provider10, Provider<MetricsRecorderProvider> provider11, Provider<Clock> provider12, Provider<WJDeviceSetupModeSupportedPredicate> provider13, Provider<ProvisioningMethod> provider14, Provider<GetAvailableWifiNetworksFromDSS> provider15, Provider<SaveWifiNetworkThroughDSS> provider16, Provider<CurrentWifiNetworkProvider> provider17, Provider<DeferredDiscoveryHandler> provider18, Provider<Single<FFSArcusSettings>> provider19) {
        this.module = workflowModule;
        this.mapAuthenticationProvider = provider;
        this.provisioningManagerProvider = provider2;
        this.eventStreamProvider = provider3;
        this.dssClientProvider = provider4;
        this.provisioningServiceConfigurationProvider = provider5;
        this.workflowConfigurationProvider = provider6;
        this.provisionerClientDataProvider = provider7;
        this.discoverySettingsProvider = provider8;
        this.bluetoothSupportProvider = provider9;
        this.locationPermissionsHelperProvider = provider10;
        this.metricsRecorderProvider = provider11;
        this.clockProvider = provider12;
        this.wjDeviceSetupModeSupportedPredicateProvider = provider13;
        this.provisioningMethodProvider = provider14;
        this.getAvailableWifiNetworksFromDSSProvider = provider15;
        this.saveWifiNetworkThroughDSSProvider = provider16;
        this.currentWifiNetworkProvider = provider17;
        this.deferredDiscoveryHandlerProvider = provider18;
        this.ffsArcusSettingsSingleProvider = provider19;
    }

    public static WorkflowModule_ProvidesDeviceActionsExecutorFactory create(WorkflowModule workflowModule, Provider<MapAuthenticationProvider> provider, Provider<ProvisioningManagerProvider> provider2, Provider<DeviceEventStream> provider3, Provider<DSSClient> provider4, Provider<ProvisioningServiceConfiguration> provider5, Provider<WorkflowConfiguration> provider6, Provider<ProvisionerClientData> provider7, Provider<DiscoverySettings> provider8, Provider<BluetoothSupportProvider> provider9, Provider<LocationPermissionsHelper> provider10, Provider<MetricsRecorderProvider> provider11, Provider<Clock> provider12, Provider<WJDeviceSetupModeSupportedPredicate> provider13, Provider<ProvisioningMethod> provider14, Provider<GetAvailableWifiNetworksFromDSS> provider15, Provider<SaveWifiNetworkThroughDSS> provider16, Provider<CurrentWifiNetworkProvider> provider17, Provider<DeferredDiscoveryHandler> provider18, Provider<Single<FFSArcusSettings>> provider19) {
        return new WorkflowModule_ProvidesDeviceActionsExecutorFactory(workflowModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19);
    }

    public static DeviceActionsExecutor provideInstance(WorkflowModule workflowModule, Provider<MapAuthenticationProvider> provider, Provider<ProvisioningManagerProvider> provider2, Provider<DeviceEventStream> provider3, Provider<DSSClient> provider4, Provider<ProvisioningServiceConfiguration> provider5, Provider<WorkflowConfiguration> provider6, Provider<ProvisionerClientData> provider7, Provider<DiscoverySettings> provider8, Provider<BluetoothSupportProvider> provider9, Provider<LocationPermissionsHelper> provider10, Provider<MetricsRecorderProvider> provider11, Provider<Clock> provider12, Provider<WJDeviceSetupModeSupportedPredicate> provider13, Provider<ProvisioningMethod> provider14, Provider<GetAvailableWifiNetworksFromDSS> provider15, Provider<SaveWifiNetworkThroughDSS> provider16, Provider<CurrentWifiNetworkProvider> provider17, Provider<DeferredDiscoveryHandler> provider18, Provider<Single<FFSArcusSettings>> provider19) {
        return proxyProvidesDeviceActionsExecutor(workflowModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), provider8.mo10268get(), provider9.mo10268get(), provider10.mo10268get(), provider11.mo10268get(), provider12.mo10268get(), provider13.mo10268get(), provider14.mo10268get(), provider15.mo10268get(), provider16.mo10268get(), provider17.mo10268get(), provider18.mo10268get(), provider19.mo10268get());
    }

    public static DeviceActionsExecutor proxyProvidesDeviceActionsExecutor(WorkflowModule workflowModule, MapAuthenticationProvider mapAuthenticationProvider, ProvisioningManagerProvider provisioningManagerProvider, DeviceEventStream deviceEventStream, DSSClient dSSClient, ProvisioningServiceConfiguration provisioningServiceConfiguration, WorkflowConfiguration workflowConfiguration, ProvisionerClientData provisionerClientData, DiscoverySettings discoverySettings, BluetoothSupportProvider bluetoothSupportProvider, LocationPermissionsHelper locationPermissionsHelper, MetricsRecorderProvider metricsRecorderProvider, Clock clock, WJDeviceSetupModeSupportedPredicate wJDeviceSetupModeSupportedPredicate, ProvisioningMethod provisioningMethod, GetAvailableWifiNetworksFromDSS getAvailableWifiNetworksFromDSS, SaveWifiNetworkThroughDSS saveWifiNetworkThroughDSS, CurrentWifiNetworkProvider currentWifiNetworkProvider, DeferredDiscoveryHandler deferredDiscoveryHandler, Single<FFSArcusSettings> single) {
        return (DeviceActionsExecutor) Preconditions.checkNotNull(workflowModule.providesDeviceActionsExecutor(mapAuthenticationProvider, provisioningManagerProvider, deviceEventStream, dSSClient, provisioningServiceConfiguration, workflowConfiguration, provisionerClientData, discoverySettings, bluetoothSupportProvider, locationPermissionsHelper, metricsRecorderProvider, clock, wJDeviceSetupModeSupportedPredicate, provisioningMethod, getAvailableWifiNetworksFromDSS, saveWifiNetworkThroughDSS, currentWifiNetworkProvider, deferredDiscoveryHandler, single), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DeviceActionsExecutor mo10268get() {
        return provideInstance(this.module, this.mapAuthenticationProvider, this.provisioningManagerProvider, this.eventStreamProvider, this.dssClientProvider, this.provisioningServiceConfigurationProvider, this.workflowConfigurationProvider, this.provisionerClientDataProvider, this.discoverySettingsProvider, this.bluetoothSupportProvider, this.locationPermissionsHelperProvider, this.metricsRecorderProvider, this.clockProvider, this.wjDeviceSetupModeSupportedPredicateProvider, this.provisioningMethodProvider, this.getAvailableWifiNetworksFromDSSProvider, this.saveWifiNetworkThroughDSSProvider, this.currentWifiNetworkProvider, this.deferredDiscoveryHandlerProvider, this.ffsArcusSettingsSingleProvider);
    }
}
