package com.amazon.dee.app.dependencies;

import com.amazon.alexa.crashreporting.CrashReportingService;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideCrashMetadataFactory implements Factory<CrashMetadata> {
    private final Provider<CrashReportingService> crashReportingServiceProvider;
    private final ServiceModule module;

    public ServiceModule_ProvideCrashMetadataFactory(ServiceModule serviceModule, Provider<CrashReportingService> provider) {
        this.module = serviceModule;
        this.crashReportingServiceProvider = provider;
    }

    public static ServiceModule_ProvideCrashMetadataFactory create(ServiceModule serviceModule, Provider<CrashReportingService> provider) {
        return new ServiceModule_ProvideCrashMetadataFactory(serviceModule, provider);
    }

    public static CrashMetadata provideInstance(ServiceModule serviceModule, Provider<CrashReportingService> provider) {
        return proxyProvideCrashMetadata(serviceModule, DoubleCheck.lazy(provider));
    }

    public static CrashMetadata proxyProvideCrashMetadata(ServiceModule serviceModule, Lazy<CrashReportingService> lazy) {
        return (CrashMetadata) Preconditions.checkNotNull(serviceModule.provideCrashMetadata(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CrashMetadata mo10268get() {
        return provideInstance(this.module, this.crashReportingServiceProvider);
    }
}
