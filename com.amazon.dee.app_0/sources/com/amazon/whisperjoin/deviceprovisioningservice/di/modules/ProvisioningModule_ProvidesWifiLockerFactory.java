package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import android.content.Context;
import com.amazon.whisperjoin.deviceprovisioningservice.identity.MapAuthenticationProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.wifi.WifiLocker;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ProvisioningModule_ProvidesWifiLockerFactory implements Factory<WifiLocker> {
    private final Provider<Context> contextProvider;
    private final Provider<MapAuthenticationProvider> mapAuthenticationProvider;
    private final ProvisioningModule module;

    public ProvisioningModule_ProvidesWifiLockerFactory(ProvisioningModule provisioningModule, Provider<MapAuthenticationProvider> provider, Provider<Context> provider2) {
        this.module = provisioningModule;
        this.mapAuthenticationProvider = provider;
        this.contextProvider = provider2;
    }

    public static ProvisioningModule_ProvidesWifiLockerFactory create(ProvisioningModule provisioningModule, Provider<MapAuthenticationProvider> provider, Provider<Context> provider2) {
        return new ProvisioningModule_ProvidesWifiLockerFactory(provisioningModule, provider, provider2);
    }

    public static WifiLocker provideInstance(ProvisioningModule provisioningModule, Provider<MapAuthenticationProvider> provider, Provider<Context> provider2) {
        return proxyProvidesWifiLocker(provisioningModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static WifiLocker proxyProvidesWifiLocker(ProvisioningModule provisioningModule, MapAuthenticationProvider mapAuthenticationProvider, Context context) {
        return (WifiLocker) Preconditions.checkNotNull(provisioningModule.providesWifiLocker(mapAuthenticationProvider, context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WifiLocker mo10268get() {
        return provideInstance(this.module, this.mapAuthenticationProvider, this.contextProvider);
    }
}
