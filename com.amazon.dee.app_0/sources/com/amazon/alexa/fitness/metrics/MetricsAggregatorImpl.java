package com.amazon.alexa.fitness.metrics;

import com.amazon.alexa.fitness.repository.MetricsAggregatorCache;
import com.amazon.alexa.fitness.utils.MetricComponent;
import com.amazon.alexa.fitness.utils.MetricHelperKt;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.connector.DefaultKinesisConnector;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: MetricsAggregatorImpl.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\tH\u0016J\u0010\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\tH\u0016J\b\u0010\u0019\u001a\u00020\u0016H\u0016J\u0018\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\nH\u0016J\u0018\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\nH\u0016J\u0010\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\tH\u0016R*\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bj\u0002`\u000bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR*\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00110\bj\u0002`\u0012X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\r\"\u0004\b\u0014\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/amazon/alexa/fitness/metrics/MetricsAggregatorImpl;", "Lcom/amazon/alexa/fitness/metrics/MetricsAggregator;", DefaultKinesisConnector.COMPONENT, "Lcom/amazon/alexa/mobilytics/Mobilytics;", "metricsAggregatorCache", "Lcom/amazon/alexa/fitness/repository/MetricsAggregatorCache;", "(Lcom/amazon/alexa/mobilytics/Mobilytics;Lcom/amazon/alexa/fitness/repository/MetricsAggregatorCache;)V", "aggregatedCounterMetrics", "", "Lcom/amazon/alexa/fitness/utils/MetricComponent;", "", "Lcom/amazon/alexa/fitness/metrics/counterMetrics;", "getAggregatedCounterMetrics", "()Ljava/util/Map;", "setAggregatedCounterMetrics", "(Ljava/util/Map;)V", "aggregatedTimerMetrics", "Lcom/amazon/alexa/mobilytics/event/operational/MobilyticsMetricsTimer;", "Lcom/amazon/alexa/fitness/metrics/timerMetrics;", "getAggregatedTimerMetrics", "setAggregatedTimerMetrics", "incrementCounter", "", "metricName", "pauseTimer", "publishMetrics", "recordCounter", "value", "recordTimer", "duration", "startOrResumeTimer", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class MetricsAggregatorImpl implements MetricsAggregator {
    @NotNull
    private Map<MetricComponent, Long> aggregatedCounterMetrics;
    @NotNull
    private Map<MetricComponent, MobilyticsMetricsTimer> aggregatedTimerMetrics;
    private final MetricsAggregatorCache metricsAggregatorCache;
    private final Mobilytics mobilytics;

    @Inject
    public MetricsAggregatorImpl(@NotNull Mobilytics mobilytics, @NotNull MetricsAggregatorCache metricsAggregatorCache) {
        Intrinsics.checkParameterIsNotNull(mobilytics, "mobilytics");
        Intrinsics.checkParameterIsNotNull(metricsAggregatorCache, "metricsAggregatorCache");
        this.mobilytics = mobilytics;
        this.metricsAggregatorCache = metricsAggregatorCache;
        this.aggregatedCounterMetrics = new LinkedHashMap();
        this.aggregatedTimerMetrics = new LinkedHashMap();
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricsAggregator
    @NotNull
    public Map<MetricComponent, Long> getAggregatedCounterMetrics() {
        return this.aggregatedCounterMetrics;
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricsAggregator
    @NotNull
    public Map<MetricComponent, MobilyticsMetricsTimer> getAggregatedTimerMetrics() {
        return this.aggregatedTimerMetrics;
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricsAggregator
    public void incrementCounter(@NotNull MetricComponent metricName) {
        Intrinsics.checkParameterIsNotNull(metricName, "metricName");
        Map<MetricComponent, Long> aggregatedCounterMetrics = getAggregatedCounterMetrics();
        Long l = getAggregatedCounterMetrics().get(metricName);
        aggregatedCounterMetrics.put(metricName, Long.valueOf((l != null ? l.longValue() : 0L) + 1));
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricsAggregator
    public void pauseTimer(@NotNull MetricComponent metricName) {
        Intrinsics.checkParameterIsNotNull(metricName, "metricName");
        MobilyticsMetricsTimer mobilyticsMetricsTimer = getAggregatedTimerMetrics().get(metricName);
        if (mobilyticsMetricsTimer != null) {
            mobilyticsMetricsTimer.pauseTimer();
        }
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricsAggregator
    public void publishMetrics() {
        for (Map.Entry<MetricComponent, Long> entry : getAggregatedCounterMetrics().entrySet()) {
            long longValue = entry.getValue().longValue();
            MetricHelperKt.recordCounter(this.mobilytics, entry.getKey(), longValue);
        }
        for (Map.Entry<MetricComponent, MobilyticsMetricsTimer> entry2 : getAggregatedTimerMetrics().entrySet()) {
            MobilyticsMetricsTimer value = entry2.getValue();
            value.finishTimer();
            this.mobilytics.recordTimer(value);
        }
        getAggregatedCounterMetrics().clear();
        getAggregatedTimerMetrics().clear();
        this.metricsAggregatorCache.deleteAggregatedMetrics();
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricsAggregator
    public void recordCounter(@NotNull MetricComponent metricName, long j) {
        Intrinsics.checkParameterIsNotNull(metricName, "metricName");
        MetricHelperKt.recordCounter(this.mobilytics, metricName, j);
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricsAggregator
    public void recordTimer(@NotNull MetricComponent metricName, long j) {
        Intrinsics.checkParameterIsNotNull(metricName, "metricName");
        MetricHelperKt.recordTimer(this.mobilytics, metricName, j);
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricsAggregator
    public void setAggregatedCounterMetrics(@NotNull Map<MetricComponent, Long> map) {
        Intrinsics.checkParameterIsNotNull(map, "<set-?>");
        this.aggregatedCounterMetrics = map;
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricsAggregator
    public void setAggregatedTimerMetrics(@NotNull Map<MetricComponent, MobilyticsMetricsTimer> map) {
        Intrinsics.checkParameterIsNotNull(map, "<set-?>");
        this.aggregatedTimerMetrics = map;
    }

    @Override // com.amazon.alexa.fitness.metrics.MetricsAggregator
    public void startOrResumeTimer(@NotNull MetricComponent metricName) {
        Intrinsics.checkParameterIsNotNull(metricName, "metricName");
        MobilyticsMetricsTimer mobilyticsMetricsTimer = getAggregatedTimerMetrics().get(metricName);
        if (mobilyticsMetricsTimer != null) {
            mobilyticsMetricsTimer.resumeTimer();
        } else {
            getAggregatedTimerMetrics().put(metricName, MetricHelperKt.createTimer(this.mobilytics, metricName));
        }
    }
}
