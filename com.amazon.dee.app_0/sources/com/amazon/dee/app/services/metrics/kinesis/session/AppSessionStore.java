package com.amazon.dee.app.services.metrics.kinesis.session;
/* loaded from: classes12.dex */
public interface AppSessionStore {
    AppSession getSession();

    void storeSession(AppSession appSession);
}
