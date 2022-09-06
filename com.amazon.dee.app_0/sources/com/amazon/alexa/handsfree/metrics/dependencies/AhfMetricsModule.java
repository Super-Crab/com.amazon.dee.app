package com.amazon.alexa.handsfree.metrics.dependencies;

import android.content.Context;
import android.provider.Settings;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.metrics.MobilyticsMetricRecorder;
import com.amazon.alexa.handsfree.metrics.MobilyticsMetricsEmitter;
import com.amazon.alexa.handsfree.metrics.caching.MobilyticsMetricSerializer;
import com.amazon.alexa.handsfree.metrics.factories.MobilyticsMetricFactoryProvider;
import com.amazon.alexa.handsfree.metrics.mobilytics.MobilyticsMetadataProvider;
import com.amazon.alexa.handsfree.metrics.utils.AttributionTagProvider;
import com.amazon.alexa.handsfree.metrics.utils.MobilyticsEventDecorator;
import com.amazon.alexa.handsfree.metrics.utils.MobilyticsPmetFactory;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfScope;
import com.amazon.alexa.handsfree.protocols.metrics.MetricRecorder;
import com.amazon.alexa.handsfree.protocols.metrics.MetricsEmitter;
import com.amazon.alexa.handsfree.protocols.metrics.MetricsEmitterConfig;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.metrics.caching.MetricSerializer;
import com.amazon.alexa.handsfree.protocols.metrics.factories.MetricFactoryProvider;
import com.amazon.alexa.handsfree.storage.metrics.CacheMetricsService;
import com.amazon.alexa.handsfree.storage.metrics.MetricsEnabledStatusStore;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes8.dex */
public class AhfMetricsModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @AhfScope
    @Provides
    public AttributionTagProvider provideAttributionTagProvider() {
        return AttributionTagProvider.getInstance();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @AhfScope
    @Provides
    public CacheMetricsService.ServiceHelper provideCacheMetricsServiceServiceHelper() {
        return new CacheMetricsService.ServiceHelper();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @AhfScope
    @Provides
    public MetricFactoryProvider provideMetricFactoryProvider() {
        return new MobilyticsMetricFactoryProvider();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @AhfScope
    @Provides
    public MetricSerializer provideMetricSerializer(@NonNull Context context, @NonNull Lazy<MetricsBuilderProvider> lazy) {
        return new MobilyticsMetricSerializer(context, lazy);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @AhfScope
    @Provides
    public MetricsEmitter provideMetricsEmitter(@NonNull MetricRecorder metricRecorder, @NonNull MetricsEmitterConfig metricsEmitterConfig) {
        return new MobilyticsMetricsEmitter(metricRecorder, metricsEmitterConfig);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @AhfScope
    @Provides
    public MetricRecorder provideMetricsRecorder(@NonNull Context context, @NonNull ComponentRegistry componentRegistry, @NonNull MetricsEnabledStatusStore metricsEnabledStatusStore, @NonNull MetricSerializer metricSerializer, @NonNull CacheMetricsService.ServiceHelper serviceHelper, @NonNull MobilyticsEventDecorator mobilyticsEventDecorator, @NonNull AttributionTagProvider attributionTagProvider, @NonNull MobilyticsPmetFactory mobilyticsPmetFactory) {
        return new MobilyticsMetricRecorder(context, componentRegistry, metricsEnabledStatusStore, metricSerializer, serviceHelper, mobilyticsEventDecorator, attributionTagProvider, mobilyticsPmetFactory);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @AhfScope
    @Provides
    public MobilyticsEventDecorator provideMobilyticsEventDecorator(@NonNull Context context, @NonNull AttributionTagProvider attributionTagProvider, @NonNull MobilyticsMetadataProvider mobilyticsMetadataProvider) {
        return new MobilyticsEventDecorator(attributionTagProvider, mobilyticsMetadataProvider, Settings.Secure.getString(context.getContentResolver(), "android_id"));
    }
}
