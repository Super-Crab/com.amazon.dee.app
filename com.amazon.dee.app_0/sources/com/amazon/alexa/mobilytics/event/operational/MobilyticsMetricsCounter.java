package com.amazon.alexa.mobilytics.event.operational;
/* loaded from: classes9.dex */
public interface MobilyticsMetricsCounter extends MobilyticsOperationalEvent {
    long getCount();

    void incrementCounter();

    void incrementCounterByValue(long j);

    void resetCounter();
}
