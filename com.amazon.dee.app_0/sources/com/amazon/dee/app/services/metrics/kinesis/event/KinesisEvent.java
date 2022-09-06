package com.amazon.dee.app.services.metrics.kinesis.event;

import java.util.Map;
/* loaded from: classes12.dex */
public interface KinesisEvent {
    void addAttribute(String str, String str2);

    void addMetric(String str, Double d);

    void addPivot(String str, String str2);

    Map<String, String> getAllAttributes();

    Map<String, Double> getAllMetrics();

    Map<String, String> getAllPivots();

    String getAttribute(String str);

    String getEventType();

    Double getMetric(String str);

    String getPivot(String str);

    boolean hasAttribute(String str);

    boolean hasMetric(String str);

    boolean hasPivot(String str);

    /* renamed from: withAttribute */
    KinesisEvent mo3596withAttribute(String str, String str2);

    /* renamed from: withMetric */
    KinesisEvent mo3597withMetric(String str, Double d);

    /* renamed from: withPivot */
    KinesisEvent mo3598withPivot(String str, String str2);
}
