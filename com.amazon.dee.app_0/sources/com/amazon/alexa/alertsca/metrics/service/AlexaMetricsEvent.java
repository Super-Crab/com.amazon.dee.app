package com.amazon.alexa.alertsca.metrics.service;

import java.util.Map;
/* loaded from: classes6.dex */
public interface AlexaMetricsEvent {
    String getComponentName();

    Map<String, Object> getCustomEntries();

    long getEventDate();

    String getEventName();

    void invalidateEvent();

    boolean isInvalidated();
}
