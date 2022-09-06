package com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig;

import android.content.Context;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class MetricsReporter_Factory implements Factory<MetricsReporter> {
    private final Provider<MetricsBuilderProvider> arg0Provider;
    private final Provider<Context> arg1Provider;

    public MetricsReporter_Factory(Provider<MetricsBuilderProvider> provider, Provider<Context> provider2) {
        this.arg0Provider = provider;
        this.arg1Provider = provider2;
    }

    public static MetricsReporter_Factory create(Provider<MetricsBuilderProvider> provider, Provider<Context> provider2) {
        return new MetricsReporter_Factory(provider, provider2);
    }

    public static MetricsReporter newMetricsReporter(MetricsBuilderProvider metricsBuilderProvider, Context context) {
        return new MetricsReporter(metricsBuilderProvider, context);
    }

    public static MetricsReporter provideInstance(Provider<MetricsBuilderProvider> provider, Provider<Context> provider2) {
        return new MetricsReporter(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricsReporter mo10268get() {
        return provideInstance(this.arg0Provider, this.arg1Provider);
    }
}
