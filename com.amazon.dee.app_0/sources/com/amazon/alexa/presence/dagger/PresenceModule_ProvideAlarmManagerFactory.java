package com.amazon.alexa.presence.dagger;

import android.app.AlarmManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes9.dex */
public final class PresenceModule_ProvideAlarmManagerFactory implements Factory<AlarmManager> {
    private final PresenceModule module;

    public PresenceModule_ProvideAlarmManagerFactory(PresenceModule presenceModule) {
        this.module = presenceModule;
    }

    public static PresenceModule_ProvideAlarmManagerFactory create(PresenceModule presenceModule) {
        return new PresenceModule_ProvideAlarmManagerFactory(presenceModule);
    }

    public static AlarmManager provideInstance(PresenceModule presenceModule) {
        return proxyProvideAlarmManager(presenceModule);
    }

    public static AlarmManager proxyProvideAlarmManager(PresenceModule presenceModule) {
        return (AlarmManager) Preconditions.checkNotNull(presenceModule.provideAlarmManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlarmManager mo10268get() {
        return provideInstance(this.module);
    }
}
