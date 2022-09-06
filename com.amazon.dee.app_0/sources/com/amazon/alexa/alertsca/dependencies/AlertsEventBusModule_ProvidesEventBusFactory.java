package com.amazon.alexa.alertsca.dependencies;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import org.greenrobot.eventbus.EventBus;
/* loaded from: classes6.dex */
public final class AlertsEventBusModule_ProvidesEventBusFactory implements Factory<EventBus> {
    private final AlertsEventBusModule module;

    public AlertsEventBusModule_ProvidesEventBusFactory(AlertsEventBusModule alertsEventBusModule) {
        this.module = alertsEventBusModule;
    }

    public static AlertsEventBusModule_ProvidesEventBusFactory create(AlertsEventBusModule alertsEventBusModule) {
        return new AlertsEventBusModule_ProvidesEventBusFactory(alertsEventBusModule);
    }

    public static EventBus provideInstance(AlertsEventBusModule alertsEventBusModule) {
        return proxyProvidesEventBus(alertsEventBusModule);
    }

    public static EventBus proxyProvidesEventBus(AlertsEventBusModule alertsEventBusModule) {
        return (EventBus) Preconditions.checkNotNull(alertsEventBusModule.providesEventBus(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public EventBus mo10268get() {
        return provideInstance(this.module);
    }
}
