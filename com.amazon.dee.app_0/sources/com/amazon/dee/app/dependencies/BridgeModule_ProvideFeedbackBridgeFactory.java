package com.amazon.dee.app.dependencies;

import android.app.Activity;
import com.amazon.alexa.logupload.LogRetriever;
import com.amazon.dee.app.ui.web.FeedbackBridge;
import com.amazon.dee.app.ui.web.JavaScriptInjector;
import com.amazon.dee.app.ui.web.JavaScriptResponseQueue;
import com.dee.app.http.CoralService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class BridgeModule_ProvideFeedbackBridgeFactory implements Factory<FeedbackBridge> {
    private final Provider<Activity> activityProvider;
    private final Provider<CoralService> coralServiceProvider;
    private final Provider<JavaScriptInjector> javaScriptInjectorProvider;
    private final Provider<JavaScriptResponseQueue> javaScriptResponseQueueProvider;
    private final Provider<LogRetriever> logRetrieverProvider;
    private final BridgeModule module;

    public BridgeModule_ProvideFeedbackBridgeFactory(BridgeModule bridgeModule, Provider<Activity> provider, Provider<JavaScriptInjector> provider2, Provider<JavaScriptResponseQueue> provider3, Provider<CoralService> provider4, Provider<LogRetriever> provider5) {
        this.module = bridgeModule;
        this.activityProvider = provider;
        this.javaScriptInjectorProvider = provider2;
        this.javaScriptResponseQueueProvider = provider3;
        this.coralServiceProvider = provider4;
        this.logRetrieverProvider = provider5;
    }

    public static BridgeModule_ProvideFeedbackBridgeFactory create(BridgeModule bridgeModule, Provider<Activity> provider, Provider<JavaScriptInjector> provider2, Provider<JavaScriptResponseQueue> provider3, Provider<CoralService> provider4, Provider<LogRetriever> provider5) {
        return new BridgeModule_ProvideFeedbackBridgeFactory(bridgeModule, provider, provider2, provider3, provider4, provider5);
    }

    public static FeedbackBridge provideInstance(BridgeModule bridgeModule, Provider<Activity> provider, Provider<JavaScriptInjector> provider2, Provider<JavaScriptResponseQueue> provider3, Provider<CoralService> provider4, Provider<LogRetriever> provider5) {
        return proxyProvideFeedbackBridge(bridgeModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get());
    }

    public static FeedbackBridge proxyProvideFeedbackBridge(BridgeModule bridgeModule, Activity activity, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue, CoralService coralService, LogRetriever logRetriever) {
        return (FeedbackBridge) Preconditions.checkNotNull(bridgeModule.provideFeedbackBridge(activity, javaScriptInjector, javaScriptResponseQueue, coralService, logRetriever), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeedbackBridge mo10268get() {
        return provideInstance(this.module, this.activityProvider, this.javaScriptInjectorProvider, this.javaScriptResponseQueueProvider, this.coralServiceProvider, this.logRetrieverProvider);
    }
}
