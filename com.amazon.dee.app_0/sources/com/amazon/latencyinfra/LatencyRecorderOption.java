package com.amazon.latencyinfra;
/* loaded from: classes12.dex */
public final class LatencyRecorderOption {
    private final boolean logReporterOption;
    private final boolean metricsReporterOption;
    private final boolean namespaceOption;
    private boolean perfTestOption;
    private final boolean reportForCustomerBuild;

    /* loaded from: classes12.dex */
    public static class Builder {
        private boolean logReporterOption;
        private boolean metricsReporterOption;
        private boolean namespaceOption;
        private boolean perfTestOption;
        private boolean reportForCustomerBuild;

        public LatencyRecorderOption build() {
            return new LatencyRecorderOption(this.logReporterOption, this.metricsReporterOption, this.namespaceOption, this.reportForCustomerBuild, this.perfTestOption);
        }

        public Builder withCustomerOption(boolean z) {
            this.reportForCustomerBuild = z;
            return this;
        }

        public Builder withLogOption(boolean z) {
            this.logReporterOption = z;
            return this;
        }

        public Builder withMetricsOption(boolean z) {
            this.metricsReporterOption = z;
            return this;
        }

        public Builder withNamespaceOption(boolean z) {
            this.namespaceOption = z;
            return this;
        }

        public Builder withPerfTestOption(boolean z) {
            this.perfTestOption = z;
            return this;
        }
    }

    public boolean hasLogReporterOption() {
        return this.logReporterOption;
    }

    public boolean hasMetricsReporterOption() {
        return this.metricsReporterOption;
    }

    public boolean hasNamespaceOption() {
        return this.namespaceOption;
    }

    public boolean hasPerfTestOption() {
        return this.perfTestOption;
    }

    public boolean isReportingForCustomerBuild() {
        return this.reportForCustomerBuild;
    }

    private LatencyRecorderOption(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        this.logReporterOption = z;
        this.metricsReporterOption = z2;
        this.namespaceOption = z3;
        this.reportForCustomerBuild = z4;
        this.perfTestOption = z5;
    }
}
