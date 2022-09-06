package com.amazon.alexa.accessory.metrics;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes.dex */
public final class CustomMetricsEvent implements MetricsEvent {
    private final Map<String, Integer> counters;
    private long eventDate;
    private final String eventName;
    private final Map<String, String> strings;
    private final Map<String, Double> timers;

    public CustomMetricsEvent(MetricsEvent metricsEvent) {
        Preconditions.notNull(metricsEvent, "metricsEvent");
        this.timers = new HashMap(metricsEvent.getTimerEntries());
        this.counters = new HashMap(metricsEvent.getCounterEntries());
        this.strings = new HashMap(metricsEvent.getStringEntries());
        this.eventName = metricsEvent.getEventName();
        this.eventDate = metricsEvent.getEventDate();
    }

    @Override // com.amazon.alexa.accessory.metrics.MetricsEvent
    public Map<String, Integer> getCounterEntries() {
        return Collections.unmodifiableMap(this.counters);
    }

    @Override // com.amazon.alexa.accessory.metrics.MetricsEvent
    public long getEventDate() {
        return this.eventDate;
    }

    @Override // com.amazon.alexa.accessory.metrics.MetricsEvent
    public String getEventName() {
        return this.eventName;
    }

    @Override // com.amazon.alexa.accessory.metrics.MetricsEvent
    public Map<String, String> getStringEntries() {
        return Collections.unmodifiableMap(this.strings);
    }

    @Override // com.amazon.alexa.accessory.metrics.MetricsEvent
    public Map<String, Double> getTimerEntries() {
        return Collections.unmodifiableMap(this.timers);
    }

    public void putCounter(String str, int i) {
        Preconditions.notEmpty(str, "key");
        this.counters.put(str, Integer.valueOf(i));
    }

    public void putString(String str, String str2) {
        Preconditions.notEmpty(str, "key");
        Preconditions.notNull(str2, "value");
        this.strings.put(str, str2);
    }

    public void putTimer(String str, double d) {
        Preconditions.notEmpty(str, "key");
        this.timers.put(str, Double.valueOf(d));
    }

    public void setEventDate(long j) {
        this.eventDate = j;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CustomMetricsEvent{timers=");
        outline107.append(this.timers);
        outline107.append(", counters=");
        outline107.append(this.counters);
        outline107.append(", strings=");
        outline107.append(this.strings);
        outline107.append(", eventName='");
        GeneratedOutlineSupport1.outline176(outline107, this.eventName, Chars.QUOTE, ", eventDate=");
        outline107.append(this.eventDate);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }

    public CustomMetricsEvent(String str) {
        Preconditions.notEmpty(str, JsonFields.EVENT_NAME);
        this.eventName = str;
        this.timers = new HashMap();
        this.counters = new HashMap();
        this.strings = new HashMap();
        this.eventDate = System.currentTimeMillis();
    }
}
