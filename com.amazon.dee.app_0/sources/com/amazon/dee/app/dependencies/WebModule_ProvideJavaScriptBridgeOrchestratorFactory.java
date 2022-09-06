package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.ui.web.JavaScriptBridgeOrchestrator;
import com.amazon.dee.app.ui.web.JavaScriptResponseQueue;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class WebModule_ProvideJavaScriptBridgeOrchestratorFactory implements Factory<JavaScriptBridgeOrchestrator> {
    private final WebModule module;
    private final Provider<JavaScriptResponseQueue> queueProvider;

    public WebModule_ProvideJavaScriptBridgeOrchestratorFactory(WebModule webModule, Provider<JavaScriptResponseQueue> provider) {
        this.module = webModule;
        this.queueProvider = provider;
    }

    public static WebModule_ProvideJavaScriptBridgeOrchestratorFactory create(WebModule webModule, Provider<JavaScriptResponseQueue> provider) {
        return new WebModule_ProvideJavaScriptBridgeOrchestratorFactory(webModule, provider);
    }

    public static JavaScriptBridgeOrchestrator provideInstance(WebModule webModule, Provider<JavaScriptResponseQueue> provider) {
        return proxyProvideJavaScriptBridgeOrchestrator(webModule, provider.mo10268get());
    }

    public static JavaScriptBridgeOrchestrator proxyProvideJavaScriptBridgeOrchestrator(WebModule webModule, JavaScriptResponseQueue javaScriptResponseQueue) {
        return (JavaScriptBridgeOrchestrator) Preconditions.checkNotNull(webModule.provideJavaScriptBridgeOrchestrator(javaScriptResponseQueue), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public JavaScriptBridgeOrchestrator mo10268get() {
        return provideInstance(this.module, this.queueProvider);
    }
}
