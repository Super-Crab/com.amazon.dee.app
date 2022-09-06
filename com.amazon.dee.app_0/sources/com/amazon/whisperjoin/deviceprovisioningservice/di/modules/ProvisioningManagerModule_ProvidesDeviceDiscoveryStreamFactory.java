package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.device.ProvisioningManagerProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceDiscoveryStream;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ProvisioningManagerModule_ProvidesDeviceDiscoveryStreamFactory implements Factory<DeviceDiscoveryStream> {
    private final ProvisioningManagerModule module;
    private final Provider<ProvisioningManagerProvider> provisioningManagerProvider;

    public ProvisioningManagerModule_ProvidesDeviceDiscoveryStreamFactory(ProvisioningManagerModule provisioningManagerModule, Provider<ProvisioningManagerProvider> provider) {
        this.module = provisioningManagerModule;
        this.provisioningManagerProvider = provider;
    }

    public static ProvisioningManagerModule_ProvidesDeviceDiscoveryStreamFactory create(ProvisioningManagerModule provisioningManagerModule, Provider<ProvisioningManagerProvider> provider) {
        return new ProvisioningManagerModule_ProvidesDeviceDiscoveryStreamFactory(provisioningManagerModule, provider);
    }

    public static DeviceDiscoveryStream provideInstance(ProvisioningManagerModule provisioningManagerModule, Provider<ProvisioningManagerProvider> provider) {
        return proxyProvidesDeviceDiscoveryStream(provisioningManagerModule, provider.mo10268get());
    }

    public static DeviceDiscoveryStream proxyProvidesDeviceDiscoveryStream(ProvisioningManagerModule provisioningManagerModule, ProvisioningManagerProvider provisioningManagerProvider) {
        return (DeviceDiscoveryStream) Preconditions.checkNotNull(provisioningManagerModule.providesDeviceDiscoveryStream(provisioningManagerProvider), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DeviceDiscoveryStream mo10268get() {
        return provideInstance(this.module, this.provisioningManagerProvider);
    }
}
