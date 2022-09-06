package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.session.client;

import android.util.Log;
@Deprecated
/* loaded from: classes13.dex */
public class InactiveSessionState extends SessionClientState {
    private static final String TAG = "InactiveSessionState";

    public InactiveSessionState(DefaultSessionClient defaultSessionClient) {
        super(defaultSessionClient);
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.session.client.SessionClientState
    public void pause() {
        Log.i(TAG, "Session Pause Failed: No session is running.");
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.session.client.SessionClientState
    public void resume() {
        this.client.eventClient.recordEvent(this.client.eventClient.createEvent("_session.resume"));
        Log.i(TAG, "Session Resume Failed: No session is paused.");
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.session.client.SessionClientState
    public void start() {
        Log.i(TAG, "InactiveSession starting");
        super.executeStart();
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.session.client.SessionClientState
    public void stop() {
        Log.i(TAG, "Session Stop Failed: No session is running.");
    }
}
