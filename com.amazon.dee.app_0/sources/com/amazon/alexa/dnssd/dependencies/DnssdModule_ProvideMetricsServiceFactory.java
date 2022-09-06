package com.amazon.alexa.dnssd.dependencies;

import com.dee.app.metrics.MetricsServiceV2;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class DnssdModule_ProvideMetricsServiceFactory implements Factory<MetricsServiceV2> {
    private final DnssdModule module;

    public DnssdModule_ProvideMetricsServiceFactory(DnssdModule dnssdModule) {
        this.module = dnssdModule;
    }

    public static DnssdModule_ProvideMetricsServiceFactory create(DnssdModule dnssdModule) {
        return new DnssdModule_ProvideMetricsServiceFactory(dnssdModule);
    }

    public static MetricsServiceV2 provideInstance(DnssdModule dnssdModule) {
        return proxyProvideMetricsService(dnssdModule);
    }

    public static MetricsServiceV2 proxyProvideMetricsService(DnssdModule dnssdModule) {
        return (MetricsServiceV2) Preconditions.checkNotNull(dnssdModule.provideMetricsService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricsServiceV2 mo10268get() {
        return provideInstance(this.module);
    }
}
