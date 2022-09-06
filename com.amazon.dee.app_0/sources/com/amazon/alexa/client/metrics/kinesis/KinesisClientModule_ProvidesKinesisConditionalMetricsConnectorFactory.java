package com.amazon.alexa.client.metrics.kinesis;

import com.amazon.alexa.client.metrics.core.MetricsConnector;
import com.amazon.alexa.client.metrics.core.MetricsStatusProvider;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class KinesisClientModule_ProvidesKinesisConditionalMetricsConnectorFactory implements Factory<MetricsConnector> {
    private final Provider<KinesisMetricsConnector> connectorProvider;
    private final Provider<MetricsStatusProvider> metricsStatusProvider;
    private final KinesisClientModule module;

    public KinesisClientModule_ProvidesKinesisConditionalMetricsConnectorFactory(KinesisClientModule kinesisClientModule, Provider<KinesisMetricsConnector> provider, Provider<MetricsStatusProvider> provider2) {
        this.module = kinesisClientModule;
        this.connectorProvider = provider;
        this.metricsStatusProvider = provider2;
    }

    public static KinesisClientModule_ProvidesKinesisConditionalMetricsConnectorFactory create(KinesisClientModule kinesisClientModule, Provider<KinesisMetricsConnector> provider, Provider<MetricsStatusProvider> provider2) {
        return new KinesisClientModule_ProvidesKinesisConditionalMetricsConnectorFactory(kinesisClientModule, provider, provider2);
    }

    public static MetricsConnector provideInstance(KinesisClientModule kinesisClientModule, Provider<KinesisMetricsConnector> provider, Provider<MetricsStatusProvider> provider2) {
        return proxyProvidesKinesisConditionalMetricsConnector(kinesisClientModule, DoubleCheck.lazy(provider), provider2.mo10268get());
    }

    public static MetricsConnector proxyProvidesKinesisConditionalMetricsConnector(KinesisClientModule kinesisClientModule, Lazy<KinesisMetricsConnector> lazy, MetricsStatusProvider metricsStatusProvider) {
        return (MetricsConnector) Preconditions.checkNotNull(kinesisClientModule.providesKinesisConditionalMetricsConnector(lazy, metricsStatusProvider), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricsConnector mo10268get() {
        return provideInstance(this.module, this.connectorProvider, this.metricsStatusProvider);
    }
}
