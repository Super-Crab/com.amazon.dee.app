package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import android.net.wifi.WifiManager;
import com.amazon.whisperjoin.deviceprovisioningservice.wifi.CurrentWifiNetworkProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class UtilModule_ProvidesCurrentWifiNetworkProviderFactory implements Factory<CurrentWifiNetworkProvider> {
    private final UtilModule module;
    private final Provider<WifiManager> wifiManagerProvider;

    public UtilModule_ProvidesCurrentWifiNetworkProviderFactory(UtilModule utilModule, Provider<WifiManager> provider) {
        this.module = utilModule;
        this.wifiManagerProvider = provider;
    }

    public static UtilModule_ProvidesCurrentWifiNetworkProviderFactory create(UtilModule utilModule, Provider<WifiManager> provider) {
        return new UtilModule_ProvidesCurrentWifiNetworkProviderFactory(utilModule, provider);
    }

    public static CurrentWifiNetworkProvider provideInstance(UtilModule utilModule, Provider<WifiManager> provider) {
        return proxyProvidesCurrentWifiNetworkProvider(utilModule, provider.mo10268get());
    }

    public static CurrentWifiNetworkProvider proxyProvidesCurrentWifiNetworkProvider(UtilModule utilModule, WifiManager wifiManager) {
        return (CurrentWifiNetworkProvider) Preconditions.checkNotNull(utilModule.providesCurrentWifiNetworkProvider(wifiManager), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CurrentWifiNetworkProvider mo10268get() {
        return provideInstance(this.module, this.wifiManagerProvider);
    }
}
