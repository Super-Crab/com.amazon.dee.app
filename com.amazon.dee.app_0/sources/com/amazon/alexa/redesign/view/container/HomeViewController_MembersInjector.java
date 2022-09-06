package com.amazon.alexa.redesign.view.container;

import com.amazon.alexa.elements.api.BridgeStatusService;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserverRegistrar;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.redesign.DismissedCardDataStore;
import com.amazon.alexa.redesign.LatencyReportingDelegate;
import com.amazon.alexa.redesign.actions.ActionFactory;
import com.amazon.alexa.redesign.client.HomeFeedServiceClient;
import com.amazon.alexa.redesign.header.HeaderNavigationDelegate;
import com.amazon.alexa.redesign.repository.HomeCardsRepository;
import com.amazon.alexa.redesign.repository.VoxIngressRepository;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.voice.model.VoiceService;
import com.amazon.latencyinfra.LatencyInfra;
import com.facebook.react.ReactInstanceManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class HomeViewController_MembersInjector implements MembersInjector<HomeViewController> {
    private final Provider<ActionFactory> actionFactoryProvider;
    private final Provider<BridgeStatusService> bridgeStatusServiceProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<FeatureServiceV2> featureServiceV2Provider;
    private final Provider<HeaderNavigationDelegate> headerNavigationDelegateProvider;
    private final Provider<HomeCardsRepository> homeCardsRepositoryProvider;
    private final Provider<HomeFeedServiceClient> homeFeedServiceClientProvider;
    private final Provider<LatencyInfra> latencyInfraProvider;
    private final Provider<LatencyReportingDelegate> latencyReportingDelegateProvider;
    private final Provider<MainActivityLifecycleObserverRegistrar> mainActivityLifecycleObserverRegistrarProvider;
    private final Provider<Mobilytics> mobilyticsProvider;
    private final Provider<NetworkService> networkServiceProvider;
    private final Provider<ReactInstanceManager> reactInstanceManagerProvider;
    private final Provider<RoutingService> routingServiceProvider;
    private final Provider<DismissedCardDataStore> sharedPreferencesRepositoryProvider;
    private final Provider<VoiceService> voiceServiceProvider;
    private final Provider<VoxIngressRepository> voxIngressRepositoryProvider;

    public HomeViewController_MembersInjector(Provider<HomeCardsRepository> provider, Provider<VoxIngressRepository> provider2, Provider<ActionFactory> provider3, Provider<HomeFeedServiceClient> provider4, Provider<EventBus> provider5, Provider<Mobilytics> provider6, Provider<DismissedCardDataStore> provider7, Provider<ReactInstanceManager> provider8, Provider<LatencyInfra> provider9, Provider<NetworkService> provider10, Provider<RoutingService> provider11, Provider<LatencyReportingDelegate> provider12, Provider<BridgeStatusService> provider13, Provider<MainActivityLifecycleObserverRegistrar> provider14, Provider<VoiceService> provider15, Provider<HeaderNavigationDelegate> provider16, Provider<FeatureServiceV2> provider17) {
        this.homeCardsRepositoryProvider = provider;
        this.voxIngressRepositoryProvider = provider2;
        this.actionFactoryProvider = provider3;
        this.homeFeedServiceClientProvider = provider4;
        this.eventBusProvider = provider5;
        this.mobilyticsProvider = provider6;
        this.sharedPreferencesRepositoryProvider = provider7;
        this.reactInstanceManagerProvider = provider8;
        this.latencyInfraProvider = provider9;
        this.networkServiceProvider = provider10;
        this.routingServiceProvider = provider11;
        this.latencyReportingDelegateProvider = provider12;
        this.bridgeStatusServiceProvider = provider13;
        this.mainActivityLifecycleObserverRegistrarProvider = provider14;
        this.voiceServiceProvider = provider15;
        this.headerNavigationDelegateProvider = provider16;
        this.featureServiceV2Provider = provider17;
    }

    public static MembersInjector<HomeViewController> create(Provider<HomeCardsRepository> provider, Provider<VoxIngressRepository> provider2, Provider<ActionFactory> provider3, Provider<HomeFeedServiceClient> provider4, Provider<EventBus> provider5, Provider<Mobilytics> provider6, Provider<DismissedCardDataStore> provider7, Provider<ReactInstanceManager> provider8, Provider<LatencyInfra> provider9, Provider<NetworkService> provider10, Provider<RoutingService> provider11, Provider<LatencyReportingDelegate> provider12, Provider<BridgeStatusService> provider13, Provider<MainActivityLifecycleObserverRegistrar> provider14, Provider<VoiceService> provider15, Provider<HeaderNavigationDelegate> provider16, Provider<FeatureServiceV2> provider17) {
        return new HomeViewController_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17);
    }

    public static void injectActionFactory(HomeViewController homeViewController, ActionFactory actionFactory) {
        homeViewController.actionFactory = actionFactory;
    }

    public static void injectBridgeStatusService(HomeViewController homeViewController, BridgeStatusService bridgeStatusService) {
        homeViewController.bridgeStatusService = bridgeStatusService;
    }

    public static void injectEventBus(HomeViewController homeViewController, EventBus eventBus) {
        homeViewController.eventBus = eventBus;
    }

    public static void injectFeatureServiceV2(HomeViewController homeViewController, FeatureServiceV2 featureServiceV2) {
        homeViewController.featureServiceV2 = featureServiceV2;
    }

    public static void injectHeaderNavigationDelegate(HomeViewController homeViewController, HeaderNavigationDelegate headerNavigationDelegate) {
        homeViewController.headerNavigationDelegate = headerNavigationDelegate;
    }

    public static void injectHomeCardsRepository(HomeViewController homeViewController, HomeCardsRepository homeCardsRepository) {
        homeViewController.homeCardsRepository = homeCardsRepository;
    }

    public static void injectHomeFeedServiceClient(HomeViewController homeViewController, HomeFeedServiceClient homeFeedServiceClient) {
        homeViewController.homeFeedServiceClient = homeFeedServiceClient;
    }

    public static void injectLatencyInfra(HomeViewController homeViewController, LatencyInfra latencyInfra) {
        homeViewController.latencyInfra = latencyInfra;
    }

    public static void injectLatencyReportingDelegate(HomeViewController homeViewController, LatencyReportingDelegate latencyReportingDelegate) {
        homeViewController.latencyReportingDelegate = latencyReportingDelegate;
    }

    public static void injectMainActivityLifecycleObserverRegistrar(HomeViewController homeViewController, MainActivityLifecycleObserverRegistrar mainActivityLifecycleObserverRegistrar) {
        homeViewController.mainActivityLifecycleObserverRegistrar = mainActivityLifecycleObserverRegistrar;
    }

    public static void injectMobilytics(HomeViewController homeViewController, Mobilytics mobilytics) {
        homeViewController.mobilytics = mobilytics;
    }

    public static void injectNetworkService(HomeViewController homeViewController, NetworkService networkService) {
        homeViewController.networkService = networkService;
    }

    public static void injectReactInstanceManager(HomeViewController homeViewController, ReactInstanceManager reactInstanceManager) {
        homeViewController.reactInstanceManager = reactInstanceManager;
    }

    public static void injectRoutingService(HomeViewController homeViewController, RoutingService routingService) {
        homeViewController.routingService = routingService;
    }

    public static void injectSharedPreferencesRepository(HomeViewController homeViewController, DismissedCardDataStore dismissedCardDataStore) {
        homeViewController.sharedPreferencesRepository = dismissedCardDataStore;
    }

    public static void injectVoiceService(HomeViewController homeViewController, VoiceService voiceService) {
        homeViewController.voiceService = voiceService;
    }

    public static void injectVoxIngressRepository(HomeViewController homeViewController, VoxIngressRepository voxIngressRepository) {
        homeViewController.voxIngressRepository = voxIngressRepository;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(HomeViewController homeViewController) {
        injectHomeCardsRepository(homeViewController, this.homeCardsRepositoryProvider.mo10268get());
        injectVoxIngressRepository(homeViewController, this.voxIngressRepositoryProvider.mo10268get());
        injectActionFactory(homeViewController, this.actionFactoryProvider.mo10268get());
        injectHomeFeedServiceClient(homeViewController, this.homeFeedServiceClientProvider.mo10268get());
        injectEventBus(homeViewController, this.eventBusProvider.mo10268get());
        injectMobilytics(homeViewController, this.mobilyticsProvider.mo10268get());
        injectSharedPreferencesRepository(homeViewController, this.sharedPreferencesRepositoryProvider.mo10268get());
        injectReactInstanceManager(homeViewController, this.reactInstanceManagerProvider.mo10268get());
        injectLatencyInfra(homeViewController, this.latencyInfraProvider.mo10268get());
        injectNetworkService(homeViewController, this.networkServiceProvider.mo10268get());
        injectRoutingService(homeViewController, this.routingServiceProvider.mo10268get());
        injectLatencyReportingDelegate(homeViewController, this.latencyReportingDelegateProvider.mo10268get());
        injectBridgeStatusService(homeViewController, this.bridgeStatusServiceProvider.mo10268get());
        injectMainActivityLifecycleObserverRegistrar(homeViewController, this.mainActivityLifecycleObserverRegistrarProvider.mo10268get());
        injectVoiceService(homeViewController, this.voiceServiceProvider.mo10268get());
        injectHeaderNavigationDelegate(homeViewController, this.headerNavigationDelegateProvider.mo10268get());
        injectFeatureServiceV2(homeViewController, this.featureServiceV2Provider.mo10268get());
    }
}
