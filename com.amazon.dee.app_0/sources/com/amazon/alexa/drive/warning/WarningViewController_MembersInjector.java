package com.amazon.alexa.drive.warning;

import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class WarningViewController_MembersInjector implements MembersInjector<WarningViewController> {
    private final Provider<DriveModeThemeManager> mDriveModeThemeManagerProvider;

    public WarningViewController_MembersInjector(Provider<DriveModeThemeManager> provider) {
        this.mDriveModeThemeManagerProvider = provider;
    }

    public static MembersInjector<WarningViewController> create(Provider<DriveModeThemeManager> provider) {
        return new WarningViewController_MembersInjector(provider);
    }

    public static void injectMDriveModeThemeManager(WarningViewController warningViewController, DriveModeThemeManager driveModeThemeManager) {
        warningViewController.mDriveModeThemeManager = driveModeThemeManager;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(WarningViewController warningViewController) {
        injectMDriveModeThemeManager(warningViewController, this.mDriveModeThemeManagerProvider.mo10268get());
    }
}
