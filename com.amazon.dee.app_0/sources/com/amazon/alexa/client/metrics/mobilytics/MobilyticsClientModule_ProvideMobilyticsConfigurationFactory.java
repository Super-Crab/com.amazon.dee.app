package com.amazon.alexa.client.metrics.mobilytics;

import android.content.Context;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.mobilytics.MobilyticsConfiguration;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class MobilyticsClientModule_ProvideMobilyticsConfigurationFactory implements Factory<MobilyticsConfiguration> {
    private final Provider<ClientConfiguration> clientConfigurationProvider;
    private final Provider<Context> contextProvider;
    private final Provider<MobilyticsDeviceProviderImpl> mobilyticsDeviceProvider;
    private final Provider<MobilyticsUserProviderImpl> mobilyticsUserProvider;
    private final MobilyticsClientModule module;

    public MobilyticsClientModule_ProvideMobilyticsConfigurationFactory(MobilyticsClientModule mobilyticsClientModule, Provider<Context> provider, Provider<ClientConfiguration> provider2, Provider<MobilyticsUserProviderImpl> provider3, Provider<MobilyticsDeviceProviderImpl> provider4) {
        this.module = mobilyticsClientModule;
        this.contextProvider = provider;
        this.clientConfigurationProvider = provider2;
        this.mobilyticsUserProvider = provider3;
        this.mobilyticsDeviceProvider = provider4;
    }

    public static MobilyticsClientModule_ProvideMobilyticsConfigurationFactory create(MobilyticsClientModule mobilyticsClientModule, Provider<Context> provider, Provider<ClientConfiguration> provider2, Provider<MobilyticsUserProviderImpl> provider3, Provider<MobilyticsDeviceProviderImpl> provider4) {
        return new MobilyticsClientModule_ProvideMobilyticsConfigurationFactory(mobilyticsClientModule, provider, provider2, provider3, provider4);
    }

    public static MobilyticsConfiguration provideInstance(MobilyticsClientModule mobilyticsClientModule, Provider<Context> provider, Provider<ClientConfiguration> provider2, Provider<MobilyticsUserProviderImpl> provider3, Provider<MobilyticsDeviceProviderImpl> provider4) {
        return proxyProvideMobilyticsConfiguration(mobilyticsClientModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static MobilyticsConfiguration proxyProvideMobilyticsConfiguration(MobilyticsClientModule mobilyticsClientModule, Context context, ClientConfiguration clientConfiguration, MobilyticsUserProviderImpl mobilyticsUserProviderImpl, MobilyticsDeviceProviderImpl mobilyticsDeviceProviderImpl) {
        return (MobilyticsConfiguration) Preconditions.checkNotNull(mobilyticsClientModule.provideMobilyticsConfiguration(context, clientConfiguration, mobilyticsUserProviderImpl, mobilyticsDeviceProviderImpl), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MobilyticsConfiguration mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.clientConfigurationProvider, this.mobilyticsUserProvider, this.mobilyticsDeviceProvider);
    }
}
