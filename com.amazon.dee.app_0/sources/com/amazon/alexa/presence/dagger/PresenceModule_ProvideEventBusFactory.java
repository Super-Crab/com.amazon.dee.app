package com.amazon.alexa.presence.dagger;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class PresenceModule_ProvideEventBusFactory implements Factory<EventBus> {
    private final Provider<ComponentRegistry> applicationComponentsProvider;

    public PresenceModule_ProvideEventBusFactory(Provider<ComponentRegistry> provider) {
        this.applicationComponentsProvider = provider;
    }

    public static PresenceModule_ProvideEventBusFactory create(Provider<ComponentRegistry> provider) {
        return new PresenceModule_ProvideEventBusFactory(provider);
    }

    public static EventBus provideInstance(Provider<ComponentRegistry> provider) {
        return proxyProvideEventBus(provider.mo10268get());
    }

    public static EventBus proxyProvideEventBus(ComponentRegistry componentRegistry) {
        return (EventBus) Preconditions.checkNotNull(PresenceModule.provideEventBus(componentRegistry), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EventBus mo10268get() {
        return provideInstance(this.applicationComponentsProvider);
    }
}
