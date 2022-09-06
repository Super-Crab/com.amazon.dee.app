package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.dee.app.ui.web.JavaScriptInjector;
import com.amazon.dee.app.ui.web.WebViewJavaScriptLoader;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class WebModule_ProvideJavaScriptInjectorFactory implements Factory<JavaScriptInjector> {
    private final Provider<Context> contextProvider;
    private final Provider<WebViewJavaScriptLoader> javaScriptLoaderProvider;
    private final WebModule module;

    public WebModule_ProvideJavaScriptInjectorFactory(WebModule webModule, Provider<Context> provider, Provider<WebViewJavaScriptLoader> provider2) {
        this.module = webModule;
        this.contextProvider = provider;
        this.javaScriptLoaderProvider = provider2;
    }

    public static WebModule_ProvideJavaScriptInjectorFactory create(WebModule webModule, Provider<Context> provider, Provider<WebViewJavaScriptLoader> provider2) {
        return new WebModule_ProvideJavaScriptInjectorFactory(webModule, provider, provider2);
    }

    public static JavaScriptInjector provideInstance(WebModule webModule, Provider<Context> provider, Provider<WebViewJavaScriptLoader> provider2) {
        return proxyProvideJavaScriptInjector(webModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static JavaScriptInjector proxyProvideJavaScriptInjector(WebModule webModule, Context context, WebViewJavaScriptLoader webViewJavaScriptLoader) {
        return (JavaScriptInjector) Preconditions.checkNotNull(webModule.provideJavaScriptInjector(context, webViewJavaScriptLoader), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public JavaScriptInjector mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.javaScriptLoaderProvider);
    }
}
