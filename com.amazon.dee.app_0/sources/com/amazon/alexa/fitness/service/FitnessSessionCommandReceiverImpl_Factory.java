package com.amazon.alexa.fitness.service;

import com.amazon.alexa.fitness.api.afx.FeatureService;
import com.amazon.alexa.fitness.configuration.FitnessSessionCommandReceiverConfigurationProvider;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.metrics.MetricEventFactory;
import com.amazon.alexa.fitness.metrics.MetricEventRecorder;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class FitnessSessionCommandReceiverImpl_Factory implements Factory<FitnessSessionCommandReceiverImpl> {
    private final Provider<FitnessSessionCommandReceiverConfigurationProvider> configurationProvider;
    private final Provider<FeatureService> featureServiceProvider;
    private final Provider<ILog> logProvider;
    private final Provider<MetricEventFactory> metricEventFactoryProvider;
    private final Provider<MetricEventRecorder> metricEventRecorderProvider;

    public FitnessSessionCommandReceiverImpl_Factory(Provider<FitnessSessionCommandReceiverConfigurationProvider> provider, Provider<FeatureService> provider2, Provider<MetricEventFactory> provider3, Provider<MetricEventRecorder> provider4, Provider<ILog> provider5) {
        this.configurationProvider = provider;
        this.featureServiceProvider = provider2;
        this.metricEventFactoryProvider = provider3;
        this.metricEventRecorderProvider = provider4;
        this.logProvider = provider5;
    }

    public static FitnessSessionCommandReceiverImpl_Factory create(Provider<FitnessSessionCommandReceiverConfigurationProvider> provider, Provider<FeatureService> provider2, Provider<MetricEventFactory> provider3, Provider<MetricEventRecorder> provider4, Provider<ILog> provider5) {
        return new FitnessSessionCommandReceiverImpl_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static FitnessSessionCommandReceiverImpl newFitnessSessionCommandReceiverImpl(FitnessSessionCommandReceiverConfigurationProvider fitnessSessionCommandReceiverConfigurationProvider, FeatureService featureService, MetricEventFactory metricEventFactory, MetricEventRecorder metricEventRecorder, ILog iLog) {
        return new FitnessSessionCommandReceiverImpl(fitnessSessionCommandReceiverConfigurationProvider, featureService, metricEventFactory, metricEventRecorder, iLog);
    }

    public static FitnessSessionCommandReceiverImpl provideInstance(Provider<FitnessSessionCommandReceiverConfigurationProvider> provider, Provider<FeatureService> provider2, Provider<MetricEventFactory> provider3, Provider<MetricEventRecorder> provider4, Provider<ILog> provider5) {
        return new FitnessSessionCommandReceiverImpl(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FitnessSessionCommandReceiverImpl mo10268get() {
        return provideInstance(this.configurationProvider, this.featureServiceProvider, this.metricEventFactoryProvider, this.metricEventRecorderProvider, this.logProvider);
    }
}
