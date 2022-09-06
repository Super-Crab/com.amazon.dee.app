package com.amazon.alexa.sharing.comms.dependencies;

import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.clouddrive.auth.RequestAuthenticator;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class AlexaSharingModule_ProvideRequestAuthenticatorFactory implements Factory<RequestAuthenticator> {
    private final Provider<IdentityService> identityServiceProvider;
    private final AlexaSharingModule module;

    public AlexaSharingModule_ProvideRequestAuthenticatorFactory(AlexaSharingModule alexaSharingModule, Provider<IdentityService> provider) {
        this.module = alexaSharingModule;
        this.identityServiceProvider = provider;
    }

    public static AlexaSharingModule_ProvideRequestAuthenticatorFactory create(AlexaSharingModule alexaSharingModule, Provider<IdentityService> provider) {
        return new AlexaSharingModule_ProvideRequestAuthenticatorFactory(alexaSharingModule, provider);
    }

    public static RequestAuthenticator provideInstance(AlexaSharingModule alexaSharingModule, Provider<IdentityService> provider) {
        return proxyProvideRequestAuthenticator(alexaSharingModule, provider.mo10268get());
    }

    public static RequestAuthenticator proxyProvideRequestAuthenticator(AlexaSharingModule alexaSharingModule, IdentityService identityService) {
        return (RequestAuthenticator) Preconditions.checkNotNull(alexaSharingModule.provideRequestAuthenticator(identityService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RequestAuthenticator mo10268get() {
        return provideInstance(this.module, this.identityServiceProvider);
    }
}
