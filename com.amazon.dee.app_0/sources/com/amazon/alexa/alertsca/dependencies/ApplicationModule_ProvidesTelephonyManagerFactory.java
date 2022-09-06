package com.amazon.alexa.alertsca.dependencies;

import android.telephony.TelephonyManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes6.dex */
public final class ApplicationModule_ProvidesTelephonyManagerFactory implements Factory<TelephonyManager> {
    private final ApplicationModule module;

    public ApplicationModule_ProvidesTelephonyManagerFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvidesTelephonyManagerFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvidesTelephonyManagerFactory(applicationModule);
    }

    public static TelephonyManager provideInstance(ApplicationModule applicationModule) {
        return proxyProvidesTelephonyManager(applicationModule);
    }

    public static TelephonyManager proxyProvidesTelephonyManager(ApplicationModule applicationModule) {
        return (TelephonyManager) Preconditions.checkNotNull(applicationModule.providesTelephonyManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TelephonyManager mo10268get() {
        return provideInstance(this.module);
    }
}
