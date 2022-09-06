package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.ui.menu.AlexaMenu;
import com.amazon.dee.app.ui.web.JavaScriptInjector;
import com.amazon.dee.app.ui.web.JavaScriptResponseQueue;
import com.amazon.dee.app.ui.web.MenuSettingsBridge;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class BridgeModule_ProvideMenuSettingsBridgeFactory implements Factory<MenuSettingsBridge> {
    private final Provider<AlexaMenu> alexaMenuProvider;
    private final Provider<JavaScriptInjector> javaScriptInjectorProvider;
    private final Provider<JavaScriptResponseQueue> javaScriptResponseQueueProvider;
    private final BridgeModule module;

    public BridgeModule_ProvideMenuSettingsBridgeFactory(BridgeModule bridgeModule, Provider<JavaScriptInjector> provider, Provider<JavaScriptResponseQueue> provider2, Provider<AlexaMenu> provider3) {
        this.module = bridgeModule;
        this.javaScriptInjectorProvider = provider;
        this.javaScriptResponseQueueProvider = provider2;
        this.alexaMenuProvider = provider3;
    }

    public static BridgeModule_ProvideMenuSettingsBridgeFactory create(BridgeModule bridgeModule, Provider<JavaScriptInjector> provider, Provider<JavaScriptResponseQueue> provider2, Provider<AlexaMenu> provider3) {
        return new BridgeModule_ProvideMenuSettingsBridgeFactory(bridgeModule, provider, provider2, provider3);
    }

    public static MenuSettingsBridge provideInstance(BridgeModule bridgeModule, Provider<JavaScriptInjector> provider, Provider<JavaScriptResponseQueue> provider2, Provider<AlexaMenu> provider3) {
        return proxyProvideMenuSettingsBridge(bridgeModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static MenuSettingsBridge proxyProvideMenuSettingsBridge(BridgeModule bridgeModule, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue, AlexaMenu alexaMenu) {
        return (MenuSettingsBridge) Preconditions.checkNotNull(bridgeModule.provideMenuSettingsBridge(javaScriptInjector, javaScriptResponseQueue, alexaMenu), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MenuSettingsBridge mo10268get() {
        return provideInstance(this.module, this.javaScriptInjectorProvider, this.javaScriptResponseQueueProvider, this.alexaMenuProvider);
    }
}
