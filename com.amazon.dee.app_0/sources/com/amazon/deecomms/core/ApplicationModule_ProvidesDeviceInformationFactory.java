package com.amazon.deecomms.core;

import com.amazon.alexa.device.api.DeviceInformation;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class ApplicationModule_ProvidesDeviceInformationFactory implements Factory<DeviceInformation> {
    private final ApplicationModule module;

    public ApplicationModule_ProvidesDeviceInformationFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvidesDeviceInformationFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvidesDeviceInformationFactory(applicationModule);
    }

    public static DeviceInformation provideInstance(ApplicationModule applicationModule) {
        return (DeviceInformation) Preconditions.checkNotNull(applicationModule.providesDeviceInformation(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static DeviceInformation proxyProvidesDeviceInformation(ApplicationModule applicationModule) {
        return (DeviceInformation) Preconditions.checkNotNull(applicationModule.providesDeviceInformation(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DeviceInformation mo10268get() {
        return provideInstance(this.module);
    }
}
