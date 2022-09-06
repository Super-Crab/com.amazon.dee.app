package com.amazon.latencyinfra;

import java.util.Collections;
import java.util.Map;
/* loaded from: classes12.dex */
public final class LatencyReporterArgument {
    private final Map<String, Long> events;
    private final String metaData;
    private final String name;
    private final String namespace;
    private String sourceMetaData;
    private final Long timeInterval;
    private final LatencyType type;

    /* loaded from: classes12.dex */
    public static class Builder {
        private Map<String, Long> events;
        private String metaData;
        private String name;
        private String namespace;
        private String sourceMetaData;
        private Long timeInterval;
        private LatencyType type;

        public LatencyReporterArgument build() {
            return new LatencyReporterArgument(this.type, this.name, this.timeInterval, this.namespace, this.events, this.metaData, this.sourceMetaData);
        }

        public Builder withEvents(Map<String, Long> map) {
            if (map != null) {
                this.events = Collections.unmodifiableMap(map);
            }
            return this;
        }

        public Builder withMetaData(String str) {
            this.metaData = str;
            return this;
        }

        public Builder withName(String str) {
            this.name = str;
            return this;
        }

        public Builder withNamespace(String str) {
            this.namespace = str;
            return this;
        }

        public Builder withSourceMetaData(String str) {
            this.sourceMetaData = str;
            return this;
        }

        public Builder withTimeInterval(Long l) {
            this.timeInterval = l;
            return this;
        }

        public Builder withType(LatencyType latencyType) {
            this.type = latencyType;
            return this;
        }
    }

    public Map<String, Long> getEvents() {
        return this.events;
    }

    public String getMetaData() {
        return this.metaData;
    }

    public String getName() {
        return this.name;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public String getSourceMetaData() {
        return this.sourceMetaData;
    }

    public Long getTimeInterval() {
        return this.timeInterval;
    }

    public LatencyType getType() {
        return this.type;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setSourceMetaData(String str) {
        this.sourceMetaData = str;
    }

    private LatencyReporterArgument(LatencyType latencyType, String str, Long l, String str2, Map<String, Long> map, String str3, String str4) throws IllegalArgumentException {
        if (latencyType != null && str != null && str2 != null && l != null) {
            this.name = str;
            this.type = latencyType;
            this.namespace = str2;
            this.events = map;
            this.timeInterval = l;
            this.metaData = str3;
            this.sourceMetaData = str4;
            return;
        }
        throw new IllegalArgumentException("[Latency Infra]: name, type, timeInterval or namespace should not be null.");
    }
}
