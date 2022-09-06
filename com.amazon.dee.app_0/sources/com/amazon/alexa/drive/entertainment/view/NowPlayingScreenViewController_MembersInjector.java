package com.amazon.alexa.drive.entertainment.view;

import com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract;
import com.amazon.alexa.drive.metrics.EntertainmentMetrics;
import com.amazon.alexa.drive.metrics.TTCFRecordOnce;
import com.amazon.alexa.drive.network.NetworkConnectivityManager;
import com.amazon.alexa.drive.weblab.WeblabManager;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class NowPlayingScreenViewController_MembersInjector implements MembersInjector<NowPlayingScreenViewController> {
    private final Provider<DriveModeThemeManager> mDriveModeThemeManagerProvider;
    private final Provider<EntertainmentMetrics> mEntertainmentMetricsProvider;
    private final Provider<NetworkConnectivityManager> mNetworkConnectivityManagerProvider;
    private final Provider<NowPlayingScreenContract.Presenter> mNowPlayingScreenPresenterProvider;
    private final Provider<TTCFRecordOnce> mTTCFRecordOnceProvider;
    private final Provider<WeblabManager> mWeblabManagerProvider;

    public NowPlayingScreenViewController_MembersInjector(Provider<NowPlayingScreenContract.Presenter> provider, Provider<NetworkConnectivityManager> provider2, Provider<TTCFRecordOnce> provider3, Provider<EntertainmentMetrics> provider4, Provider<DriveModeThemeManager> provider5, Provider<WeblabManager> provider6) {
        this.mNowPlayingScreenPresenterProvider = provider;
        this.mNetworkConnectivityManagerProvider = provider2;
        this.mTTCFRecordOnceProvider = provider3;
        this.mEntertainmentMetricsProvider = provider4;
        this.mDriveModeThemeManagerProvider = provider5;
        this.mWeblabManagerProvider = provider6;
    }

    public static MembersInjector<NowPlayingScreenViewController> create(Provider<NowPlayingScreenContract.Presenter> provider, Provider<NetworkConnectivityManager> provider2, Provider<TTCFRecordOnce> provider3, Provider<EntertainmentMetrics> provider4, Provider<DriveModeThemeManager> provider5, Provider<WeblabManager> provider6) {
        return new NowPlayingScreenViewController_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static void injectMDriveModeThemeManager(NowPlayingScreenViewController nowPlayingScreenViewController, DriveModeThemeManager driveModeThemeManager) {
        nowPlayingScreenViewController.mDriveModeThemeManager = driveModeThemeManager;
    }

    public static void injectMEntertainmentMetrics(NowPlayingScreenViewController nowPlayingScreenViewController, EntertainmentMetrics entertainmentMetrics) {
        nowPlayingScreenViewController.mEntertainmentMetrics = entertainmentMetrics;
    }

    public static void injectMNetworkConnectivityManager(NowPlayingScreenViewController nowPlayingScreenViewController, NetworkConnectivityManager networkConnectivityManager) {
        nowPlayingScreenViewController.mNetworkConnectivityManager = networkConnectivityManager;
    }

    public static void injectMNowPlayingScreenPresenter(NowPlayingScreenViewController nowPlayingScreenViewController, NowPlayingScreenContract.Presenter presenter) {
        nowPlayingScreenViewController.mNowPlayingScreenPresenter = presenter;
    }

    public static void injectMTTCFRecordOnce(NowPlayingScreenViewController nowPlayingScreenViewController, TTCFRecordOnce tTCFRecordOnce) {
        nowPlayingScreenViewController.mTTCFRecordOnce = tTCFRecordOnce;
    }

    public static void injectMWeblabManager(NowPlayingScreenViewController nowPlayingScreenViewController, WeblabManager weblabManager) {
        nowPlayingScreenViewController.mWeblabManager = weblabManager;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(NowPlayingScreenViewController nowPlayingScreenViewController) {
        injectMNowPlayingScreenPresenter(nowPlayingScreenViewController, this.mNowPlayingScreenPresenterProvider.mo10268get());
        injectMNetworkConnectivityManager(nowPlayingScreenViewController, this.mNetworkConnectivityManagerProvider.mo10268get());
        injectMTTCFRecordOnce(nowPlayingScreenViewController, this.mTTCFRecordOnceProvider.mo10268get());
        injectMEntertainmentMetrics(nowPlayingScreenViewController, this.mEntertainmentMetricsProvider.mo10268get());
        injectMDriveModeThemeManager(nowPlayingScreenViewController, this.mDriveModeThemeManagerProvider.mo10268get());
        injectMWeblabManager(nowPlayingScreenViewController, this.mWeblabManagerProvider.mo10268get());
    }
}
