package com.amazon.alexa.alertsca.dependencies;

import android.net.ConnectivityManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes6.dex */
public final class ApplicationModule_ProvidesConnectivityManagerFactory implements Factory<ConnectivityManager> {
    private final ApplicationModule module;

    public ApplicationModule_ProvidesConnectivityManagerFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvidesConnectivityManagerFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvidesConnectivityManagerFactory(applicationModule);
    }

    public static ConnectivityManager provideInstance(ApplicationModule applicationModule) {
        return proxyProvidesConnectivityManager(applicationModule);
    }

    public static ConnectivityManager proxyProvidesConnectivityManager(ApplicationModule applicationModule) {
        return (ConnectivityManager) Preconditions.checkNotNull(applicationModule.providesConnectivityManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ConnectivityManager mo10268get() {
        return provideInstance(this.module);
    }
}
