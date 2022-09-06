package com.amazon.whisperjoin.metrics;
/* loaded from: classes13.dex */
public enum WhisperJoinMetricName {
    SUCCESS("Success"),
    FAILURE("Failure");
    
    private final String mName;

    WhisperJoinMetricName(String str) {
        this.mName = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.mName;
    }
}
