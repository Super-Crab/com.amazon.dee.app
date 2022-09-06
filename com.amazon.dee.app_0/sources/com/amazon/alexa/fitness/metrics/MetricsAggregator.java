package com.amazon.alexa.fitness.metrics;

import com.amazon.alexa.fitness.utils.MetricComponent;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: MetricsAggregator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0004H&J\u0010\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0004H&J\b\u0010\u0014\u001a\u00020\u0011H&J\u0018\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0005H&J\u0018\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0005H&J\u0010\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0004H&R(\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u0002`\u0006X¦\u000e¢\u0006\f\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR(\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\f0\u0003j\u0002`\rX¦\u000e¢\u0006\f\u001a\u0004\b\u000e\u0010\b\"\u0004\b\u000f\u0010\n¨\u0006\u001a"}, d2 = {"Lcom/amazon/alexa/fitness/metrics/MetricsAggregator;", "", "aggregatedCounterMetrics", "", "Lcom/amazon/alexa/fitness/utils/MetricComponent;", "", "Lcom/amazon/alexa/fitness/metrics/counterMetrics;", "getAggregatedCounterMetrics", "()Ljava/util/Map;", "setAggregatedCounterMetrics", "(Ljava/util/Map;)V", "aggregatedTimerMetrics", "Lcom/amazon/alexa/mobilytics/event/operational/MobilyticsMetricsTimer;", "Lcom/amazon/alexa/fitness/metrics/timerMetrics;", "getAggregatedTimerMetrics", "setAggregatedTimerMetrics", "incrementCounter", "", "metricName", "pauseTimer", "publishMetrics", "recordCounter", "value", "recordTimer", "duration", "startOrResumeTimer", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface MetricsAggregator {
    @NotNull
    Map<MetricComponent, Long> getAggregatedCounterMetrics();

    @NotNull
    Map<MetricComponent, MobilyticsMetricsTimer> getAggregatedTimerMetrics();

    void incrementCounter(@NotNull MetricComponent metricComponent);

    void pauseTimer(@NotNull MetricComponent metricComponent);

    void publishMetrics();

    void recordCounter(@NotNull MetricComponent metricComponent, long j);

    void recordTimer(@NotNull MetricComponent metricComponent, long j);

    void setAggregatedCounterMetrics(@NotNull Map<MetricComponent, Long> map);

    void setAggregatedTimerMetrics(@NotNull Map<MetricComponent, MobilyticsMetricsTimer> map);

    void startOrResumeTimer(@NotNull MetricComponent metricComponent);
}
