package com.amazon.alexa.mode.dependencies;

import com.amazon.alexa.mode.util.DriveModePreferences;
import com.amazon.alexa.mode.util.PrefsDialogHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class ModeModule_ProvideDriveModePreferencesFactory implements Factory<DriveModePreferences> {
    private final ModeModule module;
    private final Provider<PrefsDialogHelper> prefsDialogHelperProvider;

    public ModeModule_ProvideDriveModePreferencesFactory(ModeModule modeModule, Provider<PrefsDialogHelper> provider) {
        this.module = modeModule;
        this.prefsDialogHelperProvider = provider;
    }

    public static ModeModule_ProvideDriveModePreferencesFactory create(ModeModule modeModule, Provider<PrefsDialogHelper> provider) {
        return new ModeModule_ProvideDriveModePreferencesFactory(modeModule, provider);
    }

    public static DriveModePreferences provideInstance(ModeModule modeModule, Provider<PrefsDialogHelper> provider) {
        return proxyProvideDriveModePreferences(modeModule, provider.mo10268get());
    }

    public static DriveModePreferences proxyProvideDriveModePreferences(ModeModule modeModule, PrefsDialogHelper prefsDialogHelper) {
        return (DriveModePreferences) Preconditions.checkNotNull(modeModule.provideDriveModePreferences(prefsDialogHelper), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DriveModePreferences mo10268get() {
        return provideInstance(this.module, this.prefsDialogHelperProvider);
    }
}
