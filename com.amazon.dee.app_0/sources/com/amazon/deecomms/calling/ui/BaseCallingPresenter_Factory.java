package com.amazon.deecomms.calling.ui;

import com.amazon.deecomms.drivemode.usecase.DriveModeSharedPreferencesUseCase;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class BaseCallingPresenter_Factory implements Factory<BaseCallingPresenter> {
    private final Provider<DriveModeSharedPreferencesUseCase> driveModeSharedPreferencesUseCaseProvider;

    public BaseCallingPresenter_Factory(Provider<DriveModeSharedPreferencesUseCase> provider) {
        this.driveModeSharedPreferencesUseCaseProvider = provider;
    }

    public static BaseCallingPresenter_Factory create(Provider<DriveModeSharedPreferencesUseCase> provider) {
        return new BaseCallingPresenter_Factory(provider);
    }

    public static BaseCallingPresenter newBaseCallingPresenter(DriveModeSharedPreferencesUseCase driveModeSharedPreferencesUseCase) {
        return new BaseCallingPresenter(driveModeSharedPreferencesUseCase);
    }

    public static BaseCallingPresenter provideInstance(Provider<DriveModeSharedPreferencesUseCase> provider) {
        return new BaseCallingPresenter(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public BaseCallingPresenter mo10268get() {
        return provideInstance(this.driveModeSharedPreferencesUseCaseProvider);
    }
}
