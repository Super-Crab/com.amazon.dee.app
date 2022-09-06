package com.amazon.alexa.presence.dagger;

import com.amazon.alexa.presence.identity.PresenceAccessTokenProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes9.dex */
public final class PresenceModule_ProvidePresenceAccessTokenProviderFactory implements Factory<PresenceAccessTokenProvider> {
    private final PresenceModule module;

    public PresenceModule_ProvidePresenceAccessTokenProviderFactory(PresenceModule presenceModule) {
        this.module = presenceModule;
    }

    public static PresenceModule_ProvidePresenceAccessTokenProviderFactory create(PresenceModule presenceModule) {
        return new PresenceModule_ProvidePresenceAccessTokenProviderFactory(presenceModule);
    }

    public static PresenceAccessTokenProvider provideInstance(PresenceModule presenceModule) {
        return proxyProvidePresenceAccessTokenProvider(presenceModule);
    }

    public static PresenceAccessTokenProvider proxyProvidePresenceAccessTokenProvider(PresenceModule presenceModule) {
        return (PresenceAccessTokenProvider) Preconditions.checkNotNull(presenceModule.providePresenceAccessTokenProvider(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PresenceAccessTokenProvider mo10268get() {
        return provideInstance(this.module);
    }
}
