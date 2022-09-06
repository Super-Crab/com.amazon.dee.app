package com.amazon.clouddrive.cdasdk.dagger.module;

import com.amazon.clouddrive.cdasdk.util.Logger;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class ApplicationModule_ProvideLoggerFactory implements Factory<Logger> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideLoggerFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideLoggerFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideLoggerFactory(applicationModule);
    }

    public static Logger provideLogger(ApplicationModule applicationModule) {
        return (Logger) Preconditions.checkNotNull(applicationModule.provideLogger(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Logger mo10268get() {
        return provideLogger(this.module);
    }
}
