package com.amazon.alexa.presence.dagger;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes9.dex */
public final class PresenceModule_ProvideContextFactory implements Factory<Context> {
    private final PresenceModule module;

    public PresenceModule_ProvideContextFactory(PresenceModule presenceModule) {
        this.module = presenceModule;
    }

    public static PresenceModule_ProvideContextFactory create(PresenceModule presenceModule) {
        return new PresenceModule_ProvideContextFactory(presenceModule);
    }

    public static Context provideInstance(PresenceModule presenceModule) {
        return proxyProvideContext(presenceModule);
    }

    public static Context proxyProvideContext(PresenceModule presenceModule) {
        return (Context) Preconditions.checkNotNull(presenceModule.provideContext(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Context mo10268get() {
        return provideInstance(this.module);
    }
}
