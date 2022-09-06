package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.ui.web.JavaScriptDelegate;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class WebModule_ProvideJavaScriptDelegateFactory implements Factory<JavaScriptDelegate> {
    private final WebModule module;

    public WebModule_ProvideJavaScriptDelegateFactory(WebModule webModule) {
        this.module = webModule;
    }

    public static WebModule_ProvideJavaScriptDelegateFactory create(WebModule webModule) {
        return new WebModule_ProvideJavaScriptDelegateFactory(webModule);
    }

    public static JavaScriptDelegate provideInstance(WebModule webModule) {
        return proxyProvideJavaScriptDelegate(webModule);
    }

    public static JavaScriptDelegate proxyProvideJavaScriptDelegate(WebModule webModule) {
        return (JavaScriptDelegate) Preconditions.checkNotNull(webModule.provideJavaScriptDelegate(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public JavaScriptDelegate mo10268get() {
        return provideInstance(this.module);
    }
}
