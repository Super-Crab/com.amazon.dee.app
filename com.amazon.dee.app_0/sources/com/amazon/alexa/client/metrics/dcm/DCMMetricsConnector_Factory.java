package com.amazon.alexa.client.metrics.dcm;

import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.client.core.marketplace.MarketplaceAuthority;
import com.amazon.alexa.client.metrics.core.DeviceInformation;
import com.amazon.client.metrics.common.MetricsFactory;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class DCMMetricsConnector_Factory implements Factory<DCMMetricsConnector> {
    private final Provider<ClientConfiguration> clientConfigurationProvider;
    private final Provider<DeviceInformation> deviceInformationProvider;
    private final Provider<MarketplaceAuthority> marketplaceAuthorityProvider;
    private final Provider<MetricsFactory> metricsFactoryProvider;

    public DCMMetricsConnector_Factory(Provider<DeviceInformation> provider, Provider<MetricsFactory> provider2, Provider<MarketplaceAuthority> provider3, Provider<ClientConfiguration> provider4) {
        this.deviceInformationProvider = provider;
        this.metricsFactoryProvider = provider2;
        this.marketplaceAuthorityProvider = provider3;
        this.clientConfigurationProvider = provider4;
    }

    public static DCMMetricsConnector_Factory create(Provider<DeviceInformation> provider, Provider<MetricsFactory> provider2, Provider<MarketplaceAuthority> provider3, Provider<ClientConfiguration> provider4) {
        return new DCMMetricsConnector_Factory(provider, provider2, provider3, provider4);
    }

    public static DCMMetricsConnector newDCMMetricsConnector(DeviceInformation deviceInformation, Lazy<MetricsFactory> lazy, MarketplaceAuthority marketplaceAuthority, Lazy<ClientConfiguration> lazy2) {
        return new DCMMetricsConnector(deviceInformation, lazy, marketplaceAuthority, lazy2);
    }

    public static DCMMetricsConnector provideInstance(Provider<DeviceInformation> provider, Provider<MetricsFactory> provider2, Provider<MarketplaceAuthority> provider3, Provider<ClientConfiguration> provider4) {
        return new DCMMetricsConnector(provider.mo10268get(), DoubleCheck.lazy(provider2), provider3.mo10268get(), DoubleCheck.lazy(provider4));
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DCMMetricsConnector mo10268get() {
        return provideInstance(this.deviceInformationProvider, this.metricsFactoryProvider, this.marketplaceAuthorityProvider, this.clientConfigurationProvider);
    }
}
