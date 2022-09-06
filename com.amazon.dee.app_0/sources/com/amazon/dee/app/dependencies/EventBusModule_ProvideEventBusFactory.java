package com.amazon.dee.app.dependencies;

import com.amazon.alexa.eventbus.api.EventBus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class EventBusModule_ProvideEventBusFactory implements Factory<EventBus> {
    private final EventBusModule module;

    public EventBusModule_ProvideEventBusFactory(EventBusModule eventBusModule) {
        this.module = eventBusModule;
    }

    public static EventBusModule_ProvideEventBusFactory create(EventBusModule eventBusModule) {
        return new EventBusModule_ProvideEventBusFactory(eventBusModule);
    }

    public static EventBus provideInstance(EventBusModule eventBusModule) {
        return proxyProvideEventBus(eventBusModule);
    }

    public static EventBus proxyProvideEventBus(EventBusModule eventBusModule) {
        return (EventBus) Preconditions.checkNotNull(eventBusModule.provideEventBus(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EventBus mo10268get() {
        return provideInstance(this.module);
    }
}
