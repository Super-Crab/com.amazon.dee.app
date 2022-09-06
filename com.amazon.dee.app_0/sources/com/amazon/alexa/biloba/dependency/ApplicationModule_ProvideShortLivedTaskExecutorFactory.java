package com.amazon.alexa.biloba.dependency;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.concurrent.ExecutorService;
/* loaded from: classes6.dex */
public final class ApplicationModule_ProvideShortLivedTaskExecutorFactory implements Factory<ExecutorService> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideShortLivedTaskExecutorFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideShortLivedTaskExecutorFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideShortLivedTaskExecutorFactory(applicationModule);
    }

    public static ExecutorService provideInstance(ApplicationModule applicationModule) {
        return proxyProvideShortLivedTaskExecutor(applicationModule);
    }

    public static ExecutorService proxyProvideShortLivedTaskExecutor(ApplicationModule applicationModule) {
        return (ExecutorService) Preconditions.checkNotNull(applicationModule.provideShortLivedTaskExecutor(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public ExecutorService mo10268get() {
        return provideInstance(this.module);
    }
}
