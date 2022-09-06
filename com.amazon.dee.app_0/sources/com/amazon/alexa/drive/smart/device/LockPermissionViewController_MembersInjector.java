package com.amazon.alexa.drive.smart.device;

import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class LockPermissionViewController_MembersInjector implements MembersInjector<LockPermissionViewController> {
    private final Provider<DriveModeThemeManager> driveModeThemeManagerProvider;

    public LockPermissionViewController_MembersInjector(Provider<DriveModeThemeManager> provider) {
        this.driveModeThemeManagerProvider = provider;
    }

    public static MembersInjector<LockPermissionViewController> create(Provider<DriveModeThemeManager> provider) {
        return new LockPermissionViewController_MembersInjector(provider);
    }

    public static void injectDriveModeThemeManager(LockPermissionViewController lockPermissionViewController, DriveModeThemeManager driveModeThemeManager) {
        lockPermissionViewController.driveModeThemeManager = driveModeThemeManager;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(LockPermissionViewController lockPermissionViewController) {
        injectDriveModeThemeManager(lockPermissionViewController, this.driveModeThemeManagerProvider.mo10268get());
    }
}
