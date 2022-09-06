package com.amazon.alexa.voice.metrics;

import com.amazon.alexa.voice.metrics.service.MetricsService;
import com.amazon.alexa.voice.ui.onedesign.util.Logger;
/* loaded from: classes11.dex */
public abstract class VoxMetricEventProcessor {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void recordEventMetric(MetricsService metricsService, String str) {
        Logger.verbose("record event metric:" + str);
        metricsService.recordEvent(str, "vox_speech", null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void recordEventMetricWithClickInteraction(MetricsService metricsService, String str, String str2, String str3) {
        Logger.verbose("record click metric:" + str);
        metricsService.recordEventMetricWithClickInteraction(str, "vox_speech", str2, str3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void recordLatency(MetricsService metricsService, String str, long j) {
        Logger.verbose("record latency event:" + str + " duration:" + j);
        metricsService.recordTimer(new VoiceMetricsTimer(str, "vox_speech", null, j, false));
    }

    public abstract void process(VoxMetricEvent voxMetricEvent);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void recordLatency(MetricsService metricsService, String str, String str2, long j) {
        Logger.verbose("record latency event:" + str2 + " duration:" + j + " componentName:" + str);
        metricsService.recordTimer(new VoiceMetricsTimer(str2, str, null, j, false));
    }
}
