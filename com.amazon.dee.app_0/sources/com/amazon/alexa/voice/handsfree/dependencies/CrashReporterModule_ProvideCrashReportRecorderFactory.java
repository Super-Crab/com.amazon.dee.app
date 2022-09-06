package com.amazon.alexa.voice.handsfree.dependencies;

import android.content.Context;
import com.amazon.alexa.handsfree.protocols.utils.CrashReportRecorder;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class CrashReporterModule_ProvideCrashReportRecorderFactory implements Factory<CrashReportRecorder> {
    private final Provider<Context> contextProvider;
    private final CrashReporterModule module;

    public CrashReporterModule_ProvideCrashReportRecorderFactory(CrashReporterModule crashReporterModule, Provider<Context> provider) {
        this.module = crashReporterModule;
        this.contextProvider = provider;
    }

    public static CrashReporterModule_ProvideCrashReportRecorderFactory create(CrashReporterModule crashReporterModule, Provider<Context> provider) {
        return new CrashReporterModule_ProvideCrashReportRecorderFactory(crashReporterModule, provider);
    }

    public static CrashReportRecorder provideInstance(CrashReporterModule crashReporterModule, Provider<Context> provider) {
        return proxyProvideCrashReportRecorder(crashReporterModule, provider.mo10268get());
    }

    public static CrashReportRecorder proxyProvideCrashReportRecorder(CrashReporterModule crashReporterModule, Context context) {
        return (CrashReportRecorder) Preconditions.checkNotNull(crashReporterModule.provideCrashReportRecorder(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CrashReportRecorder mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
