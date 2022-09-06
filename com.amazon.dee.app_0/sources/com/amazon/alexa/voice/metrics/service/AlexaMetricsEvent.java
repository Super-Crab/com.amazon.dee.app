package com.amazon.alexa.voice.metrics.service;

import java.util.Map;
/* loaded from: classes11.dex */
public interface AlexaMetricsEvent {
    String getComponentName();

    Map<String, Object> getCustomEntries();

    long getEventDate();

    String getEventName();

    void invalidateEvent();

    boolean isInvalidated();
}
