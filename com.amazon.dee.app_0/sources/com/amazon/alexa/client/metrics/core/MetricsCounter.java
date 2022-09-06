package com.amazon.alexa.client.metrics.core;
@Deprecated
/* loaded from: classes6.dex */
public interface MetricsCounter extends AlexaMetricsEvent {
    double getCount();

    void incrementCounter();

    void incrementCounterByValue(double d);

    void restartCounter();
}
