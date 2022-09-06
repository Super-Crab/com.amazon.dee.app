package com.amazon.dee.app.dependencies;

import com.amazon.alexa.elements.api.BridgeStatusService;
import com.amazon.dee.app.elements.CollectionsFactory;
import com.amazon.dee.app.elements.ReactBridgeService;
import com.amazon.dee.app.elements.ReactFeatureManager;
import com.amazon.dee.app.services.tabLayout.TabLayoutStatusService;
import com.dee.app.metrics.MetricsServiceV2;
import com.facebook.react.ReactInstanceManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ElementsModule_ProvideReactInstanceManagerProviderFactory implements Factory<ReactFeatureManager> {
    private final Provider<ReactBridgeService> bridgeServiceProvider;
    private final Provider<BridgeStatusService> bridgeStatusServiceProvider;
    private final Provider<CollectionsFactory> collectionsFactoryProvider;
    private final Provider<MetricsServiceV2> metricsServiceV2Provider;
    private final ElementsModule module;
    private final Provider<Boolean> reactDeveloperSupportEnabledProvider;
    private final Provider<ReactInstanceManager> reactInstanceManagerProvider;
    private final Provider<TabLayoutStatusService> tabLayoutStatusServiceProvider;

    public ElementsModule_ProvideReactInstanceManagerProviderFactory(ElementsModule elementsModule, Provider<CollectionsFactory> provider, Provider<ReactInstanceManager> provider2, Provider<BridgeStatusService> provider3, Provider<MetricsServiceV2> provider4, Provider<Boolean> provider5, Provider<TabLayoutStatusService> provider6, Provider<ReactBridgeService> provider7) {
        this.module = elementsModule;
        this.collectionsFactoryProvider = provider;
        this.reactInstanceManagerProvider = provider2;
        this.bridgeStatusServiceProvider = provider3;
        this.metricsServiceV2Provider = provider4;
        this.reactDeveloperSupportEnabledProvider = provider5;
        this.tabLayoutStatusServiceProvider = provider6;
        this.bridgeServiceProvider = provider7;
    }

    public static ElementsModule_ProvideReactInstanceManagerProviderFactory create(ElementsModule elementsModule, Provider<CollectionsFactory> provider, Provider<ReactInstanceManager> provider2, Provider<BridgeStatusService> provider3, Provider<MetricsServiceV2> provider4, Provider<Boolean> provider5, Provider<TabLayoutStatusService> provider6, Provider<ReactBridgeService> provider7) {
        return new ElementsModule_ProvideReactInstanceManagerProviderFactory(elementsModule, provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static ReactFeatureManager provideInstance(ElementsModule elementsModule, Provider<CollectionsFactory> provider, Provider<ReactInstanceManager> provider2, Provider<BridgeStatusService> provider3, Provider<MetricsServiceV2> provider4, Provider<Boolean> provider5, Provider<TabLayoutStatusService> provider6, Provider<ReactBridgeService> provider7) {
        return proxyProvideReactInstanceManagerProvider(elementsModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get().booleanValue(), provider6.mo10268get(), provider7.mo10268get());
    }

    public static ReactFeatureManager proxyProvideReactInstanceManagerProvider(ElementsModule elementsModule, CollectionsFactory collectionsFactory, ReactInstanceManager reactInstanceManager, BridgeStatusService bridgeStatusService, MetricsServiceV2 metricsServiceV2, boolean z, TabLayoutStatusService tabLayoutStatusService, ReactBridgeService reactBridgeService) {
        return (ReactFeatureManager) Preconditions.checkNotNull(elementsModule.provideReactInstanceManagerProvider(collectionsFactory, reactInstanceManager, bridgeStatusService, metricsServiceV2, z, tabLayoutStatusService, reactBridgeService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ReactFeatureManager mo10268get() {
        return provideInstance(this.module, this.collectionsFactoryProvider, this.reactInstanceManagerProvider, this.bridgeStatusServiceProvider, this.metricsServiceV2Provider, this.reactDeveloperSupportEnabledProvider, this.tabLayoutStatusServiceProvider, this.bridgeServiceProvider);
    }
}
