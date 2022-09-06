package com.amazon.dee.app.dependencies;

import com.amazon.alexa.elements.api.BridgeStatusService;
import com.amazon.dee.app.elements.ReactBridgeMetrics;
import com.dee.app.metrics.MetricsServiceV2;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ElementsModule_ProvideReactBridgeMetricsFactory implements Factory<ReactBridgeMetrics> {
    private final Provider<BridgeStatusService> bridgeStatusServiceProvider;
    private final Provider<MetricsServiceV2> metricsProvider;
    private final ElementsModule module;

    public ElementsModule_ProvideReactBridgeMetricsFactory(ElementsModule elementsModule, Provider<MetricsServiceV2> provider, Provider<BridgeStatusService> provider2) {
        this.module = elementsModule;
        this.metricsProvider = provider;
        this.bridgeStatusServiceProvider = provider2;
    }

    public static ElementsModule_ProvideReactBridgeMetricsFactory create(ElementsModule elementsModule, Provider<MetricsServiceV2> provider, Provider<BridgeStatusService> provider2) {
        return new ElementsModule_ProvideReactBridgeMetricsFactory(elementsModule, provider, provider2);
    }

    public static ReactBridgeMetrics provideInstance(ElementsModule elementsModule, Provider<MetricsServiceV2> provider, Provider<BridgeStatusService> provider2) {
        return proxyProvideReactBridgeMetrics(elementsModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static ReactBridgeMetrics proxyProvideReactBridgeMetrics(ElementsModule elementsModule, MetricsServiceV2 metricsServiceV2, BridgeStatusService bridgeStatusService) {
        return (ReactBridgeMetrics) Preconditions.checkNotNull(elementsModule.provideReactBridgeMetrics(metricsServiceV2, bridgeStatusService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ReactBridgeMetrics mo10268get() {
        return provideInstance(this.module, this.metricsProvider, this.bridgeStatusServiceProvider);
    }
}
