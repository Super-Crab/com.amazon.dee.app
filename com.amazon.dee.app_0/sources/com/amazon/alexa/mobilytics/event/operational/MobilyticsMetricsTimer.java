package com.amazon.alexa.mobilytics.event.operational;
/* loaded from: classes9.dex */
public interface MobilyticsMetricsTimer extends MobilyticsOperationalEvent {
    void finishTimer();

    void finishTimer(Long l);

    long getElapsedTime();

    void pauseTimer();

    void resumeTimer();
}
