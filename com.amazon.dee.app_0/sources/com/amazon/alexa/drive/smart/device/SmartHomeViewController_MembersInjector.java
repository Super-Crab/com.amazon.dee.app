package com.amazon.alexa.drive.smart.device;

import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class SmartHomeViewController_MembersInjector implements MembersInjector<SmartHomeViewController> {
    private final Provider<DriveModeThemeManager> driveModeThemeManagerProvider;
    private final Provider<SmartDevicePresenter> smartDevicePresenterProvider;

    public SmartHomeViewController_MembersInjector(Provider<DriveModeThemeManager> provider, Provider<SmartDevicePresenter> provider2) {
        this.driveModeThemeManagerProvider = provider;
        this.smartDevicePresenterProvider = provider2;
    }

    public static MembersInjector<SmartHomeViewController> create(Provider<DriveModeThemeManager> provider, Provider<SmartDevicePresenter> provider2) {
        return new SmartHomeViewController_MembersInjector(provider, provider2);
    }

    public static void injectDriveModeThemeManager(SmartHomeViewController smartHomeViewController, DriveModeThemeManager driveModeThemeManager) {
        smartHomeViewController.driveModeThemeManager = driveModeThemeManager;
    }

    public static void injectSmartDevicePresenter(SmartHomeViewController smartHomeViewController, SmartDevicePresenter smartDevicePresenter) {
        smartHomeViewController.smartDevicePresenter = smartDevicePresenter;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(SmartHomeViewController smartHomeViewController) {
        injectDriveModeThemeManager(smartHomeViewController, this.driveModeThemeManagerProvider.mo10268get());
        injectSmartDevicePresenter(smartHomeViewController, this.smartDevicePresenterProvider.mo10268get());
    }
}
