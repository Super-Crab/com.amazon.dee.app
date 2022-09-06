package com.amazon.dee.app.dependencies;

import com.amazon.alexa.routing.api.RoutingRegistry;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class RoutingModule_ProvideRoutingRegistryFactory implements Factory<RoutingRegistry> {
    private final RoutingModule module;

    public RoutingModule_ProvideRoutingRegistryFactory(RoutingModule routingModule) {
        this.module = routingModule;
    }

    public static RoutingModule_ProvideRoutingRegistryFactory create(RoutingModule routingModule) {
        return new RoutingModule_ProvideRoutingRegistryFactory(routingModule);
    }

    public static RoutingRegistry provideInstance(RoutingModule routingModule) {
        return proxyProvideRoutingRegistry(routingModule);
    }

    public static RoutingRegistry proxyProvideRoutingRegistry(RoutingModule routingModule) {
        return (RoutingRegistry) Preconditions.checkNotNull(routingModule.provideRoutingRegistry(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RoutingRegistry mo10268get() {
        return provideInstance(this.module);
    }
}
