package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.identity.api.UserIdentityStorage;
import com.amazon.dee.app.services.environment.PfmEnvironmentService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvidePfmEnvironmentServiceFactory implements Factory<PfmEnvironmentService> {
    private final Provider<Context> contextProvider;
    private final Provider<DeviceInformation> deviceInformationProvider;
    private final ServiceModule module;
    private final Provider<UserIdentityStorage> userStorageProvider;

    public ServiceModule_ProvidePfmEnvironmentServiceFactory(ServiceModule serviceModule, Provider<Context> provider, Provider<DeviceInformation> provider2, Provider<UserIdentityStorage> provider3) {
        this.module = serviceModule;
        this.contextProvider = provider;
        this.deviceInformationProvider = provider2;
        this.userStorageProvider = provider3;
    }

    public static ServiceModule_ProvidePfmEnvironmentServiceFactory create(ServiceModule serviceModule, Provider<Context> provider, Provider<DeviceInformation> provider2, Provider<UserIdentityStorage> provider3) {
        return new ServiceModule_ProvidePfmEnvironmentServiceFactory(serviceModule, provider, provider2, provider3);
    }

    public static PfmEnvironmentService provideInstance(ServiceModule serviceModule, Provider<Context> provider, Provider<DeviceInformation> provider2, Provider<UserIdentityStorage> provider3) {
        return proxyProvidePfmEnvironmentService(serviceModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static PfmEnvironmentService proxyProvidePfmEnvironmentService(ServiceModule serviceModule, Context context, DeviceInformation deviceInformation, UserIdentityStorage userIdentityStorage) {
        return (PfmEnvironmentService) Preconditions.checkNotNull(serviceModule.providePfmEnvironmentService(context, deviceInformation, userIdentityStorage), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PfmEnvironmentService mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.deviceInformationProvider, this.userStorageProvider);
    }
}
