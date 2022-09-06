package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.ui.web.JavaScriptResponseQueue;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class WebModule_ProvideJavaScriptResponseQueueFactory implements Factory<JavaScriptResponseQueue> {
    private final WebModule module;

    public WebModule_ProvideJavaScriptResponseQueueFactory(WebModule webModule) {
        this.module = webModule;
    }

    public static WebModule_ProvideJavaScriptResponseQueueFactory create(WebModule webModule) {
        return new WebModule_ProvideJavaScriptResponseQueueFactory(webModule);
    }

    public static JavaScriptResponseQueue provideInstance(WebModule webModule) {
        return proxyProvideJavaScriptResponseQueue(webModule);
    }

    public static JavaScriptResponseQueue proxyProvideJavaScriptResponseQueue(WebModule webModule) {
        return (JavaScriptResponseQueue) Preconditions.checkNotNull(webModule.provideJavaScriptResponseQueue(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public JavaScriptResponseQueue mo10268get() {
        return provideInstance(this.module);
    }
}
