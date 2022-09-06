package com.amazon.alexa.accessory.metrics;

import com.amazon.alexa.accessory.internal.util.Preconditions;
/* loaded from: classes.dex */
public final class MetricsCollectorMetricsReporter implements MetricsReporter {
    private final MetricsCollector metricsCollector;

    public MetricsCollectorMetricsReporter(MetricsCollector metricsCollector) {
        Preconditions.notNull(metricsCollector, "metricsCollector");
        this.metricsCollector = metricsCollector;
    }

    @Override // com.amazon.alexa.accessory.metrics.MetricsReporter
    public void recordEvent(MetricsEvent metricsEvent) {
        Preconditions.notNull(metricsEvent, "event");
        this.metricsCollector.recordEvent(metricsEvent);
    }
}
