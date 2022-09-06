package com.amazonaws.mobileconnectors.amazonmobileanalytics;

import java.util.Map;
@Deprecated
/* loaded from: classes13.dex */
public interface AnalyticsEvent {
    void addAttribute(String str, String str2);

    void addMetric(String str, Double d);

    Map<String, String> getAllAttributes();

    Map<String, Double> getAllMetrics();

    String getAttribute(String str);

    String getEventType();

    Double getMetric(String str);

    boolean hasAttribute(String str);

    boolean hasMetric(String str);

    /* renamed from: withAttribute */
    AnalyticsEvent mo6692withAttribute(String str, String str2);

    /* renamed from: withMetric */
    AnalyticsEvent mo6693withMetric(String str, Double d);
}
