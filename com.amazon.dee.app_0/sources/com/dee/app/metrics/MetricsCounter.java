package com.dee.app.metrics;
@Deprecated
/* loaded from: classes2.dex */
public interface MetricsCounter extends AlexaMetricsEvent {
    double getCount();

    void incrementCounter();

    void incrementCounterByValue(double d);

    void restartCounter();
}
