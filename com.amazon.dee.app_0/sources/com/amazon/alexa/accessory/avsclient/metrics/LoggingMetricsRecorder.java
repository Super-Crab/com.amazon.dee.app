package com.amazon.alexa.accessory.avsclient.metrics;

import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsService;
import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import java.util.Map;
/* loaded from: classes.dex */
public final class LoggingMetricsRecorder implements AccessoryMetricsService {
    @Override // com.amazon.alexa.accessory.metrics.AccessoryMetricsService
    public void createTimer(String str, String str2, String str3, String str4) {
        Preconditions.notNull(str, "metricIdentifier");
        Preconditions.notNull(str2, "timerName");
        Preconditions.notNull(str3, JsonFields.COMPONENT);
        Logger.d("LoggingMetricsRecorder: createTimer. metricIdentifier=%s timerName=%s, component=%s, subComponent=%s", str, str2, str3, str4);
    }

    @Override // com.amazon.alexa.accessory.metrics.AccessoryMetricsService
    public void recordCounter(String str, String str2, double d, Map<String, Object> map) {
        Logger.d("LoggingMetricsRecorder: recordCounter. name=%s, component=%s, value=%.1f, customAttributes=%s", str, str2, Double.valueOf(d), map);
    }

    @Override // com.amazon.alexa.accessory.metrics.AccessoryMetricsService
    public void recordCriticalEvent(String str, String str2, String str3, Throwable th) {
        Logger.e("LoggingMetricsRecorder: recordCriticalEvent: eventName=%s, component=%s, error=%s", str, str3, th);
    }

    @Override // com.amazon.alexa.accessory.metrics.AccessoryMetricsService
    public void recordOccurrence(String str, String str2, boolean z, Map<String, Object> map) {
        Logger.d("LoggingMetricsRecorder: recordOccurence. name=%s, component=%s, value=%b, customAttributes=%s", str, str2, Boolean.valueOf(z), map);
    }

    @Override // com.amazon.alexa.accessory.metrics.AccessoryMetricsService
    public void recordTime(String str, String str2, long j, Map<String, Object> map) {
        Logger.d("LoggingMetricsRecorder: recordTimer. name=%s, component=%s, value=%d, customAttributes=%s", str, str2, Long.valueOf(j), map);
    }

    @Override // com.amazon.alexa.accessory.metrics.AccessoryMetricsService
    public void recordTimer(String str) {
        Preconditions.notNull(str, "metricIdentifier");
        Logger.d("LoggingMetricsRecorder: recordTimer. metricIdentifier=%s", str);
    }

    @Override // com.amazon.alexa.accessory.metrics.AccessoryMetricsService
    public void recordWarningEvent(String str, String str2, String str3, Throwable th) {
        Logger.w("LoggingMetricsRecorder: recordWarningEvent: eventName=%s, component=%s, error=%s", str, str3, th);
    }
}
