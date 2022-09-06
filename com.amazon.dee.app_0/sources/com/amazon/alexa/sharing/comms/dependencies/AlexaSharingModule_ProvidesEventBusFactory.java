package com.amazon.alexa.sharing.comms.dependencies;

import com.amazon.alexa.eventbus.api.EventBus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class AlexaSharingModule_ProvidesEventBusFactory implements Factory<EventBus> {
    private final AlexaSharingModule module;

    public AlexaSharingModule_ProvidesEventBusFactory(AlexaSharingModule alexaSharingModule) {
        this.module = alexaSharingModule;
    }

    public static AlexaSharingModule_ProvidesEventBusFactory create(AlexaSharingModule alexaSharingModule) {
        return new AlexaSharingModule_ProvidesEventBusFactory(alexaSharingModule);
    }

    public static EventBus provideInstance(AlexaSharingModule alexaSharingModule) {
        return proxyProvidesEventBus(alexaSharingModule);
    }

    public static EventBus proxyProvidesEventBus(AlexaSharingModule alexaSharingModule) {
        return (EventBus) Preconditions.checkNotNull(alexaSharingModule.providesEventBus(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EventBus mo10268get() {
        return provideInstance(this.module);
    }
}
