package com.amazon.alexa.fitness.dagger;

import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class StaticReleasePackageModule_ProvideFeatureServiceV2Factory implements Factory<FeatureServiceV2> {
    private final Provider<ComponentRegistry> componentRegistryProvider;
    private final StaticReleasePackageModule module;

    public StaticReleasePackageModule_ProvideFeatureServiceV2Factory(StaticReleasePackageModule staticReleasePackageModule, Provider<ComponentRegistry> provider) {
        this.module = staticReleasePackageModule;
        this.componentRegistryProvider = provider;
    }

    public static StaticReleasePackageModule_ProvideFeatureServiceV2Factory create(StaticReleasePackageModule staticReleasePackageModule, Provider<ComponentRegistry> provider) {
        return new StaticReleasePackageModule_ProvideFeatureServiceV2Factory(staticReleasePackageModule, provider);
    }

    public static FeatureServiceV2 provideInstance(StaticReleasePackageModule staticReleasePackageModule, Provider<ComponentRegistry> provider) {
        return proxyProvideFeatureServiceV2(staticReleasePackageModule, provider.mo10268get());
    }

    public static FeatureServiceV2 proxyProvideFeatureServiceV2(StaticReleasePackageModule staticReleasePackageModule, ComponentRegistry componentRegistry) {
        return (FeatureServiceV2) Preconditions.checkNotNull(staticReleasePackageModule.provideFeatureServiceV2(componentRegistry), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureServiceV2 mo10268get() {
        return provideInstance(this.module, this.componentRegistryProvider);
    }
}
