package com.amazon.deecomms.calling.incallexperiences.effects.datachannel.model;

import java.util.Objects;
/* loaded from: classes12.dex */
public class EventMetadata {
    private String requestId;
    private long timestamp;

    /* loaded from: classes12.dex */
    public static class Builder {
        private String requestId;
        private long timestamp;

        public EventMetadata build() {
            return new EventMetadata(this.timestamp, this.requestId);
        }

        public Builder requestId(String str) {
            this.requestId = str;
            return this;
        }

        public Builder timestamp(long j) {
            this.timestamp = j;
            return this;
        }
    }

    protected EventMetadata(long j, String str) {
        this.timestamp = j;
        this.requestId = str;
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || EventMetadata.class != obj.getClass()) {
            return false;
        }
        EventMetadata eventMetadata = (EventMetadata) obj;
        return this.timestamp == eventMetadata.timestamp && Objects.equals(this.requestId, eventMetadata.requestId);
    }

    public String getRequestId() {
        return this.requestId;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public int hashCode() {
        return Objects.hash(Long.valueOf(this.timestamp), this.requestId);
    }

    public void setRequestId(String str) {
        this.requestId = str;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }
}
