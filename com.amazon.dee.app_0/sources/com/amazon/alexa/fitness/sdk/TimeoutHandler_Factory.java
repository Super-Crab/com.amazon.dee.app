package com.amazon.alexa.fitness.sdk;

import com.amazon.alexa.fitness.configuration.FitnessAccessorySessionMonitorConfigurationProvider;
import com.amazon.alexa.fitness.configuration.FitnessDataHandlerConfigurationProvider;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.metrics.MetricEventFactory;
import com.amazon.alexa.fitness.metrics.MetricEventRecorder;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class TimeoutHandler_Factory implements Factory<TimeoutHandler> {
    private final Provider<FitnessAccessorySessionMonitorConfigurationProvider> configurationProvider;
    private final Provider<FitnessDataHandlerConfigurationProvider> dataConfigurationProvider;
    private final Provider<ILog> logProvider;
    private final Provider<MetricEventFactory> metricEventFactoryProvider;
    private final Provider<MetricEventRecorder> metricEventRecorderProvider;

    public TimeoutHandler_Factory(Provider<FitnessAccessorySessionMonitorConfigurationProvider> provider, Provider<FitnessDataHandlerConfigurationProvider> provider2, Provider<MetricEventFactory> provider3, Provider<MetricEventRecorder> provider4, Provider<ILog> provider5) {
        this.configurationProvider = provider;
        this.dataConfigurationProvider = provider2;
        this.metricEventFactoryProvider = provider3;
        this.metricEventRecorderProvider = provider4;
        this.logProvider = provider5;
    }

    public static TimeoutHandler_Factory create(Provider<FitnessAccessorySessionMonitorConfigurationProvider> provider, Provider<FitnessDataHandlerConfigurationProvider> provider2, Provider<MetricEventFactory> provider3, Provider<MetricEventRecorder> provider4, Provider<ILog> provider5) {
        return new TimeoutHandler_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static TimeoutHandler newTimeoutHandler(FitnessAccessorySessionMonitorConfigurationProvider fitnessAccessorySessionMonitorConfigurationProvider, FitnessDataHandlerConfigurationProvider fitnessDataHandlerConfigurationProvider, MetricEventFactory metricEventFactory, MetricEventRecorder metricEventRecorder, ILog iLog) {
        return new TimeoutHandler(fitnessAccessorySessionMonitorConfigurationProvider, fitnessDataHandlerConfigurationProvider, metricEventFactory, metricEventRecorder, iLog);
    }

    public static TimeoutHandler provideInstance(Provider<FitnessAccessorySessionMonitorConfigurationProvider> provider, Provider<FitnessDataHandlerConfigurationProvider> provider2, Provider<MetricEventFactory> provider3, Provider<MetricEventRecorder> provider4, Provider<ILog> provider5) {
        return new TimeoutHandler(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TimeoutHandler mo10268get() {
        return provideInstance(this.configurationProvider, this.dataConfigurationProvider, this.metricEventFactoryProvider, this.metricEventRecorderProvider, this.logProvider);
    }
}
