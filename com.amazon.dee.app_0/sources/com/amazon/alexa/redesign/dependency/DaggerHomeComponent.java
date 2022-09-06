package com.amazon.alexa.redesign.dependency;

import android.content.Context;
import com.amazon.alexa.elements.api.BridgeStatusService;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserverRegistrar;
import com.amazon.alexa.protocols.usermessage.UserMessageService;
import com.amazon.alexa.redesign.DismissedCardDataStore;
import com.amazon.alexa.redesign.LatencyReportingDelegate;
import com.amazon.alexa.redesign.actions.ActionFactory;
import com.amazon.alexa.redesign.cache.HomeCacheService;
import com.amazon.alexa.redesign.client.HomeFeedServiceClient;
import com.amazon.alexa.redesign.debug.DebugMenuActivity;
import com.amazon.alexa.redesign.header.HeaderNavigationDelegate;
import com.amazon.alexa.redesign.repository.HomeCardsRepository;
import com.amazon.alexa.redesign.repository.IsAppOnlyRepository;
import com.amazon.alexa.redesign.repository.LocaleRepository;
import com.amazon.alexa.redesign.repository.VoxIngressRepository;
import com.amazon.alexa.redesign.utils.HomeOEInteractor;
import com.amazon.alexa.redesign.view.HomeRedesignViewController;
import com.amazon.alexa.redesign.view.HomeRedesignViewController_MembersInjector;
import com.amazon.alexa.redesign.view.container.HomeViewController;
import com.amazon.alexa.redesign.view.container.HomeViewController_MembersInjector;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.voice.model.VoiceService;
import com.amazon.latencyinfra.LatencyInfra;
import com.dee.app.cachemanager.AppDataCacheEntry;
import com.dee.app.cachemanager.Cache;
import com.dee.app.http.CoralService;
import com.dee.app.metrics.MetricsServiceV2;
import com.facebook.react.ReactInstanceManager;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import java.util.concurrent.ExecutorService;
import javax.inject.Provider;
import rx.Scheduler;
/* loaded from: classes10.dex */
public final class DaggerHomeComponent implements HomeComponent {
    private ApplicationModule applicationModule;
    private Provider<ActionFactory> provideActionFactoryProvider;
    private Provider<BridgeStatusService> provideBridgeStatusServiceProvider;
    private Provider<Context> provideContextProvider;
    private Provider<CoralService> provideCoralServiceProvider;
    private Provider<EventBus> provideEventBusProvider;
    private Provider<FeatureServiceV2> provideFeatureServiceV2Provider;
    private Provider<HeaderNavigationDelegate> provideHeaderNavigationDelegateProvider;
    private Provider<Cache<AppDataCacheEntry>> provideHomeCacheProvider;
    private Provider<HomeCacheService> provideHomeCacheServiceProvider;
    private Provider<HomeCardsRepository> provideHomeCardsRepositoryProvider;
    private Provider<Scheduler> provideHomeDiskSchedulerProvider;
    private Provider<HomeFeedServiceClient> provideHomeFeedServiceClientProvider;
    private Provider<HomeOEInteractor> provideHomeOEInteractorProvider;
    private Provider<IdentityService> provideIdentityServiceProvider;
    private Provider<IsAppOnlyRepository> provideIsAppOnlyRepositoryProvider;
    private Provider<LatencyInfra> provideLatencyInfraProvider;
    private Provider<LatencyReportingDelegate> provideLatencyReportingDelegateProvider;
    private Provider<LocaleRepository> provideLocaleRepositoryProvider;
    private Provider<MainActivityLifecycleObserverRegistrar> provideMainActivityLifecycleObserverRegistrarProvider;
    private Provider<MetricsServiceV2> provideMetricsServiceProvider;
    private Provider<Mobilytics> provideMobilyticsProvider;
    private Provider<ReactInstanceManager> provideReactInstanceManagerProvider;
    private Provider<RoutingService> provideRoutingServiceProvider;
    private Provider<ExecutorService> provideShortLivedTaskExecutorProvider;
    private Provider<UserMessageService> provideUserMessageServiceProvider;
    private Provider<VoiceService> provideVoiceServiceProvider;
    private Provider<VoxIngressRepository> provideVoxIngressRepositoryProvider;
    private RepositoryModule repositoryModule;

    /* loaded from: classes10.dex */
    public static final class Builder {
        private ApplicationModule applicationModule;
        private CacheModule cacheModule;
        private RepositoryModule repositoryModule;
        private ServiceClientModule serviceClientModule;

        public Builder applicationModule(ApplicationModule applicationModule) {
            this.applicationModule = (ApplicationModule) Preconditions.checkNotNull(applicationModule);
            return this;
        }

        public HomeComponent build() {
            if (this.repositoryModule == null) {
                this.repositoryModule = new RepositoryModule();
            }
            if (this.serviceClientModule == null) {
                this.serviceClientModule = new ServiceClientModule();
            }
            Preconditions.checkBuilderRequirement(this.applicationModule, ApplicationModule.class);
            if (this.cacheModule == null) {
                this.cacheModule = new CacheModule();
            }
            return new DaggerHomeComponent(this);
        }

        public Builder cacheModule(CacheModule cacheModule) {
            this.cacheModule = (CacheModule) Preconditions.checkNotNull(cacheModule);
            return this;
        }

        public Builder repositoryModule(RepositoryModule repositoryModule) {
            this.repositoryModule = (RepositoryModule) Preconditions.checkNotNull(repositoryModule);
            return this;
        }

        public Builder serviceClientModule(ServiceClientModule serviceClientModule) {
            this.serviceClientModule = (ServiceClientModule) Preconditions.checkNotNull(serviceClientModule);
            return this;
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private DismissedCardDataStore getDismissedCardDataStore() {
        return RepositoryModule_ProvideDismissedCardDataStoreFactory.proxyProvideDismissedCardDataStore(this.repositoryModule, this.provideContextProvider.mo10268get());
    }

    private void initialize(Builder builder) {
        this.provideCoralServiceProvider = DoubleCheck.provider(ApplicationModule_ProvideCoralServiceFactory.create(builder.applicationModule));
        this.provideHomeFeedServiceClientProvider = DoubleCheck.provider(ServiceClientModule_ProvideHomeFeedServiceClientFactory.create(builder.serviceClientModule, this.provideCoralServiceProvider));
        this.provideContextProvider = DoubleCheck.provider(ApplicationModule_ProvideContextFactory.create(builder.applicationModule));
        this.provideMetricsServiceProvider = DoubleCheck.provider(ApplicationModule_ProvideMetricsServiceFactory.create(builder.applicationModule));
        this.provideShortLivedTaskExecutorProvider = DoubleCheck.provider(ApplicationModule_ProvideShortLivedTaskExecutorFactory.create(builder.applicationModule));
        this.provideHomeDiskSchedulerProvider = DoubleCheck.provider(CacheModule_ProvideHomeDiskSchedulerFactory.create(builder.cacheModule));
        this.provideHomeCacheProvider = DoubleCheck.provider(CacheModule_ProvideHomeCacheFactory.create(builder.cacheModule, this.provideContextProvider, this.provideMetricsServiceProvider, this.provideShortLivedTaskExecutorProvider, this.provideHomeDiskSchedulerProvider));
        this.provideHomeCacheServiceProvider = DoubleCheck.provider(CacheModule_ProvideHomeCacheServiceFactory.create(builder.cacheModule, this.provideHomeCacheProvider));
        this.provideMobilyticsProvider = DoubleCheck.provider(ApplicationModule_ProvideMobilyticsFactory.create(builder.applicationModule));
        this.provideRoutingServiceProvider = DoubleCheck.provider(ApplicationModule_ProvideRoutingServiceFactory.create(builder.applicationModule));
        this.provideEventBusProvider = DoubleCheck.provider(ApplicationModule_ProvideEventBusFactory.create(builder.applicationModule));
        this.provideHomeOEInteractorProvider = DoubleCheck.provider(RepositoryModule_ProvideHomeOEInteractorFactory.create(builder.repositoryModule, this.provideMobilyticsProvider));
        this.provideIdentityServiceProvider = DoubleCheck.provider(ApplicationModule_ProvideIdentityServiceFactory.create(builder.applicationModule));
        this.provideUserMessageServiceProvider = DoubleCheck.provider(ApplicationModule_ProvideUserMessageServiceFactory.create(builder.applicationModule));
        this.provideActionFactoryProvider = DoubleCheck.provider(RepositoryModule_ProvideActionFactoryFactory.create(builder.repositoryModule, this.provideRoutingServiceProvider, this.provideEventBusProvider, this.provideHomeOEInteractorProvider, this.provideHomeFeedServiceClientProvider, this.provideIdentityServiceProvider, this.provideUserMessageServiceProvider, this.provideContextProvider));
        this.provideIsAppOnlyRepositoryProvider = DoubleCheck.provider(RepositoryModule_ProvideIsAppOnlyRepositoryFactory.create(builder.repositoryModule, this.provideEventBusProvider, this.provideContextProvider));
        this.provideHomeCardsRepositoryProvider = DoubleCheck.provider(RepositoryModule_ProvideHomeCardsRepositoryFactory.create(builder.repositoryModule, this.provideHomeFeedServiceClientProvider, this.provideHomeCacheServiceProvider, this.provideMobilyticsProvider, this.provideActionFactoryProvider, this.provideContextProvider, this.provideIdentityServiceProvider, this.provideIsAppOnlyRepositoryProvider));
        this.provideVoiceServiceProvider = DoubleCheck.provider(ApplicationModule_ProvideVoiceServiceFactory.create(builder.applicationModule));
        this.provideLocaleRepositoryProvider = DoubleCheck.provider(RepositoryModule_ProvideLocaleRepositoryFactory.create(builder.repositoryModule));
        this.provideVoxIngressRepositoryProvider = DoubleCheck.provider(RepositoryModule_ProvideVoxIngressRepositoryFactory.create(builder.repositoryModule, this.provideActionFactoryProvider, this.provideVoiceServiceProvider, this.provideLocaleRepositoryProvider, this.provideContextProvider));
        this.provideReactInstanceManagerProvider = DoubleCheck.provider(ApplicationModule_ProvideReactInstanceManagerFactory.create(builder.applicationModule));
        this.provideLatencyInfraProvider = DoubleCheck.provider(ApplicationModule_ProvideLatencyInfraFactory.create(builder.applicationModule));
        this.provideLatencyReportingDelegateProvider = DoubleCheck.provider(ApplicationModule_ProvideLatencyReportingDelegateFactory.create(builder.applicationModule));
        this.provideBridgeStatusServiceProvider = DoubleCheck.provider(ApplicationModule_ProvideBridgeStatusServiceFactory.create(builder.applicationModule));
        this.provideMainActivityLifecycleObserverRegistrarProvider = DoubleCheck.provider(ApplicationModule_ProvideMainActivityLifecycleObserverRegistrarFactory.create(builder.applicationModule));
        this.provideHeaderNavigationDelegateProvider = DoubleCheck.provider(ApplicationModule_ProvideHeaderNavigationDelegateFactory.create(builder.applicationModule));
        this.provideFeatureServiceV2Provider = DoubleCheck.provider(ApplicationModule_ProvideFeatureServiceV2Factory.create(builder.applicationModule));
    }

    @CanIgnoreReturnValue
    private HomeRedesignViewController injectHomeRedesignViewController(HomeRedesignViewController homeRedesignViewController) {
        HomeRedesignViewController_MembersInjector.injectHomeCardsRepository(homeRedesignViewController, this.provideHomeCardsRepositoryProvider.mo10268get());
        HomeRedesignViewController_MembersInjector.injectVoxIngressRepository(homeRedesignViewController, this.provideVoxIngressRepositoryProvider.mo10268get());
        HomeRedesignViewController_MembersInjector.injectActionFactory(homeRedesignViewController, this.provideActionFactoryProvider.mo10268get());
        HomeRedesignViewController_MembersInjector.injectHomeFeedServiceClient(homeRedesignViewController, this.provideHomeFeedServiceClientProvider.mo10268get());
        HomeRedesignViewController_MembersInjector.injectEventBus(homeRedesignViewController, this.provideEventBusProvider.mo10268get());
        HomeRedesignViewController_MembersInjector.injectMobilytics(homeRedesignViewController, this.provideMobilyticsProvider.mo10268get());
        HomeRedesignViewController_MembersInjector.injectSharedPreferencesRepository(homeRedesignViewController, getDismissedCardDataStore());
        HomeRedesignViewController_MembersInjector.injectReactInstanceManager(homeRedesignViewController, this.provideReactInstanceManagerProvider.mo10268get());
        HomeRedesignViewController_MembersInjector.injectLatencyInfra(homeRedesignViewController, this.provideLatencyInfraProvider.mo10268get());
        HomeRedesignViewController_MembersInjector.injectNetworkService(homeRedesignViewController, ApplicationModule_ProvideNetworkServiceFactory.proxyProvideNetworkService(this.applicationModule));
        HomeRedesignViewController_MembersInjector.injectRoutingService(homeRedesignViewController, this.provideRoutingServiceProvider.mo10268get());
        HomeRedesignViewController_MembersInjector.injectLatencyReportingDelegate(homeRedesignViewController, this.provideLatencyReportingDelegateProvider.mo10268get());
        HomeRedesignViewController_MembersInjector.injectBridgeStatusService(homeRedesignViewController, this.provideBridgeStatusServiceProvider.mo10268get());
        HomeRedesignViewController_MembersInjector.injectMainActivityLifecycleObserverRegistrar(homeRedesignViewController, this.provideMainActivityLifecycleObserverRegistrarProvider.mo10268get());
        HomeRedesignViewController_MembersInjector.injectVoiceService(homeRedesignViewController, this.provideVoiceServiceProvider.mo10268get());
        HomeRedesignViewController_MembersInjector.injectHeaderNavigationDelegate(homeRedesignViewController, this.provideHeaderNavigationDelegateProvider.mo10268get());
        HomeRedesignViewController_MembersInjector.injectFeatureServiceV2(homeRedesignViewController, this.provideFeatureServiceV2Provider.mo10268get());
        return homeRedesignViewController;
    }

    @CanIgnoreReturnValue
    private HomeViewController injectHomeViewController(HomeViewController homeViewController) {
        HomeViewController_MembersInjector.injectHomeCardsRepository(homeViewController, this.provideHomeCardsRepositoryProvider.mo10268get());
        HomeViewController_MembersInjector.injectVoxIngressRepository(homeViewController, this.provideVoxIngressRepositoryProvider.mo10268get());
        HomeViewController_MembersInjector.injectActionFactory(homeViewController, this.provideActionFactoryProvider.mo10268get());
        HomeViewController_MembersInjector.injectHomeFeedServiceClient(homeViewController, this.provideHomeFeedServiceClientProvider.mo10268get());
        HomeViewController_MembersInjector.injectEventBus(homeViewController, this.provideEventBusProvider.mo10268get());
        HomeViewController_MembersInjector.injectMobilytics(homeViewController, this.provideMobilyticsProvider.mo10268get());
        HomeViewController_MembersInjector.injectSharedPreferencesRepository(homeViewController, getDismissedCardDataStore());
        HomeViewController_MembersInjector.injectReactInstanceManager(homeViewController, this.provideReactInstanceManagerProvider.mo10268get());
        HomeViewController_MembersInjector.injectLatencyInfra(homeViewController, this.provideLatencyInfraProvider.mo10268get());
        HomeViewController_MembersInjector.injectNetworkService(homeViewController, ApplicationModule_ProvideNetworkServiceFactory.proxyProvideNetworkService(this.applicationModule));
        HomeViewController_MembersInjector.injectRoutingService(homeViewController, this.provideRoutingServiceProvider.mo10268get());
        HomeViewController_MembersInjector.injectLatencyReportingDelegate(homeViewController, this.provideLatencyReportingDelegateProvider.mo10268get());
        HomeViewController_MembersInjector.injectBridgeStatusService(homeViewController, this.provideBridgeStatusServiceProvider.mo10268get());
        HomeViewController_MembersInjector.injectMainActivityLifecycleObserverRegistrar(homeViewController, this.provideMainActivityLifecycleObserverRegistrarProvider.mo10268get());
        HomeViewController_MembersInjector.injectVoiceService(homeViewController, this.provideVoiceServiceProvider.mo10268get());
        HomeViewController_MembersInjector.injectHeaderNavigationDelegate(homeViewController, this.provideHeaderNavigationDelegateProvider.mo10268get());
        HomeViewController_MembersInjector.injectFeatureServiceV2(homeViewController, this.provideFeatureServiceV2Provider.mo10268get());
        return homeViewController;
    }

    @Override // com.amazon.alexa.redesign.dependency.HomeComponent
    public void inject(DebugMenuActivity debugMenuActivity) {
    }

    @Override // com.amazon.alexa.redesign.dependency.HomeComponent
    public void inject(HomeRedesignViewController homeRedesignViewController) {
        injectHomeRedesignViewController(homeRedesignViewController);
    }

    private DaggerHomeComponent(Builder builder) {
        this.repositoryModule = builder.repositoryModule;
        this.applicationModule = builder.applicationModule;
        initialize(builder);
    }

    @Override // com.amazon.alexa.redesign.dependency.HomeComponent
    public void inject(HomeViewController homeViewController) {
        injectHomeViewController(homeViewController);
    }
}
