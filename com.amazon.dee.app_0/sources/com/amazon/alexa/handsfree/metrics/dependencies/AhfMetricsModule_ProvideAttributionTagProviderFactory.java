package com.amazon.alexa.handsfree.metrics.dependencies;

import com.amazon.alexa.handsfree.metrics.utils.AttributionTagProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes8.dex */
public final class AhfMetricsModule_ProvideAttributionTagProviderFactory implements Factory<AttributionTagProvider> {
    private final AhfMetricsModule module;

    public AhfMetricsModule_ProvideAttributionTagProviderFactory(AhfMetricsModule ahfMetricsModule) {
        this.module = ahfMetricsModule;
    }

    public static AhfMetricsModule_ProvideAttributionTagProviderFactory create(AhfMetricsModule ahfMetricsModule) {
        return new AhfMetricsModule_ProvideAttributionTagProviderFactory(ahfMetricsModule);
    }

    public static AttributionTagProvider provideInstance(AhfMetricsModule ahfMetricsModule) {
        return proxyProvideAttributionTagProvider(ahfMetricsModule);
    }

    public static AttributionTagProvider proxyProvideAttributionTagProvider(AhfMetricsModule ahfMetricsModule) {
        return (AttributionTagProvider) Preconditions.checkNotNull(ahfMetricsModule.provideAttributionTagProvider(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AttributionTagProvider mo10268get() {
        return provideInstance(this.module);
    }
}
