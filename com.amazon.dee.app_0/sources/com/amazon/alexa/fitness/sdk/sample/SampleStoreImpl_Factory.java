package com.amazon.alexa.fitness.sdk.sample;

import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.metrics.MetricsAggregator;
import com.amazon.alexa.fitness.sdk.database.FitnessDatabase;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class SampleStoreImpl_Factory implements Factory<SampleStoreImpl> {
    private final Provider<ILog> logProvider;
    private final Provider<MetricsAggregator> metricsAggregatorProvider;
    private final Provider<FitnessDatabase> sampleRepositoryProvider;

    public SampleStoreImpl_Factory(Provider<FitnessDatabase> provider, Provider<MetricsAggregator> provider2, Provider<ILog> provider3) {
        this.sampleRepositoryProvider = provider;
        this.metricsAggregatorProvider = provider2;
        this.logProvider = provider3;
    }

    public static SampleStoreImpl_Factory create(Provider<FitnessDatabase> provider, Provider<MetricsAggregator> provider2, Provider<ILog> provider3) {
        return new SampleStoreImpl_Factory(provider, provider2, provider3);
    }

    public static SampleStoreImpl newSampleStoreImpl(Provider<FitnessDatabase> provider, MetricsAggregator metricsAggregator, ILog iLog) {
        return new SampleStoreImpl(provider, metricsAggregator, iLog);
    }

    public static SampleStoreImpl provideInstance(Provider<FitnessDatabase> provider, Provider<MetricsAggregator> provider2, Provider<ILog> provider3) {
        return new SampleStoreImpl(provider, provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SampleStoreImpl mo10268get() {
        return provideInstance(this.sampleRepositoryProvider, this.metricsAggregatorProvider, this.logProvider);
    }
}
