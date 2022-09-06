package com.amazon.dee.app.dependencies;

import com.amazon.alexa.device.api.DeviceInformation;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideDeviceInformationFactory implements Factory<DeviceInformation> {
    private final ServiceModule module;

    public ServiceModule_ProvideDeviceInformationFactory(ServiceModule serviceModule) {
        this.module = serviceModule;
    }

    public static ServiceModule_ProvideDeviceInformationFactory create(ServiceModule serviceModule) {
        return new ServiceModule_ProvideDeviceInformationFactory(serviceModule);
    }

    public static DeviceInformation provideInstance(ServiceModule serviceModule) {
        return proxyProvideDeviceInformation(serviceModule);
    }

    public static DeviceInformation proxyProvideDeviceInformation(ServiceModule serviceModule) {
        return (DeviceInformation) Preconditions.checkNotNull(serviceModule.provideDeviceInformation(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DeviceInformation mo10268get() {
        return provideInstance(this.module);
    }
}
