package com.amazon.dee.app.dependencies;

import com.amazon.clouddrive.auth.RequestAuthenticator;
import com.amazon.clouddrive.configuration.AccountConfiguration;
import com.amazon.clouddrive.configuration.EndpointsCache;
import com.amazon.dee.app.services.clouddrive.MAPAuthenticatedURLConnectionFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CloudDriveModule_ProvideAccountConfigurationFactory implements Factory<AccountConfiguration> {
    private final Provider<MAPAuthenticatedURLConnectionFactory> connectionFactoryProvider;
    private final Provider<EndpointsCache> endpointsCacheProvider;
    private final CloudDriveModule module;
    private final Provider<RequestAuthenticator> requestAuthenticatorProvider;

    public CloudDriveModule_ProvideAccountConfigurationFactory(CloudDriveModule cloudDriveModule, Provider<MAPAuthenticatedURLConnectionFactory> provider, Provider<EndpointsCache> provider2, Provider<RequestAuthenticator> provider3) {
        this.module = cloudDriveModule;
        this.connectionFactoryProvider = provider;
        this.endpointsCacheProvider = provider2;
        this.requestAuthenticatorProvider = provider3;
    }

    public static CloudDriveModule_ProvideAccountConfigurationFactory create(CloudDriveModule cloudDriveModule, Provider<MAPAuthenticatedURLConnectionFactory> provider, Provider<EndpointsCache> provider2, Provider<RequestAuthenticator> provider3) {
        return new CloudDriveModule_ProvideAccountConfigurationFactory(cloudDriveModule, provider, provider2, provider3);
    }

    public static AccountConfiguration provideInstance(CloudDriveModule cloudDriveModule, Provider<MAPAuthenticatedURLConnectionFactory> provider, Provider<EndpointsCache> provider2, Provider<RequestAuthenticator> provider3) {
        return proxyProvideAccountConfiguration(cloudDriveModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static AccountConfiguration proxyProvideAccountConfiguration(CloudDriveModule cloudDriveModule, MAPAuthenticatedURLConnectionFactory mAPAuthenticatedURLConnectionFactory, EndpointsCache endpointsCache, RequestAuthenticator requestAuthenticator) {
        return (AccountConfiguration) Preconditions.checkNotNull(cloudDriveModule.provideAccountConfiguration(mAPAuthenticatedURLConnectionFactory, endpointsCache, requestAuthenticator), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AccountConfiguration mo10268get() {
        return provideInstance(this.module, this.connectionFactoryProvider, this.endpointsCacheProvider, this.requestAuthenticatorProvider);
    }
}
