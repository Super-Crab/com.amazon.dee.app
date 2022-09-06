package com.amazon.alexa.fitness.dagger;

import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.routing.api.RoutingRegistry;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class StaticReleasePackageModule_ProvideRoutingRegistryFactory implements Factory<RoutingRegistry> {
    private final Provider<ComponentRegistry> componentRegistryProvider;
    private final StaticReleasePackageModule module;

    public StaticReleasePackageModule_ProvideRoutingRegistryFactory(StaticReleasePackageModule staticReleasePackageModule, Provider<ComponentRegistry> provider) {
        this.module = staticReleasePackageModule;
        this.componentRegistryProvider = provider;
    }

    public static StaticReleasePackageModule_ProvideRoutingRegistryFactory create(StaticReleasePackageModule staticReleasePackageModule, Provider<ComponentRegistry> provider) {
        return new StaticReleasePackageModule_ProvideRoutingRegistryFactory(staticReleasePackageModule, provider);
    }

    public static RoutingRegistry provideInstance(StaticReleasePackageModule staticReleasePackageModule, Provider<ComponentRegistry> provider) {
        return proxyProvideRoutingRegistry(staticReleasePackageModule, provider.mo10268get());
    }

    public static RoutingRegistry proxyProvideRoutingRegistry(StaticReleasePackageModule staticReleasePackageModule, ComponentRegistry componentRegistry) {
        return (RoutingRegistry) Preconditions.checkNotNull(staticReleasePackageModule.provideRoutingRegistry(componentRegistry), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RoutingRegistry mo10268get() {
        return provideInstance(this.module, this.componentRegistryProvider);
    }
}
