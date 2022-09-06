package com.amazon.dee.app.dependencies;

import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.clouddrive.auth.RequestAuthenticator;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CloudDriveModule_ProvideRequestAuthenticatorFactory implements Factory<RequestAuthenticator> {
    private final Provider<IdentityService> identityServiceProvider;
    private final CloudDriveModule module;

    public CloudDriveModule_ProvideRequestAuthenticatorFactory(CloudDriveModule cloudDriveModule, Provider<IdentityService> provider) {
        this.module = cloudDriveModule;
        this.identityServiceProvider = provider;
    }

    public static CloudDriveModule_ProvideRequestAuthenticatorFactory create(CloudDriveModule cloudDriveModule, Provider<IdentityService> provider) {
        return new CloudDriveModule_ProvideRequestAuthenticatorFactory(cloudDriveModule, provider);
    }

    public static RequestAuthenticator provideInstance(CloudDriveModule cloudDriveModule, Provider<IdentityService> provider) {
        return proxyProvideRequestAuthenticator(cloudDriveModule, provider.mo10268get());
    }

    public static RequestAuthenticator proxyProvideRequestAuthenticator(CloudDriveModule cloudDriveModule, IdentityService identityService) {
        return (RequestAuthenticator) Preconditions.checkNotNull(cloudDriveModule.provideRequestAuthenticator(identityService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RequestAuthenticator mo10268get() {
        return provideInstance(this.module, this.identityServiceProvider);
    }
}
