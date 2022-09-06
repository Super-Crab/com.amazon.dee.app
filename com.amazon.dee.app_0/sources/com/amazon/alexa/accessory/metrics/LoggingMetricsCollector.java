package com.amazon.alexa.accessory.metrics;

import com.amazon.alexa.accessory.internal.util.Logger;
/* loaded from: classes.dex */
public final class LoggingMetricsCollector implements MetricsCollector {
    @Override // com.amazon.alexa.accessory.metrics.MetricsCollector
    public void recordEvent(MetricsEvent metricsEvent) {
        Logger.d("Logging metrics collector: received metrics event %s", metricsEvent);
    }
}
