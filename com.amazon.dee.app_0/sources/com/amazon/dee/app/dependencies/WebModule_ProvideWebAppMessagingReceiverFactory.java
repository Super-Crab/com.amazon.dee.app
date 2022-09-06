package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.ui.web.JavaScriptInjector;
import com.amazon.dee.app.ui.web.WebAppMessagingReceiver;
import com.amazon.dee.app.ui.web.WebNavigator;
import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class WebModule_ProvideWebAppMessagingReceiverFactory implements Factory<WebAppMessagingReceiver> {
    private final Provider<Gson> gsonProvider;
    private final Provider<JavaScriptInjector> javaScriptInjectorProvider;
    private final WebModule module;
    private final Provider<WebNavigator> webNavigatorProvider;

    public WebModule_ProvideWebAppMessagingReceiverFactory(WebModule webModule, Provider<WebNavigator> provider, Provider<JavaScriptInjector> provider2, Provider<Gson> provider3) {
        this.module = webModule;
        this.webNavigatorProvider = provider;
        this.javaScriptInjectorProvider = provider2;
        this.gsonProvider = provider3;
    }

    public static WebModule_ProvideWebAppMessagingReceiverFactory create(WebModule webModule, Provider<WebNavigator> provider, Provider<JavaScriptInjector> provider2, Provider<Gson> provider3) {
        return new WebModule_ProvideWebAppMessagingReceiverFactory(webModule, provider, provider2, provider3);
    }

    public static WebAppMessagingReceiver provideInstance(WebModule webModule, Provider<WebNavigator> provider, Provider<JavaScriptInjector> provider2, Provider<Gson> provider3) {
        return proxyProvideWebAppMessagingReceiver(webModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static WebAppMessagingReceiver proxyProvideWebAppMessagingReceiver(WebModule webModule, WebNavigator webNavigator, JavaScriptInjector javaScriptInjector, Gson gson) {
        return (WebAppMessagingReceiver) Preconditions.checkNotNull(webModule.provideWebAppMessagingReceiver(webNavigator, javaScriptInjector, gson), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WebAppMessagingReceiver mo10268get() {
        return provideInstance(this.module, this.webNavigatorProvider, this.javaScriptInjectorProvider, this.gsonProvider);
    }
}
