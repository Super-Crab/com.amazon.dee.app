package com.amazonaws.mobileconnectors.amazonmobileanalytics;
@Deprecated
/* loaded from: classes13.dex */
public interface EventClient {
    void addGlobalAttribute(String str, String str2);

    void addGlobalAttribute(String str, String str2, String str3);

    void addGlobalMetric(String str, Double d);

    void addGlobalMetric(String str, String str2, Double d);

    AnalyticsEvent createEvent(String str);

    void recordEvent(AnalyticsEvent analyticsEvent);

    void removeGlobalAttribute(String str);

    void removeGlobalAttribute(String str, String str2);

    void removeGlobalMetric(String str);

    void removeGlobalMetric(String str, String str2);

    void submitEvents();
}
