package com.amazon.alexa.handsfree.metrics.dependencies;

import android.content.Context;
import com.amazon.alexa.handsfree.metrics.mobilytics.MobilyticsMetadataProvider;
import com.amazon.alexa.handsfree.metrics.utils.AttributionTagProvider;
import com.amazon.alexa.handsfree.metrics.utils.MobilyticsEventDecorator;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class AhfMetricsModule_ProvideMobilyticsEventDecoratorFactory implements Factory<MobilyticsEventDecorator> {
    private final Provider<AttributionTagProvider> attributionTagProvider;
    private final Provider<Context> contextProvider;
    private final Provider<MobilyticsMetadataProvider> mobilyticsMetadataProvider;
    private final AhfMetricsModule module;

    public AhfMetricsModule_ProvideMobilyticsEventDecoratorFactory(AhfMetricsModule ahfMetricsModule, Provider<Context> provider, Provider<AttributionTagProvider> provider2, Provider<MobilyticsMetadataProvider> provider3) {
        this.module = ahfMetricsModule;
        this.contextProvider = provider;
        this.attributionTagProvider = provider2;
        this.mobilyticsMetadataProvider = provider3;
    }

    public static AhfMetricsModule_ProvideMobilyticsEventDecoratorFactory create(AhfMetricsModule ahfMetricsModule, Provider<Context> provider, Provider<AttributionTagProvider> provider2, Provider<MobilyticsMetadataProvider> provider3) {
        return new AhfMetricsModule_ProvideMobilyticsEventDecoratorFactory(ahfMetricsModule, provider, provider2, provider3);
    }

    public static MobilyticsEventDecorator provideInstance(AhfMetricsModule ahfMetricsModule, Provider<Context> provider, Provider<AttributionTagProvider> provider2, Provider<MobilyticsMetadataProvider> provider3) {
        return proxyProvideMobilyticsEventDecorator(ahfMetricsModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static MobilyticsEventDecorator proxyProvideMobilyticsEventDecorator(AhfMetricsModule ahfMetricsModule, Context context, AttributionTagProvider attributionTagProvider, MobilyticsMetadataProvider mobilyticsMetadataProvider) {
        return (MobilyticsEventDecorator) Preconditions.checkNotNull(ahfMetricsModule.provideMobilyticsEventDecorator(context, attributionTagProvider, mobilyticsMetadataProvider), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MobilyticsEventDecorator mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.attributionTagProvider, this.mobilyticsMetadataProvider);
    }
}
