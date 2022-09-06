package com.amazon.whisperjoin.deviceprovisioningservice.di.components;

import android.content.Context;
import com.amazon.devicesetupservice.v1.ProvisionerInfo;
import com.amazon.whisperbridge.ble.BleManager;
import com.amazon.whisperjoin.common.sharedtypes.cryptography.TrustProvider;
import com.amazon.whisperjoin.common.sharedtypes.smarthome.intent.DSHSSetCredentialsAPI;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.FFSArcusSettings;
import com.amazon.whisperjoin.deviceprovisioningservice.device.DiscoverySettings;
import com.amazon.whisperjoin.deviceprovisioningservice.device.ProvisioningManagerProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.WorkflowModule;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.WorkflowModule_ProvidesActionCreatorFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.WorkflowModule_ProvidesBasicBleFactoryFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.WorkflowModule_ProvidesBleManagerFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.WorkflowModule_ProvidesControlledSetupWorkflowFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.WorkflowModule_ProvidesDeviceActionsExecutorFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.WorkflowModule_ProvidesFFSArcusSettingsFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.WorkflowModule_ProvidesGetAvailableWifiNetworksFromDSSFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.WorkflowModule_ProvidesPhilipsZigbeeBleWorkflowFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.WorkflowModule_ProvidesSaveWifiNetworkToDSSFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.WorkflowModule_ProvidesSmartHomeProvisioningEventReporterFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.WorkflowModule_ProvidesWifiSimpleSetupWorkflowFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.WorkflowModule_ProvidesWorkflowEventReporterFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.WorkflowModule_ProvidesWorkflowIdProviderFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.WorkflowModule_ProvidesWorkflowResultLoggerFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.WorkflowModule_ProvidesWorkflowStateStreamFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.WorkflowModule_ProvidesWorkflowUpdateProducerFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.identity.MapAuthenticationProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisionerClientData;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisioningServiceConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.util.Clock;
import com.amazon.whisperjoin.deviceprovisioningservice.util.LocationPermissionsHelper;
import com.amazon.whisperjoin.deviceprovisioningservice.wifi.CurrentWifiNetworkProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.ControlledSetupWorkflow;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.PhilipsZigbeeBleWorkflow;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.WifiSimpleSetupZeroTouchWorkflow;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.WorkflowConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.WorkflowStateStream;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.deferred.DeferredDiscoveryHandler;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.WorkflowUpdateProducer;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceActionCreator;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceActionsExecutor;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceEventStream;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.WJDeviceSetupModeSupportedPredicate;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.BluetoothSupportProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioningMethod;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.SmartHomeProvisioningEventReporter;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.WorkflowEventReporter;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.WorkflowResultLogger;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorMapper;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.WorkflowIdProvider;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import com.amazon.whisperjoin.metrics.MetricsRecorderProvider;
import com.amazon.whisperjoin.provisionerSDK.devices.basic.connection.BasicBleDeviceFactory;
import com.google.gson.Gson;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import io.reactivex.rxjava3.core.Single;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class DaggerWorkflowComponent implements WorkflowComponent {
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_getBluetoothSupportProvider getBluetoothSupportProvider;
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_getClock getClockProvider;
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_getContext getContextProvider;
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_getCurrentWifiNetworkProvider getCurrentWifiNetworkProvider;
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_getGson getGsonProvider;
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_getLocationPermissionsHelper getLocationPermissionsHelperProvider;
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_getMapAuthProvider getMapAuthProvider;
    private Provider<DeviceActionCreator> providesActionCreatorProvider;
    private Provider<BasicBleDeviceFactory> providesBasicBleFactoryProvider;
    private Provider<BleManager> providesBleManagerProvider;
    private Provider<ControlledSetupWorkflow> providesControlledSetupWorkflowProvider;
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesDSHSSetCredentialsAPI providesDSHSSetCredentialsAPIProvider;
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesDSSClient providesDSSClientProvider;
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesDeferredDiscoveryHandler providesDeferredDiscoveryHandlerProvider;
    private Provider<DeviceActionsExecutor> providesDeviceActionsExecutorProvider;
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesDeviceEventStream providesDeviceEventStreamProvider;
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesDiscoverySettings providesDiscoverySettingsProvider;
    private Provider<Single<FFSArcusSettings>> providesFFSArcusSettingsProvider;
    private WorkflowModule_ProvidesGetAvailableWifiNetworksFromDSSFactory providesGetAvailableWifiNetworksFromDSSProvider;
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesMetricsRecorderProvider providesMetricsRecorderProvider;
    private Provider<PhilipsZigbeeBleWorkflow> providesPhilipsZigbeeBleWorkflowProvider;
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesProvisionerClientData providesProvisionerClientDataProvider;
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesProvisionerInfo providesProvisionerInfoProvider;
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesProvisioningManagerProvider providesProvisioningManagerProvider;
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesProvisioningMethod providesProvisioningMethodProvider;
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesProvisioningServiceConfiguration providesProvisioningServiceConfigurationProvider;
    private WorkflowModule_ProvidesSaveWifiNetworkToDSSFactory providesSaveWifiNetworkToDSSProvider;
    private Provider<SmartHomeProvisioningEventReporter> providesSmartHomeProvisioningEventReporterProvider;
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesTrustState providesTrustStateProvider;
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesWJDeviceSetupModeSupportedPredicate providesWJDeviceSetupModeSupportedPredicateProvider;
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesWJErrorMapper providesWJErrorMapperProvider;
    private Provider<WifiSimpleSetupZeroTouchWorkflow> providesWifiSimpleSetupWorkflowProvider;
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesWorkflowConfiguration providesWorkflowConfigurationProvider;
    private Provider<WorkflowEventReporter> providesWorkflowEventReporterProvider;
    private Provider<WorkflowIdProvider> providesWorkflowIdProvider;
    private Provider<WorkflowResultLogger> providesWorkflowResultLoggerProvider;
    private Provider<WorkflowStateStream> providesWorkflowStateStreamProvider;
    private Provider<WorkflowUpdateProducer> providesWorkflowUpdateProducerProvider;

    /* loaded from: classes13.dex */
    public static final class Builder {
        private ProvisioningComponent provisioningComponent;
        private WorkflowModule workflowModule;

        public WorkflowComponent build() {
            Preconditions.checkBuilderRequirement(this.workflowModule, WorkflowModule.class);
            Preconditions.checkBuilderRequirement(this.provisioningComponent, ProvisioningComponent.class);
            return new DaggerWorkflowComponent(this);
        }

        public Builder provisioningComponent(ProvisioningComponent provisioningComponent) {
            this.provisioningComponent = (ProvisioningComponent) Preconditions.checkNotNull(provisioningComponent);
            return this;
        }

        public Builder workflowModule(WorkflowModule workflowModule) {
            this.workflowModule = (WorkflowModule) Preconditions.checkNotNull(workflowModule);
            return this;
        }

        private Builder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_getBluetoothSupportProvider implements Provider<BluetoothSupportProvider> {
        private final ProvisioningComponent provisioningComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_getBluetoothSupportProvider(ProvisioningComponent provisioningComponent) {
            this.provisioningComponent = provisioningComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public BluetoothSupportProvider mo10268get() {
            return (BluetoothSupportProvider) Preconditions.checkNotNull(this.provisioningComponent.getBluetoothSupportProvider(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_getClock implements Provider<Clock> {
        private final ProvisioningComponent provisioningComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_getClock(ProvisioningComponent provisioningComponent) {
            this.provisioningComponent = provisioningComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public Clock mo10268get() {
            return (Clock) Preconditions.checkNotNull(this.provisioningComponent.getClock(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_getContext implements Provider<Context> {
        private final ProvisioningComponent provisioningComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_getContext(ProvisioningComponent provisioningComponent) {
            this.provisioningComponent = provisioningComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public Context mo10268get() {
            return (Context) Preconditions.checkNotNull(this.provisioningComponent.getContext(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_getCurrentWifiNetworkProvider implements Provider<CurrentWifiNetworkProvider> {
        private final ProvisioningComponent provisioningComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_getCurrentWifiNetworkProvider(ProvisioningComponent provisioningComponent) {
            this.provisioningComponent = provisioningComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public CurrentWifiNetworkProvider mo10268get() {
            return (CurrentWifiNetworkProvider) Preconditions.checkNotNull(this.provisioningComponent.getCurrentWifiNetworkProvider(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_getGson implements Provider<Gson> {
        private final ProvisioningComponent provisioningComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_getGson(ProvisioningComponent provisioningComponent) {
            this.provisioningComponent = provisioningComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public Gson mo10268get() {
            return (Gson) Preconditions.checkNotNull(this.provisioningComponent.getGson(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_getLocationPermissionsHelper implements Provider<LocationPermissionsHelper> {
        private final ProvisioningComponent provisioningComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_getLocationPermissionsHelper(ProvisioningComponent provisioningComponent) {
            this.provisioningComponent = provisioningComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public LocationPermissionsHelper mo10268get() {
            return (LocationPermissionsHelper) Preconditions.checkNotNull(this.provisioningComponent.getLocationPermissionsHelper(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_getMapAuthProvider implements Provider<MapAuthenticationProvider> {
        private final ProvisioningComponent provisioningComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_getMapAuthProvider(ProvisioningComponent provisioningComponent) {
            this.provisioningComponent = provisioningComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public MapAuthenticationProvider mo10268get() {
            return (MapAuthenticationProvider) Preconditions.checkNotNull(this.provisioningComponent.getMapAuthProvider(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesDSHSSetCredentialsAPI implements Provider<DSHSSetCredentialsAPI> {
        private final ProvisioningComponent provisioningComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesDSHSSetCredentialsAPI(ProvisioningComponent provisioningComponent) {
            this.provisioningComponent = provisioningComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public DSHSSetCredentialsAPI mo10268get() {
            return (DSHSSetCredentialsAPI) Preconditions.checkNotNull(this.provisioningComponent.providesDSHSSetCredentialsAPI(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesDSSClient implements Provider<DSSClient> {
        private final ProvisioningComponent provisioningComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesDSSClient(ProvisioningComponent provisioningComponent) {
            this.provisioningComponent = provisioningComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public DSSClient mo10268get() {
            return (DSSClient) Preconditions.checkNotNull(this.provisioningComponent.providesDSSClient(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesDeferredDiscoveryHandler implements Provider<DeferredDiscoveryHandler> {
        private final ProvisioningComponent provisioningComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesDeferredDiscoveryHandler(ProvisioningComponent provisioningComponent) {
            this.provisioningComponent = provisioningComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public DeferredDiscoveryHandler mo10268get() {
            return (DeferredDiscoveryHandler) Preconditions.checkNotNull(this.provisioningComponent.providesDeferredDiscoveryHandler(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesDeviceEventStream implements Provider<DeviceEventStream> {
        private final ProvisioningComponent provisioningComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesDeviceEventStream(ProvisioningComponent provisioningComponent) {
            this.provisioningComponent = provisioningComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public DeviceEventStream mo10268get() {
            return (DeviceEventStream) Preconditions.checkNotNull(this.provisioningComponent.providesDeviceEventStream(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesDiscoverySettings implements Provider<DiscoverySettings> {
        private final ProvisioningComponent provisioningComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesDiscoverySettings(ProvisioningComponent provisioningComponent) {
            this.provisioningComponent = provisioningComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public DiscoverySettings mo10268get() {
            return (DiscoverySettings) Preconditions.checkNotNull(this.provisioningComponent.providesDiscoverySettings(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesMetricsRecorderProvider implements Provider<MetricsRecorderProvider> {
        private final ProvisioningComponent provisioningComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesMetricsRecorderProvider(ProvisioningComponent provisioningComponent) {
            this.provisioningComponent = provisioningComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public MetricsRecorderProvider mo10268get() {
            return (MetricsRecorderProvider) Preconditions.checkNotNull(this.provisioningComponent.providesMetricsRecorderProvider(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesProvisionerClientData implements Provider<ProvisionerClientData> {
        private final ProvisioningComponent provisioningComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesProvisionerClientData(ProvisioningComponent provisioningComponent) {
            this.provisioningComponent = provisioningComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public ProvisionerClientData mo10268get() {
            return (ProvisionerClientData) Preconditions.checkNotNull(this.provisioningComponent.providesProvisionerClientData(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesProvisionerInfo implements Provider<ProvisionerInfo> {
        private final ProvisioningComponent provisioningComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesProvisionerInfo(ProvisioningComponent provisioningComponent) {
            this.provisioningComponent = provisioningComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public ProvisionerInfo mo10268get() {
            return (ProvisionerInfo) Preconditions.checkNotNull(this.provisioningComponent.providesProvisionerInfo(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesProvisioningManagerProvider implements Provider<ProvisioningManagerProvider> {
        private final ProvisioningComponent provisioningComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesProvisioningManagerProvider(ProvisioningComponent provisioningComponent) {
            this.provisioningComponent = provisioningComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public ProvisioningManagerProvider mo10268get() {
            return (ProvisioningManagerProvider) Preconditions.checkNotNull(this.provisioningComponent.providesProvisioningManagerProvider(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesProvisioningMethod implements Provider<ProvisioningMethod> {
        private final ProvisioningComponent provisioningComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesProvisioningMethod(ProvisioningComponent provisioningComponent) {
            this.provisioningComponent = provisioningComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public ProvisioningMethod mo10268get() {
            return (ProvisioningMethod) Preconditions.checkNotNull(this.provisioningComponent.providesProvisioningMethod(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesProvisioningServiceConfiguration implements Provider<ProvisioningServiceConfiguration> {
        private final ProvisioningComponent provisioningComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesProvisioningServiceConfiguration(ProvisioningComponent provisioningComponent) {
            this.provisioningComponent = provisioningComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public ProvisioningServiceConfiguration mo10268get() {
            return (ProvisioningServiceConfiguration) Preconditions.checkNotNull(this.provisioningComponent.providesProvisioningServiceConfiguration(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesTrustState implements Provider<TrustProvider.TrustState> {
        private final ProvisioningComponent provisioningComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesTrustState(ProvisioningComponent provisioningComponent) {
            this.provisioningComponent = provisioningComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public TrustProvider.TrustState mo10268get() {
            return (TrustProvider.TrustState) Preconditions.checkNotNull(this.provisioningComponent.providesTrustState(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesWJDeviceSetupModeSupportedPredicate implements Provider<WJDeviceSetupModeSupportedPredicate> {
        private final ProvisioningComponent provisioningComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesWJDeviceSetupModeSupportedPredicate(ProvisioningComponent provisioningComponent) {
            this.provisioningComponent = provisioningComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public WJDeviceSetupModeSupportedPredicate mo10268get() {
            return (WJDeviceSetupModeSupportedPredicate) Preconditions.checkNotNull(this.provisioningComponent.providesWJDeviceSetupModeSupportedPredicate(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesWJErrorMapper implements Provider<WJErrorMapper<Throwable>> {
        private final ProvisioningComponent provisioningComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesWJErrorMapper(ProvisioningComponent provisioningComponent) {
            this.provisioningComponent = provisioningComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public WJErrorMapper<Throwable> mo10268get() {
            return (WJErrorMapper) Preconditions.checkNotNull(this.provisioningComponent.providesWJErrorMapper(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesWorkflowConfiguration implements Provider<WorkflowConfiguration> {
        private final ProvisioningComponent provisioningComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesWorkflowConfiguration(ProvisioningComponent provisioningComponent) {
            this.provisioningComponent = provisioningComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public WorkflowConfiguration mo10268get() {
            return (WorkflowConfiguration) Preconditions.checkNotNull(this.provisioningComponent.providesWorkflowConfiguration(), "Cannot return null from a non-@Nullable component method");
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(Builder builder) {
        this.providesActionCreatorProvider = DoubleCheck.provider(WorkflowModule_ProvidesActionCreatorFactory.create(builder.workflowModule));
        this.getMapAuthProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_getMapAuthProvider(builder.provisioningComponent);
        this.providesProvisioningManagerProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesProvisioningManagerProvider(builder.provisioningComponent);
        this.providesDeviceEventStreamProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesDeviceEventStream(builder.provisioningComponent);
        this.providesDSSClientProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesDSSClient(builder.provisioningComponent);
        this.providesProvisioningServiceConfigurationProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesProvisioningServiceConfiguration(builder.provisioningComponent);
        this.providesWorkflowConfigurationProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesWorkflowConfiguration(builder.provisioningComponent);
        this.providesProvisionerClientDataProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesProvisionerClientData(builder.provisioningComponent);
        this.providesDiscoverySettingsProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesDiscoverySettings(builder.provisioningComponent);
        this.getBluetoothSupportProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_getBluetoothSupportProvider(builder.provisioningComponent);
        this.getLocationPermissionsHelperProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_getLocationPermissionsHelper(builder.provisioningComponent);
        this.providesMetricsRecorderProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesMetricsRecorderProvider(builder.provisioningComponent);
        this.getClockProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_getClock(builder.provisioningComponent);
        this.providesWJDeviceSetupModeSupportedPredicateProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesWJDeviceSetupModeSupportedPredicate(builder.provisioningComponent);
        this.providesProvisioningMethodProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesProvisioningMethod(builder.provisioningComponent);
        this.getCurrentWifiNetworkProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_getCurrentWifiNetworkProvider(builder.provisioningComponent);
        this.providesTrustStateProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesTrustState(builder.provisioningComponent);
        this.providesGetAvailableWifiNetworksFromDSSProvider = WorkflowModule_ProvidesGetAvailableWifiNetworksFromDSSFactory.create(builder.workflowModule, this.providesDSSClientProvider, this.getCurrentWifiNetworkProvider, this.providesProvisioningMethodProvider, this.providesTrustStateProvider);
        this.providesSaveWifiNetworkToDSSProvider = WorkflowModule_ProvidesSaveWifiNetworkToDSSFactory.create(builder.workflowModule, this.providesDSSClientProvider, this.providesProvisioningMethodProvider, this.providesTrustStateProvider);
        this.providesDeferredDiscoveryHandlerProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesDeferredDiscoveryHandler(builder.provisioningComponent);
        this.providesFFSArcusSettingsProvider = DoubleCheck.provider(WorkflowModule_ProvidesFFSArcusSettingsFactory.create(builder.workflowModule));
        this.providesDeviceActionsExecutorProvider = DoubleCheck.provider(WorkflowModule_ProvidesDeviceActionsExecutorFactory.create(builder.workflowModule, this.getMapAuthProvider, this.providesProvisioningManagerProvider, this.providesDeviceEventStreamProvider, this.providesDSSClientProvider, this.providesProvisioningServiceConfigurationProvider, this.providesWorkflowConfigurationProvider, this.providesProvisionerClientDataProvider, this.providesDiscoverySettingsProvider, this.getBluetoothSupportProvider, this.getLocationPermissionsHelperProvider, this.providesMetricsRecorderProvider, this.getClockProvider, this.providesWJDeviceSetupModeSupportedPredicateProvider, this.providesProvisioningMethodProvider, this.providesGetAvailableWifiNetworksFromDSSProvider, this.providesSaveWifiNetworkToDSSProvider, this.getCurrentWifiNetworkProvider, this.providesDeferredDiscoveryHandlerProvider, this.providesFFSArcusSettingsProvider));
        this.providesProvisionerInfoProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesProvisionerInfo(builder.provisioningComponent);
        this.providesWJErrorMapperProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesWJErrorMapper(builder.provisioningComponent);
        this.providesWorkflowEventReporterProvider = DoubleCheck.provider(WorkflowModule_ProvidesWorkflowEventReporterFactory.create(builder.workflowModule, this.providesDSSClientProvider, this.providesProvisionerInfoProvider, this.providesProvisioningMethodProvider, this.providesWJErrorMapperProvider));
        this.providesWorkflowResultLoggerProvider = DoubleCheck.provider(WorkflowModule_ProvidesWorkflowResultLoggerFactory.create(builder.workflowModule, this.providesProvisioningMethodProvider, this.providesWJErrorMapperProvider));
        this.providesWorkflowStateStreamProvider = DoubleCheck.provider(WorkflowModule_ProvidesWorkflowStateStreamFactory.create(builder.workflowModule, this.providesActionCreatorProvider, this.providesDeviceActionsExecutorProvider, this.providesDeviceEventStreamProvider, this.providesWorkflowEventReporterProvider, this.providesWorkflowResultLoggerProvider));
        this.providesWorkflowUpdateProducerProvider = DoubleCheck.provider(WorkflowModule_ProvidesWorkflowUpdateProducerFactory.create(builder.workflowModule, this.providesActionCreatorProvider, this.providesWJErrorMapperProvider));
        this.providesControlledSetupWorkflowProvider = DoubleCheck.provider(WorkflowModule_ProvidesControlledSetupWorkflowFactory.create(builder.workflowModule, this.providesWorkflowStateStreamProvider, this.providesActionCreatorProvider, this.providesWorkflowUpdateProducerProvider, this.providesWorkflowConfigurationProvider, this.providesDSSClientProvider, this.providesTrustStateProvider));
        this.providesWorkflowIdProvider = DoubleCheck.provider(WorkflowModule_ProvidesWorkflowIdProviderFactory.create(builder.workflowModule));
        this.providesWifiSimpleSetupWorkflowProvider = DoubleCheck.provider(WorkflowModule_ProvidesWifiSimpleSetupWorkflowFactory.create(builder.workflowModule, this.providesWorkflowStateStreamProvider, this.providesActionCreatorProvider, this.providesTrustStateProvider, this.providesWorkflowIdProvider));
        this.getContextProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_getContext(builder.provisioningComponent);
        this.providesBleManagerProvider = DoubleCheck.provider(WorkflowModule_ProvidesBleManagerFactory.create(builder.workflowModule, this.getContextProvider));
        this.providesBasicBleFactoryProvider = DoubleCheck.provider(WorkflowModule_ProvidesBasicBleFactoryFactory.create(builder.workflowModule, this.getContextProvider, this.providesBleManagerProvider));
        this.providesDSHSSetCredentialsAPIProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesDSHSSetCredentialsAPI(builder.provisioningComponent);
        this.providesSmartHomeProvisioningEventReporterProvider = DoubleCheck.provider(WorkflowModule_ProvidesSmartHomeProvisioningEventReporterFactory.create(builder.workflowModule, this.providesProvisionerInfoProvider, this.providesWJErrorMapperProvider, this.providesDSSClientProvider));
        this.getGsonProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_getGson(builder.provisioningComponent);
        this.providesPhilipsZigbeeBleWorkflowProvider = DoubleCheck.provider(WorkflowModule_ProvidesPhilipsZigbeeBleWorkflowFactory.create(builder.workflowModule, this.providesDSSClientProvider, this.providesProvisionerInfoProvider, this.providesBasicBleFactoryProvider, this.providesWorkflowIdProvider, this.providesDSHSSetCredentialsAPIProvider, this.providesSmartHomeProvisioningEventReporterProvider, this.providesFFSArcusSettingsProvider, this.getGsonProvider));
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.WorkflowComponent
    public ControlledSetupWorkflow getControlledSetupWorkflow() {
        return this.providesControlledSetupWorkflowProvider.mo10268get();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.WorkflowComponent
    public PhilipsZigbeeBleWorkflow getPhilipsZigbeeBleWorkflow() {
        return this.providesPhilipsZigbeeBleWorkflowProvider.mo10268get();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.WorkflowComponent
    public WifiSimpleSetupZeroTouchWorkflow getWifiSimpleSetupZeroTouchWorkflow() {
        return this.providesWifiSimpleSetupWorkflowProvider.mo10268get();
    }

    private DaggerWorkflowComponent(Builder builder) {
        initialize(builder);
    }
}
