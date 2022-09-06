package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.ui.util.SonarUrlHandler;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
/* loaded from: classes12.dex */
public final class MainModule_ProvideSonarUrlHandlerFactory implements Factory<SonarUrlHandler> {
    private final MainModule module;
    private final Provider<OkHttpClient> okHttpClientProvider;

    public MainModule_ProvideSonarUrlHandlerFactory(MainModule mainModule, Provider<OkHttpClient> provider) {
        this.module = mainModule;
        this.okHttpClientProvider = provider;
    }

    public static MainModule_ProvideSonarUrlHandlerFactory create(MainModule mainModule, Provider<OkHttpClient> provider) {
        return new MainModule_ProvideSonarUrlHandlerFactory(mainModule, provider);
    }

    public static SonarUrlHandler provideInstance(MainModule mainModule, Provider<OkHttpClient> provider) {
        return proxyProvideSonarUrlHandler(mainModule, DoubleCheck.lazy(provider));
    }

    public static SonarUrlHandler proxyProvideSonarUrlHandler(MainModule mainModule, Lazy<OkHttpClient> lazy) {
        return (SonarUrlHandler) Preconditions.checkNotNull(mainModule.provideSonarUrlHandler(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SonarUrlHandler mo10268get() {
        return provideInstance(this.module, this.okHttpClientProvider);
    }
}
