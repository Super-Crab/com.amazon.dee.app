package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.session.client;

import android.util.Log;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.session.Session;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.session.client.DefaultSessionClient;
@Deprecated
/* loaded from: classes13.dex */
public abstract class SessionClientState {
    private static final String TAG = "SessionClientState";
    protected final DefaultSessionClient client;

    public SessionClientState(DefaultSessionClient defaultSessionClient) {
        this.client = defaultSessionClient;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void executePause() {
        this.client.session.pause();
        String str = "Session Paused: " + this.client.session.getSessionID();
        DefaultSessionClient defaultSessionClient = this.client;
        this.client.eventClient.recordEvent(defaultSessionClient.eventClient.createInternalEvent("_session.pause", defaultSessionClient.session.getStartTime(), null, this.client.session.getSessionDuration()));
        DefaultSessionClient defaultSessionClient2 = this.client;
        defaultSessionClient2.sessionStore.storeSession(defaultSessionClient2.session);
        this.client.changeState(DefaultSessionClient.SessionState.PAUSED);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void executeResume() {
        this.client.session.resume();
        this.client.eventClient.recordEvent(this.client.eventClient.createEvent("_session.resume"));
        Log.i(TAG, "Session Resumed: " + this.client.session.getSessionID());
        this.client.changeState(DefaultSessionClient.SessionState.ACTIVE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void executeStart() {
        DefaultSessionClient defaultSessionClient = this.client;
        defaultSessionClient.session = Session.newInstance(defaultSessionClient.context);
        DefaultSessionClient defaultSessionClient2 = this.client;
        defaultSessionClient2.eventClient.setSessionId(defaultSessionClient2.session.getSessionID());
        DefaultSessionClient defaultSessionClient3 = this.client;
        defaultSessionClient3.eventClient.setSessionStartTime(defaultSessionClient3.session.getStartTime());
        this.client.eventClient.recordEvent(this.client.eventClient.createEvent("_session.start"));
        this.client.changeState(DefaultSessionClient.SessionState.ACTIVE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void executeStop() {
        if (!this.client.session.isPaused()) {
            this.client.session.pause();
        }
        Long valueOf = Long.valueOf(this.client.session.getStopTime() == null ? 0L : this.client.session.getStopTime().longValue());
        DefaultSessionClient defaultSessionClient = this.client;
        this.client.eventClient.recordEvent(defaultSessionClient.eventClient.createInternalEvent("_session.stop", defaultSessionClient.session.getStartTime(), valueOf, this.client.session.getSessionDuration()));
        DefaultSessionClient defaultSessionClient2 = this.client;
        defaultSessionClient2.session = null;
        defaultSessionClient2.changeState(DefaultSessionClient.SessionState.INACTIVE);
    }

    public abstract void pause();

    public abstract void resume();

    public abstract void start();

    public abstract void stop();
}
