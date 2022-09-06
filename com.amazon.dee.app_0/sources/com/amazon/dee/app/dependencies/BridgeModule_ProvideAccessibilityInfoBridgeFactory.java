package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.dee.app.ui.web.AccessibilityInfoBridge;
import com.amazon.dee.app.ui.web.JavaScriptInjector;
import com.amazon.dee.app.ui.web.JavaScriptResponseQueue;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class BridgeModule_ProvideAccessibilityInfoBridgeFactory implements Factory<AccessibilityInfoBridge> {
    private final Provider<Context> contextProvider;
    private final Provider<JavaScriptInjector> javaScriptInjectorProvider;
    private final Provider<JavaScriptResponseQueue> javaScriptResponseQueueProvider;
    private final BridgeModule module;

    public BridgeModule_ProvideAccessibilityInfoBridgeFactory(BridgeModule bridgeModule, Provider<Context> provider, Provider<JavaScriptInjector> provider2, Provider<JavaScriptResponseQueue> provider3) {
        this.module = bridgeModule;
        this.contextProvider = provider;
        this.javaScriptInjectorProvider = provider2;
        this.javaScriptResponseQueueProvider = provider3;
    }

    public static BridgeModule_ProvideAccessibilityInfoBridgeFactory create(BridgeModule bridgeModule, Provider<Context> provider, Provider<JavaScriptInjector> provider2, Provider<JavaScriptResponseQueue> provider3) {
        return new BridgeModule_ProvideAccessibilityInfoBridgeFactory(bridgeModule, provider, provider2, provider3);
    }

    public static AccessibilityInfoBridge provideInstance(BridgeModule bridgeModule, Provider<Context> provider, Provider<JavaScriptInjector> provider2, Provider<JavaScriptResponseQueue> provider3) {
        return proxyProvideAccessibilityInfoBridge(bridgeModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static AccessibilityInfoBridge proxyProvideAccessibilityInfoBridge(BridgeModule bridgeModule, Context context, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        return (AccessibilityInfoBridge) Preconditions.checkNotNull(bridgeModule.provideAccessibilityInfoBridge(context, javaScriptInjector, javaScriptResponseQueue), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AccessibilityInfoBridge mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.javaScriptInjectorProvider, this.javaScriptResponseQueueProvider);
    }
}
