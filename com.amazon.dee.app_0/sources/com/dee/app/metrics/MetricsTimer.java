package com.dee.app.metrics;
@Deprecated
/* loaded from: classes2.dex */
public interface MetricsTimer extends AlexaMetricsEvent {
    void finishTimer();

    void finishTimer(Long l);

    long getElapsedTime();

    void pauseTimer();

    void resumeTimer();
}
