package com.amazon.alexa.drive.dependency;

import android.content.Context;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.drive.comms.CommsManager;
import com.amazon.alexa.drive.comms.contract.CommsLandingPageContract;
import com.amazon.alexa.drive.comms.interactor.CommsLandingPageInteractor;
import com.amazon.alexa.drive.entertainment.EntertainmentManager;
import com.amazon.alexa.drive.entertainment.EntertainmentManagerV2;
import com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract;
import com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract;
import com.amazon.alexa.drive.entertainment.interactor.EntertainmentAsyncTaskFactory;
import com.amazon.alexa.drive.entertainment.interactor.EntertainmentInteractor;
import com.amazon.alexa.drive.entertainment.interactor.NowPlayingScreenInteractor;
import com.amazon.alexa.drive.entertainment.repository.EntertainmentDataRepository;
import com.amazon.alexa.drive.landing.LandingPageVoiceHintHelper;
import com.amazon.alexa.drive.metrics.DriveModeMetricsHelper;
import com.amazon.alexa.drive.metrics.EntertainmentMetrics;
import com.amazon.alexa.drive.metrics.NavigationMetrics;
import com.amazon.alexa.drive.navigation.NavigationCardManager;
import com.amazon.alexa.drive.navigation.NavigationDataProvider;
import com.amazon.alexa.drive.navigation.NavigationRepo;
import com.amazon.alexa.drive.navigation.PreferredNavigationAppContentResolver;
import com.amazon.alexa.drive.network.NetworkConnectivityManager;
import com.amazon.alexa.drive.orientation.LandscapeErrorProvider;
import com.amazon.alexa.drive.smart.device.SmartDevicePresenter;
import com.amazon.alexa.drive.theme.DefaultDriveModeThemeManager;
import com.amazon.alexa.drive.theme.SunriseTimeProvider;
import com.amazon.alexa.drive.util.AutoModeCommonUtils;
import com.amazon.alexa.drive.weblab.WeblabManager;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.mode.ModeService;
import com.amazon.alexa.mode.metrics.DriveModeMetrics;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes7.dex */
class RepositoryModule {
    @Provides
    public AMPDInformationProvider provideAMPDInformationProvider(Context context) {
        return AMPDInformationProvider.getInstance(context);
    }

    @Provides
    public AlexaServicesConnection provideAlexaServicesConnection(Context context) {
        return new AlexaServicesConnection(context);
    }

    @Provides
    @Singleton
    public AutoModeCommonUtils provideAutoModeCommonUtils(DriveModeMetricsHelper driveModeMetricsHelper, DriveModeMetrics driveModeMetrics) {
        return new AutoModeCommonUtils(driveModeMetricsHelper, driveModeMetrics);
    }

    @Provides
    @Singleton
    public CommsLandingPageContract.Interactor provideCommsLandingPageInteractor(Lazy<CommsLandingPageContract.Presenter> lazy) {
        return new CommsLandingPageInteractor(lazy);
    }

    @Provides
    @Singleton
    public CommsManager provideCommsManager(Context context, CommsLandingPageContract.Interactor interactor, DriveModeThemeManager driveModeThemeManager, WeblabManager weblabManager) {
        return new CommsManager(context, interactor, driveModeThemeManager, weblabManager);
    }

    @Provides
    @Singleton
    public DriveModeThemeManager provideDriveModeThemeManager(Context context, AlexaServicesConnection alexaServicesConnection, DriveModeMetrics driveModeMetrics, SunriseTimeProvider sunriseTimeProvider) {
        return new DefaultDriveModeThemeManager(context, alexaServicesConnection, driveModeMetrics, sunriseTimeProvider);
    }

    @Provides
    @Singleton
    public EntertainmentAsyncTaskFactory provideEntertainmentAsyncTaskFactory(Context context, EntertainmentMetrics entertainmentMetrics) {
        return new EntertainmentAsyncTaskFactory(context, entertainmentMetrics);
    }

    @Provides
    @Singleton
    public EntertainmentDataRepository provideEntertainmentDataRepository() {
        return new EntertainmentDataRepository();
    }

    @Provides
    @Singleton
    public EntertainmentLandingPageContract.EntertainmentInteractor provideEntertainmentInteractor(Lazy<EntertainmentLandingPageContract.Presenter> lazy, AlexaServicesConnection alexaServicesConnection, EntertainmentDataRepository entertainmentDataRepository, NowPlayingScreenContract.Interactor interactor, EntertainmentAsyncTaskFactory entertainmentAsyncTaskFactory, EntertainmentMetrics entertainmentMetrics) {
        return new EntertainmentInteractor(lazy, alexaServicesConnection, entertainmentDataRepository, interactor, entertainmentAsyncTaskFactory, entertainmentMetrics);
    }

    @Provides
    @Singleton
    public EntertainmentManager provideEntertainmentManager(Context context, EntertainmentLandingPageContract.EntertainmentInteractor entertainmentInteractor, NowPlayingScreenContract.Interactor interactor, NetworkConnectivityManager networkConnectivityManager, EntertainmentMetrics entertainmentMetrics, DriveModeThemeManager driveModeThemeManager) {
        return new EntertainmentManager(context, entertainmentInteractor, interactor, networkConnectivityManager, entertainmentMetrics, driveModeThemeManager);
    }

    @Provides
    @Singleton
    public EntertainmentManagerV2 provideEntertainmentManagerV2(Context context, EntertainmentLandingPageContract.EntertainmentInteractor entertainmentInteractor, NowPlayingScreenContract.Interactor interactor, NetworkConnectivityManager networkConnectivityManager, EntertainmentMetrics entertainmentMetrics, DriveModeThemeManager driveModeThemeManager) {
        return new EntertainmentManagerV2(context, entertainmentInteractor, interactor, networkConnectivityManager, entertainmentMetrics, driveModeThemeManager);
    }

    @Provides
    @Singleton
    public EventBus provideEventBus() {
        return (EventBus) GeneratedOutlineSupport1.outline21(EventBus.class);
    }

    @Provides
    @Singleton
    public LandingPageVoiceHintHelper provideLandingPageVoiceHintHelper(Context context) {
        return new LandingPageVoiceHintHelper(context);
    }

    @Provides
    @Singleton
    public LandscapeErrorProvider provideLandscapeErrorProvider(Context context, ModeService modeService) {
        return new LandscapeErrorProvider(context, modeService);
    }

    @Provides
    @Singleton
    public ModeService provideModeService() {
        return (ModeService) GeneratedOutlineSupport1.outline21(ModeService.class);
    }

    @Provides
    @Singleton
    public NavigationCardManager provideNavigationCardManager(Context context, NavigationDataProvider navigationDataProvider, PreferredNavigationAppContentResolver preferredNavigationAppContentResolver, NavigationMetrics navigationMetrics, NetworkConnectivityManager networkConnectivityManager, DriveModeThemeManager driveModeThemeManager, WeblabManager weblabManager) {
        return new NavigationCardManager(context, navigationDataProvider, preferredNavigationAppContentResolver, navigationMetrics, networkConnectivityManager, driveModeThemeManager, weblabManager);
    }

    @Provides
    @Singleton
    public PreferredNavigationAppContentResolver provideNavigationContentResolver(Context context) {
        return new PreferredNavigationAppContentResolver(context);
    }

    @Provides
    @Singleton
    public NavigationDataProvider provideNavigationDataProvider(Context context, NavigationRepo navigationRepo, NavigationMetrics navigationMetrics) {
        return new NavigationDataProvider(context, navigationRepo, navigationMetrics);
    }

    @Provides
    @Singleton
    public NetworkConnectivityManager provideNetworkConnectivityManager() {
        return new NetworkConnectivityManager();
    }

    @Provides
    @Singleton
    public NowPlayingScreenContract.Interactor provideNowPlayingScreenInteractor(Lazy<NowPlayingScreenContract.Presenter> lazy, AlexaServicesConnection alexaServicesConnection, EntertainmentAsyncTaskFactory entertainmentAsyncTaskFactory) {
        return new NowPlayingScreenInteractor(lazy, alexaServicesConnection, entertainmentAsyncTaskFactory);
    }

    @Provides
    @Singleton
    public SmartDevicePresenter provideSmartDevicePresenter(Context context, DriveModeThemeManager driveModeThemeManager, WeblabManager weblabManager) {
        return new SmartDevicePresenter(context, driveModeThemeManager, weblabManager);
    }

    @Provides
    public SunriseTimeProvider provideSunriseTimeProvider() {
        return new SunriseTimeProvider();
    }

    @Provides
    @Singleton
    public WeblabManager provideWeblabManager() {
        return new WeblabManager();
    }
}
