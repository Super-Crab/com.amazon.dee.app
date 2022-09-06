package com.amazon.alexa.fitness.dagger;

import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes8.dex */
public final class StaticReleasePackageModule_ProvideComponentRegistryFactory implements Factory<ComponentRegistry> {
    private final StaticReleasePackageModule module;

    public StaticReleasePackageModule_ProvideComponentRegistryFactory(StaticReleasePackageModule staticReleasePackageModule) {
        this.module = staticReleasePackageModule;
    }

    public static StaticReleasePackageModule_ProvideComponentRegistryFactory create(StaticReleasePackageModule staticReleasePackageModule) {
        return new StaticReleasePackageModule_ProvideComponentRegistryFactory(staticReleasePackageModule);
    }

    public static ComponentRegistry provideInstance(StaticReleasePackageModule staticReleasePackageModule) {
        return proxyProvideComponentRegistry(staticReleasePackageModule);
    }

    public static ComponentRegistry proxyProvideComponentRegistry(StaticReleasePackageModule staticReleasePackageModule) {
        return (ComponentRegistry) Preconditions.checkNotNull(staticReleasePackageModule.provideComponentRegistry(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ComponentRegistry mo10268get() {
        return provideInstance(this.module);
    }
}
