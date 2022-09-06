package com.amazon.alexa.dnssd.dependencies;

import android.net.nsd.NsdManager;
import android.net.wifi.WifiManager;
import com.amazon.alexa.dnssd.RxNsd;
import com.amazon.alexa.dnssd.util.Metrics;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class DnssdModule_ProvideRxNsdFactory implements Factory<RxNsd> {
    private final Provider<Metrics> metricsProvider;
    private final DnssdModule module;
    private final Provider<WifiManager.MulticastLock> multicastLockProvider;
    private final Provider<NsdManager> nsdManagerProvider;
    private final Provider<WifiManager> wifiManagerProvider;

    public DnssdModule_ProvideRxNsdFactory(DnssdModule dnssdModule, Provider<Metrics> provider, Provider<NsdManager> provider2, Provider<WifiManager> provider3, Provider<WifiManager.MulticastLock> provider4) {
        this.module = dnssdModule;
        this.metricsProvider = provider;
        this.nsdManagerProvider = provider2;
        this.wifiManagerProvider = provider3;
        this.multicastLockProvider = provider4;
    }

    public static DnssdModule_ProvideRxNsdFactory create(DnssdModule dnssdModule, Provider<Metrics> provider, Provider<NsdManager> provider2, Provider<WifiManager> provider3, Provider<WifiManager.MulticastLock> provider4) {
        return new DnssdModule_ProvideRxNsdFactory(dnssdModule, provider, provider2, provider3, provider4);
    }

    public static RxNsd provideInstance(DnssdModule dnssdModule, Provider<Metrics> provider, Provider<NsdManager> provider2, Provider<WifiManager> provider3, Provider<WifiManager.MulticastLock> provider4) {
        return proxyProvideRxNsd(dnssdModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static RxNsd proxyProvideRxNsd(DnssdModule dnssdModule, Metrics metrics, NsdManager nsdManager, WifiManager wifiManager, WifiManager.MulticastLock multicastLock) {
        return (RxNsd) Preconditions.checkNotNull(dnssdModule.provideRxNsd(metrics, nsdManager, wifiManager, multicastLock), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RxNsd mo10268get() {
        return provideInstance(this.module, this.metricsProvider, this.nsdManagerProvider, this.wifiManagerProvider, this.multicastLockProvider);
    }
}
