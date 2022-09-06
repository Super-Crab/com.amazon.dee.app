package com.amazon.latencyinfra;
/* loaded from: classes12.dex */
class LatencyOutputObject {
    private final LatencyReporterArgument argument;
    private final LatencyRecorderOption options;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LatencyOutputObject(LatencyReporterArgument latencyReporterArgument, LatencyRecorderOption latencyRecorderOption) {
        this.argument = latencyReporterArgument;
        this.options = latencyRecorderOption;
    }

    public LatencyReporterArgument getArgument() {
        return this.argument;
    }

    public LatencyRecorderOption getOptions() {
        return this.options;
    }
}
