package com.amazon.alexa.drive.landing;

import com.amazon.alexa.drive.comms.CommsManager;
import com.amazon.alexa.drive.entertainment.EntertainmentManager;
import com.amazon.alexa.drive.metrics.TTCFRecordOnce;
import com.amazon.alexa.drive.navigation.NavigationCardManager;
import com.amazon.alexa.drive.navigation.PreferredNavigationAppContentResolver;
import com.amazon.alexa.drive.network.NetworkConnectivityManager;
import com.amazon.alexa.drive.smart.device.SmartDevicePresenter;
import com.amazon.alexa.drive.weblab.WeblabManager;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.mode.ModeService;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class LandingPageViewController_MembersInjector implements MembersInjector<LandingPageViewController> {
    private final Provider<CommsManager> commsManagerProvider;
    private final Provider<DriveModeThemeManager> driveModeThemeManagerProvider;
    private final Provider<LandingPageVoiceHintHelper> landingPageVoiceHintHelperProvider;
    private final Provider<EntertainmentManager> mEntertainmentManagerProvider;
    private final Provider<EventBus> mEventBusProvider;
    private final Provider<ModeService> modeServiceProvider;
    private final Provider<NavigationCardManager> navigationCardManagerProvider;
    private final Provider<PreferredNavigationAppContentResolver> navigationContentResolverProvider;
    private final Provider<NetworkConnectivityManager> networkConnectivityManagerProvider;
    private final Provider<SmartDevicePresenter> smartDevicePresenterProvider;
    private final Provider<TTCFRecordOnce> ttcfRecordOnceProvider;
    private final Provider<WeblabManager> weblabManagerProvider;

    public LandingPageViewController_MembersInjector(Provider<CommsManager> provider, Provider<EntertainmentManager> provider2, Provider<PreferredNavigationAppContentResolver> provider3, Provider<ModeService> provider4, Provider<EventBus> provider5, Provider<NavigationCardManager> provider6, Provider<NetworkConnectivityManager> provider7, Provider<TTCFRecordOnce> provider8, Provider<LandingPageVoiceHintHelper> provider9, Provider<DriveModeThemeManager> provider10, Provider<SmartDevicePresenter> provider11, Provider<WeblabManager> provider12) {
        this.commsManagerProvider = provider;
        this.mEntertainmentManagerProvider = provider2;
        this.navigationContentResolverProvider = provider3;
        this.modeServiceProvider = provider4;
        this.mEventBusProvider = provider5;
        this.navigationCardManagerProvider = provider6;
        this.networkConnectivityManagerProvider = provider7;
        this.ttcfRecordOnceProvider = provider8;
        this.landingPageVoiceHintHelperProvider = provider9;
        this.driveModeThemeManagerProvider = provider10;
        this.smartDevicePresenterProvider = provider11;
        this.weblabManagerProvider = provider12;
    }

    public static MembersInjector<LandingPageViewController> create(Provider<CommsManager> provider, Provider<EntertainmentManager> provider2, Provider<PreferredNavigationAppContentResolver> provider3, Provider<ModeService> provider4, Provider<EventBus> provider5, Provider<NavigationCardManager> provider6, Provider<NetworkConnectivityManager> provider7, Provider<TTCFRecordOnce> provider8, Provider<LandingPageVoiceHintHelper> provider9, Provider<DriveModeThemeManager> provider10, Provider<SmartDevicePresenter> provider11, Provider<WeblabManager> provider12) {
        return new LandingPageViewController_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12);
    }

    public static void injectCommsManager(LandingPageViewController landingPageViewController, CommsManager commsManager) {
        landingPageViewController.commsManager = commsManager;
    }

    public static void injectDriveModeThemeManager(LandingPageViewController landingPageViewController, DriveModeThemeManager driveModeThemeManager) {
        landingPageViewController.driveModeThemeManager = driveModeThemeManager;
    }

    public static void injectLandingPageVoiceHintHelper(LandingPageViewController landingPageViewController, LandingPageVoiceHintHelper landingPageVoiceHintHelper) {
        landingPageViewController.landingPageVoiceHintHelper = landingPageVoiceHintHelper;
    }

    public static void injectMEntertainmentManager(LandingPageViewController landingPageViewController, EntertainmentManager entertainmentManager) {
        landingPageViewController.mEntertainmentManager = entertainmentManager;
    }

    public static void injectMEventBus(LandingPageViewController landingPageViewController, EventBus eventBus) {
        landingPageViewController.mEventBus = eventBus;
    }

    public static void injectModeService(LandingPageViewController landingPageViewController, ModeService modeService) {
        landingPageViewController.modeService = modeService;
    }

    public static void injectNavigationCardManager(LandingPageViewController landingPageViewController, NavigationCardManager navigationCardManager) {
        landingPageViewController.navigationCardManager = navigationCardManager;
    }

    public static void injectNavigationContentResolver(LandingPageViewController landingPageViewController, PreferredNavigationAppContentResolver preferredNavigationAppContentResolver) {
        landingPageViewController.navigationContentResolver = preferredNavigationAppContentResolver;
    }

    public static void injectNetworkConnectivityManager(LandingPageViewController landingPageViewController, NetworkConnectivityManager networkConnectivityManager) {
        landingPageViewController.networkConnectivityManager = networkConnectivityManager;
    }

    public static void injectSmartDevicePresenter(LandingPageViewController landingPageViewController, SmartDevicePresenter smartDevicePresenter) {
        landingPageViewController.smartDevicePresenter = smartDevicePresenter;
    }

    public static void injectTtcfRecordOnce(LandingPageViewController landingPageViewController, TTCFRecordOnce tTCFRecordOnce) {
        landingPageViewController.ttcfRecordOnce = tTCFRecordOnce;
    }

    public static void injectWeblabManager(LandingPageViewController landingPageViewController, WeblabManager weblabManager) {
        landingPageViewController.weblabManager = weblabManager;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(LandingPageViewController landingPageViewController) {
        injectCommsManager(landingPageViewController, this.commsManagerProvider.mo10268get());
        injectMEntertainmentManager(landingPageViewController, this.mEntertainmentManagerProvider.mo10268get());
        injectNavigationContentResolver(landingPageViewController, this.navigationContentResolverProvider.mo10268get());
        injectModeService(landingPageViewController, this.modeServiceProvider.mo10268get());
        injectMEventBus(landingPageViewController, this.mEventBusProvider.mo10268get());
        injectNavigationCardManager(landingPageViewController, this.navigationCardManagerProvider.mo10268get());
        injectNetworkConnectivityManager(landingPageViewController, this.networkConnectivityManagerProvider.mo10268get());
        injectTtcfRecordOnce(landingPageViewController, this.ttcfRecordOnceProvider.mo10268get());
        injectLandingPageVoiceHintHelper(landingPageViewController, this.landingPageVoiceHintHelperProvider.mo10268get());
        injectDriveModeThemeManager(landingPageViewController, this.driveModeThemeManagerProvider.mo10268get());
        injectSmartDevicePresenter(landingPageViewController, this.smartDevicePresenterProvider.mo10268get());
        injectWeblabManager(landingPageViewController, this.weblabManagerProvider.mo10268get());
    }
}
