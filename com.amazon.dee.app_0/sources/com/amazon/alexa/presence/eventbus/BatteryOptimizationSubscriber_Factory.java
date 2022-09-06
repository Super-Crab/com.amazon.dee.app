package com.amazon.alexa.presence.eventbus;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.presence.battery.BatteryOptimization;
import com.dee.app.metrics.MetricsServiceV2;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class BatteryOptimizationSubscriber_Factory implements Factory<BatteryOptimizationSubscriber> {
    private final Provider<BatteryOptimization> batteryOptimizationProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<EventMessageFilter> eventMessageFilterProvider;
    private final Provider<MetricsServiceV2> metricsServiceV2Provider;

    public BatteryOptimizationSubscriber_Factory(Provider<EventBus> provider, Provider<BatteryOptimization> provider2, Provider<EventMessageFilter> provider3, Provider<MetricsServiceV2> provider4) {
        this.eventBusProvider = provider;
        this.batteryOptimizationProvider = provider2;
        this.eventMessageFilterProvider = provider3;
        this.metricsServiceV2Provider = provider4;
    }

    public static BatteryOptimizationSubscriber_Factory create(Provider<EventBus> provider, Provider<BatteryOptimization> provider2, Provider<EventMessageFilter> provider3, Provider<MetricsServiceV2> provider4) {
        return new BatteryOptimizationSubscriber_Factory(provider, provider2, provider3, provider4);
    }

    public static BatteryOptimizationSubscriber newBatteryOptimizationSubscriber(EventBus eventBus, BatteryOptimization batteryOptimization, EventMessageFilter eventMessageFilter, MetricsServiceV2 metricsServiceV2) {
        return new BatteryOptimizationSubscriber(eventBus, batteryOptimization, eventMessageFilter, metricsServiceV2);
    }

    public static BatteryOptimizationSubscriber provideInstance(Provider<EventBus> provider, Provider<BatteryOptimization> provider2, Provider<EventMessageFilter> provider3, Provider<MetricsServiceV2> provider4) {
        return new BatteryOptimizationSubscriber(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public BatteryOptimizationSubscriber mo10268get() {
        return provideInstance(this.eventBusProvider, this.batteryOptimizationProvider, this.eventMessageFilterProvider, this.metricsServiceV2Provider);
    }
}
