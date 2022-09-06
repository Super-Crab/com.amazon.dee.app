package com.amazon.alexa.fitness.repository;

import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.metrics.MetricEventFactory;
import com.amazon.alexa.fitness.metrics.MetricEventRecorder;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class RecoveryRepositoryImpl_Factory implements Factory<RecoveryRepositoryImpl> {
    private final Provider<CacheProvider> cacheProvider;
    private final Provider<ILog> logProvider;
    private final Provider<MetricEventFactory> metricEventFactoryProvider;
    private final Provider<MetricEventRecorder> metricEventRecorderProvider;

    public RecoveryRepositoryImpl_Factory(Provider<CacheProvider> provider, Provider<MetricEventFactory> provider2, Provider<MetricEventRecorder> provider3, Provider<ILog> provider4) {
        this.cacheProvider = provider;
        this.metricEventFactoryProvider = provider2;
        this.metricEventRecorderProvider = provider3;
        this.logProvider = provider4;
    }

    public static RecoveryRepositoryImpl_Factory create(Provider<CacheProvider> provider, Provider<MetricEventFactory> provider2, Provider<MetricEventRecorder> provider3, Provider<ILog> provider4) {
        return new RecoveryRepositoryImpl_Factory(provider, provider2, provider3, provider4);
    }

    public static RecoveryRepositoryImpl newRecoveryRepositoryImpl(CacheProvider cacheProvider, MetricEventFactory metricEventFactory, MetricEventRecorder metricEventRecorder, ILog iLog) {
        return new RecoveryRepositoryImpl(cacheProvider, metricEventFactory, metricEventRecorder, iLog);
    }

    public static RecoveryRepositoryImpl provideInstance(Provider<CacheProvider> provider, Provider<MetricEventFactory> provider2, Provider<MetricEventRecorder> provider3, Provider<ILog> provider4) {
        return new RecoveryRepositoryImpl(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RecoveryRepositoryImpl mo10268get() {
        return provideInstance(this.cacheProvider, this.metricEventFactoryProvider, this.metricEventRecorderProvider, this.logProvider);
    }
}
