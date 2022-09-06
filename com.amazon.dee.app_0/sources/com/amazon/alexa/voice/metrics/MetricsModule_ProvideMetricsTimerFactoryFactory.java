package com.amazon.alexa.voice.metrics;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class MetricsModule_ProvideMetricsTimerFactoryFactory implements Factory<MetricsTimerFactory> {
    private static final MetricsModule_ProvideMetricsTimerFactoryFactory INSTANCE = new MetricsModule_ProvideMetricsTimerFactoryFactory();

    public static MetricsModule_ProvideMetricsTimerFactoryFactory create() {
        return INSTANCE;
    }

    public static MetricsTimerFactory provideInstance() {
        return proxyProvideMetricsTimerFactory();
    }

    public static MetricsTimerFactory proxyProvideMetricsTimerFactory() {
        return (MetricsTimerFactory) Preconditions.checkNotNull(MetricsModule.provideMetricsTimerFactory(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricsTimerFactory mo10268get() {
        return provideInstance();
    }
}
