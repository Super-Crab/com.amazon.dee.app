package com.amazon.alexa.handsfree.metrics.dependencies;

import android.content.Context;
import com.amazon.alexa.handsfree.metrics.utils.AttributionTagProvider;
import com.amazon.alexa.handsfree.metrics.utils.MobilyticsEventDecorator;
import com.amazon.alexa.handsfree.metrics.utils.MobilyticsPmetFactory;
import com.amazon.alexa.handsfree.protocols.metrics.MetricRecorder;
import com.amazon.alexa.handsfree.protocols.metrics.caching.MetricSerializer;
import com.amazon.alexa.handsfree.storage.metrics.CacheMetricsService;
import com.amazon.alexa.handsfree.storage.metrics.MetricsEnabledStatusStore;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class AhfMetricsModule_ProvideMetricsRecorderFactory implements Factory<MetricRecorder> {
    private final Provider<AttributionTagProvider> attributionTagProvider;
    private final Provider<ComponentRegistry> componentRegistryProvider;
    private final Provider<Context> contextProvider;
    private final Provider<MetricSerializer> metricSerializerProvider;
    private final Provider<MetricsEnabledStatusStore> metricsEnabledStatusStoreProvider;
    private final Provider<MobilyticsEventDecorator> mobilyticsEventDecoratorProvider;
    private final Provider<MobilyticsPmetFactory> mobilyticsPmetFactoryProvider;
    private final AhfMetricsModule module;
    private final Provider<CacheMetricsService.ServiceHelper> serviceHelperProvider;

    public AhfMetricsModule_ProvideMetricsRecorderFactory(AhfMetricsModule ahfMetricsModule, Provider<Context> provider, Provider<ComponentRegistry> provider2, Provider<MetricsEnabledStatusStore> provider3, Provider<MetricSerializer> provider4, Provider<CacheMetricsService.ServiceHelper> provider5, Provider<MobilyticsEventDecorator> provider6, Provider<AttributionTagProvider> provider7, Provider<MobilyticsPmetFactory> provider8) {
        this.module = ahfMetricsModule;
        this.contextProvider = provider;
        this.componentRegistryProvider = provider2;
        this.metricsEnabledStatusStoreProvider = provider3;
        this.metricSerializerProvider = provider4;
        this.serviceHelperProvider = provider5;
        this.mobilyticsEventDecoratorProvider = provider6;
        this.attributionTagProvider = provider7;
        this.mobilyticsPmetFactoryProvider = provider8;
    }

    public static AhfMetricsModule_ProvideMetricsRecorderFactory create(AhfMetricsModule ahfMetricsModule, Provider<Context> provider, Provider<ComponentRegistry> provider2, Provider<MetricsEnabledStatusStore> provider3, Provider<MetricSerializer> provider4, Provider<CacheMetricsService.ServiceHelper> provider5, Provider<MobilyticsEventDecorator> provider6, Provider<AttributionTagProvider> provider7, Provider<MobilyticsPmetFactory> provider8) {
        return new AhfMetricsModule_ProvideMetricsRecorderFactory(ahfMetricsModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public static MetricRecorder provideInstance(AhfMetricsModule ahfMetricsModule, Provider<Context> provider, Provider<ComponentRegistry> provider2, Provider<MetricsEnabledStatusStore> provider3, Provider<MetricSerializer> provider4, Provider<CacheMetricsService.ServiceHelper> provider5, Provider<MobilyticsEventDecorator> provider6, Provider<AttributionTagProvider> provider7, Provider<MobilyticsPmetFactory> provider8) {
        return proxyProvideMetricsRecorder(ahfMetricsModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), provider8.mo10268get());
    }

    public static MetricRecorder proxyProvideMetricsRecorder(AhfMetricsModule ahfMetricsModule, Context context, ComponentRegistry componentRegistry, MetricsEnabledStatusStore metricsEnabledStatusStore, MetricSerializer metricSerializer, CacheMetricsService.ServiceHelper serviceHelper, MobilyticsEventDecorator mobilyticsEventDecorator, AttributionTagProvider attributionTagProvider, MobilyticsPmetFactory mobilyticsPmetFactory) {
        return (MetricRecorder) Preconditions.checkNotNull(ahfMetricsModule.provideMetricsRecorder(context, componentRegistry, metricsEnabledStatusStore, metricSerializer, serviceHelper, mobilyticsEventDecorator, attributionTagProvider, mobilyticsPmetFactory), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricRecorder mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.componentRegistryProvider, this.metricsEnabledStatusStoreProvider, this.metricSerializerProvider, this.serviceHelperProvider, this.mobilyticsEventDecoratorProvider, this.attributionTagProvider, this.mobilyticsPmetFactoryProvider);
    }
}
