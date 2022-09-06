package com.amazon.alexa.fitness.metrics;

import com.amazon.alexa.fitness.logs.ILog;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class DefaultMetricEventFactory_Factory implements Factory<DefaultMetricEventFactory> {
    private final Provider<ILog> logProvider;
    private final Provider<MonotonicallyIncreasingTimeSource> timeSourceProvider;

    public DefaultMetricEventFactory_Factory(Provider<MonotonicallyIncreasingTimeSource> provider, Provider<ILog> provider2) {
        this.timeSourceProvider = provider;
        this.logProvider = provider2;
    }

    public static DefaultMetricEventFactory_Factory create(Provider<MonotonicallyIncreasingTimeSource> provider, Provider<ILog> provider2) {
        return new DefaultMetricEventFactory_Factory(provider, provider2);
    }

    public static DefaultMetricEventFactory newDefaultMetricEventFactory(MonotonicallyIncreasingTimeSource monotonicallyIncreasingTimeSource, ILog iLog) {
        return new DefaultMetricEventFactory(monotonicallyIncreasingTimeSource, iLog);
    }

    public static DefaultMetricEventFactory provideInstance(Provider<MonotonicallyIncreasingTimeSource> provider, Provider<ILog> provider2) {
        return new DefaultMetricEventFactory(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DefaultMetricEventFactory mo10268get() {
        return provideInstance(this.timeSourceProvider, this.logProvider);
    }
}
