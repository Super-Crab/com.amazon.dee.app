package com.amazon.alexa.fitness.dagger;

import android.webkit.CookieManager;
import com.amazon.alexa.fitness.identity.IdentityManager;
import com.amazon.alexa.fitness.util.AfitsHeaderUtils;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class StaticReleasePackageModule_ProvideAfitsHeaderUtilFactory implements Factory<AfitsHeaderUtils> {
    private final Provider<CookieManager> cookieManagerProvider;
    private final Provider<EnvironmentService> environmentServiceProvider;
    private final Provider<IdentityManager> identityManagerProvider;
    private final StaticReleasePackageModule module;

    public StaticReleasePackageModule_ProvideAfitsHeaderUtilFactory(StaticReleasePackageModule staticReleasePackageModule, Provider<EnvironmentService> provider, Provider<CookieManager> provider2, Provider<IdentityManager> provider3) {
        this.module = staticReleasePackageModule;
        this.environmentServiceProvider = provider;
        this.cookieManagerProvider = provider2;
        this.identityManagerProvider = provider3;
    }

    public static StaticReleasePackageModule_ProvideAfitsHeaderUtilFactory create(StaticReleasePackageModule staticReleasePackageModule, Provider<EnvironmentService> provider, Provider<CookieManager> provider2, Provider<IdentityManager> provider3) {
        return new StaticReleasePackageModule_ProvideAfitsHeaderUtilFactory(staticReleasePackageModule, provider, provider2, provider3);
    }

    public static AfitsHeaderUtils provideInstance(StaticReleasePackageModule staticReleasePackageModule, Provider<EnvironmentService> provider, Provider<CookieManager> provider2, Provider<IdentityManager> provider3) {
        return proxyProvideAfitsHeaderUtil(staticReleasePackageModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static AfitsHeaderUtils proxyProvideAfitsHeaderUtil(StaticReleasePackageModule staticReleasePackageModule, EnvironmentService environmentService, CookieManager cookieManager, IdentityManager identityManager) {
        return (AfitsHeaderUtils) Preconditions.checkNotNull(staticReleasePackageModule.provideAfitsHeaderUtil(environmentService, cookieManager, identityManager), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AfitsHeaderUtils mo10268get() {
        return provideInstance(this.module, this.environmentServiceProvider, this.cookieManagerProvider, this.identityManagerProvider);
    }
}
