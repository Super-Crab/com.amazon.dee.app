package com.amazon.alexa.client.metrics.core;
@Deprecated
/* loaded from: classes6.dex */
public interface MetricsTimer extends AlexaMetricsEvent {
    void finishTimer();

    void finishTimer(Long l);

    long getElapsedTime();

    void pauseTimer();

    void resumeTimer();
}
