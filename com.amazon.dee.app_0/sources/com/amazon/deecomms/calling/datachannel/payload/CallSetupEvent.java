package com.amazon.deecomms.calling.datachannel.payload;

import androidx.annotation.NonNull;
/* loaded from: classes12.dex */
public final class CallSetupEvent {
    private String callMode;

    /* loaded from: classes12.dex */
    public static final class Builder {
        private String callMode;

        public CallSetupEvent build() {
            return new CallSetupEvent(this.callMode, null);
        }

        public Builder callMode(@NonNull String str) {
            this.callMode = str;
            return this;
        }
    }

    private CallSetupEvent(@NonNull String str) {
        this.callMode = str;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getCallMode() {
        return this.callMode;
    }

    /* synthetic */ CallSetupEvent(String str, AnonymousClass1 anonymousClass1) {
        this.callMode = str;
    }
}
