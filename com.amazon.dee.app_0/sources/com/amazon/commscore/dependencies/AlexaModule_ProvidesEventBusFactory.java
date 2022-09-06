package com.amazon.commscore.dependencies;

import com.amazon.alexa.eventbus.api.EventBus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class AlexaModule_ProvidesEventBusFactory implements Factory<EventBus> {
    private final AlexaModule module;

    public AlexaModule_ProvidesEventBusFactory(AlexaModule alexaModule) {
        this.module = alexaModule;
    }

    public static AlexaModule_ProvidesEventBusFactory create(AlexaModule alexaModule) {
        return new AlexaModule_ProvidesEventBusFactory(alexaModule);
    }

    public static EventBus provideInstance(AlexaModule alexaModule) {
        return proxyProvidesEventBus(alexaModule);
    }

    public static EventBus proxyProvidesEventBus(AlexaModule alexaModule) {
        return (EventBus) Preconditions.checkNotNull(alexaModule.providesEventBus(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EventBus mo10268get() {
        return provideInstance(this.module);
    }
}
