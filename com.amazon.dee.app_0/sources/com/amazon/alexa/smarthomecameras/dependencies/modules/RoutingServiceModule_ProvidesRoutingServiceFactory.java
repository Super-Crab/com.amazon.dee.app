package com.amazon.alexa.smarthomecameras.dependencies.modules;

import com.amazon.alexa.routing.api.RoutingService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class RoutingServiceModule_ProvidesRoutingServiceFactory implements Factory<RoutingService> {
    private final RoutingServiceModule module;

    public RoutingServiceModule_ProvidesRoutingServiceFactory(RoutingServiceModule routingServiceModule) {
        this.module = routingServiceModule;
    }

    public static RoutingServiceModule_ProvidesRoutingServiceFactory create(RoutingServiceModule routingServiceModule) {
        return new RoutingServiceModule_ProvidesRoutingServiceFactory(routingServiceModule);
    }

    public static RoutingService provideInstance(RoutingServiceModule routingServiceModule) {
        return proxyProvidesRoutingService(routingServiceModule);
    }

    public static RoutingService proxyProvidesRoutingService(RoutingServiceModule routingServiceModule) {
        return (RoutingService) Preconditions.checkNotNull(routingServiceModule.providesRoutingService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RoutingService mo10268get() {
        return provideInstance(this.module);
    }
}
