package com.amazon.alexa.fitness.dagger;

import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.routing.api.RoutingService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class StaticReleasePackageModule_ProvideRoutingServiceFactory implements Factory<RoutingService> {
    private final Provider<ComponentRegistry> componentRegistryProvider;
    private final StaticReleasePackageModule module;

    public StaticReleasePackageModule_ProvideRoutingServiceFactory(StaticReleasePackageModule staticReleasePackageModule, Provider<ComponentRegistry> provider) {
        this.module = staticReleasePackageModule;
        this.componentRegistryProvider = provider;
    }

    public static StaticReleasePackageModule_ProvideRoutingServiceFactory create(StaticReleasePackageModule staticReleasePackageModule, Provider<ComponentRegistry> provider) {
        return new StaticReleasePackageModule_ProvideRoutingServiceFactory(staticReleasePackageModule, provider);
    }

    public static RoutingService provideInstance(StaticReleasePackageModule staticReleasePackageModule, Provider<ComponentRegistry> provider) {
        return proxyProvideRoutingService(staticReleasePackageModule, provider.mo10268get());
    }

    public static RoutingService proxyProvideRoutingService(StaticReleasePackageModule staticReleasePackageModule, ComponentRegistry componentRegistry) {
        return (RoutingService) Preconditions.checkNotNull(staticReleasePackageModule.provideRoutingService(componentRegistry), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RoutingService mo10268get() {
        return provideInstance(this.module, this.componentRegistryProvider);
    }
}
