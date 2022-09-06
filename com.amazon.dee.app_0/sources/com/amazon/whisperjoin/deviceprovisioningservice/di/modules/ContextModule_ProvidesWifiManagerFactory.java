package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import android.content.Context;
import android.net.wifi.WifiManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ContextModule_ProvidesWifiManagerFactory implements Factory<WifiManager> {
    private final Provider<Context> contextProvider;
    private final ContextModule module;

    public ContextModule_ProvidesWifiManagerFactory(ContextModule contextModule, Provider<Context> provider) {
        this.module = contextModule;
        this.contextProvider = provider;
    }

    public static ContextModule_ProvidesWifiManagerFactory create(ContextModule contextModule, Provider<Context> provider) {
        return new ContextModule_ProvidesWifiManagerFactory(contextModule, provider);
    }

    public static WifiManager provideInstance(ContextModule contextModule, Provider<Context> provider) {
        return proxyProvidesWifiManager(contextModule, provider.mo10268get());
    }

    public static WifiManager proxyProvidesWifiManager(ContextModule contextModule, Context context) {
        return (WifiManager) Preconditions.checkNotNull(contextModule.providesWifiManager(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WifiManager mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
