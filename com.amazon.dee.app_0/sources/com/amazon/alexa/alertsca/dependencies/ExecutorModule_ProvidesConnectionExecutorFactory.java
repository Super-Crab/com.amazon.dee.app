package com.amazon.alexa.alertsca.dependencies;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.concurrent.ScheduledExecutorService;
/* loaded from: classes6.dex */
public final class ExecutorModule_ProvidesConnectionExecutorFactory implements Factory<ScheduledExecutorService> {
    private final ExecutorModule module;

    public ExecutorModule_ProvidesConnectionExecutorFactory(ExecutorModule executorModule) {
        this.module = executorModule;
    }

    public static ExecutorModule_ProvidesConnectionExecutorFactory create(ExecutorModule executorModule) {
        return new ExecutorModule_ProvidesConnectionExecutorFactory(executorModule);
    }

    public static ScheduledExecutorService provideInstance(ExecutorModule executorModule) {
        return proxyProvidesConnectionExecutor(executorModule);
    }

    public static ScheduledExecutorService proxyProvidesConnectionExecutor(ExecutorModule executorModule) {
        return (ScheduledExecutorService) Preconditions.checkNotNull(executorModule.providesConnectionExecutor(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public ScheduledExecutorService mo10268get() {
        return provideInstance(this.module);
    }
}
