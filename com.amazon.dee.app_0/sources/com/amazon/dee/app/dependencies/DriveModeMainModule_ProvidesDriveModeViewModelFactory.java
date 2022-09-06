package com.amazon.dee.app.dependencies;

import com.amazon.alexa.drivemode.api.DriveModeMainActivityCompanion;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class DriveModeMainModule_ProvidesDriveModeViewModelFactory implements Factory<DriveModeMainActivityCompanion.ViewModel> {
    private final Provider<DriveModeMainActivityCompanion> companionProvider;
    private final DriveModeMainModule module;

    public DriveModeMainModule_ProvidesDriveModeViewModelFactory(DriveModeMainModule driveModeMainModule, Provider<DriveModeMainActivityCompanion> provider) {
        this.module = driveModeMainModule;
        this.companionProvider = provider;
    }

    public static DriveModeMainModule_ProvidesDriveModeViewModelFactory create(DriveModeMainModule driveModeMainModule, Provider<DriveModeMainActivityCompanion> provider) {
        return new DriveModeMainModule_ProvidesDriveModeViewModelFactory(driveModeMainModule, provider);
    }

    public static DriveModeMainActivityCompanion.ViewModel provideInstance(DriveModeMainModule driveModeMainModule, Provider<DriveModeMainActivityCompanion> provider) {
        return proxyProvidesDriveModeViewModel(driveModeMainModule, provider.mo10268get());
    }

    public static DriveModeMainActivityCompanion.ViewModel proxyProvidesDriveModeViewModel(DriveModeMainModule driveModeMainModule, DriveModeMainActivityCompanion driveModeMainActivityCompanion) {
        return (DriveModeMainActivityCompanion.ViewModel) Preconditions.checkNotNull(driveModeMainModule.providesDriveModeViewModel(driveModeMainActivityCompanion), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DriveModeMainActivityCompanion.ViewModel mo10268get() {
        return provideInstance(this.module, this.companionProvider);
    }
}
