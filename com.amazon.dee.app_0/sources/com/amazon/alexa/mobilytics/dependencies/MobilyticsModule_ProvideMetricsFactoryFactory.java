package com.amazon.alexa.mobilytics.dependencies;

import com.amazon.alexa.mobilytics.internal.DCMMetricsFactoryProvider;
import com.amazon.client.metrics.common.MetricsFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class MobilyticsModule_ProvideMetricsFactoryFactory implements Factory<MetricsFactory> {
    private final Provider<DCMMetricsFactoryProvider> metricsFactoryProvider;
    private final MobilyticsModule module;

    public MobilyticsModule_ProvideMetricsFactoryFactory(MobilyticsModule mobilyticsModule, Provider<DCMMetricsFactoryProvider> provider) {
        this.module = mobilyticsModule;
        this.metricsFactoryProvider = provider;
    }

    public static MobilyticsModule_ProvideMetricsFactoryFactory create(MobilyticsModule mobilyticsModule, Provider<DCMMetricsFactoryProvider> provider) {
        return new MobilyticsModule_ProvideMetricsFactoryFactory(mobilyticsModule, provider);
    }

    public static MetricsFactory provideInstance(MobilyticsModule mobilyticsModule, Provider<DCMMetricsFactoryProvider> provider) {
        return proxyProvideMetricsFactory(mobilyticsModule, provider.mo10268get());
    }

    public static MetricsFactory proxyProvideMetricsFactory(MobilyticsModule mobilyticsModule, DCMMetricsFactoryProvider dCMMetricsFactoryProvider) {
        return (MetricsFactory) Preconditions.checkNotNull(mobilyticsModule.provideMetricsFactory(dCMMetricsFactoryProvider), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricsFactory mo10268get() {
        return provideInstance(this.module, this.metricsFactoryProvider);
    }
}
