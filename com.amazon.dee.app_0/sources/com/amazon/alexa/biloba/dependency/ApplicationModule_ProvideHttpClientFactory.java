package com.amazon.alexa.biloba.dependency;

import com.amazon.alexa.network.api.HttpClient;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes6.dex */
public final class ApplicationModule_ProvideHttpClientFactory implements Factory<HttpClient> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideHttpClientFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideHttpClientFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideHttpClientFactory(applicationModule);
    }

    public static HttpClient provideInstance(ApplicationModule applicationModule) {
        return proxyProvideHttpClient(applicationModule);
    }

    public static HttpClient proxyProvideHttpClient(ApplicationModule applicationModule) {
        return (HttpClient) Preconditions.checkNotNull(applicationModule.provideHttpClient(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public HttpClient mo10268get() {
        return provideInstance(this.module);
    }
}
