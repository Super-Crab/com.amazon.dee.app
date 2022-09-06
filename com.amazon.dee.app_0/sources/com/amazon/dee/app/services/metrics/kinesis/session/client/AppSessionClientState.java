package com.amazon.dee.app.services.metrics.kinesis.session.client;

import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.metrics.kinesis.session.AppSession;
import com.amazon.dee.app.services.metrics.kinesis.session.client.AppDefaultSessionClient;
/* loaded from: classes12.dex */
public abstract class AppSessionClientState {
    private static final String TAG = Log.tag(AppSessionClientState.class);
    protected final AppDefaultSessionClient client;

    public AppSessionClientState(AppDefaultSessionClient appDefaultSessionClient) {
        this.client = appDefaultSessionClient;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void executePause() {
        this.client.appSession.pause();
        String str = "AppSession Paused: " + this.client.appSession.getSessionID();
        this.client.fireSessionPauseEvent();
        AppDefaultSessionClient appDefaultSessionClient = this.client;
        appDefaultSessionClient.appSessionStore.storeSession(appDefaultSessionClient.appSession);
        this.client.changeState(AppDefaultSessionClient.SessionState.PAUSED);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void executeResume() {
        this.client.appSession.resume();
        this.client.fireSessionResumeEvent();
        String str = "AppSession Resumed: " + this.client.appSession.getSessionID();
        this.client.changeState(AppDefaultSessionClient.SessionState.ACTIVE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void executeStart() {
        AppDefaultSessionClient appDefaultSessionClient = this.client;
        appDefaultSessionClient.appSession = AppSession.newInstance(appDefaultSessionClient.kinesisContext);
        this.client.fireSessionStartEvent();
        this.client.changeState(AppDefaultSessionClient.SessionState.ACTIVE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void executeStop() {
        if (!this.client.appSession.isPaused()) {
            this.client.appSession.pause();
        }
        this.client.fireSessionStopEvent();
        AppDefaultSessionClient appDefaultSessionClient = this.client;
        appDefaultSessionClient.appSession = null;
        appDefaultSessionClient.changeState(AppDefaultSessionClient.SessionState.INACTIVE);
    }

    public abstract void pause();

    public abstract void resume();

    public abstract void start();

    public abstract void stop();
}
