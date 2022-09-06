package com.amazon.dee.app.services.metrics.kinesis.session.client;
/* loaded from: classes12.dex */
public class AppInactiveSessionState extends AppSessionClientState {
    private static final String TAG = "AppInactiveSessionState";

    public AppInactiveSessionState(AppDefaultSessionClient appDefaultSessionClient) {
        super(appDefaultSessionClient);
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.session.client.AppSessionClientState
    public void pause() {
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.session.client.AppSessionClientState
    public void resume() {
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.session.client.AppSessionClientState
    public void start() {
        super.executeStart();
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.session.client.AppSessionClientState
    public void stop() {
    }
}
