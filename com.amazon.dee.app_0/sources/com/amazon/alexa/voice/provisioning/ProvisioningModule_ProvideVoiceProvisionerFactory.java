package com.amazon.alexa.voice.provisioning;

import com.amazon.alexa.identity.api.AccountUpgradeService;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.voice.metrics.service.MetricsService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class ProvisioningModule_ProvideVoiceProvisionerFactory implements Factory<FeatureProvisioner> {
    private final Provider<AccountUpgradeService> accountUpgradeServiceProvider;
    private final Provider<MetricsService> metricsServiceProvider;
    private final Provider<NetworkService> networkServiceProvider;

    public ProvisioningModule_ProvideVoiceProvisionerFactory(Provider<AccountUpgradeService> provider, Provider<NetworkService> provider2, Provider<MetricsService> provider3) {
        this.accountUpgradeServiceProvider = provider;
        this.networkServiceProvider = provider2;
        this.metricsServiceProvider = provider3;
    }

    public static ProvisioningModule_ProvideVoiceProvisionerFactory create(Provider<AccountUpgradeService> provider, Provider<NetworkService> provider2, Provider<MetricsService> provider3) {
        return new ProvisioningModule_ProvideVoiceProvisionerFactory(provider, provider2, provider3);
    }

    public static FeatureProvisioner provideInstance(Provider<AccountUpgradeService> provider, Provider<NetworkService> provider2, Provider<MetricsService> provider3) {
        return proxyProvideVoiceProvisioner(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static FeatureProvisioner proxyProvideVoiceProvisioner(AccountUpgradeService accountUpgradeService, NetworkService networkService, MetricsService metricsService) {
        return (FeatureProvisioner) Preconditions.checkNotNull(ProvisioningModule.provideVoiceProvisioner(accountUpgradeService, networkService, metricsService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureProvisioner mo10268get() {
        return provideInstance(this.accountUpgradeServiceProvider, this.networkServiceProvider, this.metricsServiceProvider);
    }
}
