package com.amazon.dee.app.services.metrics.kinesis.session.client;
/* loaded from: classes12.dex */
public class AppPausedSessionState extends AppSessionClientState {
    private static final String TAG = "AppPausedSessionState";

    public AppPausedSessionState(AppDefaultSessionClient appDefaultSessionClient) {
        super(appDefaultSessionClient);
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.session.client.AppSessionClientState
    public void pause() {
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.session.client.AppSessionClientState
    public void resume() {
        if (System.currentTimeMillis() - this.client.appSession.getStopTime().longValue() < this.client.getResumeDelay()) {
            super.executeResume();
        } else {
            start();
        }
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.session.client.AppSessionClientState
    public void start() {
        super.executeStop();
        super.executeStart();
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.session.client.AppSessionClientState
    public void stop() {
        super.executeStop();
    }
}
