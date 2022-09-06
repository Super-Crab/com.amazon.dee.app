package com.amazon.alexa.fitness.dagger;

import com.amazon.alexa.fitness.logs.ILog;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes8.dex */
public final class RuntimeDebugStaticReleasePackageModule_ProvideILogFactory implements Factory<ILog> {
    private final RuntimeDebugStaticReleasePackageModule module;

    public RuntimeDebugStaticReleasePackageModule_ProvideILogFactory(RuntimeDebugStaticReleasePackageModule runtimeDebugStaticReleasePackageModule) {
        this.module = runtimeDebugStaticReleasePackageModule;
    }

    public static RuntimeDebugStaticReleasePackageModule_ProvideILogFactory create(RuntimeDebugStaticReleasePackageModule runtimeDebugStaticReleasePackageModule) {
        return new RuntimeDebugStaticReleasePackageModule_ProvideILogFactory(runtimeDebugStaticReleasePackageModule);
    }

    public static ILog provideInstance(RuntimeDebugStaticReleasePackageModule runtimeDebugStaticReleasePackageModule) {
        return proxyProvideILog(runtimeDebugStaticReleasePackageModule);
    }

    public static ILog proxyProvideILog(RuntimeDebugStaticReleasePackageModule runtimeDebugStaticReleasePackageModule) {
        return (ILog) Preconditions.checkNotNull(runtimeDebugStaticReleasePackageModule.provideILog(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ILog mo10268get() {
        return provideInstance(this.module);
    }
}
