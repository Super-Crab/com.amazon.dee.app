package com.amazon.dee.app.dependencies;

import com.amazon.alexa.ttcf.TTCFService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class TTCFModule_ProvideTTCFServiceFactory implements Factory<TTCFService> {
    private final TTCFModule module;

    public TTCFModule_ProvideTTCFServiceFactory(TTCFModule tTCFModule) {
        this.module = tTCFModule;
    }

    public static TTCFModule_ProvideTTCFServiceFactory create(TTCFModule tTCFModule) {
        return new TTCFModule_ProvideTTCFServiceFactory(tTCFModule);
    }

    public static TTCFService provideInstance(TTCFModule tTCFModule) {
        return proxyProvideTTCFService(tTCFModule);
    }

    public static TTCFService proxyProvideTTCFService(TTCFModule tTCFModule) {
        return (TTCFService) Preconditions.checkNotNull(tTCFModule.provideTTCFService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TTCFService mo10268get() {
        return provideInstance(this.module);
    }
}
