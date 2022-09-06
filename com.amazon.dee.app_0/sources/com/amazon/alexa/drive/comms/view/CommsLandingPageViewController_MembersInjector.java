package com.amazon.alexa.drive.comms.view;

import com.amazon.alexa.drive.comms.contract.CommsLandingPageContract;
import com.amazon.alexa.drive.network.NetworkConnectivityManager;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class CommsLandingPageViewController_MembersInjector implements MembersInjector<CommsLandingPageViewController> {
    private final Provider<CommsLandingPageContract.Presenter> mCommsLandingPagePresenterProvider;
    private final Provider<DriveModeThemeManager> mDriveModeThemeManagerProvider;
    private final Provider<NetworkConnectivityManager> mNetworkConnectivityManagerProvider;

    public CommsLandingPageViewController_MembersInjector(Provider<CommsLandingPageContract.Presenter> provider, Provider<DriveModeThemeManager> provider2, Provider<NetworkConnectivityManager> provider3) {
        this.mCommsLandingPagePresenterProvider = provider;
        this.mDriveModeThemeManagerProvider = provider2;
        this.mNetworkConnectivityManagerProvider = provider3;
    }

    public static MembersInjector<CommsLandingPageViewController> create(Provider<CommsLandingPageContract.Presenter> provider, Provider<DriveModeThemeManager> provider2, Provider<NetworkConnectivityManager> provider3) {
        return new CommsLandingPageViewController_MembersInjector(provider, provider2, provider3);
    }

    public static void injectMCommsLandingPagePresenter(CommsLandingPageViewController commsLandingPageViewController, CommsLandingPageContract.Presenter presenter) {
        commsLandingPageViewController.mCommsLandingPagePresenter = presenter;
    }

    public static void injectMDriveModeThemeManager(CommsLandingPageViewController commsLandingPageViewController, DriveModeThemeManager driveModeThemeManager) {
        commsLandingPageViewController.mDriveModeThemeManager = driveModeThemeManager;
    }

    public static void injectMNetworkConnectivityManager(CommsLandingPageViewController commsLandingPageViewController, NetworkConnectivityManager networkConnectivityManager) {
        commsLandingPageViewController.mNetworkConnectivityManager = networkConnectivityManager;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(CommsLandingPageViewController commsLandingPageViewController) {
        injectMCommsLandingPagePresenter(commsLandingPageViewController, this.mCommsLandingPagePresenterProvider.mo10268get());
        injectMDriveModeThemeManager(commsLandingPageViewController, this.mDriveModeThemeManagerProvider.mo10268get());
        injectMNetworkConnectivityManager(commsLandingPageViewController, this.mNetworkConnectivityManagerProvider.mo10268get());
    }
}
