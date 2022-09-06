package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import android.content.Context;
import com.amazon.whisperjoin.deviceprovisioningservice.service.power.DevicePowerStatusProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class UtilModule_ProvidesDevicePowerStatsProviderFactory implements Factory<DevicePowerStatusProvider> {
    private final Provider<Context> contextProvider;
    private final UtilModule module;

    public UtilModule_ProvidesDevicePowerStatsProviderFactory(UtilModule utilModule, Provider<Context> provider) {
        this.module = utilModule;
        this.contextProvider = provider;
    }

    public static UtilModule_ProvidesDevicePowerStatsProviderFactory create(UtilModule utilModule, Provider<Context> provider) {
        return new UtilModule_ProvidesDevicePowerStatsProviderFactory(utilModule, provider);
    }

    public static DevicePowerStatusProvider provideInstance(UtilModule utilModule, Provider<Context> provider) {
        return proxyProvidesDevicePowerStatsProvider(utilModule, provider.mo10268get());
    }

    public static DevicePowerStatusProvider proxyProvidesDevicePowerStatsProvider(UtilModule utilModule, Context context) {
        return (DevicePowerStatusProvider) Preconditions.checkNotNull(utilModule.providesDevicePowerStatsProvider(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DevicePowerStatusProvider mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
