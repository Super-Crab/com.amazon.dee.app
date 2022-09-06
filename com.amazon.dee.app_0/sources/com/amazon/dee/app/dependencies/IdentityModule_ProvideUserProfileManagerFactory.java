package com.amazon.dee.app.dependencies;

import com.amazon.alexa.identity.api.UserProfileManager;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.api.CommsServiceV2;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class IdentityModule_ProvideUserProfileManagerFactory implements Factory<UserProfileManager> {
    private final Provider<CommsManager> commsManagerProvider;
    private final Provider<CommsServiceV2> commsServiceProvider;
    private final IdentityModule module;

    public IdentityModule_ProvideUserProfileManagerFactory(IdentityModule identityModule, Provider<CommsManager> provider, Provider<CommsServiceV2> provider2) {
        this.module = identityModule;
        this.commsManagerProvider = provider;
        this.commsServiceProvider = provider2;
    }

    public static IdentityModule_ProvideUserProfileManagerFactory create(IdentityModule identityModule, Provider<CommsManager> provider, Provider<CommsServiceV2> provider2) {
        return new IdentityModule_ProvideUserProfileManagerFactory(identityModule, provider, provider2);
    }

    public static UserProfileManager provideInstance(IdentityModule identityModule, Provider<CommsManager> provider, Provider<CommsServiceV2> provider2) {
        return proxyProvideUserProfileManager(identityModule, provider.mo10268get(), DoubleCheck.lazy(provider2));
    }

    public static UserProfileManager proxyProvideUserProfileManager(IdentityModule identityModule, CommsManager commsManager, Lazy<CommsServiceV2> lazy) {
        return (UserProfileManager) Preconditions.checkNotNull(identityModule.provideUserProfileManager(commsManager, lazy), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UserProfileManager mo10268get() {
        return provideInstance(this.module, this.commsManagerProvider, this.commsServiceProvider);
    }
}
