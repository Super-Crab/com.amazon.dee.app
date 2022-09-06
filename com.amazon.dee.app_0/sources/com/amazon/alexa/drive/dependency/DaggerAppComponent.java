package com.amazon.alexa.drive.dependency;

import com.amazon.alexa.drive.comms.CommsManager;
import com.amazon.alexa.drive.comms.contract.CommsLandingPageContract;
import com.amazon.alexa.drive.comms.view.CommsLandingPageViewController;
import com.amazon.alexa.drive.comms.view.CommsLandingPageViewController_MembersInjector;
import com.amazon.alexa.drive.entertainment.EntertainmentManager;
import com.amazon.alexa.drive.entertainment.EntertainmentManagerV2;
import com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract;
import com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract;
import com.amazon.alexa.drive.entertainment.interactor.EntertainmentAsyncTaskFactory;
import com.amazon.alexa.drive.entertainment.repository.EntertainmentDataRepository;
import com.amazon.alexa.drive.entertainment.view.EntertainmentLandingPageViewController;
import com.amazon.alexa.drive.entertainment.view.EntertainmentLandingPageViewControllerV2;
import com.amazon.alexa.drive.entertainment.view.EntertainmentLandingPageViewControllerV2_MembersInjector;
import com.amazon.alexa.drive.entertainment.view.EntertainmentLandingPageViewController_MembersInjector;
import com.amazon.alexa.drive.entertainment.view.NowPlayingScreenViewController;
import com.amazon.alexa.drive.entertainment.view.NowPlayingScreenViewController_MembersInjector;
import com.amazon.alexa.drive.landing.LandingPageViewController;
import com.amazon.alexa.drive.landing.LandingPageViewControllerV2;
import com.amazon.alexa.drive.landing.LandingPageViewControllerV2_MembersInjector;
import com.amazon.alexa.drive.landing.LandingPageViewController_MembersInjector;
import com.amazon.alexa.drive.landing.LandingPageVoiceHintHelper;
import com.amazon.alexa.drive.main.DriveModeMainContract;
import com.amazon.alexa.drive.main.v2.DriveModeMainActivityViewModel;
import com.amazon.alexa.drive.main.v2.DriveModeMainActivityViewModel_MembersInjector;
import com.amazon.alexa.drive.metrics.DriveModeMetricsHelper;
import com.amazon.alexa.drive.metrics.EntertainmentMetrics;
import com.amazon.alexa.drive.metrics.LandingPageMetrics;
import com.amazon.alexa.drive.metrics.NavigationMetrics;
import com.amazon.alexa.drive.metrics.TTCFRecordOnce;
import com.amazon.alexa.drive.navigation.NavigationCardManager;
import com.amazon.alexa.drive.navigation.NavigationDataProvider;
import com.amazon.alexa.drive.navigation.NavigationRepo;
import com.amazon.alexa.drive.navigation.NavigationViewController;
import com.amazon.alexa.drive.navigation.NavigationViewControllerV2;
import com.amazon.alexa.drive.navigation.NavigationViewControllerV2_MembersInjector;
import com.amazon.alexa.drive.navigation.NavigationViewController_MembersInjector;
import com.amazon.alexa.drive.navigation.PreferredNavigationAppContentResolver;
import com.amazon.alexa.drive.network.NetworkConnectivityManager;
import com.amazon.alexa.drive.orientation.LandscapeErrorProvider;
import com.amazon.alexa.drive.smart.device.LockPermissionViewController;
import com.amazon.alexa.drive.smart.device.LockPermissionViewController_MembersInjector;
import com.amazon.alexa.drive.smart.device.SmartDevicePresenter;
import com.amazon.alexa.drive.smart.device.SmartHomeViewController;
import com.amazon.alexa.drive.smart.device.SmartHomeViewController_MembersInjector;
import com.amazon.alexa.drive.userstudy.DriverDistractionLog;
import com.amazon.alexa.drive.util.AutoModeCommonUtils;
import com.amazon.alexa.drive.view.ViewManagerViewController;
import com.amazon.alexa.drive.warning.WarningViewController;
import com.amazon.alexa.drive.warning.WarningViewController_MembersInjector;
import com.amazon.alexa.drive.weblab.WeblabManager;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.mode.ModeService;
import com.amazon.alexa.mode.metrics.DriveModeMetrics;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.amazon.alexa.ttcf.api.TTCFCheckpoint;
import dagger.internal.DelegateFactory;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class DaggerAppComponent implements AppComponent {
    private Provider<NavigationRepo> provideNavigationRepoProvider;

    /* loaded from: classes7.dex */
    public static final class Builder {
        private NavigationDataModule navigationDataModule;

        public AppComponent build() {
            if (this.navigationDataModule == null) {
                this.navigationDataModule = new NavigationDataModule();
            }
            return new DaggerAppComponent(this);
        }

        public Builder navigationDataModule(NavigationDataModule navigationDataModule) {
            this.navigationDataModule = (NavigationDataModule) Preconditions.checkNotNull(navigationDataModule);
            return this;
        }

        private Builder() {
        }
    }

    /* loaded from: classes7.dex */
    private final class DriveModeRootComponentImpl implements DriveModeRootComponent {
        private AndroidModule androidModule;
        private ModeMetricsModule modeMetricsModule;
        private PresenterModule presenterModule;
        private RepositoryModule_ProvideAlexaServicesConnectionFactory provideAlexaServicesConnectionProvider;
        private AndroidModule_ProvideAndroidContextFactory provideAndroidContextProvider;
        private Provider<AutoModeCommonUtils> provideAutoModeCommonUtilsProvider;
        private Provider<CommsLandingPageContract.Interactor> provideCommsLandingPageInteractorProvider;
        private Provider<CommsLandingPageContract.Presenter> provideCommsLandingPagePresenterProvider;
        private Provider<CommsManager> provideCommsManagerProvider;
        private Provider<DriveModeMetrics> provideDriveModeMetricsProvider;
        private Provider<DriveModeThemeManager> provideDriveModeThemeManagerProvider;
        private Provider<DriverDistractionLog> provideDriverDistractionProvider;
        private Provider<EntertainmentAsyncTaskFactory> provideEntertainmentAsyncTaskFactoryProvider;
        private Provider<EntertainmentDataRepository> provideEntertainmentDataRepositoryProvider;
        private Provider<EntertainmentLandingPageContract.EntertainmentInteractor> provideEntertainmentInteractorProvider;
        private Provider<EntertainmentLandingPageContract.Presenter> provideEntertainmentLandingPagePresenterProvider;
        private Provider<EntertainmentManager> provideEntertainmentManagerProvider;
        private Provider<EntertainmentManagerV2> provideEntertainmentManagerV2Provider;
        private Provider<EntertainmentMetrics> provideEntertainmentnMetricsProvider;
        private Provider<EventBus> provideEventBusProvider;
        private Provider<LandingPageMetrics> provideLandingPageMetricsProvider;
        private Provider<LandingPageVoiceHintHelper> provideLandingPageVoiceHintHelperProvider;
        private Provider<LandscapeErrorProvider> provideLandscapeErrorProvider;
        private Provider<DriveModeMetricsHelper> provideMetricsHelperProvider;
        private Provider<ModeService> provideModeServiceProvider;
        private Provider<NavigationCardManager> provideNavigationCardManagerProvider;
        private Provider<PreferredNavigationAppContentResolver> provideNavigationContentResolverProvider;
        private Provider<NavigationDataProvider> provideNavigationDataProvider;
        private Provider<NavigationMetrics> provideNavigationMetricsProvider;
        private Provider<NetworkConnectivityManager> provideNetworkConnectivityManagerProvider;
        private Provider<NowPlayingScreenContract.Interactor> provideNowPlayingScreenInteractorProvider;
        private Provider<NowPlayingScreenContract.Presenter> provideNowPlayingScreenPresenterProvider;
        private Provider<SmartDevicePresenter> provideSmartDevicePresenterProvider;
        private RepositoryModule_ProvideSunriseTimeProviderFactory provideSunriseTimeProvider;
        private Provider<LazyComponent<TTCFCheckpoint>> provideTTCFCheckPointProvider;
        private Provider<WeblabManager> provideWeblabManagerProvider;
        private RepositoryModule repositoryModule;

        private AMPDInformationProvider getAMPDInformationProvider() {
            return RepositoryModule_ProvideAMPDInformationProviderFactory.proxyProvideAMPDInformationProvider(this.repositoryModule, AndroidModule_ProvideAndroidContextFactory.proxyProvideAndroidContext(this.androidModule));
        }

        private DriveModeMainContract.Presenter getPresenter() {
            return PresenterModule_ProvideMainPresenterFactory.proxyProvideMainPresenter(this.presenterModule, this.provideMetricsHelperProvider.mo10268get(), this.provideWeblabManagerProvider.mo10268get());
        }

        private TTCFRecordOnce getTTCFRecordOnce() {
            return ModeMetricsModule_ProvideTTCFRecordOnceFactory.proxyProvideTTCFRecordOnce(this.modeMetricsModule, this.provideTTCFCheckPointProvider.mo10268get());
        }

        private void initialize(AndroidModule androidModule, RoutingModule routingModule) {
            this.provideLandingPageMetricsProvider = DoubleCheck.provider(ModeMetricsModule_ProvideLandingPageMetricsFactory.create(this.modeMetricsModule));
            this.provideAndroidContextProvider = AndroidModule_ProvideAndroidContextFactory.create(androidModule);
            this.provideDriverDistractionProvider = DoubleCheck.provider(ModeMetricsModule_ProvideDriverDistractionFactory.create(this.modeMetricsModule, this.provideAndroidContextProvider));
            this.provideModeServiceProvider = DoubleCheck.provider(RepositoryModule_ProvideModeServiceFactory.create(this.repositoryModule));
            this.provideMetricsHelperProvider = DoubleCheck.provider(ModeMetricsModule_ProvideMetricsHelperFactory.create(this.modeMetricsModule, this.provideLandingPageMetricsProvider, this.provideDriverDistractionProvider, this.provideModeServiceProvider));
            this.provideWeblabManagerProvider = DoubleCheck.provider(RepositoryModule_ProvideWeblabManagerFactory.create(this.repositoryModule));
            this.provideAlexaServicesConnectionProvider = RepositoryModule_ProvideAlexaServicesConnectionFactory.create(this.repositoryModule, this.provideAndroidContextProvider);
            this.provideDriveModeMetricsProvider = DoubleCheck.provider(ModeMetricsModule_ProvideDriveModeMetricsFactory.create(this.modeMetricsModule));
            this.provideSunriseTimeProvider = RepositoryModule_ProvideSunriseTimeProviderFactory.create(this.repositoryModule);
            this.provideDriveModeThemeManagerProvider = DoubleCheck.provider(RepositoryModule_ProvideDriveModeThemeManagerFactory.create(this.repositoryModule, this.provideAndroidContextProvider, this.provideAlexaServicesConnectionProvider, this.provideDriveModeMetricsProvider, this.provideSunriseTimeProvider));
            this.provideNetworkConnectivityManagerProvider = DoubleCheck.provider(RepositoryModule_ProvideNetworkConnectivityManagerFactory.create(this.repositoryModule));
            this.provideEventBusProvider = DoubleCheck.provider(RepositoryModule_ProvideEventBusFactory.create(this.repositoryModule));
            this.provideLandscapeErrorProvider = DoubleCheck.provider(RepositoryModule_ProvideLandscapeErrorProviderFactory.create(this.repositoryModule, this.provideAndroidContextProvider, this.provideModeServiceProvider));
            this.provideCommsLandingPageInteractorProvider = new DelegateFactory();
            this.provideCommsLandingPagePresenterProvider = DoubleCheck.provider(PresenterModule_ProvideCommsLandingPagePresenterFactory.create(this.presenterModule, this.provideCommsLandingPageInteractorProvider));
            DelegateFactory.setDelegate(this.provideCommsLandingPageInteractorProvider, DoubleCheck.provider(RepositoryModule_ProvideCommsLandingPageInteractorFactory.create(this.repositoryModule, this.provideCommsLandingPagePresenterProvider)));
            this.provideCommsManagerProvider = DoubleCheck.provider(RepositoryModule_ProvideCommsManagerFactory.create(this.repositoryModule, this.provideAndroidContextProvider, this.provideCommsLandingPageInteractorProvider, this.provideDriveModeThemeManagerProvider, this.provideWeblabManagerProvider));
            this.provideEntertainmentInteractorProvider = new DelegateFactory();
            this.provideEntertainmentLandingPagePresenterProvider = DoubleCheck.provider(PresenterModule_ProvideEntertainmentLandingPagePresenterFactory.create(this.presenterModule, this.provideEntertainmentInteractorProvider));
            this.provideEntertainmentDataRepositoryProvider = DoubleCheck.provider(RepositoryModule_ProvideEntertainmentDataRepositoryFactory.create(this.repositoryModule));
            this.provideNowPlayingScreenInteractorProvider = new DelegateFactory();
            this.provideNowPlayingScreenPresenterProvider = DoubleCheck.provider(PresenterModule_ProvideNowPlayingScreenPresenterFactory.create(this.presenterModule, this.provideNowPlayingScreenInteractorProvider));
            this.provideEntertainmentnMetricsProvider = DoubleCheck.provider(ModeMetricsModule_ProvideEntertainmentnMetricsFactory.create(this.modeMetricsModule));
            this.provideEntertainmentAsyncTaskFactoryProvider = DoubleCheck.provider(RepositoryModule_ProvideEntertainmentAsyncTaskFactoryFactory.create(this.repositoryModule, this.provideAndroidContextProvider, this.provideEntertainmentnMetricsProvider));
            DelegateFactory.setDelegate(this.provideNowPlayingScreenInteractorProvider, DoubleCheck.provider(RepositoryModule_ProvideNowPlayingScreenInteractorFactory.create(this.repositoryModule, this.provideNowPlayingScreenPresenterProvider, this.provideAlexaServicesConnectionProvider, this.provideEntertainmentAsyncTaskFactoryProvider)));
            DelegateFactory.setDelegate(this.provideEntertainmentInteractorProvider, DoubleCheck.provider(RepositoryModule_ProvideEntertainmentInteractorFactory.create(this.repositoryModule, this.provideEntertainmentLandingPagePresenterProvider, this.provideAlexaServicesConnectionProvider, this.provideEntertainmentDataRepositoryProvider, this.provideNowPlayingScreenInteractorProvider, this.provideEntertainmentAsyncTaskFactoryProvider, this.provideEntertainmentnMetricsProvider)));
            this.provideEntertainmentManagerProvider = DoubleCheck.provider(RepositoryModule_ProvideEntertainmentManagerFactory.create(this.repositoryModule, this.provideAndroidContextProvider, this.provideEntertainmentInteractorProvider, this.provideNowPlayingScreenInteractorProvider, this.provideNetworkConnectivityManagerProvider, this.provideEntertainmentnMetricsProvider, this.provideDriveModeThemeManagerProvider));
            this.provideNavigationContentResolverProvider = DoubleCheck.provider(RepositoryModule_ProvideNavigationContentResolverFactory.create(this.repositoryModule, this.provideAndroidContextProvider));
            this.provideNavigationMetricsProvider = DoubleCheck.provider(ModeMetricsModule_ProvideNavigationMetricsFactory.create(this.modeMetricsModule));
            this.provideNavigationDataProvider = DoubleCheck.provider(RepositoryModule_ProvideNavigationDataProviderFactory.create(this.repositoryModule, this.provideAndroidContextProvider, DaggerAppComponent.this.provideNavigationRepoProvider, this.provideNavigationMetricsProvider));
            this.provideNavigationCardManagerProvider = DoubleCheck.provider(RepositoryModule_ProvideNavigationCardManagerFactory.create(this.repositoryModule, this.provideAndroidContextProvider, this.provideNavigationDataProvider, this.provideNavigationContentResolverProvider, this.provideNavigationMetricsProvider, this.provideNetworkConnectivityManagerProvider, this.provideDriveModeThemeManagerProvider, this.provideWeblabManagerProvider));
            this.provideTTCFCheckPointProvider = DoubleCheck.provider(ModeMetricsModule_ProvideTTCFCheckPointFactory.create(this.modeMetricsModule));
            this.provideLandingPageVoiceHintHelperProvider = DoubleCheck.provider(RepositoryModule_ProvideLandingPageVoiceHintHelperFactory.create(this.repositoryModule, this.provideAndroidContextProvider));
            this.provideSmartDevicePresenterProvider = DoubleCheck.provider(RepositoryModule_ProvideSmartDevicePresenterFactory.create(this.repositoryModule, this.provideAndroidContextProvider, this.provideDriveModeThemeManagerProvider, this.provideWeblabManagerProvider));
            this.provideEntertainmentManagerV2Provider = DoubleCheck.provider(RepositoryModule_ProvideEntertainmentManagerV2Factory.create(this.repositoryModule, this.provideAndroidContextProvider, this.provideEntertainmentInteractorProvider, this.provideNowPlayingScreenInteractorProvider, this.provideNetworkConnectivityManagerProvider, this.provideEntertainmentnMetricsProvider, this.provideDriveModeThemeManagerProvider));
            this.provideAutoModeCommonUtilsProvider = DoubleCheck.provider(RepositoryModule_ProvideAutoModeCommonUtilsFactory.create(this.repositoryModule, this.provideMetricsHelperProvider, this.provideDriveModeMetricsProvider));
        }

        private CommsLandingPageViewController injectCommsLandingPageViewController(CommsLandingPageViewController commsLandingPageViewController) {
            CommsLandingPageViewController_MembersInjector.injectMCommsLandingPagePresenter(commsLandingPageViewController, this.provideCommsLandingPagePresenterProvider.mo10268get());
            CommsLandingPageViewController_MembersInjector.injectMDriveModeThemeManager(commsLandingPageViewController, this.provideDriveModeThemeManagerProvider.mo10268get());
            CommsLandingPageViewController_MembersInjector.injectMNetworkConnectivityManager(commsLandingPageViewController, this.provideNetworkConnectivityManagerProvider.mo10268get());
            return commsLandingPageViewController;
        }

        private DriveModeMainActivityViewModel injectDriveModeMainActivityViewModel(DriveModeMainActivityViewModel driveModeMainActivityViewModel) {
            DriveModeMainActivityViewModel_MembersInjector.injectPresenter(driveModeMainActivityViewModel, getPresenter());
            DriveModeMainActivityViewModel_MembersInjector.injectMDriveModeThemeManager(driveModeMainActivityViewModel, this.provideDriveModeThemeManagerProvider.mo10268get());
            DriveModeMainActivityViewModel_MembersInjector.injectMDriveModeMetrics(driveModeMainActivityViewModel, this.provideDriveModeMetricsProvider.mo10268get());
            DriveModeMainActivityViewModel_MembersInjector.injectMDriveModeMetricsHelper(driveModeMainActivityViewModel, this.provideMetricsHelperProvider.mo10268get());
            DriveModeMainActivityViewModel_MembersInjector.injectMNetworkConnectivityManager(driveModeMainActivityViewModel, this.provideNetworkConnectivityManagerProvider.mo10268get());
            DriveModeMainActivityViewModel_MembersInjector.injectMAMPDInformationProvider(driveModeMainActivityViewModel, getAMPDInformationProvider());
            DriveModeMainActivityViewModel_MembersInjector.injectMEventBus(driveModeMainActivityViewModel, this.provideEventBusProvider.mo10268get());
            DriveModeMainActivityViewModel_MembersInjector.injectModeService(driveModeMainActivityViewModel, this.provideModeServiceProvider.mo10268get());
            DriveModeMainActivityViewModel_MembersInjector.injectMWeblabManager(driveModeMainActivityViewModel, this.provideWeblabManagerProvider.mo10268get());
            DriveModeMainActivityViewModel_MembersInjector.injectMLandscapeErrorProvider(driveModeMainActivityViewModel, this.provideLandscapeErrorProvider.mo10268get());
            return driveModeMainActivityViewModel;
        }

        private EntertainmentLandingPageViewController injectEntertainmentLandingPageViewController(EntertainmentLandingPageViewController entertainmentLandingPageViewController) {
            EntertainmentLandingPageViewController_MembersInjector.injectMEntertainmentLandingPagePresenter(entertainmentLandingPageViewController, this.provideEntertainmentLandingPagePresenterProvider.mo10268get());
            EntertainmentLandingPageViewController_MembersInjector.injectMEntertainmentDataRepository(entertainmentLandingPageViewController, this.provideEntertainmentDataRepositoryProvider.mo10268get());
            EntertainmentLandingPageViewController_MembersInjector.injectMNetworkConnectivityManager(entertainmentLandingPageViewController, this.provideNetworkConnectivityManagerProvider.mo10268get());
            EntertainmentLandingPageViewController_MembersInjector.injectMTtcfRecordOnce(entertainmentLandingPageViewController, getTTCFRecordOnce());
            EntertainmentLandingPageViewController_MembersInjector.injectMEntertainmentMetrics(entertainmentLandingPageViewController, this.provideEntertainmentnMetricsProvider.mo10268get());
            EntertainmentLandingPageViewController_MembersInjector.injectMDriveModeThemeManager(entertainmentLandingPageViewController, this.provideDriveModeThemeManagerProvider.mo10268get());
            EntertainmentLandingPageViewController_MembersInjector.injectMWeblabManager(entertainmentLandingPageViewController, this.provideWeblabManagerProvider.mo10268get());
            return entertainmentLandingPageViewController;
        }

        private EntertainmentLandingPageViewControllerV2 injectEntertainmentLandingPageViewControllerV2(EntertainmentLandingPageViewControllerV2 entertainmentLandingPageViewControllerV2) {
            EntertainmentLandingPageViewControllerV2_MembersInjector.injectMEntertainmentLandingPagePresenter(entertainmentLandingPageViewControllerV2, this.provideEntertainmentLandingPagePresenterProvider.mo10268get());
            EntertainmentLandingPageViewControllerV2_MembersInjector.injectMEntertainmentDataRepository(entertainmentLandingPageViewControllerV2, this.provideEntertainmentDataRepositoryProvider.mo10268get());
            EntertainmentLandingPageViewControllerV2_MembersInjector.injectMNetworkConnectivityManager(entertainmentLandingPageViewControllerV2, this.provideNetworkConnectivityManagerProvider.mo10268get());
            EntertainmentLandingPageViewControllerV2_MembersInjector.injectMTtcfRecordOnce(entertainmentLandingPageViewControllerV2, getTTCFRecordOnce());
            EntertainmentLandingPageViewControllerV2_MembersInjector.injectMEntertainmentMetrics(entertainmentLandingPageViewControllerV2, this.provideEntertainmentnMetricsProvider.mo10268get());
            EntertainmentLandingPageViewControllerV2_MembersInjector.injectMDriveModeThemeManager(entertainmentLandingPageViewControllerV2, this.provideDriveModeThemeManagerProvider.mo10268get());
            EntertainmentLandingPageViewControllerV2_MembersInjector.injectMWeblabManager(entertainmentLandingPageViewControllerV2, this.provideWeblabManagerProvider.mo10268get());
            return entertainmentLandingPageViewControllerV2;
        }

        private LandingPageViewController injectLandingPageViewController(LandingPageViewController landingPageViewController) {
            LandingPageViewController_MembersInjector.injectCommsManager(landingPageViewController, this.provideCommsManagerProvider.mo10268get());
            LandingPageViewController_MembersInjector.injectMEntertainmentManager(landingPageViewController, this.provideEntertainmentManagerProvider.mo10268get());
            LandingPageViewController_MembersInjector.injectNavigationContentResolver(landingPageViewController, this.provideNavigationContentResolverProvider.mo10268get());
            LandingPageViewController_MembersInjector.injectModeService(landingPageViewController, this.provideModeServiceProvider.mo10268get());
            LandingPageViewController_MembersInjector.injectMEventBus(landingPageViewController, this.provideEventBusProvider.mo10268get());
            LandingPageViewController_MembersInjector.injectNavigationCardManager(landingPageViewController, this.provideNavigationCardManagerProvider.mo10268get());
            LandingPageViewController_MembersInjector.injectNetworkConnectivityManager(landingPageViewController, this.provideNetworkConnectivityManagerProvider.mo10268get());
            LandingPageViewController_MembersInjector.injectTtcfRecordOnce(landingPageViewController, getTTCFRecordOnce());
            LandingPageViewController_MembersInjector.injectLandingPageVoiceHintHelper(landingPageViewController, this.provideLandingPageVoiceHintHelperProvider.mo10268get());
            LandingPageViewController_MembersInjector.injectDriveModeThemeManager(landingPageViewController, this.provideDriveModeThemeManagerProvider.mo10268get());
            LandingPageViewController_MembersInjector.injectSmartDevicePresenter(landingPageViewController, this.provideSmartDevicePresenterProvider.mo10268get());
            LandingPageViewController_MembersInjector.injectWeblabManager(landingPageViewController, this.provideWeblabManagerProvider.mo10268get());
            return landingPageViewController;
        }

        private LandingPageViewControllerV2 injectLandingPageViewControllerV2(LandingPageViewControllerV2 landingPageViewControllerV2) {
            LandingPageViewControllerV2_MembersInjector.injectTtcfRecordOnce(landingPageViewControllerV2, getTTCFRecordOnce());
            LandingPageViewControllerV2_MembersInjector.injectDriveModeThemeManager(landingPageViewControllerV2, this.provideDriveModeThemeManagerProvider.mo10268get());
            LandingPageViewControllerV2_MembersInjector.injectNavigationCardManager(landingPageViewControllerV2, this.provideNavigationCardManagerProvider.mo10268get());
            LandingPageViewControllerV2_MembersInjector.injectMEntertainmentManager(landingPageViewControllerV2, this.provideEntertainmentManagerV2Provider.mo10268get());
            LandingPageViewControllerV2_MembersInjector.injectLandingPageVoiceHintHelper(landingPageViewControllerV2, this.provideLandingPageVoiceHintHelperProvider.mo10268get());
            LandingPageViewControllerV2_MembersInjector.injectMAutoModeCommonUtils(landingPageViewControllerV2, this.provideAutoModeCommonUtilsProvider.mo10268get());
            LandingPageViewControllerV2_MembersInjector.injectMNetworkConnectivityManager(landingPageViewControllerV2, this.provideNetworkConnectivityManagerProvider.mo10268get());
            return landingPageViewControllerV2;
        }

        private LockPermissionViewController injectLockPermissionViewController(LockPermissionViewController lockPermissionViewController) {
            LockPermissionViewController_MembersInjector.injectDriveModeThemeManager(lockPermissionViewController, this.provideDriveModeThemeManagerProvider.mo10268get());
            return lockPermissionViewController;
        }

        private NavigationViewController injectNavigationViewController(NavigationViewController navigationViewController) {
            NavigationViewController_MembersInjector.injectMNavigationDataProvider(navigationViewController, this.provideNavigationDataProvider.mo10268get());
            NavigationViewController_MembersInjector.injectMPreferredNavigationAppContentResolver(navigationViewController, this.provideNavigationContentResolverProvider.mo10268get());
            NavigationViewController_MembersInjector.injectMNavigationCardManager(navigationViewController, this.provideNavigationCardManagerProvider.mo10268get());
            NavigationViewController_MembersInjector.injectMTTCFRecordOnce(navigationViewController, getTTCFRecordOnce());
            NavigationViewController_MembersInjector.injectNavigationMetrics(navigationViewController, this.provideNavigationMetricsProvider.mo10268get());
            NavigationViewController_MembersInjector.injectMNetworkConnectivityManager(navigationViewController, this.provideNetworkConnectivityManagerProvider.mo10268get());
            NavigationViewController_MembersInjector.injectMDriveModeThemeManager(navigationViewController, this.provideDriveModeThemeManagerProvider.mo10268get());
            return navigationViewController;
        }

        private NavigationViewControllerV2 injectNavigationViewControllerV2(NavigationViewControllerV2 navigationViewControllerV2) {
            NavigationViewControllerV2_MembersInjector.injectMDriveModeThemeManager(navigationViewControllerV2, this.provideDriveModeThemeManagerProvider.mo10268get());
            NavigationViewControllerV2_MembersInjector.injectMPreferredNavigationAppContentResolver(navigationViewControllerV2, this.provideNavigationContentResolverProvider.mo10268get());
            NavigationViewControllerV2_MembersInjector.injectMNavigationCardManager(navigationViewControllerV2, this.provideNavigationCardManagerProvider.mo10268get());
            NavigationViewControllerV2_MembersInjector.injectMNavigationDataProvider(navigationViewControllerV2, this.provideNavigationDataProvider.mo10268get());
            NavigationViewControllerV2_MembersInjector.injectMTTCFRecordOnce(navigationViewControllerV2, getTTCFRecordOnce());
            NavigationViewControllerV2_MembersInjector.injectMNavigationMetrics(navigationViewControllerV2, this.provideNavigationMetricsProvider.mo10268get());
            NavigationViewControllerV2_MembersInjector.injectMAutoModeCommonUtils(navigationViewControllerV2, this.provideAutoModeCommonUtilsProvider.mo10268get());
            NavigationViewControllerV2_MembersInjector.injectMNetworkConnectivityManager(navigationViewControllerV2, this.provideNetworkConnectivityManagerProvider.mo10268get());
            return navigationViewControllerV2;
        }

        private NowPlayingScreenViewController injectNowPlayingScreenViewController(NowPlayingScreenViewController nowPlayingScreenViewController) {
            NowPlayingScreenViewController_MembersInjector.injectMNowPlayingScreenPresenter(nowPlayingScreenViewController, this.provideNowPlayingScreenPresenterProvider.mo10268get());
            NowPlayingScreenViewController_MembersInjector.injectMNetworkConnectivityManager(nowPlayingScreenViewController, this.provideNetworkConnectivityManagerProvider.mo10268get());
            NowPlayingScreenViewController_MembersInjector.injectMTTCFRecordOnce(nowPlayingScreenViewController, getTTCFRecordOnce());
            NowPlayingScreenViewController_MembersInjector.injectMEntertainmentMetrics(nowPlayingScreenViewController, this.provideEntertainmentnMetricsProvider.mo10268get());
            NowPlayingScreenViewController_MembersInjector.injectMDriveModeThemeManager(nowPlayingScreenViewController, this.provideDriveModeThemeManagerProvider.mo10268get());
            NowPlayingScreenViewController_MembersInjector.injectMWeblabManager(nowPlayingScreenViewController, this.provideWeblabManagerProvider.mo10268get());
            return nowPlayingScreenViewController;
        }

        private SmartHomeViewController injectSmartHomeViewController(SmartHomeViewController smartHomeViewController) {
            SmartHomeViewController_MembersInjector.injectDriveModeThemeManager(smartHomeViewController, this.provideDriveModeThemeManagerProvider.mo10268get());
            SmartHomeViewController_MembersInjector.injectSmartDevicePresenter(smartHomeViewController, this.provideSmartDevicePresenterProvider.mo10268get());
            return smartHomeViewController;
        }

        private WarningViewController injectWarningViewController(WarningViewController warningViewController) {
            WarningViewController_MembersInjector.injectMDriveModeThemeManager(warningViewController, this.provideDriveModeThemeManagerProvider.mo10268get());
            return warningViewController;
        }

        @Override // com.amazon.alexa.drive.dependency.DriveModeRootComponent
        public void inject(DriveModeMainActivityViewModel driveModeMainActivityViewModel) {
            injectDriveModeMainActivityViewModel(driveModeMainActivityViewModel);
        }

        @Override // com.amazon.alexa.drive.dependency.DriveModeRootComponent
        public void inject(ViewManagerViewController viewManagerViewController) {
        }

        private DriveModeRootComponentImpl(AndroidModule androidModule, RoutingModule routingModule) {
            this.modeMetricsModule = new ModeMetricsModule();
            this.androidModule = (AndroidModule) Preconditions.checkNotNull(androidModule);
            this.repositoryModule = new RepositoryModule();
            this.presenterModule = new PresenterModule();
            initialize(androidModule, routingModule);
        }

        @Override // com.amazon.alexa.drive.dependency.DriveModeRootComponent
        public void inject(LandingPageViewController landingPageViewController) {
            injectLandingPageViewController(landingPageViewController);
        }

        @Override // com.amazon.alexa.drive.dependency.DriveModeRootComponent
        public void inject(LandingPageViewControllerV2 landingPageViewControllerV2) {
            injectLandingPageViewControllerV2(landingPageViewControllerV2);
        }

        @Override // com.amazon.alexa.drive.dependency.DriveModeRootComponent
        public void inject(NavigationViewController navigationViewController) {
            injectNavigationViewController(navigationViewController);
        }

        @Override // com.amazon.alexa.drive.dependency.DriveModeRootComponent
        public void inject(NavigationViewControllerV2 navigationViewControllerV2) {
            injectNavigationViewControllerV2(navigationViewControllerV2);
        }

        @Override // com.amazon.alexa.drive.dependency.DriveModeRootComponent
        public void inject(SmartHomeViewController smartHomeViewController) {
            injectSmartHomeViewController(smartHomeViewController);
        }

        @Override // com.amazon.alexa.drive.dependency.DriveModeRootComponent
        public void inject(LockPermissionViewController lockPermissionViewController) {
            injectLockPermissionViewController(lockPermissionViewController);
        }

        @Override // com.amazon.alexa.drive.dependency.DriveModeRootComponent
        public void inject(EntertainmentLandingPageViewController entertainmentLandingPageViewController) {
            injectEntertainmentLandingPageViewController(entertainmentLandingPageViewController);
        }

        @Override // com.amazon.alexa.drive.dependency.DriveModeRootComponent
        public void inject(EntertainmentLandingPageViewControllerV2 entertainmentLandingPageViewControllerV2) {
            injectEntertainmentLandingPageViewControllerV2(entertainmentLandingPageViewControllerV2);
        }

        @Override // com.amazon.alexa.drive.dependency.DriveModeRootComponent
        public void inject(NowPlayingScreenViewController nowPlayingScreenViewController) {
            injectNowPlayingScreenViewController(nowPlayingScreenViewController);
        }

        @Override // com.amazon.alexa.drive.dependency.DriveModeRootComponent
        public void inject(WarningViewController warningViewController) {
            injectWarningViewController(warningViewController);
        }

        @Override // com.amazon.alexa.drive.dependency.DriveModeRootComponent
        public void inject(CommsLandingPageViewController commsLandingPageViewController) {
            injectCommsLandingPageViewController(commsLandingPageViewController);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static AppComponent create() {
        return new Builder().build();
    }

    private void initialize(Builder builder) {
        this.provideNavigationRepoProvider = DoubleCheck.provider(NavigationDataModule_ProvideNavigationRepoFactory.create(builder.navigationDataModule));
    }

    @Override // com.amazon.alexa.drive.dependency.AppComponent
    public DriveModeRootComponent getDriveModeRootComponent(AndroidModule androidModule, RoutingModule routingModule) {
        return new DriveModeRootComponentImpl(androidModule, routingModule);
    }

    private DaggerAppComponent(Builder builder) {
        initialize(builder);
    }
}
