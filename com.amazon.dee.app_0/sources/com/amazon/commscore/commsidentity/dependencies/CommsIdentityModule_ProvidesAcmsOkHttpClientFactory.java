package com.amazon.commscore.commsidentity.dependencies;

import com.amazon.commscore.commsidentity.common.MAPAuthenticationInterceptor;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
/* loaded from: classes12.dex */
public final class CommsIdentityModule_ProvidesAcmsOkHttpClientFactory implements Factory<OkHttpClient> {
    private final Provider<MAPAuthenticationInterceptor> mapAuthenticationInterceptorProvider;
    private final CommsIdentityModule module;

    public CommsIdentityModule_ProvidesAcmsOkHttpClientFactory(CommsIdentityModule commsIdentityModule, Provider<MAPAuthenticationInterceptor> provider) {
        this.module = commsIdentityModule;
        this.mapAuthenticationInterceptorProvider = provider;
    }

    public static CommsIdentityModule_ProvidesAcmsOkHttpClientFactory create(CommsIdentityModule commsIdentityModule, Provider<MAPAuthenticationInterceptor> provider) {
        return new CommsIdentityModule_ProvidesAcmsOkHttpClientFactory(commsIdentityModule, provider);
    }

    public static OkHttpClient provideInstance(CommsIdentityModule commsIdentityModule, Provider<MAPAuthenticationInterceptor> provider) {
        return proxyProvidesAcmsOkHttpClient(commsIdentityModule, provider.mo10268get());
    }

    public static OkHttpClient proxyProvidesAcmsOkHttpClient(CommsIdentityModule commsIdentityModule, MAPAuthenticationInterceptor mAPAuthenticationInterceptor) {
        return (OkHttpClient) Preconditions.checkNotNull(commsIdentityModule.providesAcmsOkHttpClient(mAPAuthenticationInterceptor), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public OkHttpClient mo10268get() {
        return provideInstance(this.module, this.mapAuthenticationInterceptorProvider);
    }
}
