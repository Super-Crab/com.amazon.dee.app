package com.amazon.comms.ringservice.authtoken;
/* loaded from: classes12.dex */
public interface AuthTokenManager {
    void cancelAuthTokenAlarm();

    boolean isAuthTokenValid();

    void setAuthTokenAlarm(int i, long j);
}
