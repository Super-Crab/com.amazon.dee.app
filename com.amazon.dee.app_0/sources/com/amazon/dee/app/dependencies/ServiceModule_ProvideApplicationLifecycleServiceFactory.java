package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.services.core.DefaultApplicationLifecycleService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.concurrent.ExecutorService;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideApplicationLifecycleServiceFactory implements Factory<DefaultApplicationLifecycleService> {
    private final ServiceModule module;
    private final Provider<ExecutorService> shortLivedTaskExecutorProvider;

    public ServiceModule_ProvideApplicationLifecycleServiceFactory(ServiceModule serviceModule, Provider<ExecutorService> provider) {
        this.module = serviceModule;
        this.shortLivedTaskExecutorProvider = provider;
    }

    public static ServiceModule_ProvideApplicationLifecycleServiceFactory create(ServiceModule serviceModule, Provider<ExecutorService> provider) {
        return new ServiceModule_ProvideApplicationLifecycleServiceFactory(serviceModule, provider);
    }

    public static DefaultApplicationLifecycleService provideInstance(ServiceModule serviceModule, Provider<ExecutorService> provider) {
        return proxyProvideApplicationLifecycleService(serviceModule, provider.mo10268get());
    }

    public static DefaultApplicationLifecycleService proxyProvideApplicationLifecycleService(ServiceModule serviceModule, ExecutorService executorService) {
        return (DefaultApplicationLifecycleService) Preconditions.checkNotNull(serviceModule.provideApplicationLifecycleService(executorService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DefaultApplicationLifecycleService mo10268get() {
        return provideInstance(this.module, this.shortLivedTaskExecutorProvider);
    }
}
