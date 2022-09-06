package com.amazon.alexa.alertsca.dependencies;

import android.app.AlarmManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes6.dex */
public final class ApplicationModule_ProvidesAlarmManagerFactory implements Factory<AlarmManager> {
    private final ApplicationModule module;

    public ApplicationModule_ProvidesAlarmManagerFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvidesAlarmManagerFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvidesAlarmManagerFactory(applicationModule);
    }

    public static AlarmManager provideInstance(ApplicationModule applicationModule) {
        return proxyProvidesAlarmManager(applicationModule);
    }

    public static AlarmManager proxyProvidesAlarmManager(ApplicationModule applicationModule) {
        return (AlarmManager) Preconditions.checkNotNull(applicationModule.providesAlarmManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlarmManager mo10268get() {
        return provideInstance(this.module);
    }
}
