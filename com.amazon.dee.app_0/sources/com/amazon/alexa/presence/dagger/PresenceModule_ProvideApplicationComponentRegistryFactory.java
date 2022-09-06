package com.amazon.alexa.presence.dagger;

import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes9.dex */
public final class PresenceModule_ProvideApplicationComponentRegistryFactory implements Factory<ComponentRegistry> {
    private static final PresenceModule_ProvideApplicationComponentRegistryFactory INSTANCE = new PresenceModule_ProvideApplicationComponentRegistryFactory();

    public static PresenceModule_ProvideApplicationComponentRegistryFactory create() {
        return INSTANCE;
    }

    public static ComponentRegistry provideInstance() {
        return proxyProvideApplicationComponentRegistry();
    }

    public static ComponentRegistry proxyProvideApplicationComponentRegistry() {
        return (ComponentRegistry) Preconditions.checkNotNull(PresenceModule.provideApplicationComponentRegistry(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ComponentRegistry mo10268get() {
        return provideInstance();
    }
}
