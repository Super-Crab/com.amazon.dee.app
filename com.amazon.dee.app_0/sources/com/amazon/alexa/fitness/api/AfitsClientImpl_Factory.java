package com.amazon.alexa.fitness.api;

import com.amazon.alexa.fitness.util.AfitsHeaderUtils;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.dee.app.http.HttpCoralService;
import com.dee.app.metrics.MetricsServiceV2;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class AfitsClientImpl_Factory implements Factory<AfitsClientImpl> {
    private final Provider<AfitsHeaderUtils> afitsHeaderUtilsProvider;
    private final Provider<ComponentRegistry> componentRegistryProvider;
    private final Provider<HttpCoralService> coralServiceProvider;
    private final Provider<MetricsServiceV2> metricsServiceV2Provider;

    public AfitsClientImpl_Factory(Provider<ComponentRegistry> provider, Provider<MetricsServiceV2> provider2, Provider<HttpCoralService> provider3, Provider<AfitsHeaderUtils> provider4) {
        this.componentRegistryProvider = provider;
        this.metricsServiceV2Provider = provider2;
        this.coralServiceProvider = provider3;
        this.afitsHeaderUtilsProvider = provider4;
    }

    public static AfitsClientImpl_Factory create(Provider<ComponentRegistry> provider, Provider<MetricsServiceV2> provider2, Provider<HttpCoralService> provider3, Provider<AfitsHeaderUtils> provider4) {
        return new AfitsClientImpl_Factory(provider, provider2, provider3, provider4);
    }

    public static AfitsClientImpl newAfitsClientImpl(ComponentRegistry componentRegistry, MetricsServiceV2 metricsServiceV2, HttpCoralService httpCoralService, AfitsHeaderUtils afitsHeaderUtils) {
        return new AfitsClientImpl(componentRegistry, metricsServiceV2, httpCoralService, afitsHeaderUtils);
    }

    public static AfitsClientImpl provideInstance(Provider<ComponentRegistry> provider, Provider<MetricsServiceV2> provider2, Provider<HttpCoralService> provider3, Provider<AfitsHeaderUtils> provider4) {
        return new AfitsClientImpl(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AfitsClientImpl mo10268get() {
        return provideInstance(this.componentRegistryProvider, this.metricsServiceV2Provider, this.coralServiceProvider, this.afitsHeaderUtilsProvider);
    }
}
