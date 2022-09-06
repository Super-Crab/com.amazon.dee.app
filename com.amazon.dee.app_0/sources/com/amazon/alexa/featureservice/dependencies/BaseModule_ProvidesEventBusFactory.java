package com.amazon.alexa.featureservice.dependencies;

import com.amazon.alexa.eventbus.api.EventBus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class BaseModule_ProvidesEventBusFactory implements Factory<EventBus> {
    private final BaseModule module;

    public BaseModule_ProvidesEventBusFactory(BaseModule baseModule) {
        this.module = baseModule;
    }

    public static BaseModule_ProvidesEventBusFactory create(BaseModule baseModule) {
        return new BaseModule_ProvidesEventBusFactory(baseModule);
    }

    public static EventBus provideInstance(BaseModule baseModule) {
        return proxyProvidesEventBus(baseModule);
    }

    public static EventBus proxyProvidesEventBus(BaseModule baseModule) {
        return (EventBus) Preconditions.checkNotNull(baseModule.providesEventBus(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EventBus mo10268get() {
        return provideInstance(this.module);
    }
}
