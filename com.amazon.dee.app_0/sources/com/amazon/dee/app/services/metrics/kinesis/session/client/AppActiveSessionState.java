package com.amazon.dee.app.services.metrics.kinesis.session.client;

import com.amazon.dee.app.services.logging.Log;
/* loaded from: classes12.dex */
public final class AppActiveSessionState extends AppSessionClientState {
    private static final String TAG = Log.tag(AppActiveSessionState.class);

    public AppActiveSessionState(AppDefaultSessionClient appDefaultSessionClient) {
        super(appDefaultSessionClient);
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.session.client.AppSessionClientState
    public void pause() {
        super.executePause();
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.session.client.AppSessionClientState
    public void resume() {
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.session.client.AppSessionClientState
    public void start() {
        if (System.currentTimeMillis() - this.client.appSession.getStartTime().longValue() > this.client.getRestartDelay()) {
            super.executeStop();
            super.executeStart();
        }
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.session.client.AppSessionClientState
    public void stop() {
        super.executeStop();
    }
}
