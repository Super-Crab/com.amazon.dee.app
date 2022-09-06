package com.amazon.alexa.dnssd.dependencies;

import com.amazon.alexa.dnssd.util.Metrics;
import com.dee.app.metrics.MetricsServiceV2;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class DnssdModule_ProvideMetricsFactory implements Factory<Metrics> {
    private final Provider<MetricsServiceV2> metricsServiceV2Provider;
    private final DnssdModule module;

    public DnssdModule_ProvideMetricsFactory(DnssdModule dnssdModule, Provider<MetricsServiceV2> provider) {
        this.module = dnssdModule;
        this.metricsServiceV2Provider = provider;
    }

    public static DnssdModule_ProvideMetricsFactory create(DnssdModule dnssdModule, Provider<MetricsServiceV2> provider) {
        return new DnssdModule_ProvideMetricsFactory(dnssdModule, provider);
    }

    public static Metrics provideInstance(DnssdModule dnssdModule, Provider<MetricsServiceV2> provider) {
        return proxyProvideMetrics(dnssdModule, provider.mo10268get());
    }

    public static Metrics proxyProvideMetrics(DnssdModule dnssdModule, MetricsServiceV2 metricsServiceV2) {
        return (Metrics) Preconditions.checkNotNull(dnssdModule.provideMetrics(metricsServiceV2), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Metrics mo10268get() {
        return provideInstance(this.module, this.metricsServiceV2Provider);
    }
}
