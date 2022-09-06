package com.amazon.alexa.handsfree.protocols.metrics;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfScope;
import com.amazon.alexa.handsfree.protocols.metrics.caching.MetricSerializer;
import com.amazon.alexa.handsfree.protocols.metrics.factories.MetricFactoryProvider;
import javax.inject.Inject;
@AhfScope
/* loaded from: classes8.dex */
public class MetricsConfiguration {
    private final MetricFactoryProvider mMetricFactoryProvider;
    private final MetricSerializer mMetricSerializer;
    private final MetricsEmitter mMetricsEmitter;

    @Inject
    public MetricsConfiguration(MetricsEmitter metricsEmitter, MetricFactoryProvider metricFactoryProvider, MetricSerializer metricSerializer) {
        this.mMetricsEmitter = metricsEmitter;
        this.mMetricFactoryProvider = metricFactoryProvider;
        this.mMetricSerializer = metricSerializer;
    }

    @NonNull
    public MetricFactoryProvider getMetricFactoryProvider() {
        return this.mMetricFactoryProvider;
    }

    @NonNull
    public MetricSerializer getMetricSerializer() {
        return this.mMetricSerializer;
    }

    @NonNull
    public MetricsEmitter getMetricsEmitter() {
        return this.mMetricsEmitter;
    }
}
