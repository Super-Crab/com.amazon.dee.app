package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.dee.app.services.clouddrive.MAPAuthenticatedURLConnectionFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CloudDriveModule_ProvideMAPAuthenticatedURLConnectionFactoryFactory implements Factory<MAPAuthenticatedURLConnectionFactory> {
    private final Provider<Context> contextProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final CloudDriveModule module;

    public CloudDriveModule_ProvideMAPAuthenticatedURLConnectionFactoryFactory(CloudDriveModule cloudDriveModule, Provider<Context> provider, Provider<IdentityService> provider2) {
        this.module = cloudDriveModule;
        this.contextProvider = provider;
        this.identityServiceProvider = provider2;
    }

    public static CloudDriveModule_ProvideMAPAuthenticatedURLConnectionFactoryFactory create(CloudDriveModule cloudDriveModule, Provider<Context> provider, Provider<IdentityService> provider2) {
        return new CloudDriveModule_ProvideMAPAuthenticatedURLConnectionFactoryFactory(cloudDriveModule, provider, provider2);
    }

    public static MAPAuthenticatedURLConnectionFactory provideInstance(CloudDriveModule cloudDriveModule, Provider<Context> provider, Provider<IdentityService> provider2) {
        return proxyProvideMAPAuthenticatedURLConnectionFactory(cloudDriveModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static MAPAuthenticatedURLConnectionFactory proxyProvideMAPAuthenticatedURLConnectionFactory(CloudDriveModule cloudDriveModule, Context context, IdentityService identityService) {
        return (MAPAuthenticatedURLConnectionFactory) Preconditions.checkNotNull(cloudDriveModule.provideMAPAuthenticatedURLConnectionFactory(context, identityService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MAPAuthenticatedURLConnectionFactory mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.identityServiceProvider);
    }
}
