package com.amazon.dee.app.dependencies;

import com.amazon.alexa.drivemode.api.DriveModeService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class DriveModeApplicationModule_ProvideDriveModeServiceFactory implements Factory<DriveModeService> {
    private final DriveModeApplicationModule module;

    public DriveModeApplicationModule_ProvideDriveModeServiceFactory(DriveModeApplicationModule driveModeApplicationModule) {
        this.module = driveModeApplicationModule;
    }

    public static DriveModeApplicationModule_ProvideDriveModeServiceFactory create(DriveModeApplicationModule driveModeApplicationModule) {
        return new DriveModeApplicationModule_ProvideDriveModeServiceFactory(driveModeApplicationModule);
    }

    public static DriveModeService provideInstance(DriveModeApplicationModule driveModeApplicationModule) {
        return proxyProvideDriveModeService(driveModeApplicationModule);
    }

    public static DriveModeService proxyProvideDriveModeService(DriveModeApplicationModule driveModeApplicationModule) {
        return (DriveModeService) Preconditions.checkNotNull(driveModeApplicationModule.provideDriveModeService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DriveModeService mo10268get() {
        return provideInstance(this.module);
    }
}
