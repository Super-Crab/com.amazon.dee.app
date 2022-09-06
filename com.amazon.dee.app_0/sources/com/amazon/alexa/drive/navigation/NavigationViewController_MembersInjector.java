package com.amazon.alexa.drive.navigation;

import com.amazon.alexa.drive.metrics.NavigationMetrics;
import com.amazon.alexa.drive.metrics.TTCFRecordOnce;
import com.amazon.alexa.drive.network.NetworkConnectivityManager;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class NavigationViewController_MembersInjector implements MembersInjector<NavigationViewController> {
    private final Provider<DriveModeThemeManager> mDriveModeThemeManagerProvider;
    private final Provider<NavigationCardManager> mNavigationCardManagerProvider;
    private final Provider<NavigationDataProvider> mNavigationDataProvider;
    private final Provider<NetworkConnectivityManager> mNetworkConnectivityManagerProvider;
    private final Provider<PreferredNavigationAppContentResolver> mPreferredNavigationAppContentResolverProvider;
    private final Provider<TTCFRecordOnce> mTTCFRecordOnceProvider;
    private final Provider<NavigationMetrics> navigationMetricsProvider;

    public NavigationViewController_MembersInjector(Provider<NavigationDataProvider> provider, Provider<PreferredNavigationAppContentResolver> provider2, Provider<NavigationCardManager> provider3, Provider<TTCFRecordOnce> provider4, Provider<NavigationMetrics> provider5, Provider<NetworkConnectivityManager> provider6, Provider<DriveModeThemeManager> provider7) {
        this.mNavigationDataProvider = provider;
        this.mPreferredNavigationAppContentResolverProvider = provider2;
        this.mNavigationCardManagerProvider = provider3;
        this.mTTCFRecordOnceProvider = provider4;
        this.navigationMetricsProvider = provider5;
        this.mNetworkConnectivityManagerProvider = provider6;
        this.mDriveModeThemeManagerProvider = provider7;
    }

    public static MembersInjector<NavigationViewController> create(Provider<NavigationDataProvider> provider, Provider<PreferredNavigationAppContentResolver> provider2, Provider<NavigationCardManager> provider3, Provider<TTCFRecordOnce> provider4, Provider<NavigationMetrics> provider5, Provider<NetworkConnectivityManager> provider6, Provider<DriveModeThemeManager> provider7) {
        return new NavigationViewController_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static void injectMDriveModeThemeManager(NavigationViewController navigationViewController, DriveModeThemeManager driveModeThemeManager) {
        navigationViewController.mDriveModeThemeManager = driveModeThemeManager;
    }

    public static void injectMNavigationCardManager(NavigationViewController navigationViewController, NavigationCardManager navigationCardManager) {
        navigationViewController.mNavigationCardManager = navigationCardManager;
    }

    public static void injectMNavigationDataProvider(NavigationViewController navigationViewController, NavigationDataProvider navigationDataProvider) {
        navigationViewController.mNavigationDataProvider = navigationDataProvider;
    }

    public static void injectMNetworkConnectivityManager(NavigationViewController navigationViewController, NetworkConnectivityManager networkConnectivityManager) {
        navigationViewController.mNetworkConnectivityManager = networkConnectivityManager;
    }

    public static void injectMPreferredNavigationAppContentResolver(NavigationViewController navigationViewController, PreferredNavigationAppContentResolver preferredNavigationAppContentResolver) {
        navigationViewController.mPreferredNavigationAppContentResolver = preferredNavigationAppContentResolver;
    }

    public static void injectMTTCFRecordOnce(NavigationViewController navigationViewController, TTCFRecordOnce tTCFRecordOnce) {
        navigationViewController.mTTCFRecordOnce = tTCFRecordOnce;
    }

    public static void injectNavigationMetrics(NavigationViewController navigationViewController, NavigationMetrics navigationMetrics) {
        navigationViewController.navigationMetrics = navigationMetrics;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(NavigationViewController navigationViewController) {
        injectMNavigationDataProvider(navigationViewController, this.mNavigationDataProvider.mo10268get());
        injectMPreferredNavigationAppContentResolver(navigationViewController, this.mPreferredNavigationAppContentResolverProvider.mo10268get());
        injectMNavigationCardManager(navigationViewController, this.mNavigationCardManagerProvider.mo10268get());
        injectMTTCFRecordOnce(navigationViewController, this.mTTCFRecordOnceProvider.mo10268get());
        injectNavigationMetrics(navigationViewController, this.navigationMetricsProvider.mo10268get());
        injectMNetworkConnectivityManager(navigationViewController, this.mNetworkConnectivityManagerProvider.mo10268get());
        injectMDriveModeThemeManager(navigationViewController, this.mDriveModeThemeManagerProvider.mo10268get());
    }
}
