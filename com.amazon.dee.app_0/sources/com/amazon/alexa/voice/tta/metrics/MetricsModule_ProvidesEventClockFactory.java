package com.amazon.alexa.voice.tta.metrics;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class MetricsModule_ProvidesEventClockFactory implements Factory<EventClock> {
    private final MetricsModule module;

    public MetricsModule_ProvidesEventClockFactory(MetricsModule metricsModule) {
        this.module = metricsModule;
    }

    public static MetricsModule_ProvidesEventClockFactory create(MetricsModule metricsModule) {
        return new MetricsModule_ProvidesEventClockFactory(metricsModule);
    }

    public static EventClock provideInstance(MetricsModule metricsModule) {
        return proxyProvidesEventClock(metricsModule);
    }

    public static EventClock proxyProvidesEventClock(MetricsModule metricsModule) {
        return (EventClock) Preconditions.checkNotNull(metricsModule.providesEventClock(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EventClock mo10268get() {
        return provideInstance(this.module);
    }
}
