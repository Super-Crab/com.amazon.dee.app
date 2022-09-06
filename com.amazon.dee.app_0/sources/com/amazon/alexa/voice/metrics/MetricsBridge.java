package com.amazon.alexa.voice.metrics;

import java.util.Map;
/* loaded from: classes11.dex */
public interface MetricsBridge {
    void reportEvent(String str);

    void reportEvent(String str, String str2, Map<String, Object> map);

    void reportOccurrence(String str, String str2, boolean z, Map<String, Object> map);

    void reportTimer(String str, String str2, long j, Map<String, Object> map);

    void startTimerWhenTappingIngressButtonOnCard();
}
