package com.amazon.dee.app.dependencies;

import com.amazon.alexa.tasks.api.TaskManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideTaskManagerFactory implements Factory<TaskManager> {
    private final ServiceModule module;

    public ServiceModule_ProvideTaskManagerFactory(ServiceModule serviceModule) {
        this.module = serviceModule;
    }

    public static ServiceModule_ProvideTaskManagerFactory create(ServiceModule serviceModule) {
        return new ServiceModule_ProvideTaskManagerFactory(serviceModule);
    }

    public static TaskManager provideInstance(ServiceModule serviceModule) {
        return proxyProvideTaskManager(serviceModule);
    }

    public static TaskManager proxyProvideTaskManager(ServiceModule serviceModule) {
        return (TaskManager) Preconditions.checkNotNull(serviceModule.provideTaskManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TaskManager mo10268get() {
        return provideInstance(this.module);
    }
}
