package com.amazon.dee.app.dependencies;

import com.amazon.alexa.viewmanagement.impl.ReactNativeViewManager;
import com.amazon.dee.app.elements.ReactBridgeService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ViewManagerModule_ProvideReactNativeViewManagerFactory implements Factory<ReactNativeViewManager> {
    private final ViewManagerModule module;
    private final Provider<ReactBridgeService> reactBridgeProvider;

    public ViewManagerModule_ProvideReactNativeViewManagerFactory(ViewManagerModule viewManagerModule, Provider<ReactBridgeService> provider) {
        this.module = viewManagerModule;
        this.reactBridgeProvider = provider;
    }

    public static ViewManagerModule_ProvideReactNativeViewManagerFactory create(ViewManagerModule viewManagerModule, Provider<ReactBridgeService> provider) {
        return new ViewManagerModule_ProvideReactNativeViewManagerFactory(viewManagerModule, provider);
    }

    public static ReactNativeViewManager provideInstance(ViewManagerModule viewManagerModule, Provider<ReactBridgeService> provider) {
        return proxyProvideReactNativeViewManager(viewManagerModule, DoubleCheck.lazy(provider));
    }

    public static ReactNativeViewManager proxyProvideReactNativeViewManager(ViewManagerModule viewManagerModule, Lazy<ReactBridgeService> lazy) {
        return (ReactNativeViewManager) Preconditions.checkNotNull(viewManagerModule.provideReactNativeViewManager(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ReactNativeViewManager mo10268get() {
        return provideInstance(this.module, this.reactBridgeProvider);
    }
}
