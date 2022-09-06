package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.ui.web.WebViewDelegate;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class WebModule_ProvideWebViewDelegateFactory implements Factory<WebViewDelegate> {
    private final WebModule module;

    public WebModule_ProvideWebViewDelegateFactory(WebModule webModule) {
        this.module = webModule;
    }

    public static WebModule_ProvideWebViewDelegateFactory create(WebModule webModule) {
        return new WebModule_ProvideWebViewDelegateFactory(webModule);
    }

    public static WebViewDelegate provideInstance(WebModule webModule) {
        return proxyProvideWebViewDelegate(webModule);
    }

    public static WebViewDelegate proxyProvideWebViewDelegate(WebModule webModule) {
        return (WebViewDelegate) Preconditions.checkNotNull(webModule.provideWebViewDelegate(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WebViewDelegate mo10268get() {
        return provideInstance(this.module);
    }
}
