package com.amazon.alexa.client.metrics.core;

import java.util.Map;
@Deprecated
/* loaded from: classes6.dex */
public interface AlexaMetricsEvent {
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_NORMAL = 0;

    String getComponentName();

    Map<String, Object> getCustomEntries();

    long getEventDate();

    String getEventName();

    void invalidateEvent();

    boolean isInvalidated();
}
