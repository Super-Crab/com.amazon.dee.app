package com.amazon.alexa.client.metrics.kinesis.session.client;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.utils.TimeProvider;
/* loaded from: classes6.dex */
public class AppPausedSessionState extends AppSessionClientState {
    private static final String TAG = "AppPausedSessionState";

    public AppPausedSessionState(AppDefaultSessionClient appDefaultSessionClient) {
        this(appDefaultSessionClient, new TimeProvider());
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.session.client.AppSessionClientState
    public void pause() {
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.session.client.AppSessionClientState
    public void resume() {
        if (this.timeProvider.currentTimeMillis() - this.client.appSession.getStopTime().longValue() < this.client.getResumeDelay()) {
            super.executeResume();
        } else {
            start();
        }
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.session.client.AppSessionClientState
    public void start() {
        super.executeStop();
        super.executeStart();
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.session.client.AppSessionClientState
    public void stop() {
        super.executeStop();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public AppPausedSessionState(AppDefaultSessionClient appDefaultSessionClient, TimeProvider timeProvider) {
        super(appDefaultSessionClient, timeProvider);
    }
}
