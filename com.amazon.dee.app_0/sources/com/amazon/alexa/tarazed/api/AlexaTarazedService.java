package com.amazon.alexa.tarazed.api;
/* loaded from: classes10.dex */
public interface AlexaTarazedService {
    void endSession();

    void initialize();

    boolean isSessionActive();

    void pauseSession();

    void resumeSession();

    void suspendSession();
}
