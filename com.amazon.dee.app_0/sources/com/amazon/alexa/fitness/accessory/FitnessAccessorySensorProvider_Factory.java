package com.amazon.alexa.fitness.accessory;

import com.amazon.alexa.accessoryclient.client.accessories.Accessories;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.metrics.MetricEventFactory;
import com.amazon.alexa.fitness.metrics.MetricEventRecorder;
import com.amazon.alexa.fitness.metrics.MetricsAggregator;
import com.amazon.alexa.fitness.sdk.AfxMessageProcessor;
import com.amazon.alexa.fitness.session.FitnessDataHandler;
import com.amazon.alexa.mobilytics.Mobilytics;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class FitnessAccessorySensorProvider_Factory implements Factory<FitnessAccessorySensorProvider> {
    private final Provider<Accessories> accessoriesProvider;
    private final Provider<AfxMessageProcessor> afxMessageProcessorProvider;
    private final Provider<FitnessAccessoryCommandHandler> fitnessAccessoryCommandHandlerProvider;
    private final Provider<FitnessAccessorySessionMonitor> fitnessAccessorySessionMonitorProvider;
    private final Provider<FitnessDataHandler> fitnessDataHandlerProvider;
    private final Provider<ILog> logProvider;
    private final Provider<MetricEventFactory> metricEventFactoryProvider;
    private final Provider<MetricEventRecorder> metricEventRecorderProvider;
    private final Provider<MetricsAggregator> metricsAggregatorProvider;
    private final Provider<Mobilytics> metricsProvider;

    public FitnessAccessorySensorProvider_Factory(Provider<FitnessAccessorySessionMonitor> provider, Provider<FitnessAccessoryCommandHandler> provider2, Provider<AfxMessageProcessor> provider3, Provider<Mobilytics> provider4, Provider<MetricEventRecorder> provider5, Provider<MetricEventFactory> provider6, Provider<FitnessDataHandler> provider7, Provider<MetricsAggregator> provider8, Provider<ILog> provider9, Provider<Accessories> provider10) {
        this.fitnessAccessorySessionMonitorProvider = provider;
        this.fitnessAccessoryCommandHandlerProvider = provider2;
        this.afxMessageProcessorProvider = provider3;
        this.metricsProvider = provider4;
        this.metricEventRecorderProvider = provider5;
        this.metricEventFactoryProvider = provider6;
        this.fitnessDataHandlerProvider = provider7;
        this.metricsAggregatorProvider = provider8;
        this.logProvider = provider9;
        this.accessoriesProvider = provider10;
    }

    public static FitnessAccessorySensorProvider_Factory create(Provider<FitnessAccessorySessionMonitor> provider, Provider<FitnessAccessoryCommandHandler> provider2, Provider<AfxMessageProcessor> provider3, Provider<Mobilytics> provider4, Provider<MetricEventRecorder> provider5, Provider<MetricEventFactory> provider6, Provider<FitnessDataHandler> provider7, Provider<MetricsAggregator> provider8, Provider<ILog> provider9, Provider<Accessories> provider10) {
        return new FitnessAccessorySensorProvider_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10);
    }

    public static FitnessAccessorySensorProvider newFitnessAccessorySensorProvider(FitnessAccessorySessionMonitor fitnessAccessorySessionMonitor, Lazy<FitnessAccessoryCommandHandler> lazy, AfxMessageProcessor afxMessageProcessor, Mobilytics mobilytics, MetricEventRecorder metricEventRecorder, MetricEventFactory metricEventFactory, FitnessDataHandler fitnessDataHandler, MetricsAggregator metricsAggregator, ILog iLog, Accessories accessories) {
        return new FitnessAccessorySensorProvider(fitnessAccessorySessionMonitor, lazy, afxMessageProcessor, mobilytics, metricEventRecorder, metricEventFactory, fitnessDataHandler, metricsAggregator, iLog, accessories);
    }

    public static FitnessAccessorySensorProvider provideInstance(Provider<FitnessAccessorySessionMonitor> provider, Provider<FitnessAccessoryCommandHandler> provider2, Provider<AfxMessageProcessor> provider3, Provider<Mobilytics> provider4, Provider<MetricEventRecorder> provider5, Provider<MetricEventFactory> provider6, Provider<FitnessDataHandler> provider7, Provider<MetricsAggregator> provider8, Provider<ILog> provider9, Provider<Accessories> provider10) {
        return new FitnessAccessorySensorProvider(provider.mo10268get(), DoubleCheck.lazy(provider2), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), provider8.mo10268get(), provider9.mo10268get(), provider10.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FitnessAccessorySensorProvider mo10268get() {
        return provideInstance(this.fitnessAccessorySessionMonitorProvider, this.fitnessAccessoryCommandHandlerProvider, this.afxMessageProcessorProvider, this.metricsProvider, this.metricEventRecorderProvider, this.metricEventFactoryProvider, this.fitnessDataHandlerProvider, this.metricsAggregatorProvider, this.logProvider, this.accessoriesProvider);
    }
}
