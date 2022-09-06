package com.amazon.alexa.client.metrics.mobilytics;

import com.amazon.alexa.client.metrics.core.MetricsConnector;
import com.amazon.alexa.client.metrics.core.MetricsStatusProvider;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class MobilyticsClientModule_ProvidesMobilyticsConditionalMetricsConnectorFactory implements Factory<MetricsConnector> {
    private final Provider<MobilyticsMetricsConnector> connectorProvider;
    private final Provider<MetricsStatusProvider> metricsStatusProvider;
    private final MobilyticsClientModule module;

    public MobilyticsClientModule_ProvidesMobilyticsConditionalMetricsConnectorFactory(MobilyticsClientModule mobilyticsClientModule, Provider<MobilyticsMetricsConnector> provider, Provider<MetricsStatusProvider> provider2) {
        this.module = mobilyticsClientModule;
        this.connectorProvider = provider;
        this.metricsStatusProvider = provider2;
    }

    public static MobilyticsClientModule_ProvidesMobilyticsConditionalMetricsConnectorFactory create(MobilyticsClientModule mobilyticsClientModule, Provider<MobilyticsMetricsConnector> provider, Provider<MetricsStatusProvider> provider2) {
        return new MobilyticsClientModule_ProvidesMobilyticsConditionalMetricsConnectorFactory(mobilyticsClientModule, provider, provider2);
    }

    public static MetricsConnector provideInstance(MobilyticsClientModule mobilyticsClientModule, Provider<MobilyticsMetricsConnector> provider, Provider<MetricsStatusProvider> provider2) {
        return proxyProvidesMobilyticsConditionalMetricsConnector(mobilyticsClientModule, DoubleCheck.lazy(provider), provider2.mo10268get());
    }

    public static MetricsConnector proxyProvidesMobilyticsConditionalMetricsConnector(MobilyticsClientModule mobilyticsClientModule, Lazy<MobilyticsMetricsConnector> lazy, MetricsStatusProvider metricsStatusProvider) {
        return (MetricsConnector) Preconditions.checkNotNull(mobilyticsClientModule.providesMobilyticsConditionalMetricsConnector(lazy, metricsStatusProvider), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricsConnector mo10268get() {
        return provideInstance(this.module, this.connectorProvider, this.metricsStatusProvider);
    }
}
