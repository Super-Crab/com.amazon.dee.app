package com.amazon.alexa.fitness.accessory;

import com.amazon.alexa.accessoryclient.client.accessories.Accessories;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.metrics.MetricEventFactory;
import com.amazon.alexa.fitness.metrics.MetricEventRecorder;
import com.amazon.alexa.fitness.metrics.MetricsAggregator;
import com.amazon.alexa.fitness.sdk.AfxMessageProcessor;
import com.amazon.alexa.fitness.util.DisposableManagerFactory;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class FitnessAccessorySessionMonitorImpl_Factory implements Factory<FitnessAccessorySessionMonitorImpl> {
    private final Provider<Accessories> accessoriesProvider;
    private final Provider<AfxMessageProcessor> afxMessageProcessorProvider;
    private final Provider<DisposableManagerFactory> disposableManagerFactoryProvider;
    private final Provider<ILog> logProvider;
    private final Provider<MetricEventFactory> metricEventFactoryProvider;
    private final Provider<MetricEventRecorder> metricEventRecorderProvider;
    private final Provider<MetricsAggregator> metricsAggregatorProvider;

    public FitnessAccessorySessionMonitorImpl_Factory(Provider<DisposableManagerFactory> provider, Provider<MetricEventFactory> provider2, Provider<MetricEventRecorder> provider3, Provider<AfxMessageProcessor> provider4, Provider<MetricsAggregator> provider5, Provider<ILog> provider6, Provider<Accessories> provider7) {
        this.disposableManagerFactoryProvider = provider;
        this.metricEventFactoryProvider = provider2;
        this.metricEventRecorderProvider = provider3;
        this.afxMessageProcessorProvider = provider4;
        this.metricsAggregatorProvider = provider5;
        this.logProvider = provider6;
        this.accessoriesProvider = provider7;
    }

    public static FitnessAccessorySessionMonitorImpl_Factory create(Provider<DisposableManagerFactory> provider, Provider<MetricEventFactory> provider2, Provider<MetricEventRecorder> provider3, Provider<AfxMessageProcessor> provider4, Provider<MetricsAggregator> provider5, Provider<ILog> provider6, Provider<Accessories> provider7) {
        return new FitnessAccessorySessionMonitorImpl_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static FitnessAccessorySessionMonitorImpl newFitnessAccessorySessionMonitorImpl(DisposableManagerFactory disposableManagerFactory, MetricEventFactory metricEventFactory, MetricEventRecorder metricEventRecorder, AfxMessageProcessor afxMessageProcessor, MetricsAggregator metricsAggregator, ILog iLog, Accessories accessories) {
        return new FitnessAccessorySessionMonitorImpl(disposableManagerFactory, metricEventFactory, metricEventRecorder, afxMessageProcessor, metricsAggregator, iLog, accessories);
    }

    public static FitnessAccessorySessionMonitorImpl provideInstance(Provider<DisposableManagerFactory> provider, Provider<MetricEventFactory> provider2, Provider<MetricEventRecorder> provider3, Provider<AfxMessageProcessor> provider4, Provider<MetricsAggregator> provider5, Provider<ILog> provider6, Provider<Accessories> provider7) {
        return new FitnessAccessorySessionMonitorImpl(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FitnessAccessorySessionMonitorImpl mo10268get() {
        return provideInstance(this.disposableManagerFactoryProvider, this.metricEventFactoryProvider, this.metricEventRecorderProvider, this.afxMessageProcessorProvider, this.metricsAggregatorProvider, this.logProvider, this.accessoriesProvider);
    }
}
