package com.amazon.alexa.handsfree.metrics;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.metrics.Metric;
import com.amazon.alexa.handsfree.protocols.metrics.MetricRecorder;
import com.amazon.alexa.handsfree.protocols.metrics.MetricsEmitter;
import com.amazon.alexa.handsfree.protocols.metrics.MetricsEmitterConfig;
import com.amazon.alexa.handsfree.protocols.metrics.MetricsRecordMode;
import com.amazon.alexa.handsfree.protocols.utils.Log;
/* loaded from: classes8.dex */
public class MobilyticsMetricsEmitter implements MetricsEmitter {
    private static final String TAG = "MobilyticsMetricsEmitter";
    private final MetricRecorder mMetricRecorder;
    private final MetricsEmitterConfig mMetricsEmitterConfig;

    public MobilyticsMetricsEmitter(@NonNull MetricRecorder metricRecorder, @NonNull MetricsEmitterConfig metricsEmitterConfig) {
        this.mMetricRecorder = metricRecorder;
        this.mMetricsEmitterConfig = metricsEmitterConfig;
    }

    private void emitMetric(@NonNull Object obj, @NonNull MetricsRecordMode metricsRecordMode, @NonNull Metric... metricArr) {
        this.mMetricRecorder.recordMetricGroup(obj, metricsRecordMode, metricArr);
    }

    private boolean isRemotelyDisabled(@NonNull MetricsRecordMode metricsRecordMode) {
        if (metricsRecordMode != MetricsRecordMode.CHECK_BEFORE_RECORD || this.mMetricsEmitterConfig.canEmitMetrics()) {
            return false;
        }
        Log.i(TAG, "recordMetrics(RecordMode,Context,Object,Metric...): Metrics are remotely disabled");
        return true;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.MetricsEmitter
    public void recordMetrics(@NonNull Context context, @NonNull Object obj, @NonNull Metric... metricArr) {
        recordMetrics(MetricsRecordMode.CHECK_BEFORE_RECORD, context, obj, metricArr);
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.MetricsEmitter
    public void recordMetrics(@NonNull MetricsRecordMode metricsRecordMode, @NonNull Context context, @NonNull Object obj, @NonNull Metric... metricArr) {
        if (isRemotelyDisabled(metricsRecordMode)) {
            return;
        }
        emitMetric(obj, metricsRecordMode, metricArr);
    }
}
