package com.amazon.alexa.accessory.metrics;

import com.amazon.alexa.accessory.BuildConfig;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
/* loaded from: classes.dex */
public final class EnvironmentMetricsReporter implements MetricsReporter {
    private final MetricsReporter metricsReporter;

    public EnvironmentMetricsReporter(MetricsReporter metricsReporter) {
        Preconditions.notNull(metricsReporter, "metricsReporter");
        this.metricsReporter = metricsReporter;
    }

    @Override // com.amazon.alexa.accessory.metrics.MetricsReporter
    public void recordEvent(MetricsEvent metricsEvent) {
        Preconditions.notNull(metricsEvent, "event");
        CustomMetricsEvent customMetricsEvent = new CustomMetricsEvent(metricsEvent);
        customMetricsEvent.putString(MetricsConstants.Environment.ACCESSORY_LIBRARY_VERSION, BuildConfig.VERSION_NAME);
        this.metricsReporter.recordEvent(customMetricsEvent);
    }
}
