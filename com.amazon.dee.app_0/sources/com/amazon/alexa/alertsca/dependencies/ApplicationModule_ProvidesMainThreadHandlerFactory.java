package com.amazon.alexa.alertsca.dependencies;

import android.os.Handler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes6.dex */
public final class ApplicationModule_ProvidesMainThreadHandlerFactory implements Factory<Handler> {
    private final ApplicationModule module;

    public ApplicationModule_ProvidesMainThreadHandlerFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvidesMainThreadHandlerFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvidesMainThreadHandlerFactory(applicationModule);
    }

    public static Handler provideInstance(ApplicationModule applicationModule) {
        return proxyProvidesMainThreadHandler(applicationModule);
    }

    public static Handler proxyProvidesMainThreadHandler(ApplicationModule applicationModule) {
        return (Handler) Preconditions.checkNotNull(applicationModule.providesMainThreadHandler(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Handler mo10268get() {
        return provideInstance(this.module);
    }
}
