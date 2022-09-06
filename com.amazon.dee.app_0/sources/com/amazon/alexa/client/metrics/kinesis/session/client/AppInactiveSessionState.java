package com.amazon.alexa.client.metrics.kinesis.session.client;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.utils.TimeProvider;
/* loaded from: classes6.dex */
public class AppInactiveSessionState extends AppSessionClientState {
    private static final String TAG = "AppInactiveSessionState";

    public AppInactiveSessionState(AppDefaultSessionClient appDefaultSessionClient) {
        this(appDefaultSessionClient, new TimeProvider());
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.session.client.AppSessionClientState
    public void pause() {
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.session.client.AppSessionClientState
    public void resume() {
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.session.client.AppSessionClientState
    public void start() {
        super.executeStart();
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.session.client.AppSessionClientState
    public void stop() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public AppInactiveSessionState(AppDefaultSessionClient appDefaultSessionClient, TimeProvider timeProvider) {
        super(appDefaultSessionClient, timeProvider);
    }
}
