package com.amazon.ptz.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import org.greenrobot.eventbus.EventBus;
/* loaded from: classes13.dex */
public final class UtilModule_ProvideEventBusFactory implements Factory<EventBus> {
    private final UtilModule module;

    public UtilModule_ProvideEventBusFactory(UtilModule utilModule) {
        this.module = utilModule;
    }

    public static UtilModule_ProvideEventBusFactory create(UtilModule utilModule) {
        return new UtilModule_ProvideEventBusFactory(utilModule);
    }

    public static EventBus provideInstance(UtilModule utilModule) {
        return proxyProvideEventBus(utilModule);
    }

    public static EventBus proxyProvideEventBus(UtilModule utilModule) {
        return (EventBus) Preconditions.checkNotNull(utilModule.provideEventBus(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public EventBus mo10268get() {
        return provideInstance(this.module);
    }
}
