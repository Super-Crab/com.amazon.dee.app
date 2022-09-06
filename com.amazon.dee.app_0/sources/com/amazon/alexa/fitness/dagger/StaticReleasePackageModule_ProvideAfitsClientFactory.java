package com.amazon.alexa.fitness.dagger;

import com.amazon.alexa.fitness.api.afits.AfitsClient;
import com.amazon.alexa.fitness.util.AfitsHeaderUtils;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.dee.app.http.HttpCoralService;
import com.dee.app.metrics.MetricsServiceV2;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class StaticReleasePackageModule_ProvideAfitsClientFactory implements Factory<AfitsClient> {
    private final Provider<AfitsHeaderUtils> afitsHeaderUtilsProvider;
    private final Provider<ComponentRegistry> componentRegistryProvider;
    private final Provider<HttpCoralService> httpCoralServiceProvider;
    private final Provider<MetricsServiceV2> metricsServiceV2Provider;
    private final StaticReleasePackageModule module;

    public StaticReleasePackageModule_ProvideAfitsClientFactory(StaticReleasePackageModule staticReleasePackageModule, Provider<ComponentRegistry> provider, Provider<MetricsServiceV2> provider2, Provider<HttpCoralService> provider3, Provider<AfitsHeaderUtils> provider4) {
        this.module = staticReleasePackageModule;
        this.componentRegistryProvider = provider;
        this.metricsServiceV2Provider = provider2;
        this.httpCoralServiceProvider = provider3;
        this.afitsHeaderUtilsProvider = provider4;
    }

    public static StaticReleasePackageModule_ProvideAfitsClientFactory create(StaticReleasePackageModule staticReleasePackageModule, Provider<ComponentRegistry> provider, Provider<MetricsServiceV2> provider2, Provider<HttpCoralService> provider3, Provider<AfitsHeaderUtils> provider4) {
        return new StaticReleasePackageModule_ProvideAfitsClientFactory(staticReleasePackageModule, provider, provider2, provider3, provider4);
    }

    public static AfitsClient provideInstance(StaticReleasePackageModule staticReleasePackageModule, Provider<ComponentRegistry> provider, Provider<MetricsServiceV2> provider2, Provider<HttpCoralService> provider3, Provider<AfitsHeaderUtils> provider4) {
        return proxyProvideAfitsClient(staticReleasePackageModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static AfitsClient proxyProvideAfitsClient(StaticReleasePackageModule staticReleasePackageModule, ComponentRegistry componentRegistry, MetricsServiceV2 metricsServiceV2, HttpCoralService httpCoralService, AfitsHeaderUtils afitsHeaderUtils) {
        return (AfitsClient) Preconditions.checkNotNull(staticReleasePackageModule.provideAfitsClient(componentRegistry, metricsServiceV2, httpCoralService, afitsHeaderUtils), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AfitsClient mo10268get() {
        return provideInstance(this.module, this.componentRegistryProvider, this.metricsServiceV2Provider, this.httpCoralServiceProvider, this.afitsHeaderUtilsProvider);
    }
}
