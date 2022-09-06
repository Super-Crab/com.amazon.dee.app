package com.amazon.dee.app.dependencies;

import com.amazon.alexa.ttcf.TTCFService;
import com.amazon.alexa.ttcf.api.TTCFCheckpoint;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class TTCFModule_ProvideTTCFCheckpointFactory implements Factory<TTCFCheckpoint> {
    private final TTCFModule module;
    private final Provider<TTCFService> ttcfServiceProvider;

    public TTCFModule_ProvideTTCFCheckpointFactory(TTCFModule tTCFModule, Provider<TTCFService> provider) {
        this.module = tTCFModule;
        this.ttcfServiceProvider = provider;
    }

    public static TTCFModule_ProvideTTCFCheckpointFactory create(TTCFModule tTCFModule, Provider<TTCFService> provider) {
        return new TTCFModule_ProvideTTCFCheckpointFactory(tTCFModule, provider);
    }

    public static TTCFCheckpoint provideInstance(TTCFModule tTCFModule, Provider<TTCFService> provider) {
        return proxyProvideTTCFCheckpoint(tTCFModule, provider.mo10268get());
    }

    public static TTCFCheckpoint proxyProvideTTCFCheckpoint(TTCFModule tTCFModule, TTCFService tTCFService) {
        return (TTCFCheckpoint) Preconditions.checkNotNull(tTCFModule.provideTTCFCheckpoint(tTCFService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TTCFCheckpoint mo10268get() {
        return provideInstance(this.module, this.ttcfServiceProvider);
    }
}
