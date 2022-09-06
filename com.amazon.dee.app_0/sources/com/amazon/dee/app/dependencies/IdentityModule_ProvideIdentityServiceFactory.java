package com.amazon.dee.app.dependencies;

import com.amazon.alexa.identity.MAPIdentityService;
import com.amazon.alexa.identity.api.IdentityService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class IdentityModule_ProvideIdentityServiceFactory implements Factory<IdentityService> {
    private final Provider<MAPIdentityService> mapIdentityServiceProvider;
    private final IdentityModule module;

    public IdentityModule_ProvideIdentityServiceFactory(IdentityModule identityModule, Provider<MAPIdentityService> provider) {
        this.module = identityModule;
        this.mapIdentityServiceProvider = provider;
    }

    public static IdentityModule_ProvideIdentityServiceFactory create(IdentityModule identityModule, Provider<MAPIdentityService> provider) {
        return new IdentityModule_ProvideIdentityServiceFactory(identityModule, provider);
    }

    public static IdentityService provideInstance(IdentityModule identityModule, Provider<MAPIdentityService> provider) {
        return proxyProvideIdentityService(identityModule, provider.mo10268get());
    }

    public static IdentityService proxyProvideIdentityService(IdentityModule identityModule, MAPIdentityService mAPIdentityService) {
        return (IdentityService) Preconditions.checkNotNull(identityModule.provideIdentityService(mAPIdentityService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public IdentityService mo10268get() {
        return provideInstance(this.module, this.mapIdentityServiceProvider);
    }
}
