package com.amazon.alexa.alertsca.dependencies;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes6.dex */
public final class ApplicationModule_ProvidesApplicationContextFactory implements Factory<Context> {
    private final ApplicationModule module;

    public ApplicationModule_ProvidesApplicationContextFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvidesApplicationContextFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvidesApplicationContextFactory(applicationModule);
    }

    public static Context provideInstance(ApplicationModule applicationModule) {
        return proxyProvidesApplicationContext(applicationModule);
    }

    public static Context proxyProvidesApplicationContext(ApplicationModule applicationModule) {
        return (Context) Preconditions.checkNotNull(applicationModule.providesApplicationContext(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Context mo10268get() {
        return provideInstance(this.module);
    }
}
