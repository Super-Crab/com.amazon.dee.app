package com.amazon.alexa.tarazed.dagger.modules;

import com.amazon.alexa.device.api.DeviceInformation;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class TarazedIntegrationModule_ProvideDeviceInformation$AlexaMobileAndroidTarazedIntegration_releaseFactory implements Factory<DeviceInformation> {
    private final TarazedIntegrationModule module;

    public TarazedIntegrationModule_ProvideDeviceInformation$AlexaMobileAndroidTarazedIntegration_releaseFactory(TarazedIntegrationModule tarazedIntegrationModule) {
        this.module = tarazedIntegrationModule;
    }

    public static TarazedIntegrationModule_ProvideDeviceInformation$AlexaMobileAndroidTarazedIntegration_releaseFactory create(TarazedIntegrationModule tarazedIntegrationModule) {
        return new TarazedIntegrationModule_ProvideDeviceInformation$AlexaMobileAndroidTarazedIntegration_releaseFactory(tarazedIntegrationModule);
    }

    public static DeviceInformation provideInstance(TarazedIntegrationModule tarazedIntegrationModule) {
        return proxyProvideDeviceInformation$AlexaMobileAndroidTarazedIntegration_release(tarazedIntegrationModule);
    }

    public static DeviceInformation proxyProvideDeviceInformation$AlexaMobileAndroidTarazedIntegration_release(TarazedIntegrationModule tarazedIntegrationModule) {
        return (DeviceInformation) Preconditions.checkNotNull(tarazedIntegrationModule.provideDeviceInformation$AlexaMobileAndroidTarazedIntegration_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DeviceInformation mo10268get() {
        return provideInstance(this.module);
    }
}
