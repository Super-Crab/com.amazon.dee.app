package com.amazon.alexa.drive.landing;

import com.amazon.alexa.drive.entertainment.EntertainmentManagerV2;
import com.amazon.alexa.drive.metrics.TTCFRecordOnce;
import com.amazon.alexa.drive.navigation.NavigationCardManager;
import com.amazon.alexa.drive.network.NetworkConnectivityManager;
import com.amazon.alexa.drive.util.AutoModeCommonUtils;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class LandingPageViewControllerV2_MembersInjector implements MembersInjector<LandingPageViewControllerV2> {
    private final Provider<DriveModeThemeManager> driveModeThemeManagerProvider;
    private final Provider<LandingPageVoiceHintHelper> landingPageVoiceHintHelperProvider;
    private final Provider<AutoModeCommonUtils> mAutoModeCommonUtilsProvider;
    private final Provider<EntertainmentManagerV2> mEntertainmentManagerProvider;
    private final Provider<NetworkConnectivityManager> mNetworkConnectivityManagerProvider;
    private final Provider<NavigationCardManager> navigationCardManagerProvider;
    private final Provider<TTCFRecordOnce> ttcfRecordOnceProvider;

    public LandingPageViewControllerV2_MembersInjector(Provider<TTCFRecordOnce> provider, Provider<DriveModeThemeManager> provider2, Provider<NavigationCardManager> provider3, Provider<EntertainmentManagerV2> provider4, Provider<LandingPageVoiceHintHelper> provider5, Provider<AutoModeCommonUtils> provider6, Provider<NetworkConnectivityManager> provider7) {
        this.ttcfRecordOnceProvider = provider;
        this.driveModeThemeManagerProvider = provider2;
        this.navigationCardManagerProvider = provider3;
        this.mEntertainmentManagerProvider = provider4;
        this.landingPageVoiceHintHelperProvider = provider5;
        this.mAutoModeCommonUtilsProvider = provider6;
        this.mNetworkConnectivityManagerProvider = provider7;
    }

    public static MembersInjector<LandingPageViewControllerV2> create(Provider<TTCFRecordOnce> provider, Provider<DriveModeThemeManager> provider2, Provider<NavigationCardManager> provider3, Provider<EntertainmentManagerV2> provider4, Provider<LandingPageVoiceHintHelper> provider5, Provider<AutoModeCommonUtils> provider6, Provider<NetworkConnectivityManager> provider7) {
        return new LandingPageViewControllerV2_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static void injectDriveModeThemeManager(LandingPageViewControllerV2 landingPageViewControllerV2, DriveModeThemeManager driveModeThemeManager) {
        landingPageViewControllerV2.driveModeThemeManager = driveModeThemeManager;
    }

    public static void injectLandingPageVoiceHintHelper(LandingPageViewControllerV2 landingPageViewControllerV2, LandingPageVoiceHintHelper landingPageVoiceHintHelper) {
        landingPageViewControllerV2.landingPageVoiceHintHelper = landingPageVoiceHintHelper;
    }

    public static void injectMAutoModeCommonUtils(LandingPageViewControllerV2 landingPageViewControllerV2, AutoModeCommonUtils autoModeCommonUtils) {
        landingPageViewControllerV2.mAutoModeCommonUtils = autoModeCommonUtils;
    }

    public static void injectMEntertainmentManager(LandingPageViewControllerV2 landingPageViewControllerV2, EntertainmentManagerV2 entertainmentManagerV2) {
        landingPageViewControllerV2.mEntertainmentManager = entertainmentManagerV2;
    }

    public static void injectMNetworkConnectivityManager(LandingPageViewControllerV2 landingPageViewControllerV2, NetworkConnectivityManager networkConnectivityManager) {
        landingPageViewControllerV2.mNetworkConnectivityManager = networkConnectivityManager;
    }

    public static void injectNavigationCardManager(LandingPageViewControllerV2 landingPageViewControllerV2, NavigationCardManager navigationCardManager) {
        landingPageViewControllerV2.navigationCardManager = navigationCardManager;
    }

    public static void injectTtcfRecordOnce(LandingPageViewControllerV2 landingPageViewControllerV2, TTCFRecordOnce tTCFRecordOnce) {
        landingPageViewControllerV2.ttcfRecordOnce = tTCFRecordOnce;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(LandingPageViewControllerV2 landingPageViewControllerV2) {
        injectTtcfRecordOnce(landingPageViewControllerV2, this.ttcfRecordOnceProvider.mo10268get());
        injectDriveModeThemeManager(landingPageViewControllerV2, this.driveModeThemeManagerProvider.mo10268get());
        injectNavigationCardManager(landingPageViewControllerV2, this.navigationCardManagerProvider.mo10268get());
        injectMEntertainmentManager(landingPageViewControllerV2, this.mEntertainmentManagerProvider.mo10268get());
        injectLandingPageVoiceHintHelper(landingPageViewControllerV2, this.landingPageVoiceHintHelperProvider.mo10268get());
        injectMAutoModeCommonUtils(landingPageViewControllerV2, this.mAutoModeCommonUtilsProvider.mo10268get());
        injectMNetworkConnectivityManager(landingPageViewControllerV2, this.mNetworkConnectivityManagerProvider.mo10268get());
    }
}
