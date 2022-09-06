package com.amazon.alexa.presence.dagger;

import com.amazon.alexa.presence.service.PresenceForegroundServiceStateAdviser;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes9.dex */
public final class PresenceModule_PresenceForegroundServiceStateAdviserFactory implements Factory<PresenceForegroundServiceStateAdviser> {
    private final PresenceModule module;

    public PresenceModule_PresenceForegroundServiceStateAdviserFactory(PresenceModule presenceModule) {
        this.module = presenceModule;
    }

    public static PresenceModule_PresenceForegroundServiceStateAdviserFactory create(PresenceModule presenceModule) {
        return new PresenceModule_PresenceForegroundServiceStateAdviserFactory(presenceModule);
    }

    public static PresenceForegroundServiceStateAdviser provideInstance(PresenceModule presenceModule) {
        return proxyPresenceForegroundServiceStateAdviser(presenceModule);
    }

    public static PresenceForegroundServiceStateAdviser proxyPresenceForegroundServiceStateAdviser(PresenceModule presenceModule) {
        return (PresenceForegroundServiceStateAdviser) Preconditions.checkNotNull(presenceModule.presenceForegroundServiceStateAdviser(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PresenceForegroundServiceStateAdviser mo10268get() {
        return provideInstance(this.module);
    }
}
