package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import android.bluetooth.le.ScanFilter;
import android.content.Context;
import com.amazon.whisperjoin.common.sharedtypes.configuration.OveractiveConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.device.ProvisioningManagerProvider;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import com.amazon.whisperjoin.metrics.MetricsRecorderProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.List;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ProvisioningManagerModule_ProvidesProvisioningManagerProviderFactory implements Factory<ProvisioningManagerProvider> {
    private final Provider<Context> contextProvider;
    private final Provider<DSSClient> dssClientProvider;
    private final Provider<MetricsRecorderProvider> metricsRecorderProvider;
    private final ProvisioningManagerModule module;
    private final Provider<OveractiveConfiguration> overactiveConfigurationProvider;
    private final Provider<List<ScanFilter>> scanFilterListProvider;

    public ProvisioningManagerModule_ProvidesProvisioningManagerProviderFactory(ProvisioningManagerModule provisioningManagerModule, Provider<Context> provider, Provider<DSSClient> provider2, Provider<MetricsRecorderProvider> provider3, Provider<List<ScanFilter>> provider4, Provider<OveractiveConfiguration> provider5) {
        this.module = provisioningManagerModule;
        this.contextProvider = provider;
        this.dssClientProvider = provider2;
        this.metricsRecorderProvider = provider3;
        this.scanFilterListProvider = provider4;
        this.overactiveConfigurationProvider = provider5;
    }

    public static ProvisioningManagerModule_ProvidesProvisioningManagerProviderFactory create(ProvisioningManagerModule provisioningManagerModule, Provider<Context> provider, Provider<DSSClient> provider2, Provider<MetricsRecorderProvider> provider3, Provider<List<ScanFilter>> provider4, Provider<OveractiveConfiguration> provider5) {
        return new ProvisioningManagerModule_ProvidesProvisioningManagerProviderFactory(provisioningManagerModule, provider, provider2, provider3, provider4, provider5);
    }

    public static ProvisioningManagerProvider provideInstance(ProvisioningManagerModule provisioningManagerModule, Provider<Context> provider, Provider<DSSClient> provider2, Provider<MetricsRecorderProvider> provider3, Provider<List<ScanFilter>> provider4, Provider<OveractiveConfiguration> provider5) {
        return proxyProvidesProvisioningManagerProvider(provisioningManagerModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get());
    }

    public static ProvisioningManagerProvider proxyProvidesProvisioningManagerProvider(ProvisioningManagerModule provisioningManagerModule, Context context, DSSClient dSSClient, MetricsRecorderProvider metricsRecorderProvider, List<ScanFilter> list, OveractiveConfiguration overactiveConfiguration) {
        return (ProvisioningManagerProvider) Preconditions.checkNotNull(provisioningManagerModule.providesProvisioningManagerProvider(context, dSSClient, metricsRecorderProvider, list, overactiveConfiguration), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ProvisioningManagerProvider mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.dssClientProvider, this.metricsRecorderProvider, this.scanFilterListProvider, this.overactiveConfigurationProvider);
    }
}
