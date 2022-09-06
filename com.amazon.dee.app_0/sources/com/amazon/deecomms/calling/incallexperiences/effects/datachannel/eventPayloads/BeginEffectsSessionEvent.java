package com.amazon.deecomms.calling.incallexperiences.effects.datachannel.eventPayloads;

import androidx.annotation.VisibleForTesting;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.model.EffectCategory;
import java.util.List;
import java.util.Objects;
/* loaded from: classes12.dex */
public class BeginEffectsSessionEvent {
    private List<String> activeEffects;
    private List<EffectCategory> availableEffects;
    private String effectsSessionId;

    /* loaded from: classes12.dex */
    public static class Builder {
        private List<String> activeEffects;
        private List<EffectCategory> availableEffects;
        private String effectsSessionId;

        public Builder activeEffects(List<String> list) {
            this.activeEffects = list;
            return this;
        }

        public Builder availableEffects(List<EffectCategory> list) {
            this.availableEffects = list;
            return this;
        }

        public BeginEffectsSessionEvent build() {
            return new BeginEffectsSessionEvent(this.effectsSessionId, this.activeEffects, this.availableEffects);
        }

        public Builder effectsSessionId(String str) {
            this.effectsSessionId = str;
            return this;
        }
    }

    @VisibleForTesting
    BeginEffectsSessionEvent(String str, List<String> list, List<EffectCategory> list2) {
        this.effectsSessionId = str;
        this.activeEffects = list;
        this.availableEffects = list2;
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || BeginEffectsSessionEvent.class != obj.getClass()) {
            return false;
        }
        BeginEffectsSessionEvent beginEffectsSessionEvent = (BeginEffectsSessionEvent) obj;
        return this.effectsSessionId.equals(beginEffectsSessionEvent.effectsSessionId) && this.activeEffects.equals(beginEffectsSessionEvent.activeEffects) && this.availableEffects.equals(beginEffectsSessionEvent.availableEffects);
    }

    public List<String> getActiveEffects() {
        return this.activeEffects;
    }

    public List<EffectCategory> getAvailableEffects() {
        return this.availableEffects;
    }

    public String getEffectsSessionId() {
        return this.effectsSessionId;
    }

    public int hashCode() {
        return Objects.hash(this.effectsSessionId, this.activeEffects, this.availableEffects);
    }

    public void setActiveEffects(List<String> list) {
        this.activeEffects = list;
    }

    public void setAvailableEffects(List<EffectCategory> list) {
        this.availableEffects = list;
    }

    public void setEffectsSessionId(String str) {
        this.effectsSessionId = str;
    }
}
