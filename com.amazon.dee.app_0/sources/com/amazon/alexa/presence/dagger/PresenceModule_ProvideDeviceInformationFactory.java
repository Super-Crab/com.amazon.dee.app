package com.amazon.alexa.presence.dagger;

import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class PresenceModule_ProvideDeviceInformationFactory implements Factory<DeviceInformation> {
    private final Provider<ComponentRegistry> applicationComponentsProvider;

    public PresenceModule_ProvideDeviceInformationFactory(Provider<ComponentRegistry> provider) {
        this.applicationComponentsProvider = provider;
    }

    public static PresenceModule_ProvideDeviceInformationFactory create(Provider<ComponentRegistry> provider) {
        return new PresenceModule_ProvideDeviceInformationFactory(provider);
    }

    public static DeviceInformation provideInstance(Provider<ComponentRegistry> provider) {
        return proxyProvideDeviceInformation(provider.mo10268get());
    }

    public static DeviceInformation proxyProvideDeviceInformation(ComponentRegistry componentRegistry) {
        return (DeviceInformation) Preconditions.checkNotNull(PresenceModule.provideDeviceInformation(componentRegistry), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DeviceInformation mo10268get() {
        return provideInstance(this.applicationComponentsProvider);
    }
}
