package com.amazon.latencyinfra;
/* loaded from: classes12.dex */
public enum TimelineLatencyAction {
    START_RECORD_TIMELINE("startTimeline"),
    RECORD_EVENT_IN_TIMELINE("record"),
    RECORD_CACHED_EVENTS_IN_TIMELINE("recrodEvents"),
    END_TIMELINE("endTimeline"),
    RECORD_TIMELINE_WITH_CACHED_START_AND_END_TIMESTAMP("recordWithData");
    
    private final String type;

    TimelineLatencyAction(String str) {
        this.type = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.type;
    }
}
