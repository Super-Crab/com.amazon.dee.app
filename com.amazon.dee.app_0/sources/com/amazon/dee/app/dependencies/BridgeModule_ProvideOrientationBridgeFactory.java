package com.amazon.dee.app.dependencies;

import android.app.Activity;
import com.amazon.dee.app.ui.web.JavaScriptInjector;
import com.amazon.dee.app.ui.web.JavaScriptResponseQueue;
import com.amazon.dee.app.ui.web.OrientationBridge;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class BridgeModule_ProvideOrientationBridgeFactory implements Factory<OrientationBridge> {
    private final Provider<Activity> activityProvider;
    private final Provider<JavaScriptInjector> javaScriptInjectorProvider;
    private final Provider<JavaScriptResponseQueue> javaScriptResponseQueueProvider;
    private final BridgeModule module;

    public BridgeModule_ProvideOrientationBridgeFactory(BridgeModule bridgeModule, Provider<Activity> provider, Provider<JavaScriptInjector> provider2, Provider<JavaScriptResponseQueue> provider3) {
        this.module = bridgeModule;
        this.activityProvider = provider;
        this.javaScriptInjectorProvider = provider2;
        this.javaScriptResponseQueueProvider = provider3;
    }

    public static BridgeModule_ProvideOrientationBridgeFactory create(BridgeModule bridgeModule, Provider<Activity> provider, Provider<JavaScriptInjector> provider2, Provider<JavaScriptResponseQueue> provider3) {
        return new BridgeModule_ProvideOrientationBridgeFactory(bridgeModule, provider, provider2, provider3);
    }

    public static OrientationBridge provideInstance(BridgeModule bridgeModule, Provider<Activity> provider, Provider<JavaScriptInjector> provider2, Provider<JavaScriptResponseQueue> provider3) {
        return proxyProvideOrientationBridge(bridgeModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static OrientationBridge proxyProvideOrientationBridge(BridgeModule bridgeModule, Activity activity, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        return (OrientationBridge) Preconditions.checkNotNull(bridgeModule.provideOrientationBridge(activity, javaScriptInjector, javaScriptResponseQueue), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public OrientationBridge mo10268get() {
        return provideInstance(this.module, this.activityProvider, this.javaScriptInjectorProvider, this.javaScriptResponseQueueProvider);
    }
}
