package com.amazon.deecomms.calling.incallexperiences.effects.datachannel.model;

import androidx.annotation.NonNull;
import java.util.HashMap;
/* loaded from: classes12.dex */
public class EffectsMetric {
    public static final String COUNT_METRIC_TYPE = "count";
    public static final String LATENCY_METRIC_TYPE = "latency";
    private HashMap<String, String> dimensions;
    private String metricName;
    private String type;
    private Double value;

    /* loaded from: classes12.dex */
    public static final class Builder {
        private HashMap<String, String> dimensions;
        private String metricName;
        private String type;
        private Double value;

        public EffectsMetric build() {
            return new EffectsMetric(this.type, this.metricName, this.value, this.dimensions);
        }

        public Builder withDimensions(@NonNull HashMap<String, String> hashMap) {
            this.dimensions = hashMap;
            return this;
        }

        public Builder withMetricName(@NonNull String str) {
            this.metricName = str;
            return this;
        }

        public Builder withType(@NonNull String str) {
            this.type = str;
            return this;
        }

        public Builder withValue(@NonNull Double d) {
            this.value = d;
            return this;
        }
    }

    /* loaded from: classes12.dex */
    public enum MetricName {
        SEPIA_UPL_APPLY_EFFECT,
        SEPIA_UPL_APPLY_EFFECT_FAILED,
        SEPIA_UPL_APPLY_REACTION,
        SEPIA_UPL_APPLY_REACTION_FAILED,
        SEPIA_UPL_REMOVE_EFFECT,
        SEPIA_UPL_REMOVE_EFFECT_FAILED
    }

    public EffectsMetric(String str, String str2, Double d, HashMap<String, String> hashMap) {
        this.type = str;
        this.metricName = str2;
        this.value = d;
        this.dimensions = hashMap;
    }

    public static Builder builder() {
        return new Builder();
    }

    public HashMap<String, String> getDimensions() {
        return this.dimensions;
    }

    public String getMetricName() {
        return this.metricName;
    }

    public String getType() {
        return this.type;
    }

    public Double getValue() {
        return this.value;
    }

    public void setDimensions(HashMap<String, String> hashMap) {
        this.dimensions = hashMap;
    }

    public void setMetricName(String str) {
        this.metricName = str;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setValue(Double d) {
        this.value = d;
    }
}
