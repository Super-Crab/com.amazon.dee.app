package com.amazon.alexa.dnssd.dependencies;

import android.net.wifi.WifiManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class DnssdModule_ProvidesMulticastLockFactory implements Factory<WifiManager.MulticastLock> {
    private final DnssdModule module;
    private final Provider<WifiManager> wifiProvider;

    public DnssdModule_ProvidesMulticastLockFactory(DnssdModule dnssdModule, Provider<WifiManager> provider) {
        this.module = dnssdModule;
        this.wifiProvider = provider;
    }

    public static DnssdModule_ProvidesMulticastLockFactory create(DnssdModule dnssdModule, Provider<WifiManager> provider) {
        return new DnssdModule_ProvidesMulticastLockFactory(dnssdModule, provider);
    }

    public static WifiManager.MulticastLock provideInstance(DnssdModule dnssdModule, Provider<WifiManager> provider) {
        return proxyProvidesMulticastLock(dnssdModule, provider.mo10268get());
    }

    public static WifiManager.MulticastLock proxyProvidesMulticastLock(DnssdModule dnssdModule, WifiManager wifiManager) {
        return (WifiManager.MulticastLock) Preconditions.checkNotNull(dnssdModule.providesMulticastLock(wifiManager), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WifiManager.MulticastLock mo10268get() {
        return provideInstance(this.module, this.wifiProvider);
    }
}
