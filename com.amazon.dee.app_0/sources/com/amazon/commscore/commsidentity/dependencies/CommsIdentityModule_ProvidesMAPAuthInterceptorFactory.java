package com.amazon.commscore.commsidentity.dependencies;

import com.amazon.commscore.commsidentity.common.MAPAuthenticationInterceptor;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class CommsIdentityModule_ProvidesMAPAuthInterceptorFactory implements Factory<MAPAuthenticationInterceptor> {
    private final CommsIdentityModule module;

    public CommsIdentityModule_ProvidesMAPAuthInterceptorFactory(CommsIdentityModule commsIdentityModule) {
        this.module = commsIdentityModule;
    }

    public static CommsIdentityModule_ProvidesMAPAuthInterceptorFactory create(CommsIdentityModule commsIdentityModule) {
        return new CommsIdentityModule_ProvidesMAPAuthInterceptorFactory(commsIdentityModule);
    }

    public static MAPAuthenticationInterceptor provideInstance(CommsIdentityModule commsIdentityModule) {
        return proxyProvidesMAPAuthInterceptor(commsIdentityModule);
    }

    public static MAPAuthenticationInterceptor proxyProvidesMAPAuthInterceptor(CommsIdentityModule commsIdentityModule) {
        return (MAPAuthenticationInterceptor) Preconditions.checkNotNull(commsIdentityModule.providesMAPAuthInterceptor(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MAPAuthenticationInterceptor mo10268get() {
        return provideInstance(this.module);
    }
}
