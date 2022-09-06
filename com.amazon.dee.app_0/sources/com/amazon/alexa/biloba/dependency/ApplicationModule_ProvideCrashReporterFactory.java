package com.amazon.alexa.biloba.dependency;

import com.amazon.alexa.crashreporting.api.CrashReporter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes6.dex */
public final class ApplicationModule_ProvideCrashReporterFactory implements Factory<CrashReporter> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideCrashReporterFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideCrashReporterFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideCrashReporterFactory(applicationModule);
    }

    public static CrashReporter provideInstance(ApplicationModule applicationModule) {
        return proxyProvideCrashReporter(applicationModule);
    }

    public static CrashReporter proxyProvideCrashReporter(ApplicationModule applicationModule) {
        return (CrashReporter) Preconditions.checkNotNull(applicationModule.provideCrashReporter(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CrashReporter mo10268get() {
        return provideInstance(this.module);
    }
}
