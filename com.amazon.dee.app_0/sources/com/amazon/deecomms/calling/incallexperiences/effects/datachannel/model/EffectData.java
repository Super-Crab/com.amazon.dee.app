package com.amazon.deecomms.calling.incallexperiences.effects.datachannel.model;

import com.amazon.deecomms.calling.incallexperiences.effects.EffectParameter;
import java.util.List;
import java.util.Objects;
/* loaded from: classes12.dex */
public class EffectData {
    private List<EffectIcon> effectIcons;
    private String effectsSessionId;
    private boolean isApplied;
    private List<EffectParameter> parameters;
    private List<String> targets;

    /* loaded from: classes12.dex */
    public static class Builder {
        private List<EffectIcon> effectIcons;
        private String effectsSessionId;
        private boolean isApplied;
        private List<EffectParameter> parameters;
        private List<String> targets;

        public EffectData build() {
            return new EffectData(this.effectsSessionId, this.effectIcons, this.targets, this.parameters, this.isApplied);
        }

        public Builder effectIcons(List<EffectIcon> list) {
            this.effectIcons = list;
            return this;
        }

        public Builder effectsSessionId(String str) {
            this.effectsSessionId = str;
            return this;
        }

        public Builder isApplied(boolean z) {
            this.isApplied = z;
            return this;
        }

        public Builder parameters(List<EffectParameter> list) {
            this.parameters = list;
            return this;
        }

        public Builder targets(List<String> list) {
            this.targets = list;
            return this;
        }
    }

    EffectData(String str, List<EffectIcon> list, List<String> list2, List<EffectParameter> list3, boolean z) {
        this.effectsSessionId = str;
        this.effectIcons = list;
        this.targets = list2;
        this.parameters = list3;
        this.isApplied = z;
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || EffectData.class != obj.getClass()) {
            return false;
        }
        EffectData effectData = (EffectData) obj;
        return this.isApplied == effectData.isApplied && this.effectsSessionId.equals(effectData.effectsSessionId) && this.effectIcons.equals(effectData.effectIcons) && Objects.equals(this.targets, effectData.targets) && Objects.equals(this.parameters, effectData.parameters);
    }

    public List<EffectIcon> getEffectIcons() {
        return this.effectIcons;
    }

    public String getEffectsSessionId() {
        return this.effectsSessionId;
    }

    public List<EffectParameter> getParameters() {
        return this.parameters;
    }

    public List<String> getTargets() {
        return this.targets;
    }

    public int hashCode() {
        return Objects.hash(this.effectsSessionId, this.effectIcons, this.targets, this.parameters, Boolean.valueOf(this.isApplied));
    }

    public boolean isApplied() {
        return this.isApplied;
    }

    public void setApplied(boolean z) {
        this.isApplied = z;
    }

    public void setEffectIcons(List<EffectIcon> list) {
        this.effectIcons = list;
    }

    public void setEffectsSessionId(String str) {
        this.effectsSessionId = str;
    }

    public void setParameters(List<EffectParameter> list) {
        this.parameters = list;
    }

    public void setTargets(List<String> list) {
        this.targets = list;
    }
}
