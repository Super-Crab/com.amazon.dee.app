package com.amazon.alexa.client.metrics.mobilytics;

import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class MobilyticsClientModule_ProvidesMobilyticsDeviceProviderFactory implements Factory<MobilyticsDeviceProviderImpl> {
    private final Provider<ClientConfiguration> clientConfigurationProvider;
    private final MobilyticsClientModule module;

    public MobilyticsClientModule_ProvidesMobilyticsDeviceProviderFactory(MobilyticsClientModule mobilyticsClientModule, Provider<ClientConfiguration> provider) {
        this.module = mobilyticsClientModule;
        this.clientConfigurationProvider = provider;
    }

    public static MobilyticsClientModule_ProvidesMobilyticsDeviceProviderFactory create(MobilyticsClientModule mobilyticsClientModule, Provider<ClientConfiguration> provider) {
        return new MobilyticsClientModule_ProvidesMobilyticsDeviceProviderFactory(mobilyticsClientModule, provider);
    }

    public static MobilyticsDeviceProviderImpl provideInstance(MobilyticsClientModule mobilyticsClientModule, Provider<ClientConfiguration> provider) {
        return proxyProvidesMobilyticsDeviceProvider(mobilyticsClientModule, provider.mo10268get());
    }

    public static MobilyticsDeviceProviderImpl proxyProvidesMobilyticsDeviceProvider(MobilyticsClientModule mobilyticsClientModule, ClientConfiguration clientConfiguration) {
        return (MobilyticsDeviceProviderImpl) Preconditions.checkNotNull(mobilyticsClientModule.providesMobilyticsDeviceProvider(clientConfiguration), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MobilyticsDeviceProviderImpl mo10268get() {
        return provideInstance(this.module, this.clientConfigurationProvider);
    }
}
