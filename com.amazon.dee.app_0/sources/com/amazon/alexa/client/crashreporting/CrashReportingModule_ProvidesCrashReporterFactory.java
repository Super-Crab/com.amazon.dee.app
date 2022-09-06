package com.amazon.alexa.client.crashreporting;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes6.dex */
public final class CrashReportingModule_ProvidesCrashReporterFactory implements Factory<CrashReporter> {
    private final CrashReportingModule module;

    public CrashReportingModule_ProvidesCrashReporterFactory(CrashReportingModule crashReportingModule) {
        this.module = crashReportingModule;
    }

    public static CrashReportingModule_ProvidesCrashReporterFactory create(CrashReportingModule crashReportingModule) {
        return new CrashReportingModule_ProvidesCrashReporterFactory(crashReportingModule);
    }

    public static CrashReporter provideInstance(CrashReportingModule crashReportingModule) {
        return proxyProvidesCrashReporter(crashReportingModule);
    }

    public static CrashReporter proxyProvidesCrashReporter(CrashReportingModule crashReportingModule) {
        return (CrashReporter) Preconditions.checkNotNull(crashReportingModule.providesCrashReporter(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CrashReporter mo10268get() {
        return provideInstance(this.module);
    }
}
