package com.amazon.deecomms.alexa.fireos;

import androidx.annotation.NonNull;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes12.dex */
class HalloEventPayload {
    @JsonProperty
    private String clientIdentifier;
    @JsonProperty
    private String state;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HalloEventPayload(@NonNull String str, @NonNull String str2) {
        this.clientIdentifier = str;
        this.state = str2;
    }

    public String getClientIdentifier() {
        return this.clientIdentifier;
    }

    public String getState() {
        return this.state;
    }

    public void setClientIdentifier(@NonNull String str) {
        this.clientIdentifier = str;
    }

    public void setState(@NonNull String str) {
        this.state = str;
    }
}
