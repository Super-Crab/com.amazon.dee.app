package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.dee.app.ui.web.JavaScriptInjector;
import com.amazon.dee.app.ui.web.JavaScriptResponseQueue;
import com.amazon.dee.app.ui.web.NotificationServiceBridge;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class BridgeModule_ProvideNotificationServiceBridgeFactory implements Factory<NotificationServiceBridge> {
    private final Provider<Context> contextProvider;
    private final Provider<JavaScriptInjector> javaScriptInjectorProvider;
    private final Provider<JavaScriptResponseQueue> javaScriptResponseQueueProvider;
    private final BridgeModule module;

    public BridgeModule_ProvideNotificationServiceBridgeFactory(BridgeModule bridgeModule, Provider<Context> provider, Provider<JavaScriptInjector> provider2, Provider<JavaScriptResponseQueue> provider3) {
        this.module = bridgeModule;
        this.contextProvider = provider;
        this.javaScriptInjectorProvider = provider2;
        this.javaScriptResponseQueueProvider = provider3;
    }

    public static BridgeModule_ProvideNotificationServiceBridgeFactory create(BridgeModule bridgeModule, Provider<Context> provider, Provider<JavaScriptInjector> provider2, Provider<JavaScriptResponseQueue> provider3) {
        return new BridgeModule_ProvideNotificationServiceBridgeFactory(bridgeModule, provider, provider2, provider3);
    }

    public static NotificationServiceBridge provideInstance(BridgeModule bridgeModule, Provider<Context> provider, Provider<JavaScriptInjector> provider2, Provider<JavaScriptResponseQueue> provider3) {
        return proxyProvideNotificationServiceBridge(bridgeModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static NotificationServiceBridge proxyProvideNotificationServiceBridge(BridgeModule bridgeModule, Context context, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        return (NotificationServiceBridge) Preconditions.checkNotNull(bridgeModule.provideNotificationServiceBridge(context, javaScriptInjector, javaScriptResponseQueue), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NotificationServiceBridge mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.javaScriptInjectorProvider, this.javaScriptResponseQueueProvider);
    }
}
