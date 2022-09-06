package com.amazon.alexa.presence.dagger;

import android.os.PowerManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes9.dex */
public final class PresenceModule_ProvidePowerManagerFactory implements Factory<PowerManager> {
    private final PresenceModule module;

    public PresenceModule_ProvidePowerManagerFactory(PresenceModule presenceModule) {
        this.module = presenceModule;
    }

    public static PresenceModule_ProvidePowerManagerFactory create(PresenceModule presenceModule) {
        return new PresenceModule_ProvidePowerManagerFactory(presenceModule);
    }

    public static PowerManager provideInstance(PresenceModule presenceModule) {
        return proxyProvidePowerManager(presenceModule);
    }

    public static PowerManager proxyProvidePowerManager(PresenceModule presenceModule) {
        return (PowerManager) Preconditions.checkNotNull(presenceModule.providePowerManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PowerManager mo10268get() {
        return provideInstance(this.module);
    }
}
