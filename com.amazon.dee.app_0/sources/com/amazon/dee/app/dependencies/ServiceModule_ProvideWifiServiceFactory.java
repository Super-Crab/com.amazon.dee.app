package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.dee.app.services.wifi.WifiService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideWifiServiceFactory implements Factory<WifiService> {
    private final Provider<Context> contextProvider;
    private final Provider<EnvironmentService> environmentServiceProvider;
    private final Provider<Mobilytics> mobilyticsProvider;
    private final ServiceModule module;

    public ServiceModule_ProvideWifiServiceFactory(ServiceModule serviceModule, Provider<Context> provider, Provider<EnvironmentService> provider2, Provider<Mobilytics> provider3) {
        this.module = serviceModule;
        this.contextProvider = provider;
        this.environmentServiceProvider = provider2;
        this.mobilyticsProvider = provider3;
    }

    public static ServiceModule_ProvideWifiServiceFactory create(ServiceModule serviceModule, Provider<Context> provider, Provider<EnvironmentService> provider2, Provider<Mobilytics> provider3) {
        return new ServiceModule_ProvideWifiServiceFactory(serviceModule, provider, provider2, provider3);
    }

    public static WifiService provideInstance(ServiceModule serviceModule, Provider<Context> provider, Provider<EnvironmentService> provider2, Provider<Mobilytics> provider3) {
        return proxyProvideWifiService(serviceModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static WifiService proxyProvideWifiService(ServiceModule serviceModule, Context context, EnvironmentService environmentService, Mobilytics mobilytics) {
        return (WifiService) Preconditions.checkNotNull(serviceModule.provideWifiService(context, environmentService, mobilytics), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WifiService mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.environmentServiceProvider, this.mobilyticsProvider);
    }
}
