package com.amazon.whisperjoin.metrics;
/* loaded from: classes13.dex */
public enum MetricsProgramName {
    WJv2Provisionable("WJv2Provisionable"),
    WJv2Provisioner("WJv2Provisioner");
    
    private final String mProgramName;

    MetricsProgramName(String str) {
        this.mProgramName = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.mProgramName;
    }
}
