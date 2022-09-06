package com.amazon.alexa.fitness.dagger;

import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class StaticReleasePackageModule_ProvideIdentityServiceFactory implements Factory<IdentityService> {
    private final Provider<ComponentRegistry> componentRegistryProvider;
    private final StaticReleasePackageModule module;

    public StaticReleasePackageModule_ProvideIdentityServiceFactory(StaticReleasePackageModule staticReleasePackageModule, Provider<ComponentRegistry> provider) {
        this.module = staticReleasePackageModule;
        this.componentRegistryProvider = provider;
    }

    public static StaticReleasePackageModule_ProvideIdentityServiceFactory create(StaticReleasePackageModule staticReleasePackageModule, Provider<ComponentRegistry> provider) {
        return new StaticReleasePackageModule_ProvideIdentityServiceFactory(staticReleasePackageModule, provider);
    }

    public static IdentityService provideInstance(StaticReleasePackageModule staticReleasePackageModule, Provider<ComponentRegistry> provider) {
        return proxyProvideIdentityService(staticReleasePackageModule, provider.mo10268get());
    }

    public static IdentityService proxyProvideIdentityService(StaticReleasePackageModule staticReleasePackageModule, ComponentRegistry componentRegistry) {
        return (IdentityService) Preconditions.checkNotNull(staticReleasePackageModule.provideIdentityService(componentRegistry), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public IdentityService mo10268get() {
        return provideInstance(this.module, this.componentRegistryProvider);
    }
}
