package com.amazon.alexa.smarthomecameras.dependencies.modules;

import com.amazon.alexa.eventbus.api.EventBus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class EventBusModule_ProvideEventBusFactory implements Factory<EventBus> {
    private static final EventBusModule_ProvideEventBusFactory INSTANCE = new EventBusModule_ProvideEventBusFactory();

    public static EventBusModule_ProvideEventBusFactory create() {
        return INSTANCE;
    }

    public static EventBus provideInstance() {
        return proxyProvideEventBus();
    }

    public static EventBus proxyProvideEventBus() {
        return (EventBus) Preconditions.checkNotNull(EventBusModule.provideEventBus(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EventBus mo10268get() {
        return provideInstance();
    }
}
