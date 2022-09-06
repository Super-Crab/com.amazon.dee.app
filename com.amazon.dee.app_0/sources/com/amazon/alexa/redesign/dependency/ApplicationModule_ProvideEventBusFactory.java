package com.amazon.alexa.redesign.dependency;

import com.amazon.alexa.eventbus.api.EventBus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class ApplicationModule_ProvideEventBusFactory implements Factory<EventBus> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideEventBusFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideEventBusFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideEventBusFactory(applicationModule);
    }

    public static EventBus provideInstance(ApplicationModule applicationModule) {
        return proxyProvideEventBus(applicationModule);
    }

    public static EventBus proxyProvideEventBus(ApplicationModule applicationModule) {
        return (EventBus) Preconditions.checkNotNull(applicationModule.provideEventBus(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EventBus mo10268get() {
        return provideInstance(this.module);
    }
}
