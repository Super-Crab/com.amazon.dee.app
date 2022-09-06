package com.amazon.alexa.alertsca.dependencies;

import android.os.PowerManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes6.dex */
public final class ApplicationModule_ProvidesPowerManagerFactory implements Factory<PowerManager> {
    private final ApplicationModule module;

    public ApplicationModule_ProvidesPowerManagerFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvidesPowerManagerFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvidesPowerManagerFactory(applicationModule);
    }

    public static PowerManager provideInstance(ApplicationModule applicationModule) {
        return proxyProvidesPowerManager(applicationModule);
    }

    public static PowerManager proxyProvidesPowerManager(ApplicationModule applicationModule) {
        return (PowerManager) Preconditions.checkNotNull(applicationModule.providesPowerManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PowerManager mo10268get() {
        return provideInstance(this.module);
    }
}
