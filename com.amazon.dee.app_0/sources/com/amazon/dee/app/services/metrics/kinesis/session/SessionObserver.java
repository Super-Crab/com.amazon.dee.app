package com.amazon.dee.app.services.metrics.kinesis.session;
/* loaded from: classes12.dex */
public interface SessionObserver {
    void onSessionPause(AppSession appSession);

    void onSessionResume(AppSession appSession);

    void onSessionStart(AppSession appSession);

    void onSessionStop(AppSession appSession);
}
