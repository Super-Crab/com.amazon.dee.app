package com.amazon.alexa.fitness.dagger;

import com.amazon.alexa.fitness.logs.ILog;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes8.dex */
public final class RuntimeReleaseStaticReleasePackageModule_ProvideILogFactory implements Factory<ILog> {
    private final RuntimeReleaseStaticReleasePackageModule module;

    public RuntimeReleaseStaticReleasePackageModule_ProvideILogFactory(RuntimeReleaseStaticReleasePackageModule runtimeReleaseStaticReleasePackageModule) {
        this.module = runtimeReleaseStaticReleasePackageModule;
    }

    public static RuntimeReleaseStaticReleasePackageModule_ProvideILogFactory create(RuntimeReleaseStaticReleasePackageModule runtimeReleaseStaticReleasePackageModule) {
        return new RuntimeReleaseStaticReleasePackageModule_ProvideILogFactory(runtimeReleaseStaticReleasePackageModule);
    }

    public static ILog provideInstance(RuntimeReleaseStaticReleasePackageModule runtimeReleaseStaticReleasePackageModule) {
        return proxyProvideILog(runtimeReleaseStaticReleasePackageModule);
    }

    public static ILog proxyProvideILog(RuntimeReleaseStaticReleasePackageModule runtimeReleaseStaticReleasePackageModule) {
        return (ILog) Preconditions.checkNotNull(runtimeReleaseStaticReleasePackageModule.provideILog(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ILog mo10268get() {
        return provideInstance(this.module);
    }
}
