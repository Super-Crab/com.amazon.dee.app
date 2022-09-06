package com.amazon.dee.app.dependencies;

import com.amazon.alexa.tasks.api.TaskManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.concurrent.ExecutorService;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideShortLivedTaskExecutorFactory implements Factory<ExecutorService> {
    private final ServiceModule module;
    private final Provider<TaskManager> taskManagerProvider;

    public ServiceModule_ProvideShortLivedTaskExecutorFactory(ServiceModule serviceModule, Provider<TaskManager> provider) {
        this.module = serviceModule;
        this.taskManagerProvider = provider;
    }

    public static ServiceModule_ProvideShortLivedTaskExecutorFactory create(ServiceModule serviceModule, Provider<TaskManager> provider) {
        return new ServiceModule_ProvideShortLivedTaskExecutorFactory(serviceModule, provider);
    }

    public static ExecutorService provideInstance(ServiceModule serviceModule, Provider<TaskManager> provider) {
        return proxyProvideShortLivedTaskExecutor(serviceModule, provider.mo10268get());
    }

    public static ExecutorService proxyProvideShortLivedTaskExecutor(ServiceModule serviceModule, TaskManager taskManager) {
        return (ExecutorService) Preconditions.checkNotNull(serviceModule.provideShortLivedTaskExecutor(taskManager), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public ExecutorService mo10268get() {
        return provideInstance(this.module, this.taskManagerProvider);
    }
}
