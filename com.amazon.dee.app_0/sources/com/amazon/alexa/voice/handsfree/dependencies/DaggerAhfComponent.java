package com.amazon.alexa.voice.handsfree.dependencies;

import android.content.Context;
import android.content.Intent;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.devices.DeviceTypeInformationProvider;
import com.amazon.alexa.handsfree.metrics.FirstStartupMetricJobService_Helper_Factory;
import com.amazon.alexa.handsfree.metrics.MetricsModule_Factory;
import com.amazon.alexa.handsfree.metrics.amok.VoiceAppMetricsInitializer;
import com.amazon.alexa.handsfree.metrics.amok.VoiceAppMetricsInitializer_Factory;
import com.amazon.alexa.handsfree.metrics.dependencies.AhfMetricsModule;
import com.amazon.alexa.handsfree.metrics.dependencies.AhfMetricsModule_ProvideAttributionTagProviderFactory;
import com.amazon.alexa.handsfree.metrics.dependencies.AhfMetricsModule_ProvideCacheMetricsServiceServiceHelperFactory;
import com.amazon.alexa.handsfree.metrics.dependencies.AhfMetricsModule_ProvideMetricFactoryProviderFactory;
import com.amazon.alexa.handsfree.metrics.dependencies.AhfMetricsModule_ProvideMetricSerializerFactory;
import com.amazon.alexa.handsfree.metrics.dependencies.AhfMetricsModule_ProvideMetricsEmitterFactory;
import com.amazon.alexa.handsfree.metrics.dependencies.AhfMetricsModule_ProvideMetricsRecorderFactory;
import com.amazon.alexa.handsfree.metrics.dependencies.AhfMetricsModule_ProvideMobilyticsEventDecoratorFactory;
import com.amazon.alexa.handsfree.metrics.dependencies.AlexaMobileMetricsComponent;
import com.amazon.alexa.handsfree.metrics.mobilytics.MobilyticsMetadataProvider;
import com.amazon.alexa.handsfree.metrics.mobilytics.MobilyticsMetadataProvider_Factory;
import com.amazon.alexa.handsfree.metrics.utils.AttributionTagProvider;
import com.amazon.alexa.handsfree.metrics.utils.IdentityServiceManager;
import com.amazon.alexa.handsfree.metrics.utils.IdentityServiceManager_Factory;
import com.amazon.alexa.handsfree.metrics.utils.MobilyticsEventDecorator;
import com.amazon.alexa.handsfree.metrics.utils.MobilyticsPmetFactory;
import com.amazon.alexa.handsfree.metrics.utils.MobilyticsPmetFactory_Factory;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentityProvider;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentityProvider_Factory;
import com.amazon.alexa.handsfree.protocols.metrics.MetricRecorder;
import com.amazon.alexa.handsfree.protocols.metrics.MetricsConfiguration;
import com.amazon.alexa.handsfree.protocols.metrics.MetricsConfiguration_Factory;
import com.amazon.alexa.handsfree.protocols.metrics.MetricsEmitter;
import com.amazon.alexa.handsfree.protocols.metrics.MetricsEmitterConfig;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider_Factory;
import com.amazon.alexa.handsfree.protocols.metrics.caching.MetricSerializer;
import com.amazon.alexa.handsfree.protocols.metrics.factories.MetricFactoryProvider;
import com.amazon.alexa.handsfree.protocols.sync.DataSyncIncomingMessageHandler;
import com.amazon.alexa.handsfree.protocols.sync.DataSyncManager;
import com.amazon.alexa.handsfree.protocols.sync.DataSyncManager_Factory;
import com.amazon.alexa.handsfree.protocols.sync.DataSyncService;
import com.amazon.alexa.handsfree.protocols.sync.DataSyncService_MembersInjector;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.handsfree.protocols.utils.AlexaAppSignInContract;
import com.amazon.alexa.handsfree.protocols.utils.ApplicationInformationProvider;
import com.amazon.alexa.handsfree.protocols.utils.CrashReportRecorder;
import com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.MetricsReporter;
import com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.MetricsReporter_Factory;
import com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.RemoteConfigDeserializer;
import com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.RemoteConfigDeserializer_Factory;
import com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.RemoteConfigManager;
import com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.RemoteConfigManager_Factory;
import com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.provider.HandsFreeStateProvider;
import com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.provider.HandsFreeStateProvider_Factory;
import com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.provider.RemoteConfigProvider;
import com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.provider.RemoteConfigProvider_Factory;
import com.amazon.alexa.handsfree.settings.contract.SettingsContract;
import com.amazon.alexa.handsfree.settings.contract.SettingsSetupFlowContract;
import com.amazon.alexa.handsfree.settings.contract.dependencies.FalcoSettingContractComponent;
import com.amazon.alexa.handsfree.settings.dependencies.FalcoAlexaAppSettingsMediatorComponent;
import com.amazon.alexa.handsfree.settings.dependencies.FalcoSettingsComponent;
import com.amazon.alexa.handsfree.settings.handsfreesettings.AlexaSettingsActivity;
import com.amazon.alexa.handsfree.settings.handsfreesettings.AlexaSettingsActivity_MembersInjector;
import com.amazon.alexa.handsfree.settings.handsfreesettings.AlexaSettingsPreferenceFragment;
import com.amazon.alexa.handsfree.settings.handsfreesettings.AlexaSettingsPreferenceFragment_MembersInjector;
import com.amazon.alexa.handsfree.settings.handsfreesettings.lockscreen.RespondWhileLockedActivity;
import com.amazon.alexa.handsfree.settings.handsfreesettings.lockscreen.RespondWhileLockedActivity_MembersInjector;
import com.amazon.alexa.handsfree.settings.locale.VoiceAppLocalesProvider;
import com.amazon.alexa.handsfree.settings.voxsettings.VoxSettingsEnqueuer;
import com.amazon.alexa.handsfree.settings.voxsettings.VoxSettingsJobIntentService;
import com.amazon.alexa.handsfree.settings.voxsettings.VoxSettingsJobIntentService_MembersInjector;
import com.amazon.alexa.handsfree.settings.voxsettings.VoxSettingsSetRetryAttemptReceiver;
import com.amazon.alexa.handsfree.settings.voxsettings.VoxSettingsSetRetryAttemptReceiver_MembersInjector;
import com.amazon.alexa.handsfree.storage.dependencies.FalcoStorageComponent;
import com.amazon.alexa.handsfree.storage.initialization.AppInitializationStatusStore_Factory;
import com.amazon.alexa.handsfree.storage.initialization.DspApkVersionCodeStore_Factory;
import com.amazon.alexa.handsfree.storage.initialization.SdkVersionCodeStore_Factory;
import com.amazon.alexa.handsfree.storage.metrics.CacheMetricsService;
import com.amazon.alexa.handsfree.storage.metrics.CacheMetricsService_MembersInjector;
import com.amazon.alexa.handsfree.storage.metrics.EnableMetricsBroadcastReceiver;
import com.amazon.alexa.handsfree.storage.metrics.EnableMetricsBroadcastReceiver_MembersInjector;
import com.amazon.alexa.handsfree.storage.metrics.MetricsEnabledStatusStore;
import com.amazon.alexa.handsfree.storage.metrics.MetricsEnabledStatusStore_Factory;
import com.amazon.alexa.handsfree.storage.metrics.ProcessMetricsCacheService;
import com.amazon.alexa.handsfree.storage.metrics.ProcessMetricsCacheService_MembersInjector;
import com.amazon.alexa.handsfree.storage.metrics.ProcessMetricsCacheService_ServiceHelper_Factory;
import com.amazon.alexa.handsfree.storage.metrics.database.MetricsCacheDatabaseHelper;
import com.amazon.alexa.handsfree.storage.metrics.database.MetricsCacheDatabaseHelper_Factory;
import com.amazon.alexa.handsfree.vendor.bridge.VendorAPIWrapperProvider;
import com.amazon.alexa.handsfree.vendor.bridge.VendorAPIWrapperProvider_Factory;
import com.amazon.alexa.handsfree.vendor.bridge.VoiceAppPackageInfoProvider;
import com.amazon.alexa.handsfree.vendor.bridge.dependencies.FalcoVendorBridgeComponent;
import com.amazon.alexa.handsfree.voiceappreporter.VoiceAppEventContentProvider;
import com.amazon.alexa.handsfree.voiceappreporter.VoiceAppEventContentProvider_MembersInjector;
import com.amazon.alexa.handsfree.voiceappreporter.database.VoiceAppEventReporterDatabaseHelper;
import com.amazon.alexa.handsfree.voiceappreporter.database.VoiceAppEventReporterDatabaseHelper_Factory;
import com.amazon.alexa.handsfree.voiceappreporter.schedulers.VoiceAppEventReporterServiceScheduler;
import com.amazon.alexa.handsfree.voiceappreporter.schedulers.VoiceAppEventReporterServiceScheduler_Factory;
import com.amazon.alexa.handsfree.voiceappreporter.services.VoiceAppEventReporterService;
import com.amazon.alexa.handsfree.voiceappreporter.services.VoiceAppEventReporterService_MembersInjector;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.voice.handsfree.decider.setup.HandsFreeIntroActivity;
import com.amazon.alexa.voice.handsfree.decider.setup.HandsFreeIntroActivity_MembersInjector;
import com.amazon.alexa.voice.handsfree.features.AmokSignInNotifier;
import com.amazon.alexa.voice.handsfree.features.AmokSignInNotifier_Factory;
import com.amazon.alexa.voice.handsfree.features.IdentityServiceProvider;
import com.amazon.alexa.voice.handsfree.features.IdentityServiceProvider_Factory;
import com.amazon.alexa.voice.handsfree.features.IdentityServiceSubscriber;
import com.amazon.alexa.voice.handsfree.features.IdentityServiceSubscriber_Factory;
import com.amazon.alexa.voice.handsfree.initialization.TestModeInitializer;
import com.amazon.alexa.voice.handsfree.initialization.TestModeInitializer_Factory;
import com.amazon.alexa.voice.handsfree.initialization.VoiceAppProfileRemover_Factory;
import com.amazon.alexa.voice.handsfree.utils.WakeWordStateValidator;
import com.amazon.alexa.voice.handsfree.utils.WakeWordStateValidator_Factory;
import dagger.Lazy;
import dagger.internal.DelegateFactory;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class DaggerAhfComponent implements AhfComponent {
    private Provider<AmokSignInNotifier> amokSignInNotifierProvider;
    private Provider<AMPDInformationProvider> ampdInformationProvider;
    private Provider<DataSyncManager> dataSyncManagerProvider;
    private Provider<HandsFreeStateProvider> handsFreeStateProvider;
    private Provider<HandsFreeUserIdentityProvider> handsFreeUserIdentityProvider;
    private Provider<IdentityServiceManager> identityServiceManagerProvider;
    private Provider<IdentityServiceProvider> identityServiceProvider;
    private Provider<IdentityServiceSubscriber> identityServiceSubscriberProvider;
    private Provider<MetricsBuilderProvider> metricsBuilderProvider;
    private Provider<MetricsCacheDatabaseHelper> metricsCacheDatabaseHelperProvider;
    private Provider<MetricsConfiguration> metricsConfigurationProvider;
    private MetricsEnabledStatusStore_Factory metricsEnabledStatusStoreProvider;
    private Provider<com.amazon.alexa.handsfree.metrics.MetricsModule> metricsModuleProvider;
    private Provider<MetricsReporter> metricsReporterProvider;
    private Provider<MobilyticsMetadataProvider> mobilyticsMetadataProvider;
    private Provider<MobilyticsPmetFactory> mobilyticsPmetFactoryProvider;
    private Provider<AlexaAppSignInContract> provideAlexaAppSignInContractProvider;
    private Provider<Context> provideApplicationContextProvider;
    private Provider<ApplicationInformationProvider> provideApplicationInformationProvider;
    private Provider<AttributionTagProvider> provideAttributionTagProvider;
    private Provider<Intent> provideAudioProviderServiceIntentProvider;
    private Provider<CacheMetricsService.ServiceHelper> provideCacheMetricsServiceServiceHelperProvider;
    private Provider<ComponentRegistry> provideComponentRegistryProvider;
    private Provider<CrashReportRecorder> provideCrashReportRecorderProvider;
    private Provider<DataSyncIncomingMessageHandler> provideDataSyncIncomingMessageHandlerProvider;
    private Provider<DeviceTypeInformationProvider> provideDeviceTypeInformationProvider;
    private Provider<FeatureServiceV2> provideFeatureServiceV2Provider;
    private Provider<IdentityService> provideIdentityServiceProvider;
    private Provider<Initializer> provideInitializerProvider;
    private Provider<MetricFactoryProvider> provideMetricFactoryProvider;
    private Provider<MetricSerializer> provideMetricSerializerProvider;
    private Provider<MetricsEmitterConfig> provideMetricsEmitterConfigProvider;
    private Provider<MetricsEmitter> provideMetricsEmitterProvider;
    private Provider<MetricRecorder> provideMetricsRecorderProvider;
    private Provider<MobilyticsEventDecorator> provideMobilyticsEventDecoratorProvider;
    private Provider<SettingsContract> provideSettingsContractProvider;
    private Provider<SettingsSetupFlowContract> provideSettingsSetupFlowContractProvider;
    private Provider<EnrollmentTypeResolver> provideVoxEnrollmentTypeResolverProvider;
    private Provider<RemoteConfigDeserializer> remoteConfigDeserializerProvider;
    private Provider<RemoteConfigManager> remoteConfigManagerProvider;
    private Provider<RemoteConfigProvider> remoteConfigProvider;
    private Provider<VendorAPIWrapperProvider> vendorAPIWrapperProvider;
    private Provider<VoiceAppEventReporterDatabaseHelper> voiceAppEventReporterDatabaseHelperProvider;
    private Provider<VoiceAppEventReporterServiceScheduler> voiceAppEventReporterServiceSchedulerProvider;
    private Provider<VoiceAppMetricsInitializer> voiceAppMetricsInitializerProvider;
    private VoiceAppProfileRemover_Factory voiceAppProfileRemoverProvider;
    private Provider<WakeWordStateValidator> wakeWordStateValidatorProvider;

    /* loaded from: classes11.dex */
    private final class AlexaMobileMetricsComponentImpl implements AlexaMobileMetricsComponent {
        private VoiceAppEventContentProvider injectVoiceAppEventContentProvider(VoiceAppEventContentProvider voiceAppEventContentProvider) {
            VoiceAppEventContentProvider_MembersInjector.injectMVoiceAppEventReporterDatabaseHelper(voiceAppEventContentProvider, (VoiceAppEventReporterDatabaseHelper) DaggerAhfComponent.this.voiceAppEventReporterDatabaseHelperProvider.mo10268get());
            return voiceAppEventContentProvider;
        }

        private VoiceAppEventReporterService injectVoiceAppEventReporterService(VoiceAppEventReporterService voiceAppEventReporterService) {
            VoiceAppEventReporterService_MembersInjector.injectMInitializer(voiceAppEventReporterService, (Initializer) DaggerAhfComponent.this.provideInitializerProvider.mo10268get());
            VoiceAppEventReporterService_MembersInjector.injectMCrashReportRecorder(voiceAppEventReporterService, (CrashReportRecorder) DaggerAhfComponent.this.provideCrashReportRecorderProvider.mo10268get());
            VoiceAppEventReporterService_MembersInjector.injectMMetricsBuilderProvider(voiceAppEventReporterService, (MetricsBuilderProvider) DaggerAhfComponent.this.metricsBuilderProvider.mo10268get());
            VoiceAppEventReporterService_MembersInjector.injectMVoiceAppEventReporterDatabaseHelper(voiceAppEventReporterService, (VoiceAppEventReporterDatabaseHelper) DaggerAhfComponent.this.voiceAppEventReporterDatabaseHelperProvider.mo10268get());
            VoiceAppEventReporterService_MembersInjector.injectMVoiceAppEventReporterServiceScheduler(voiceAppEventReporterService, (VoiceAppEventReporterServiceScheduler) DaggerAhfComponent.this.voiceAppEventReporterServiceSchedulerProvider.mo10268get());
            VoiceAppEventReporterService_MembersInjector.injectMAMPDInformationProvider(voiceAppEventReporterService, (AMPDInformationProvider) DaggerAhfComponent.this.ampdInformationProvider.mo10268get());
            return voiceAppEventReporterService;
        }

        @Override // com.amazon.alexa.handsfree.metrics.dependencies.AlexaMobileMetricsComponent
        public void inject(VoiceAppEventReporterService voiceAppEventReporterService) {
            injectVoiceAppEventReporterService(voiceAppEventReporterService);
        }

        @Override // com.amazon.alexa.handsfree.metrics.dependencies.AlexaMobileMetricsComponent
        public VoiceAppEventReporterServiceScheduler voiceAppEventReporterServiceScheduler() {
            return (VoiceAppEventReporterServiceScheduler) DaggerAhfComponent.this.voiceAppEventReporterServiceSchedulerProvider.mo10268get();
        }

        private AlexaMobileMetricsComponentImpl() {
        }

        @Override // com.amazon.alexa.handsfree.metrics.dependencies.AlexaMobileMetricsComponent
        public void inject(VoiceAppEventContentProvider voiceAppEventContentProvider) {
            injectVoiceAppEventContentProvider(voiceAppEventContentProvider);
        }
    }

    /* loaded from: classes11.dex */
    public static final class Builder {
        private AhfMetricsModule ahfMetricsModule;
        private AhfModule ahfModule;
        private ConnectionModule connectionModule;
        private CrashReporterModule crashReporterModule;
        private DataSyncModule dataSyncModule;
        private EnrollmentTypeResolverModule enrollmentTypeResolverModule;
        private FalcoDevicesModule falcoDevicesModule;
        private FalcoSettingContractorModule falcoSettingContractorModule;
        private MetricsModule metricsModule;

        public Builder ahfMetricsModule(AhfMetricsModule ahfMetricsModule) {
            this.ahfMetricsModule = (AhfMetricsModule) Preconditions.checkNotNull(ahfMetricsModule);
            return this;
        }

        public Builder ahfModule(AhfModule ahfModule) {
            this.ahfModule = (AhfModule) Preconditions.checkNotNull(ahfModule);
            return this;
        }

        public AhfComponent build() {
            Preconditions.checkBuilderRequirement(this.ahfModule, AhfModule.class);
            if (this.ahfMetricsModule == null) {
                this.ahfMetricsModule = new AhfMetricsModule();
            }
            if (this.metricsModule == null) {
                this.metricsModule = new MetricsModule();
            }
            if (this.falcoDevicesModule == null) {
                this.falcoDevicesModule = new FalcoDevicesModule();
            }
            if (this.enrollmentTypeResolverModule == null) {
                this.enrollmentTypeResolverModule = new EnrollmentTypeResolverModule();
            }
            if (this.crashReporterModule == null) {
                this.crashReporterModule = new CrashReporterModule();
            }
            if (this.connectionModule == null) {
                this.connectionModule = new ConnectionModule();
            }
            if (this.dataSyncModule == null) {
                this.dataSyncModule = new DataSyncModule();
            }
            if (this.falcoSettingContractorModule == null) {
                this.falcoSettingContractorModule = new FalcoSettingContractorModule();
            }
            return new DaggerAhfComponent(this);
        }

        public Builder connectionModule(ConnectionModule connectionModule) {
            this.connectionModule = (ConnectionModule) Preconditions.checkNotNull(connectionModule);
            return this;
        }

        public Builder crashReporterModule(CrashReporterModule crashReporterModule) {
            this.crashReporterModule = (CrashReporterModule) Preconditions.checkNotNull(crashReporterModule);
            return this;
        }

        public Builder dataSyncModule(DataSyncModule dataSyncModule) {
            this.dataSyncModule = (DataSyncModule) Preconditions.checkNotNull(dataSyncModule);
            return this;
        }

        public Builder enrollmentTypeResolverModule(EnrollmentTypeResolverModule enrollmentTypeResolverModule) {
            this.enrollmentTypeResolverModule = (EnrollmentTypeResolverModule) Preconditions.checkNotNull(enrollmentTypeResolverModule);
            return this;
        }

        public Builder falcoDevicesModule(FalcoDevicesModule falcoDevicesModule) {
            this.falcoDevicesModule = (FalcoDevicesModule) Preconditions.checkNotNull(falcoDevicesModule);
            return this;
        }

        public Builder falcoSettingContractorModule(FalcoSettingContractorModule falcoSettingContractorModule) {
            this.falcoSettingContractorModule = (FalcoSettingContractorModule) Preconditions.checkNotNull(falcoSettingContractorModule);
            return this;
        }

        public Builder metricsModule(MetricsModule metricsModule) {
            this.metricsModule = (MetricsModule) Preconditions.checkNotNull(metricsModule);
            return this;
        }

        private Builder() {
        }
    }

    /* loaded from: classes11.dex */
    private final class FalcoAlexaAppSettingsMediatorComponentImpl implements FalcoAlexaAppSettingsMediatorComponent {
        private VoxSettingsJobIntentService injectVoxSettingsJobIntentService(VoxSettingsJobIntentService voxSettingsJobIntentService) {
            VoxSettingsJobIntentService_MembersInjector.injectMInitializer(voxSettingsJobIntentService, (Initializer) DaggerAhfComponent.this.provideInitializerProvider.mo10268get());
            VoxSettingsJobIntentService_MembersInjector.injectMHandsFreeUserIdentityProvider(voxSettingsJobIntentService, (HandsFreeUserIdentityProvider) DaggerAhfComponent.this.handsFreeUserIdentityProvider.mo10268get());
            return voxSettingsJobIntentService;
        }

        private VoxSettingsSetRetryAttemptReceiver injectVoxSettingsSetRetryAttemptReceiver(VoxSettingsSetRetryAttemptReceiver voxSettingsSetRetryAttemptReceiver) {
            VoxSettingsSetRetryAttemptReceiver_MembersInjector.injectMEnqueuer(voxSettingsSetRetryAttemptReceiver, new VoxSettingsEnqueuer());
            VoxSettingsSetRetryAttemptReceiver_MembersInjector.injectMInitializer(voxSettingsSetRetryAttemptReceiver, (Initializer) DaggerAhfComponent.this.provideInitializerProvider.mo10268get());
            return voxSettingsSetRetryAttemptReceiver;
        }

        @Override // com.amazon.alexa.handsfree.settings.dependencies.FalcoAlexaAppSettingsMediatorComponent
        public void inject(VoxSettingsSetRetryAttemptReceiver voxSettingsSetRetryAttemptReceiver) {
            injectVoxSettingsSetRetryAttemptReceiver(voxSettingsSetRetryAttemptReceiver);
        }

        private FalcoAlexaAppSettingsMediatorComponentImpl() {
        }

        @Override // com.amazon.alexa.handsfree.settings.dependencies.FalcoAlexaAppSettingsMediatorComponent
        public void inject(VoxSettingsJobIntentService voxSettingsJobIntentService) {
            injectVoxSettingsJobIntentService(voxSettingsJobIntentService);
        }
    }

    /* loaded from: classes11.dex */
    private final class FalcoProtocolComponentImpl implements FalcoProtocolComponent {
        private DataSyncService injectDataSyncService(DataSyncService dataSyncService) {
            DataSyncService_MembersInjector.injectMDataSyncIncomingMessageHandler(dataSyncService, (DataSyncIncomingMessageHandler) DaggerAhfComponent.this.provideDataSyncIncomingMessageHandlerProvider.mo10268get());
            return dataSyncService;
        }

        @Override // com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent
        public Lazy<AlexaAppSignInContract> alexaAppSignInContractLazy() {
            return DoubleCheck.lazy(DaggerAhfComponent.this.provideAlexaAppSignInContractProvider);
        }

        @Override // com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent
        public Context applicationContext() {
            return (Context) DaggerAhfComponent.this.provideApplicationContextProvider.mo10268get();
        }

        @Override // com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent
        public Lazy<ApplicationInformationProvider> applicationInformationProviderLazy() {
            return DoubleCheck.lazy(DaggerAhfComponent.this.provideApplicationInformationProvider);
        }

        @Override // com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent
        public Lazy<CrashReportRecorder> crashReportRecorderLazy() {
            return DoubleCheck.lazy(DaggerAhfComponent.this.provideCrashReportRecorderProvider);
        }

        @Override // com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent
        public DataSyncIncomingMessageHandler dataSyncIncomingMessageHandler() {
            return (DataSyncIncomingMessageHandler) DaggerAhfComponent.this.provideDataSyncIncomingMessageHandlerProvider.mo10268get();
        }

        @Override // com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent
        public DataSyncManager dataSyncManager() {
            return (DataSyncManager) DaggerAhfComponent.this.dataSyncManagerProvider.mo10268get();
        }

        @Override // com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent
        public Lazy<EnrollmentTypeResolver> enrollmentTypeResolverLazy() {
            return DoubleCheck.lazy(DaggerAhfComponent.this.provideVoxEnrollmentTypeResolverProvider);
        }

        @Override // com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent
        public HandsFreeUserIdentityProvider handsFreeUserIdentityProvider() {
            return (HandsFreeUserIdentityProvider) DaggerAhfComponent.this.handsFreeUserIdentityProvider.mo10268get();
        }

        @Override // com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent
        public void inject(DataSyncService dataSyncService) {
            injectDataSyncService(dataSyncService);
        }

        @Override // com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent
        public MetricsBuilderProvider metricsBuilderProvider() {
            return (MetricsBuilderProvider) DaggerAhfComponent.this.metricsBuilderProvider.mo10268get();
        }

        @Override // com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent
        public Lazy<MetricsBuilderProvider> metricsBuilderProviderLazy() {
            return DoubleCheck.lazy(DaggerAhfComponent.this.metricsBuilderProvider);
        }

        @Override // com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent
        public MetricsConfiguration metricsConfiguration() {
            return (MetricsConfiguration) DaggerAhfComponent.this.metricsConfigurationProvider.mo10268get();
        }

        private FalcoProtocolComponentImpl() {
        }
    }

    /* loaded from: classes11.dex */
    private final class FalcoSettingContractComponentImpl implements FalcoSettingContractComponent {
        @Override // com.amazon.alexa.handsfree.settings.contract.dependencies.FalcoSettingContractComponent
        public Intent audioProviderServiceIntent() {
            return (Intent) DaggerAhfComponent.this.provideAudioProviderServiceIntentProvider.mo10268get();
        }

        @Override // com.amazon.alexa.handsfree.settings.contract.dependencies.FalcoSettingContractComponent
        public SettingsContract settingsContract() {
            return (SettingsContract) DaggerAhfComponent.this.provideSettingsContractProvider.mo10268get();
        }

        @Override // com.amazon.alexa.handsfree.settings.contract.dependencies.FalcoSettingContractComponent
        public SettingsSetupFlowContract settingsSetupFlowContract() {
            return (SettingsSetupFlowContract) DaggerAhfComponent.this.provideSettingsSetupFlowContractProvider.mo10268get();
        }

        private FalcoSettingContractComponentImpl() {
        }
    }

    /* loaded from: classes11.dex */
    private final class FalcoSettingsComponentImpl implements FalcoSettingsComponent {
        private AlexaSettingsActivity injectAlexaSettingsActivity(AlexaSettingsActivity alexaSettingsActivity) {
            AlexaSettingsActivity_MembersInjector.injectMInitializer(alexaSettingsActivity, (Initializer) DaggerAhfComponent.this.provideInitializerProvider.mo10268get());
            return alexaSettingsActivity;
        }

        private AlexaSettingsPreferenceFragment injectAlexaSettingsPreferenceFragment(AlexaSettingsPreferenceFragment alexaSettingsPreferenceFragment) {
            AlexaSettingsPreferenceFragment_MembersInjector.injectMEnrollmentTypeResolverLazy(alexaSettingsPreferenceFragment, DoubleCheck.lazy(DaggerAhfComponent.this.provideVoxEnrollmentTypeResolverProvider));
            AlexaSettingsPreferenceFragment_MembersInjector.injectMHandsFreeUserIdentityProvider(alexaSettingsPreferenceFragment, (HandsFreeUserIdentityProvider) DaggerAhfComponent.this.handsFreeUserIdentityProvider.mo10268get());
            return alexaSettingsPreferenceFragment;
        }

        private RespondWhileLockedActivity injectRespondWhileLockedActivity(RespondWhileLockedActivity respondWhileLockedActivity) {
            RespondWhileLockedActivity_MembersInjector.injectMMetricsBuilderProvider(respondWhileLockedActivity, (MetricsBuilderProvider) DaggerAhfComponent.this.metricsBuilderProvider.mo10268get());
            return respondWhileLockedActivity;
        }

        @Override // com.amazon.alexa.handsfree.settings.dependencies.FalcoSettingsComponent
        public void inject(AlexaSettingsActivity alexaSettingsActivity) {
            injectAlexaSettingsActivity(alexaSettingsActivity);
        }

        @Override // com.amazon.alexa.handsfree.settings.dependencies.FalcoSettingsComponent
        public RemoteConfigManager remoteConfigManager() {
            return (RemoteConfigManager) DaggerAhfComponent.this.remoteConfigManagerProvider.mo10268get();
        }

        @Override // com.amazon.alexa.handsfree.settings.dependencies.FalcoSettingsComponent
        public VoiceAppLocalesProvider voiceAppLocalesProvider() {
            return new VoiceAppLocalesProvider((Context) DaggerAhfComponent.this.provideApplicationContextProvider.mo10268get(), (MetricsBuilderProvider) DaggerAhfComponent.this.metricsBuilderProvider.mo10268get());
        }

        private FalcoSettingsComponentImpl() {
        }

        @Override // com.amazon.alexa.handsfree.settings.dependencies.FalcoSettingsComponent
        public void inject(AlexaSettingsPreferenceFragment alexaSettingsPreferenceFragment) {
            injectAlexaSettingsPreferenceFragment(alexaSettingsPreferenceFragment);
        }

        @Override // com.amazon.alexa.handsfree.settings.dependencies.FalcoSettingsComponent
        public void inject(RespondWhileLockedActivity respondWhileLockedActivity) {
            injectRespondWhileLockedActivity(respondWhileLockedActivity);
        }
    }

    /* loaded from: classes11.dex */
    private final class FalcoStorageComponentImpl implements FalcoStorageComponent {
        private CacheMetricsService injectCacheMetricsService(CacheMetricsService cacheMetricsService) {
            CacheMetricsService_MembersInjector.injectMInitializer(cacheMetricsService, (Initializer) DaggerAhfComponent.this.provideInitializerProvider.mo10268get());
            CacheMetricsService_MembersInjector.injectMMetricsCacheDatabaseHelper(cacheMetricsService, (MetricsCacheDatabaseHelper) DaggerAhfComponent.this.metricsCacheDatabaseHelperProvider.mo10268get());
            return cacheMetricsService;
        }

        private EnableMetricsBroadcastReceiver injectEnableMetricsBroadcastReceiver(EnableMetricsBroadcastReceiver enableMetricsBroadcastReceiver) {
            EnableMetricsBroadcastReceiver_MembersInjector.injectMMetricsEnabledStatusStore(enableMetricsBroadcastReceiver, DaggerAhfComponent.this.getMetricsEnabledStatusStore());
            return enableMetricsBroadcastReceiver;
        }

        private ProcessMetricsCacheService injectProcessMetricsCacheService(ProcessMetricsCacheService processMetricsCacheService) {
            ProcessMetricsCacheService_MembersInjector.injectMInitializer(processMetricsCacheService, (Initializer) DaggerAhfComponent.this.provideInitializerProvider.mo10268get());
            ProcessMetricsCacheService_MembersInjector.injectMMetricsCacheDatabaseHelper(processMetricsCacheService, (MetricsCacheDatabaseHelper) DaggerAhfComponent.this.metricsCacheDatabaseHelperProvider.mo10268get());
            ProcessMetricsCacheService_MembersInjector.injectMMetricsEmitter(processMetricsCacheService, (MetricsEmitter) DaggerAhfComponent.this.provideMetricsEmitterProvider.mo10268get());
            ProcessMetricsCacheService_MembersInjector.injectMMetricsBuilderProvider(processMetricsCacheService, (MetricsBuilderProvider) DaggerAhfComponent.this.metricsBuilderProvider.mo10268get());
            ProcessMetricsCacheService_MembersInjector.injectMMetricSerializer(processMetricsCacheService, (MetricSerializer) DaggerAhfComponent.this.provideMetricSerializerProvider.mo10268get());
            return processMetricsCacheService;
        }

        @Override // com.amazon.alexa.handsfree.storage.dependencies.FalcoStorageComponent
        public void inject(EnableMetricsBroadcastReceiver enableMetricsBroadcastReceiver) {
            injectEnableMetricsBroadcastReceiver(enableMetricsBroadcastReceiver);
        }

        private FalcoStorageComponentImpl() {
        }

        @Override // com.amazon.alexa.handsfree.storage.dependencies.FalcoStorageComponent
        public void inject(ProcessMetricsCacheService processMetricsCacheService) {
            injectProcessMetricsCacheService(processMetricsCacheService);
        }

        @Override // com.amazon.alexa.handsfree.storage.dependencies.FalcoStorageComponent
        public void inject(CacheMetricsService cacheMetricsService) {
            injectCacheMetricsService(cacheMetricsService);
        }
    }

    /* loaded from: classes11.dex */
    private final class FalcoVendorBridgeComponentImpl implements FalcoVendorBridgeComponent {
        @Override // com.amazon.alexa.handsfree.vendor.bridge.dependencies.FalcoVendorBridgeComponent
        public VendorAPIWrapperProvider vendorAPIWrapperProvider() {
            return (VendorAPIWrapperProvider) DaggerAhfComponent.this.vendorAPIWrapperProvider.mo10268get();
        }

        private FalcoVendorBridgeComponentImpl() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MetricsEnabledStatusStore getMetricsEnabledStatusStore() {
        return new MetricsEnabledStatusStore(new ProcessMetricsCacheService.ServiceHelper());
    }

    private VoiceAppPackageInfoProvider getVoiceAppPackageInfoProvider() {
        return new VoiceAppPackageInfoProvider(this.provideApplicationContextProvider.mo10268get());
    }

    private void initialize(Builder builder) {
        this.provideApplicationContextProvider = DoubleCheck.provider(AhfModule_ProvideApplicationContextFactory.create(builder.ahfModule));
        this.provideComponentRegistryProvider = DoubleCheck.provider(AhfModule_ProvideComponentRegistryFactory.create(builder.ahfModule));
        this.metricsEnabledStatusStoreProvider = MetricsEnabledStatusStore_Factory.create(ProcessMetricsCacheService_ServiceHelper_Factory.create());
        this.metricsBuilderProvider = new DelegateFactory();
        this.provideMetricSerializerProvider = DoubleCheck.provider(AhfMetricsModule_ProvideMetricSerializerFactory.create(builder.ahfMetricsModule, this.provideApplicationContextProvider, this.metricsBuilderProvider));
        this.provideCacheMetricsServiceServiceHelperProvider = DoubleCheck.provider(AhfMetricsModule_ProvideCacheMetricsServiceServiceHelperFactory.create(builder.ahfMetricsModule));
        this.provideAttributionTagProvider = DoubleCheck.provider(AhfMetricsModule_ProvideAttributionTagProviderFactory.create(builder.ahfMetricsModule));
        this.provideApplicationInformationProvider = DoubleCheck.provider(MetricsModule_ProvideApplicationInformationProviderFactory.create(builder.metricsModule, this.provideApplicationContextProvider));
        this.provideDeviceTypeInformationProvider = DoubleCheck.provider(FalcoDevicesModule_ProvideDeviceTypeInformationProviderFactory.create(builder.falcoDevicesModule, this.provideApplicationContextProvider));
        this.vendorAPIWrapperProvider = DoubleCheck.provider(VendorAPIWrapperProvider_Factory.create(this.provideApplicationContextProvider));
        this.handsFreeUserIdentityProvider = DoubleCheck.provider(HandsFreeUserIdentityProvider_Factory.create());
        this.provideVoxEnrollmentTypeResolverProvider = DoubleCheck.provider(EnrollmentTypeResolverModule_ProvideVoxEnrollmentTypeResolverFactory.create(builder.enrollmentTypeResolverModule, this.provideApplicationContextProvider, this.vendorAPIWrapperProvider, this.handsFreeUserIdentityProvider));
        this.mobilyticsMetadataProvider = DoubleCheck.provider(MobilyticsMetadataProvider_Factory.create(this.provideApplicationContextProvider, this.provideApplicationInformationProvider, this.provideDeviceTypeInformationProvider, this.provideVoxEnrollmentTypeResolverProvider));
        this.provideMobilyticsEventDecoratorProvider = DoubleCheck.provider(AhfMetricsModule_ProvideMobilyticsEventDecoratorFactory.create(builder.ahfMetricsModule, this.provideApplicationContextProvider, this.provideAttributionTagProvider, this.mobilyticsMetadataProvider));
        this.mobilyticsPmetFactoryProvider = DoubleCheck.provider(MobilyticsPmetFactory_Factory.create());
        this.provideMetricsRecorderProvider = DoubleCheck.provider(AhfMetricsModule_ProvideMetricsRecorderFactory.create(builder.ahfMetricsModule, this.provideApplicationContextProvider, this.provideComponentRegistryProvider, this.metricsEnabledStatusStoreProvider, this.provideMetricSerializerProvider, this.provideCacheMetricsServiceServiceHelperProvider, this.provideMobilyticsEventDecoratorProvider, this.provideAttributionTagProvider, this.mobilyticsPmetFactoryProvider));
        this.provideMetricsEmitterConfigProvider = DoubleCheck.provider(MetricsModule_ProvideMetricsEmitterConfigFactory.create(builder.metricsModule));
        this.provideMetricsEmitterProvider = DoubleCheck.provider(AhfMetricsModule_ProvideMetricsEmitterFactory.create(builder.ahfMetricsModule, this.provideMetricsRecorderProvider, this.provideMetricsEmitterConfigProvider));
        this.provideMetricFactoryProvider = DoubleCheck.provider(AhfMetricsModule_ProvideMetricFactoryProviderFactory.create(builder.ahfMetricsModule));
        this.metricsConfigurationProvider = DoubleCheck.provider(MetricsConfiguration_Factory.create(this.provideMetricsEmitterProvider, this.provideMetricFactoryProvider, this.provideMetricSerializerProvider));
        DelegateFactory.setDelegate(this.metricsBuilderProvider, DoubleCheck.provider(MetricsBuilderProvider_Factory.create(this.metricsConfigurationProvider)));
        this.provideIdentityServiceProvider = DoubleCheck.provider(AhfModule_ProvideIdentityServiceFactory.create(builder.ahfModule, this.provideComponentRegistryProvider));
        this.identityServiceManagerProvider = DoubleCheck.provider(IdentityServiceManager_Factory.create(this.provideIdentityServiceProvider));
        this.provideFeatureServiceV2Provider = DoubleCheck.provider(AhfModule_ProvideFeatureServiceV2Factory.create(builder.ahfModule, this.provideComponentRegistryProvider));
        this.voiceAppMetricsInitializerProvider = DoubleCheck.provider(VoiceAppMetricsInitializer_Factory.create(this.provideFeatureServiceV2Provider));
        this.metricsModuleProvider = DoubleCheck.provider(MetricsModule_Factory.create(AppInitializationStatusStore_Factory.create(), this.metricsEnabledStatusStoreProvider, DspApkVersionCodeStore_Factory.create(), SdkVersionCodeStore_Factory.create(), this.metricsBuilderProvider, this.identityServiceManagerProvider, this.provideAttributionTagProvider, FirstStartupMetricJobService_Helper_Factory.create(), this.voiceAppMetricsInitializerProvider, this.provideDeviceTypeInformationProvider, this.provideApplicationInformationProvider));
        this.identityServiceProvider = DoubleCheck.provider(IdentityServiceProvider_Factory.create());
        this.amokSignInNotifierProvider = DoubleCheck.provider(AmokSignInNotifier_Factory.create());
        this.voiceAppProfileRemoverProvider = VoiceAppProfileRemover_Factory.create(this.provideApplicationContextProvider);
        this.identityServiceSubscriberProvider = DoubleCheck.provider(IdentityServiceSubscriber_Factory.create(this.identityServiceProvider, this.handsFreeUserIdentityProvider, this.amokSignInNotifierProvider, this.metricsBuilderProvider, this.voiceAppProfileRemoverProvider));
        this.wakeWordStateValidatorProvider = DoubleCheck.provider(WakeWordStateValidator_Factory.create(this.provideApplicationContextProvider));
        this.provideCrashReportRecorderProvider = DoubleCheck.provider(CrashReporterModule_ProvideCrashReportRecorderFactory.create(builder.crashReporterModule, this.provideApplicationContextProvider));
        this.provideAlexaAppSignInContractProvider = DoubleCheck.provider(ConnectionModule_ProvideAlexaAppSignInContractFactory.create(builder.connectionModule));
        this.dataSyncManagerProvider = DoubleCheck.provider(DataSyncManager_Factory.create(this.provideApplicationContextProvider, this.handsFreeUserIdentityProvider));
        this.provideDataSyncIncomingMessageHandlerProvider = DoubleCheck.provider(DataSyncModule_ProvideDataSyncIncomingMessageHandlerFactory.create(builder.dataSyncModule));
        this.provideInitializerProvider = DoubleCheck.provider(AhfModule_ProvideInitializerFactory.create(builder.ahfModule));
        this.metricsCacheDatabaseHelperProvider = DoubleCheck.provider(MetricsCacheDatabaseHelper_Factory.create(this.provideApplicationContextProvider));
        this.provideAudioProviderServiceIntentProvider = DoubleCheck.provider(FalcoSettingContractorModule_ProvideAudioProviderServiceIntentFactory.create(builder.falcoSettingContractorModule, this.provideApplicationContextProvider, this.vendorAPIWrapperProvider));
        this.provideSettingsContractProvider = DoubleCheck.provider(FalcoSettingContractorModule_ProvideSettingsContractFactory.create(builder.falcoSettingContractorModule, this.vendorAPIWrapperProvider));
        this.provideSettingsSetupFlowContractProvider = DoubleCheck.provider(FalcoSettingContractorModule_ProvideSettingsSetupFlowContractFactory.create(builder.falcoSettingContractorModule));
        this.remoteConfigProvider = DoubleCheck.provider(RemoteConfigProvider_Factory.create(this.provideApplicationContextProvider));
        this.metricsReporterProvider = DoubleCheck.provider(MetricsReporter_Factory.create(this.metricsBuilderProvider, this.provideApplicationContextProvider));
        this.remoteConfigDeserializerProvider = DoubleCheck.provider(RemoteConfigDeserializer_Factory.create(this.provideApplicationContextProvider, this.metricsReporterProvider));
        this.handsFreeStateProvider = DoubleCheck.provider(HandsFreeStateProvider_Factory.create(this.provideApplicationContextProvider));
        this.remoteConfigManagerProvider = DoubleCheck.provider(RemoteConfigManager_Factory.create(this.remoteConfigProvider, this.remoteConfigDeserializerProvider, this.handsFreeStateProvider, this.provideApplicationInformationProvider));
        this.voiceAppEventReporterServiceSchedulerProvider = DoubleCheck.provider(VoiceAppEventReporterServiceScheduler_Factory.create(this.provideApplicationContextProvider));
        this.voiceAppEventReporterDatabaseHelperProvider = DoubleCheck.provider(VoiceAppEventReporterDatabaseHelper_Factory.create(this.provideApplicationContextProvider));
        this.ampdInformationProvider = DoubleCheck.provider(FalcoDevicesModule_AmpdInformationProviderFactory.create(builder.falcoDevicesModule, this.provideApplicationContextProvider));
    }

    private HandsFreeIntroActivity injectHandsFreeIntroActivity(HandsFreeIntroActivity handsFreeIntroActivity) {
        HandsFreeIntroActivity_MembersInjector.injectMMetricsBuilderProvider(handsFreeIntroActivity, this.metricsBuilderProvider.mo10268get());
        HandsFreeIntroActivity_MembersInjector.injectMEnrollmentTypeResolverLazy(handsFreeIntroActivity, DoubleCheck.lazy(this.provideVoxEnrollmentTypeResolverProvider));
        return handsFreeIntroActivity;
    }

    @Override // com.amazon.alexa.voice.handsfree.dependencies.AhfComponent
    public AlexaMobileMetricsComponent alexaMobileMetricsComponent() {
        return new AlexaMobileMetricsComponentImpl();
    }

    @Override // com.amazon.alexa.voice.handsfree.dependencies.AhfComponent
    public Context applicationContext() {
        return this.provideApplicationContextProvider.mo10268get();
    }

    @Override // com.amazon.alexa.voice.handsfree.dependencies.AhfComponent
    public ComponentRegistry componentRegistry() {
        return this.provideComponentRegistryProvider.mo10268get();
    }

    @Override // com.amazon.alexa.voice.handsfree.dependencies.AhfComponent
    public FalcoAlexaAppSettingsMediatorComponent falcoAlexaAppSettingsMediatorComponent() {
        return new FalcoAlexaAppSettingsMediatorComponentImpl();
    }

    @Override // com.amazon.alexa.voice.handsfree.dependencies.AhfComponent
    public FalcoProtocolComponent falcoProtocolComponent() {
        return new FalcoProtocolComponentImpl();
    }

    @Override // com.amazon.alexa.voice.handsfree.dependencies.AhfComponent
    public FalcoSettingContractComponent falcoSettingContractComponent() {
        return new FalcoSettingContractComponentImpl();
    }

    @Override // com.amazon.alexa.voice.handsfree.dependencies.AhfComponent
    public FalcoSettingsComponent falcoSettingsComponent() {
        return new FalcoSettingsComponentImpl();
    }

    @Override // com.amazon.alexa.voice.handsfree.dependencies.AhfComponent
    public FalcoStorageComponent falcoStorageComponent() {
        return new FalcoStorageComponentImpl();
    }

    @Override // com.amazon.alexa.voice.handsfree.dependencies.AhfComponent
    public FalcoVendorBridgeComponent falcoVendorBridgeComponent() {
        return new FalcoVendorBridgeComponentImpl();
    }

    @Override // com.amazon.alexa.voice.handsfree.dependencies.AhfComponent
    public IdentityServiceSubscriber identityServiceSubscriber() {
        return this.identityServiceSubscriberProvider.mo10268get();
    }

    @Override // com.amazon.alexa.voice.handsfree.dependencies.AhfComponent
    public void inject(HandsFreeIntroActivity handsFreeIntroActivity) {
        injectHandsFreeIntroActivity(handsFreeIntroActivity);
    }

    @Override // com.amazon.alexa.voice.handsfree.dependencies.AhfComponent
    public com.amazon.alexa.handsfree.metrics.MetricsModule metricsModule() {
        return this.metricsModuleProvider.mo10268get();
    }

    @Override // com.amazon.alexa.voice.handsfree.dependencies.AhfComponent
    public TestModeInitializer testModeInitializer() {
        return TestModeInitializer_Factory.newTestModeInitializer(getVoiceAppPackageInfoProvider(), this.identityServiceSubscriberProvider.mo10268get());
    }

    @Override // com.amazon.alexa.voice.handsfree.dependencies.AhfComponent
    public WakeWordStateValidator wakeWordStateValidator() {
        return this.wakeWordStateValidatorProvider.mo10268get();
    }

    private DaggerAhfComponent(Builder builder) {
        initialize(builder);
    }
}
