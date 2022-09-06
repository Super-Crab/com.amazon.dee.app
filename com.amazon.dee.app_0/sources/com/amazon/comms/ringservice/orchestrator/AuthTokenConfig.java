package com.amazon.comms.ringservice.orchestrator;
/* loaded from: classes12.dex */
public final class AuthTokenConfig {
    String authToken;
    int authTokenIntervalInSecs;
    boolean forceRegister;

    public AuthTokenConfig(String str, int i, boolean z) {
        this.authToken = str;
        this.authTokenIntervalInSecs = i;
        this.forceRegister = z;
    }

    public String getAuthToken() {
        return this.authToken;
    }

    public int getAuthTokenIntervalInSecs() {
        return this.authTokenIntervalInSecs;
    }

    public boolean isForceRegister() {
        return this.forceRegister;
    }
}
