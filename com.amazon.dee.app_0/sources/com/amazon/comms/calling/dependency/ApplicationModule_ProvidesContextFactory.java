package com.amazon.comms.calling.dependency;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class ApplicationModule_ProvidesContextFactory implements Factory<Context> {
    private final ApplicationModule module;

    public ApplicationModule_ProvidesContextFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvidesContextFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvidesContextFactory(applicationModule);
    }

    public static Context provideInstance(ApplicationModule applicationModule) {
        return proxyProvidesContext(applicationModule);
    }

    public static Context proxyProvidesContext(ApplicationModule applicationModule) {
        return (Context) Preconditions.checkNotNull(applicationModule.providesContext(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Context mo10268get() {
        return provideInstance(this.module);
    }
}
