package com.amazon.alexa;

import com.amazon.alexa.api.messages.messagereceiver.ExceptionReporter;
import com.amazon.alexa.client.crashreporting.CrashReporter;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: ErrorReporter.java */
@Singleton
/* loaded from: classes.dex */
public class UYN implements ExceptionReporter {
    public final CrashReporter zZm;

    /* compiled from: ErrorReporter.java */
    /* loaded from: classes.dex */
    public static class zZm extends Exception {
        public zZm(Throwable th) {
            super(th);
        }
    }

    @Inject
    public UYN(CrashReporter crashReporter) {
        this.zZm = crashReporter;
    }

    @Override // com.amazon.alexa.api.messages.messagereceiver.ExceptionReporter
    public void onError(Throwable th) {
        this.zZm.notifyHandledException(new zZm(th));
    }
}
