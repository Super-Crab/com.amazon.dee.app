package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceDiscoveryStream;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceEventStream;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.reactivex.rxjava3.disposables.Disposable;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ProvisioningManagerModule_GetProvisioningManagerDisposableFactory implements Factory<Disposable> {
    private final Provider<DeviceDiscoveryStream> deviceDiscoveryStreamProvider;
    private final Provider<DeviceEventStream> deviceEventStreamProvider;
    private final ProvisioningManagerModule module;

    public ProvisioningManagerModule_GetProvisioningManagerDisposableFactory(ProvisioningManagerModule provisioningManagerModule, Provider<DeviceEventStream> provider, Provider<DeviceDiscoveryStream> provider2) {
        this.module = provisioningManagerModule;
        this.deviceEventStreamProvider = provider;
        this.deviceDiscoveryStreamProvider = provider2;
    }

    public static ProvisioningManagerModule_GetProvisioningManagerDisposableFactory create(ProvisioningManagerModule provisioningManagerModule, Provider<DeviceEventStream> provider, Provider<DeviceDiscoveryStream> provider2) {
        return new ProvisioningManagerModule_GetProvisioningManagerDisposableFactory(provisioningManagerModule, provider, provider2);
    }

    public static Disposable provideInstance(ProvisioningManagerModule provisioningManagerModule, Provider<DeviceEventStream> provider, Provider<DeviceDiscoveryStream> provider2) {
        return proxyGetProvisioningManagerDisposable(provisioningManagerModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static Disposable proxyGetProvisioningManagerDisposable(ProvisioningManagerModule provisioningManagerModule, DeviceEventStream deviceEventStream, DeviceDiscoveryStream deviceDiscoveryStream) {
        return (Disposable) Preconditions.checkNotNull(provisioningManagerModule.getProvisioningManagerDisposable(deviceEventStream, deviceDiscoveryStream), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Disposable mo10268get() {
        return provideInstance(this.module, this.deviceEventStreamProvider, this.deviceDiscoveryStreamProvider);
    }
}
