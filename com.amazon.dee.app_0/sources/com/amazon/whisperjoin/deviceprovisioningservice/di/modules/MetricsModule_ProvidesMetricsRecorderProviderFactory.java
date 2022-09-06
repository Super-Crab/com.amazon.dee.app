package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.client.metrics.common.MetricsFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.identity.MapAuthenticationProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisionerClientData;
import com.amazon.whisperjoin.metrics.MetricsRecorderProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class MetricsModule_ProvidesMetricsRecorderProviderFactory implements Factory<MetricsRecorderProvider> {
    private final Provider<MapAuthenticationProvider> mapAuthenticationProvider;
    private final Provider<MetricsFactory> metricsFactoryProvider;
    private final MetricsModule module;
    private final Provider<ProvisionerClientData> provisionerClientDataProvider;

    public MetricsModule_ProvidesMetricsRecorderProviderFactory(MetricsModule metricsModule, Provider<MetricsFactory> provider, Provider<MapAuthenticationProvider> provider2, Provider<ProvisionerClientData> provider3) {
        this.module = metricsModule;
        this.metricsFactoryProvider = provider;
        this.mapAuthenticationProvider = provider2;
        this.provisionerClientDataProvider = provider3;
    }

    public static MetricsModule_ProvidesMetricsRecorderProviderFactory create(MetricsModule metricsModule, Provider<MetricsFactory> provider, Provider<MapAuthenticationProvider> provider2, Provider<ProvisionerClientData> provider3) {
        return new MetricsModule_ProvidesMetricsRecorderProviderFactory(metricsModule, provider, provider2, provider3);
    }

    public static MetricsRecorderProvider provideInstance(MetricsModule metricsModule, Provider<MetricsFactory> provider, Provider<MapAuthenticationProvider> provider2, Provider<ProvisionerClientData> provider3) {
        return proxyProvidesMetricsRecorderProvider(metricsModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static MetricsRecorderProvider proxyProvidesMetricsRecorderProvider(MetricsModule metricsModule, MetricsFactory metricsFactory, MapAuthenticationProvider mapAuthenticationProvider, ProvisionerClientData provisionerClientData) {
        return (MetricsRecorderProvider) Preconditions.checkNotNull(metricsModule.providesMetricsRecorderProvider(metricsFactory, mapAuthenticationProvider, provisionerClientData), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricsRecorderProvider mo10268get() {
        return provideInstance(this.module, this.metricsFactoryProvider, this.mapAuthenticationProvider, this.provisionerClientDataProvider);
    }
}
