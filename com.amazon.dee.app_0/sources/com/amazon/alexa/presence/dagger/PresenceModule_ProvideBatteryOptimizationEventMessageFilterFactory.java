package com.amazon.alexa.presence.dagger;

import com.amazon.alexa.presence.eventbus.EventMessageFilter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes9.dex */
public final class PresenceModule_ProvideBatteryOptimizationEventMessageFilterFactory implements Factory<EventMessageFilter> {
    private final PresenceModule module;

    public PresenceModule_ProvideBatteryOptimizationEventMessageFilterFactory(PresenceModule presenceModule) {
        this.module = presenceModule;
    }

    public static PresenceModule_ProvideBatteryOptimizationEventMessageFilterFactory create(PresenceModule presenceModule) {
        return new PresenceModule_ProvideBatteryOptimizationEventMessageFilterFactory(presenceModule);
    }

    public static EventMessageFilter provideInstance(PresenceModule presenceModule) {
        return proxyProvideBatteryOptimizationEventMessageFilter(presenceModule);
    }

    public static EventMessageFilter proxyProvideBatteryOptimizationEventMessageFilter(PresenceModule presenceModule) {
        return (EventMessageFilter) Preconditions.checkNotNull(presenceModule.provideBatteryOptimizationEventMessageFilter(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EventMessageFilter mo10268get() {
        return provideInstance(this.module);
    }
}
