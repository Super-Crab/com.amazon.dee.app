package com.amazon.alexa.hho.dependency;

import com.amazon.alexa.hho.metrics.HHOMetricsService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class HHOModule_ProvideMetricsFactory implements Factory<HHOMetricsService> {
    private final Provider<LazyComponent<Mobilytics>> mobilyticsProvider;
    private final HHOModule module;

    public HHOModule_ProvideMetricsFactory(HHOModule hHOModule, Provider<LazyComponent<Mobilytics>> provider) {
        this.module = hHOModule;
        this.mobilyticsProvider = provider;
    }

    public static HHOModule_ProvideMetricsFactory create(HHOModule hHOModule, Provider<LazyComponent<Mobilytics>> provider) {
        return new HHOModule_ProvideMetricsFactory(hHOModule, provider);
    }

    public static HHOMetricsService provideInstance(HHOModule hHOModule, Provider<LazyComponent<Mobilytics>> provider) {
        return proxyProvideMetrics(hHOModule, provider.mo10268get());
    }

    public static HHOMetricsService proxyProvideMetrics(HHOModule hHOModule, LazyComponent<Mobilytics> lazyComponent) {
        return (HHOMetricsService) Preconditions.checkNotNull(hHOModule.provideMetrics(lazyComponent), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public HHOMetricsService mo10268get() {
        return provideInstance(this.module, this.mobilyticsProvider);
    }
}
