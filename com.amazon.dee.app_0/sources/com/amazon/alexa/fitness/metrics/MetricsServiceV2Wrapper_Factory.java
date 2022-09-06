package com.amazon.alexa.fitness.metrics;

import com.amazon.alexa.fitness.logs.ILog;
import com.dee.app.metrics.MetricsServiceV2;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class MetricsServiceV2Wrapper_Factory implements Factory<MetricsServiceV2Wrapper> {
    private final Provider<ILog> logProvider;
    private final Provider<MetricsServiceV2> metricsServiceV2Provider;

    public MetricsServiceV2Wrapper_Factory(Provider<MetricsServiceV2> provider, Provider<ILog> provider2) {
        this.metricsServiceV2Provider = provider;
        this.logProvider = provider2;
    }

    public static MetricsServiceV2Wrapper_Factory create(Provider<MetricsServiceV2> provider, Provider<ILog> provider2) {
        return new MetricsServiceV2Wrapper_Factory(provider, provider2);
    }

    public static MetricsServiceV2Wrapper newMetricsServiceV2Wrapper(MetricsServiceV2 metricsServiceV2, ILog iLog) {
        return new MetricsServiceV2Wrapper(metricsServiceV2, iLog);
    }

    public static MetricsServiceV2Wrapper provideInstance(Provider<MetricsServiceV2> provider, Provider<ILog> provider2) {
        return new MetricsServiceV2Wrapper(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricsServiceV2Wrapper mo10268get() {
        return provideInstance(this.metricsServiceV2Provider, this.logProvider);
    }
}
