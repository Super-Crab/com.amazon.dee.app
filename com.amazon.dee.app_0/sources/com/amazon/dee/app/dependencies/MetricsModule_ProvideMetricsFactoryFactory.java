package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.client.metrics.common.MetricsFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class MetricsModule_ProvideMetricsFactoryFactory implements Factory<MetricsFactory> {
    private final Provider<Context> contextProvider;
    private final MetricsModule module;

    public MetricsModule_ProvideMetricsFactoryFactory(MetricsModule metricsModule, Provider<Context> provider) {
        this.module = metricsModule;
        this.contextProvider = provider;
    }

    public static MetricsModule_ProvideMetricsFactoryFactory create(MetricsModule metricsModule, Provider<Context> provider) {
        return new MetricsModule_ProvideMetricsFactoryFactory(metricsModule, provider);
    }

    public static MetricsFactory provideInstance(MetricsModule metricsModule, Provider<Context> provider) {
        return proxyProvideMetricsFactory(metricsModule, provider.mo10268get());
    }

    public static MetricsFactory proxyProvideMetricsFactory(MetricsModule metricsModule, Context context) {
        return (MetricsFactory) Preconditions.checkNotNull(metricsModule.provideMetricsFactory(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricsFactory mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
