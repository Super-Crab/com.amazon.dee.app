package com.amazon.latencyinfra;

import com.amazon.latencyinfra.LatencyRecorderOption;
/* loaded from: classes12.dex */
public final class SingleEventInputArgument {
    private final Long endTimestamp;
    private final String eventName;
    private final String metaData;
    private final String namespace;
    private final LatencyRecorderOption options;
    private final LatencyType type;

    /* loaded from: classes12.dex */
    public static class Builder {
        private Long endTimestamp;
        private String eventName;
        private String metaData;
        private String namespace;
        private LatencyRecorderOption.Builder options = new LatencyRecorderOption.Builder();

        public SingleEventInputArgument build() {
            return new SingleEventInputArgument(this.eventName, this.namespace, this.metaData, this.endTimestamp, this.options.build());
        }

        public Builder withCustomerOption(boolean z) {
            this.options.withCustomerOption(z);
            return this;
        }

        public Builder withEndTimestamp(Long l) {
            this.endTimestamp = l;
            return this;
        }

        public Builder withEventName(String str) {
            this.eventName = str;
            return this;
        }

        public Builder withLogOption(boolean z) {
            this.options.withLogOption(z);
            return this;
        }

        public Builder withMetaData(String str) {
            if (!str.equals("")) {
                this.metaData = str;
            }
            return this;
        }

        public Builder withMetricsOption(boolean z) {
            this.options.withMetricsOption(z);
            return this;
        }

        public Builder withNamespace(String str) {
            this.namespace = str;
            return this;
        }

        public Builder withNamespaceOption(boolean z) {
            this.options.withNamespaceOption(z);
            return this;
        }

        public Builder withPerfTestOption(boolean z) {
            this.options.withPerfTestOption(z);
            return this;
        }
    }

    public Long getEndTimestamp() {
        return this.endTimestamp;
    }

    public String getEventName() {
        return this.eventName;
    }

    public String getMetaData() {
        return this.metaData;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public LatencyRecorderOption getOptions() {
        return this.options;
    }

    public LatencyType getType() {
        return this.type;
    }

    private SingleEventInputArgument(String str, String str2, String str3, Long l, LatencyRecorderOption latencyRecorderOption) {
        this.type = LatencyType.SINGLE;
        if (str != null && str2 != null) {
            this.eventName = str;
            this.namespace = str2;
            this.metaData = str3;
            this.endTimestamp = l;
            this.options = latencyRecorderOption;
            return;
        }
        throw new IllegalArgumentException("[Latency-Infra] event name, namepace, options should not be empty to recrod single time stamp event.");
    }
}
