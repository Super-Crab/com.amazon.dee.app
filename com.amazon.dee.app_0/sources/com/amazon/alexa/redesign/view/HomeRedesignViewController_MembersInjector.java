package com.amazon.alexa.redesign.view;

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
public final class HomeRedesignViewController_MembersInjector implements MembersInjector<HomeRedesignViewController> {
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

    public HomeRedesignViewController_MembersInjector(Provider<HomeCardsRepository> provider, Provider<VoxIngressRepository> provider2, Provider<ActionFactory> provider3, Provider<HomeFeedServiceClient> provider4, Provider<EventBus> provider5, Provider<Mobilytics> provider6, Provider<DismissedCardDataStore> provider7, Provider<ReactInstanceManager> provider8, Provider<LatencyInfra> provider9, Provider<NetworkService> provider10, Provider<RoutingService> provider11, Provider<LatencyReportingDelegate> provider12, Provider<BridgeStatusService> provider13, Provider<MainActivityLifecycleObserverRegistrar> provider14, Provider<VoiceService> provider15, Provider<HeaderNavigationDelegate> provider16, Provider<FeatureServiceV2> provider17) {
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

    public static MembersInjector<HomeRedesignViewController> create(Provider<HomeCardsRepository> provider, Provider<VoxIngressRepository> provider2, Provider<ActionFactory> provider3, Provider<HomeFeedServiceClient> provider4, Provider<EventBus> provider5, Provider<Mobilytics> provider6, Provider<DismissedCardDataStore> provider7, Provider<ReactInstanceManager> provider8, Provider<LatencyInfra> provider9, Provider<NetworkService> provider10, Provider<RoutingService> provider11, Provider<LatencyReportingDelegate> provider12, Provider<BridgeStatusService> provider13, Provider<MainActivityLifecycleObserverRegistrar> provider14, Provider<VoiceService> provider15, Provider<HeaderNavigationDelegate> provider16, Provider<FeatureServiceV2> provider17) {
        return new HomeRedesignViewController_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17);
    }

    public static void injectActionFactory(HomeRedesignViewController homeRedesignViewController, ActionFactory actionFactory) {
        homeRedesignViewController.actionFactory = actionFactory;
    }

    public static void injectBridgeStatusService(HomeRedesignViewController homeRedesignViewController, BridgeStatusService bridgeStatusService) {
        homeRedesignViewController.bridgeStatusService = bridgeStatusService;
    }

    public static void injectEventBus(HomeRedesignViewController homeRedesignViewController, EventBus eventBus) {
        homeRedesignViewController.eventBus = eventBus;
    }

    public static void injectFeatureServiceV2(HomeRedesignViewController homeRedesignViewController, FeatureServiceV2 featureServiceV2) {
        homeRedesignViewController.featureServiceV2 = featureServiceV2;
    }

    public static void injectHeaderNavigationDelegate(HomeRedesignViewController homeRedesignViewController, HeaderNavigationDelegate headerNavigationDelegate) {
        homeRedesignViewController.headerNavigationDelegate = headerNavigationDelegate;
    }

    public static void injectHomeCardsRepository(HomeRedesignViewController homeRedesignViewController, HomeCardsRepository homeCardsRepository) {
        homeRedesignViewController.homeCardsRepository = homeCardsRepository;
    }

    public static void injectHomeFeedServiceClient(HomeRedesignViewController homeRedesignViewController, HomeFeedServiceClient homeFeedServiceClient) {
        homeRedesignViewController.homeFeedServiceClient = homeFeedServiceClient;
    }

    public static void injectLatencyInfra(HomeRedesignViewController homeRedesignViewController, LatencyInfra latencyInfra) {
        homeRedesignViewController.latencyInfra = latencyInfra;
    }

    public static void injectLatencyReportingDelegate(HomeRedesignViewController homeRedesignViewController, LatencyReportingDelegate latencyReportingDelegate) {
        homeRedesignViewController.latencyReportingDelegate = latencyReportingDelegate;
    }

    public static void injectMainActivityLifecycleObserverRegistrar(HomeRedesignViewController homeRedesignViewController, MainActivityLifecycleObserverRegistrar mainActivityLifecycleObserverRegistrar) {
        homeRedesignViewController.mainActivityLifecycleObserverRegistrar = mainActivityLifecycleObserverRegistrar;
    }

    public static void injectMobilytics(HomeRedesignViewController homeRedesignViewController, Mobilytics mobilytics) {
        homeRedesignViewController.mobilytics = mobilytics;
    }

    public static void injectNetworkService(HomeRedesignViewController homeRedesignViewController, NetworkService networkService) {
        homeRedesignViewController.networkService = networkService;
    }

    public static void injectReactInstanceManager(HomeRedesignViewController homeRedesignViewController, ReactInstanceManager reactInstanceManager) {
        homeRedesignViewController.reactInstanceManager = reactInstanceManager;
    }

    public static void injectRoutingService(HomeRedesignViewController homeRedesignViewController, RoutingService routingService) {
        homeRedesignViewController.routingService = routingService;
    }

    public static void injectSharedPreferencesRepository(HomeRedesignViewController homeRedesignViewController, DismissedCardDataStore dismissedCardDataStore) {
        homeRedesignViewController.sharedPreferencesRepository = dismissedCardDataStore;
    }

    public static void injectVoiceService(HomeRedesignViewController homeRedesignViewController, VoiceService voiceService) {
        homeRedesignViewController.voiceService = voiceService;
    }

    public static void injectVoxIngressRepository(HomeRedesignViewController homeRedesignViewController, VoxIngressRepository voxIngressRepository) {
        homeRedesignViewController.voxIngressRepository = voxIngressRepository;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(HomeRedesignViewController homeRedesignViewController) {
        injectHomeCardsRepository(homeRedesignViewController, this.homeCardsRepositoryProvider.mo10268get());
        injectVoxIngressRepository(homeRedesignViewController, this.voxIngressRepositoryProvider.mo10268get());
        injectActionFactory(homeRedesignViewController, this.actionFactoryProvider.mo10268get());
        injectHomeFeedServiceClient(homeRedesignViewController, this.homeFeedServiceClientProvider.mo10268get());
        injectEventBus(homeRedesignViewController, this.eventBusProvider.mo10268get());
        injectMobilytics(homeRedesignViewController, this.mobilyticsProvider.mo10268get());
        injectSharedPreferencesRepository(homeRedesignViewController, this.sharedPreferencesRepositoryProvider.mo10268get());
        injectReactInstanceManager(homeRedesignViewController, this.reactInstanceManagerProvider.mo10268get());
        injectLatencyInfra(homeRedesignViewController, this.latencyInfraProvider.mo10268get());
        injectNetworkService(homeRedesignViewController, this.networkServiceProvider.mo10268get());
        injectRoutingService(homeRedesignViewController, this.routingServiceProvider.mo10268get());
        injectLatencyReportingDelegate(homeRedesignViewController, this.latencyReportingDelegateProvider.mo10268get());
        injectBridgeStatusService(homeRedesignViewController, this.bridgeStatusServiceProvider.mo10268get());
        injectMainActivityLifecycleObserverRegistrar(homeRedesignViewController, this.mainActivityLifecycleObserverRegistrarProvider.mo10268get());
        injectVoiceService(homeRedesignViewController, this.voiceServiceProvider.mo10268get());
        injectHeaderNavigationDelegate(homeRedesignViewController, this.headerNavigationDelegateProvider.mo10268get());
        injectFeatureServiceV2(homeRedesignViewController, this.featureServiceV2Provider.mo10268get());
    }
}
