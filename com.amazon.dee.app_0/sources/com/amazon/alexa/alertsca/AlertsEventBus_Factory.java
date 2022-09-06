package com.amazon.alexa.alertsca;

import dagger.internal.Factory;
import javax.inject.Provider;
import org.greenrobot.eventbus.EventBus;
/* loaded from: classes6.dex */
public final class AlertsEventBus_Factory implements Factory<AlertsEventBus> {
    private final Provider<EventBus> eventBusProvider;

    public AlertsEventBus_Factory(Provider<EventBus> provider) {
        this.eventBusProvider = provider;
    }

    public static AlertsEventBus_Factory create(Provider<EventBus> provider) {
        return new AlertsEventBus_Factory(provider);
    }

    public static AlertsEventBus newAlertsEventBus(EventBus eventBus) {
        return new AlertsEventBus(eventBus);
    }

    public static AlertsEventBus provideInstance(Provider<EventBus> provider) {
        return new AlertsEventBus(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlertsEventBus mo10268get() {
        return provideInstance(this.eventBusProvider);
    }
}
