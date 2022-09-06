package com.amazon.alexa.fitness.dagger;

import android.content.Context;
import android.content.res.Resources;
import android.webkit.CookieManager;
import com.amazon.alexa.accessoryclient.client.accessories.Accessories;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.fitness.accessory.FitnessAccessoryCommandHandlerImpl_Factory;
import com.amazon.alexa.fitness.accessory.FitnessAccessorySensorProvider;
import com.amazon.alexa.fitness.accessory.FitnessAccessorySensorProvider_Factory;
import com.amazon.alexa.fitness.accessory.FitnessAccessorySessionMonitorImpl;
import com.amazon.alexa.fitness.accessory.FitnessAccessorySessionMonitorImpl_Factory;
import com.amazon.alexa.fitness.algorithm.FitnessAlgorithmsManagerImpl;
import com.amazon.alexa.fitness.algorithm.FitnessAlgorithmsManagerImpl_Factory;
import com.amazon.alexa.fitness.algorithm.StepsToDistanceAlgorithmAdapter_Factory;
import com.amazon.alexa.fitness.algorithm.aggregatedDistance.AggregatedDistanceAlgorithm_Factory;
import com.amazon.alexa.fitness.algorithm.calories.CaloriesAlgorithmAdapter_Factory;
import com.amazon.alexa.fitness.api.UserPreferenceStore;
import com.amazon.alexa.fitness.api.afits.AfitsClient;
import com.amazon.alexa.fitness.api.afx.FeatureService;
import com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestrator;
import com.amazon.alexa.fitness.api.afx.HomeCardViewModel;
import com.amazon.alexa.fitness.api.afx.UserProfileRepository;
import com.amazon.alexa.fitness.configuration.AlexaFitnessCapabilityAgentServiceConfigurationProvider;
import com.amazon.alexa.fitness.configuration.HdsConfigurationProvider_Factory;
import com.amazon.alexa.fitness.configuration.LocalResourcesConfigurationProvider;
import com.amazon.alexa.fitness.configuration.LocalResourcesConfigurationProvider_Factory;
import com.amazon.alexa.fitness.context.AlexaFitnessContextManager;
import com.amazon.alexa.fitness.context.AlexaFitnessContextManagerImpl;
import com.amazon.alexa.fitness.context.AlexaFitnessContextManagerImpl_Factory;
import com.amazon.alexa.fitness.context.AlexaFitnessContextsProvider;
import com.amazon.alexa.fitness.context.AlexaFitnessContextsProvider_Factory;
import com.amazon.alexa.fitness.context.SessionSummaryProviderImpl;
import com.amazon.alexa.fitness.context.SessionSummaryProviderImpl_Factory;
import com.amazon.alexa.fitness.identity.IdentityManager;
import com.amazon.alexa.fitness.identity.IdentityManagerImpl;
import com.amazon.alexa.fitness.identity.IdentityManagerImpl_Factory;
import com.amazon.alexa.fitness.location.LocationSensorProvider;
import com.amazon.alexa.fitness.location.LocationSensorProvider_Factory;
import com.amazon.alexa.fitness.location.LocationServiceConfiguration;
import com.amazon.alexa.fitness.location.LocationServiceImpl;
import com.amazon.alexa.fitness.location.LocationServiceImpl_Factory;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.message.FitnessHomeCardPublisher;
import com.amazon.alexa.fitness.message.FitnessHomeCardPublisher_Factory;
import com.amazon.alexa.fitness.message.FitnessSessionEventEmitter;
import com.amazon.alexa.fitness.message.HomeCardDataProvider;
import com.amazon.alexa.fitness.message.HomeCardDataProvider_Factory;
import com.amazon.alexa.fitness.message.SpeechletEventEmitter;
import com.amazon.alexa.fitness.message.SpeechletEventEmitterImpl;
import com.amazon.alexa.fitness.message.SpeechletEventEmitterImpl_Factory;
import com.amazon.alexa.fitness.message.notification.FitnessNotificationPublisherImpl_Factory;
import com.amazon.alexa.fitness.metrics.DefaultMetricEventFactory;
import com.amazon.alexa.fitness.metrics.DefaultMetricEventFactory_Factory;
import com.amazon.alexa.fitness.metrics.DefaultMetricEventRecorder;
import com.amazon.alexa.fitness.metrics.DefaultMetricEventRecorder_Factory;
import com.amazon.alexa.fitness.metrics.ElapsedRealTimeClock_Factory;
import com.amazon.alexa.fitness.metrics.MetricEventFactory;
import com.amazon.alexa.fitness.metrics.MetricEventRecorder;
import com.amazon.alexa.fitness.metrics.MetricsAggregator;
import com.amazon.alexa.fitness.metrics.MetricsServiceV2Wrapper;
import com.amazon.alexa.fitness.metrics.MetricsServiceV2Wrapper_Factory;
import com.amazon.alexa.fitness.metrics.SessionMetrics_Factory;
import com.amazon.alexa.fitness.repository.CacheProviderImpl;
import com.amazon.alexa.fitness.repository.CacheProviderImpl_Factory;
import com.amazon.alexa.fitness.repository.FitnessSessionRepositoryImpl;
import com.amazon.alexa.fitness.repository.FitnessSessionRepositoryImpl_Factory;
import com.amazon.alexa.fitness.repository.MetricsAggregatorCacheImpl;
import com.amazon.alexa.fitness.repository.MetricsAggregatorCacheImpl_Factory;
import com.amazon.alexa.fitness.repository.RecoveryRepositoryImpl;
import com.amazon.alexa.fitness.repository.RecoveryRepositoryImpl_Factory;
import com.amazon.alexa.fitness.repository.SessionSummaryCache;
import com.amazon.alexa.fitness.repository.SessionSummaryCacheImpl;
import com.amazon.alexa.fitness.repository.SessionSummaryCacheImpl_Factory;
import com.amazon.alexa.fitness.repository.UserPreferenceStoreImpl;
import com.amazon.alexa.fitness.repository.UserPreferenceStoreImpl_Factory;
import com.amazon.alexa.fitness.repository.UserProfileCacheImpl;
import com.amazon.alexa.fitness.repository.UserProfileCacheImpl_Factory;
import com.amazon.alexa.fitness.sdk.AfxMessageProcessor;
import com.amazon.alexa.fitness.sdk.FitnessSdk;
import com.amazon.alexa.fitness.sdk.MetricsAggregatorRecoveryImpl_Factory;
import com.amazon.alexa.fitness.sdk.SessionManager;
import com.amazon.alexa.fitness.sdk.SessionRecoveryManagerImpl;
import com.amazon.alexa.fitness.sdk.SessionRecoveryManagerImpl_Factory;
import com.amazon.alexa.fitness.sdk.TimeoutHandler;
import com.amazon.alexa.fitness.sdk.database.FitnessDatabase;
import com.amazon.alexa.fitness.sdk.sample.SampleStore;
import com.amazon.alexa.fitness.sdk.sample.SampleStoreImpl;
import com.amazon.alexa.fitness.sdk.sample.SampleStoreImpl_Factory;
import com.amazon.alexa.fitness.service.AlexaFitnessCapabilityAgentService;
import com.amazon.alexa.fitness.service.AlexaFitnessManagerImpl;
import com.amazon.alexa.fitness.service.FeatureServiceImpl;
import com.amazon.alexa.fitness.service.FeatureServiceImpl_Factory;
import com.amazon.alexa.fitness.service.InstrumentedAlexaServicesConnection;
import com.amazon.alexa.fitness.service.InstrumentedAlexaServicesConnectionImpl;
import com.amazon.alexa.fitness.service.InstrumentedAlexaServicesConnectionImpl_Factory;
import com.amazon.alexa.fitness.service.UserProfileService;
import com.amazon.alexa.fitness.service.UserProfileServiceImpl;
import com.amazon.alexa.fitness.service.UserProfileServiceImpl_Factory;
import com.amazon.alexa.fitness.service.afits.WorkoutSessionDeleteHandler;
import com.amazon.alexa.fitness.service.hds.HdsClientImpl;
import com.amazon.alexa.fitness.service.hds.HdsClientImpl_Factory;
import com.amazon.alexa.fitness.service.hds.HdsRetryHandler;
import com.amazon.alexa.fitness.service.hds.HdsRetryHandlerImpl;
import com.amazon.alexa.fitness.service.hds.HdsRetryHandlerImpl_Factory;
import com.amazon.alexa.fitness.service.hds.HdsThreadHandlerImpl;
import com.amazon.alexa.fitness.service.hds.HdsThreadHandlerImpl_Factory;
import com.amazon.alexa.fitness.service.hds.HttpClientImpl_Factory;
import com.amazon.alexa.fitness.session.FitnessDataHandlerImpl;
import com.amazon.alexa.fitness.session.FitnessDataHandlerImpl_Factory;
import com.amazon.alexa.fitness.session.FitnessSessionStateService;
import com.amazon.alexa.fitness.session.FitnessSessionStateServiceImpl;
import com.amazon.alexa.fitness.session.FitnessSessionStateServiceImpl_Factory;
import com.amazon.alexa.fitness.util.AfitsHeaderUtils;
import com.amazon.alexa.fitness.util.DefaultDisposableManagerFactory_Factory;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.marketplace.MarketplaceService;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.routing.api.RoutingRegistry;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.amazon.wellness.io.format.abs.FitnessDataParser;
import com.dee.app.http.HttpCoralService;
import com.dee.app.metrics.MetricsServiceV2;
import com.google.android.gms.location.FusedLocationProviderClient;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class DaggerRuntimeReleaseStaticReleasePackageComponent implements RuntimeReleaseStaticReleasePackageComponent {
    private AggregatedDistanceAlgorithm_Factory aggregatedDistanceAlgorithmProvider;
    private Provider<AlexaFitnessContextManagerImpl> alexaFitnessContextManagerImplProvider;
    private Provider<AlexaFitnessContextsProvider> alexaFitnessContextsProvider;
    private Provider<CacheProviderImpl> cacheProviderImplProvider;
    private CaloriesAlgorithmAdapter_Factory caloriesAlgorithmAdapterProvider;
    private Provider<DefaultMetricEventFactory> defaultMetricEventFactoryProvider;
    private Provider<DefaultMetricEventRecorder> defaultMetricEventRecorderProvider;
    private Provider<FeatureServiceImpl> featureServiceImplProvider;
    private FitnessAccessoryCommandHandlerImpl_Factory fitnessAccessoryCommandHandlerImplProvider;
    private Provider<FitnessAccessorySensorProvider> fitnessAccessorySensorProvider;
    private Provider<FitnessAccessorySessionMonitorImpl> fitnessAccessorySessionMonitorImplProvider;
    private Provider<FitnessAlgorithmsManagerImpl> fitnessAlgorithmsManagerImplProvider;
    private Provider<FitnessDataHandlerImpl> fitnessDataHandlerImplProvider;
    private Provider<FitnessHomeCardPublisher> fitnessHomeCardPublisherProvider;
    private FitnessNotificationPublisherImpl_Factory fitnessNotificationPublisherImplProvider;
    private Provider<FitnessSessionRepositoryImpl> fitnessSessionRepositoryImplProvider;
    private Provider<FitnessSessionStateServiceImpl> fitnessSessionStateServiceImplProvider;
    private Provider<HdsClientImpl> hdsClientImplProvider;
    private HdsConfigurationProvider_Factory hdsConfigurationProvider;
    private Provider<HdsRetryHandlerImpl> hdsRetryHandlerImplProvider;
    private Provider<HdsThreadHandlerImpl> hdsThreadHandlerImplProvider;
    private Provider<HomeCardDataProvider> homeCardDataProvider;
    private Provider<IdentityManagerImpl> identityManagerImplProvider;
    private Provider<InstrumentedAlexaServicesConnectionImpl> instrumentedAlexaServicesConnectionImplProvider;
    private LocalResourcesConfigurationProvider_Factory localResourcesConfigurationProvider;
    private Provider<LocationSensorProvider> locationSensorProvider;
    private Provider<LocationServiceImpl> locationServiceImplProvider;
    private Provider<MetricsAggregatorCacheImpl> metricsAggregatorCacheImplProvider;
    private MetricsAggregatorRecoveryImpl_Factory metricsAggregatorRecoveryImplProvider;
    private Provider<MetricsServiceV2Wrapper> metricsServiceV2WrapperProvider;
    private Provider<Accessories> provideAccessoriesProvider;
    private Provider<AfitsClient> provideAfitsClientProvider;
    private Provider<AfitsHeaderUtils> provideAfitsHeaderUtilProvider;
    private Provider<AfxMessageProcessor> provideAfxMessageProcessorProvider;
    private Provider<AlexaServicesConnection> provideAlexaServicesConnectionProvider;
    private Provider<Context> provideApplicationContextProvider;
    private Provider<ComponentRegistry> provideComponentRegistryProvider;
    private Provider<CookieManager> provideCookieManagerProvider;
    private Provider<EnvironmentService> provideEnvironmentServiceProvider;
    private Provider<EventBus> provideEventBusProvider;
    private Provider<FeatureServiceV2> provideFeatureServiceV2Provider;
    private Provider<FitnessDataParser> provideFitnessDataParserProvider;
    private Provider<FitnessDatabase> provideFitnessDatabaseProvider;
    private Provider<FitnessSessionOrchestrator> provideFitnessSessionOrchestratorProvider;
    private Provider<FusedLocationProviderClient> provideFusedLocationProviderClientProvider;
    private Provider<HomeCardViewModel> provideHomeCardViewModelProvider;
    private Provider<HttpCoralService> provideHttpCoralServiceProvider;
    private Provider<ILog> provideILogProvider;
    private Provider<IdentityService> provideIdentityServiceProvider;
    private Provider<LocationServiceConfiguration> provideLocationServiceConfigurationProvider;
    private Provider<MarketplaceService> provideMarketplaceServiceProvider;
    private Provider<MetricsAggregator> provideMetricsAggregatorProvider;
    private Provider<MetricsServiceV2> provideMetricsServiceV2Provider;
    private Provider<Mobilytics> provideMobilyticsProvider;
    private Provider<NetworkService> provideNetworkServiceProvider;
    private Provider<Resources> provideResourcesProvider;
    private Provider<RoutingRegistry> provideRoutingRegistryProvider;
    private Provider<RoutingService> provideRoutingServiceProvider;
    private Provider<SessionManager> provideSessionManagerProvider;
    private StaticReleasePackageModule_ProvideStepToDistanceAlgorithmFactory provideStepToDistanceAlgorithmProvider;
    private Provider<TimeoutHandler> provideTimeoutHandlerProvider;
    private Provider<TokenManagement> provideTokenManagementProvider;
    private Provider<FitnessSessionEventEmitter> providerFitnessSessionEventEmitterProvider;
    private StaticReleasePackageModule_ProvidesCaloriesAlgorithmV2Factory providesCaloriesAlgorithmV2Provider;
    private Provider<RecoveryRepositoryImpl> recoveryRepositoryImplProvider;
    private Provider<SampleStoreImpl> sampleStoreImplProvider;
    private SessionMetrics_Factory sessionMetricsProvider;
    private Provider<SessionRecoveryManagerImpl> sessionRecoveryManagerImplProvider;
    private Provider<SessionSummaryCacheImpl> sessionSummaryCacheImplProvider;
    private Provider<SessionSummaryProviderImpl> sessionSummaryProviderImplProvider;
    private Provider<SpeechletEventEmitterImpl> speechletEventEmitterImplProvider;
    private StepsToDistanceAlgorithmAdapter_Factory stepsToDistanceAlgorithmAdapterProvider;
    private Provider<UserPreferenceStoreImpl> userPreferenceStoreImplProvider;
    private Provider<UserProfileCacheImpl> userProfileCacheImplProvider;
    private Provider<UserProfileServiceImpl> userProfileServiceImplProvider;

    /* loaded from: classes8.dex */
    private final class AFCAS_SubComponentImpl implements AlexaFitnessCapabilityAgentService.SubComponent {
        @Override // com.amazon.alexa.fitness.service.AlexaFitnessCapabilityAgentService.SubComponent
        public AlexaFitnessContextManager alexaFitnessContextManager() {
            return (AlexaFitnessContextManager) DaggerRuntimeReleaseStaticReleasePackageComponent.this.alexaFitnessContextManagerImplProvider.mo10268get();
        }

        @Override // com.amazon.alexa.fitness.service.AlexaFitnessCapabilityAgentService.SubComponent
        public AlexaFitnessContextsProvider alexaFitnessContextsProvider() {
            return (AlexaFitnessContextsProvider) DaggerRuntimeReleaseStaticReleasePackageComponent.this.alexaFitnessContextsProvider.mo10268get();
        }

        @Override // com.amazon.alexa.fitness.service.AlexaFitnessCapabilityAgentService.SubComponent
        public AlexaFitnessCapabilityAgentServiceConfigurationProvider configurationProvider() {
            return DaggerRuntimeReleaseStaticReleasePackageComponent.this.getLocalResourcesConfigurationProvider();
        }

        @Override // com.amazon.alexa.fitness.service.AlexaFitnessCapabilityAgentService.SubComponent
        public FeatureService featureService() {
            return (FeatureService) DaggerRuntimeReleaseStaticReleasePackageComponent.this.featureServiceImplProvider.mo10268get();
        }

        @Override // com.amazon.alexa.fitness.service.AlexaFitnessCapabilityAgentService.SubComponent
        public FitnessSessionEventEmitter fitnessSessionEventEmitter() {
            return (FitnessSessionEventEmitter) DaggerRuntimeReleaseStaticReleasePackageComponent.this.providerFitnessSessionEventEmitterProvider.mo10268get();
        }

        @Override // com.amazon.alexa.fitness.service.AlexaFitnessCapabilityAgentService.SubComponent
        public FitnessSessionOrchestrator fitnessSessionOrchestrator() {
            return (FitnessSessionOrchestrator) DaggerRuntimeReleaseStaticReleasePackageComponent.this.provideFitnessSessionOrchestratorProvider.mo10268get();
        }

        @Override // com.amazon.alexa.fitness.service.AlexaFitnessCapabilityAgentService.SubComponent
        public FitnessSessionStateService fitnessSessionStateManager() {
            return (FitnessSessionStateService) DaggerRuntimeReleaseStaticReleasePackageComponent.this.fitnessSessionStateServiceImplProvider.mo10268get();
        }

        @Override // com.amazon.alexa.fitness.service.AlexaFitnessCapabilityAgentService.SubComponent
        public ILog iLog() {
            return (ILog) DaggerRuntimeReleaseStaticReleasePackageComponent.this.provideILogProvider.mo10268get();
        }

        @Override // com.amazon.alexa.fitness.service.AlexaFitnessCapabilityAgentService.SubComponent
        public IdentityManager identityManager() {
            return (IdentityManager) DaggerRuntimeReleaseStaticReleasePackageComponent.this.identityManagerImplProvider.mo10268get();
        }

        @Override // com.amazon.alexa.fitness.service.AlexaFitnessCapabilityAgentService.SubComponent
        public InstrumentedAlexaServicesConnection instrumentedAlexaServicesConnection() {
            return (InstrumentedAlexaServicesConnection) DaggerRuntimeReleaseStaticReleasePackageComponent.this.instrumentedAlexaServicesConnectionImplProvider.mo10268get();
        }

        @Override // com.amazon.alexa.fitness.service.AlexaFitnessCapabilityAgentService.SubComponent
        public MetricEventFactory metricEventFactory() {
            return (MetricEventFactory) DaggerRuntimeReleaseStaticReleasePackageComponent.this.defaultMetricEventFactoryProvider.mo10268get();
        }

        @Override // com.amazon.alexa.fitness.service.AlexaFitnessCapabilityAgentService.SubComponent
        public MetricEventRecorder metricEventRecorder() {
            return (MetricEventRecorder) DaggerRuntimeReleaseStaticReleasePackageComponent.this.defaultMetricEventRecorderProvider.mo10268get();
        }

        @Override // com.amazon.alexa.fitness.service.AlexaFitnessCapabilityAgentService.SubComponent
        public RoutingService routingService() {
            return (RoutingService) DaggerRuntimeReleaseStaticReleasePackageComponent.this.provideRoutingServiceProvider.mo10268get();
        }

        @Override // com.amazon.alexa.fitness.service.AlexaFitnessCapabilityAgentService.SubComponent
        public SpeechletEventEmitter speechletEventEmitter() {
            return (SpeechletEventEmitter) DaggerRuntimeReleaseStaticReleasePackageComponent.this.speechletEventEmitterImplProvider.mo10268get();
        }

        @Override // com.amazon.alexa.fitness.service.AlexaFitnessCapabilityAgentService.SubComponent
        public UserProfileRepository userProfileRepository() {
            return (UserProfileRepository) DaggerRuntimeReleaseStaticReleasePackageComponent.this.userProfileCacheImplProvider.mo10268get();
        }

        private AFCAS_SubComponentImpl() {
        }
    }

    /* loaded from: classes8.dex */
    private final class AFMI_SubComponentImpl implements AlexaFitnessManagerImpl.SubComponent {
        @Override // com.amazon.alexa.fitness.service.AlexaFitnessManagerImpl.SubComponent
        public AfitsClient afitsClient() {
            return (AfitsClient) DaggerRuntimeReleaseStaticReleasePackageComponent.this.provideAfitsClientProvider.mo10268get();
        }

        @Override // com.amazon.alexa.fitness.service.AlexaFitnessManagerImpl.SubComponent
        public AlexaFitnessContextManager alexaFitnessContextManager() {
            return (AlexaFitnessContextManager) DaggerRuntimeReleaseStaticReleasePackageComponent.this.alexaFitnessContextManagerImplProvider.mo10268get();
        }

        @Override // com.amazon.alexa.fitness.service.AlexaFitnessManagerImpl.SubComponent
        public FeatureService featureService() {
            return (FeatureService) DaggerRuntimeReleaseStaticReleasePackageComponent.this.featureServiceImplProvider.mo10268get();
        }

        @Override // com.amazon.alexa.fitness.service.AlexaFitnessManagerImpl.SubComponent
        public HdsRetryHandler hdsRetryHandler() {
            return (HdsRetryHandler) DaggerRuntimeReleaseStaticReleasePackageComponent.this.hdsRetryHandlerImplProvider.mo10268get();
        }

        @Override // com.amazon.alexa.fitness.service.AlexaFitnessManagerImpl.SubComponent
        public HomeCardDataProvider homeCardDataProvider() {
            return (HomeCardDataProvider) DaggerRuntimeReleaseStaticReleasePackageComponent.this.homeCardDataProvider.mo10268get();
        }

        @Override // com.amazon.alexa.fitness.service.AlexaFitnessManagerImpl.SubComponent
        public IdentityManager identityManager() {
            return (IdentityManager) DaggerRuntimeReleaseStaticReleasePackageComponent.this.identityManagerImplProvider.mo10268get();
        }

        @Override // com.amazon.alexa.fitness.service.AlexaFitnessManagerImpl.SubComponent
        public InstrumentedAlexaServicesConnection instrumentedAlexaServicesConnection() {
            return (InstrumentedAlexaServicesConnection) DaggerRuntimeReleaseStaticReleasePackageComponent.this.instrumentedAlexaServicesConnectionImplProvider.mo10268get();
        }

        @Override // com.amazon.alexa.fitness.service.AlexaFitnessManagerImpl.SubComponent
        public ILog log() {
            return (ILog) DaggerRuntimeReleaseStaticReleasePackageComponent.this.provideILogProvider.mo10268get();
        }

        @Override // com.amazon.alexa.fitness.service.AlexaFitnessManagerImpl.SubComponent
        public RoutingRegistry routingRegistry() {
            return (RoutingRegistry) DaggerRuntimeReleaseStaticReleasePackageComponent.this.provideRoutingRegistryProvider.mo10268get();
        }

        @Override // com.amazon.alexa.fitness.service.AlexaFitnessManagerImpl.SubComponent
        public SpeechletEventEmitter speechletEventEmitter() {
            return (SpeechletEventEmitter) DaggerRuntimeReleaseStaticReleasePackageComponent.this.speechletEventEmitterImplProvider.mo10268get();
        }

        @Override // com.amazon.alexa.fitness.service.AlexaFitnessManagerImpl.SubComponent
        public UserPreferenceStore userPreferenceStore() {
            return (UserPreferenceStore) DaggerRuntimeReleaseStaticReleasePackageComponent.this.userPreferenceStoreImplProvider.mo10268get();
        }

        @Override // com.amazon.alexa.fitness.service.AlexaFitnessManagerImpl.SubComponent
        public UserProfileService userProfileService() {
            return (UserProfileService) DaggerRuntimeReleaseStaticReleasePackageComponent.this.userProfileServiceImplProvider.mo10268get();
        }

        @Override // com.amazon.alexa.fitness.service.AlexaFitnessManagerImpl.SubComponent
        public WorkoutSessionDeleteHandler workoutSessionDeleteHandler() {
            return new WorkoutSessionDeleteHandler((SessionSummaryCache) DaggerRuntimeReleaseStaticReleasePackageComponent.this.sessionSummaryCacheImplProvider.mo10268get(), (SampleStore) DaggerRuntimeReleaseStaticReleasePackageComponent.this.sampleStoreImplProvider.mo10268get(), (ILog) DaggerRuntimeReleaseStaticReleasePackageComponent.this.provideILogProvider.mo10268get());
        }

        private AFMI_SubComponentImpl() {
        }
    }

    /* loaded from: classes8.dex */
    public static final class Builder {
        private RuntimeReleaseStaticReleasePackageModule runtimeReleaseStaticReleasePackageModule;

        public RuntimeReleaseStaticReleasePackageComponent build() {
            Preconditions.checkBuilderRequirement(this.runtimeReleaseStaticReleasePackageModule, RuntimeReleaseStaticReleasePackageModule.class);
            return new DaggerRuntimeReleaseStaticReleasePackageComponent(this);
        }

        public Builder runtimeReleaseStaticReleasePackageModule(RuntimeReleaseStaticReleasePackageModule runtimeReleaseStaticReleasePackageModule) {
            this.runtimeReleaseStaticReleasePackageModule = (RuntimeReleaseStaticReleasePackageModule) Preconditions.checkNotNull(runtimeReleaseStaticReleasePackageModule);
            return this;
        }

        private Builder() {
        }
    }

    /* loaded from: classes8.dex */
    private final class FS_SubComponentImpl implements FitnessSdk.SubComponent {
        @Override // com.amazon.alexa.fitness.sdk.FitnessSdk.SubComponent
        public SessionManager sessionManager() {
            return (SessionManager) DaggerRuntimeReleaseStaticReleasePackageComponent.this.provideSessionManagerProvider.mo10268get();
        }

        private FS_SubComponentImpl() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public LocalResourcesConfigurationProvider getLocalResourcesConfigurationProvider() {
        return new LocalResourcesConfigurationProvider(this.provideResourcesProvider.mo10268get(), this.provideILogProvider.mo10268get());
    }

    private void initialize(Builder builder) {
        this.provideApplicationContextProvider = DoubleCheck.provider(StaticReleasePackageModule_ProvideApplicationContextFactory.create(builder.runtimeReleaseStaticReleasePackageModule));
        this.provideAlexaServicesConnectionProvider = DoubleCheck.provider(StaticReleasePackageModule_ProvideAlexaServicesConnectionFactory.create(builder.runtimeReleaseStaticReleasePackageModule, this.provideApplicationContextProvider));
        this.provideComponentRegistryProvider = DoubleCheck.provider(StaticReleasePackageModule_ProvideComponentRegistryFactory.create(builder.runtimeReleaseStaticReleasePackageModule));
        this.provideMobilyticsProvider = DoubleCheck.provider(StaticReleasePackageModule_ProvideMobilyticsFactory.create(builder.runtimeReleaseStaticReleasePackageModule, this.provideComponentRegistryProvider));
        this.provideResourcesProvider = DoubleCheck.provider(StaticReleasePackageModule_ProvideResourcesFactory.create(builder.runtimeReleaseStaticReleasePackageModule, this.provideApplicationContextProvider));
        this.provideILogProvider = DoubleCheck.provider(RuntimeReleaseStaticReleasePackageModule_ProvideILogFactory.create(builder.runtimeReleaseStaticReleasePackageModule));
        this.localResourcesConfigurationProvider = LocalResourcesConfigurationProvider_Factory.create(this.provideResourcesProvider, this.provideILogProvider);
        this.cacheProviderImplProvider = DoubleCheck.provider(CacheProviderImpl_Factory.create(this.provideApplicationContextProvider, this.localResourcesConfigurationProvider));
        this.metricsAggregatorCacheImplProvider = DoubleCheck.provider(MetricsAggregatorCacheImpl_Factory.create(this.cacheProviderImplProvider, this.provideILogProvider));
        this.provideMetricsAggregatorProvider = DoubleCheck.provider(StaticReleasePackageModule_ProvideMetricsAggregatorFactory.create(builder.runtimeReleaseStaticReleasePackageModule, this.provideMobilyticsProvider, this.metricsAggregatorCacheImplProvider));
        this.provideMetricsServiceV2Provider = DoubleCheck.provider(StaticReleasePackageModule_ProvideMetricsServiceV2Factory.create(builder.runtimeReleaseStaticReleasePackageModule, this.provideComponentRegistryProvider));
        this.metricsServiceV2WrapperProvider = DoubleCheck.provider(MetricsServiceV2Wrapper_Factory.create(this.provideMetricsServiceV2Provider, this.provideILogProvider));
        this.defaultMetricEventRecorderProvider = DoubleCheck.provider(DefaultMetricEventRecorder_Factory.create(this.metricsServiceV2WrapperProvider));
        this.instrumentedAlexaServicesConnectionImplProvider = DoubleCheck.provider(InstrumentedAlexaServicesConnectionImpl_Factory.create(this.provideAlexaServicesConnectionProvider, this.provideMetricsAggregatorProvider, this.defaultMetricEventRecorderProvider, this.provideILogProvider));
        this.provideAfxMessageProcessorProvider = DoubleCheck.provider(StaticReleasePackageModule_ProvideAfxMessageProcessorFactory.create(builder.runtimeReleaseStaticReleasePackageModule));
        this.provideEnvironmentServiceProvider = DoubleCheck.provider(StaticReleasePackageModule_ProvideEnvironmentServiceFactory.create(builder.runtimeReleaseStaticReleasePackageModule, this.provideComponentRegistryProvider));
        this.provideMarketplaceServiceProvider = DoubleCheck.provider(StaticReleasePackageModule_ProvideMarketplaceServiceFactory.create(builder.runtimeReleaseStaticReleasePackageModule, this.provideComponentRegistryProvider));
        this.hdsConfigurationProvider = HdsConfigurationProvider_Factory.create(this.provideEnvironmentServiceProvider, this.provideMarketplaceServiceProvider, this.provideResourcesProvider, this.provideILogProvider);
        this.hdsThreadHandlerImplProvider = DoubleCheck.provider(HdsThreadHandlerImpl_Factory.create());
        this.provideEventBusProvider = DoubleCheck.provider(StaticReleasePackageModule_ProvideEventBusFactory.create(builder.runtimeReleaseStaticReleasePackageModule, this.provideComponentRegistryProvider));
        this.provideIdentityServiceProvider = DoubleCheck.provider(StaticReleasePackageModule_ProvideIdentityServiceFactory.create(builder.runtimeReleaseStaticReleasePackageModule, this.provideComponentRegistryProvider));
        this.defaultMetricEventFactoryProvider = DoubleCheck.provider(DefaultMetricEventFactory_Factory.create(ElapsedRealTimeClock_Factory.create(), this.provideILogProvider));
        this.provideTokenManagementProvider = DoubleCheck.provider(StaticReleasePackageModule_ProvideTokenManagementFactory.create(builder.runtimeReleaseStaticReleasePackageModule, this.provideApplicationContextProvider));
        this.identityManagerImplProvider = DoubleCheck.provider(IdentityManagerImpl_Factory.create(this.provideEventBusProvider, this.provideIdentityServiceProvider, this.defaultMetricEventFactoryProvider, this.defaultMetricEventRecorderProvider, this.provideTokenManagementProvider, this.provideILogProvider));
        this.sessionSummaryCacheImplProvider = DoubleCheck.provider(SessionSummaryCacheImpl_Factory.create(this.cacheProviderImplProvider, this.identityManagerImplProvider, this.provideILogProvider));
        this.hdsClientImplProvider = DoubleCheck.provider(HdsClientImpl_Factory.create(this.hdsConfigurationProvider, this.hdsThreadHandlerImplProvider, HttpClientImpl_Factory.create(), this.identityManagerImplProvider, this.provideMobilyticsProvider, this.sessionSummaryCacheImplProvider, this.provideILogProvider));
        this.provideFeatureServiceV2Provider = DoubleCheck.provider(StaticReleasePackageModule_ProvideFeatureServiceV2Factory.create(builder.runtimeReleaseStaticReleasePackageModule, this.provideComponentRegistryProvider));
        this.featureServiceImplProvider = DoubleCheck.provider(FeatureServiceImpl_Factory.create(this.provideComponentRegistryProvider, this.provideFeatureServiceV2Provider));
        this.fitnessNotificationPublisherImplProvider = FitnessNotificationPublisherImpl_Factory.create(this.provideApplicationContextProvider, this.provideILogProvider);
        this.provideFitnessDatabaseProvider = DoubleCheck.provider(StaticReleasePackageModule_ProvideFitnessDatabaseFactory.create(builder.runtimeReleaseStaticReleasePackageModule, this.provideApplicationContextProvider));
        this.sampleStoreImplProvider = DoubleCheck.provider(SampleStoreImpl_Factory.create(this.provideFitnessDatabaseProvider, this.provideMetricsAggregatorProvider, this.provideILogProvider));
        this.sessionSummaryProviderImplProvider = DoubleCheck.provider(SessionSummaryProviderImpl_Factory.create(this.sampleStoreImplProvider, this.provideILogProvider));
        this.metricsAggregatorRecoveryImplProvider = MetricsAggregatorRecoveryImpl_Factory.create(this.metricsAggregatorCacheImplProvider, this.provideMetricsAggregatorProvider);
        this.recoveryRepositoryImplProvider = DoubleCheck.provider(RecoveryRepositoryImpl_Factory.create(this.cacheProviderImplProvider, this.defaultMetricEventFactoryProvider, this.defaultMetricEventRecorderProvider, this.provideILogProvider));
        this.sessionRecoveryManagerImplProvider = DoubleCheck.provider(SessionRecoveryManagerImpl_Factory.create(this.recoveryRepositoryImplProvider, this.localResourcesConfigurationProvider, this.defaultMetricEventFactoryProvider, this.defaultMetricEventRecorderProvider, this.sampleStoreImplProvider, this.provideILogProvider));
        this.fitnessAlgorithmsManagerImplProvider = DoubleCheck.provider(FitnessAlgorithmsManagerImpl_Factory.create(this.sessionRecoveryManagerImplProvider, this.sampleStoreImplProvider, this.provideILogProvider));
        RuntimeReleaseStaticReleasePackageModule runtimeReleaseStaticReleasePackageModule = builder.runtimeReleaseStaticReleasePackageModule;
        LocalResourcesConfigurationProvider_Factory localResourcesConfigurationProvider_Factory = this.localResourcesConfigurationProvider;
        this.provideTimeoutHandlerProvider = DoubleCheck.provider(StaticReleasePackageModule_ProvideTimeoutHandlerFactory.create(runtimeReleaseStaticReleasePackageModule, localResourcesConfigurationProvider_Factory, localResourcesConfigurationProvider_Factory, this.defaultMetricEventFactoryProvider, this.defaultMetricEventRecorderProvider, this.provideILogProvider));
        this.userPreferenceStoreImplProvider = DoubleCheck.provider(UserPreferenceStoreImpl_Factory.create(this.provideComponentRegistryProvider, this.provideApplicationContextProvider, this.identityManagerImplProvider, this.provideILogProvider));
        this.provideSessionManagerProvider = DoubleCheck.provider(StaticReleasePackageModule_ProvideSessionManagerFactory.create(builder.runtimeReleaseStaticReleasePackageModule, this.provideAfxMessageProcessorProvider, this.provideMetricsAggregatorProvider, this.metricsAggregatorRecoveryImplProvider, this.fitnessAlgorithmsManagerImplProvider, this.sessionRecoveryManagerImplProvider, this.provideILogProvider, this.sampleStoreImplProvider, this.provideTimeoutHandlerProvider, this.userPreferenceStoreImplProvider));
        this.provideAccessoriesProvider = DoubleCheck.provider(StaticReleasePackageModule_ProvideAccessoriesFactory.create(builder.runtimeReleaseStaticReleasePackageModule, this.provideApplicationContextProvider));
        this.fitnessAccessorySessionMonitorImplProvider = DoubleCheck.provider(FitnessAccessorySessionMonitorImpl_Factory.create(DefaultDisposableManagerFactory_Factory.create(), this.defaultMetricEventFactoryProvider, this.defaultMetricEventRecorderProvider, this.provideAfxMessageProcessorProvider, this.provideMetricsAggregatorProvider, this.provideILogProvider, this.provideAccessoriesProvider));
        this.fitnessSessionRepositoryImplProvider = DoubleCheck.provider(FitnessSessionRepositoryImpl_Factory.create(this.cacheProviderImplProvider, this.provideILogProvider));
        this.fitnessAccessoryCommandHandlerImplProvider = FitnessAccessoryCommandHandlerImpl_Factory.create(this.fitnessSessionRepositoryImplProvider, this.defaultMetricEventRecorderProvider, this.defaultMetricEventFactoryProvider, this.provideAfxMessageProcessorProvider, this.provideILogProvider);
        this.provideFitnessDataParserProvider = DoubleCheck.provider(StaticReleasePackageModule_ProvideFitnessDataParserFactory.create(builder.runtimeReleaseStaticReleasePackageModule));
        this.fitnessDataHandlerImplProvider = DoubleCheck.provider(FitnessDataHandlerImpl_Factory.create(this.provideFitnessDataParserProvider, this.provideILogProvider));
        this.fitnessAccessorySensorProvider = DoubleCheck.provider(FitnessAccessorySensorProvider_Factory.create(this.fitnessAccessorySessionMonitorImplProvider, this.fitnessAccessoryCommandHandlerImplProvider, this.provideAfxMessageProcessorProvider, this.provideMobilyticsProvider, this.defaultMetricEventRecorderProvider, this.defaultMetricEventFactoryProvider, this.fitnessDataHandlerImplProvider, this.provideMetricsAggregatorProvider, this.provideILogProvider, this.provideAccessoriesProvider));
        this.provideFusedLocationProviderClientProvider = DoubleCheck.provider(StaticReleasePackageModule_ProvideFusedLocationProviderClientFactory.create(builder.runtimeReleaseStaticReleasePackageModule, this.provideApplicationContextProvider));
        this.provideLocationServiceConfigurationProvider = DoubleCheck.provider(StaticReleasePackageModule_ProvideLocationServiceConfigurationFactory.create(builder.runtimeReleaseStaticReleasePackageModule));
        this.locationServiceImplProvider = DoubleCheck.provider(LocationServiceImpl_Factory.create(this.provideComponentRegistryProvider, this.provideApplicationContextProvider, this.provideFusedLocationProviderClientProvider, this.provideLocationServiceConfigurationProvider, this.provideILogProvider));
        this.locationSensorProvider = DoubleCheck.provider(LocationSensorProvider_Factory.create(this.provideAfxMessageProcessorProvider, this.locationServiceImplProvider, this.provideMetricsAggregatorProvider, this.userPreferenceStoreImplProvider, this.provideILogProvider));
        this.provideStepToDistanceAlgorithmProvider = StaticReleasePackageModule_ProvideStepToDistanceAlgorithmFactory.create(builder.runtimeReleaseStaticReleasePackageModule);
        this.stepsToDistanceAlgorithmAdapterProvider = StepsToDistanceAlgorithmAdapter_Factory.create(this.provideStepToDistanceAlgorithmProvider, this.featureServiceImplProvider, this.provideILogProvider);
        this.providesCaloriesAlgorithmV2Provider = StaticReleasePackageModule_ProvidesCaloriesAlgorithmV2Factory.create(builder.runtimeReleaseStaticReleasePackageModule, this.provideILogProvider);
        this.caloriesAlgorithmAdapterProvider = CaloriesAlgorithmAdapter_Factory.create(this.providesCaloriesAlgorithmV2Provider, this.provideILogProvider);
        this.aggregatedDistanceAlgorithmProvider = AggregatedDistanceAlgorithm_Factory.create(this.provideAfxMessageProcessorProvider, this.featureServiceImplProvider, this.provideILogProvider);
        this.sessionMetricsProvider = SessionMetrics_Factory.create(this.provideMetricsAggregatorProvider, this.metricsAggregatorRecoveryImplProvider, this.provideILogProvider);
        this.provideFitnessSessionOrchestratorProvider = DoubleCheck.provider(StaticReleasePackageModule_ProvideFitnessSessionOrchestratorFactory.create(builder.runtimeReleaseStaticReleasePackageModule, this.provideComponentRegistryProvider, this.provideAfxMessageProcessorProvider, this.hdsClientImplProvider, this.featureServiceImplProvider, this.fitnessNotificationPublisherImplProvider, this.sessionSummaryProviderImplProvider, this.provideSessionManagerProvider, this.fitnessAccessorySensorProvider, this.locationSensorProvider, this.defaultMetricEventRecorderProvider, this.defaultMetricEventFactoryProvider, this.provideMetricsAggregatorProvider, this.metricsAggregatorRecoveryImplProvider, this.stepsToDistanceAlgorithmAdapterProvider, this.caloriesAlgorithmAdapterProvider, this.aggregatedDistanceAlgorithmProvider, this.sessionMetricsProvider, this.sampleStoreImplProvider, this.sessionSummaryCacheImplProvider, this.provideEventBusProvider, this.provideILogProvider));
        this.alexaFitnessContextsProvider = DoubleCheck.provider(AlexaFitnessContextsProvider_Factory.create(this.provideFitnessSessionOrchestratorProvider, this.provideILogProvider));
        this.fitnessSessionStateServiceImplProvider = DoubleCheck.provider(FitnessSessionStateServiceImpl_Factory.create(this.provideSessionManagerProvider));
        this.alexaFitnessContextManagerImplProvider = DoubleCheck.provider(AlexaFitnessContextManagerImpl_Factory.create(this.instrumentedAlexaServicesConnectionImplProvider, this.alexaFitnessContextsProvider, this.fitnessSessionStateServiceImplProvider, this.provideILogProvider, this.defaultMetricEventFactoryProvider));
        this.provideRoutingServiceProvider = DoubleCheck.provider(StaticReleasePackageModule_ProvideRoutingServiceFactory.create(builder.runtimeReleaseStaticReleasePackageModule, this.provideComponentRegistryProvider));
        this.speechletEventEmitterImplProvider = DoubleCheck.provider(SpeechletEventEmitterImpl_Factory.create(this.localResourcesConfigurationProvider, this.instrumentedAlexaServicesConnectionImplProvider, this.provideFitnessSessionOrchestratorProvider, this.fitnessSessionStateServiceImplProvider, this.defaultMetricEventFactoryProvider, this.defaultMetricEventRecorderProvider, this.provideILogProvider));
        this.userProfileCacheImplProvider = DoubleCheck.provider(UserProfileCacheImpl_Factory.create(this.provideComponentRegistryProvider, this.cacheProviderImplProvider, this.identityManagerImplProvider, this.provideILogProvider));
        this.providerFitnessSessionEventEmitterProvider = DoubleCheck.provider(StaticReleasePackageModule_ProviderFitnessSessionEventEmitterFactory.create(builder.runtimeReleaseStaticReleasePackageModule, this.speechletEventEmitterImplProvider));
        this.provideNetworkServiceProvider = DoubleCheck.provider(StaticReleasePackageModule_ProvideNetworkServiceFactory.create(builder.runtimeReleaseStaticReleasePackageModule, this.provideComponentRegistryProvider));
        this.hdsRetryHandlerImplProvider = DoubleCheck.provider(HdsRetryHandlerImpl_Factory.create(this.hdsClientImplProvider, this.hdsThreadHandlerImplProvider, this.provideMobilyticsProvider, this.defaultMetricEventFactoryProvider, this.defaultMetricEventRecorderProvider, this.provideNetworkServiceProvider, this.sessionSummaryCacheImplProvider, this.provideILogProvider));
        this.fitnessHomeCardPublisherProvider = DoubleCheck.provider(FitnessHomeCardPublisher_Factory.create(this.provideILogProvider));
        this.provideHomeCardViewModelProvider = DoubleCheck.provider(StaticReleasePackageModule_ProvideHomeCardViewModelFactory.create(builder.runtimeReleaseStaticReleasePackageModule));
        this.homeCardDataProvider = DoubleCheck.provider(HomeCardDataProvider_Factory.create(this.fitnessAccessorySensorProvider, this.provideApplicationContextProvider, this.fitnessHomeCardPublisherProvider, this.provideHomeCardViewModelProvider, this.provideSessionManagerProvider, this.provideFitnessSessionOrchestratorProvider, this.provideComponentRegistryProvider, this.provideILogProvider));
        this.provideRoutingRegistryProvider = DoubleCheck.provider(StaticReleasePackageModule_ProvideRoutingRegistryFactory.create(builder.runtimeReleaseStaticReleasePackageModule, this.provideComponentRegistryProvider));
        this.userProfileServiceImplProvider = DoubleCheck.provider(UserProfileServiceImpl_Factory.create(this.localResourcesConfigurationProvider, this.identityManagerImplProvider, this.userProfileCacheImplProvider, this.provideILogProvider));
        this.provideHttpCoralServiceProvider = DoubleCheck.provider(StaticReleasePackageModule_ProvideHttpCoralServiceFactory.create(builder.runtimeReleaseStaticReleasePackageModule));
        this.provideCookieManagerProvider = DoubleCheck.provider(StaticReleasePackageModule_ProvideCookieManagerFactory.create(builder.runtimeReleaseStaticReleasePackageModule));
        this.provideAfitsHeaderUtilProvider = DoubleCheck.provider(StaticReleasePackageModule_ProvideAfitsHeaderUtilFactory.create(builder.runtimeReleaseStaticReleasePackageModule, this.provideEnvironmentServiceProvider, this.provideCookieManagerProvider, this.identityManagerImplProvider));
        this.provideAfitsClientProvider = DoubleCheck.provider(StaticReleasePackageModule_ProvideAfitsClientFactory.create(builder.runtimeReleaseStaticReleasePackageModule, this.provideComponentRegistryProvider, this.provideMetricsServiceV2Provider, this.provideHttpCoralServiceProvider, this.provideAfitsHeaderUtilProvider));
    }

    @Override // com.amazon.alexa.fitness.dagger.StaticReleasePackageComponent
    public AlexaFitnessCapabilityAgentService.SubComponent alexaFitnessCapabilityAgentServiceSubComponent() {
        return new AFCAS_SubComponentImpl();
    }

    @Override // com.amazon.alexa.fitness.dagger.StaticReleasePackageComponent
    public AlexaFitnessManagerImpl.SubComponent alexaFitnessManagerSubComponent() {
        return new AFMI_SubComponentImpl();
    }

    @Override // com.amazon.alexa.fitness.dagger.StaticReleasePackageComponent
    public FitnessSdk.SubComponent fitnessSdkSubComponent() {
        return new FS_SubComponentImpl();
    }

    private DaggerRuntimeReleaseStaticReleasePackageComponent(Builder builder) {
        initialize(builder);
    }
}
