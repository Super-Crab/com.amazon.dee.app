package com.amazon.dee.app.dependencies;

import com.amazon.alexa.elements.api.BridgeStatusService;
import com.amazon.dee.app.elements.ReactBridgeMetrics;
import com.amazon.dee.app.elements.ReactBridgeService;
import com.facebook.react.ReactInstanceManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ElementsModule_ProvideReactInstanceManagerFactory implements Factory<ReactInstanceManager> {
    private final Provider<ReactBridgeMetrics> bridgeMetricsProvider;
    private final Provider<BridgeStatusService> bridgeStatusServiceProvider;
    private final ElementsModule module;
    private final Provider<ReactBridgeService> reactBridgeServiceProvider;

    public ElementsModule_ProvideReactInstanceManagerFactory(ElementsModule elementsModule, Provider<ReactBridgeService> provider, Provider<ReactBridgeMetrics> provider2, Provider<BridgeStatusService> provider3) {
        this.module = elementsModule;
        this.reactBridgeServiceProvider = provider;
        this.bridgeMetricsProvider = provider2;
        this.bridgeStatusServiceProvider = provider3;
    }

    public static ElementsModule_ProvideReactInstanceManagerFactory create(ElementsModule elementsModule, Provider<ReactBridgeService> provider, Provider<ReactBridgeMetrics> provider2, Provider<BridgeStatusService> provider3) {
        return new ElementsModule_ProvideReactInstanceManagerFactory(elementsModule, provider, provider2, provider3);
    }

    public static ReactInstanceManager provideInstance(ElementsModule elementsModule, Provider<ReactBridgeService> provider, Provider<ReactBridgeMetrics> provider2, Provider<BridgeStatusService> provider3) {
        return proxyProvideReactInstanceManager(elementsModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static ReactInstanceManager proxyProvideReactInstanceManager(ElementsModule elementsModule, ReactBridgeService reactBridgeService, ReactBridgeMetrics reactBridgeMetrics, BridgeStatusService bridgeStatusService) {
        return (ReactInstanceManager) Preconditions.checkNotNull(elementsModule.provideReactInstanceManager(reactBridgeService, reactBridgeMetrics, bridgeStatusService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ReactInstanceManager mo10268get() {
        return provideInstance(this.module, this.reactBridgeServiceProvider, this.bridgeMetricsProvider, this.bridgeStatusServiceProvider);
    }
}
