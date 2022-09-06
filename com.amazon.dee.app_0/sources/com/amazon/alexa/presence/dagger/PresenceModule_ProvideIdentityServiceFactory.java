package com.amazon.alexa.presence.dagger;

import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class PresenceModule_ProvideIdentityServiceFactory implements Factory<IdentityService> {
    private final Provider<ComponentRegistry> applicationComponentsProvider;

    public PresenceModule_ProvideIdentityServiceFactory(Provider<ComponentRegistry> provider) {
        this.applicationComponentsProvider = provider;
    }

    public static PresenceModule_ProvideIdentityServiceFactory create(Provider<ComponentRegistry> provider) {
        return new PresenceModule_ProvideIdentityServiceFactory(provider);
    }

    public static IdentityService provideInstance(Provider<ComponentRegistry> provider) {
        return proxyProvideIdentityService(provider.mo10268get());
    }

    public static IdentityService proxyProvideIdentityService(ComponentRegistry componentRegistry) {
        return (IdentityService) Preconditions.checkNotNull(PresenceModule.provideIdentityService(componentRegistry), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public IdentityService mo10268get() {
        return provideInstance(this.applicationComponentsProvider);
    }
}
