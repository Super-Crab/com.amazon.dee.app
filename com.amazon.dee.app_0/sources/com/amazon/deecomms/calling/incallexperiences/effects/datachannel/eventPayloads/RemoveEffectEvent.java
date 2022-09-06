package com.amazon.deecomms.calling.incallexperiences.effects.datachannel.eventPayloads;
/* loaded from: classes12.dex */
public final class RemoveEffectEvent {
    private String effectId;
    private String effectsSessionId;
    private String requestId;

    /* loaded from: classes12.dex */
    public static final class Builder {
        private String effectId;
        private String effectsSessionId;
        private String requestId;

        public RemoveEffectEvent build() {
            return new RemoveEffectEvent(this.requestId, this.effectsSessionId, this.effectId);
        }

        public Builder effectId(String str) {
            this.effectId = str;
            return this;
        }

        public Builder effectsSessionId(String str) {
            this.effectsSessionId = str;
            return this;
        }

        public Builder requestId(String str) {
            this.requestId = str;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getEffectId() {
        return this.effectId;
    }

    public String getEffectsSessionId() {
        return this.effectsSessionId;
    }

    public String getRequestId() {
        return this.requestId;
    }

    private RemoveEffectEvent(String str, String str2, String str3) {
        this.requestId = str;
        this.effectsSessionId = str2;
        this.effectId = str3;
    }
}
