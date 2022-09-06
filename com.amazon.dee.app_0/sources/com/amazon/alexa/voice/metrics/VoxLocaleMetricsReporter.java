package com.amazon.alexa.voice.metrics;

import com.amazon.alexa.voice.metrics.service.MetricsService;
/* loaded from: classes11.dex */
public class VoxLocaleMetricsReporter extends VoxMetricEventProcessor {
    private static final String VOX_LOCALE_UPDATED_PREFIX = "VOX_LOCALE_UPDATED";
    private final MetricsService metricsService;

    public VoxLocaleMetricsReporter(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @Override // com.amazon.alexa.voice.metrics.VoxMetricEventProcessor
    public void process(VoxMetricEvent voxMetricEvent) {
        String name = voxMetricEvent.getName();
        if (!name.equals(VoxMetricEventName.VOX_LOCALE_UPDATED_SUCCESS) && !name.equals(VoxMetricEventName.VOX_LOCALE_UPDATED_FAILED)) {
            if (!name.startsWith("VOX_LOCALE_UPDATED")) {
                return;
            }
            VoxMetricEventProcessor.recordEventMetricWithClickInteraction(this.metricsService, name, voxMetricEvent.getLocales(), voxMetricEvent.getFromWhere());
            return;
        }
        VoxMetricEventProcessor.recordEventMetric(this.metricsService, name);
    }
}
