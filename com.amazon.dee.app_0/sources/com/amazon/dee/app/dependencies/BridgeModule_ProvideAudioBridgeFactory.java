package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.dee.app.ui.web.AudioBridge;
import com.amazon.dee.app.ui.web.JavaScriptInjector;
import com.amazon.dee.app.ui.web.JavaScriptPlayer;
import com.amazon.dee.app.ui.web.JavaScriptResponseQueue;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class BridgeModule_ProvideAudioBridgeFactory implements Factory<AudioBridge> {
    private final Provider<Context> contextProvider;
    private final Provider<JavaScriptInjector> javaScriptInjectorProvider;
    private final Provider<JavaScriptPlayer> javaScriptPlayerProvider;
    private final Provider<JavaScriptResponseQueue> javaScriptResponseQueueProvider;
    private final BridgeModule module;

    public BridgeModule_ProvideAudioBridgeFactory(BridgeModule bridgeModule, Provider<Context> provider, Provider<JavaScriptPlayer> provider2, Provider<JavaScriptInjector> provider3, Provider<JavaScriptResponseQueue> provider4) {
        this.module = bridgeModule;
        this.contextProvider = provider;
        this.javaScriptPlayerProvider = provider2;
        this.javaScriptInjectorProvider = provider3;
        this.javaScriptResponseQueueProvider = provider4;
    }

    public static BridgeModule_ProvideAudioBridgeFactory create(BridgeModule bridgeModule, Provider<Context> provider, Provider<JavaScriptPlayer> provider2, Provider<JavaScriptInjector> provider3, Provider<JavaScriptResponseQueue> provider4) {
        return new BridgeModule_ProvideAudioBridgeFactory(bridgeModule, provider, provider2, provider3, provider4);
    }

    public static AudioBridge provideInstance(BridgeModule bridgeModule, Provider<Context> provider, Provider<JavaScriptPlayer> provider2, Provider<JavaScriptInjector> provider3, Provider<JavaScriptResponseQueue> provider4) {
        return proxyProvideAudioBridge(bridgeModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static AudioBridge proxyProvideAudioBridge(BridgeModule bridgeModule, Context context, JavaScriptPlayer javaScriptPlayer, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        return (AudioBridge) Preconditions.checkNotNull(bridgeModule.provideAudioBridge(context, javaScriptPlayer, javaScriptInjector, javaScriptResponseQueue), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AudioBridge mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.javaScriptPlayerProvider, this.javaScriptInjectorProvider, this.javaScriptResponseQueueProvider);
    }
}
