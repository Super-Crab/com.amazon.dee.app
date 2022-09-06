package com.amazon.alexa.drive.navigation;

import com.amazon.alexa.drive.metrics.NavigationMetrics;
import com.amazon.alexa.drive.metrics.TTCFRecordOnce;
import com.amazon.alexa.drive.network.NetworkConnectivityManager;
import com.amazon.alexa.drive.util.AutoModeCommonUtils;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class NavigationViewControllerV2_MembersInjector implements MembersInjector<NavigationViewControllerV2> {
    private final Provider<AutoModeCommonUtils> mAutoModeCommonUtilsProvider;
    private final Provider<DriveModeThemeManager> mDriveModeThemeManagerProvider;
    private final Provider<NavigationCardManager> mNavigationCardManagerProvider;
    private final Provider<NavigationDataProvider> mNavigationDataProvider;
    private final Provider<NavigationMetrics> mNavigationMetricsProvider;
    private final Provider<NetworkConnectivityManager> mNetworkConnectivityManagerProvider;
    private final Provider<PreferredNavigationAppContentResolver> mPreferredNavigationAppContentResolverProvider;
    private final Provider<TTCFRecordOnce> mTTCFRecordOnceProvider;

    public NavigationViewControllerV2_MembersInjector(Provider<DriveModeThemeManager> provider, Provider<PreferredNavigationAppContentResolver> provider2, Provider<NavigationCardManager> provider3, Provider<NavigationDataProvider> provider4, Provider<TTCFRecordOnce> provider5, Provider<NavigationMetrics> provider6, Provider<AutoModeCommonUtils> provider7, Provider<NetworkConnectivityManager> provider8) {
        this.mDriveModeThemeManagerProvider = provider;
        this.mPreferredNavigationAppContentResolverProvider = provider2;
        this.mNavigationCardManagerProvider = provider3;
        this.mNavigationDataProvider = provider4;
        this.mTTCFRecordOnceProvider = provider5;
        this.mNavigationMetricsProvider = provider6;
        this.mAutoModeCommonUtilsProvider = provider7;
        this.mNetworkConnectivityManagerProvider = provider8;
    }

    public static MembersInjector<NavigationViewControllerV2> create(Provider<DriveModeThemeManager> provider, Provider<PreferredNavigationAppContentResolver> provider2, Provider<NavigationCardManager> provider3, Provider<NavigationDataProvider> provider4, Provider<TTCFRecordOnce> provider5, Provider<NavigationMetrics> provider6, Provider<AutoModeCommonUtils> provider7, Provider<NetworkConnectivityManager> provider8) {
        return new NavigationViewControllerV2_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public static void injectMAutoModeCommonUtils(NavigationViewControllerV2 navigationViewControllerV2, AutoModeCommonUtils autoModeCommonUtils) {
        navigationViewControllerV2.mAutoModeCommonUtils = autoModeCommonUtils;
    }

    public static void injectMDriveModeThemeManager(NavigationViewControllerV2 navigationViewControllerV2, DriveModeThemeManager driveModeThemeManager) {
        navigationViewControllerV2.mDriveModeThemeManager = driveModeThemeManager;
    }

    public static void injectMNavigationCardManager(NavigationViewControllerV2 navigationViewControllerV2, NavigationCardManager navigationCardManager) {
        navigationViewControllerV2.mNavigationCardManager = navigationCardManager;
    }

    public static void injectMNavigationDataProvider(NavigationViewControllerV2 navigationViewControllerV2, NavigationDataProvider navigationDataProvider) {
        navigationViewControllerV2.mNavigationDataProvider = navigationDataProvider;
    }

    public static void injectMNavigationMetrics(NavigationViewControllerV2 navigationViewControllerV2, NavigationMetrics navigationMetrics) {
        navigationViewControllerV2.mNavigationMetrics = navigationMetrics;
    }

    public static void injectMNetworkConnectivityManager(NavigationViewControllerV2 navigationViewControllerV2, NetworkConnectivityManager networkConnectivityManager) {
        navigationViewControllerV2.mNetworkConnectivityManager = networkConnectivityManager;
    }

    public static void injectMPreferredNavigationAppContentResolver(NavigationViewControllerV2 navigationViewControllerV2, PreferredNavigationAppContentResolver preferredNavigationAppContentResolver) {
        navigationViewControllerV2.mPreferredNavigationAppContentResolver = preferredNavigationAppContentResolver;
    }

    public static void injectMTTCFRecordOnce(NavigationViewControllerV2 navigationViewControllerV2, TTCFRecordOnce tTCFRecordOnce) {
        navigationViewControllerV2.mTTCFRecordOnce = tTCFRecordOnce;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(NavigationViewControllerV2 navigationViewControllerV2) {
        injectMDriveModeThemeManager(navigationViewControllerV2, this.mDriveModeThemeManagerProvider.mo10268get());
        injectMPreferredNavigationAppContentResolver(navigationViewControllerV2, this.mPreferredNavigationAppContentResolverProvider.mo10268get());
        injectMNavigationCardManager(navigationViewControllerV2, this.mNavigationCardManagerProvider.mo10268get());
        injectMNavigationDataProvider(navigationViewControllerV2, this.mNavigationDataProvider.mo10268get());
        injectMTTCFRecordOnce(navigationViewControllerV2, this.mTTCFRecordOnceProvider.mo10268get());
        injectMNavigationMetrics(navigationViewControllerV2, this.mNavigationMetricsProvider.mo10268get());
        injectMAutoModeCommonUtils(navigationViewControllerV2, this.mAutoModeCommonUtilsProvider.mo10268get());
        injectMNetworkConnectivityManager(navigationViewControllerV2, this.mNetworkConnectivityManagerProvider.mo10268get());
    }
}
