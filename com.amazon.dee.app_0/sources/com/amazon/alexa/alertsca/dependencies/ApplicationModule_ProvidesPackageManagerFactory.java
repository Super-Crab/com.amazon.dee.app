package com.amazon.alexa.alertsca.dependencies;

import android.content.pm.PackageManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes6.dex */
public final class ApplicationModule_ProvidesPackageManagerFactory implements Factory<PackageManager> {
    private final ApplicationModule module;

    public ApplicationModule_ProvidesPackageManagerFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvidesPackageManagerFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvidesPackageManagerFactory(applicationModule);
    }

    public static PackageManager provideInstance(ApplicationModule applicationModule) {
        return proxyProvidesPackageManager(applicationModule);
    }

    public static PackageManager proxyProvidesPackageManager(ApplicationModule applicationModule) {
        return (PackageManager) Preconditions.checkNotNull(applicationModule.providesPackageManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PackageManager mo10268get() {
        return provideInstance(this.module);
    }
}
