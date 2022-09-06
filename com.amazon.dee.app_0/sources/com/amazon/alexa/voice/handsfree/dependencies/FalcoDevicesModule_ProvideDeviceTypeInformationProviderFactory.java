package com.amazon.alexa.voice.handsfree.dependencies;

import android.content.Context;
import com.amazon.alexa.handsfree.devices.DeviceTypeInformationProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class FalcoDevicesModule_ProvideDeviceTypeInformationProviderFactory implements Factory<DeviceTypeInformationProvider> {
    private final Provider<Context> contextProvider;
    private final FalcoDevicesModule module;

    public FalcoDevicesModule_ProvideDeviceTypeInformationProviderFactory(FalcoDevicesModule falcoDevicesModule, Provider<Context> provider) {
        this.module = falcoDevicesModule;
        this.contextProvider = provider;
    }

    public static FalcoDevicesModule_ProvideDeviceTypeInformationProviderFactory create(FalcoDevicesModule falcoDevicesModule, Provider<Context> provider) {
        return new FalcoDevicesModule_ProvideDeviceTypeInformationProviderFactory(falcoDevicesModule, provider);
    }

    public static DeviceTypeInformationProvider provideInstance(FalcoDevicesModule falcoDevicesModule, Provider<Context> provider) {
        return proxyProvideDeviceTypeInformationProvider(falcoDevicesModule, provider.mo10268get());
    }

    public static DeviceTypeInformationProvider proxyProvideDeviceTypeInformationProvider(FalcoDevicesModule falcoDevicesModule, Context context) {
        return (DeviceTypeInformationProvider) Preconditions.checkNotNull(falcoDevicesModule.provideDeviceTypeInformationProvider(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DeviceTypeInformationProvider mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
