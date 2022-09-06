package com.amazon.alexa.fitness.util;

import android.webkit.CookieManager;
import com.amazon.alexa.fitness.identity.IdentityManager;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class AfitsHeaderUtils_Factory implements Factory<AfitsHeaderUtils> {
    private final Provider<CookieManager> cookieManagerProvider;
    private final Provider<EnvironmentService> environmentServiceProvider;
    private final Provider<IdentityManager> identityManagerProvider;

    public AfitsHeaderUtils_Factory(Provider<EnvironmentService> provider, Provider<CookieManager> provider2, Provider<IdentityManager> provider3) {
        this.environmentServiceProvider = provider;
        this.cookieManagerProvider = provider2;
        this.identityManagerProvider = provider3;
    }

    public static AfitsHeaderUtils_Factory create(Provider<EnvironmentService> provider, Provider<CookieManager> provider2, Provider<IdentityManager> provider3) {
        return new AfitsHeaderUtils_Factory(provider, provider2, provider3);
    }

    public static AfitsHeaderUtils newAfitsHeaderUtils(EnvironmentService environmentService, CookieManager cookieManager, IdentityManager identityManager) {
        return new AfitsHeaderUtils(environmentService, cookieManager, identityManager);
    }

    public static AfitsHeaderUtils provideInstance(Provider<EnvironmentService> provider, Provider<CookieManager> provider2, Provider<IdentityManager> provider3) {
        return new AfitsHeaderUtils(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AfitsHeaderUtils mo10268get() {
        return provideInstance(this.environmentServiceProvider, this.cookieManagerProvider, this.identityManagerProvider);
    }
}
