package com.amazon.whisperjoin.deviceprovisioningservice.di.components;

import android.app.job.JobScheduler;
import android.content.Context;
import android.net.wifi.WifiManager;
import com.amazon.client.metrics.common.MetricsFactory;
import com.amazon.devicesetupservice.v1.ProvisionerInfo;
import com.amazon.whisperjoin.common.sharedtypes.cryptography.TrustProvider;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.ConnectionFailureException;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.TrustProviderInitializationFailedException;
import com.amazon.whisperjoin.common.sharedtypes.smarthome.intent.DSHSSetCredentialsAPI;
import com.amazon.whisperjoin.common.sharedtypes.smarthome.metrics.CredentialSyncMetricsRecorder;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.FFSArcusSyncCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ErrorCodeMapperModule;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ErrorCodeMapperModule_ProvidesBLETransportOperationErrorDetailsProviderFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ErrorCodeMapperModule_ProvidesConnectionFailureMapperFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ErrorCodeMapperModule_ProvidesDSSClientErrorMapperFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ErrorCodeMapperModule_ProvidesErrorMapperFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ErrorCodeMapperModule_ProvidesPreconditionalFailureMapperFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ErrorCodeMapperModule_ProvidesProvisionerCommandErrorMapperFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ErrorCodeMapperModule_ProvidesProvisionignNotGrantedMapperFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ErrorCodeMapperModule_ProvidesProvisioningFailureMapperFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ErrorCodeMapperModule_ProvidesTrustProviderInitializationErrorMapperFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ErrorCodeMapperModule_ProvidesWiFiSyncAuthTokenFailedValidationMapperFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ErrorCodeMapperModule_ProvidesWiFiSyncAuthTokenNotFoundMapperFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ErrorCodeMapperModule_ProvidesWorkflowFailureExceptionErrorMapperFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.MetricsModule;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.MetricsModule_ProvidesAutoDiscoveryMetricsRecorderFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.MetricsModule_ProvidesCredentialSyncMetricsRecorderFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.MetricsModule_ProvidesFFSProvisioningServiceMetricsRecorderFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.MetricsModule_ProvidesMetricsFactoryFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.MetricsModule_ProvidesMetricsRecorderProviderFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ProvisioningConfigurationModule;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ProvisioningConfigurationModule_ProvidesDSSServiceConfigurationFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ProvisioningConfigurationModule_ProvidesIsDebugEnabledFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ProvisioningConfigurationModule_ProvidesProvisionerClientDataFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ProvisioningConfigurationModule_ProvidesProvisionerInfoFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ProvisioningConfigurationModule_ProvidesProvisioningMethodFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ProvisioningConfigurationModule_ProvidesProvisioningServiceConfigurationFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ProvisioningConfigurationModule_ProvidesTrustStateFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ProvisioningConfigurationModule_ProvidesWorkflowConfigurationFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ProvisioningServicesModule;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ProvisioningServicesModule_ProvidesAssociatedDeviceCredentialsSyncCoordinatorFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ProvisioningServicesModule_ProvidesAutoDiscoverySyncCoordinatorFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ProvisioningServicesModule_ProvidesCredentialSyncCoordinatorFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ProvisioningServicesModule_ProvidesDSHSSetCredentialsAPIFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ProvisioningServicesModule_ProvidesDSSClientFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ProvisioningServicesModule_ProvidesDevicePowerMonitorFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ProvisioningServicesModule_ProvidesFFSWhiteListPolicyCoordinatorFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ProvisioningServicesModule_ProvidesWhiteListPolicyUpdateListenerFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ProvisioningServicesModule_ProvidesWifiLockerFactory;
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
import com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSServiceConfiguration;
import com.amazon.whisperjoin.metrics.MetricsRecorderProvider;
import com.amazon.whisperjoin.provisionerSDK.radios.error.ProvisionerCommandError;
import com.google.gson.Gson;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class DaggerProvisionerServicesComponent implements ProvisionerServicesComponent {
    private BaseComponent baseComponent;
    private ErrorCodeMapperModule errorCodeMapperModule;
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_BaseComponent_getClock getClockProvider;
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_BaseComponent_getContext getContextProvider;
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_BaseComponent_getGson getGsonProvider;
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_BaseComponent_getJobInfoProvider getJobInfoProvider;
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_BaseComponent_getJobScheduler getJobSchedulerProvider;
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_BaseComponent_getMapAuthProvider getMapAuthProvider;
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_BaseComponent_getSharedPreferencesProvider getSharedPreferencesProvider;
    private Provider<AssociatedDeviceCredentialsSyncCoordinator> providesAssociatedDeviceCredentialsSyncCoordinatorProvider;
    private Provider<AutoDiscoveryMetricsRecorder> providesAutoDiscoveryMetricsRecorderProvider;
    private Provider<CustomerProvisioneesSetupStatusSyncCoordinator> providesAutoDiscoverySyncCoordinatorProvider;
    private Provider<ZigbeeCredentialsSyncCoordinator> providesCredentialSyncCoordinatorProvider;
    private Provider<CredentialSyncMetricsRecorder> providesCredentialSyncMetricsRecorderProvider;
    private Provider<DSHSSetCredentialsAPI> providesDSHSSetCredentialsAPIProvider;
    private Provider<DSSClient> providesDSSClientProvider;
    private Provider<DSSServiceConfiguration> providesDSSServiceConfigurationProvider;
    private Provider<FFSProvisioningServiceMetricsRecorder> providesFFSProvisioningServiceMetricsRecorderProvider;
    private Provider<WhiteListPolicyCoordinator> providesFFSWhiteListPolicyCoordinatorProvider;
    private Provider<Boolean> providesIsDebugEnabledProvider;
    private Provider<MetricsFactory> providesMetricsFactoryProvider;
    private Provider<MetricsRecorderProvider> providesMetricsRecorderProvider;
    private Provider<ProvisionerClientData> providesProvisionerClientDataProvider;
    private Provider<ProvisionerInfo> providesProvisionerInfoProvider;
    private Provider<ProvisioningMethod> providesProvisioningMethodProvider;
    private Provider<ProvisioningServiceConfiguration> providesProvisioningServiceConfigurationProvider;
    private Provider<TrustProvider.TrustState> providesTrustStateProvider;
    private Provider<WhiteListPolicyUpdateListener> providesWhiteListPolicyUpdateListenerProvider;
    private Provider<WifiLocker> providesWifiLockerProvider;
    private Provider<WorkflowConfiguration> providesWorkflowConfigurationProvider;
    private ProvisioningServicesModule provisioningServicesModule;

    /* loaded from: classes13.dex */
    public static final class Builder {
        private BaseComponent baseComponent;
        private ErrorCodeMapperModule errorCodeMapperModule;
        private MetricsModule metricsModule;
        private ProvisioningConfigurationModule provisioningConfigurationModule;
        private ProvisioningServicesModule provisioningServicesModule;

        public Builder baseComponent(BaseComponent baseComponent) {
            this.baseComponent = (BaseComponent) Preconditions.checkNotNull(baseComponent);
            return this;
        }

        public ProvisionerServicesComponent build() {
            Preconditions.checkBuilderRequirement(this.provisioningConfigurationModule, ProvisioningConfigurationModule.class);
            if (this.provisioningServicesModule == null) {
                this.provisioningServicesModule = new ProvisioningServicesModule();
            }
            if (this.metricsModule == null) {
                this.metricsModule = new MetricsModule();
            }
            if (this.errorCodeMapperModule == null) {
                this.errorCodeMapperModule = new ErrorCodeMapperModule();
            }
            Preconditions.checkBuilderRequirement(this.baseComponent, BaseComponent.class);
            return new DaggerProvisionerServicesComponent(this);
        }

        public Builder errorCodeMapperModule(ErrorCodeMapperModule errorCodeMapperModule) {
            this.errorCodeMapperModule = (ErrorCodeMapperModule) Preconditions.checkNotNull(errorCodeMapperModule);
            return this;
        }

        public Builder metricsModule(MetricsModule metricsModule) {
            this.metricsModule = (MetricsModule) Preconditions.checkNotNull(metricsModule);
            return this;
        }

        public Builder provisioningConfigurationModule(ProvisioningConfigurationModule provisioningConfigurationModule) {
            this.provisioningConfigurationModule = (ProvisioningConfigurationModule) Preconditions.checkNotNull(provisioningConfigurationModule);
            return this;
        }

        public Builder provisioningServicesModule(ProvisioningServicesModule provisioningServicesModule) {
            this.provisioningServicesModule = (ProvisioningServicesModule) Preconditions.checkNotNull(provisioningServicesModule);
            return this;
        }

        private Builder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_BaseComponent_getClock implements Provider<Clock> {
        private final BaseComponent baseComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_BaseComponent_getClock(BaseComponent baseComponent) {
            this.baseComponent = baseComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public Clock mo10268get() {
            return (Clock) Preconditions.checkNotNull(this.baseComponent.getClock(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_BaseComponent_getContext implements Provider<Context> {
        private final BaseComponent baseComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_BaseComponent_getContext(BaseComponent baseComponent) {
            this.baseComponent = baseComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public Context mo10268get() {
            return (Context) Preconditions.checkNotNull(this.baseComponent.getContext(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_BaseComponent_getGson implements Provider<Gson> {
        private final BaseComponent baseComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_BaseComponent_getGson(BaseComponent baseComponent) {
            this.baseComponent = baseComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public Gson mo10268get() {
            return (Gson) Preconditions.checkNotNull(this.baseComponent.getGson(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_BaseComponent_getJobInfoProvider implements Provider<JobInfoProvider> {
        private final BaseComponent baseComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_BaseComponent_getJobInfoProvider(BaseComponent baseComponent) {
            this.baseComponent = baseComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public JobInfoProvider mo10268get() {
            return (JobInfoProvider) Preconditions.checkNotNull(this.baseComponent.getJobInfoProvider(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_BaseComponent_getJobScheduler implements Provider<JobScheduler> {
        private final BaseComponent baseComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_BaseComponent_getJobScheduler(BaseComponent baseComponent) {
            this.baseComponent = baseComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public JobScheduler mo10268get() {
            return (JobScheduler) Preconditions.checkNotNull(this.baseComponent.getJobScheduler(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_BaseComponent_getMapAuthProvider implements Provider<MapAuthenticationProvider> {
        private final BaseComponent baseComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_BaseComponent_getMapAuthProvider(BaseComponent baseComponent) {
            this.baseComponent = baseComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public MapAuthenticationProvider mo10268get() {
            return (MapAuthenticationProvider) Preconditions.checkNotNull(this.baseComponent.getMapAuthProvider(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_BaseComponent_getSharedPreferencesProvider implements Provider<SharedPreferencesProvider> {
        private final BaseComponent baseComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_BaseComponent_getSharedPreferencesProvider(BaseComponent baseComponent) {
            this.baseComponent = baseComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public SharedPreferencesProvider mo10268get() {
            return (SharedPreferencesProvider) Preconditions.checkNotNull(this.baseComponent.getSharedPreferencesProvider(), "Cannot return null from a non-@Nullable component method");
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private WJErrorMapper<ConnectionFailureException> getWJErrorMapperOfConnectionFailureException() {
        ErrorCodeMapperModule errorCodeMapperModule = this.errorCodeMapperModule;
        return ErrorCodeMapperModule_ProvidesConnectionFailureMapperFactory.proxyProvidesConnectionFailureMapper(errorCodeMapperModule, ErrorCodeMapperModule_ProvidesBLETransportOperationErrorDetailsProviderFactory.proxyProvidesBLETransportOperationErrorDetailsProvider(errorCodeMapperModule));
    }

    private WJErrorMapper<ProvisionerCommandError> getWJErrorMapperOfProvisionerCommandError() {
        ErrorCodeMapperModule errorCodeMapperModule = this.errorCodeMapperModule;
        return ErrorCodeMapperModule_ProvidesProvisionerCommandErrorMapperFactory.proxyProvidesProvisionerCommandErrorMapper(errorCodeMapperModule, ErrorCodeMapperModule_ProvidesBLETransportOperationErrorDetailsProviderFactory.proxyProvidesBLETransportOperationErrorDetailsProvider(errorCodeMapperModule));
    }

    private WJErrorMapper<TrustProviderInitializationFailedException> getWJErrorMapperOfTrustProviderInitializationFailedException() {
        return ErrorCodeMapperModule_ProvidesTrustProviderInitializationErrorMapperFactory.proxyProvidesTrustProviderInitializationErrorMapper(this.errorCodeMapperModule, getWJErrorMapperOfProvisionerCommandError(), ErrorCodeMapperModule_ProvidesDSSClientErrorMapperFactory.proxyProvidesDSSClientErrorMapper(this.errorCodeMapperModule));
    }

    private void initialize(Builder builder) {
        this.providesIsDebugEnabledProvider = DoubleCheck.provider(ProvisioningConfigurationModule_ProvidesIsDebugEnabledFactory.create(builder.provisioningConfigurationModule));
        this.providesProvisioningServiceConfigurationProvider = DoubleCheck.provider(ProvisioningConfigurationModule_ProvidesProvisioningServiceConfigurationFactory.create(builder.provisioningConfigurationModule));
        this.providesWorkflowConfigurationProvider = DoubleCheck.provider(ProvisioningConfigurationModule_ProvidesWorkflowConfigurationFactory.create(builder.provisioningConfigurationModule));
        this.providesProvisioningMethodProvider = DoubleCheck.provider(ProvisioningConfigurationModule_ProvidesProvisioningMethodFactory.create(builder.provisioningConfigurationModule));
        this.getMapAuthProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_BaseComponent_getMapAuthProvider(builder.baseComponent);
        this.providesProvisionerClientDataProvider = DoubleCheck.provider(ProvisioningConfigurationModule_ProvidesProvisionerClientDataFactory.create(builder.provisioningConfigurationModule, this.providesProvisioningServiceConfigurationProvider, this.getMapAuthProvider));
        this.providesProvisionerInfoProvider = DoubleCheck.provider(ProvisioningConfigurationModule_ProvidesProvisionerInfoFactory.create(builder.provisioningConfigurationModule, this.providesProvisionerClientDataProvider));
        this.providesTrustStateProvider = DoubleCheck.provider(ProvisioningConfigurationModule_ProvidesTrustStateFactory.create(builder.provisioningConfigurationModule, this.providesWorkflowConfigurationProvider, this.providesProvisioningMethodProvider));
        this.getContextProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_BaseComponent_getContext(builder.baseComponent);
        this.providesWifiLockerProvider = DoubleCheck.provider(ProvisioningServicesModule_ProvidesWifiLockerFactory.create(builder.provisioningServicesModule, this.getMapAuthProvider, this.getContextProvider));
        this.providesDSSServiceConfigurationProvider = DoubleCheck.provider(ProvisioningConfigurationModule_ProvidesDSSServiceConfigurationFactory.create(builder.provisioningConfigurationModule));
        this.providesDSSClientProvider = DoubleCheck.provider(ProvisioningServicesModule_ProvidesDSSClientFactory.create(builder.provisioningServicesModule, this.getContextProvider, this.providesDSSServiceConfigurationProvider));
        this.getJobSchedulerProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_BaseComponent_getJobScheduler(builder.baseComponent);
        this.getJobInfoProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_BaseComponent_getJobInfoProvider(builder.baseComponent);
        this.getSharedPreferencesProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_BaseComponent_getSharedPreferencesProvider(builder.baseComponent);
        this.getClockProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_BaseComponent_getClock(builder.baseComponent);
        this.providesFFSWhiteListPolicyCoordinatorProvider = DoubleCheck.provider(ProvisioningServicesModule_ProvidesFFSWhiteListPolicyCoordinatorFactory.create(builder.provisioningServicesModule, this.getContextProvider, this.getJobSchedulerProvider, this.getJobInfoProvider, this.getSharedPreferencesProvider, this.getClockProvider, this.providesDSSClientProvider, this.providesProvisionerClientDataProvider));
        this.providesMetricsFactoryProvider = DoubleCheck.provider(MetricsModule_ProvidesMetricsFactoryFactory.create(builder.metricsModule, this.getContextProvider, this.providesProvisionerClientDataProvider));
        this.providesMetricsRecorderProvider = DoubleCheck.provider(MetricsModule_ProvidesMetricsRecorderProviderFactory.create(builder.metricsModule, this.providesMetricsFactoryProvider, this.getMapAuthProvider, this.providesProvisionerClientDataProvider));
        this.providesCredentialSyncMetricsRecorderProvider = DoubleCheck.provider(MetricsModule_ProvidesCredentialSyncMetricsRecorderFactory.create(builder.metricsModule, this.providesMetricsRecorderProvider));
        this.providesDSHSSetCredentialsAPIProvider = DoubleCheck.provider(ProvisioningServicesModule_ProvidesDSHSSetCredentialsAPIFactory.create(builder.provisioningServicesModule, this.getContextProvider, this.providesCredentialSyncMetricsRecorderProvider));
        this.providesCredentialSyncCoordinatorProvider = DoubleCheck.provider(ProvisioningServicesModule_ProvidesCredentialSyncCoordinatorFactory.create(builder.provisioningServicesModule, this.getContextProvider, this.providesProvisionerInfoProvider, this.providesDSSClientProvider, this.getJobSchedulerProvider, this.getJobInfoProvider, this.getClockProvider, this.getSharedPreferencesProvider));
        this.getGsonProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_BaseComponent_getGson(builder.baseComponent);
        this.providesAssociatedDeviceCredentialsSyncCoordinatorProvider = DoubleCheck.provider(ProvisioningServicesModule_ProvidesAssociatedDeviceCredentialsSyncCoordinatorFactory.create(builder.provisioningServicesModule, this.getContextProvider, this.providesProvisionerInfoProvider, this.providesDSSClientProvider, this.getJobSchedulerProvider, this.getJobInfoProvider, this.getClockProvider, this.getSharedPreferencesProvider, this.getGsonProvider));
        this.providesWhiteListPolicyUpdateListenerProvider = DoubleCheck.provider(ProvisioningServicesModule_ProvidesWhiteListPolicyUpdateListenerFactory.create(builder.provisioningServicesModule, this.getContextProvider, this.providesProvisionerClientDataProvider));
        this.providesAutoDiscoverySyncCoordinatorProvider = DoubleCheck.provider(ProvisioningServicesModule_ProvidesAutoDiscoverySyncCoordinatorFactory.create(builder.provisioningServicesModule, this.getContextProvider, this.providesProvisionerInfoProvider, this.providesDSSClientProvider, this.getJobSchedulerProvider, this.getJobInfoProvider, this.getClockProvider, this.getSharedPreferencesProvider));
        this.providesAutoDiscoveryMetricsRecorderProvider = DoubleCheck.provider(MetricsModule_ProvidesAutoDiscoveryMetricsRecorderFactory.create(builder.metricsModule, this.providesMetricsRecorderProvider));
        this.providesFFSProvisioningServiceMetricsRecorderProvider = DoubleCheck.provider(MetricsModule_ProvidesFFSProvisioningServiceMetricsRecorderFactory.create(builder.metricsModule, this.providesMetricsRecorderProvider));
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesComponent
    public BluetoothSupportProvider getBluetoothSupportProvider() {
        return (BluetoothSupportProvider) Preconditions.checkNotNull(this.baseComponent.getBluetoothSupportProvider(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesComponent
    public Clock getClock() {
        return (Clock) Preconditions.checkNotNull(this.baseComponent.getClock(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesComponent
    public Context getContext() {
        return (Context) Preconditions.checkNotNull(this.baseComponent.getContext(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesComponent
    public CurrentWifiNetworkProvider getCurrentWifiNetworkProvider() {
        return (CurrentWifiNetworkProvider) Preconditions.checkNotNull(this.baseComponent.getCurrentWifiNetworkProvider(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesComponent
    public DevicePowerMonitor getDevicePowerMonitor() {
        return ProvisioningServicesModule_ProvidesDevicePowerMonitorFactory.proxyProvidesDevicePowerMonitor(this.provisioningServicesModule, (Context) Preconditions.checkNotNull(this.baseComponent.getContext(), "Cannot return null from a non-@Nullable component method"), (DevicePowerStatusProvider) Preconditions.checkNotNull(this.baseComponent.getDevicePowerStatusProvider(), "Cannot return null from a non-@Nullable component method"), this.providesMetricsRecorderProvider.mo10268get(), this.providesProvisionerClientDataProvider.mo10268get());
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesComponent
    public DevicePowerStatusProvider getDevicePowerStatusProvider() {
        return (DevicePowerStatusProvider) Preconditions.checkNotNull(this.baseComponent.getDevicePowerStatusProvider(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesComponent
    public FFSArcusSyncCoordinator getFFSArcusSyncCoordinator() {
        return (FFSArcusSyncCoordinator) Preconditions.checkNotNull(this.baseComponent.getFFSArcusSyncCoordinator(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesComponent
    public Gson getGson() {
        return (Gson) Preconditions.checkNotNull(this.baseComponent.getGson(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesComponent
    public JobInfoProvider getJobInfoProvider() {
        return (JobInfoProvider) Preconditions.checkNotNull(this.baseComponent.getJobInfoProvider(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesComponent
    public JobScheduler getJobScheduler() {
        return (JobScheduler) Preconditions.checkNotNull(this.baseComponent.getJobScheduler(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesComponent
    public LocationPermissionsHelper getLocationPermissionsHelper() {
        return (LocationPermissionsHelper) Preconditions.checkNotNull(this.baseComponent.getLocationPermissionHelper(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesComponent
    public MapAuthenticationProvider getMapAuthProvider() {
        return (MapAuthenticationProvider) Preconditions.checkNotNull(this.baseComponent.getMapAuthProvider(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesComponent
    public SharedPreferencesProvider getSharedPreferencesProvider() {
        return (SharedPreferencesProvider) Preconditions.checkNotNull(this.baseComponent.getSharedPreferencesProvider(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesComponent
    public WifiManager getWifiManager() {
        return (WifiManager) Preconditions.checkNotNull(this.baseComponent.getWifiManager(), "Cannot return null from a non-@Nullable component method");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesComponent
    public CustomerProvisioneesSetupStatusSyncCoordinator provideCustomerProvisioneesSetupStatusSyncCoordinator() {
        return this.providesAutoDiscoverySyncCoordinatorProvider.mo10268get();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesComponent
    public AssociatedDeviceCredentialsSyncCoordinator providesAssociatedDeviceCredentialsSyncCoordinator() {
        return this.providesAssociatedDeviceCredentialsSyncCoordinatorProvider.mo10268get();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesComponent
    public AutoDiscoveryMetricsRecorder providesAutoDiscoveryMetricsRecorder() {
        return this.providesAutoDiscoveryMetricsRecorderProvider.mo10268get();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesComponent
    public ZigbeeCredentialsSyncCoordinator providesCredentialSyncCoordinator() {
        return this.providesCredentialSyncCoordinatorProvider.mo10268get();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesComponent
    public CredentialSyncMetricsRecorder providesCredentialSyncMetricsRecorder() {
        return this.providesCredentialSyncMetricsRecorderProvider.mo10268get();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesComponent
    public DSSClient providesDSSClient() {
        return this.providesDSSClientProvider.mo10268get();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesComponent
    public FFSProvisioningServiceMetricsRecorder providesFFSProvisioningServiceMetricsRecorder() {
        return this.providesFFSProvisioningServiceMetricsRecorderProvider.mo10268get();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesComponent
    public WhiteListPolicyCoordinator providesFFSWhiteListPolicyCoordinator() {
        return this.providesFFSWhiteListPolicyCoordinatorProvider.mo10268get();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesComponent
    public Boolean providesIsDebugEnabled() {
        return this.providesIsDebugEnabledProvider.mo10268get();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesComponent
    public MetricsFactory providesMetricsFactory() {
        return this.providesMetricsFactoryProvider.mo10268get();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesComponent
    public MetricsRecorderProvider providesMetricsRecorderProvider() {
        return this.providesMetricsRecorderProvider.mo10268get();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesComponent
    public ProvisionerClientData providesProvisionerClientData() {
        return this.providesProvisionerClientDataProvider.mo10268get();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesComponent
    public ProvisionerInfo providesProvisionerInfo() {
        return this.providesProvisionerInfoProvider.mo10268get();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesComponent
    public ProvisioningMethod providesProvisioningMethod() {
        return this.providesProvisioningMethodProvider.mo10268get();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesComponent
    public ProvisioningServiceConfiguration providesProvisioningServiceConfiguration() {
        return this.providesProvisioningServiceConfigurationProvider.mo10268get();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesComponent
    public TrustProvider.TrustState providesTrustState() {
        return this.providesTrustStateProvider.mo10268get();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesComponent
    public WJErrorMapper<Throwable> providesWJErrorMapper() {
        return ErrorCodeMapperModule_ProvidesErrorMapperFactory.proxyProvidesErrorMapper(this.errorCodeMapperModule, getWJErrorMapperOfProvisionerCommandError(), ErrorCodeMapperModule_ProvidesDSSClientErrorMapperFactory.proxyProvidesDSSClientErrorMapper(this.errorCodeMapperModule), ErrorCodeMapperModule_ProvidesProvisioningFailureMapperFactory.proxyProvidesProvisioningFailureMapper(this.errorCodeMapperModule), ErrorCodeMapperModule_ProvidesPreconditionalFailureMapperFactory.proxyProvidesPreconditionalFailureMapper(this.errorCodeMapperModule), getWJErrorMapperOfConnectionFailureException(), getWJErrorMapperOfTrustProviderInitializationFailedException(), ErrorCodeMapperModule_ProvidesProvisionignNotGrantedMapperFactory.proxyProvidesProvisionignNotGrantedMapper(this.errorCodeMapperModule), ErrorCodeMapperModule_ProvidesWiFiSyncAuthTokenFailedValidationMapperFactory.proxyProvidesWiFiSyncAuthTokenFailedValidationMapper(this.errorCodeMapperModule), ErrorCodeMapperModule_ProvidesWiFiSyncAuthTokenNotFoundMapperFactory.proxyProvidesWiFiSyncAuthTokenNotFoundMapper(this.errorCodeMapperModule), ErrorCodeMapperModule_ProvidesWorkflowFailureExceptionErrorMapperFactory.proxyProvidesWorkflowFailureExceptionErrorMapper(this.errorCodeMapperModule));
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesComponent
    public WhiteListPolicyUpdateListener providesWhiteListPolicyUpdateListener() {
        return this.providesWhiteListPolicyUpdateListenerProvider.mo10268get();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesComponent
    public WifiLocker providesWifiLocker() {
        return this.providesWifiLockerProvider.mo10268get();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesComponent
    public WorkflowConfiguration providesWorkflowConfiguration() {
        return this.providesWorkflowConfigurationProvider.mo10268get();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesComponent
    public DSHSSetCredentialsAPI providesZigbeeCredentialSyncIntent() {
        return this.providesDSHSSetCredentialsAPIProvider.mo10268get();
    }

    private DaggerProvisionerServicesComponent(Builder builder) {
        this.baseComponent = builder.baseComponent;
        this.provisioningServicesModule = builder.provisioningServicesModule;
        this.errorCodeMapperModule = builder.errorCodeMapperModule;
        initialize(builder);
    }
}
