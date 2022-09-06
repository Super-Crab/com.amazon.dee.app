package com.amazon.alexa.biloba.dependency;

import com.amazon.alexa.device.api.DeviceInformation;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes6.dex */
public final class ApplicationModule_ProvideDeviceInformationFactory implements Factory<DeviceInformation> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideDeviceInformationFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideDeviceInformationFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideDeviceInformationFactory(applicationModule);
    }

    public static DeviceInformation provideInstance(ApplicationModule applicationModule) {
        return proxyProvideDeviceInformation(applicationModule);
    }

    public static DeviceInformation proxyProvideDeviceInformation(ApplicationModule applicationModule) {
        return (DeviceInformation) Preconditions.checkNotNull(applicationModule.provideDeviceInformation(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DeviceInformation mo10268get() {
        return provideInstance(this.module);
    }
}
