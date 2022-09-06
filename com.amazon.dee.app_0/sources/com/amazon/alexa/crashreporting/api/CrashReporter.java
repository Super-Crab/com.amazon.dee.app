package com.amazon.alexa.crashreporting.api;
/* loaded from: classes6.dex */
public interface CrashReporter {
    @Deprecated
    void reportNonFatal(Throwable th);

    void reportNonFatal(Throwable th, int i);
}
