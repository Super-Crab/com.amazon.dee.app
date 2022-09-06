package com.amazon.alexa.client.metrics.kinesis.session;
/* loaded from: classes6.dex */
public interface AppSessionStore {
    AppSession getSession();

    void storeSession(AppSession appSession);
}
