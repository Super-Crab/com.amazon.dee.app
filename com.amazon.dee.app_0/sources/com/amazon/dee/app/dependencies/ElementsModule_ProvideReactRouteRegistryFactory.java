package com.amazon.dee.app.dependencies;

import com.amazon.alexa.routing.RouteFeatureGroupRegistry;
import com.amazon.alexa.routing.RouteWatcher;
import com.amazon.dee.app.elements.ReactRouteRegistry;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ElementsModule_ProvideReactRouteRegistryFactory implements Factory<ReactRouteRegistry> {
    private final Provider<RouteFeatureGroupRegistry> groupRegistryProvider;
    private final ElementsModule module;
    private final Provider<RouteWatcher> routeWatcherProvider;

    public ElementsModule_ProvideReactRouteRegistryFactory(ElementsModule elementsModule, Provider<RouteFeatureGroupRegistry> provider, Provider<RouteWatcher> provider2) {
        this.module = elementsModule;
        this.groupRegistryProvider = provider;
        this.routeWatcherProvider = provider2;
    }

    public static ElementsModule_ProvideReactRouteRegistryFactory create(ElementsModule elementsModule, Provider<RouteFeatureGroupRegistry> provider, Provider<RouteWatcher> provider2) {
        return new ElementsModule_ProvideReactRouteRegistryFactory(elementsModule, provider, provider2);
    }

    public static ReactRouteRegistry provideInstance(ElementsModule elementsModule, Provider<RouteFeatureGroupRegistry> provider, Provider<RouteWatcher> provider2) {
        return proxyProvideReactRouteRegistry(elementsModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static ReactRouteRegistry proxyProvideReactRouteRegistry(ElementsModule elementsModule, RouteFeatureGroupRegistry routeFeatureGroupRegistry, RouteWatcher routeWatcher) {
        return (ReactRouteRegistry) Preconditions.checkNotNull(elementsModule.provideReactRouteRegistry(routeFeatureGroupRegistry, routeWatcher), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ReactRouteRegistry mo10268get() {
        return provideInstance(this.module, this.groupRegistryProvider, this.routeWatcherProvider);
    }
}
