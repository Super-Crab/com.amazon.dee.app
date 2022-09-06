package com.amazon.dee.app.dependencies;

import com.amazon.alexa.routing.RouteFeatureGroupRegistry;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class RoutingModule_ProvideGroupRoutingRegistryFactory implements Factory<RouteFeatureGroupRegistry> {
    private final RoutingModule module;

    public RoutingModule_ProvideGroupRoutingRegistryFactory(RoutingModule routingModule) {
        this.module = routingModule;
    }

    public static RoutingModule_ProvideGroupRoutingRegistryFactory create(RoutingModule routingModule) {
        return new RoutingModule_ProvideGroupRoutingRegistryFactory(routingModule);
    }

    public static RouteFeatureGroupRegistry provideInstance(RoutingModule routingModule) {
        return proxyProvideGroupRoutingRegistry(routingModule);
    }

    public static RouteFeatureGroupRegistry proxyProvideGroupRoutingRegistry(RoutingModule routingModule) {
        return (RouteFeatureGroupRegistry) Preconditions.checkNotNull(routingModule.provideGroupRoutingRegistry(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RouteFeatureGroupRegistry mo10268get() {
        return provideInstance(this.module);
    }
}
