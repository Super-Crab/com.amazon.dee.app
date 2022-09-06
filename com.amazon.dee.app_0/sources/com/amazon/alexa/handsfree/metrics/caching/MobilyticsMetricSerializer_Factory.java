package com.amazon.alexa.handsfree.metrics.caching;

import android.content.Context;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class MobilyticsMetricSerializer_Factory implements Factory<MobilyticsMetricSerializer> {
    private final Provider<Context> contextProvider;
    private final Provider<MetricsBuilderProvider> metricsBuilderProviderLazyProvider;

    public MobilyticsMetricSerializer_Factory(Provider<Context> provider, Provider<MetricsBuilderProvider> provider2) {
        this.contextProvider = provider;
        this.metricsBuilderProviderLazyProvider = provider2;
    }

    public static MobilyticsMetricSerializer_Factory create(Provider<Context> provider, Provider<MetricsBuilderProvider> provider2) {
        return new MobilyticsMetricSerializer_Factory(provider, provider2);
    }

    public static MobilyticsMetricSerializer newMobilyticsMetricSerializer(Context context, Lazy<MetricsBuilderProvider> lazy) {
        return new MobilyticsMetricSerializer(context, lazy);
    }

    public static MobilyticsMetricSerializer provideInstance(Provider<Context> provider, Provider<MetricsBuilderProvider> provider2) {
        return new MobilyticsMetricSerializer(provider.mo10268get(), DoubleCheck.lazy(provider2));
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MobilyticsMetricSerializer mo10268get() {
        return provideInstance(this.contextProvider, this.metricsBuilderProviderLazyProvider);
    }
}
