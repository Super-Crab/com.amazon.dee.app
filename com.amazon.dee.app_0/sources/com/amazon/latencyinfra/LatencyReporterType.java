package com.amazon.latencyinfra;
/* loaded from: classes12.dex */
public enum LatencyReporterType {
    LOG("log"),
    NAMESPACE("namespace"),
    METRIC("metric");
    
    private final String type;

    LatencyReporterType(String str) {
        this.type = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.type;
    }
}
