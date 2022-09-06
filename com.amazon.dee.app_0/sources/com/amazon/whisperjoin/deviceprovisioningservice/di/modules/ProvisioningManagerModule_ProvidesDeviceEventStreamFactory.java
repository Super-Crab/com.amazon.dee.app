package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.device.ProvisioningManagerProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceEventStream;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ProvisioningManagerModule_ProvidesDeviceEventStreamFactory implements Factory<DeviceEventStream> {
    private final ProvisioningManagerModule module;
    private final Provider<ProvisioningManagerProvider> provisioningManagerProvider;

    public ProvisioningManagerModule_ProvidesDeviceEventStreamFactory(ProvisioningManagerModule provisioningManagerModule, Provider<ProvisioningManagerProvider> provider) {
        this.module = provisioningManagerModule;
        this.provisioningManagerProvider = provider;
    }

    public static ProvisioningManagerModule_ProvidesDeviceEventStreamFactory create(ProvisioningManagerModule provisioningManagerModule, Provider<ProvisioningManagerProvider> provider) {
        return new ProvisioningManagerModule_ProvidesDeviceEventStreamFactory(provisioningManagerModule, provider);
    }

    public static DeviceEventStream provideInstance(ProvisioningManagerModule provisioningManagerModule, Provider<ProvisioningManagerProvider> provider) {
        return proxyProvidesDeviceEventStream(provisioningManagerModule, provider.mo10268get());
    }

    public static DeviceEventStream proxyProvidesDeviceEventStream(ProvisioningManagerModule provisioningManagerModule, ProvisioningManagerProvider provisioningManagerProvider) {
        return (DeviceEventStream) Preconditions.checkNotNull(provisioningManagerModule.providesDeviceEventStream(provisioningManagerProvider), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DeviceEventStream mo10268get() {
        return provideInstance(this.module, this.provisioningManagerProvider);
    }
}
