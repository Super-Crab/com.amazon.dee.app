package com.amazon.alexa.handsfree.metrics;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.metrics.utils.AttributionTagProvider;
import com.amazon.alexa.handsfree.metrics.utils.MobilyticsEventDecorator;
import com.amazon.alexa.handsfree.metrics.utils.MobilyticsPmetFactory;
import com.amazon.alexa.handsfree.protocols.metrics.Metric;
import com.amazon.alexa.handsfree.protocols.metrics.MetricRecorder;
import com.amazon.alexa.handsfree.protocols.metrics.MetricsRecordMode;
import com.amazon.alexa.handsfree.protocols.metrics.caching.MetricSerializer;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.storage.metrics.CacheMetricsService;
import com.amazon.alexa.handsfree.storage.metrics.MetricsEnabledStatusStore;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsOperationalEvent;
import com.amazon.alexa.mobilytics.event.userinteraction.MobilyticsUserInteractionEvent;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
/* loaded from: classes8.dex */
public class MobilyticsMetricRecorder implements MetricRecorder {
    private static final String TAG = "MobilyticsMetricRecorder";
    private final AttributionTagProvider mAttributionTagProvider;
    private final ComponentRegistry mComponentRegistry;
    private final Context mContext;
    private final MetricSerializer mMetricSerializer;
    private final MetricsEnabledStatusStore mMetricsEnabledStatusStore;
    private Mobilytics mMobilytics;
    private final MobilyticsEventDecorator mMobilyticsEventDecorator;
    private final MobilyticsPmetFactory mMobilyticsPmetFactory;
    private final CacheMetricsService.ServiceHelper mServiceHelper;

    public MobilyticsMetricRecorder(@NonNull Context context, @NonNull ComponentRegistry componentRegistry, @NonNull MetricsEnabledStatusStore metricsEnabledStatusStore, @NonNull MetricSerializer metricSerializer, @NonNull CacheMetricsService.ServiceHelper serviceHelper, @NonNull MobilyticsEventDecorator mobilyticsEventDecorator, @NonNull AttributionTagProvider attributionTagProvider, @NonNull MobilyticsPmetFactory mobilyticsPmetFactory) {
        this.mContext = context;
        this.mComponentRegistry = componentRegistry;
        this.mMetricsEnabledStatusStore = metricsEnabledStatusStore;
        this.mMetricSerializer = metricSerializer;
        this.mServiceHelper = serviceHelper;
        this.mMobilyticsEventDecorator = mobilyticsEventDecorator;
        this.mAttributionTagProvider = attributionTagProvider;
        this.mMobilyticsPmetFactory = mobilyticsPmetFactory;
    }

    private void cacheMetrics(@NonNull List<Metric> list) {
        String serializeMetricsList = this.mMetricSerializer.serializeMetricsList(list);
        if (serializeMetricsList != null) {
            this.mServiceHelper.cacheMetrics(this.mContext, serializeMetricsList);
        }
    }

    private void emitMetrics(@NonNull List<Metric> list) {
        String uuid = UUID.randomUUID().toString();
        for (Metric metric : list) {
            this.mMobilyticsEventDecorator.decorate(metric, uuid);
            if (metric instanceof MobilyticsOperationalEvent) {
                this.mMobilytics.recordOperationalEvent((MobilyticsOperationalEvent) metric);
            } else if (metric instanceof MobilyticsUserInteractionEvent) {
                MobilyticsUserInteractionEvent mobilyticsUserInteractionEvent = (MobilyticsUserInteractionEvent) metric;
                this.mMobilytics.recordUserInteractionEvent(mobilyticsUserInteractionEvent);
                MobilyticsOperationalEvent createPmetEvent = this.mMobilyticsPmetFactory.createPmetEvent(mobilyticsUserInteractionEvent);
                this.mMobilyticsEventDecorator.decorate((Metric) createPmetEvent, uuid);
                this.mMobilytics.recordOperationalEvent(createPmetEvent);
            } else {
                String str = TAG;
                Log.e(str, "recordMetricGroup(Object,List<Metric>): Invalid metric instance for " + metric);
            }
        }
    }

    private Mobilytics getMobilytics() {
        Mobilytics mobilytics = (Mobilytics) this.mComponentRegistry.get(Mobilytics.class).orNull();
        if (mobilytics == null) {
            Log.e(TAG, "Mobilytics instance is null. Metrics will not be recorded.");
        }
        return mobilytics;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.MetricRecorder
    public void recordMetric(@NonNull Object obj, @NonNull MetricsRecordMode metricsRecordMode, @NonNull Metric metric) {
        recordMetricGroup(obj, metricsRecordMode, new ArrayList(Collections.singletonList(metric)));
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.MetricRecorder
    public void recordMetricGroup(@NonNull Object obj, @NonNull MetricsRecordMode metricsRecordMode, Metric... metricArr) {
        if (metricArr == null) {
            return;
        }
        recordMetricGroup(obj, metricsRecordMode, new ArrayList(Arrays.asList(metricArr)));
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.MetricRecorder
    public void recordMetricGroup(@NonNull Object obj, @NonNull MetricsRecordMode metricsRecordMode, @NonNull List<Metric> list) {
        if (this.mMobilytics == null) {
            this.mMobilytics = getMobilytics();
        }
        if (this.mMobilytics != null && this.mAttributionTagProvider.hasComputedAttributionTag() && (this.mMetricsEnabledStatusStore.canEmitMetrics(this.mContext) || metricsRecordMode == MetricsRecordMode.CHECK_BEFORE_RECORD_IGNORE_METRICS_ENABLED)) {
            emitMetrics(list);
        } else if (metricsRecordMode == MetricsRecordMode.CHECK_BEFORE_RECORD_SKIP_CACHE) {
        } else {
            cacheMetrics(list);
        }
    }
}
