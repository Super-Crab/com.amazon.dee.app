package com.amazon.alexa.fitness.util;

import com.amazon.alexa.fitness.metrics.MetricsAggregator;
import com.amazon.alexa.fitness.sdk.MetricsAggregatorRecovery;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class ActivityLifecycleObserver_Factory implements Factory<ActivityLifecycleObserver> {
    private final Provider<MetricsAggregator> metricsAggregatorProvider;
    private final Provider<MetricsAggregatorRecovery> metricsAggregatorRecoveryProvider;

    public ActivityLifecycleObserver_Factory(Provider<MetricsAggregatorRecovery> provider, Provider<MetricsAggregator> provider2) {
        this.metricsAggregatorRecoveryProvider = provider;
        this.metricsAggregatorProvider = provider2;
    }

    public static ActivityLifecycleObserver_Factory create(Provider<MetricsAggregatorRecovery> provider, Provider<MetricsAggregator> provider2) {
        return new ActivityLifecycleObserver_Factory(provider, provider2);
    }

    public static ActivityLifecycleObserver newActivityLifecycleObserver(MetricsAggregatorRecovery metricsAggregatorRecovery, MetricsAggregator metricsAggregator) {
        return new ActivityLifecycleObserver(metricsAggregatorRecovery, metricsAggregator);
    }

    public static ActivityLifecycleObserver provideInstance(Provider<MetricsAggregatorRecovery> provider, Provider<MetricsAggregator> provider2) {
        return new ActivityLifecycleObserver(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ActivityLifecycleObserver mo10268get() {
        return provideInstance(this.metricsAggregatorRecoveryProvider, this.metricsAggregatorProvider);
    }
}
