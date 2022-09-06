package com.amazon.deecomms.calling.incallexperiences.effects.datachannel.eventPayloads;

import androidx.annotation.NonNull;
import java.util.List;
/* loaded from: classes12.dex */
public final class CurrentStatusEvent {
    private List<String> activeEffects;
    private String effectsSessionId;
    private String message;
    private String requestId;
    private String status;

    /* loaded from: classes12.dex */
    public static final class Builder {
        private List<String> activeEffects;
        private String effectsSessionId;
        private String message;
        private String requestId;
        private String status;

        public Builder activeEffects(@NonNull List<String> list) {
            this.activeEffects = list;
            return this;
        }

        public CurrentStatusEvent build() {
            return new CurrentStatusEvent(this.requestId, this.effectsSessionId, this.status, this.activeEffects, this.message);
        }

        public Builder effectsSessionId(@NonNull String str) {
            this.effectsSessionId = str;
            return this;
        }

        public Builder message(String str) {
            this.message = str;
            return this;
        }

        public Builder requestId(@NonNull String str) {
            this.requestId = str;
            return this;
        }

        public Builder status(@NonNull String str) {
            this.status = str;
            return this;
        }
    }

    /* loaded from: classes12.dex */
    public enum Status {
        SUCCESS("success"),
        FAILED("failed");
        
        private String value;

        Status(String str) {
            this.value = str;
        }

        @Override // java.lang.Enum
        @NonNull
        public String toString() {
            return this.value;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public List<String> getActiveEffects() {
        return this.activeEffects;
    }

    public String getEffectsSessionId() {
        return this.effectsSessionId;
    }

    public String getMessage() {
        return this.message;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public String getStatus() {
        return this.status;
    }

    private CurrentStatusEvent(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull List<String> list, String str4) {
        this.requestId = str;
        this.effectsSessionId = str2;
        this.status = str3;
        this.activeEffects = list;
        this.message = str4;
    }
}
