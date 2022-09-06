package com.amazon.dee.app.dependencies;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.dee.app.ui.web.EventBusWebViewBridge;
import com.amazon.dee.app.ui.web.JavaScriptInjector;
import com.amazon.dee.app.ui.web.JavaScriptResponseQueue;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class BridgeModule_ProvideEventBusWebViewBridgeFactory implements Factory<EventBusWebViewBridge> {
    private final Provider<EventBus> eventBusProvider;
    private final Provider<JavaScriptInjector> javaScriptInjectorProvider;
    private final Provider<JavaScriptResponseQueue> javaScriptResponseQueueProvider;
    private final BridgeModule module;

    public BridgeModule_ProvideEventBusWebViewBridgeFactory(BridgeModule bridgeModule, Provider<JavaScriptInjector> provider, Provider<JavaScriptResponseQueue> provider2, Provider<EventBus> provider3) {
        this.module = bridgeModule;
        this.javaScriptInjectorProvider = provider;
        this.javaScriptResponseQueueProvider = provider2;
        this.eventBusProvider = provider3;
    }

    public static BridgeModule_ProvideEventBusWebViewBridgeFactory create(BridgeModule bridgeModule, Provider<JavaScriptInjector> provider, Provider<JavaScriptResponseQueue> provider2, Provider<EventBus> provider3) {
        return new BridgeModule_ProvideEventBusWebViewBridgeFactory(bridgeModule, provider, provider2, provider3);
    }

    public static EventBusWebViewBridge provideInstance(BridgeModule bridgeModule, Provider<JavaScriptInjector> provider, Provider<JavaScriptResponseQueue> provider2, Provider<EventBus> provider3) {
        return proxyProvideEventBusWebViewBridge(bridgeModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static EventBusWebViewBridge proxyProvideEventBusWebViewBridge(BridgeModule bridgeModule, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue, EventBus eventBus) {
        return (EventBusWebViewBridge) Preconditions.checkNotNull(bridgeModule.provideEventBusWebViewBridge(javaScriptInjector, javaScriptResponseQueue, eventBus), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EventBusWebViewBridge mo10268get() {
        return provideInstance(this.module, this.javaScriptInjectorProvider, this.javaScriptResponseQueueProvider, this.eventBusProvider);
    }
}
