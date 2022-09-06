package com.amazon.dee.app.dependencies;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.routing.RouteFeatureGroupRegistry;
import com.amazon.alexa.routing.RouteWatcher;
import com.amazon.alexa.routing.api.RoutingRegistry;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class RoutingModule_ProvideRouteWatcherFactory implements Factory<RouteWatcher> {
    private final Provider<EventBus> eventBusProvider;
    private final Provider<RouteFeatureGroupRegistry> groupRoutesProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final RoutingModule module;
    private final Provider<RoutingRegistry> routesProvider;

    public RoutingModule_ProvideRouteWatcherFactory(RoutingModule routingModule, Provider<IdentityService> provider, Provider<RoutingRegistry> provider2, Provider<RouteFeatureGroupRegistry> provider3, Provider<EventBus> provider4) {
        this.module = routingModule;
        this.identityServiceProvider = provider;
        this.routesProvider = provider2;
        this.groupRoutesProvider = provider3;
        this.eventBusProvider = provider4;
    }

    public static RoutingModule_ProvideRouteWatcherFactory create(RoutingModule routingModule, Provider<IdentityService> provider, Provider<RoutingRegistry> provider2, Provider<RouteFeatureGroupRegistry> provider3, Provider<EventBus> provider4) {
        return new RoutingModule_ProvideRouteWatcherFactory(routingModule, provider, provider2, provider3, provider4);
    }

    public static RouteWatcher provideInstance(RoutingModule routingModule, Provider<IdentityService> provider, Provider<RoutingRegistry> provider2, Provider<RouteFeatureGroupRegistry> provider3, Provider<EventBus> provider4) {
        return proxyProvideRouteWatcher(routingModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static RouteWatcher proxyProvideRouteWatcher(RoutingModule routingModule, IdentityService identityService, RoutingRegistry routingRegistry, RouteFeatureGroupRegistry routeFeatureGroupRegistry, EventBus eventBus) {
        return (RouteWatcher) Preconditions.checkNotNull(routingModule.provideRouteWatcher(identityService, routingRegistry, routeFeatureGroupRegistry, eventBus), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RouteWatcher mo10268get() {
        return provideInstance(this.module, this.identityServiceProvider, this.routesProvider, this.groupRoutesProvider, this.eventBusProvider);
    }
}
