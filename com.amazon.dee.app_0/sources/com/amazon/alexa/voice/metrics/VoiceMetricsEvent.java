package com.amazon.alexa.voice.metrics;

import com.amazon.alexa.voice.metrics.service.AlexaMetricsEvent;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes11.dex */
public class VoiceMetricsEvent implements AlexaMetricsEvent {
    private final String componentName;
    private final Map<String, Object> customEntries;
    final long eventDate;
    private final String eventName;
    private boolean invalidated = false;

    public VoiceMetricsEvent(String str, String str2, Map<String, Object> map) {
        this.eventName = str;
        this.componentName = str2;
        this.customEntries = map == null ? new HashMap<>() : map;
        this.eventDate = System.currentTimeMillis();
    }

    @Override // com.amazon.alexa.voice.metrics.service.AlexaMetricsEvent
    public String getComponentName() {
        return this.componentName;
    }

    @Override // com.amazon.alexa.voice.metrics.service.AlexaMetricsEvent
    public Map<String, Object> getCustomEntries() {
        return this.customEntries;
    }

    @Override // com.amazon.alexa.voice.metrics.service.AlexaMetricsEvent
    public long getEventDate() {
        return this.eventDate;
    }

    @Override // com.amazon.alexa.voice.metrics.service.AlexaMetricsEvent
    public String getEventName() {
        return this.eventName;
    }

    @Override // com.amazon.alexa.voice.metrics.service.AlexaMetricsEvent
    public void invalidateEvent() {
        this.invalidated = true;
    }

    @Override // com.amazon.alexa.voice.metrics.service.AlexaMetricsEvent
    public boolean isInvalidated() {
        return this.invalidated;
    }
}
