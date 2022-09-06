package com.amazon.alexa.sharing.comms.dependencies;

import android.content.Context;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.sharing.media.clouddrive.MAPAuthenticatedURLConnectionFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class AlexaSharingModule_ProvideMAPAuthenticatedURLConnectionFactoryFactory implements Factory<MAPAuthenticatedURLConnectionFactory> {
    private final Provider<Context> contextProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final AlexaSharingModule module;

    public AlexaSharingModule_ProvideMAPAuthenticatedURLConnectionFactoryFactory(AlexaSharingModule alexaSharingModule, Provider<Context> provider, Provider<IdentityService> provider2) {
        this.module = alexaSharingModule;
        this.contextProvider = provider;
        this.identityServiceProvider = provider2;
    }

    public static AlexaSharingModule_ProvideMAPAuthenticatedURLConnectionFactoryFactory create(AlexaSharingModule alexaSharingModule, Provider<Context> provider, Provider<IdentityService> provider2) {
        return new AlexaSharingModule_ProvideMAPAuthenticatedURLConnectionFactoryFactory(alexaSharingModule, provider, provider2);
    }

    public static MAPAuthenticatedURLConnectionFactory provideInstance(AlexaSharingModule alexaSharingModule, Provider<Context> provider, Provider<IdentityService> provider2) {
        return proxyProvideMAPAuthenticatedURLConnectionFactory(alexaSharingModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static MAPAuthenticatedURLConnectionFactory proxyProvideMAPAuthenticatedURLConnectionFactory(AlexaSharingModule alexaSharingModule, Context context, IdentityService identityService) {
        return (MAPAuthenticatedURLConnectionFactory) Preconditions.checkNotNull(alexaSharingModule.provideMAPAuthenticatedURLConnectionFactory(context, identityService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MAPAuthenticatedURLConnectionFactory mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.identityServiceProvider);
    }
}
