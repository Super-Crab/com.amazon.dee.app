package com.amazon.alexa.fitness.dagger;

import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class StaticReleasePackageModule_ProvideEnvironmentServiceFactory implements Factory<EnvironmentService> {
    private final Provider<ComponentRegistry> componentRegistryProvider;
    private final StaticReleasePackageModule module;

    public StaticReleasePackageModule_ProvideEnvironmentServiceFactory(StaticReleasePackageModule staticReleasePackageModule, Provider<ComponentRegistry> provider) {
        this.module = staticReleasePackageModule;
        this.componentRegistryProvider = provider;
    }

    public static StaticReleasePackageModule_ProvideEnvironmentServiceFactory create(StaticReleasePackageModule staticReleasePackageModule, Provider<ComponentRegistry> provider) {
        return new StaticReleasePackageModule_ProvideEnvironmentServiceFactory(staticReleasePackageModule, provider);
    }

    public static EnvironmentService provideInstance(StaticReleasePackageModule staticReleasePackageModule, Provider<ComponentRegistry> provider) {
        return proxyProvideEnvironmentService(staticReleasePackageModule, provider.mo10268get());
    }

    public static EnvironmentService proxyProvideEnvironmentService(StaticReleasePackageModule staticReleasePackageModule, ComponentRegistry componentRegistry) {
        return (EnvironmentService) Preconditions.checkNotNull(staticReleasePackageModule.provideEnvironmentService(componentRegistry), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EnvironmentService mo10268get() {
        return provideInstance(this.module, this.componentRegistryProvider);
    }
}
