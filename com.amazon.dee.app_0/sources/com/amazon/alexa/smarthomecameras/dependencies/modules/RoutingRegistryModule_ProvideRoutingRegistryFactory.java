package com.amazon.alexa.smarthomecameras.dependencies.modules;

import com.amazon.alexa.routing.api.RoutingRegistry;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class RoutingRegistryModule_ProvideRoutingRegistryFactory implements Factory<RoutingRegistry> {
    private static final RoutingRegistryModule_ProvideRoutingRegistryFactory INSTANCE = new RoutingRegistryModule_ProvideRoutingRegistryFactory();

    public static RoutingRegistryModule_ProvideRoutingRegistryFactory create() {
        return INSTANCE;
    }

    public static RoutingRegistry provideInstance() {
        return proxyProvideRoutingRegistry();
    }

    public static RoutingRegistry proxyProvideRoutingRegistry() {
        return (RoutingRegistry) Preconditions.checkNotNull(RoutingRegistryModule.provideRoutingRegistry(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RoutingRegistry mo10268get() {
        return provideInstance();
    }
}
