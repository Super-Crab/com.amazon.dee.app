package com.amazon.alexa.client.metrics.kinesis.session.client;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.utils.TimeProvider;
/* loaded from: classes6.dex */
public final class AppActiveSessionState extends AppSessionClientState {
    private static final String TAG = "AppActiveSessionState";

    public AppActiveSessionState(AppDefaultSessionClient appDefaultSessionClient) {
        this(appDefaultSessionClient, new TimeProvider());
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.session.client.AppSessionClientState
    public void pause() {
        super.executePause();
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.session.client.AppSessionClientState
    public void resume() {
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.session.client.AppSessionClientState
    public void start() {
        if (this.timeProvider.currentTimeMillis() - this.client.appSession.getStartTime() > this.client.getRestartDelay()) {
            super.executeStop();
            super.executeStart();
        }
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.session.client.AppSessionClientState
    public void stop() {
        super.executeStop();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public AppActiveSessionState(AppDefaultSessionClient appDefaultSessionClient, TimeProvider timeProvider) {
        super(appDefaultSessionClient, timeProvider);
    }
}
