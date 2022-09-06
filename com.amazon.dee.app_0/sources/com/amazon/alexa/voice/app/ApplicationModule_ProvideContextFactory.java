package com.amazon.alexa.voice.app;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class ApplicationModule_ProvideContextFactory implements Factory<Context> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideContextFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideContextFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideContextFactory(applicationModule);
    }

    public static Context provideInstance(ApplicationModule applicationModule) {
        return proxyProvideContext(applicationModule);
    }

    public static Context proxyProvideContext(ApplicationModule applicationModule) {
        return (Context) Preconditions.checkNotNull(applicationModule.provideContext(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Context mo10268get() {
        return provideInstance(this.module);
    }
}
