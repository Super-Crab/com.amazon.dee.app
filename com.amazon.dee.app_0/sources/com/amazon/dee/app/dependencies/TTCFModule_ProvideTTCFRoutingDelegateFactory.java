package com.amazon.dee.app.dependencies;

import com.amazon.alexa.ttcf.TTCFService;
import com.amazon.alexa.ttcf.api.TTCFRoutingDelegate;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class TTCFModule_ProvideTTCFRoutingDelegateFactory implements Factory<TTCFRoutingDelegate> {
    private final TTCFModule module;
    private final Provider<TTCFService> ttcfServiceProvider;

    public TTCFModule_ProvideTTCFRoutingDelegateFactory(TTCFModule tTCFModule, Provider<TTCFService> provider) {
        this.module = tTCFModule;
        this.ttcfServiceProvider = provider;
    }

    public static TTCFModule_ProvideTTCFRoutingDelegateFactory create(TTCFModule tTCFModule, Provider<TTCFService> provider) {
        return new TTCFModule_ProvideTTCFRoutingDelegateFactory(tTCFModule, provider);
    }

    public static TTCFRoutingDelegate provideInstance(TTCFModule tTCFModule, Provider<TTCFService> provider) {
        return proxyProvideTTCFRoutingDelegate(tTCFModule, provider.mo10268get());
    }

    public static TTCFRoutingDelegate proxyProvideTTCFRoutingDelegate(TTCFModule tTCFModule, TTCFService tTCFService) {
        return (TTCFRoutingDelegate) Preconditions.checkNotNull(tTCFModule.provideTTCFRoutingDelegate(tTCFService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TTCFRoutingDelegate mo10268get() {
        return provideInstance(this.module, this.ttcfServiceProvider);
    }
}
