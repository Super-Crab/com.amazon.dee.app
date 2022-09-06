package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.client.metrics.common.MetricsFactory;
import com.amazon.dee.app.services.metrics.DCMMetricsConnector;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class MetricsModule_ProvideDCMMetricsConnectorFactory implements Factory<DCMMetricsConnector> {
    private final Provider<Context> contextProvider;
    private final Provider<EnvironmentService> environmentServiceProvider;
    private final Provider<MetricsFactory> metricsFactoryProvider;
    private final MetricsModule module;

    public MetricsModule_ProvideDCMMetricsConnectorFactory(MetricsModule metricsModule, Provider<Context> provider, Provider<EnvironmentService> provider2, Provider<MetricsFactory> provider3) {
        this.module = metricsModule;
        this.contextProvider = provider;
        this.environmentServiceProvider = provider2;
        this.metricsFactoryProvider = provider3;
    }

    public static MetricsModule_ProvideDCMMetricsConnectorFactory create(MetricsModule metricsModule, Provider<Context> provider, Provider<EnvironmentService> provider2, Provider<MetricsFactory> provider3) {
        return new MetricsModule_ProvideDCMMetricsConnectorFactory(metricsModule, provider, provider2, provider3);
    }

    public static DCMMetricsConnector provideInstance(MetricsModule metricsModule, Provider<Context> provider, Provider<EnvironmentService> provider2, Provider<MetricsFactory> provider3) {
        return proxyProvideDCMMetricsConnector(metricsModule, provider.mo10268get(), provider2.mo10268get(), DoubleCheck.lazy(provider3));
    }

    public static DCMMetricsConnector proxyProvideDCMMetricsConnector(MetricsModule metricsModule, Context context, EnvironmentService environmentService, Lazy<MetricsFactory> lazy) {
        return (DCMMetricsConnector) Preconditions.checkNotNull(metricsModule.provideDCMMetricsConnector(context, environmentService, lazy), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DCMMetricsConnector mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.environmentServiceProvider, this.metricsFactoryProvider);
    }
}
