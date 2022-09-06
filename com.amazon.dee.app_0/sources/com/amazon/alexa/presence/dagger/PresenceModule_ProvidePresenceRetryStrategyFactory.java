package com.amazon.alexa.presence.dagger;

import com.amazon.alexa.presence.retry.PresenceRetryStrategy;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes9.dex */
public final class PresenceModule_ProvidePresenceRetryStrategyFactory implements Factory<PresenceRetryStrategy> {
    private final PresenceModule module;

    public PresenceModule_ProvidePresenceRetryStrategyFactory(PresenceModule presenceModule) {
        this.module = presenceModule;
    }

    public static PresenceModule_ProvidePresenceRetryStrategyFactory create(PresenceModule presenceModule) {
        return new PresenceModule_ProvidePresenceRetryStrategyFactory(presenceModule);
    }

    public static PresenceRetryStrategy provideInstance(PresenceModule presenceModule) {
        return proxyProvidePresenceRetryStrategy(presenceModule);
    }

    public static PresenceRetryStrategy proxyProvidePresenceRetryStrategy(PresenceModule presenceModule) {
        return (PresenceRetryStrategy) Preconditions.checkNotNull(presenceModule.providePresenceRetryStrategy(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PresenceRetryStrategy mo10268get() {
        return provideInstance(this.module);
    }
}
