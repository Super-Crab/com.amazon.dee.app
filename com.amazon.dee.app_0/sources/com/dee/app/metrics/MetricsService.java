package com.dee.app.metrics;

import java.util.Map;
@Deprecated
/* loaded from: classes2.dex */
public interface MetricsService {
    void beginSession();

    void cancelTimer(String str);

    AlexaMetricsEvent createEvent(String str, String str2, Map<String, Object> map);

    void endSession();

    void incrementCounter(String str);

    void incrementCounterByValue(String str, int i);

    boolean invalidateEvent(String str);

    boolean isOngoingEvent(String str);

    void pauseSession();

    void pauseTimer(String str);

    void recordCounter(MetricsCounter metricsCounter);

    void recordCustom(String str, String str2, String str3, Map<String, Object> map);

    void recordData(String str, String str2, String str3, Map<String, Object> map);

    void recordEngagement(String str, String str2, Map<String, Object> map);

    void recordError(String str, String str2, String str3, Map<String, Object> map);

    void recordEvent(AlexaMetricsEvent alexaMetricsEvent);

    void recordEvent(String str);

    void recordEvent(String str, String str2, Map<String, Object> map);

    void recordImpression(String str, String str2, Map<String, Object> map);

    void recordMonetization(String str, String str2, Map<String, Object> map);

    void recordOccurrence(String str, String str2, boolean z, Map<String, Object> map);

    void recordPercentOccurrence(String str, String str2, boolean z, Map<String, Object> map);

    void recordTimer(MetricsTimer metricsTimer);

    void recordTimer(String str);

    void recordTimer(String str, Map<String, Object> map);

    void recordTimerAs(String str, String str2);

    void resumeSession();

    void resumeTimer(String str);

    MetricsCounter startCounter(String str, String str2, Map<String, Object> map);

    MetricsTimer startTimer(String str, String str2, Map<String, Object> map);
}
