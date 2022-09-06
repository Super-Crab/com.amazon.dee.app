package com.amazon.alexa.voice.metrics.service;

import java.util.Map;
/* loaded from: classes11.dex */
public interface MetricsService {
    void cancelTimer(String str);

    void recordError(String str, String str2, String str3, Map<String, Object> map);

    void recordEvent(String str, String str2, Map<String, Object> map);

    void recordEventMetricWithClickInteraction(String str, String str2, String str3, String str4);

    void recordOccurrence(String str, String str2, boolean z, Map<String, Object> map);

    void recordPercentOccurrence(String str, String str2, boolean z, Map<String, Object> map);

    void recordTimer(MetricsTimer metricsTimer);

    void recordTimer(String str);

    MetricsTimer startTimer(String str, String str2, Map<String, Object> map);
}
