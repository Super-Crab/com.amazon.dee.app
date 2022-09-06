package com.amazon.alexa.fitness.dagger;

import com.amazon.alexa.protocols.marketplace.MarketplaceService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class StaticReleasePackageModule_ProvideMarketplaceServiceFactory implements Factory<MarketplaceService> {
    private final Provider<ComponentRegistry> componentRegistryProvider;
    private final StaticReleasePackageModule module;

    public StaticReleasePackageModule_ProvideMarketplaceServiceFactory(StaticReleasePackageModule staticReleasePackageModule, Provider<ComponentRegistry> provider) {
        this.module = staticReleasePackageModule;
        this.componentRegistryProvider = provider;
    }

    public static StaticReleasePackageModule_ProvideMarketplaceServiceFactory create(StaticReleasePackageModule staticReleasePackageModule, Provider<ComponentRegistry> provider) {
        return new StaticReleasePackageModule_ProvideMarketplaceServiceFactory(staticReleasePackageModule, provider);
    }

    public static MarketplaceService provideInstance(StaticReleasePackageModule staticReleasePackageModule, Provider<ComponentRegistry> provider) {
        return proxyProvideMarketplaceService(staticReleasePackageModule, provider.mo10268get());
    }

    public static MarketplaceService proxyProvideMarketplaceService(StaticReleasePackageModule staticReleasePackageModule, ComponentRegistry componentRegistry) {
        return (MarketplaceService) Preconditions.checkNotNull(staticReleasePackageModule.provideMarketplaceService(componentRegistry), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MarketplaceService mo10268get() {
        return provideInstance(this.module, this.componentRegistryProvider);
    }
}
