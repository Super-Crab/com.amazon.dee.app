package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import android.bluetooth.le.ScanFilter;
import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.List;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class DiscoveryConfigurationModule_ProvidesScanFilterFactory implements Factory<List<ScanFilter>> {
    private final Provider<Context> contextProvider;
    private final DiscoveryConfigurationModule module;

    public DiscoveryConfigurationModule_ProvidesScanFilterFactory(DiscoveryConfigurationModule discoveryConfigurationModule, Provider<Context> provider) {
        this.module = discoveryConfigurationModule;
        this.contextProvider = provider;
    }

    public static DiscoveryConfigurationModule_ProvidesScanFilterFactory create(DiscoveryConfigurationModule discoveryConfigurationModule, Provider<Context> provider) {
        return new DiscoveryConfigurationModule_ProvidesScanFilterFactory(discoveryConfigurationModule, provider);
    }

    public static List<ScanFilter> provideInstance(DiscoveryConfigurationModule discoveryConfigurationModule, Provider<Context> provider) {
        return proxyProvidesScanFilter(discoveryConfigurationModule, provider.mo10268get());
    }

    public static List<ScanFilter> proxyProvidesScanFilter(DiscoveryConfigurationModule discoveryConfigurationModule, Context context) {
        return (List) Preconditions.checkNotNull(discoveryConfigurationModule.providesScanFilter(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public List<ScanFilter> mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
