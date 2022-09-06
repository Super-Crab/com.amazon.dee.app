package com.amazon.latencyinfra;
/* loaded from: classes12.dex */
public enum SingleLatencyAction {
    RECORD_DURATION_FROM_APP_START("record"),
    RECORD_DURATION_FROM_CACHED_TIMESTAMP("recordWithData");
    
    private final String type;

    SingleLatencyAction(String str) {
        this.type = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.type;
    }
}
