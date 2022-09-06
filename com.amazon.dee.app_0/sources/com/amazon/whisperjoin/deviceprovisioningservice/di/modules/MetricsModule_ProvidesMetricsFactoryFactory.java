package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import android.content.Context;
import com.amazon.client.metrics.common.MetricsFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisionerClientData;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class MetricsModule_ProvidesMetricsFactoryFactory implements Factory<MetricsFactory> {
    private final Provider<Context> contextProvider;
    private final MetricsModule module;
    private final Provider<ProvisionerClientData> provisionerClientDataProvider;

    public MetricsModule_ProvidesMetricsFactoryFactory(MetricsModule metricsModule, Provider<Context> provider, Provider<ProvisionerClientData> provider2) {
        this.module = metricsModule;
        this.contextProvider = provider;
        this.provisionerClientDataProvider = provider2;
    }

    public static MetricsModule_ProvidesMetricsFactoryFactory create(MetricsModule metricsModule, Provider<Context> provider, Provider<ProvisionerClientData> provider2) {
        return new MetricsModule_ProvidesMetricsFactoryFactory(metricsModule, provider, provider2);
    }

    public static MetricsFactory provideInstance(MetricsModule metricsModule, Provider<Context> provider, Provider<ProvisionerClientData> provider2) {
        return proxyProvidesMetricsFactory(metricsModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static MetricsFactory proxyProvidesMetricsFactory(MetricsModule metricsModule, Context context, ProvisionerClientData provisionerClientData) {
        return (MetricsFactory) Preconditions.checkNotNull(metricsModule.providesMetricsFactory(context, provisionerClientData), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricsFactory mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.provisionerClientDataProvider);
    }
}
