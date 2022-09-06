package com.amazon.alexa.dnssd.dependencies;

import android.net.nsd.NsdManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class DnssdModule_ProvidesNsdManagerFactory implements Factory<NsdManager> {
    private final DnssdModule module;

    public DnssdModule_ProvidesNsdManagerFactory(DnssdModule dnssdModule) {
        this.module = dnssdModule;
    }

    public static DnssdModule_ProvidesNsdManagerFactory create(DnssdModule dnssdModule) {
        return new DnssdModule_ProvidesNsdManagerFactory(dnssdModule);
    }

    public static NsdManager provideInstance(DnssdModule dnssdModule) {
        return proxyProvidesNsdManager(dnssdModule);
    }

    public static NsdManager proxyProvidesNsdManager(DnssdModule dnssdModule) {
        return (NsdManager) Preconditions.checkNotNull(dnssdModule.providesNsdManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NsdManager mo10268get() {
        return provideInstance(this.module);
    }
}
