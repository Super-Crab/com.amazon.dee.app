package com.amazon.dee.app.dependencies;

import com.amazon.alexa.logupload.LogRetriever;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class BridgeModule_ProvideLogRetrieverFactory implements Factory<LogRetriever> {
    private final BridgeModule module;

    public BridgeModule_ProvideLogRetrieverFactory(BridgeModule bridgeModule) {
        this.module = bridgeModule;
    }

    public static BridgeModule_ProvideLogRetrieverFactory create(BridgeModule bridgeModule) {
        return new BridgeModule_ProvideLogRetrieverFactory(bridgeModule);
    }

    public static LogRetriever provideInstance(BridgeModule bridgeModule) {
        return proxyProvideLogRetriever(bridgeModule);
    }

    public static LogRetriever proxyProvideLogRetriever(BridgeModule bridgeModule) {
        return (LogRetriever) Preconditions.checkNotNull(bridgeModule.provideLogRetriever(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public LogRetriever mo10268get() {
        return provideInstance(this.module);
    }
}
