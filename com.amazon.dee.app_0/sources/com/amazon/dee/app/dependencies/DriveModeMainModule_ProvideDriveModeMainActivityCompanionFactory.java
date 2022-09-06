package com.amazon.dee.app.dependencies;

import com.amazon.alexa.drivemode.api.DriveModeMainActivityCompanion;
import com.amazon.alexa.drivemode.api.DriveModeService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class DriveModeMainModule_ProvideDriveModeMainActivityCompanionFactory implements Factory<DriveModeMainActivityCompanion> {
    private final Provider<DriveModeService> driveModeServiceProvider;
    private final DriveModeMainModule module;

    public DriveModeMainModule_ProvideDriveModeMainActivityCompanionFactory(DriveModeMainModule driveModeMainModule, Provider<DriveModeService> provider) {
        this.module = driveModeMainModule;
        this.driveModeServiceProvider = provider;
    }

    public static DriveModeMainModule_ProvideDriveModeMainActivityCompanionFactory create(DriveModeMainModule driveModeMainModule, Provider<DriveModeService> provider) {
        return new DriveModeMainModule_ProvideDriveModeMainActivityCompanionFactory(driveModeMainModule, provider);
    }

    public static DriveModeMainActivityCompanion provideInstance(DriveModeMainModule driveModeMainModule, Provider<DriveModeService> provider) {
        return proxyProvideDriveModeMainActivityCompanion(driveModeMainModule, provider.mo10268get());
    }

    public static DriveModeMainActivityCompanion proxyProvideDriveModeMainActivityCompanion(DriveModeMainModule driveModeMainModule, DriveModeService driveModeService) {
        return (DriveModeMainActivityCompanion) Preconditions.checkNotNull(driveModeMainModule.provideDriveModeMainActivityCompanion(driveModeService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DriveModeMainActivityCompanion mo10268get() {
        return provideInstance(this.module, this.driveModeServiceProvider);
    }
}
