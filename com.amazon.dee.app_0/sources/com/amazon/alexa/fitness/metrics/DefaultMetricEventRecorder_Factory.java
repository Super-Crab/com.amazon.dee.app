package com.amazon.alexa.fitness.metrics;

import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class DefaultMetricEventRecorder_Factory implements Factory<DefaultMetricEventRecorder> {
    private final Provider<MetricService> metricServiceProvider;

    public DefaultMetricEventRecorder_Factory(Provider<MetricService> provider) {
        this.metricServiceProvider = provider;
    }

    public static DefaultMetricEventRecorder_Factory create(Provider<MetricService> provider) {
        return new DefaultMetricEventRecorder_Factory(provider);
    }

    public static DefaultMetricEventRecorder newDefaultMetricEventRecorder(MetricService metricService) {
        return new DefaultMetricEventRecorder(metricService);
    }

    public static DefaultMetricEventRecorder provideInstance(Provider<MetricService> provider) {
        return new DefaultMetricEventRecorder(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DefaultMetricEventRecorder mo10268get() {
        return provideInstance(this.metricServiceProvider);
    }
}
