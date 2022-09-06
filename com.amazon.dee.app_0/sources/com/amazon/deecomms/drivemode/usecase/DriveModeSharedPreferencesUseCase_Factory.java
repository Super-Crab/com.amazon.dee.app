package com.amazon.deecomms.drivemode.usecase;

import com.amazon.deecomms.auth.CommsSharedPreferences;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class DriveModeSharedPreferencesUseCase_Factory implements Factory<DriveModeSharedPreferencesUseCase> {
    private final Provider<CommsSharedPreferences> sharedPreferencesProvider;

    public DriveModeSharedPreferencesUseCase_Factory(Provider<CommsSharedPreferences> provider) {
        this.sharedPreferencesProvider = provider;
    }

    public static DriveModeSharedPreferencesUseCase_Factory create(Provider<CommsSharedPreferences> provider) {
        return new DriveModeSharedPreferencesUseCase_Factory(provider);
    }

    public static DriveModeSharedPreferencesUseCase newDriveModeSharedPreferencesUseCase(CommsSharedPreferences commsSharedPreferences) {
        return new DriveModeSharedPreferencesUseCase(commsSharedPreferences);
    }

    public static DriveModeSharedPreferencesUseCase provideInstance(Provider<CommsSharedPreferences> provider) {
        return new DriveModeSharedPreferencesUseCase(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DriveModeSharedPreferencesUseCase mo10268get() {
        return provideInstance(this.sharedPreferencesProvider);
    }
}
