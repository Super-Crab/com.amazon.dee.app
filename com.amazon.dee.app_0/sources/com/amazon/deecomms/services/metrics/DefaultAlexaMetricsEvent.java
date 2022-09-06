package com.amazon.deecomms.services.metrics;

import androidx.annotation.VisibleForTesting;
import com.dee.app.metrics.AlexaMetricsEvent;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes12.dex */
public class DefaultAlexaMetricsEvent implements AlexaMetricsEvent {
    private final String componentName;
    private final long eventDate;
    private final String eventName;
    @VisibleForTesting
    final int priority;
    private final Map<String, Object> customEntries = new HashMap();
    private boolean invalidated = false;

    public DefaultAlexaMetricsEvent(String str, String str2, Map<String, Object> map) {
        this.eventName = str;
        this.componentName = str2;
        if (map != null) {
            this.customEntries.putAll(map);
        }
        this.priority = 0;
        this.eventDate = System.currentTimeMillis();
    }

    @Override // com.dee.app.metrics.AlexaMetricsEvent
    public String getComponentName() {
        return this.componentName;
    }

    @Override // com.dee.app.metrics.AlexaMetricsEvent
    public Map<String, Object> getCustomEntries() {
        return this.customEntries;
    }

    @Override // com.dee.app.metrics.AlexaMetricsEvent
    public long getEventDate() {
        return this.eventDate;
    }

    @Override // com.dee.app.metrics.AlexaMetricsEvent
    public String getEventName() {
        return this.eventName;
    }

    @Override // com.dee.app.metrics.AlexaMetricsEvent
    public void invalidateEvent() {
        this.invalidated = true;
    }

    @Override // com.dee.app.metrics.AlexaMetricsEvent
    public boolean isInvalidated() {
        return this.invalidated;
    }
}
