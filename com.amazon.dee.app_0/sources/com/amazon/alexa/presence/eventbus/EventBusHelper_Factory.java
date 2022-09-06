package com.amazon.alexa.presence.eventbus;

import com.amazon.alexa.eventbus.api.EventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class EventBusHelper_Factory implements Factory<EventBusHelper> {
    private final Provider<BatteryOptimizationSubscriber> batteryOptimizationSubscriberProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<PresenceSubscriber> presenceSubscriberProvider;
    private final Provider<PushNotificationSubscriber> pushNotificationSubscriberProvider;

    public EventBusHelper_Factory(Provider<EventBus> provider, Provider<PresenceSubscriber> provider2, Provider<BatteryOptimizationSubscriber> provider3, Provider<PushNotificationSubscriber> provider4) {
        this.eventBusProvider = provider;
        this.presenceSubscriberProvider = provider2;
        this.batteryOptimizationSubscriberProvider = provider3;
        this.pushNotificationSubscriberProvider = provider4;
    }

    public static EventBusHelper_Factory create(Provider<EventBus> provider, Provider<PresenceSubscriber> provider2, Provider<BatteryOptimizationSubscriber> provider3, Provider<PushNotificationSubscriber> provider4) {
        return new EventBusHelper_Factory(provider, provider2, provider3, provider4);
    }

    public static EventBusHelper newEventBusHelper(EventBus eventBus, PresenceSubscriber presenceSubscriber, BatteryOptimizationSubscriber batteryOptimizationSubscriber, PushNotificationSubscriber pushNotificationSubscriber) {
        return new EventBusHelper(eventBus, presenceSubscriber, batteryOptimizationSubscriber, pushNotificationSubscriber);
    }

    public static EventBusHelper provideInstance(Provider<EventBus> provider, Provider<PresenceSubscriber> provider2, Provider<BatteryOptimizationSubscriber> provider3, Provider<PushNotificationSubscriber> provider4) {
        return new EventBusHelper(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EventBusHelper mo10268get() {
        return provideInstance(this.eventBusProvider, this.presenceSubscriberProvider, this.batteryOptimizationSubscriberProvider, this.pushNotificationSubscriberProvider);
    }
}
