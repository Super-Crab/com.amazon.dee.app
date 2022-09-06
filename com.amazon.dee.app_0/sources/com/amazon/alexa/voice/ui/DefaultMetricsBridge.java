package com.amazon.alexa.voice.ui;

import com.amazon.alexa.voice.metrics.MetricsBridge;
import com.amazon.alexa.voice.metrics.VoiceMetricsConstants;
import com.amazon.alexa.voice.metrics.VoiceMetricsTimer;
import com.amazon.alexa.voice.metrics.VoxMetricEvent;
import com.amazon.alexa.voice.metrics.VoxMetricEventProcessingService;
import com.amazon.alexa.voice.metrics.service.MetricsService;
import com.amazon.alexa.voice.model.VoiceService;
import java.util.Collections;
import java.util.Map;
/* loaded from: classes11.dex */
public final class DefaultMetricsBridge implements MetricsBridge {
    private final MetricsService metricsService;
    private final VoxMetricEventProcessingService voxMetricEventProcessingService;

    public DefaultMetricsBridge(MetricsService metricsService, VoxMetricEventProcessingService voxMetricEventProcessingService) {
        this.metricsService = metricsService;
        this.voxMetricEventProcessingService = voxMetricEventProcessingService;
    }

    @Override // com.amazon.alexa.voice.metrics.MetricsBridge
    public void reportEvent(String str) {
        this.voxMetricEventProcessingService.process(VoxMetricEvent.occurNow(str));
    }

    @Override // com.amazon.alexa.voice.metrics.MetricsBridge
    public void reportOccurrence(String str, String str2, boolean z, Map<String, Object> map) {
        this.metricsService.recordOccurrence(str2, str, z, map);
    }

    @Override // com.amazon.alexa.voice.metrics.MetricsBridge
    public void reportTimer(String str, String str2, long j, Map<String, Object> map) {
        this.metricsService.recordTimer(new VoiceMetricsTimer(str2, str, map, j, false));
    }

    @Override // com.amazon.alexa.voice.metrics.MetricsBridge
    public void startTimerWhenTappingIngressButtonOnCard() {
        this.metricsService.startTimer(VoiceMetricsConstants.VOX_TAP_TO_VOICE_RECORD_START, VoiceService.InvocationType.CARD.getName(), Collections.emptyMap());
    }

    @Override // com.amazon.alexa.voice.metrics.MetricsBridge
    public void reportEvent(String str, String str2, Map<String, Object> map) {
        this.metricsService.recordEvent(str2, str, map);
    }
}
