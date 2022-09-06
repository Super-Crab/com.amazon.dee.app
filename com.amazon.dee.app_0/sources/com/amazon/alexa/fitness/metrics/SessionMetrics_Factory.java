package com.amazon.alexa.fitness.metrics;

import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.sdk.MetricsAggregatorRecovery;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class SessionMetrics_Factory implements Factory<SessionMetrics> {
    private final Provider<ILog> logProvider;
    private final Provider<MetricsAggregator> metricsAggregatorProvider;
    private final Provider<MetricsAggregatorRecovery> metricsAggregatorRecoveryProvider;

    public SessionMetrics_Factory(Provider<MetricsAggregator> provider, Provider<MetricsAggregatorRecovery> provider2, Provider<ILog> provider3) {
        this.metricsAggregatorProvider = provider;
        this.metricsAggregatorRecoveryProvider = provider2;
        this.logProvider = provider3;
    }

    public static SessionMetrics_Factory create(Provider<MetricsAggregator> provider, Provider<MetricsAggregatorRecovery> provider2, Provider<ILog> provider3) {
        return new SessionMetrics_Factory(provider, provider2, provider3);
    }

    public static SessionMetrics newSessionMetrics(MetricsAggregator metricsAggregator, MetricsAggregatorRecovery metricsAggregatorRecovery, ILog iLog) {
        return new SessionMetrics(metricsAggregator, metricsAggregatorRecovery, iLog);
    }

    public static SessionMetrics provideInstance(Provider<MetricsAggregator> provider, Provider<MetricsAggregatorRecovery> provider2, Provider<ILog> provider3) {
        return new SessionMetrics(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SessionMetrics mo10268get() {
        return provideInstance(this.metricsAggregatorProvider, this.metricsAggregatorRecoveryProvider, this.logProvider);
    }
}
