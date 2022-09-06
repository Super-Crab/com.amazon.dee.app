package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.ui.web.JavaScriptInjector;
import com.amazon.dee.app.ui.web.JavaScriptResponseQueue;
import com.amazon.dee.app.ui.web.TachyonSettingsBridge;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.settings.IdentityPreferencesProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class BridgeModule_ProvideTachyonSettingsBridgeFactory implements Factory<TachyonSettingsBridge> {
    private final Provider<CommsManager> commsManagerProvider;
    private final Provider<JavaScriptInjector> javaScriptInjectorProvider;
    private final Provider<JavaScriptResponseQueue> javaScriptResponseQueueProvider;
    private final BridgeModule module;
    private final Provider<IdentityPreferencesProvider> preferencesProvider;

    public BridgeModule_ProvideTachyonSettingsBridgeFactory(BridgeModule bridgeModule, Provider<JavaScriptInjector> provider, Provider<JavaScriptResponseQueue> provider2, Provider<CommsManager> provider3, Provider<IdentityPreferencesProvider> provider4) {
        this.module = bridgeModule;
        this.javaScriptInjectorProvider = provider;
        this.javaScriptResponseQueueProvider = provider2;
        this.commsManagerProvider = provider3;
        this.preferencesProvider = provider4;
    }

    public static BridgeModule_ProvideTachyonSettingsBridgeFactory create(BridgeModule bridgeModule, Provider<JavaScriptInjector> provider, Provider<JavaScriptResponseQueue> provider2, Provider<CommsManager> provider3, Provider<IdentityPreferencesProvider> provider4) {
        return new BridgeModule_ProvideTachyonSettingsBridgeFactory(bridgeModule, provider, provider2, provider3, provider4);
    }

    public static TachyonSettingsBridge provideInstance(BridgeModule bridgeModule, Provider<JavaScriptInjector> provider, Provider<JavaScriptResponseQueue> provider2, Provider<CommsManager> provider3, Provider<IdentityPreferencesProvider> provider4) {
        return proxyProvideTachyonSettingsBridge(bridgeModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static TachyonSettingsBridge proxyProvideTachyonSettingsBridge(BridgeModule bridgeModule, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue, CommsManager commsManager, IdentityPreferencesProvider identityPreferencesProvider) {
        return (TachyonSettingsBridge) Preconditions.checkNotNull(bridgeModule.provideTachyonSettingsBridge(javaScriptInjector, javaScriptResponseQueue, commsManager, identityPreferencesProvider), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TachyonSettingsBridge mo10268get() {
        return provideInstance(this.module, this.javaScriptInjectorProvider, this.javaScriptResponseQueueProvider, this.commsManagerProvider, this.preferencesProvider);
    }
}
