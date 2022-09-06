package com.amazon.deecomms.calling.incallexperiences.effects.datachannel.eventPayloads;

import androidx.annotation.NonNull;
/* loaded from: classes12.dex */
public final class AckEvent {
    private String effectsSessionId;
    private String requestId;

    /* loaded from: classes12.dex */
    public static final class Builder {
        private String effectsSessionId;
        private String requestId;

        public AckEvent build() {
            return new AckEvent(this.requestId, this.effectsSessionId, null);
        }

        public Builder effectsSessionId(@NonNull String str) {
            this.effectsSessionId = str;
            return this;
        }

        public Builder requestId(@NonNull String str) {
            this.requestId = str;
            return this;
        }
    }

    private AckEvent(@NonNull String str, @NonNull String str2) {
        this.requestId = str;
        this.effectsSessionId = str2;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getEffectsSessionId() {
        return this.effectsSessionId;
    }

    public String getRequestId() {
        return this.requestId;
    }

    /* synthetic */ AckEvent(String str, String str2, AnonymousClass1 anonymousClass1) {
        this.requestId = str;
        this.effectsSessionId = str2;
    }
}
