package com.amazon.alexa.voice.app;

import com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class ApplicationModule_ProvideApplicationLifecycleServiceFactory implements Factory<ApplicationLifecycleService> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideApplicationLifecycleServiceFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideApplicationLifecycleServiceFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideApplicationLifecycleServiceFactory(applicationModule);
    }

    public static ApplicationLifecycleService provideInstance(ApplicationModule applicationModule) {
        return proxyProvideApplicationLifecycleService(applicationModule);
    }

    public static ApplicationLifecycleService proxyProvideApplicationLifecycleService(ApplicationModule applicationModule) {
        return (ApplicationLifecycleService) Preconditions.checkNotNull(applicationModule.provideApplicationLifecycleService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ApplicationLifecycleService mo10268get() {
        return provideInstance(this.module);
    }
}
