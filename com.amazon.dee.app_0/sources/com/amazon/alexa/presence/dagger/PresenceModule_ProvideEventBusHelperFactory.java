package com.amazon.alexa.presence.dagger;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.presence.eventbus.BatteryOptimizationSubscriber;
import com.amazon.alexa.presence.eventbus.EventBusHelper;
import com.amazon.alexa.presence.eventbus.PresenceSubscriber;
import com.amazon.alexa.presence.eventbus.PushNotificationSubscriber;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class PresenceModule_ProvideEventBusHelperFactory implements Factory<EventBusHelper> {
    private final Provider<BatteryOptimizationSubscriber> batteryOptimizationSubscriberProvider;
    private final Provider<EventBus> eventBusProvider;
    private final PresenceModule module;
    private final Provider<PresenceSubscriber> presenceSubscriberProvider;
    private final Provider<PushNotificationSubscriber> pushNotificationSubscriberProvider;

    public PresenceModule_ProvideEventBusHelperFactory(PresenceModule presenceModule, Provider<EventBus> provider, Provider<PresenceSubscriber> provider2, Provider<BatteryOptimizationSubscriber> provider3, Provider<PushNotificationSubscriber> provider4) {
        this.module = presenceModule;
        this.eventBusProvider = provider;
        this.presenceSubscriberProvider = provider2;
        this.batteryOptimizationSubscriberProvider = provider3;
        this.pushNotificationSubscriberProvider = provider4;
    }

    public static PresenceModule_ProvideEventBusHelperFactory create(PresenceModule presenceModule, Provider<EventBus> provider, Provider<PresenceSubscriber> provider2, Provider<BatteryOptimizationSubscriber> provider3, Provider<PushNotificationSubscriber> provider4) {
        return new PresenceModule_ProvideEventBusHelperFactory(presenceModule, provider, provider2, provider3, provider4);
    }

    public static EventBusHelper provideInstance(PresenceModule presenceModule, Provider<EventBus> provider, Provider<PresenceSubscriber> provider2, Provider<BatteryOptimizationSubscriber> provider3, Provider<PushNotificationSubscriber> provider4) {
        return proxyProvideEventBusHelper(presenceModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static EventBusHelper proxyProvideEventBusHelper(PresenceModule presenceModule, EventBus eventBus, PresenceSubscriber presenceSubscriber, BatteryOptimizationSubscriber batteryOptimizationSubscriber, PushNotificationSubscriber pushNotificationSubscriber) {
        return (EventBusHelper) Preconditions.checkNotNull(presenceModule.provideEventBusHelper(eventBus, presenceSubscriber, batteryOptimizationSubscriber, pushNotificationSubscriber), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EventBusHelper mo10268get() {
        return provideInstance(this.module, this.eventBusProvider, this.presenceSubscriberProvider, this.batteryOptimizationSubscriberProvider, this.pushNotificationSubscriberProvider);
    }
}
