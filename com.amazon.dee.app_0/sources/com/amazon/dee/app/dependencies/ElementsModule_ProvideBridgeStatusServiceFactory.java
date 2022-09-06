package com.amazon.dee.app.dependencies;

import com.amazon.alexa.elements.api.BridgeStatusService;
import com.amazon.alexa.eventbus.api.EventBus;
import com.dee.app.metrics.MetricsService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ElementsModule_ProvideBridgeStatusServiceFactory implements Factory<BridgeStatusService> {
    private final Provider<EventBus> eventBusProvider;
    private final Provider<MetricsService> metricsServiceProvider;
    private final ElementsModule module;

    public ElementsModule_ProvideBridgeStatusServiceFactory(ElementsModule elementsModule, Provider<MetricsService> provider, Provider<EventBus> provider2) {
        this.module = elementsModule;
        this.metricsServiceProvider = provider;
        this.eventBusProvider = provider2;
    }

    public static ElementsModule_ProvideBridgeStatusServiceFactory create(ElementsModule elementsModule, Provider<MetricsService> provider, Provider<EventBus> provider2) {
        return new ElementsModule_ProvideBridgeStatusServiceFactory(elementsModule, provider, provider2);
    }

    public static BridgeStatusService provideInstance(ElementsModule elementsModule, Provider<MetricsService> provider, Provider<EventBus> provider2) {
        return proxyProvideBridgeStatusService(elementsModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static BridgeStatusService proxyProvideBridgeStatusService(ElementsModule elementsModule, MetricsService metricsService, EventBus eventBus) {
        return (BridgeStatusService) Preconditions.checkNotNull(elementsModule.provideBridgeStatusService(metricsService, eventBus), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public BridgeStatusService mo10268get() {
        return provideInstance(this.module, this.metricsServiceProvider, this.eventBusProvider);
    }
}
