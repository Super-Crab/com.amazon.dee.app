package com.amazon.alexa.crashreporting;

import com.amazon.alexa.component.api.ServiceLifecycle;
/* loaded from: classes6.dex */
public interface CrashReportingService extends ServiceLifecycle {

    @FunctionalInterface
    /* loaded from: classes6.dex */
    public interface CrashObserver {
        void onCrash();
    }

    void addObserver(CrashObserver crashObserver);

    void putMetadata(String str, String str2);

    void reportNonFatal(Throwable th);

    void setAccount(String str, String str2, String str3);
}
