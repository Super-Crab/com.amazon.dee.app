package com.amazon.alexa.fitness.dagger;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class StaticReleasePackageModule_ProvideEventBusFactory implements Factory<EventBus> {
    private final Provider<ComponentRegistry> componentRegistryProvider;
    private final StaticReleasePackageModule module;

    public StaticReleasePackageModule_ProvideEventBusFactory(StaticReleasePackageModule staticReleasePackageModule, Provider<ComponentRegistry> provider) {
        this.module = staticReleasePackageModule;
        this.componentRegistryProvider = provider;
    }

    public static StaticReleasePackageModule_ProvideEventBusFactory create(StaticReleasePackageModule staticReleasePackageModule, Provider<ComponentRegistry> provider) {
        return new StaticReleasePackageModule_ProvideEventBusFactory(staticReleasePackageModule, provider);
    }

    public static EventBus provideInstance(StaticReleasePackageModule staticReleasePackageModule, Provider<ComponentRegistry> provider) {
        return proxyProvideEventBus(staticReleasePackageModule, provider.mo10268get());
    }

    public static EventBus proxyProvideEventBus(StaticReleasePackageModule staticReleasePackageModule, ComponentRegistry componentRegistry) {
        return (EventBus) Preconditions.checkNotNull(staticReleasePackageModule.provideEventBus(componentRegistry), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EventBus mo10268get() {
        return provideInstance(this.module, this.componentRegistryProvider);
    }
}
