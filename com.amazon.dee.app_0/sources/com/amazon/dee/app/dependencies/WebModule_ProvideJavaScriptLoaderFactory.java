package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.ui.web.WebViewJavaScriptLoader;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class WebModule_ProvideJavaScriptLoaderFactory implements Factory<WebViewJavaScriptLoader> {
    private final WebModule module;

    public WebModule_ProvideJavaScriptLoaderFactory(WebModule webModule) {
        this.module = webModule;
    }

    public static WebModule_ProvideJavaScriptLoaderFactory create(WebModule webModule) {
        return new WebModule_ProvideJavaScriptLoaderFactory(webModule);
    }

    public static WebViewJavaScriptLoader provideInstance(WebModule webModule) {
        return proxyProvideJavaScriptLoader(webModule);
    }

    public static WebViewJavaScriptLoader proxyProvideJavaScriptLoader(WebModule webModule) {
        return (WebViewJavaScriptLoader) Preconditions.checkNotNull(webModule.provideJavaScriptLoader(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WebViewJavaScriptLoader mo10268get() {
        return provideInstance(this.module);
    }
}
