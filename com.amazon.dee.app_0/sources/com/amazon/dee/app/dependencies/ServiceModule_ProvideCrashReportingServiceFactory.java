package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.crashreporting.CrashReportingService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideCrashReportingServiceFactory implements Factory<CrashReportingService> {
    private final Provider<Context> contextProvider;
    private final ServiceModule module;

    public ServiceModule_ProvideCrashReportingServiceFactory(ServiceModule serviceModule, Provider<Context> provider) {
        this.module = serviceModule;
        this.contextProvider = provider;
    }

    public static ServiceModule_ProvideCrashReportingServiceFactory create(ServiceModule serviceModule, Provider<Context> provider) {
        return new ServiceModule_ProvideCrashReportingServiceFactory(serviceModule, provider);
    }

    public static CrashReportingService provideInstance(ServiceModule serviceModule, Provider<Context> provider) {
        return proxyProvideCrashReportingService(serviceModule, provider.mo10268get());
    }

    public static CrashReportingService proxyProvideCrashReportingService(ServiceModule serviceModule, Context context) {
        return (CrashReportingService) Preconditions.checkNotNull(serviceModule.provideCrashReportingService(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CrashReportingService mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
