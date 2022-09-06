package com.amazon.alexa.fitness.dagger;

import com.dee.app.http.HttpCoralService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes8.dex */
public final class StaticReleasePackageModule_ProvideHttpCoralServiceFactory implements Factory<HttpCoralService> {
    private final StaticReleasePackageModule module;

    public StaticReleasePackageModule_ProvideHttpCoralServiceFactory(StaticReleasePackageModule staticReleasePackageModule) {
        this.module = staticReleasePackageModule;
    }

    public static StaticReleasePackageModule_ProvideHttpCoralServiceFactory create(StaticReleasePackageModule staticReleasePackageModule) {
        return new StaticReleasePackageModule_ProvideHttpCoralServiceFactory(staticReleasePackageModule);
    }

    public static HttpCoralService provideInstance(StaticReleasePackageModule staticReleasePackageModule) {
        return proxyProvideHttpCoralService(staticReleasePackageModule);
    }

    public static HttpCoralService proxyProvideHttpCoralService(StaticReleasePackageModule staticReleasePackageModule) {
        return (HttpCoralService) Preconditions.checkNotNull(staticReleasePackageModule.provideHttpCoralService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public HttpCoralService mo10268get() {
        return provideInstance(this.module);
    }
}
