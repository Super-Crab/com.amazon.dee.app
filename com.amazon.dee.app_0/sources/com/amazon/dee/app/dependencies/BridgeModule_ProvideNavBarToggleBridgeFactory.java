package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.ui.web.JavaScriptDelegate;
import com.amazon.dee.app.ui.web.JavaScriptInjector;
import com.amazon.dee.app.ui.web.JavaScriptResponseQueue;
import com.amazon.dee.app.ui.web.NavBarToggleBridge;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class BridgeModule_ProvideNavBarToggleBridgeFactory implements Factory<NavBarToggleBridge> {
    private final Provider<JavaScriptDelegate> javaScriptDelegateProvider;
    private final Provider<JavaScriptInjector> javaScriptInjectorProvider;
    private final Provider<JavaScriptResponseQueue> javaScriptResponseQueueProvider;
    private final BridgeModule module;

    public BridgeModule_ProvideNavBarToggleBridgeFactory(BridgeModule bridgeModule, Provider<JavaScriptDelegate> provider, Provider<JavaScriptInjector> provider2, Provider<JavaScriptResponseQueue> provider3) {
        this.module = bridgeModule;
        this.javaScriptDelegateProvider = provider;
        this.javaScriptInjectorProvider = provider2;
        this.javaScriptResponseQueueProvider = provider3;
    }

    public static BridgeModule_ProvideNavBarToggleBridgeFactory create(BridgeModule bridgeModule, Provider<JavaScriptDelegate> provider, Provider<JavaScriptInjector> provider2, Provider<JavaScriptResponseQueue> provider3) {
        return new BridgeModule_ProvideNavBarToggleBridgeFactory(bridgeModule, provider, provider2, provider3);
    }

    public static NavBarToggleBridge provideInstance(BridgeModule bridgeModule, Provider<JavaScriptDelegate> provider, Provider<JavaScriptInjector> provider2, Provider<JavaScriptResponseQueue> provider3) {
        return proxyProvideNavBarToggleBridge(bridgeModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static NavBarToggleBridge proxyProvideNavBarToggleBridge(BridgeModule bridgeModule, JavaScriptDelegate javaScriptDelegate, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        return (NavBarToggleBridge) Preconditions.checkNotNull(bridgeModule.provideNavBarToggleBridge(javaScriptDelegate, javaScriptInjector, javaScriptResponseQueue), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NavBarToggleBridge mo10268get() {
        return provideInstance(this.module, this.javaScriptDelegateProvider, this.javaScriptInjectorProvider, this.javaScriptResponseQueueProvider);
    }
}
