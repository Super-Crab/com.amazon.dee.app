package com.amazon.dee.app.dependencies;

import android.app.Activity;
import com.amazon.dee.app.ui.web.JavaScriptInjector;
import com.amazon.dee.app.ui.web.JavaScriptResponseQueue;
import com.amazon.dee.app.ui.web.VideoPlaybackBridge;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class BridgeModule_ProvideVideoPlaybackBridgeFactory implements Factory<VideoPlaybackBridge> {
    private final Provider<Activity> activityProvider;
    private final Provider<JavaScriptInjector> javaScriptInjectorProvider;
    private final Provider<JavaScriptResponseQueue> javaScriptResponseQueueProvider;
    private final BridgeModule module;

    public BridgeModule_ProvideVideoPlaybackBridgeFactory(BridgeModule bridgeModule, Provider<Activity> provider, Provider<JavaScriptInjector> provider2, Provider<JavaScriptResponseQueue> provider3) {
        this.module = bridgeModule;
        this.activityProvider = provider;
        this.javaScriptInjectorProvider = provider2;
        this.javaScriptResponseQueueProvider = provider3;
    }

    public static BridgeModule_ProvideVideoPlaybackBridgeFactory create(BridgeModule bridgeModule, Provider<Activity> provider, Provider<JavaScriptInjector> provider2, Provider<JavaScriptResponseQueue> provider3) {
        return new BridgeModule_ProvideVideoPlaybackBridgeFactory(bridgeModule, provider, provider2, provider3);
    }

    public static VideoPlaybackBridge provideInstance(BridgeModule bridgeModule, Provider<Activity> provider, Provider<JavaScriptInjector> provider2, Provider<JavaScriptResponseQueue> provider3) {
        return proxyProvideVideoPlaybackBridge(bridgeModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static VideoPlaybackBridge proxyProvideVideoPlaybackBridge(BridgeModule bridgeModule, Activity activity, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        return (VideoPlaybackBridge) Preconditions.checkNotNull(bridgeModule.provideVideoPlaybackBridge(activity, javaScriptInjector, javaScriptResponseQueue), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VideoPlaybackBridge mo10268get() {
        return provideInstance(this.module, this.activityProvider, this.javaScriptInjectorProvider, this.javaScriptResponseQueueProvider);
    }
}
