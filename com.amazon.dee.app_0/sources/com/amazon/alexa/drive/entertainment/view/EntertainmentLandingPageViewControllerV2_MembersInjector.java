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
public final class EntertainmentLandingPageViewControllerV2_MembersInjector implements MembersInjector<EntertainmentLandingPageViewControllerV2> {
    private final Provider<DriveModeThemeManager> mDriveModeThemeManagerProvider;
    private final Provider<EntertainmentDataRepository> mEntertainmentDataRepositoryProvider;
    private final Provider<EntertainmentLandingPageContract.Presenter> mEntertainmentLandingPagePresenterProvider;
    private final Provider<EntertainmentMetrics> mEntertainmentMetricsProvider;
    private final Provider<NetworkConnectivityManager> mNetworkConnectivityManagerProvider;
    private final Provider<TTCFRecordOnce> mTtcfRecordOnceProvider;
    private final Provider<WeblabManager> mWeblabManagerProvider;

    public EntertainmentLandingPageViewControllerV2_MembersInjector(Provider<EntertainmentLandingPageContract.Presenter> provider, Provider<EntertainmentDataRepository> provider2, Provider<NetworkConnectivityManager> provider3, Provider<TTCFRecordOnce> provider4, Provider<EntertainmentMetrics> provider5, Provider<DriveModeThemeManager> provider6, Provider<WeblabManager> provider7) {
        this.mEntertainmentLandingPagePresenterProvider = provider;
        this.mEntertainmentDataRepositoryProvider = provider2;
        this.mNetworkConnectivityManagerProvider = provider3;
        this.mTtcfRecordOnceProvider = provider4;
        this.mEntertainmentMetricsProvider = provider5;
        this.mDriveModeThemeManagerProvider = provider6;
        this.mWeblabManagerProvider = provider7;
    }

    public static MembersInjector<EntertainmentLandingPageViewControllerV2> create(Provider<EntertainmentLandingPageContract.Presenter> provider, Provider<EntertainmentDataRepository> provider2, Provider<NetworkConnectivityManager> provider3, Provider<TTCFRecordOnce> provider4, Provider<EntertainmentMetrics> provider5, Provider<DriveModeThemeManager> provider6, Provider<WeblabManager> provider7) {
        return new EntertainmentLandingPageViewControllerV2_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static void injectMDriveModeThemeManager(EntertainmentLandingPageViewControllerV2 entertainmentLandingPageViewControllerV2, DriveModeThemeManager driveModeThemeManager) {
        entertainmentLandingPageViewControllerV2.mDriveModeThemeManager = driveModeThemeManager;
    }

    public static void injectMEntertainmentDataRepository(EntertainmentLandingPageViewControllerV2 entertainmentLandingPageViewControllerV2, EntertainmentDataRepository entertainmentDataRepository) {
        entertainmentLandingPageViewControllerV2.mEntertainmentDataRepository = entertainmentDataRepository;
    }

    public static void injectMEntertainmentLandingPagePresenter(EntertainmentLandingPageViewControllerV2 entertainmentLandingPageViewControllerV2, EntertainmentLandingPageContract.Presenter presenter) {
        entertainmentLandingPageViewControllerV2.mEntertainmentLandingPagePresenter = presenter;
    }

    public static void injectMEntertainmentMetrics(EntertainmentLandingPageViewControllerV2 entertainmentLandingPageViewControllerV2, EntertainmentMetrics entertainmentMetrics) {
        entertainmentLandingPageViewControllerV2.mEntertainmentMetrics = entertainmentMetrics;
    }

    public static void injectMNetworkConnectivityManager(EntertainmentLandingPageViewControllerV2 entertainmentLandingPageViewControllerV2, NetworkConnectivityManager networkConnectivityManager) {
        entertainmentLandingPageViewControllerV2.mNetworkConnectivityManager = networkConnectivityManager;
    }

    public static void injectMTtcfRecordOnce(EntertainmentLandingPageViewControllerV2 entertainmentLandingPageViewControllerV2, TTCFRecordOnce tTCFRecordOnce) {
        entertainmentLandingPageViewControllerV2.mTtcfRecordOnce = tTCFRecordOnce;
    }

    public static void injectMWeblabManager(EntertainmentLandingPageViewControllerV2 entertainmentLandingPageViewControllerV2, WeblabManager weblabManager) {
        entertainmentLandingPageViewControllerV2.mWeblabManager = weblabManager;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(EntertainmentLandingPageViewControllerV2 entertainmentLandingPageViewControllerV2) {
        injectMEntertainmentLandingPagePresenter(entertainmentLandingPageViewControllerV2, this.mEntertainmentLandingPagePresenterProvider.mo10268get());
        injectMEntertainmentDataRepository(entertainmentLandingPageViewControllerV2, this.mEntertainmentDataRepositoryProvider.mo10268get());
        injectMNetworkConnectivityManager(entertainmentLandingPageViewControllerV2, this.mNetworkConnectivityManagerProvider.mo10268get());
        injectMTtcfRecordOnce(entertainmentLandingPageViewControllerV2, this.mTtcfRecordOnceProvider.mo10268get());
        injectMEntertainmentMetrics(entertainmentLandingPageViewControllerV2, this.mEntertainmentMetricsProvider.mo10268get());
        injectMDriveModeThemeManager(entertainmentLandingPageViewControllerV2, this.mDriveModeThemeManagerProvider.mo10268get());
        injectMWeblabManager(entertainmentLandingPageViewControllerV2, this.mWeblabManagerProvider.mo10268get());
    }
}
