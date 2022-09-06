package com.amazon.alexa.alertsca.metrics.service;

import com.amazon.alexa.alertsca.metrics.MetricsElement;
/* loaded from: classes6.dex */
public interface MetricsService {
    void cancelTimer(String str);

    void recordError(String str, String str2);

    void recordEvent(MetricsElement metricsElement);

    void recordEvent(String str);

    void recordOccurrence(String str, boolean z);

    void recordPercentOccurrence(String str, boolean z);

    void recordTimer(MetricsTimer metricsTimer);

    void recordTimer(String str);

    MetricsTimer startTimer(String str);
}
