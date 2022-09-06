package com.amazon.alexa.fitness.service;

import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.metrics.MetricEventRecorder;
import com.amazon.alexa.fitness.metrics.MetricsAggregator;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class InstrumentedAlexaServicesConnectionImpl_Factory implements Factory<InstrumentedAlexaServicesConnectionImpl> {
    private final Provider<AlexaServicesConnection> alexaServicesConnectionProvider;
    private final Provider<ILog> logProvider;
    private final Provider<MetricEventRecorder> metricEventRecorderProvider;
    private final Provider<MetricsAggregator> metricsAggregatorProvider;

    public InstrumentedAlexaServicesConnectionImpl_Factory(Provider<AlexaServicesConnection> provider, Provider<MetricsAggregator> provider2, Provider<MetricEventRecorder> provider3, Provider<ILog> provider4) {
        this.alexaServicesConnectionProvider = provider;
        this.metricsAggregatorProvider = provider2;
        this.metricEventRecorderProvider = provider3;
        this.logProvider = provider4;
    }

    public static InstrumentedAlexaServicesConnectionImpl_Factory create(Provider<AlexaServicesConnection> provider, Provider<MetricsAggregator> provider2, Provider<MetricEventRecorder> provider3, Provider<ILog> provider4) {
        return new InstrumentedAlexaServicesConnectionImpl_Factory(provider, provider2, provider3, provider4);
    }

    public static InstrumentedAlexaServicesConnectionImpl newInstrumentedAlexaServicesConnectionImpl(AlexaServicesConnection alexaServicesConnection, MetricsAggregator metricsAggregator, MetricEventRecorder metricEventRecorder, ILog iLog) {
        return new InstrumentedAlexaServicesConnectionImpl(alexaServicesConnection, metricsAggregator, metricEventRecorder, iLog);
    }

    public static InstrumentedAlexaServicesConnectionImpl provideInstance(Provider<AlexaServicesConnection> provider, Provider<MetricsAggregator> provider2, Provider<MetricEventRecorder> provider3, Provider<ILog> provider4) {
        return new InstrumentedAlexaServicesConnectionImpl(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public InstrumentedAlexaServicesConnectionImpl mo10268get() {
        return provideInstance(this.alexaServicesConnectionProvider, this.metricsAggregatorProvider, this.metricEventRecorderProvider, this.logProvider);
    }
}
