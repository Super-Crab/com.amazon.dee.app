package com.amazon.alexa.dnssd.dependencies;

import android.net.wifi.WifiManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class DnssdModule_ProvidesWifiManagerFactory implements Factory<WifiManager> {
    private final DnssdModule module;

    public DnssdModule_ProvidesWifiManagerFactory(DnssdModule dnssdModule) {
        this.module = dnssdModule;
    }

    public static DnssdModule_ProvidesWifiManagerFactory create(DnssdModule dnssdModule) {
        return new DnssdModule_ProvidesWifiManagerFactory(dnssdModule);
    }

    public static WifiManager provideInstance(DnssdModule dnssdModule) {
        return proxyProvidesWifiManager(dnssdModule);
    }

    public static WifiManager proxyProvidesWifiManager(DnssdModule dnssdModule) {
        return (WifiManager) Preconditions.checkNotNull(dnssdModule.providesWifiManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WifiManager mo10268get() {
        return provideInstance(this.module);
    }
}
