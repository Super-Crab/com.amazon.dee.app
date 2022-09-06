package com.amazon.dee.app.services.metrics.kinesis.session;
/* loaded from: classes12.dex */
public interface AppSessionClient {
    void fireSessionPauseEvent();

    void fireSessionResumeEvent();

    void fireSessionStartEvent();

    void fireSessionStopEvent();

    void pauseSession();

    void registerSessionObserver(SessionObserver sessionObserver);

    void resumeSession();

    void startSession();

    void stopSession();

    void unregisterSessionObserver(SessionObserver sessionObserver);
}
