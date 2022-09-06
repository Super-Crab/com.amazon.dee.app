package com.amazon.client.metrics.common;

import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public interface MetricEvent {
    void addCounter(String str, double d);

    void addDataPoint(DataPoint dataPoint) throws MetricsException;

    void addDataPoints(List<DataPoint> list) throws MetricsException;

    void addString(String str, String str2);

    void addTimer(String str, double d);

    void addTimer(String str, double d, int i);

    void appendString(String str, String str2);

    void clear();

    boolean getAnonymous();

    List<DataPoint> getAsDataPoints();

    MetricEventType getMetricEventType();

    String getNonAnonymousCustomerId();

    String getNonAnonymousSessionId();

    String getProgram();

    String getSource();

    @Deprecated
    boolean hasDataPoints();

    void incrementCounter(String str, double d);

    void removeCounter(String str);

    void removeString(String str);

    void removeTimer(String str);

    void restoreFromMap(Map<String, String> map) throws IllegalArgumentException;

    void saveToMap(Map<String, String> map);

    void setAnonymous(boolean z);

    @Deprecated
    void setClickstreamUserAgent(String str);

    void setNonAnonymousCustomerId(String str);

    void setNonAnonymousSessionId(String str);

    void startTimer(String str);

    void stopTimer(String str);
}
