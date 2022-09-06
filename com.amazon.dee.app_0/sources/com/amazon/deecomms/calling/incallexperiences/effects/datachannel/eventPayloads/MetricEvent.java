package com.amazon.deecomms.calling.incallexperiences.effects.datachannel.eventPayloads;

import androidx.annotation.NonNull;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.model.EffectsMetric;
import java.util.List;
import java.util.Objects;
/* loaded from: classes12.dex */
public final class MetricEvent {
    private String effectSessionId;
    private List<EffectsMetric> metrics;
    private String requestId;

    /* loaded from: classes12.dex */
    public static final class Builder {
        private String effectsSessionId;
        private List<EffectsMetric> metrics;
        private String requestId;

        public MetricEvent build() {
            return new MetricEvent(this.requestId, this.effectsSessionId, this.metrics);
        }

        public Builder withEffectsSessionId(@NonNull String str) {
            this.effectsSessionId = str;
            return this;
        }

        public Builder withMetrics(@NonNull List<EffectsMetric> list) {
            this.metrics = list;
            return this;
        }

        public Builder withRequestId(@NonNull String str) {
            this.requestId = str;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || MetricEvent.class != obj.getClass()) {
            return false;
        }
        MetricEvent metricEvent = (MetricEvent) obj;
        return this.requestId.equals(metricEvent.requestId) && this.effectSessionId.equals(metricEvent.effectSessionId) && Objects.equals(this.metrics, metricEvent.metrics);
    }

    public String getEffectsSessionId() {
        return this.effectSessionId;
    }

    public List<EffectsMetric> getMetrics() {
        return this.metrics;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public int hashCode() {
        return Objects.hash(this.requestId, this.effectSessionId, this.metrics);
    }

    private MetricEvent(@NonNull String str, @NonNull String str2, @NonNull List<EffectsMetric> list) {
        this.requestId = str;
        this.effectSessionId = str2;
        this.metrics = list;
    }
}
