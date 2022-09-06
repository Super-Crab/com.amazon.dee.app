package com.amazon.alexa.drive.main.v2;

import com.amazon.alexa.drive.main.DriveModeMainContract;
import com.amazon.alexa.drive.metrics.DriveModeMetricsHelper;
import com.amazon.alexa.drive.network.NetworkConnectivityManager;
import com.amazon.alexa.drive.orientation.LandscapeErrorProvider;
import com.amazon.alexa.drive.weblab.WeblabManager;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.mode.ModeService;
import com.amazon.alexa.mode.metrics.DriveModeMetrics;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class DriveModeMainActivityViewModel_MembersInjector implements MembersInjector<DriveModeMainActivityViewModel> {
    private final Provider<AMPDInformationProvider> mAMPDInformationProvider;
    private final Provider<DriveModeMetricsHelper> mDriveModeMetricsHelperProvider;
    private final Provider<DriveModeMetrics> mDriveModeMetricsProvider;
    private final Provider<DriveModeThemeManager> mDriveModeThemeManagerProvider;
    private final Provider<EventBus> mEventBusProvider;
    private final Provider<LandscapeErrorProvider> mLandscapeErrorProvider;
    private final Provider<NetworkConnectivityManager> mNetworkConnectivityManagerProvider;
    private final Provider<WeblabManager> mWeblabManagerProvider;
    private final Provider<ModeService> modeServiceProvider;
    private final Provider<DriveModeMainContract.Presenter> presenterProvider;

    public DriveModeMainActivityViewModel_MembersInjector(Provider<DriveModeMainContract.Presenter> provider, Provider<DriveModeThemeManager> provider2, Provider<DriveModeMetrics> provider3, Provider<DriveModeMetricsHelper> provider4, Provider<NetworkConnectivityManager> provider5, Provider<AMPDInformationProvider> provider6, Provider<EventBus> provider7, Provider<ModeService> provider8, Provider<WeblabManager> provider9, Provider<LandscapeErrorProvider> provider10) {
        this.presenterProvider = provider;
        this.mDriveModeThemeManagerProvider = provider2;
        this.mDriveModeMetricsProvider = provider3;
        this.mDriveModeMetricsHelperProvider = provider4;
        this.mNetworkConnectivityManagerProvider = provider5;
        this.mAMPDInformationProvider = provider6;
        this.mEventBusProvider = provider7;
        this.modeServiceProvider = provider8;
        this.mWeblabManagerProvider = provider9;
        this.mLandscapeErrorProvider = provider10;
    }

    public static MembersInjector<DriveModeMainActivityViewModel> create(Provider<DriveModeMainContract.Presenter> provider, Provider<DriveModeThemeManager> provider2, Provider<DriveModeMetrics> provider3, Provider<DriveModeMetricsHelper> provider4, Provider<NetworkConnectivityManager> provider5, Provider<AMPDInformationProvider> provider6, Provider<EventBus> provider7, Provider<ModeService> provider8, Provider<WeblabManager> provider9, Provider<LandscapeErrorProvider> provider10) {
        return new DriveModeMainActivityViewModel_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10);
    }

    public static void injectMAMPDInformationProvider(DriveModeMainActivityViewModel driveModeMainActivityViewModel, AMPDInformationProvider aMPDInformationProvider) {
        driveModeMainActivityViewModel.mAMPDInformationProvider = aMPDInformationProvider;
    }

    public static void injectMDriveModeMetrics(DriveModeMainActivityViewModel driveModeMainActivityViewModel, DriveModeMetrics driveModeMetrics) {
        driveModeMainActivityViewModel.mDriveModeMetrics = driveModeMetrics;
    }

    public static void injectMDriveModeMetricsHelper(DriveModeMainActivityViewModel driveModeMainActivityViewModel, DriveModeMetricsHelper driveModeMetricsHelper) {
        driveModeMainActivityViewModel.mDriveModeMetricsHelper = driveModeMetricsHelper;
    }

    public static void injectMDriveModeThemeManager(DriveModeMainActivityViewModel driveModeMainActivityViewModel, DriveModeThemeManager driveModeThemeManager) {
        driveModeMainActivityViewModel.mDriveModeThemeManager = driveModeThemeManager;
    }

    public static void injectMEventBus(DriveModeMainActivityViewModel driveModeMainActivityViewModel, EventBus eventBus) {
        driveModeMainActivityViewModel.mEventBus = eventBus;
    }

    public static void injectMLandscapeErrorProvider(DriveModeMainActivityViewModel driveModeMainActivityViewModel, LandscapeErrorProvider landscapeErrorProvider) {
        driveModeMainActivityViewModel.mLandscapeErrorProvider = landscapeErrorProvider;
    }

    public static void injectMNetworkConnectivityManager(DriveModeMainActivityViewModel driveModeMainActivityViewModel, NetworkConnectivityManager networkConnectivityManager) {
        driveModeMainActivityViewModel.mNetworkConnectivityManager = networkConnectivityManager;
    }

    public static void injectMWeblabManager(DriveModeMainActivityViewModel driveModeMainActivityViewModel, WeblabManager weblabManager) {
        driveModeMainActivityViewModel.mWeblabManager = weblabManager;
    }

    public static void injectModeService(DriveModeMainActivityViewModel driveModeMainActivityViewModel, ModeService modeService) {
        driveModeMainActivityViewModel.modeService = modeService;
    }

    public static void injectPresenter(DriveModeMainActivityViewModel driveModeMainActivityViewModel, DriveModeMainContract.Presenter presenter) {
        driveModeMainActivityViewModel.presenter = presenter;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(DriveModeMainActivityViewModel driveModeMainActivityViewModel) {
        injectPresenter(driveModeMainActivityViewModel, this.presenterProvider.mo10268get());
        injectMDriveModeThemeManager(driveModeMainActivityViewModel, this.mDriveModeThemeManagerProvider.mo10268get());
        injectMDriveModeMetrics(driveModeMainActivityViewModel, this.mDriveModeMetricsProvider.mo10268get());
        injectMDriveModeMetricsHelper(driveModeMainActivityViewModel, this.mDriveModeMetricsHelperProvider.mo10268get());
        injectMNetworkConnectivityManager(driveModeMainActivityViewModel, this.mNetworkConnectivityManagerProvider.mo10268get());
        injectMAMPDInformationProvider(driveModeMainActivityViewModel, this.mAMPDInformationProvider.mo10268get());
        injectMEventBus(driveModeMainActivityViewModel, this.mEventBusProvider.mo10268get());
        injectModeService(driveModeMainActivityViewModel, this.modeServiceProvider.mo10268get());
        injectMWeblabManager(driveModeMainActivityViewModel, this.mWeblabManagerProvider.mo10268get());
        injectMLandscapeErrorProvider(driveModeMainActivityViewModel, this.mLandscapeErrorProvider.mo10268get());
    }
}
