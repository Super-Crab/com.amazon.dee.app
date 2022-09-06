package com.amazon.alexa.voice.tta.dependencies;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class DependenciesModule_ProvidesDeviceInformationProviderFactory implements Factory<DeviceInformationProvider> {
    private final DependenciesModule module;

    public DependenciesModule_ProvidesDeviceInformationProviderFactory(DependenciesModule dependenciesModule) {
        this.module = dependenciesModule;
    }

    public static DependenciesModule_ProvidesDeviceInformationProviderFactory create(DependenciesModule dependenciesModule) {
        return new DependenciesModule_ProvidesDeviceInformationProviderFactory(dependenciesModule);
    }

    public static DeviceInformationProvider provideInstance(DependenciesModule dependenciesModule) {
        return proxyProvidesDeviceInformationProvider(dependenciesModule);
    }

    public static DeviceInformationProvider proxyProvidesDeviceInformationProvider(DependenciesModule dependenciesModule) {
        return (DeviceInformationProvider) Preconditions.checkNotNull(dependenciesModule.providesDeviceInformationProvider(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DeviceInformationProvider mo10268get() {
        return provideInstance(this.module);
    }
}
