package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.services.core.MainActivityLifecycleService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.concurrent.ExecutorService;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideMainActivityLifecycleServiceFactory implements Factory<MainActivityLifecycleService> {
    private final ServiceModule module;
    private final Provider<ExecutorService> shortLivedTaskExecutorProvider;

    public ServiceModule_ProvideMainActivityLifecycleServiceFactory(ServiceModule serviceModule, Provider<ExecutorService> provider) {
        this.module = serviceModule;
        this.shortLivedTaskExecutorProvider = provider;
    }

    public static ServiceModule_ProvideMainActivityLifecycleServiceFactory create(ServiceModule serviceModule, Provider<ExecutorService> provider) {
        return new ServiceModule_ProvideMainActivityLifecycleServiceFactory(serviceModule, provider);
    }

    public static MainActivityLifecycleService provideInstance(ServiceModule serviceModule, Provider<ExecutorService> provider) {
        return proxyProvideMainActivityLifecycleService(serviceModule, provider.mo10268get());
    }

    public static MainActivityLifecycleService proxyProvideMainActivityLifecycleService(ServiceModule serviceModule, ExecutorService executorService) {
        return (MainActivityLifecycleService) Preconditions.checkNotNull(serviceModule.provideMainActivityLifecycleService(executorService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MainActivityLifecycleService mo10268get() {
        return provideInstance(this.module, this.shortLivedTaskExecutorProvider);
    }
}
