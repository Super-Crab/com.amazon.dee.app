package com.amazon.dee.app.dependencies;

import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.dee.app.elements.ElementsRoutingAdapter;
import com.amazon.dee.app.elements.ReactBridgeService;
import com.amazon.dee.app.elements.ReactFeatureManager;
import com.amazon.regulator.Router;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ElementsModule_ProvideElementsRoutingAdapterFactory implements Factory<ElementsRoutingAdapter> {
    private final Provider<IdentityService> identityServiceProvider;
    private final ElementsModule module;
    private final Provider<ReactBridgeService> reactBridgeProvider;
    private final Provider<ReactFeatureManager> reactFeatureManagerProvider;
    private final Provider<Router> routerProvider;

    public ElementsModule_ProvideElementsRoutingAdapterFactory(ElementsModule elementsModule, Provider<Router> provider, Provider<ReactFeatureManager> provider2, Provider<IdentityService> provider3, Provider<ReactBridgeService> provider4) {
        this.module = elementsModule;
        this.routerProvider = provider;
        this.reactFeatureManagerProvider = provider2;
        this.identityServiceProvider = provider3;
        this.reactBridgeProvider = provider4;
    }

    public static ElementsModule_ProvideElementsRoutingAdapterFactory create(ElementsModule elementsModule, Provider<Router> provider, Provider<ReactFeatureManager> provider2, Provider<IdentityService> provider3, Provider<ReactBridgeService> provider4) {
        return new ElementsModule_ProvideElementsRoutingAdapterFactory(elementsModule, provider, provider2, provider3, provider4);
    }

    public static ElementsRoutingAdapter provideInstance(ElementsModule elementsModule, Provider<Router> provider, Provider<ReactFeatureManager> provider2, Provider<IdentityService> provider3, Provider<ReactBridgeService> provider4) {
        return proxyProvideElementsRoutingAdapter(elementsModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static ElementsRoutingAdapter proxyProvideElementsRoutingAdapter(ElementsModule elementsModule, Router router, ReactFeatureManager reactFeatureManager, IdentityService identityService, ReactBridgeService reactBridgeService) {
        return (ElementsRoutingAdapter) Preconditions.checkNotNull(elementsModule.provideElementsRoutingAdapter(router, reactFeatureManager, identityService, reactBridgeService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ElementsRoutingAdapter mo10268get() {
        return provideInstance(this.module, this.routerProvider, this.reactFeatureManagerProvider, this.identityServiceProvider, this.reactBridgeProvider);
    }
}
