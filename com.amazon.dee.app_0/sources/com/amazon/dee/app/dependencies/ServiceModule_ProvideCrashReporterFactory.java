package com.amazon.dee.app.dependencies;

import com.amazon.alexa.crashreporting.CrashReportingService;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideCrashReporterFactory implements Factory<CrashReporter> {
    private final Provider<CrashReportingService> crashReportingServiceProvider;
    private final ServiceModule module;

    public ServiceModule_ProvideCrashReporterFactory(ServiceModule serviceModule, Provider<CrashReportingService> provider) {
        this.module = serviceModule;
        this.crashReportingServiceProvider = provider;
    }

    public static ServiceModule_ProvideCrashReporterFactory create(ServiceModule serviceModule, Provider<CrashReportingService> provider) {
        return new ServiceModule_ProvideCrashReporterFactory(serviceModule, provider);
    }

    public static CrashReporter provideInstance(ServiceModule serviceModule, Provider<CrashReportingService> provider) {
        return proxyProvideCrashReporter(serviceModule, DoubleCheck.lazy(provider));
    }

    public static CrashReporter proxyProvideCrashReporter(ServiceModule serviceModule, Lazy<CrashReportingService> lazy) {
        return (CrashReporter) Preconditions.checkNotNull(serviceModule.provideCrashReporter(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CrashReporter mo10268get() {
        return provideInstance(this.module, this.crashReportingServiceProvider);
    }
}
