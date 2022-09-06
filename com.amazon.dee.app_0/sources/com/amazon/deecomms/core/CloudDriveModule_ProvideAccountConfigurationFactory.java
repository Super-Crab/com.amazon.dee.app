package com.amazon.deecomms.core;

import com.amazon.clouddrive.configuration.AccountConfiguration;
import com.amazon.clouddrive.configuration.EndpointsCache;
import com.amazon.deecomms.auth.CommsRequestAuthenticator;
import com.amazon.deecomms.media.photos.MAPAuthenticatedURLConnectionFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CloudDriveModule_ProvideAccountConfigurationFactory implements Factory<AccountConfiguration> {
    private final Provider<MAPAuthenticatedURLConnectionFactory> connectionFactoryProvider;
    private final Provider<EndpointsCache> endpointsCacheProvider;
    private final CloudDriveModule module;
    private final Provider<CommsRequestAuthenticator> requestAuthenticatorProvider;

    public CloudDriveModule_ProvideAccountConfigurationFactory(CloudDriveModule cloudDriveModule, Provider<MAPAuthenticatedURLConnectionFactory> provider, Provider<EndpointsCache> provider2, Provider<CommsRequestAuthenticator> provider3) {
        this.module = cloudDriveModule;
        this.connectionFactoryProvider = provider;
        this.endpointsCacheProvider = provider2;
        this.requestAuthenticatorProvider = provider3;
    }

    public static CloudDriveModule_ProvideAccountConfigurationFactory create(CloudDriveModule cloudDriveModule, Provider<MAPAuthenticatedURLConnectionFactory> provider, Provider<EndpointsCache> provider2, Provider<CommsRequestAuthenticator> provider3) {
        return new CloudDriveModule_ProvideAccountConfigurationFactory(cloudDriveModule, provider, provider2, provider3);
    }

    public static AccountConfiguration provideInstance(CloudDriveModule cloudDriveModule, Provider<MAPAuthenticatedURLConnectionFactory> provider, Provider<EndpointsCache> provider2, Provider<CommsRequestAuthenticator> provider3) {
        return (AccountConfiguration) Preconditions.checkNotNull(cloudDriveModule.provideAccountConfiguration(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static AccountConfiguration proxyProvideAccountConfiguration(CloudDriveModule cloudDriveModule, MAPAuthenticatedURLConnectionFactory mAPAuthenticatedURLConnectionFactory, EndpointsCache endpointsCache, CommsRequestAuthenticator commsRequestAuthenticator) {
        return (AccountConfiguration) Preconditions.checkNotNull(cloudDriveModule.provideAccountConfiguration(mAPAuthenticatedURLConnectionFactory, endpointsCache, commsRequestAuthenticator), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AccountConfiguration mo10268get() {
        return provideInstance(this.module, this.connectionFactoryProvider, this.endpointsCacheProvider, this.requestAuthenticatorProvider);
    }
}
