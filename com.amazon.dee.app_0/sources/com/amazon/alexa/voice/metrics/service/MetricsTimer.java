package com.amazon.alexa.voice.metrics.service;
/* loaded from: classes11.dex */
public interface MetricsTimer extends AlexaMetricsEvent {
    void finishTimer();

    void finishTimer(Long l);

    long getElapsedTime();

    void pauseTimer();

    void resumeTimer();
}
