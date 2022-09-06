package com.amazon.alexa.fitness.dagger;

import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.dee.app.metrics.MetricsServiceV2;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class StaticReleasePackageModule_ProvideMetricsServiceV2Factory implements Factory<MetricsServiceV2> {
    private final Provider<ComponentRegistry> componentRegistryProvider;
    private final StaticReleasePackageModule module;

    public StaticReleasePackageModule_ProvideMetricsServiceV2Factory(StaticReleasePackageModule staticReleasePackageModule, Provider<ComponentRegistry> provider) {
        this.module = staticReleasePackageModule;
        this.componentRegistryProvider = provider;
    }

    public static StaticReleasePackageModule_ProvideMetricsServiceV2Factory create(StaticReleasePackageModule staticReleasePackageModule, Provider<ComponentRegistry> provider) {
        return new StaticReleasePackageModule_ProvideMetricsServiceV2Factory(staticReleasePackageModule, provider);
    }

    public static MetricsServiceV2 provideInstance(StaticReleasePackageModule staticReleasePackageModule, Provider<ComponentRegistry> provider) {
        return proxyProvideMetricsServiceV2(staticReleasePackageModule, provider.mo10268get());
    }

    public static MetricsServiceV2 proxyProvideMetricsServiceV2(StaticReleasePackageModule staticReleasePackageModule, ComponentRegistry componentRegistry) {
        return (MetricsServiceV2) Preconditions.checkNotNull(staticReleasePackageModule.provideMetricsServiceV2(componentRegistry), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricsServiceV2 mo10268get() {
        return provideInstance(this.module, this.componentRegistryProvider);
    }
}
