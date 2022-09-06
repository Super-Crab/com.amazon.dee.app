package com.amazon.latencyinfra;

import java.util.HashMap;
import java.util.Map;
/* loaded from: classes12.dex */
class LatencyTimeline {
    private String metaData;
    private LatencyRecorderOption options;
    private final Long startTimeInMills = Long.valueOf(System.currentTimeMillis());
    private Map<String, Long> events = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public LatencyTimeline(String str, LatencyRecorderOption latencyRecorderOption) {
        this.metaData = str;
        this.options = latencyRecorderOption;
    }

    private Long getDuration() {
        return Long.valueOf(Long.valueOf(System.currentTimeMillis()).longValue() - this.startTimeInMills.longValue());
    }

    private Long getDurationFromMills(Long l) {
        return Long.valueOf(l.longValue() - this.startTimeInMills.longValue());
    }

    public String getDefaultMetaData() {
        return this.metaData;
    }

    public Map<String, Long> getEvents() {
        return this.events;
    }

    public LatencyRecorderOption getOptions() {
        return this.options;
    }

    public Long getStartTimeInMills() {
        return this.startTimeInMills;
    }

    public void recordEvent(String str) {
        this.events.put(str, getDuration());
    }

    public void recordEvents(Map<String, Long> map) {
        for (Map.Entry<String, Long> entry : map.entrySet()) {
            this.events.put(entry.getKey(), getDurationFromMills(entry.getValue()));
        }
    }

    public Long stop() {
        return getDuration();
    }
}
