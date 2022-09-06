package com.amazon.alexa.presence.service;
/* loaded from: classes9.dex */
public interface AuthTokenProvider {
    String getAuthToken();

    void refreshAuthToken();
}
