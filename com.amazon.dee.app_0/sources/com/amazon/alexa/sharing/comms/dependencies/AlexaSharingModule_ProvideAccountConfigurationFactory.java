package com.amazon.alexa.sharing.comms.dependencies;

import com.amazon.alexa.sharing.media.clouddrive.MAPAuthenticatedURLConnectionFactory;
import com.amazon.clouddrive.auth.RequestAuthenticator;
import com.amazon.clouddrive.configuration.AccountConfiguration;
import com.amazon.clouddrive.configuration.EndpointsCache;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class AlexaSharingModule_ProvideAccountConfigurationFactory implements Factory<AccountConfiguration> {
    private final Provider<MAPAuthenticatedURLConnectionFactory> connectionFactoryProvider;
    private final Provider<EndpointsCache> endpointsCacheProvider;
    private final AlexaSharingModule module;
    private final Provider<RequestAuthenticator> requestAuthenticatorProvider;

    public AlexaSharingModule_ProvideAccountConfigurationFactory(AlexaSharingModule alexaSharingModule, Provider<MAPAuthenticatedURLConnectionFactory> provider, Provider<EndpointsCache> provider2, Provider<RequestAuthenticator> provider3) {
        this.module = alexaSharingModule;
        this.connectionFactoryProvider = provider;
        this.endpointsCacheProvider = provider2;
        this.requestAuthenticatorProvider = provider3;
    }

    public static AlexaSharingModule_ProvideAccountConfigurationFactory create(AlexaSharingModule alexaSharingModule, Provider<MAPAuthenticatedURLConnectionFactory> provider, Provider<EndpointsCache> provider2, Provider<RequestAuthenticator> provider3) {
        return new AlexaSharingModule_ProvideAccountConfigurationFactory(alexaSharingModule, provider, provider2, provider3);
    }

    public static AccountConfiguration provideInstance(AlexaSharingModule alexaSharingModule, Provider<MAPAuthenticatedURLConnectionFactory> provider, Provider<EndpointsCache> provider2, Provider<RequestAuthenticator> provider3) {
        return proxyProvideAccountConfiguration(alexaSharingModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static AccountConfiguration proxyProvideAccountConfiguration(AlexaSharingModule alexaSharingModule, MAPAuthenticatedURLConnectionFactory mAPAuthenticatedURLConnectionFactory, EndpointsCache endpointsCache, RequestAuthenticator requestAuthenticator) {
        return (AccountConfiguration) Preconditions.checkNotNull(alexaSharingModule.provideAccountConfiguration(mAPAuthenticatedURLConnectionFactory, endpointsCache, requestAuthenticator), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AccountConfiguration mo10268get() {
        return provideInstance(this.module, this.connectionFactoryProvider, this.endpointsCacheProvider, this.requestAuthenticatorProvider);
    }
}
