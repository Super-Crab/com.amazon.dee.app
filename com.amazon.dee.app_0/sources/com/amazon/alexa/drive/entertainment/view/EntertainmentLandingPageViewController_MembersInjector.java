package com.amazon.alexa.drive.entertainment.view;

import com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract;
import com.amazon.alexa.drive.entertainment.repository.EntertainmentDataRepository;
import com.amazon.alexa.drive.metrics.EntertainmentMetrics;
import com.amazon.alexa.drive.metrics.TTCFRecordOnce;
import com.amazon.alexa.drive.network.NetworkConnectivityManager;
import com.amazon.alexa.drive.weblab.WeblabManager;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class EntertainmentLandingPageViewController_MembersInjector implements MembersInjector<EntertainmentLandingPageViewController> {
    private final Provider<DriveModeThemeManager> mDriveModeThemeManagerProvider;
    private final Provider<EntertainmentDataRepository> mEntertainmentDataRepositoryProvider;
    private final Provider<EntertainmentLandingPageContract.Presenter> mEntertainmentLandingPagePresenterProvider;
    private final Provider<EntertainmentMetrics> mEntertainmentMetricsProvider;
    private final Provider<NetworkConnectivityManager> mNetworkConnectivityManagerProvider;
    private final Provider<TTCFRecordOnce> mTtcfRecordOnceProvider;
    private final Provider<WeblabManager> mWeblabManagerProvider;

    public EntertainmentLandingPageViewController_MembersInjector(Provider<EntertainmentLandingPageContract.Presenter> provider, Provider<EntertainmentDataRepository> provider2, Provider<NetworkConnectivityManager> provider3, Provider<TTCFRecordOnce> provider4, Provider<EntertainmentMetrics> provider5, Provider<DriveModeThemeManager> provider6, Provider<WeblabManager> provider7) {
        this.mEntertainmentLandingPagePresenterProvider = provider;
        this.mEntertainmentDataRepositoryProvider = provider2;
        this.mNetworkConnectivityManagerProvider = provider3;
        this.mTtcfRecordOnceProvider = provider4;
        this.mEntertainmentMetricsProvider = provider5;
        this.mDriveModeThemeManagerProvider = provider6;
        this.mWeblabManagerProvider = provider7;
    }

    public static MembersInjector<EntertainmentLandingPageViewController> create(Provider<EntertainmentLandingPageContract.Presenter> provider, Provider<EntertainmentDataRepository> provider2, Provider<NetworkConnectivityManager> provider3, Provider<TTCFRecordOnce> provider4, Provider<EntertainmentMetrics> provider5, Provider<DriveModeThemeManager> provider6, Provider<WeblabManager> provider7) {
        return new EntertainmentLandingPageViewController_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static void injectMDriveModeThemeManager(EntertainmentLandingPageViewController entertainmentLandingPageViewController, DriveModeThemeManager driveModeThemeManager) {
        entertainmentLandingPageViewController.mDriveModeThemeManager = driveModeThemeManager;
    }

    public static void injectMEntertainmentDataRepository(EntertainmentLandingPageViewController entertainmentLandingPageViewController, EntertainmentDataRepository entertainmentDataRepository) {
        entertainmentLandingPageViewController.mEntertainmentDataRepository = entertainmentDataRepository;
    }

    public static void injectMEntertainmentLandingPagePresenter(EntertainmentLandingPageViewController entertainmentLandingPageViewController, EntertainmentLandingPageContract.Presenter presenter) {
        entertainmentLandingPageViewController.mEntertainmentLandingPagePresenter = presenter;
    }

    public static void injectMEntertainmentMetrics(EntertainmentLandingPageViewController entertainmentLandingPageViewController, EntertainmentMetrics entertainmentMetrics) {
        entertainmentLandingPageViewController.mEntertainmentMetrics = entertainmentMetrics;
    }

    public static void injectMNetworkConnectivityManager(EntertainmentLandingPageViewController entertainmentLandingPageViewController, NetworkConnectivityManager networkConnectivityManager) {
        entertainmentLandingPageViewController.mNetworkConnectivityManager = networkConnectivityManager;
    }

    public static void injectMTtcfRecordOnce(EntertainmentLandingPageViewController entertainmentLandingPageViewController, TTCFRecordOnce tTCFRecordOnce) {
        entertainmentLandingPageViewController.mTtcfRecordOnce = tTCFRecordOnce;
    }

    public static void injectMWeblabManager(EntertainmentLandingPageViewController entertainmentLandingPageViewController, WeblabManager weblabManager) {
        entertainmentLandingPageViewController.mWeblabManager = weblabManager;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(EntertainmentLandingPageViewController entertainmentLandingPageViewController) {
        injectMEntertainmentLandingPagePresenter(entertainmentLandingPageViewController, this.mEntertainmentLandingPagePresenterProvider.mo10268get());
        injectMEntertainmentDataRepository(entertainmentLandingPageViewController, this.mEntertainmentDataRepositoryProvider.mo10268get());
        injectMNetworkConnectivityManager(entertainmentLandingPageViewController, this.mNetworkConnectivityManagerProvider.mo10268get());
        injectMTtcfRecordOnce(entertainmentLandingPageViewController, this.mTtcfRecordOnceProvider.mo10268get());
        injectMEntertainmentMetrics(entertainmentLandingPageViewController, this.mEntertainmentMetricsProvider.mo10268get());
        injectMDriveModeThemeManager(entertainmentLandingPageViewController, this.mDriveModeThemeManagerProvider.mo10268get());
        injectMWeblabManager(entertainmentLandingPageViewController, this.mWeblabManagerProvider.mo10268get());
    }
}
