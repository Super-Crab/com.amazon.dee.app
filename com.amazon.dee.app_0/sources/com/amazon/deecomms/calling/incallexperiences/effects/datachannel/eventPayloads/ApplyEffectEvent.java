package com.amazon.deecomms.calling.incallexperiences.effects.datachannel.eventPayloads;

import androidx.annotation.NonNull;
import com.amazon.deecomms.calling.incallexperiences.effects.EffectParameter;
import java.util.List;
import java.util.Objects;
/* loaded from: classes12.dex */
public class ApplyEffectEvent {
    private String effectId;
    private String effectsSessionId;
    private List<EffectParameter> parameters;
    private String requestId;

    /* loaded from: classes12.dex */
    public static final class Builder {
        private String effectId;
        private String effectsSessionId;
        private List<EffectParameter> parameters;
        private String requestId;

        public ApplyEffectEvent build() {
            return new ApplyEffectEvent(this.requestId, this.effectsSessionId, this.effectId, this.parameters);
        }

        public Builder withEffectId(@NonNull String str) {
            this.effectId = str;
            return this;
        }

        public Builder withEffectsSessionId(@NonNull String str) {
            this.effectsSessionId = str;
            return this;
        }

        public Builder withParameters(@NonNull List<EffectParameter> list) {
            this.parameters = list;
            return this;
        }

        public Builder withRequestId(@NonNull String str) {
            this.requestId = str;
            return this;
        }
    }

    ApplyEffectEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull List<EffectParameter> list) {
        this.requestId = str;
        this.effectsSessionId = str2;
        this.effectId = str3;
        this.parameters = list;
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ApplyEffectEvent.class != obj.getClass()) {
            return false;
        }
        ApplyEffectEvent applyEffectEvent = (ApplyEffectEvent) obj;
        return this.requestId.equals(applyEffectEvent.requestId) && this.effectsSessionId.equals(applyEffectEvent.effectsSessionId) && this.effectId.equals(applyEffectEvent.effectId) && Objects.equals(this.parameters, applyEffectEvent.parameters);
    }

    public String getEffectId() {
        return this.effectId;
    }

    public String getEffectsSessionId() {
        return this.effectsSessionId;
    }

    public List<EffectParameter> getParameters() {
        return this.parameters;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public int hashCode() {
        return Objects.hash(this.requestId, this.effectsSessionId, this.effectId, this.parameters);
    }
}
