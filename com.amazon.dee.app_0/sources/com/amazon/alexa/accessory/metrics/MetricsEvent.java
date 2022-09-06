package com.amazon.alexa.accessory.metrics;

import java.util.Map;
/* loaded from: classes.dex */
public interface MetricsEvent {
    Map<String, Integer> getCounterEntries();

    long getEventDate();

    String getEventName();

    Map<String, String> getStringEntries();

    Map<String, Double> getTimerEntries();
}
