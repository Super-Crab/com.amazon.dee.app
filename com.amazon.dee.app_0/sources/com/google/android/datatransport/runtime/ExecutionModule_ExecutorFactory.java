package com.google.android.datatransport.runtime;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.concurrent.Executor;
/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
/* loaded from: classes2.dex */
public final class ExecutionModule_ExecutorFactory implements Factory<Executor> {
    private static final ExecutionModule_ExecutorFactory INSTANCE = new ExecutionModule_ExecutorFactory();

    public static ExecutionModule_ExecutorFactory create() {
        return INSTANCE;
    }

    public static Executor executor() {
        return (Executor) Preconditions.checkNotNull(ExecutionModule.executor(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public Executor mo10268get() {
        return executor();
    }
}
