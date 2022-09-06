package com.amazon.alexa.alertsca.dependencies;

import android.content.SharedPreferences;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes6.dex */
public final class ApplicationModule_ProvidesSharedPreferencesFactory implements Factory<SharedPreferences> {
    private final ApplicationModule module;

    public ApplicationModule_ProvidesSharedPreferencesFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvidesSharedPreferencesFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvidesSharedPreferencesFactory(applicationModule);
    }

    public static SharedPreferences provideInstance(ApplicationModule applicationModule) {
        return proxyProvidesSharedPreferences(applicationModule);
    }

    public static SharedPreferences proxyProvidesSharedPreferences(ApplicationModule applicationModule) {
        return (SharedPreferences) Preconditions.checkNotNull(applicationModule.providesSharedPreferences(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SharedPreferences mo10268get() {
        return provideInstance(this.module);
    }
}
