package com.amazon.dee.app.dependencies;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.dee.app.ui.web.JavaScriptInjector;
import com.amazon.dee.app.ui.web.WebNavigator;
import com.amazon.dee.app.ui.web.WebViewDelegate;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class WebModule_ProvideWebNavigatorFactory implements Factory<WebNavigator> {
    private final Provider<EnvironmentService> environmentServiceProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<JavaScriptInjector> javaScriptInjectorProvider;
    private final WebModule module;
    private final Provider<WebViewDelegate> webViewDelegateProvider;

    public WebModule_ProvideWebNavigatorFactory(WebModule webModule, Provider<WebViewDelegate> provider, Provider<JavaScriptInjector> provider2, Provider<EnvironmentService> provider3, Provider<IdentityService> provider4, Provider<EventBus> provider5) {
        this.module = webModule;
        this.webViewDelegateProvider = provider;
        this.javaScriptInjectorProvider = provider2;
        this.environmentServiceProvider = provider3;
        this.identityServiceProvider = provider4;
        this.eventBusProvider = provider5;
    }

    public static WebModule_ProvideWebNavigatorFactory create(WebModule webModule, Provider<WebViewDelegate> provider, Provider<JavaScriptInjector> provider2, Provider<EnvironmentService> provider3, Provider<IdentityService> provider4, Provider<EventBus> provider5) {
        return new WebModule_ProvideWebNavigatorFactory(webModule, provider, provider2, provider3, provider4, provider5);
    }

    public static WebNavigator provideInstance(WebModule webModule, Provider<WebViewDelegate> provider, Provider<JavaScriptInjector> provider2, Provider<EnvironmentService> provider3, Provider<IdentityService> provider4, Provider<EventBus> provider5) {
        return proxyProvideWebNavigator(webModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get());
    }

    public static WebNavigator proxyProvideWebNavigator(WebModule webModule, WebViewDelegate webViewDelegate, JavaScriptInjector javaScriptInjector, EnvironmentService environmentService, IdentityService identityService, EventBus eventBus) {
        return (WebNavigator) Preconditions.checkNotNull(webModule.provideWebNavigator(webViewDelegate, javaScriptInjector, environmentService, identityService, eventBus), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WebNavigator mo10268get() {
        return provideInstance(this.module, this.webViewDelegateProvider, this.javaScriptInjectorProvider, this.environmentServiceProvider, this.identityServiceProvider, this.eventBusProvider);
    }
}
