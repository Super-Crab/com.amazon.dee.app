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
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.DiscoveryConfigurationModule_ProvidesDiscoverySettingsFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.DiscoveryConfigurationModule_ProvidesOveractiveConfigurationFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.DiscoveryConfigurationModule_ProvidesScanFilterFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.DiscoveryConfigurationModule_ProvidesScanningModeFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.DiscoveryConfigurationModule_ProvidesWJDeviceSetupModeSupportedPredicateFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ProvisioningManagerModule;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ProvisioningManagerModule_GetProvisioningManagerDisposableFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ProvisioningManagerModule_ProvidesDeferredDiscoveryHandlerFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ProvisioningManagerModule_ProvidesDeviceDiscoveryStreamFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ProvisioningManagerModule_ProvidesDeviceEventStreamFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ProvisioningManagerModule_ProvidesProvisioningManagerProviderFactory;
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
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import io.reactivex.rxjava3.disposables.Disposable;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class DaggerProvisioningComponent implements ProvisioningComponent {
    private DiscoveryConfigurationModule discoveryConfigurationModule;
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisionerServicesComponent_getClock getClockProvider;
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisionerServicesComponent_getContext getContextProvider;
    private Provider<Disposable> getProvisioningManagerDisposableProvider;
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisionerServicesComponent_providesDSSClient providesDSSClientProvider;
    private Provider<DeferredDiscoveryHandler> providesDeferredDiscoveryHandlerProvider;
    private Provider<DeviceDiscoveryStream> providesDeviceDiscoveryStreamProvider;
    private Provider<DeviceEventStream> providesDeviceEventStreamProvider;
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisionerServicesComponent_providesMetricsRecorderProvider providesMetricsRecorderProvider;
    private DiscoveryConfigurationModule_ProvidesOveractiveConfigurationFactory providesOveractiveConfigurationProvider;
    private Provider<ProvisioningManagerProvider> providesProvisioningManagerProvider;
    private DiscoveryConfigurationModule_ProvidesScanFilterFactory providesScanFilterProvider;
    private ProvisionerServicesComponent provisionerServicesComponent;

    /* loaded from: classes13.dex */
    public static final class Builder {
        private DiscoveryConfigurationModule discoveryConfigurationModule;
        private ProvisionerServicesComponent provisionerServicesComponent;
        private ProvisioningManagerModule provisioningManagerModule;

        public ProvisioningComponent build() {
            Preconditions.checkBuilderRequirement(this.discoveryConfigurationModule, DiscoveryConfigurationModule.class);
            if (this.provisioningManagerModule == null) {
                this.provisioningManagerModule = new ProvisioningManagerModule();
            }
            Preconditions.checkBuilderRequirement(this.provisionerServicesComponent, ProvisionerServicesComponent.class);
            return new DaggerProvisioningComponent(this);
        }

        public Builder discoveryConfigurationModule(DiscoveryConfigurationModule discoveryConfigurationModule) {
            this.discoveryConfigurationModule = (DiscoveryConfigurationModule) Preconditions.checkNotNull(discoveryConfigurationModule);
            return this;
        }

        public Builder provisionerServicesComponent(ProvisionerServicesComponent provisionerServicesComponent) {
            this.provisionerServicesComponent = (ProvisionerServicesComponent) Preconditions.checkNotNull(provisionerServicesComponent);
            return this;
        }

        public Builder provisioningManagerModule(ProvisioningManagerModule provisioningManagerModule) {
            this.provisioningManagerModule = (ProvisioningManagerModule) Preconditions.checkNotNull(provisioningManagerModule);
            return this;
        }

        private Builder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisionerServicesComponent_getClock implements Provider<Clock> {
        private final ProvisionerServicesComponent provisionerServicesComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisionerServicesComponent_getClock(ProvisionerServicesComponent provisionerServicesComponent) {
            this.provisionerServicesComponent = provisionerServicesComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public Clock mo10268get() {
            return (Clock) Preconditions.checkNotNull(this.provisionerServicesComponent.getClock(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisionerServicesComponent_getContext implements Provider<Context> {
        private final ProvisionerServicesComponent provisionerServicesComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisionerServicesComponent_getContext(ProvisionerServicesComponent provisionerServicesComponent) {
            this.provisionerServicesComponent = provisionerServicesComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public Context mo10268get() {
            return (Context) Preconditions.checkNotNull(this.provisionerServicesComponent.getContext(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisionerServicesComponent_providesDSSClient implements Provider<DSSClient> {
        private final ProvisionerServicesComponent provisionerServicesComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisionerServicesComponent_providesDSSClient(ProvisionerServicesComponent provisionerServicesComponent) {
            this.provisionerServicesComponent = provisionerServicesComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public DSSClient mo10268get() {
            return (DSSClient) Preconditions.checkNotNull(this.provisionerServicesComponent.providesDSSClient(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisionerServicesComponent_providesMetricsRecorderProvider implements Provider<MetricsRecorderProvider> {
        private final ProvisionerServicesComponent provisionerServicesComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisionerServicesComponent_providesMetricsRecorderProvider(ProvisionerServicesComponent provisionerServicesComponent) {
            this.provisionerServicesComponent = provisionerServicesComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public MetricsRecorderProvider mo10268get() {
            return (MetricsRecorderProvider) Preconditions.checkNotNull(this.provisionerServicesComponent.providesMetricsRecorderProvider(), "Cannot return null from a non-@Nullable component method");
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(Builder builder) {
        this.getContextProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisionerServicesComponent_getContext(builder.provisionerServicesComponent);
        this.providesDSSClientProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisionerServicesComponent_providesDSSClient(builder.provisionerServicesComponent);
        this.providesMetricsRecorderProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisionerServicesComponent_providesMetricsRecorderProvider(builder.provisionerServicesComponent);
        this.providesScanFilterProvider = DiscoveryConfigurationModule_ProvidesScanFilterFactory.create(builder.discoveryConfigurationModule, this.getContextProvider);
        this.providesOveractiveConfigurationProvider = DiscoveryConfigurationModule_ProvidesOveractiveConfigurationFactory.create(builder.discoveryConfigurationModule);
        this.providesProvisioningManagerProvider = DoubleCheck.provider(ProvisioningManagerModule_ProvidesProvisioningManagerProviderFactory.create(builder.provisioningManagerModule, this.getContextProvider, this.providesDSSClientProvider, this.providesMetricsRecorderProvider, this.providesScanFilterProvider, this.providesOveractiveConfigurationProvider));
        this.providesDeviceEventStreamProvider = DoubleCheck.provider(ProvisioningManagerModule_ProvidesDeviceEventStreamFactory.create(builder.provisioningManagerModule, this.providesProvisioningManagerProvider));
        this.providesDeviceDiscoveryStreamProvider = DoubleCheck.provider(ProvisioningManagerModule_ProvidesDeviceDiscoveryStreamFactory.create(builder.provisioningManagerModule, this.providesProvisioningManagerProvider));
        this.getProvisioningManagerDisposableProvider = DoubleCheck.provider(ProvisioningManagerModule_GetProvisioningManagerDisposableFactory.create(builder.provisioningManagerModule, this.providesDeviceEventStreamProvider, this.providesDeviceDiscoveryStreamProvider));
        this.getClockProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisionerServicesComponent_getClock(builder.provisionerServicesComponent);
        this.providesDeferredDiscoveryHandlerProvider = DoubleCheck.provider(ProvisioningManagerModule_ProvidesDeferredDiscoveryHandlerFactory.create(builder.provisioningManagerModule, this.getClockProvider));
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningComponent
    public BluetoothSupportProvider getBluetoothSupportProvider() {
        return (BluetoothSupportProvider) Preconditions.checkNotNull(this.provisionerServicesComponent.getBluetoothSupportProvider(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningComponent
    public Clock getClock() {
        return (Clock) Preconditions.checkNotNull(this.provisionerServicesComponent.getClock(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningComponent
    public Context getContext() {
        return (Context) Preconditions.checkNotNull(this.provisionerServicesComponent.getContext(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningComponent
    public CurrentWifiNetworkProvider getCurrentWifiNetworkProvider() {
        return (CurrentWifiNetworkProvider) Preconditions.checkNotNull(this.provisionerServicesComponent.getCurrentWifiNetworkProvider(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningComponent
    public DevicePowerMonitor getDevicePowerMonitor() {
        return (DevicePowerMonitor) Preconditions.checkNotNull(this.provisionerServicesComponent.getDevicePowerMonitor(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningComponent
    public Gson getGson() {
        return (Gson) Preconditions.checkNotNull(this.provisionerServicesComponent.getGson(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningComponent
    public LocationPermissionsHelper getLocationPermissionsHelper() {
        return (LocationPermissionsHelper) Preconditions.checkNotNull(this.provisionerServicesComponent.getLocationPermissionsHelper(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningComponent
    public MapAuthenticationProvider getMapAuthProvider() {
        return (MapAuthenticationProvider) Preconditions.checkNotNull(this.provisionerServicesComponent.getMapAuthProvider(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningComponent
    public WifiManager getWifiManager() {
        return (WifiManager) Preconditions.checkNotNull(this.provisionerServicesComponent.getWifiManager(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningComponent
    public CustomerProvisioneesSetupStatusSyncCoordinator provideCustomerProvisioneesSetupStatusSyncCoordinator() {
        return (CustomerProvisioneesSetupStatusSyncCoordinator) Preconditions.checkNotNull(this.provisionerServicesComponent.provideCustomerProvisioneesSetupStatusSyncCoordinator(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningComponent
    public AssociatedDeviceCredentialsSyncCoordinator providesAssociatedDeviceCredentialsSyncCoordinator() {
        return (AssociatedDeviceCredentialsSyncCoordinator) Preconditions.checkNotNull(this.provisionerServicesComponent.providesAssociatedDeviceCredentialsSyncCoordinator(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningComponent
    public AutoDiscoveryMetricsRecorder providesAutoDiscoveryMetricsRecorder() {
        return (AutoDiscoveryMetricsRecorder) Preconditions.checkNotNull(this.provisionerServicesComponent.providesAutoDiscoveryMetricsRecorder(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningComponent
    public DSHSSetCredentialsAPI providesDSHSSetCredentialsAPI() {
        return (DSHSSetCredentialsAPI) Preconditions.checkNotNull(this.provisionerServicesComponent.providesZigbeeCredentialSyncIntent(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningComponent
    public DSSClient providesDSSClient() {
        return (DSSClient) Preconditions.checkNotNull(this.provisionerServicesComponent.providesDSSClient(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningComponent
    public DeferredDiscoveryHandler providesDeferredDiscoveryHandler() {
        return this.providesDeferredDiscoveryHandlerProvider.mo10268get();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningComponent
    public DeviceDiscoveryStream providesDeviceDiscoveryStream() {
        return this.providesDeviceDiscoveryStreamProvider.mo10268get();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningComponent
    public DeviceEventStream providesDeviceEventStream() {
        return this.providesDeviceEventStreamProvider.mo10268get();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningComponent
    public DiscoverySettings providesDiscoverySettings() {
        return DiscoveryConfigurationModule_ProvidesDiscoverySettingsFactory.proxyProvidesDiscoverySettings(this.discoveryConfigurationModule, (WorkflowConfiguration) Preconditions.checkNotNull(this.provisionerServicesComponent.providesWorkflowConfiguration(), "Cannot return null from a non-@Nullable component method"), DiscoveryConfigurationModule_ProvidesScanningModeFactory.proxyProvidesScanningMode(this.discoveryConfigurationModule), (ProvisioningMethod) Preconditions.checkNotNull(this.provisionerServicesComponent.providesProvisioningMethod(), "Cannot return null from a non-@Nullable component method"));
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningComponent
    public FFSArcusSyncCoordinator providesFFSArcusSyncCoordinator() {
        return (FFSArcusSyncCoordinator) Preconditions.checkNotNull(this.provisionerServicesComponent.getFFSArcusSyncCoordinator(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningComponent
    public FFSProvisioningServiceMetricsRecorder providesFFSProvisioningServiceMetricsRecorder() {
        return (FFSProvisioningServiceMetricsRecorder) Preconditions.checkNotNull(this.provisionerServicesComponent.providesFFSProvisioningServiceMetricsRecorder(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningComponent
    public WhiteListPolicyCoordinator providesFFSWhiteListPolicyCoordinator() {
        return (WhiteListPolicyCoordinator) Preconditions.checkNotNull(this.provisionerServicesComponent.providesFFSWhiteListPolicyCoordinator(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningComponent
    public Boolean providesIsDebugEnabled() {
        return (Boolean) Preconditions.checkNotNull(this.provisionerServicesComponent.providesIsDebugEnabled(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningComponent
    public MetricsFactory providesMetricsFactory() {
        return (MetricsFactory) Preconditions.checkNotNull(this.provisionerServicesComponent.providesMetricsFactory(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningComponent
    public MetricsRecorderProvider providesMetricsRecorderProvider() {
        return (MetricsRecorderProvider) Preconditions.checkNotNull(this.provisionerServicesComponent.providesMetricsRecorderProvider(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningComponent
    public ProvisionerClientData providesProvisionerClientData() {
        return (ProvisionerClientData) Preconditions.checkNotNull(this.provisionerServicesComponent.providesProvisionerClientData(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningComponent
    public ProvisionerInfo providesProvisionerInfo() {
        return (ProvisionerInfo) Preconditions.checkNotNull(this.provisionerServicesComponent.providesProvisionerInfo(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningComponent
    public Disposable providesProvisioningManagerDisposable() {
        return this.getProvisioningManagerDisposableProvider.mo10268get();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningComponent
    public ProvisioningManagerProvider providesProvisioningManagerProvider() {
        return this.providesProvisioningManagerProvider.mo10268get();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningComponent
    public ProvisioningMethod providesProvisioningMethod() {
        return (ProvisioningMethod) Preconditions.checkNotNull(this.provisionerServicesComponent.providesProvisioningMethod(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningComponent
    public ProvisioningServiceConfiguration providesProvisioningServiceConfiguration() {
        return (ProvisioningServiceConfiguration) Preconditions.checkNotNull(this.provisionerServicesComponent.providesProvisioningServiceConfiguration(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningComponent
    public TrustProvider.TrustState providesTrustState() {
        return (TrustProvider.TrustState) Preconditions.checkNotNull(this.provisionerServicesComponent.providesTrustState(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningComponent
    public WJDeviceSetupModeSupportedPredicate providesWJDeviceSetupModeSupportedPredicate() {
        return DiscoveryConfigurationModule_ProvidesWJDeviceSetupModeSupportedPredicateFactory.proxyProvidesWJDeviceSetupModeSupportedPredicate(this.discoveryConfigurationModule, (ProvisioningMethod) Preconditions.checkNotNull(this.provisionerServicesComponent.providesProvisioningMethod(), "Cannot return null from a non-@Nullable component method"), (WorkflowConfiguration) Preconditions.checkNotNull(this.provisionerServicesComponent.providesWorkflowConfiguration(), "Cannot return null from a non-@Nullable component method"));
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningComponent
    public WJErrorMapper<Throwable> providesWJErrorMapper() {
        return (WJErrorMapper) Preconditions.checkNotNull(this.provisionerServicesComponent.providesWJErrorMapper(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningComponent
    public WhiteListPolicyUpdateListener providesWhiteListPolicyUpdateListener() {
        return (WhiteListPolicyUpdateListener) Preconditions.checkNotNull(this.provisionerServicesComponent.providesWhiteListPolicyUpdateListener(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningComponent
    public WifiLocker providesWifiLocker() {
        return (WifiLocker) Preconditions.checkNotNull(this.provisionerServicesComponent.providesWifiLocker(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningComponent
    public WorkflowConfiguration providesWorkflowConfiguration() {
        return (WorkflowConfiguration) Preconditions.checkNotNull(this.provisionerServicesComponent.providesWorkflowConfiguration(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningComponent
    public ZigbeeCredentialsSyncCoordinator providesZigbeeSyncCoordinator() {
        return (ZigbeeCredentialsSyncCoordinator) Preconditions.checkNotNull(this.provisionerServicesComponent.providesCredentialSyncCoordinator(), "Cannot return null from a non-@Nullable component method");
    }

    private DaggerProvisioningComponent(Builder builder) {
        this.provisionerServicesComponent = builder.provisionerServicesComponent;
        this.discoveryConfigurationModule = builder.discoveryConfigurationModule;
        initialize(builder);
    }
}
