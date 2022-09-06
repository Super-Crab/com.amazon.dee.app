package com.amazon.dee.app.dependencies;

import android.app.Application;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class ApplicationModule_ProvideApplicationFactory implements Factory<Application> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideApplicationFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideApplicationFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideApplicationFactory(applicationModule);
    }

    public static Application provideInstance(ApplicationModule applicationModule) {
        return proxyProvideApplication(applicationModule);
    }

    public static Application proxyProvideApplication(ApplicationModule applicationModule) {
        return (Application) Preconditions.checkNotNull(applicationModule.provideApplication(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Application mo10268get() {
        return provideInstance(this.module);
    }
}
