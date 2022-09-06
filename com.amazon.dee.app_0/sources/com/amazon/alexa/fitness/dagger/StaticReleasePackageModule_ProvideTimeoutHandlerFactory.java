package com.amazon.alexa.fitness.dagger;

import com.amazon.alexa.fitness.configuration.FitnessAccessorySessionMonitorConfigurationProvider;
import com.amazon.alexa.fitness.configuration.FitnessDataHandlerConfigurationProvider;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.metrics.MetricEventFactory;
import com.amazon.alexa.fitness.metrics.MetricEventRecorder;
import com.amazon.alexa.fitness.sdk.TimeoutHandler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class StaticReleasePackageModule_ProvideTimeoutHandlerFactory implements Factory<TimeoutHandler> {
    private final Provider<FitnessAccessorySessionMonitorConfigurationProvider> fitnessAccessorySessionMonitorConfigurationProvider;
    private final Provider<FitnessDataHandlerConfigurationProvider> fitnessDataHandlerConfigurationProvider;
    private final Provider<ILog> logProvider;
    private final Provider<MetricEventFactory> metricEventFactoryProvider;
    private final Provider<MetricEventRecorder> metricEventRecorderProvider;
    private final StaticReleasePackageModule module;

    public StaticReleasePackageModule_ProvideTimeoutHandlerFactory(StaticReleasePackageModule staticReleasePackageModule, Provider<FitnessDataHandlerConfigurationProvider> provider, Provider<FitnessAccessorySessionMonitorConfigurationProvider> provider2, Provider<MetricEventFactory> provider3, Provider<MetricEventRecorder> provider4, Provider<ILog> provider5) {
        this.module = staticReleasePackageModule;
        this.fitnessDataHandlerConfigurationProvider = provider;
        this.fitnessAccessorySessionMonitorConfigurationProvider = provider2;
        this.metricEventFactoryProvider = provider3;
        this.metricEventRecorderProvider = provider4;
        this.logProvider = provider5;
    }

    public static StaticReleasePackageModule_ProvideTimeoutHandlerFactory create(StaticReleasePackageModule staticReleasePackageModule, Provider<FitnessDataHandlerConfigurationProvider> provider, Provider<FitnessAccessorySessionMonitorConfigurationProvider> provider2, Provider<MetricEventFactory> provider3, Provider<MetricEventRecorder> provider4, Provider<ILog> provider5) {
        return new StaticReleasePackageModule_ProvideTimeoutHandlerFactory(staticReleasePackageModule, provider, provider2, provider3, provider4, provider5);
    }

    public static TimeoutHandler provideInstance(StaticReleasePackageModule staticReleasePackageModule, Provider<FitnessDataHandlerConfigurationProvider> provider, Provider<FitnessAccessorySessionMonitorConfigurationProvider> provider2, Provider<MetricEventFactory> provider3, Provider<MetricEventRecorder> provider4, Provider<ILog> provider5) {
        return proxyProvideTimeoutHandler(staticReleasePackageModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get());
    }

    public static TimeoutHandler proxyProvideTimeoutHandler(StaticReleasePackageModule staticReleasePackageModule, FitnessDataHandlerConfigurationProvider fitnessDataHandlerConfigurationProvider, FitnessAccessorySessionMonitorConfigurationProvider fitnessAccessorySessionMonitorConfigurationProvider, MetricEventFactory metricEventFactory, MetricEventRecorder metricEventRecorder, ILog iLog) {
        return (TimeoutHandler) Preconditions.checkNotNull(staticReleasePackageModule.provideTimeoutHandler(fitnessDataHandlerConfigurationProvider, fitnessAccessorySessionMonitorConfigurationProvider, metricEventFactory, metricEventRecorder, iLog), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TimeoutHandler mo10268get() {
        return provideInstance(this.module, this.fitnessDataHandlerConfigurationProvider, this.fitnessAccessorySessionMonitorConfigurationProvider, this.metricEventFactoryProvider, this.metricEventRecorderProvider, this.logProvider);
    }
}
