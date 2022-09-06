package com.amazon.alexa.tarazed.dagger.modules;

import com.amazon.alexa.eventbus.api.EventBus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class TarazedIntegrationModule_ProvideEventBus$AlexaMobileAndroidTarazedIntegration_releaseFactory implements Factory<EventBus> {
    private final TarazedIntegrationModule module;

    public TarazedIntegrationModule_ProvideEventBus$AlexaMobileAndroidTarazedIntegration_releaseFactory(TarazedIntegrationModule tarazedIntegrationModule) {
        this.module = tarazedIntegrationModule;
    }

    public static TarazedIntegrationModule_ProvideEventBus$AlexaMobileAndroidTarazedIntegration_releaseFactory create(TarazedIntegrationModule tarazedIntegrationModule) {
        return new TarazedIntegrationModule_ProvideEventBus$AlexaMobileAndroidTarazedIntegration_releaseFactory(tarazedIntegrationModule);
    }

    public static EventBus provideInstance(TarazedIntegrationModule tarazedIntegrationModule) {
        return proxyProvideEventBus$AlexaMobileAndroidTarazedIntegration_release(tarazedIntegrationModule);
    }

    public static EventBus proxyProvideEventBus$AlexaMobileAndroidTarazedIntegration_release(TarazedIntegrationModule tarazedIntegrationModule) {
        return (EventBus) Preconditions.checkNotNull(tarazedIntegrationModule.provideEventBus$AlexaMobileAndroidTarazedIntegration_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EventBus mo10268get() {
        return provideInstance(this.module);
    }
}
