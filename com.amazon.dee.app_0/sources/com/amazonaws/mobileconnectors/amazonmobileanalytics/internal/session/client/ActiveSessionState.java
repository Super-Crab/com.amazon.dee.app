package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.session.client;

import android.util.Log;
@Deprecated
/* loaded from: classes13.dex */
public final class ActiveSessionState extends SessionClientState {
    private static final String TAG = "ActiveSessoinState";

    public ActiveSessionState(DefaultSessionClient defaultSessionClient) {
        super(defaultSessionClient);
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.session.client.SessionClientState
    public void pause() {
        super.executePause();
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.session.client.SessionClientState
    public void resume() {
        Log.i(TAG, "Session Resume Failed: Session is already running.");
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.session.client.SessionClientState
    public void start() {
        if (System.currentTimeMillis() - this.client.session.getStartTime() > this.client.getRestartDelay()) {
            super.executeStop();
            super.executeStart();
            return;
        }
        Log.i(TAG, "Session Start Failed: Previous session was started too recently");
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.session.client.SessionClientState
    public void stop() {
        super.executeStop();
    }
}
