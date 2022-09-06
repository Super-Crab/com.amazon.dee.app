package com.amazon.maft.metrics;
/* loaded from: classes12.dex */
public interface PmetMetrics {
    PmetMetrics addCount(String str, double d);

    PmetMetrics addProperty(String str, String str2);

    PmetMetrics addTime(String str, double d);

    void record();
}
