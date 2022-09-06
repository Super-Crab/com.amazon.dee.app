package com.amazon.comms.ringservice.dagger;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import org.pjsip.pjsua2.LogWriter;
/* loaded from: classes12.dex */
public enum PjsipModule_ProvideLogWriterFactory implements Factory<LogWriter> {
    INSTANCE;

    public static Factory<LogWriter> create() {
        return INSTANCE;
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public LogWriter mo10268get() {
        return (LogWriter) Preconditions.checkNotNull(PjsipModule.provideLogWriter(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
