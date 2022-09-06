package com.amazon.alexa.fitness.dagger;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes8.dex */
public final class StaticReleasePackageModule_ProvideApplicationContextFactory implements Factory<Context> {
    private final StaticReleasePackageModule module;

    public StaticReleasePackageModule_ProvideApplicationContextFactory(StaticReleasePackageModule staticReleasePackageModule) {
        this.module = staticReleasePackageModule;
    }

    public static StaticReleasePackageModule_ProvideApplicationContextFactory create(StaticReleasePackageModule staticReleasePackageModule) {
        return new StaticReleasePackageModule_ProvideApplicationContextFactory(staticReleasePackageModule);
    }

    public static Context provideInstance(StaticReleasePackageModule staticReleasePackageModule) {
        return proxyProvideApplicationContext(staticReleasePackageModule);
    }

    public static Context proxyProvideApplicationContext(StaticReleasePackageModule staticReleasePackageModule) {
        return (Context) Preconditions.checkNotNull(staticReleasePackageModule.provideApplicationContext(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Context mo10268get() {
        return provideInstance(this.module);
    }
}
