package com.amazon.latencyinfra;
/* loaded from: classes12.dex */
public interface LatencyReporter {
    LatencyReporterType getType();

    void report(LatencyReporterArgument latencyReporterArgument);
}
