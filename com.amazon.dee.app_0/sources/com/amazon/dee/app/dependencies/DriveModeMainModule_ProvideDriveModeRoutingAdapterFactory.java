package com.amazon.dee.app.dependencies;

import com.amazon.alexa.drivemode.api.DriveModeMainActivityCompanion;
import com.amazon.alexa.routing.api.RoutingAdapter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class DriveModeMainModule_ProvideDriveModeRoutingAdapterFactory implements Factory<RoutingAdapter> {
    private final Provider<DriveModeMainActivityCompanion> companionProvider;
    private final DriveModeMainModule module;

    public DriveModeMainModule_ProvideDriveModeRoutingAdapterFactory(DriveModeMainModule driveModeMainModule, Provider<DriveModeMainActivityCompanion> provider) {
        this.module = driveModeMainModule;
        this.companionProvider = provider;
    }

    public static DriveModeMainModule_ProvideDriveModeRoutingAdapterFactory create(DriveModeMainModule driveModeMainModule, Provider<DriveModeMainActivityCompanion> provider) {
        return new DriveModeMainModule_ProvideDriveModeRoutingAdapterFactory(driveModeMainModule, provider);
    }

    public static RoutingAdapter provideInstance(DriveModeMainModule driveModeMainModule, Provider<DriveModeMainActivityCompanion> provider) {
        return proxyProvideDriveModeRoutingAdapter(driveModeMainModule, provider.mo10268get());
    }

    public static RoutingAdapter proxyProvideDriveModeRoutingAdapter(DriveModeMainModule driveModeMainModule, DriveModeMainActivityCompanion driveModeMainActivityCompanion) {
        return (RoutingAdapter) Preconditions.checkNotNull(driveModeMainModule.provideDriveModeRoutingAdapter(driveModeMainActivityCompanion), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RoutingAdapter mo10268get() {
        return provideInstance(this.module, this.companionProvider);
    }
}
