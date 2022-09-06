package com.amazon.alexa.alertsca.dependencies;

import com.amazon.alexa.device.api.DeviceInformation;
import dagger.internal.Factory;
import javax.annotation.Nullable;
/* loaded from: classes6.dex */
public final class DeviceInformationModule_ProvideDeviceInformationFactory implements Factory<DeviceInformation> {
    private final DeviceInformationModule module;

    public DeviceInformationModule_ProvideDeviceInformationFactory(DeviceInformationModule deviceInformationModule) {
        this.module = deviceInformationModule;
    }

    public static DeviceInformationModule_ProvideDeviceInformationFactory create(DeviceInformationModule deviceInformationModule) {
        return new DeviceInformationModule_ProvideDeviceInformationFactory(deviceInformationModule);
    }

    @Nullable
    public static DeviceInformation provideInstance(DeviceInformationModule deviceInformationModule) {
        return proxyProvideDeviceInformation(deviceInformationModule);
    }

    @Nullable
    public static DeviceInformation proxyProvideDeviceInformation(DeviceInformationModule deviceInformationModule) {
        return deviceInformationModule.provideDeviceInformation();
    }

    @Override // javax.inject.Provider
    @Nullable
    /* renamed from: get */
    public DeviceInformation mo10268get() {
        return provideInstance(this.module);
    }
}
