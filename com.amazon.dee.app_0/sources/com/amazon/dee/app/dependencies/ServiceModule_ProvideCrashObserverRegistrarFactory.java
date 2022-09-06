package com.amazon.dee.app.dependencies;

import com.amazon.alexa.crashreporting.CrashReportingService;
import com.amazon.alexa.crashreporting.api.CrashObserverRegistrar;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideCrashObserverRegistrarFactory implements Factory<CrashObserverRegistrar> {
    private final Provider<CrashReportingService> crashReportingServiceProvider;
    private final ServiceModule module;

    public ServiceModule_ProvideCrashObserverRegistrarFactory(ServiceModule serviceModule, Provider<CrashReportingService> provider) {
        this.module = serviceModule;
        this.crashReportingServiceProvider = provider;
    }

    public static ServiceModule_ProvideCrashObserverRegistrarFactory create(ServiceModule serviceModule, Provider<CrashReportingService> provider) {
        return new ServiceModule_ProvideCrashObserverRegistrarFactory(serviceModule, provider);
    }

    public static CrashObserverRegistrar provideInstance(ServiceModule serviceModule, Provider<CrashReportingService> provider) {
        return proxyProvideCrashObserverRegistrar(serviceModule, DoubleCheck.lazy(provider));
    }

    public static CrashObserverRegistrar proxyProvideCrashObserverRegistrar(ServiceModule serviceModule, Lazy<CrashReportingService> lazy) {
        return (CrashObserverRegistrar) Preconditions.checkNotNull(serviceModule.provideCrashObserverRegistrar(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CrashObserverRegistrar mo10268get() {
        return provideInstance(this.module, this.crashReportingServiceProvider);
    }
}
