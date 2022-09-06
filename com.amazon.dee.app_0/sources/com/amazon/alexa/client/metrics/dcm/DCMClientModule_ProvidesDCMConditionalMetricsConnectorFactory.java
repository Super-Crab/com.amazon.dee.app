package com.amazon.alexa.client.metrics.dcm;

import com.amazon.alexa.client.metrics.core.MetricsConnector;
import com.amazon.alexa.client.metrics.core.MetricsStatusProvider;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class DCMClientModule_ProvidesDCMConditionalMetricsConnectorFactory implements Factory<MetricsConnector> {
    private final Provider<DCMMetricsConnector> connectorProvider;
    private final Provider<MetricsStatusProvider> metricsStatusProvider;
    private final DCMClientModule module;

    public DCMClientModule_ProvidesDCMConditionalMetricsConnectorFactory(DCMClientModule dCMClientModule, Provider<DCMMetricsConnector> provider, Provider<MetricsStatusProvider> provider2) {
        this.module = dCMClientModule;
        this.connectorProvider = provider;
        this.metricsStatusProvider = provider2;
    }

    public static DCMClientModule_ProvidesDCMConditionalMetricsConnectorFactory create(DCMClientModule dCMClientModule, Provider<DCMMetricsConnector> provider, Provider<MetricsStatusProvider> provider2) {
        return new DCMClientModule_ProvidesDCMConditionalMetricsConnectorFactory(dCMClientModule, provider, provider2);
    }

    public static MetricsConnector provideInstance(DCMClientModule dCMClientModule, Provider<DCMMetricsConnector> provider, Provider<MetricsStatusProvider> provider2) {
        return proxyProvidesDCMConditionalMetricsConnector(dCMClientModule, DoubleCheck.lazy(provider), provider2.mo10268get());
    }

    public static MetricsConnector proxyProvidesDCMConditionalMetricsConnector(DCMClientModule dCMClientModule, Lazy<DCMMetricsConnector> lazy, MetricsStatusProvider metricsStatusProvider) {
        return (MetricsConnector) Preconditions.checkNotNull(dCMClientModule.providesDCMConditionalMetricsConnector(lazy, metricsStatusProvider), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricsConnector mo10268get() {
        return provideInstance(this.module, this.connectorProvider, this.metricsStatusProvider);
    }
}
