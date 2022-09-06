package com.amazon.dee.app.dependencies;

import com.amazon.alexa.component.api.ServiceLifecycle;
import com.amazon.alexa.eventbus.api.EventBus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class EventBusModule_ProvideEventBusServiceFactory implements Factory<ServiceLifecycle> {
    private final Provider<EventBus> eventBusProvider;
    private final EventBusModule module;

    public EventBusModule_ProvideEventBusServiceFactory(EventBusModule eventBusModule, Provider<EventBus> provider) {
        this.module = eventBusModule;
        this.eventBusProvider = provider;
    }

    public static EventBusModule_ProvideEventBusServiceFactory create(EventBusModule eventBusModule, Provider<EventBus> provider) {
        return new EventBusModule_ProvideEventBusServiceFactory(eventBusModule, provider);
    }

    public static ServiceLifecycle provideInstance(EventBusModule eventBusModule, Provider<EventBus> provider) {
        return proxyProvideEventBusService(eventBusModule, provider.mo10268get());
    }

    public static ServiceLifecycle proxyProvideEventBusService(EventBusModule eventBusModule, EventBus eventBus) {
        return (ServiceLifecycle) Preconditions.checkNotNull(eventBusModule.provideEventBusService(eventBus), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ServiceLifecycle mo10268get() {
        return provideInstance(this.module, this.eventBusProvider);
    }
}
