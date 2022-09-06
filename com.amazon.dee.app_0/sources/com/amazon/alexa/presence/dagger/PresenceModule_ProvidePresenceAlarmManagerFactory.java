package com.amazon.alexa.presence.dagger;

import android.app.AlarmManager;
import com.amazon.alexa.presence.alarm.PresenceAlarmManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class PresenceModule_ProvidePresenceAlarmManagerFactory implements Factory<PresenceAlarmManager> {
    private final Provider<AlarmManager> alarmManagerProvider;
    private final PresenceModule module;

    public PresenceModule_ProvidePresenceAlarmManagerFactory(PresenceModule presenceModule, Provider<AlarmManager> provider) {
        this.module = presenceModule;
        this.alarmManagerProvider = provider;
    }

    public static PresenceModule_ProvidePresenceAlarmManagerFactory create(PresenceModule presenceModule, Provider<AlarmManager> provider) {
        return new PresenceModule_ProvidePresenceAlarmManagerFactory(presenceModule, provider);
    }

    public static PresenceAlarmManager provideInstance(PresenceModule presenceModule, Provider<AlarmManager> provider) {
        return proxyProvidePresenceAlarmManager(presenceModule, provider.mo10268get());
    }

    public static PresenceAlarmManager proxyProvidePresenceAlarmManager(PresenceModule presenceModule, AlarmManager alarmManager) {
        return (PresenceAlarmManager) Preconditions.checkNotNull(presenceModule.providePresenceAlarmManager(alarmManager), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PresenceAlarmManager mo10268get() {
        return provideInstance(this.module, this.alarmManagerProvider);
    }
}
