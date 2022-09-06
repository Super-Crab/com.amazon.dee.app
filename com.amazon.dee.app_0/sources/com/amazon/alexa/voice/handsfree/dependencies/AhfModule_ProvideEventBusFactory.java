package com.amazon.alexa.voice.handsfree.dependencies;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class AhfModule_ProvideEventBusFactory implements Factory<EventBus> {
    private final Provider<ComponentRegistry> componentRegistryProvider;
    private final AhfModule module;

    public AhfModule_ProvideEventBusFactory(AhfModule ahfModule, Provider<ComponentRegistry> provider) {
        this.module = ahfModule;
        this.componentRegistryProvider = provider;
    }

    public static AhfModule_ProvideEventBusFactory create(AhfModule ahfModule, Provider<ComponentRegistry> provider) {
        return new AhfModule_ProvideEventBusFactory(ahfModule, provider);
    }

    public static EventBus provideInstance(AhfModule ahfModule, Provider<ComponentRegistry> provider) {
        return proxyProvideEventBus(ahfModule, provider.mo10268get());
    }

    public static EventBus proxyProvideEventBus(AhfModule ahfModule, ComponentRegistry componentRegistry) {
        return (EventBus) Preconditions.checkNotNull(ahfModule.provideEventBus(componentRegistry), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EventBus mo10268get() {
        return provideInstance(this.module, this.componentRegistryProvider);
    }
}
