package com.amazon.dee.app.ui.main;

import android.app.Activity;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.drivemode.api.DriveModeMainActivityCompanion;
import com.amazon.alexa.drivemode.api.DriveModeService;
import com.amazon.alexa.elements.api.BridgeStatusService;
import com.amazon.alexa.enrollment.route.EnrollmentRoutingAdapter;
import com.amazon.alexa.enrollment.route.KidsEnrollmentRoutingAdapter;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.AccountService;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mode.ModeService;
import com.amazon.alexa.presence.battery.BatteryOptimizationRoutingAdapter;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.features.FeatureQuery;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.alexa.routing.RouteFeatureGroupRegistry;
import com.amazon.alexa.routing.RouteWatcher;
import com.amazon.alexa.routing.api.RoutingAdapter;
import com.amazon.alexa.routing.api.RoutingRegistry;
import com.amazon.alexa.routing.api.RoutingRegistryAdapter;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.routing.api.RoutingViewService;
import com.amazon.alexa.tasks.api.TaskManager;
import com.amazon.alexa.viewmanagement.impl.ViewManagerRoutingAdapter;
import com.amazon.alexa.voice.handsfree.HandsFreeRoutingAdapter;
import com.amazon.alexa.voice.handsfree.HandsFreeSetup;
import com.amazon.alexa.voice.model.VoiceService;
import com.amazon.alexa.voice.routing.VoiceRoutingAdapter;
import com.amazon.dee.app.elements.ElementsRoutingAdapter;
import com.amazon.dee.app.elements.ReactBridgeService;
import com.amazon.dee.app.elements.ReactFeatureManager;
import com.amazon.dee.app.framework.EventBusMessagingReceiver;
import com.amazon.dee.app.services.appreviewrequest.AppReviewRequestService;
import com.amazon.dee.app.services.header.HeaderCacheService;
import com.amazon.dee.app.services.tabLayout.TabLayoutStatusService;
import com.amazon.dee.app.services.testing.TestArgumentsService;
import com.amazon.dee.app.services.toolbar.ToolbarService;
import com.amazon.dee.app.services.toolbar.ToolbarWatcher;
import com.amazon.dee.app.ui.nowplaying.NowPlayingViewModel;
import com.amazon.dee.app.ui.preload.PreloadAttributionUIManager;
import com.amazon.dee.app.ui.util.CacheClearOperations;
import com.amazon.dee.app.ui.util.LocationPermissionMetricHelper;
import com.amazon.dee.app.ui.util.SonarUrlHandler;
import com.amazon.dee.app.ui.web.WebRoutingAdapter;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.api.CommsServiceV2;
import com.amazon.deecomms.common.ui.main.ConversationRoutingAdapter;
import com.amazon.deecomms.conversation.ConversationService;
import com.amazon.deecomms.services.ConversationUIService;
import com.amazon.deecomms.ui.util.CommsRoutingHelper;
import com.amazon.latencyinfra.LatencyInfra;
import com.dee.app.metrics.MetricsService;
import com.dee.app.metrics.MetricsServiceV2;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class MainViewModel_MembersInjector implements MembersInjector<MainViewModel> {
    private final Provider<AccountService> accountServiceProvider;
    private final Provider<Activity> activityProvider;
    private final Provider<RoutingRegistryAdapter> adaptersProvider;
    private final Provider<AppReviewRequestService> appReviewRequestServiceProvider;
    private final Provider<BatteryOptimizationRoutingAdapter> batteryOptimizationRoutingAdapterProvider;
    private final Provider<BridgeStatusService> bridgeStatusServiceProvider;
    private final Provider<CacheClearOperations> cacheClearOperationsProvider;
    private final Provider<CommsManager> commsManagerProvider;
    private final Provider<CommsRoutingHelper> commsRoutingHelperProvider;
    private final Provider<CommsServiceV2> commsServiceV2Provider;
    private final Provider<ConversationRoutingAdapter> conversationRoutingAdapterProvider;
    private final Provider<ConversationService> conversationServiceProvider;
    private final Provider<ConversationUIService> conversationUIServiceProvider;
    private final Provider<CrashMetadata> crashMetadataProvider;
    private final Provider<RoutingAdapter> driveModeRoutingAdapterProvider;
    private final Provider<DriveModeService> driveModeServiceProvider;
    private final Provider<DriveModeMainActivityCompanion.ViewModel> driveModeViewModelProvider;
    private final Provider<ElementsRoutingAdapter> elementsRoutingAdapterProvider;
    private final Provider<EnrollmentRoutingAdapter> enrollmentRoutingAdapterProvider;
    private final Provider<EnvironmentService> environmentServiceProvider;
    private final Provider<EventBusMessagingReceiver> eventBusMessagingReceiverProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<AuthenticationExceptionHandler> exceptionHandlerProvider;
    private final Provider<FeatureQuery> featureQueryProvider;
    private final Provider<FeatureServiceV2> featureServiceV2LazyProvider;
    private final Provider<HandsFreeRoutingAdapter> handsFreeRoutingAdapterProvider;
    private final Provider<HandsFreeSetup> handsFreeSetupProvider;
    private final Provider<HeaderCacheService> headerCacheServiceProvider;
    private final Provider<RoutingAdapter> homeRoutingAdapterProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<IntentFactory> intentFactoryProvider;
    private final Provider<KidsEnrollmentRoutingAdapter> kidsEnrollmentRoutingAdapterProvider;
    private final Provider<LatencyInfra> latencyInfraProvider;
    private final Provider<LocationPermissionMetricHelper> locationPermissionMetricHelperProvider;
    private final Provider<MainActivity> mainActivityProvider;
    private final Provider<MainRoutingAdapter> mainRoutingAdapterProvider;
    private final Provider<MetricsService> metricsServiceProvider;
    private final Provider<MetricsServiceV2> metricsServiceV2Provider;
    private final Provider<Mobilytics> mobilyticsProvider;
    private final Provider<ModeService> modeServiceProvider;
    private final Provider<NowPlayingViewModel> nowPlayingViewModelProvider;
    private final Provider<PersistentStorage.Factory> persistentStorageFactoryProvider;
    private final Provider<PreloadAttributionUIManager> preloadAttributionUIManagerProvider;
    private final Provider<ReactBridgeService> reactBridgeServiceProvider;
    private final Provider<ReactFeatureManager> reactFeatureManagerProvider;
    private final Provider<RouteFeatureGroupRegistry> routeFeatureGroupRegistryProvider;
    private final Provider<RouteWatcher> routeWatcherProvider;
    private final Provider<RoutingRegistry> routesProvider;
    private final Provider<RoutingService> routingServiceProvider;
    private final Provider<SonarUrlHandler> sonarUrlHandlerProvider;
    private final Provider<TabLayoutStatusService> tabLayoutServiceProvider;
    private final Provider<TaskManager> taskManagerProvider;
    private final Provider<TestArgumentsService> testArgumentsProvider;
    private final Provider<ThemeRecorder> themeRecorderProvider;
    private final Provider<ToolbarService> toolbarServiceProvider;
    private final Provider<ToolbarWatcher> toolbarWatcherProvider;
    private final Provider<ViewManagerRoutingAdapter> viewManagerRoutingAdapterProvider;
    private final Provider<RoutingViewService> viewServiceProvider;
    private final Provider<VoiceRoutingAdapter> voiceRoutingAdapterProvider;
    private final Provider<VoiceService> voiceServiceProvider;
    private final Provider<WebRoutingAdapter> webRoutingAdapterProvider;

    public MainViewModel_MembersInjector(Provider<Activity> provider, Provider<MainActivity> provider2, Provider<TaskManager> provider3, Provider<IdentityService> provider4, Provider<FeatureQuery> provider5, Provider<AccountService> provider6, Provider<MetricsService> provider7, Provider<MetricsServiceV2> provider8, Provider<CrashMetadata> provider9, Provider<Mobilytics> provider10, Provider<RoutingService> provider11, Provider<RoutingViewService> provider12, Provider<RoutingRegistryAdapter> provider13, Provider<MainRoutingAdapter> provider14, Provider<ConversationRoutingAdapter> provider15, Provider<ElementsRoutingAdapter> provider16, Provider<VoiceRoutingAdapter> provider17, Provider<BatteryOptimizationRoutingAdapter> provider18, Provider<EnrollmentRoutingAdapter> provider19, Provider<KidsEnrollmentRoutingAdapter> provider20, Provider<RoutingAdapter> provider21, Provider<RoutingRegistry> provider22, Provider<RouteFeatureGroupRegistry> provider23, Provider<RouteWatcher> provider24, Provider<EnvironmentService> provider25, Provider<CommsServiceV2> provider26, Provider<ConversationService> provider27, Provider<ConversationUIService> provider28, Provider<HeaderCacheService> provider29, Provider<AuthenticationExceptionHandler> provider30, Provider<VoiceService> provider31, Provider<NowPlayingViewModel> provider32, Provider<CacheClearOperations> provider33, Provider<ReactFeatureManager> provider34, Provider<BridgeStatusService> provider35, Provider<PersistentStorage.Factory> provider36, Provider<EventBusMessagingReceiver> provider37, Provider<HandsFreeSetup> provider38, Provider<ToolbarService> provider39, Provider<CommsManager> provider40, Provider<AppReviewRequestService> provider41, Provider<EventBus> provider42, Provider<ToolbarWatcher> provider43, Provider<TabLayoutStatusService> provider44, Provider<PreloadAttributionUIManager> provider45, Provider<ReactBridgeService> provider46, Provider<LatencyInfra> provider47, Provider<IntentFactory> provider48, Provider<CommsRoutingHelper> provider49, Provider<HandsFreeRoutingAdapter> provider50, Provider<LocationPermissionMetricHelper> provider51, Provider<ViewManagerRoutingAdapter> provider52, Provider<WebRoutingAdapter> provider53, Provider<RoutingAdapter> provider54, Provider<DriveModeMainActivityCompanion.ViewModel> provider55, Provider<ModeService> provider56, Provider<DriveModeService> provider57, Provider<ThemeRecorder> provider58, Provider<SonarUrlHandler> provider59, Provider<FeatureServiceV2> provider60, Provider<TestArgumentsService> provider61) {
        this.activityProvider = provider;
        this.mainActivityProvider = provider2;
        this.taskManagerProvider = provider3;
        this.identityServiceProvider = provider4;
        this.featureQueryProvider = provider5;
        this.accountServiceProvider = provider6;
        this.metricsServiceProvider = provider7;
        this.metricsServiceV2Provider = provider8;
        this.crashMetadataProvider = provider9;
        this.mobilyticsProvider = provider10;
        this.routingServiceProvider = provider11;
        this.viewServiceProvider = provider12;
        this.adaptersProvider = provider13;
        this.mainRoutingAdapterProvider = provider14;
        this.conversationRoutingAdapterProvider = provider15;
        this.elementsRoutingAdapterProvider = provider16;
        this.voiceRoutingAdapterProvider = provider17;
        this.batteryOptimizationRoutingAdapterProvider = provider18;
        this.enrollmentRoutingAdapterProvider = provider19;
        this.kidsEnrollmentRoutingAdapterProvider = provider20;
        this.homeRoutingAdapterProvider = provider21;
        this.routesProvider = provider22;
        this.routeFeatureGroupRegistryProvider = provider23;
        this.routeWatcherProvider = provider24;
        this.environmentServiceProvider = provider25;
        this.commsServiceV2Provider = provider26;
        this.conversationServiceProvider = provider27;
        this.conversationUIServiceProvider = provider28;
        this.headerCacheServiceProvider = provider29;
        this.exceptionHandlerProvider = provider30;
        this.voiceServiceProvider = provider31;
        this.nowPlayingViewModelProvider = provider32;
        this.cacheClearOperationsProvider = provider33;
        this.reactFeatureManagerProvider = provider34;
        this.bridgeStatusServiceProvider = provider35;
        this.persistentStorageFactoryProvider = provider36;
        this.eventBusMessagingReceiverProvider = provider37;
        this.handsFreeSetupProvider = provider38;
        this.toolbarServiceProvider = provider39;
        this.commsManagerProvider = provider40;
        this.appReviewRequestServiceProvider = provider41;
        this.eventBusProvider = provider42;
        this.toolbarWatcherProvider = provider43;
        this.tabLayoutServiceProvider = provider44;
        this.preloadAttributionUIManagerProvider = provider45;
        this.reactBridgeServiceProvider = provider46;
        this.latencyInfraProvider = provider47;
        this.intentFactoryProvider = provider48;
        this.commsRoutingHelperProvider = provider49;
        this.handsFreeRoutingAdapterProvider = provider50;
        this.locationPermissionMetricHelperProvider = provider51;
        this.viewManagerRoutingAdapterProvider = provider52;
        this.webRoutingAdapterProvider = provider53;
        this.driveModeRoutingAdapterProvider = provider54;
        this.driveModeViewModelProvider = provider55;
        this.modeServiceProvider = provider56;
        this.driveModeServiceProvider = provider57;
        this.themeRecorderProvider = provider58;
        this.sonarUrlHandlerProvider = provider59;
        this.featureServiceV2LazyProvider = provider60;
        this.testArgumentsProvider = provider61;
    }

    public static MembersInjector<MainViewModel> create(Provider<Activity> provider, Provider<MainActivity> provider2, Provider<TaskManager> provider3, Provider<IdentityService> provider4, Provider<FeatureQuery> provider5, Provider<AccountService> provider6, Provider<MetricsService> provider7, Provider<MetricsServiceV2> provider8, Provider<CrashMetadata> provider9, Provider<Mobilytics> provider10, Provider<RoutingService> provider11, Provider<RoutingViewService> provider12, Provider<RoutingRegistryAdapter> provider13, Provider<MainRoutingAdapter> provider14, Provider<ConversationRoutingAdapter> provider15, Provider<ElementsRoutingAdapter> provider16, Provider<VoiceRoutingAdapter> provider17, Provider<BatteryOptimizationRoutingAdapter> provider18, Provider<EnrollmentRoutingAdapter> provider19, Provider<KidsEnrollmentRoutingAdapter> provider20, Provider<RoutingAdapter> provider21, Provider<RoutingRegistry> provider22, Provider<RouteFeatureGroupRegistry> provider23, Provider<RouteWatcher> provider24, Provider<EnvironmentService> provider25, Provider<CommsServiceV2> provider26, Provider<ConversationService> provider27, Provider<ConversationUIService> provider28, Provider<HeaderCacheService> provider29, Provider<AuthenticationExceptionHandler> provider30, Provider<VoiceService> provider31, Provider<NowPlayingViewModel> provider32, Provider<CacheClearOperations> provider33, Provider<ReactFeatureManager> provider34, Provider<BridgeStatusService> provider35, Provider<PersistentStorage.Factory> provider36, Provider<EventBusMessagingReceiver> provider37, Provider<HandsFreeSetup> provider38, Provider<ToolbarService> provider39, Provider<CommsManager> provider40, Provider<AppReviewRequestService> provider41, Provider<EventBus> provider42, Provider<ToolbarWatcher> provider43, Provider<TabLayoutStatusService> provider44, Provider<PreloadAttributionUIManager> provider45, Provider<ReactBridgeService> provider46, Provider<LatencyInfra> provider47, Provider<IntentFactory> provider48, Provider<CommsRoutingHelper> provider49, Provider<HandsFreeRoutingAdapter> provider50, Provider<LocationPermissionMetricHelper> provider51, Provider<ViewManagerRoutingAdapter> provider52, Provider<WebRoutingAdapter> provider53, Provider<RoutingAdapter> provider54, Provider<DriveModeMainActivityCompanion.ViewModel> provider55, Provider<ModeService> provider56, Provider<DriveModeService> provider57, Provider<ThemeRecorder> provider58, Provider<SonarUrlHandler> provider59, Provider<FeatureServiceV2> provider60, Provider<TestArgumentsService> provider61) {
        return new MainViewModel_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20, provider21, provider22, provider23, provider24, provider25, provider26, provider27, provider28, provider29, provider30, provider31, provider32, provider33, provider34, provider35, provider36, provider37, provider38, provider39, provider40, provider41, provider42, provider43, provider44, provider45, provider46, provider47, provider48, provider49, provider50, provider51, provider52, provider53, provider54, provider55, provider56, provider57, provider58, provider59, provider60, provider61);
    }

    public static void injectAccountService(MainViewModel mainViewModel, AccountService accountService) {
        mainViewModel.accountService = accountService;
    }

    public static void injectActivity(MainViewModel mainViewModel, Activity activity) {
        mainViewModel.activity = activity;
    }

    public static void injectAdapters(MainViewModel mainViewModel, RoutingRegistryAdapter routingRegistryAdapter) {
        mainViewModel.adapters = routingRegistryAdapter;
    }

    public static void injectAppReviewRequestService(MainViewModel mainViewModel, AppReviewRequestService appReviewRequestService) {
        mainViewModel.appReviewRequestService = appReviewRequestService;
    }

    public static void injectBatteryOptimizationRoutingAdapter(MainViewModel mainViewModel, BatteryOptimizationRoutingAdapter batteryOptimizationRoutingAdapter) {
        mainViewModel.batteryOptimizationRoutingAdapter = batteryOptimizationRoutingAdapter;
    }

    public static void injectBridgeStatusService(MainViewModel mainViewModel, BridgeStatusService bridgeStatusService) {
        mainViewModel.bridgeStatusService = bridgeStatusService;
    }

    public static void injectCacheClearOperations(MainViewModel mainViewModel, CacheClearOperations cacheClearOperations) {
        mainViewModel.cacheClearOperations = cacheClearOperations;
    }

    public static void injectCommsManager(MainViewModel mainViewModel, Lazy<CommsManager> lazy) {
        mainViewModel.commsManager = lazy;
    }

    public static void injectCommsRoutingHelper(MainViewModel mainViewModel, CommsRoutingHelper commsRoutingHelper) {
        mainViewModel.commsRoutingHelper = commsRoutingHelper;
    }

    public static void injectCommsServiceV2(MainViewModel mainViewModel, Lazy<CommsServiceV2> lazy) {
        mainViewModel.commsServiceV2 = lazy;
    }

    public static void injectConversationRoutingAdapter(MainViewModel mainViewModel, ConversationRoutingAdapter conversationRoutingAdapter) {
        mainViewModel.conversationRoutingAdapter = conversationRoutingAdapter;
    }

    public static void injectConversationService(MainViewModel mainViewModel, Lazy<ConversationService> lazy) {
        mainViewModel.conversationService = lazy;
    }

    public static void injectConversationUIService(MainViewModel mainViewModel, Lazy<ConversationUIService> lazy) {
        mainViewModel.conversationUIService = lazy;
    }

    public static void injectCrashMetadata(MainViewModel mainViewModel, CrashMetadata crashMetadata) {
        mainViewModel.crashMetadata = crashMetadata;
    }

    public static void injectDriveModeRoutingAdapter(MainViewModel mainViewModel, RoutingAdapter routingAdapter) {
        mainViewModel.driveModeRoutingAdapter = routingAdapter;
    }

    public static void injectDriveModeService(MainViewModel mainViewModel, Lazy<DriveModeService> lazy) {
        mainViewModel.driveModeService = lazy;
    }

    public static void injectDriveModeViewModel(MainViewModel mainViewModel, Lazy<DriveModeMainActivityCompanion.ViewModel> lazy) {
        mainViewModel.driveModeViewModel = lazy;
    }

    public static void injectElementsRoutingAdapter(MainViewModel mainViewModel, ElementsRoutingAdapter elementsRoutingAdapter) {
        mainViewModel.elementsRoutingAdapter = elementsRoutingAdapter;
    }

    public static void injectEnrollmentRoutingAdapter(MainViewModel mainViewModel, EnrollmentRoutingAdapter enrollmentRoutingAdapter) {
        mainViewModel.enrollmentRoutingAdapter = enrollmentRoutingAdapter;
    }

    public static void injectEnvironmentService(MainViewModel mainViewModel, EnvironmentService environmentService) {
        mainViewModel.environmentService = environmentService;
    }

    public static void injectEventBus(MainViewModel mainViewModel, EventBus eventBus) {
        mainViewModel.eventBus = eventBus;
    }

    public static void injectEventBusMessagingReceiver(MainViewModel mainViewModel, EventBusMessagingReceiver eventBusMessagingReceiver) {
        mainViewModel.eventBusMessagingReceiver = eventBusMessagingReceiver;
    }

    public static void injectExceptionHandler(MainViewModel mainViewModel, AuthenticationExceptionHandler authenticationExceptionHandler) {
        mainViewModel.exceptionHandler = authenticationExceptionHandler;
    }

    public static void injectFeatureQuery(MainViewModel mainViewModel, FeatureQuery featureQuery) {
        mainViewModel.featureQuery = featureQuery;
    }

    public static void injectFeatureServiceV2Lazy(MainViewModel mainViewModel, Lazy<FeatureServiceV2> lazy) {
        mainViewModel.featureServiceV2Lazy = lazy;
    }

    public static void injectHandsFreeRoutingAdapter(MainViewModel mainViewModel, HandsFreeRoutingAdapter handsFreeRoutingAdapter) {
        mainViewModel.handsFreeRoutingAdapter = handsFreeRoutingAdapter;
    }

    public static void injectHandsFreeSetup(MainViewModel mainViewModel, HandsFreeSetup handsFreeSetup) {
        mainViewModel.handsFreeSetup = handsFreeSetup;
    }

    public static void injectHeaderCacheService(MainViewModel mainViewModel, HeaderCacheService headerCacheService) {
        mainViewModel.headerCacheService = headerCacheService;
    }

    public static void injectHomeRoutingAdapter(MainViewModel mainViewModel, RoutingAdapter routingAdapter) {
        mainViewModel.homeRoutingAdapter = routingAdapter;
    }

    public static void injectIdentityService(MainViewModel mainViewModel, IdentityService identityService) {
        mainViewModel.identityService = identityService;
    }

    public static void injectIntentFactory(MainViewModel mainViewModel, IntentFactory intentFactory) {
        mainViewModel.intentFactory = intentFactory;
    }

    public static void injectKidsEnrollmentRoutingAdapter(MainViewModel mainViewModel, KidsEnrollmentRoutingAdapter kidsEnrollmentRoutingAdapter) {
        mainViewModel.kidsEnrollmentRoutingAdapter = kidsEnrollmentRoutingAdapter;
    }

    public static void injectLatencyInfra(MainViewModel mainViewModel, LatencyInfra latencyInfra) {
        mainViewModel.latencyInfra = latencyInfra;
    }

    public static void injectLocationPermissionMetricHelper(MainViewModel mainViewModel, LocationPermissionMetricHelper locationPermissionMetricHelper) {
        mainViewModel.locationPermissionMetricHelper = locationPermissionMetricHelper;
    }

    public static void injectMainActivity(MainViewModel mainViewModel, MainActivity mainActivity) {
        mainViewModel.mainActivity = mainActivity;
    }

    public static void injectMainRoutingAdapter(MainViewModel mainViewModel, MainRoutingAdapter mainRoutingAdapter) {
        mainViewModel.mainRoutingAdapter = mainRoutingAdapter;
    }

    public static void injectMetricsService(MainViewModel mainViewModel, MetricsService metricsService) {
        mainViewModel.metricsService = metricsService;
    }

    public static void injectMetricsServiceV2(MainViewModel mainViewModel, MetricsServiceV2 metricsServiceV2) {
        mainViewModel.metricsServiceV2 = metricsServiceV2;
    }

    public static void injectMobilytics(MainViewModel mainViewModel, Mobilytics mobilytics) {
        mainViewModel.mobilytics = mobilytics;
    }

    public static void injectModeService(MainViewModel mainViewModel, Lazy<ModeService> lazy) {
        mainViewModel.modeService = lazy;
    }

    public static void injectNowPlayingViewModel(MainViewModel mainViewModel, NowPlayingViewModel nowPlayingViewModel) {
        mainViewModel.nowPlayingViewModel = nowPlayingViewModel;
    }

    public static void injectPersistentStorageFactory(MainViewModel mainViewModel, PersistentStorage.Factory factory) {
        mainViewModel.persistentStorageFactory = factory;
    }

    public static void injectPreloadAttributionUIManager(MainViewModel mainViewModel, Lazy<PreloadAttributionUIManager> lazy) {
        mainViewModel.preloadAttributionUIManager = lazy;
    }

    public static void injectReactBridgeService(MainViewModel mainViewModel, ReactBridgeService reactBridgeService) {
        mainViewModel.reactBridgeService = reactBridgeService;
    }

    public static void injectReactFeatureManager(MainViewModel mainViewModel, ReactFeatureManager reactFeatureManager) {
        mainViewModel.reactFeatureManager = reactFeatureManager;
    }

    public static void injectRouteFeatureGroupRegistry(MainViewModel mainViewModel, RouteFeatureGroupRegistry routeFeatureGroupRegistry) {
        mainViewModel.routeFeatureGroupRegistry = routeFeatureGroupRegistry;
    }

    public static void injectRouteWatcher(MainViewModel mainViewModel, RouteWatcher routeWatcher) {
        mainViewModel.routeWatcher = routeWatcher;
    }

    public static void injectRoutes(MainViewModel mainViewModel, RoutingRegistry routingRegistry) {
        mainViewModel.routes = routingRegistry;
    }

    public static void injectRoutingService(MainViewModel mainViewModel, RoutingService routingService) {
        mainViewModel.routingService = routingService;
    }

    public static void injectSonarUrlHandler(MainViewModel mainViewModel, Lazy<SonarUrlHandler> lazy) {
        mainViewModel.sonarUrlHandler = lazy;
    }

    public static void injectTabLayoutService(MainViewModel mainViewModel, TabLayoutStatusService tabLayoutStatusService) {
        mainViewModel.tabLayoutService = tabLayoutStatusService;
    }

    public static void injectTaskManager(MainViewModel mainViewModel, TaskManager taskManager) {
        mainViewModel.taskManager = taskManager;
    }

    public static void injectTestArguments(MainViewModel mainViewModel, TestArgumentsService testArgumentsService) {
        mainViewModel.testArguments = testArgumentsService;
    }

    public static void injectThemeRecorder(MainViewModel mainViewModel, Lazy<ThemeRecorder> lazy) {
        mainViewModel.themeRecorder = lazy;
    }

    public static void injectToolbarService(MainViewModel mainViewModel, ToolbarService toolbarService) {
        mainViewModel.toolbarService = toolbarService;
    }

    public static void injectToolbarWatcher(MainViewModel mainViewModel, ToolbarWatcher toolbarWatcher) {
        mainViewModel.toolbarWatcher = toolbarWatcher;
    }

    public static void injectViewManagerRoutingAdapter(MainViewModel mainViewModel, ViewManagerRoutingAdapter viewManagerRoutingAdapter) {
        mainViewModel.viewManagerRoutingAdapter = viewManagerRoutingAdapter;
    }

    public static void injectViewService(MainViewModel mainViewModel, RoutingViewService routingViewService) {
        mainViewModel.viewService = routingViewService;
    }

    public static void injectVoiceRoutingAdapter(MainViewModel mainViewModel, VoiceRoutingAdapter voiceRoutingAdapter) {
        mainViewModel.voiceRoutingAdapter = voiceRoutingAdapter;
    }

    public static void injectVoiceService(MainViewModel mainViewModel, VoiceService voiceService) {
        mainViewModel.voiceService = voiceService;
    }

    public static void injectWebRoutingAdapter(MainViewModel mainViewModel, WebRoutingAdapter webRoutingAdapter) {
        mainViewModel.webRoutingAdapter = webRoutingAdapter;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(MainViewModel mainViewModel) {
        injectActivity(mainViewModel, this.activityProvider.mo10268get());
        injectMainActivity(mainViewModel, this.mainActivityProvider.mo10268get());
        injectTaskManager(mainViewModel, this.taskManagerProvider.mo10268get());
        injectIdentityService(mainViewModel, this.identityServiceProvider.mo10268get());
        injectFeatureQuery(mainViewModel, this.featureQueryProvider.mo10268get());
        injectAccountService(mainViewModel, this.accountServiceProvider.mo10268get());
        injectMetricsService(mainViewModel, this.metricsServiceProvider.mo10268get());
        injectMetricsServiceV2(mainViewModel, this.metricsServiceV2Provider.mo10268get());
        injectCrashMetadata(mainViewModel, this.crashMetadataProvider.mo10268get());
        injectMobilytics(mainViewModel, this.mobilyticsProvider.mo10268get());
        injectRoutingService(mainViewModel, this.routingServiceProvider.mo10268get());
        injectViewService(mainViewModel, this.viewServiceProvider.mo10268get());
        injectAdapters(mainViewModel, this.adaptersProvider.mo10268get());
        injectMainRoutingAdapter(mainViewModel, this.mainRoutingAdapterProvider.mo10268get());
        injectConversationRoutingAdapter(mainViewModel, this.conversationRoutingAdapterProvider.mo10268get());
        injectElementsRoutingAdapter(mainViewModel, this.elementsRoutingAdapterProvider.mo10268get());
        injectVoiceRoutingAdapter(mainViewModel, this.voiceRoutingAdapterProvider.mo10268get());
        injectBatteryOptimizationRoutingAdapter(mainViewModel, this.batteryOptimizationRoutingAdapterProvider.mo10268get());
        injectEnrollmentRoutingAdapter(mainViewModel, this.enrollmentRoutingAdapterProvider.mo10268get());
        injectKidsEnrollmentRoutingAdapter(mainViewModel, this.kidsEnrollmentRoutingAdapterProvider.mo10268get());
        injectHomeRoutingAdapter(mainViewModel, this.homeRoutingAdapterProvider.mo10268get());
        injectRoutes(mainViewModel, this.routesProvider.mo10268get());
        injectRouteFeatureGroupRegistry(mainViewModel, this.routeFeatureGroupRegistryProvider.mo10268get());
        injectRouteWatcher(mainViewModel, this.routeWatcherProvider.mo10268get());
        injectEnvironmentService(mainViewModel, this.environmentServiceProvider.mo10268get());
        injectCommsServiceV2(mainViewModel, DoubleCheck.lazy(this.commsServiceV2Provider));
        injectConversationService(mainViewModel, DoubleCheck.lazy(this.conversationServiceProvider));
        injectConversationUIService(mainViewModel, DoubleCheck.lazy(this.conversationUIServiceProvider));
        injectHeaderCacheService(mainViewModel, this.headerCacheServiceProvider.mo10268get());
        injectExceptionHandler(mainViewModel, this.exceptionHandlerProvider.mo10268get());
        injectVoiceService(mainViewModel, this.voiceServiceProvider.mo10268get());
        injectNowPlayingViewModel(mainViewModel, this.nowPlayingViewModelProvider.mo10268get());
        injectCacheClearOperations(mainViewModel, this.cacheClearOperationsProvider.mo10268get());
        injectReactFeatureManager(mainViewModel, this.reactFeatureManagerProvider.mo10268get());
        injectBridgeStatusService(mainViewModel, this.bridgeStatusServiceProvider.mo10268get());
        injectPersistentStorageFactory(mainViewModel, this.persistentStorageFactoryProvider.mo10268get());
        injectEventBusMessagingReceiver(mainViewModel, this.eventBusMessagingReceiverProvider.mo10268get());
        injectHandsFreeSetup(mainViewModel, this.handsFreeSetupProvider.mo10268get());
        injectToolbarService(mainViewModel, this.toolbarServiceProvider.mo10268get());
        injectCommsManager(mainViewModel, DoubleCheck.lazy(this.commsManagerProvider));
        injectAppReviewRequestService(mainViewModel, this.appReviewRequestServiceProvider.mo10268get());
        injectEventBus(mainViewModel, this.eventBusProvider.mo10268get());
        injectToolbarWatcher(mainViewModel, this.toolbarWatcherProvider.mo10268get());
        injectTabLayoutService(mainViewModel, this.tabLayoutServiceProvider.mo10268get());
        injectPreloadAttributionUIManager(mainViewModel, DoubleCheck.lazy(this.preloadAttributionUIManagerProvider));
        injectReactBridgeService(mainViewModel, this.reactBridgeServiceProvider.mo10268get());
        injectLatencyInfra(mainViewModel, this.latencyInfraProvider.mo10268get());
        injectIntentFactory(mainViewModel, this.intentFactoryProvider.mo10268get());
        injectCommsRoutingHelper(mainViewModel, this.commsRoutingHelperProvider.mo10268get());
        injectHandsFreeRoutingAdapter(mainViewModel, this.handsFreeRoutingAdapterProvider.mo10268get());
        injectLocationPermissionMetricHelper(mainViewModel, this.locationPermissionMetricHelperProvider.mo10268get());
        injectViewManagerRoutingAdapter(mainViewModel, this.viewManagerRoutingAdapterProvider.mo10268get());
        injectWebRoutingAdapter(mainViewModel, this.webRoutingAdapterProvider.mo10268get());
        injectDriveModeRoutingAdapter(mainViewModel, this.driveModeRoutingAdapterProvider.mo10268get());
        injectDriveModeViewModel(mainViewModel, DoubleCheck.lazy(this.driveModeViewModelProvider));
        injectModeService(mainViewModel, DoubleCheck.lazy(this.modeServiceProvider));
        injectDriveModeService(mainViewModel, DoubleCheck.lazy(this.driveModeServiceProvider));
        injectThemeRecorder(mainViewModel, DoubleCheck.lazy(this.themeRecorderProvider));
        injectSonarUrlHandler(mainViewModel, DoubleCheck.lazy(this.sonarUrlHandlerProvider));
        injectFeatureServiceV2Lazy(mainViewModel, DoubleCheck.lazy(this.featureServiceV2LazyProvider));
        injectTestArguments(mainViewModel, this.testArgumentsProvider.mo10268get());
    }
}
