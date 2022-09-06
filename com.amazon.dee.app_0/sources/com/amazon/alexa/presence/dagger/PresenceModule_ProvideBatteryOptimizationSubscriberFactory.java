package com.amazon.alexa.presence.dagger;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.presence.battery.BatteryOptimization;
import com.amazon.alexa.presence.eventbus.BatteryOptimizationSubscriber;
import com.amazon.alexa.presence.eventbus.EventMessageFilter;
import com.dee.app.metrics.MetricsServiceV2;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class PresenceModule_ProvideBatteryOptimizationSubscriberFactory implements Factory<BatteryOptimizationSubscriber> {
    private final Provider<BatteryOptimization> batteryOptimizationProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<EventMessageFilter> eventMessageFilterProvider;
    private final Provider<MetricsServiceV2> metricsServiceV2Provider;
    private final PresenceModule module;

    public PresenceModule_ProvideBatteryOptimizationSubscriberFactory(PresenceModule presenceModule, Provider<EventBus> provider, Provider<BatteryOptimization> provider2, Provider<EventMessageFilter> provider3, Provider<MetricsServiceV2> provider4) {
        this.module = presenceModule;
        this.eventBusProvider = provider;
        this.batteryOptimizationProvider = provider2;
        this.eventMessageFilterProvider = provider3;
        this.metricsServiceV2Provider = provider4;
    }

    public static PresenceModule_ProvideBatteryOptimizationSubscriberFactory create(PresenceModule presenceModule, Provider<EventBus> provider, Provider<BatteryOptimization> provider2, Provider<EventMessageFilter> provider3, Provider<MetricsServiceV2> provider4) {
        return new PresenceModule_ProvideBatteryOptimizationSubscriberFactory(presenceModule, provider, provider2, provider3, provider4);
    }

    public static BatteryOptimizationSubscriber provideInstance(PresenceModule presenceModule, Provider<EventBus> provider, Provider<BatteryOptimization> provider2, Provider<EventMessageFilter> provider3, Provider<MetricsServiceV2> provider4) {
        return proxyProvideBatteryOptimizationSubscriber(presenceModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static BatteryOptimizationSubscriber proxyProvideBatteryOptimizationSubscriber(PresenceModule presenceModule, EventBus eventBus, BatteryOptimization batteryOptimization, EventMessageFilter eventMessageFilter, MetricsServiceV2 metricsServiceV2) {
        return (BatteryOptimizationSubscriber) Preconditions.checkNotNull(presenceModule.provideBatteryOptimizationSubscriber(eventBus, batteryOptimization, eventMessageFilter, metricsServiceV2), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public BatteryOptimizationSubscriber mo10268get() {
        return provideInstance(this.module, this.eventBusProvider, this.batteryOptimizationProvider, this.eventMessageFilterProvider, this.metricsServiceV2Provider);
    }
}
