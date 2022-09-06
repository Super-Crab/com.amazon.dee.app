package com.amazon.alexa.fitness.dagger;

import android.webkit.CookieManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes8.dex */
public final class StaticReleasePackageModule_ProvideCookieManagerFactory implements Factory<CookieManager> {
    private final StaticReleasePackageModule module;

    public StaticReleasePackageModule_ProvideCookieManagerFactory(StaticReleasePackageModule staticReleasePackageModule) {
        this.module = staticReleasePackageModule;
    }

    public static StaticReleasePackageModule_ProvideCookieManagerFactory create(StaticReleasePackageModule staticReleasePackageModule) {
        return new StaticReleasePackageModule_ProvideCookieManagerFactory(staticReleasePackageModule);
    }

    public static CookieManager provideInstance(StaticReleasePackageModule staticReleasePackageModule) {
        return proxyProvideCookieManager(staticReleasePackageModule);
    }

    public static CookieManager proxyProvideCookieManager(StaticReleasePackageModule staticReleasePackageModule) {
        return (CookieManager) Preconditions.checkNotNull(staticReleasePackageModule.provideCookieManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CookieManager mo10268get() {
        return provideInstance(this.module);
    }
}
