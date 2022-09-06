package com.amazon.alexa.fitness.dagger;

import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class StaticReleasePackageModule_ProvideNetworkServiceFactory implements Factory<NetworkService> {
    private final Provider<ComponentRegistry> componentRegistryProvider;
    private final StaticReleasePackageModule module;

    public StaticReleasePackageModule_ProvideNetworkServiceFactory(StaticReleasePackageModule staticReleasePackageModule, Provider<ComponentRegistry> provider) {
        this.module = staticReleasePackageModule;
        this.componentRegistryProvider = provider;
    }

    public static StaticReleasePackageModule_ProvideNetworkServiceFactory create(StaticReleasePackageModule staticReleasePackageModule, Provider<ComponentRegistry> provider) {
        return new StaticReleasePackageModule_ProvideNetworkServiceFactory(staticReleasePackageModule, provider);
    }

    public static NetworkService provideInstance(StaticReleasePackageModule staticReleasePackageModule, Provider<ComponentRegistry> provider) {
        return proxyProvideNetworkService(staticReleasePackageModule, provider.mo10268get());
    }

    public static NetworkService proxyProvideNetworkService(StaticReleasePackageModule staticReleasePackageModule, ComponentRegistry componentRegistry) {
        return (NetworkService) Preconditions.checkNotNull(staticReleasePackageModule.provideNetworkService(componentRegistry), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NetworkService mo10268get() {
        return provideInstance(this.module, this.componentRegistryProvider);
    }
}
