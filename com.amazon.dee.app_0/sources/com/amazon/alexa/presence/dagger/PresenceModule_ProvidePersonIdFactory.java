package com.amazon.alexa.presence.dagger;

import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class PresenceModule_ProvidePersonIdFactory implements Factory<PersonIdProvider> {
    private final Provider<ComponentRegistry> applicationComponentsProvider;

    public PresenceModule_ProvidePersonIdFactory(Provider<ComponentRegistry> provider) {
        this.applicationComponentsProvider = provider;
    }

    public static PresenceModule_ProvidePersonIdFactory create(Provider<ComponentRegistry> provider) {
        return new PresenceModule_ProvidePersonIdFactory(provider);
    }

    public static PersonIdProvider provideInstance(Provider<ComponentRegistry> provider) {
        return proxyProvidePersonId(provider.mo10268get());
    }

    public static PersonIdProvider proxyProvidePersonId(ComponentRegistry componentRegistry) {
        return (PersonIdProvider) Preconditions.checkNotNull(PresenceModule.providePersonId(componentRegistry), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PersonIdProvider mo10268get() {
        return provideInstance(this.applicationComponentsProvider);
    }
}
