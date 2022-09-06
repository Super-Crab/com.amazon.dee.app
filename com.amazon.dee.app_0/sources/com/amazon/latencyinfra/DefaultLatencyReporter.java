package com.amazon.latencyinfra;
/* loaded from: classes12.dex */
public abstract class DefaultLatencyReporter implements LatencyReporter {
    private final LatencyReporterType type;

    public DefaultLatencyReporter(LatencyReporterType latencyReporterType) {
        this.type = latencyReporterType;
    }

    @Override // com.amazon.latencyinfra.LatencyReporter
    public LatencyReporterType getType() {
        return this.type;
    }
}
