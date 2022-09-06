package com.amazon.alexa.fitness.metrics;

import com.amazon.alexa.fitness.metrics.Timer;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: MetricEventRecorder.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/fitness/metrics/DefaultMetricEventRecorder;", "Lcom/amazon/alexa/fitness/metrics/MetricEventRecorder;", "metricService", "Lcom/amazon/alexa/fitness/metrics/MetricService;", "(Lcom/amazon/alexa/fitness/metrics/MetricService;)V", "record", "", "metricEvent", "Lcom/amazon/alexa/fitness/metrics/MetricEvent;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class DefaultMetricEventRecorder implements MetricEventRecorder {
    private final MetricService metricService;

    @Inject
    public DefaultMetricEventRecorder(@NotNull MetricService metricService) {
        Intrinsics.checkParameterIsNotNull(metricService, "metricService");
        this.metricService = metricService;
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricEventRecorder
    public void record(@NotNull MetricEvent metricEvent) {
        Intrinsics.checkParameterIsNotNull(metricEvent, "metricEvent");
        Map<String, String> snapshotMetadata = metricEvent.snapshotMetadata();
        for (Counter counter : metricEvent.snapshotCounters()) {
            this.metricService.recordCount(MetricsConstantsKt.METRIC_MODULE, counter.getMetric(), counter.getValue(), snapshotMetadata);
        }
        for (Timer.Stopped stopped : metricEvent.snapshotTimers()) {
            this.metricService.recordTimer(MetricsConstantsKt.METRIC_MODULE, stopped.getMetric(), stopped.getDurationMilli(), snapshotMetadata);
        }
    }
}
