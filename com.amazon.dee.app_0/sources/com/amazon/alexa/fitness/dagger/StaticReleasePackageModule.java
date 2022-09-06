package com.amazon.alexa.fitness.dagger;

import android.content.Context;
import android.content.res.Resources;
import android.webkit.CookieManager;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;
import com.amazon.alexa.accessoryclient.client.accessories.Accessories;
import com.amazon.alexa.api.AlexaContextsProvider;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.fitness.accessory.FitnessAccessoryCommandHandler;
import com.amazon.alexa.fitness.accessory.FitnessAccessoryCommandHandlerImpl;
import com.amazon.alexa.fitness.accessory.FitnessAccessorySensorProvider;
import com.amazon.alexa.fitness.accessory.FitnessAccessorySessionMonitor;
import com.amazon.alexa.fitness.accessory.FitnessAccessorySessionMonitorImpl;
import com.amazon.alexa.fitness.algorithm.FitnessAlgorithmsManager;
import com.amazon.alexa.fitness.algorithm.FitnessAlgorithmsManagerImpl;
import com.amazon.alexa.fitness.algorithm.StepsToDistanceAlgorithmAdapter;
import com.amazon.alexa.fitness.algorithm.aggregatedDistance.AggregatedDistanceAlgorithm;
import com.amazon.alexa.fitness.algorithm.calories.AutoMETCaloriesAlgorithmImpl;
import com.amazon.alexa.fitness.algorithm.calories.CaloriesAlgorithmAdapter;
import com.amazon.alexa.fitness.algorithm.calories.CaloriesAlgorithmV2;
import com.amazon.alexa.fitness.algorithms.StepToDistanceAlgorithm;
import com.amazon.alexa.fitness.api.AfitsClientImpl;
import com.amazon.alexa.fitness.api.EnvironmentUrlResolver;
import com.amazon.alexa.fitness.api.LocationService;
import com.amazon.alexa.fitness.api.UserPreferenceStore;
import com.amazon.alexa.fitness.api.afits.AfitsClient;
import com.amazon.alexa.fitness.api.afx.FeatureService;
import com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestrator;
import com.amazon.alexa.fitness.api.afx.HomeCardViewModel;
import com.amazon.alexa.fitness.api.afx.UserProfileRepository;
import com.amazon.alexa.fitness.configuration.AlexaFitnessCapabilityAgentServiceConfigurationProvider;
import com.amazon.alexa.fitness.configuration.CacheProviderConfigurationProvider;
import com.amazon.alexa.fitness.configuration.ConfigurationProvider;
import com.amazon.alexa.fitness.configuration.FitnessAccessorySessionMonitorConfigurationProvider;
import com.amazon.alexa.fitness.configuration.FitnessDataHandlerConfigurationProvider;
import com.amazon.alexa.fitness.configuration.FitnessSessionCommandReceiverConfigurationProvider;
import com.amazon.alexa.fitness.configuration.HdsClientConfigurationProvider;
import com.amazon.alexa.fitness.configuration.HdsConfigurationProvider;
import com.amazon.alexa.fitness.configuration.LocalResourcesConfigurationProvider;
import com.amazon.alexa.fitness.configuration.RecoveryConfigurationProvider;
import com.amazon.alexa.fitness.configuration.SpeechletEventEmitterConfigurationProvider;
import com.amazon.alexa.fitness.configuration.UserProfileServiceConfigurationProvider;
import com.amazon.alexa.fitness.context.AlexaFitnessContextManager;
import com.amazon.alexa.fitness.context.AlexaFitnessContextManagerImpl;
import com.amazon.alexa.fitness.context.AlexaFitnessContextsProvider;
import com.amazon.alexa.fitness.context.SessionSummaryProvider;
import com.amazon.alexa.fitness.context.SessionSummaryProviderImpl;
import com.amazon.alexa.fitness.identity.IdentityManager;
import com.amazon.alexa.fitness.identity.IdentityManagerImpl;
import com.amazon.alexa.fitness.location.LocationSensorProvider;
import com.amazon.alexa.fitness.location.LocationServiceConfiguration;
import com.amazon.alexa.fitness.location.LocationServiceImpl;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.message.FitnessSessionEventEmitter;
import com.amazon.alexa.fitness.message.SpeechletEventEmitter;
import com.amazon.alexa.fitness.message.SpeechletEventEmitterImpl;
import com.amazon.alexa.fitness.message.notification.FitnessNotificationPublisher;
import com.amazon.alexa.fitness.message.notification.FitnessNotificationPublisherImpl;
import com.amazon.alexa.fitness.metrics.DefaultMetricEventFactory;
import com.amazon.alexa.fitness.metrics.DefaultMetricEventRecorder;
import com.amazon.alexa.fitness.metrics.ElapsedRealTimeClock;
import com.amazon.alexa.fitness.metrics.MetricEventFactory;
import com.amazon.alexa.fitness.metrics.MetricEventRecorder;
import com.amazon.alexa.fitness.metrics.MetricService;
import com.amazon.alexa.fitness.metrics.MetricsAggregator;
import com.amazon.alexa.fitness.metrics.MetricsAggregatorImpl;
import com.amazon.alexa.fitness.metrics.MetricsServiceV2Wrapper;
import com.amazon.alexa.fitness.metrics.MonotonicallyIncreasingTimeSource;
import com.amazon.alexa.fitness.metrics.SessionMetrics;
import com.amazon.alexa.fitness.orchestrator.FitnessSessionOrchestratorImpl;
import com.amazon.alexa.fitness.repository.CacheProvider;
import com.amazon.alexa.fitness.repository.CacheProviderImpl;
import com.amazon.alexa.fitness.repository.FitnessSessionRepository;
import com.amazon.alexa.fitness.repository.FitnessSessionRepositoryImpl;
import com.amazon.alexa.fitness.repository.MetricsAggregatorCache;
import com.amazon.alexa.fitness.repository.MetricsAggregatorCacheImpl;
import com.amazon.alexa.fitness.repository.RecoveryRepository;
import com.amazon.alexa.fitness.repository.RecoveryRepositoryImpl;
import com.amazon.alexa.fitness.repository.SampleRepository;
import com.amazon.alexa.fitness.repository.SampleRepositoryImpl;
import com.amazon.alexa.fitness.repository.SessionSummaryCache;
import com.amazon.alexa.fitness.repository.SessionSummaryCacheImpl;
import com.amazon.alexa.fitness.repository.UserPreferenceStoreImpl;
import com.amazon.alexa.fitness.repository.UserProfileCacheImpl;
import com.amazon.alexa.fitness.sdk.AfxMessageProcessor;
import com.amazon.alexa.fitness.sdk.MetricsAggregatorRecovery;
import com.amazon.alexa.fitness.sdk.MetricsAggregatorRecoveryImpl;
import com.amazon.alexa.fitness.sdk.SessionManager;
import com.amazon.alexa.fitness.sdk.SessionManagerImpl;
import com.amazon.alexa.fitness.sdk.SessionRecoveryManager;
import com.amazon.alexa.fitness.sdk.SessionRecoveryManagerImpl;
import com.amazon.alexa.fitness.sdk.TimeoutHandler;
import com.amazon.alexa.fitness.sdk.database.FitnessDatabase;
import com.amazon.alexa.fitness.sdk.database.SampleDao;
import com.amazon.alexa.fitness.sdk.sample.SampleStore;
import com.amazon.alexa.fitness.sdk.sample.SampleStoreImpl;
import com.amazon.alexa.fitness.service.FeatureServiceImpl;
import com.amazon.alexa.fitness.service.FitnessSessionCommandReceiver;
import com.amazon.alexa.fitness.service.FitnessSessionCommandReceiverImpl;
import com.amazon.alexa.fitness.service.InstrumentedAlexaServicesConnection;
import com.amazon.alexa.fitness.service.InstrumentedAlexaServicesConnectionImpl;
import com.amazon.alexa.fitness.service.UserProfileService;
import com.amazon.alexa.fitness.service.UserProfileServiceImpl;
import com.amazon.alexa.fitness.service.hds.HdsClient;
import com.amazon.alexa.fitness.service.hds.HdsClientImpl;
import com.amazon.alexa.fitness.service.hds.HdsRetryHandler;
import com.amazon.alexa.fitness.service.hds.HdsRetryHandlerImpl;
import com.amazon.alexa.fitness.service.hds.HdsThreadHandler;
import com.amazon.alexa.fitness.service.hds.HdsThreadHandlerImpl;
import com.amazon.alexa.fitness.service.hds.HttpClient;
import com.amazon.alexa.fitness.service.hds.HttpClientImpl;
import com.amazon.alexa.fitness.session.FitnessDataHandler;
import com.amazon.alexa.fitness.session.FitnessDataHandlerImpl;
import com.amazon.alexa.fitness.session.FitnessSessionStateService;
import com.amazon.alexa.fitness.session.FitnessSessionStateServiceImpl;
import com.amazon.alexa.fitness.util.AfitsHeaderUtils;
import com.amazon.alexa.fitness.util.DefaultDisposableManagerFactory;
import com.amazon.alexa.fitness.util.DisposableManagerFactory;
import com.amazon.alexa.fitness.view.HomeCardViewModelImpl;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.marketplace.MarketplaceService;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.routing.api.RoutingRegistry;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.amazon.wellness.io.format.abs.AbsFitnessDataParserImpl;
import com.amazon.wellness.io.format.abs.FitnessDataParser;
import com.dee.app.http.HttpCoralService;
import com.dee.app.metrics.MetricsServiceV2;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.gson.Gson;
import dagger.Binds;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import java.util.Arrays;
import javax.inject.Provider;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
@Module(includes = {ModuleBinds.class})
/* loaded from: classes8.dex */
abstract class StaticReleasePackageModule {
    private static final String FITNESS_DATABASE_NAME = "fitness_database";
    private final Context applicationContext;

    @Module
    /* loaded from: classes8.dex */
    interface ModuleBinds {
        @Binds
        AlexaFitnessCapabilityAgentServiceConfigurationProvider bindAlexaCapabilityAgentServiceConfigurationProvider(@NonNull ConfigurationProvider configurationProvider);

        @Binds
        AlexaContextsProvider bindAlexaContextsProvider(@NonNull AlexaFitnessContextsProvider alexaFitnessContextsProvider);

        @Binds
        AlexaFitnessContextManager bindAlexaFitnessContextManager(@NonNull AlexaFitnessContextManagerImpl alexaFitnessContextManagerImpl);

        @Binds
        CacheProvider bindCacheProvider(@NonNull CacheProviderImpl cacheProviderImpl);

        @Binds
        CacheProviderConfigurationProvider bindCacheProviderConfigurationProvider(@NonNull LocalResourcesConfigurationProvider localResourcesConfigurationProvider);

        @Binds
        ConfigurationProvider bindConfigurationProvider(@NonNull LocalResourcesConfigurationProvider localResourcesConfigurationProvider);

        @Binds
        DisposableManagerFactory bindDisposableManagerFactory(@NonNull DefaultDisposableManagerFactory defaultDisposableManagerFactory);

        @Binds
        FeatureService bindFeatureService(@NonNull FeatureServiceImpl featureServiceImpl);

        @Binds
        FitnessAccessoryCommandHandler bindFitnessAccessoryCommandHandler(@NonNull FitnessAccessoryCommandHandlerImpl fitnessAccessoryCommandHandlerImpl);

        @Binds
        FitnessAccessorySessionMonitor bindFitnessAccessorySessionMonitor(@NonNull FitnessAccessorySessionMonitorImpl fitnessAccessorySessionMonitorImpl);

        @Binds
        FitnessAccessorySessionMonitorConfigurationProvider bindFitnessAccessorySessionMonitorConfigurationProvider(@NonNull ConfigurationProvider configurationProvider);

        @Binds
        FitnessAlgorithmsManager bindFitnessAlgorithmsManager(@NonNull FitnessAlgorithmsManagerImpl fitnessAlgorithmsManagerImpl);

        @Binds
        FitnessDataHandler bindFitnessDataHandler(@NonNull FitnessDataHandlerImpl fitnessDataHandlerImpl);

        @Binds
        FitnessDataHandlerConfigurationProvider bindFitnessDataHandlerConfigurationProvider(@NonNull ConfigurationProvider configurationProvider);

        @Binds
        FitnessNotificationPublisher bindFitnessNotificationPublisher(@NonNull FitnessNotificationPublisherImpl fitnessNotificationPublisherImpl);

        @Binds
        FitnessSessionCommandReceiver bindFitnessSessionCommandReceiver(@NonNull FitnessSessionCommandReceiverImpl fitnessSessionCommandReceiverImpl);

        @Binds
        FitnessSessionRepository bindFitnessSessionRepository(@NonNull FitnessSessionRepositoryImpl fitnessSessionRepositoryImpl);

        @Binds
        FitnessSessionStateService bindFitnessSessionStateManager(@NonNull FitnessSessionStateServiceImpl fitnessSessionStateServiceImpl);

        @Binds
        HdsClient bindHdsClient(@NonNull HdsClientImpl hdsClientImpl);

        @Binds
        HdsClientConfigurationProvider bindHdsClientConfigurationProvider(@NonNull HdsConfigurationProvider hdsConfigurationProvider);

        @Binds
        HdsRetryHandler bindHdsRetryHandler(@NonNull HdsRetryHandlerImpl hdsRetryHandlerImpl);

        @Binds
        HdsThreadHandler bindHdsThreadHandler(@NonNull HdsThreadHandlerImpl hdsThreadHandlerImpl);

        @Binds
        HttpClient bindHttpClient(@NonNull HttpClientImpl httpClientImpl);

        @Binds
        IdentityManager bindIdentityManager(@NonNull IdentityManagerImpl identityManagerImpl);

        @Binds
        InstrumentedAlexaServicesConnection bindInstrumentedAlexaServicesConnection(@NonNull InstrumentedAlexaServicesConnectionImpl instrumentedAlexaServicesConnectionImpl);

        @Binds
        LocationService bindLocationService(@NonNull LocationServiceImpl locationServiceImpl);

        @Binds
        MetricEventFactory bindMetricEventFactory(@NonNull DefaultMetricEventFactory defaultMetricEventFactory);

        @Binds
        MetricEventRecorder bindMetricEventRecorder(@NonNull DefaultMetricEventRecorder defaultMetricEventRecorder);

        @Binds
        MetricService bindMetricService(@NonNull MetricsServiceV2Wrapper metricsServiceV2Wrapper);

        @Binds
        MetricsAggregatorCache bindMetricsAggregatorCache(@NonNull MetricsAggregatorCacheImpl metricsAggregatorCacheImpl);

        @Binds
        MetricsAggregatorRecovery bindMetricsAggregatorRecovery(@NonNull MetricsAggregatorRecoveryImpl metricsAggregatorRecoveryImpl);

        @Binds
        MonotonicallyIncreasingTimeSource bindMonotonicallyIncreasingTimeSource(@NonNull ElapsedRealTimeClock elapsedRealTimeClock);

        @Binds
        RecoveryConfigurationProvider bindRecoveryConfigurationProvider(@NonNull ConfigurationProvider configurationProvider);

        @Binds
        RecoveryRepository bindRecoveryRepository(@NonNull RecoveryRepositoryImpl recoveryRepositoryImpl);

        @Binds
        SampleRepository bindSampleRepository(@NonNull SampleRepositoryImpl sampleRepositoryImpl);

        @Binds
        SampleStore bindSampleStore(@NonNull SampleStoreImpl sampleStoreImpl);

        @Binds
        SessionRecoveryManager bindSessionRecoveryManager(@NonNull SessionRecoveryManagerImpl sessionRecoveryManagerImpl);

        @Binds
        SessionSummaryCache bindSessionSummaryCache(@NonNull SessionSummaryCacheImpl sessionSummaryCacheImpl);

        @Binds
        SessionSummaryProvider bindSessionSummaryProvider(@NonNull SessionSummaryProviderImpl sessionSummaryProviderImpl);

        @Binds
        SpeechletEventEmitter bindSpeechletEventEmitter(@NonNull SpeechletEventEmitterImpl speechletEventEmitterImpl);

        @Binds
        SpeechletEventEmitterConfigurationProvider bindSpeechletEventEmitterConfigurationProvider(@NonNull ConfigurationProvider configurationProvider);

        @Binds
        UserPreferenceStore bindUserPreferenceStore(@NonNull UserPreferenceStoreImpl userPreferenceStoreImpl);

        @Binds
        UserProfileRepository bindUserProfileCache(@NonNull UserProfileCacheImpl userProfileCacheImpl);

        @Binds
        UserProfileService bindUserProfileService(@NonNull UserProfileServiceImpl userProfileServiceImpl);

        @Binds
        UserProfileServiceConfigurationProvider bindUserProfileServiceConfigurationProvider(@NonNull ConfigurationProvider configurationProvider);

        @Binds
        FitnessSessionCommandReceiverConfigurationProvider bindWorkoutGUIToggleConfigurationProvider(@NonNull ConfigurationProvider configurationProvider);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public StaticReleasePackageModule(@NonNull Context context) {
        this.applicationContext = context.getApplicationContext();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @Provides
    @Singleton
    public Accessories provideAccessories(@NonNull Context context) {
        return Accessories.Shared.INSTANCE.get(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @Provides
    @Singleton
    public AfitsClient provideAfitsClient(ComponentRegistry componentRegistry, MetricsServiceV2 metricsServiceV2, HttpCoralService httpCoralService, AfitsHeaderUtils afitsHeaderUtils) {
        return new AfitsClientImpl(componentRegistry, metricsServiceV2, httpCoralService, afitsHeaderUtils);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @Provides
    @Singleton
    public AfitsHeaderUtils provideAfitsHeaderUtil(EnvironmentService environmentService, CookieManager cookieManager, IdentityManager identityManager) {
        return new AfitsHeaderUtils(environmentService, cookieManager, identityManager);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @Provides
    @Singleton
    public AfxMessageProcessor provideAfxMessageProcessor() {
        return new AfxMessageProcessor();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @Provides
    @Singleton
    public AlexaServicesConnection provideAlexaServicesConnection(@NonNull Context context) {
        return new AlexaServicesConnection(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return this.applicationContext;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @Provides
    @Singleton
    public ComponentRegistry provideComponentRegistry() {
        return ComponentRegistry.getInstance();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @Provides
    @Singleton
    public CookieManager provideCookieManager() {
        return CookieManager.getInstance();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @Provides
    @Singleton
    public EnvironmentService provideEnvironmentService(ComponentRegistry componentRegistry) {
        return (EnvironmentService) componentRegistry.get(EnvironmentService.class).get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @Provides
    @Singleton
    public EventBus provideEventBus(ComponentRegistry componentRegistry) {
        return (EventBus) componentRegistry.get(EventBus.class).get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @Provides
    @Singleton
    public FeatureServiceV2 provideFeatureServiceV2(ComponentRegistry componentRegistry) {
        return (FeatureServiceV2) componentRegistry.get(FeatureServiceV2.class).get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @Provides
    @Singleton
    public FitnessDataParser provideFitnessDataParser() {
        return new AbsFitnessDataParserImpl();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @Provides
    @Singleton
    public FitnessDatabase provideFitnessDatabase(Context context) {
        return (FitnessDatabase) Room.databaseBuilder(context.getApplicationContext(), FitnessDatabase.class, FITNESS_DATABASE_NAME).build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @Provides
    @Singleton
    public FitnessSessionOrchestrator provideFitnessSessionOrchestrator(ComponentRegistry componentRegistry, AfxMessageProcessor afxMessageProcessor, HdsClient hdsClient, FeatureService featureService, FitnessNotificationPublisher fitnessNotificationPublisher, Lazy<SessionSummaryProvider> lazy, SessionManager sessionManager, FitnessAccessorySensorProvider fitnessAccessorySensorProvider, LocationSensorProvider locationSensorProvider, MetricEventRecorder metricEventRecorder, MetricEventFactory metricEventFactory, MetricsAggregator metricsAggregator, MetricsAggregatorRecovery metricsAggregatorRecovery, Provider<StepsToDistanceAlgorithmAdapter> provider, Provider<CaloriesAlgorithmAdapter> provider2, Provider<AggregatedDistanceAlgorithm> provider3, SessionMetrics sessionMetrics, SampleStore sampleStore, SessionSummaryCache sessionSummaryCache, EventBus eventBus, ILog iLog) {
        return new FitnessSessionOrchestratorImpl(componentRegistry, afxMessageProcessor, hdsClient, featureService, fitnessNotificationPublisher, lazy, sessionManager, Arrays.asList(fitnessAccessorySensorProvider, locationSensorProvider), metricEventRecorder, metricEventFactory, provider, provider2, provider3, metricsAggregator, sessionMetrics, metricsAggregatorRecovery, sampleStore, sessionSummaryCache, eventBus, iLog);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @Provides
    @Singleton
    public FusedLocationProviderClient provideFusedLocationProviderClient(Context context) {
        return LocationServices.getFusedLocationProviderClient(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @Provides
    @Singleton
    public HomeCardViewModel provideHomeCardViewModel() {
        return (HomeCardViewModel) new ViewModelProvider.NewInstanceFactory().create(HomeCardViewModelImpl.class);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @Provides
    @Singleton
    public HttpCoralService provideHttpCoralService() {
        return new HttpCoralService(new OkHttpClient(), new Gson(), new EnvironmentUrlResolver(provideEnvironmentService(provideComponentRegistry())), provideMetricsServiceV2(provideComponentRegistry()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @Provides
    @Singleton
    public IdentityService provideIdentityService(ComponentRegistry componentRegistry) {
        return (IdentityService) componentRegistry.get(IdentityService.class).get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @Provides
    @Singleton
    public LocationServiceConfiguration provideLocationServiceConfiguration() {
        return new LocationServiceConfiguration();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @Provides
    @Singleton
    public MarketplaceService provideMarketplaceService(ComponentRegistry componentRegistry) {
        return (MarketplaceService) componentRegistry.get(MarketplaceService.class).get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @Provides
    @Singleton
    public MetricsAggregator provideMetricsAggregator(Mobilytics mobilytics, @NonNull MetricsAggregatorCache metricsAggregatorCache) {
        return new MetricsAggregatorImpl(mobilytics, metricsAggregatorCache);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @Provides
    @Singleton
    public MetricsServiceV2 provideMetricsServiceV2(ComponentRegistry componentRegistry) {
        return (MetricsServiceV2) componentRegistry.get(MetricsServiceV2.class).get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @Provides
    @Singleton
    public Mobilytics provideMobilytics(ComponentRegistry componentRegistry) {
        return (Mobilytics) componentRegistry.get(Mobilytics.class).get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @Provides
    @Singleton
    public NetworkService provideNetworkService(ComponentRegistry componentRegistry) {
        return (NetworkService) componentRegistry.get(NetworkService.class).get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @Provides
    @Singleton
    public Resources provideResources(@NonNull Context context) {
        return context.getResources();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @Provides
    @Singleton
    public RoutingRegistry provideRoutingRegistry(ComponentRegistry componentRegistry) {
        return (RoutingRegistry) componentRegistry.get(RoutingRegistry.class).get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @Provides
    @Singleton
    public RoutingService provideRoutingService(ComponentRegistry componentRegistry) {
        return (RoutingService) componentRegistry.get(RoutingService.class).get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @Provides
    @Singleton
    public SampleDao provideSampleDao(FitnessDatabase fitnessDatabase) {
        return fitnessDatabase.sampleDao();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @Provides
    @Singleton
    public SessionManager provideSessionManager(AfxMessageProcessor afxMessageProcessor, MetricsAggregator metricsAggregator, MetricsAggregatorRecovery metricsAggregatorRecovery, FitnessAlgorithmsManager fitnessAlgorithmsManager, SessionRecoveryManager sessionRecoveryManager, ILog iLog, SampleStore sampleStore, TimeoutHandler timeoutHandler, UserPreferenceStore userPreferenceStore) {
        return new SessionManagerImpl(afxMessageProcessor, metricsAggregator, metricsAggregatorRecovery, fitnessAlgorithmsManager, sessionRecoveryManager, null, iLog, timeoutHandler, sampleStore, userPreferenceStore);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @Provides
    public StepToDistanceAlgorithm provideStepToDistanceAlgorithm() {
        return new StepToDistanceAlgorithm();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @Provides
    @Singleton
    public TimeoutHandler provideTimeoutHandler(FitnessDataHandlerConfigurationProvider fitnessDataHandlerConfigurationProvider, FitnessAccessorySessionMonitorConfigurationProvider fitnessAccessorySessionMonitorConfigurationProvider, MetricEventFactory metricEventFactory, MetricEventRecorder metricEventRecorder, ILog iLog) {
        return new TimeoutHandler(fitnessAccessorySessionMonitorConfigurationProvider, fitnessDataHandlerConfigurationProvider, metricEventFactory, metricEventRecorder, iLog);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @Provides
    @Singleton
    public TokenManagement provideTokenManagement(@NonNull Context context) {
        return new TokenManagement(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @Provides
    @Singleton
    public FitnessSessionEventEmitter providerFitnessSessionEventEmitter(@NonNull SpeechletEventEmitter speechletEventEmitter) {
        return new FitnessSessionEventEmitter(speechletEventEmitter);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @Provides
    public CaloriesAlgorithmV2 providesCaloriesAlgorithmV2(@NonNull ILog iLog) {
        return new AutoMETCaloriesAlgorithmImpl(iLog);
    }
}
