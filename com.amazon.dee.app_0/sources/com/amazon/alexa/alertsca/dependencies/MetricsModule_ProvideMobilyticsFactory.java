package com.amazon.alexa.alertsca.dependencies;

import com.amazon.alexa.mobilytics.Mobilytics;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes6.dex */
public final class MetricsModule_ProvideMobilyticsFactory implements Factory<Mobilytics> {
    private final MetricsModule module;

    public MetricsModule_ProvideMobilyticsFactory(MetricsModule metricsModule) {
        this.module = metricsModule;
    }

    public static MetricsModule_ProvideMobilyticsFactory create(MetricsModule metricsModule) {
        return new MetricsModule_ProvideMobilyticsFactory(metricsModule);
    }

    public static Mobilytics provideInstance(MetricsModule metricsModule) {
        return proxyProvideMobilytics(metricsModule);
    }

    public static Mobilytics proxyProvideMobilytics(MetricsModule metricsModule) {
        return (Mobilytics) Preconditions.checkNotNull(metricsModule.provideMobilytics(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Mobilytics mo10268get() {
        return provideInstance(this.module);
    }
}
