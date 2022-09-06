package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.session.client;

import android.util.Log;
@Deprecated
/* loaded from: classes13.dex */
public class PausedSessionState extends SessionClientState {
    private static final String TAG = "PausedSessionState";

    public PausedSessionState(DefaultSessionClient defaultSessionClient) {
        super(defaultSessionClient);
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.session.client.SessionClientState
    public void pause() {
        Log.i(TAG, "Session Pause Failed: Session is already paused.");
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.session.client.SessionClientState
    public void resume() {
        if (System.currentTimeMillis() - this.client.session.getStopTime().longValue() < this.client.getResumeDelay()) {
            super.executeResume();
            return;
        }
        Log.i(TAG, "Session has expired. Starting a fresh one...");
        start();
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.session.client.SessionClientState
    public void start() {
        super.executeStop();
        super.executeStart();
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.session.client.SessionClientState
    public void stop() {
        super.executeStop();
    }
}
